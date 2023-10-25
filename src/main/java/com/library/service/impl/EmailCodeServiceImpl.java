package com.library.service.impl;

import com.library.entity.config.AppConfig;
import com.library.entity.constants.Constants;
import com.library.entity.po.EmailCode;
import com.library.entity.po.UserInfo;
import com.library.entity.query.EmailCodeQuery;
import com.library.entity.query.SimplePage;
import com.library.entity.query.UserInfoQuery;
import com.library.entity.vo.PaginationResultVO;
import com.library.enums.PageSize;
import com.library.exception.BusinessException;
import com.library.mappers.EmailCodeMapper;
import com.library.mappers.UserInfoMapper;
import com.library.service.EmailCodeService;
import com.library.utils.StringTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;

/**
 * @Description: 邮箱验证码ServiceImpl
 * @author: luceln
 * @date: 2023/09/24
 */
@Service("emailCodeService")
public class EmailCodeServiceImpl implements EmailCodeService {

	private static final Logger logger = LoggerFactory.getLogger(EmailCodeServiceImpl.class);

	@Resource
	private EmailCodeMapper<EmailCode, EmailCodeQuery> emailCodeMapper;

	@Resource
	private UserInfoMapper<UserInfo, UserInfoQuery> userInfoMapper;

	@Resource
	private JavaMailSender javaMailSender;

	@Resource
	private AppConfig appConfig;

	/**
	 * 根据条件查询列表
	 */
	public List<EmailCode> findListByParam(EmailCodeQuery query) {
		return this.emailCodeMapper.selectList(query);
	}

	/**
	 * 根据条件查询数量
	 */
	public Integer findCountByParam(EmailCodeQuery query) {
		return this.emailCodeMapper.selectCount(query);
	}

	/**
	 * 分页查询
	 */
	public PaginationResultVO<EmailCode> findListByPage(EmailCodeQuery query) {
		Integer count = this.findCountByParam(query);
		Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
		SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
		query.setSimplePage(page);
		List<EmailCode> list = this.findListByParam(query);
		PaginationResultVO<EmailCode> result = new PaginationResultVO<>(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	public Integer add(EmailCode bean) {
		return this.emailCodeMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	public Integer addBatch(List<EmailCode> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.emailCodeMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或修改
	 */
	public Integer addOrUpdateBatch(List<EmailCode> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.emailCodeMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 根据EmailAndCode查询
	 */
	public EmailCode getEmailCodeByEmailAndCode(String email, String code) {
		return this.emailCodeMapper.selectByEmailAndCode(email, code);
	}

	/**
	 * 根据EmailAndCode更新
	 */
	public Integer updateEmailCodeByEmailAndCode(EmailCode bean, String email, String code) {
		return this.emailCodeMapper.updateByEmailAndCode(bean, email, code);
	}

	/**
	 * 根据EmailAndCode删除
	 */
	public Integer deleteEmailCodeByEmailAndCode(String email, String code) {
		return this.emailCodeMapper.deleteByEmailAndCode(email, code);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void sendEmailCode(String email, Integer type) {
		if (type == Constants.ZERO) {  // 注册
			UserInfo userInfo = userInfoMapper.selectByEmail(email);
			if (null != userInfo) {
				throw new BusinessException("邮箱已经存在");
			}
		}
		String code = StringTools.getRandomNumber(Constants.LENGTH_5);
		sendEmailCode(email, code);

		// 将之前的验证码置为无效
		emailCodeMapper.disableEmailCode(email);

		EmailCode emailCode = new EmailCode();
		emailCode.setCode(code);
		emailCode.setEmail(email);
		emailCode.setStatus(Constants.ZERO);  // 0：验证码未使用
		emailCode.setCreateTime(new Date());
		emailCodeMapper.insert(emailCode);
	}

	private void sendEmailCode(String toEmail, String code) {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(appConfig.getSendUserName());  // 邮件发送人
			helper.setTo(toEmail);  // 邮件收件人 1或多个
			// 邮件主题
			helper.setSubject(appConfig.getRegisterEmailTitle());
			// 邮件内容
			helper.setText(String.format(appConfig.getRegisterEmailContent(), code));
			// 邮件发送时间
			helper.setSentDate(new Date());
			javaMailSender.send(message);
		} catch (Exception e) {
			logger.error("邮件发送失败", e);
			throw new BusinessException("邮件发送失败");
		}
	}

	@Override
	public void checkCode(String email, String code) {
		EmailCode emailCode = this.emailCodeMapper.selectByEmailAndCode(email, code);
		if (null == emailCode) {
			throw new BusinessException("邮箱验证码不正确");
		}
		//  检查邮箱验证码是否已使用或者过期
		if (emailCode.getStatus() == 1
				|| (System.currentTimeMillis() - emailCode.getCreateTime().getTime()) > Constants.LENGTH_15 * 1000 * 60) {
			throw new BusinessException("邮箱验证码已失效");
		}

		// 已验证的邮箱验证码将其失效
		emailCodeMapper.disableEmailCode(email);
	}
}