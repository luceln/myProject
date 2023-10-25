package com.library.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

public class StringTools {
    /**
     * 判断是否为空
     */
    public static boolean isEmpty(String str) {
        if (null == str || "".equals(str) || "null".equals(str) || "\u0000".equals(str)) {
            return true;
        } else if ("".equals(str.trim())) {
            return true;
        }
        return false;
    }


    public static final String getRandomNumber(Integer count) {
        return RandomStringUtils.random(count, false, true);  // 返回随机数字
    }

    /**
     * 返回数字或字符
     */
    public static final String getRandomString(Integer count) {
        return RandomStringUtils.random(count, true, true);
    }

    /**
     * MD5加密
     */
    public static String encodeByMD5(String originString) {
        return isEmpty(originString) ? null : DigestUtils.md5Hex(originString);
    }
}
