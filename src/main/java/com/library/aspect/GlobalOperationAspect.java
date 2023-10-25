package com.library.aspect;

import com.library.annotation.GlobalInterceptor;
import com.library.annotation.VerifyParam;
import com.library.entity.constants.Constants;
import com.library.entity.dto.SessionWebUserDto;
import com.library.enums.ResponseCodeEnum;
import com.library.exception.BusinessException;
import com.library.utils.StringTools;
import com.library.utils.VerifyUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 切面类，通过注解识别需要切入的方法
 */
@Aspect
@Component("globalOperationAspect")
public class GlobalOperationAspect {

    private static final Logger logger = LoggerFactory.getLogger(GlobalOperationAspect.class);

    /**
     * 基础类型的全限定名
     */
    private static final String[] BASE_TYPE = {
            "java.lang.String", "java.lang.Integer", "java.lang.Long"
    };

    /**
     * 在方法的前面切入，用于校验参数、校验登录
     */
    @Before("@annotation(com.library.annotation.GlobalInterceptor)")
    public void interceptorDo(JoinPoint point) {
        try {

            Object target = point.getTarget();
            // 获取传入方法的所有参数
            Object[] args = point.getArgs();
            // 获取方法名
            String methodName = point.getSignature().getName();
            // 获取所有参数的类型
            Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
            Method method = target.getClass().getMethod(methodName, parameterTypes);
            // 通过方法获取标识在方法上的注解
            GlobalInterceptor interceptor = method.getAnnotation(GlobalInterceptor.class);
            // 方法上没有标识该注解
            if (null == interceptor) {
                return;
            }

            /**
             * 校验登录
             */
            if (interceptor.checkLogin() || interceptor.checkAdmin()) {
                checkLogin(interceptor.checkAdmin());
            }

            /**
             * 校验参数
             */
            if (interceptor.checkParams()) {
                validateParams(method, args);
            }
        } catch (BusinessException e) {
            logger.error("全局拦截器异常", e);
            throw e;
        } catch (Exception e) {
            logger.error("全局拦截器异常", e);
            throw new BusinessException(ResponseCodeEnum.CODE_500);
        }
    }

    /**
     * 校验参数
     */
    private void checkLogin(Boolean checkAdmin) {
        // 获取到当前的请求
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 获取session
        HttpSession session = request.getSession();
        // 获取session中存的用户信息
        SessionWebUserDto userDto = (SessionWebUserDto) session.getAttribute(Constants.SESSION_KEY);

        if (null == userDto) {
            throw new BusinessException(ResponseCodeEnum.CODE_901);
        }

        // 如果需要校验管理员权限，但不是管理员
        if (checkAdmin && !userDto.getAdmin()) {
            throw new BusinessException(ResponseCodeEnum.CODE_404);
        }
    }

    /**
     * 校验参数
     */
    private void validateParams(Method method, Object[] arguments) {
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            // 参数
            Parameter parameter = parameters[i];
            // 传入的参数值
            Object value = arguments[i];
            VerifyParam verifyParam = parameter.getAnnotation(VerifyParam.class);
            // 如果没有在参数上标识注解
            if (null == verifyParam) {
                continue;
            }
            // 校验基本数据类型
            if (ArrayUtils.contains(BASE_TYPE, parameter.getParameterizedType().getTypeName())) {
                checkValue(value, verifyParam);
            } else {  // 校验对象
                checkObjValue(parameter, value);
            }
        }
    }

    /**
     * 校验基本类型参数
     */
    private void checkValue(Object value, VerifyParam verifyParam) {
        boolean isEmpty = (value == null) || StringTools.isEmpty(value.toString());
        int length = value == null ? 0 : value.toString().length();

        /**
         * 校验空
         */
        if (isEmpty && verifyParam.required()) {
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }

        /**
         * 校验长度
         * 长度小于设定的最小长度 或 长度大于设定的最大长度
         */
        if (!isEmpty && (verifyParam.min() != -1 && verifyParam.min() > length
                || verifyParam.max() != -1 && verifyParam.max() < length)) {
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }

        /**
         * 校验正则表达式
         * 需要校验正则表达式，但校验不通过
         */
        if (!isEmpty && !StringTools.isEmpty(verifyParam.regex().getRegex())
                && !VerifyUtils.verify(verifyParam.regex(), String.valueOf(value))) {
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }
    }

    /**
     * 校验对象类型
     * 使用反射获取需要校验的成员属性，转为校验基本类型
     */
    private void checkObjValue(Parameter parameter, Object value) {
        try {
            // 参数的完全限定类名
            String typeName = parameter.getParameterizedType().getTypeName();
            Class<?> clazz = Class.forName(typeName);
            // 获取本类所有属性（包括私有）
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                VerifyParam fieldAnnotation = field.getAnnotation(VerifyParam.class);
                // 没有标识的跳过
                if (null == fieldAnnotation) {
                    continue;
                }
                field.setAccessible(true);
                Object resultValue = field.get(value);
                checkValue(resultValue, fieldAnnotation);
            }
        } catch (Exception e) {
            logger.error("校验参数失败", e);
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }

    }
}
