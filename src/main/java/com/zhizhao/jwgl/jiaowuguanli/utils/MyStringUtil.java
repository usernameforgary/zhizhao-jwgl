package com.zhizhao.jwgl.jiaowuguanli.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyStringUtil {
    // 验证手机号
    public static boolean isValidShouJi(String shouJi) {
        String regExp = "^1[3|4|5|7|8][0-9]\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(shouJi);
        return m.find();
    }
}
