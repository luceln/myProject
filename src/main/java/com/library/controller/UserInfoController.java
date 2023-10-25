package com.library.controller;

import com.library.annotation.GlobalInterceptor;
import com.library.annotation.VerifyParam;
import com.library.entity.constants.Constants;
import com.library.entity.dto.CreateImageCode;
import com.library.entity.dto.SessionWebUserDto;
import com.library.entity.vo.ResponseVO;
import com.library.enums.VerifyRegexEnum;
import com.library.exception.BusinessException;
import com.library.service.EmailCodeService;
import com.library.service.UserInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Description: 用户信息Controller
 * @author: luceln
 * @date: 2023/10/23
 */
@RestController
public class UserInfoController extends ABaseController {

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private EmailCodeService emailCodeService;

    @RequestMapping("/checkCode")
    public void checkCode(HttpServletResponse response,
                          HttpSession session,
                          Integer type) throws IOException {
        CreateImageCode imageCode = new CreateImageCode(130, 38, 5, 10);
        // 不缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        String code = imageCode.getCode();
        // 注册登录图片验证码
        if (type == null || type == 0) {
            session.setAttribute(Constants.CHECK_CODE_KEY, code);
        } else {  // 邮箱发送前图片验证码
            session.setAttribute(Constants.CHECK_CODE_KEY_EMAIL, code);
        }
        imageCode.write(response.getOutputStream());
    }

    @RequestMapping("/login")
    @GlobalInterceptor(checkLogin = false, checkParams = true)
    public ResponseVO login(HttpSession session,
                            @VerifyParam(required = true, regex = VerifyRegexEnum.EMAIL) String email,
                            @VerifyParam(required = true, regex = VerifyRegexEnum.PASSWORD) String password,
                            @VerifyParam(required = true) String checkCode) {
        try {
            // 验证码不相同
            if (!checkCode.equalsIgnoreCase((String) session.getAttribute(Constants.CHECK_CODE_KEY))) {
                throw new BusinessException("验证码不正确");
            }
            // 保存用户信息
            SessionWebUserDto userDto = userInfoService.login(email, password);
            session.setAttribute(Constants.SESSION_KEY, userDto);
            return getSuccessResponseVO(userDto);
        } finally {
            // 失效验证码
            session.removeAttribute(Constants.CHECK_CODE_KEY);
        }
    }

    /**
     * 发送邮箱验证码
     */
    @RequestMapping("/sendEmailCode")
    @GlobalInterceptor(checkParams = true, checkLogin = false)
    public ResponseVO sendEmailCode(HttpSession session,
                                    @VerifyParam(required = true, regex = VerifyRegexEnum.EMAIL, max = 150) String email,
                                    @VerifyParam(required = true) String checkCode,
                                    @VerifyParam(required = true) Integer type) {
        try {
            if (!checkCode.equalsIgnoreCase((String) session.getAttribute(Constants.CHECK_CODE_KEY_EMAIL))) {
                throw new BusinessException("图片验证码不正确");
            }
            emailCodeService.sendEmailCode(email, type);
            return getSuccessResponseVO(null);
        } finally {
            session.removeAttribute(Constants.CHECK_CODE_KEY_EMAIL);
        }
    }

    /**
     * 注册
     */
    @RequestMapping("/register")
    @GlobalInterceptor(checkParams = true, checkLogin = false)
    public ResponseVO register(HttpSession session,
                               @VerifyParam(required = true, regex = VerifyRegexEnum.EMAIL, max = 150) String email,
                               @VerifyParam(required = true) String nickName,
                               @VerifyParam(required = true, regex = VerifyRegexEnum.PASSWORD, min = 8, max = 18) String password,
                               @VerifyParam(required = true) String checkCode,
                               @VerifyParam(required = true) String emailCode) {
        try {
            if (!checkCode.equalsIgnoreCase((String) session.getAttribute(Constants.CHECK_CODE_KEY))) {
                throw new BusinessException("图片验证码不正确");
            }
            userInfoService.register(email, nickName, password, emailCode);
            return getSuccessResponseVO(null);
        } finally {
            session.removeAttribute(Constants.CHECK_CODE_KEY);
        }
    }
}