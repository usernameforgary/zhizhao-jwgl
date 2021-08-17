package com.zhizhao.jwgl.jiaowuguanli.service.oss;

import java.security.MessageDigest;
import java.time.OffsetDateTime;
import java.util.UUID;

public class OSSUtil {
    public static String generateFileName(String ext) throws  Exception {
        return md5String(UUID.randomUUID() + "-" + OffsetDateTime.now().toInstant().toEpochMilli()) + "." + ext;
    }

    public static String md5String(String str) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes());
        byte[] hash = md.digest();
        StringBuffer hexString = new StringBuffer();
        for(int i = 0; i < hash.length; i++) {
            if ((0xff & hash[i]) < 0x10) {
                hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
            } else {
                hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
        }
        return hexString.toString().toLowerCase();
    }
}
