package com.library.service;



import com.library.entity.po.EmailCode;
import com.library.entity.query.EmailCodeQuery;
import com.library.entity.vo.PaginationResultVO;

import java.util.List;

/**
 * @Description: 邮箱验证码Service
 * @author: luceln
 * @date: 2023/09/24
 */
public interface EmailCodeService {

	/**
	 * 根据条件查询列表
	 */
	List<EmailCode> findListByParam(EmailCodeQuery query);

	/**
	 * 根据条件查询数量
	 */
	Integer findCountByParam(EmailCodeQuery query);

	/**
	 * 分页查询
	 */
	PaginationResultVO<EmailCode> findListByPage(EmailCodeQuery query);

	/**
	 * 新增
	 */
	Integer add(EmailCode bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<EmailCode> listBean);

	/**
	 * 批量新增或修改
	 */
	Integer addOrUpdateBatch(List<EmailCode> listBean);

	/**
	 * 根据EmailAndCode查询
	 */
	EmailCode getEmailCodeByEmailAndCode(String email, String code);

	/**
	 * 根据EmailAndCode更新
	 */
	Integer updateEmailCodeByEmailAndCode(EmailCode bean, String email, String code);

	/**
	 * 根据EmailAndCode删除
	 */
	Integer deleteEmailCodeByEmailAndCode(String email, String code);

	/**
	 * 发送邮箱验证码
	 */
	void sendEmailCode(String email, Integer type);

	void checkCode(String email, String code);
}