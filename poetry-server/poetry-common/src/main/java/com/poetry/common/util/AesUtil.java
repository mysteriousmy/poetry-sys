package com.poetry.common.util;

import com.poetry.common.consts.others.AesSalt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class AesUtil {
    // 加密
    public static String Encrypt(String sSrc){
        try {
            String sKey = AesSalt.AesSalt;
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
            Encoder encoder = Base64.getEncoder();
            return encoder.encodeToString(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
        }catch (Exception e){
            throw new RuntimeException("解密错误！");
        }

    }

    // 解密
    public static String Decrypt(String sSrc){
        try {
            String sKey = AesSalt.AesSalt;
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            Decoder decoder = Base64.getDecoder();
            byte[] encrypted1 = decoder.decode(sSrc);
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,"utf-8");
                return originalString;
            } catch (Exception e) {
                throw new RuntimeException("解密错误！");
            }
        } catch (Exception ex) {
            throw new RuntimeException("解密错误！");
        }
    }
}
