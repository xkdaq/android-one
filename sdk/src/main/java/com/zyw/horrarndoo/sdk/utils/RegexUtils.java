package com.zyw.horrarndoo.sdk.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by xuke on 2018/1/25.
 * 正则工具类，校验邮箱手机号
 */
public class RegexUtils {

    /**
     * 验证邮箱
     *
     * @param email 邮箱
     * @return boolean 是否合法
     */
    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            //String check = "^(\\w+((-\\w+)|(\\.\\w+))*)\\+\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
            //String check = "^\\\\s*\\\\w+(?:\\\\.{0,1}[\\\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\\\.[a-zA-Z]+\\\\s*$";
            //String check = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 验证手机号码
     *
     * @param mobileNumber 手机号
     * @return boolean 是否合法
     */
    public static boolean checkMobileNumber(String mobileNumber) {
        boolean flag = false;
        try {
            //Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
            Pattern regex = Pattern.compile("^((13[0-9])|(14[5,7,9])|(15[^4,\\D])|(17[0,1,3,5-8])|(18[0-9]))\\d{8}$");
            Matcher matcher = regex.matcher(mobileNumber);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public static boolean isMobile(String mobiles) {
        Pattern p = Pattern.compile("^((\\+{0,1}86){0,1})1[0-9]{10}");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 验证密码是否合法
     *
     * @param password 密码
     * @return boolean 是否合法
     */
    public static boolean passwordCheck(String password) {
        boolean is = false;
        char[] passwordChar = password.toCharArray();
        if (passwordChar.length > 5) {
            is = true;
        }
        return is;
    }


    /**
     * 18位身份证校验,粗略的校验
     *
     * @param idCard 身份证号
     * @return boolean 是否合法
     */
    public static boolean isIdCard(String idCard) {
        Pattern pattern1 = Pattern.compile("^(\\d{6})(19|20)(\\d{2})(1[0-2]|0[1-9])(0[1-9]|[1-2][0-9]|3[0-1])(\\d{3})(\\d|X|x)?$");
        Matcher matcher = pattern1.matcher(idCard);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }
}
