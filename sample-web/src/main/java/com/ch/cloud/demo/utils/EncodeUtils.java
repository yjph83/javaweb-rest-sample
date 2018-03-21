package com.ch.cloud.demo.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * 描述:
 * 时间:2018-03-13 09:05
 *
 * @author:yjph83
 */
public class EncodeUtils {
    private static final String PLUS = "+";
    private static final String DASH = "-";
    private static final String FORWARD_SLASH = "/";
    private static final String UNDER_LINE = "_";

    public EncodeUtils() {
    }

    public static String getRandomString(int length) {
        String base = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < length; ++i) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }

        return sb.toString();
    }

    public static String encodeBase64URLSafeString(String data) throws UnsupportedEncodingException {
        String base64str = Base64.encodeBase64String(data.getBytes());
        if (base64str.contains("+")) {
            base64str = base64str.replaceAll("\\+", "-");
        }

        if (base64str.contains("/")) {
            base64str = base64str.replaceAll("/", "_");
        }

        return base64str;
    }

    public static String encodeBase64URLSafeString(byte[] bytes) throws UnsupportedEncodingException {
        String base64str = Base64.encodeBase64String(bytes);
        if (base64str.contains("+")) {
            base64str = base64str.replaceAll("\\+", "-");
        }

        if (base64str.contains("/")) {
            base64str = base64str.replaceAll("/", "_");
        }

        return base64str;
    }

    public static String decodeBase64URLSafeString(String data) throws UnsupportedEncodingException {
        if (data.contains("-")) {
            data = data.replaceAll("-", "+");
        }

        if (data.contains("_")) {
            data = data.replaceAll("_", "/");
        }

        return new String(Base64.decodeBase64(data));
    }

    public static String hmacSha1Base64UrlSafe(String data, String key) throws UnsupportedEncodingException {
        byte[] hmacSHA1 = hmacSHA1(data, key);
        return encodeBase64URLSafeString(hmacSHA1);
    }

    public static byte[] hmacSHA1(String data, String key) {
        byte[] byteHMAC = null;

        try {
            Mac mac = Mac.getInstance("HmacSHA1");
            SecretKeySpec spec = new SecretKeySpec(key.getBytes(), "HmacSHA1");
            mac.init(spec);
            byteHMAC = mac.doFinal(data.getBytes());
        } catch (InvalidKeyException var5) {
            var5.printStackTrace();
        } catch (NoSuchAlgorithmException var6) {
            ;
        }

        return byteHMAC;
    }

    public static String generatToken(String apiKey, String secretKey, JSONObject policy) throws UnsupportedEncodingException {
        StringBuffer token = new StringBuffer(encodeBase64URLSafeString(apiKey));
        token.append(":");
        token.append(hmacSha1Base64UrlSafe(encodeBase64URLSafeString(policy.toJSONString()), secretKey));
        token.append(":");
        token.append(encodeBase64URLSafeString(policy.toJSONString()));
        return token.toString();
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String data = "oauth_consumer_key=6b05dd4fc9054dc6x17224d27d052312&oauth_nonce=qbMYDKes&oauth_signature_method=HMAC-SHA1&oauth_timestamp=1419251247000&oauth_version=1.0";
        System.out.println(hmacSHA1("9521b0b61af374456979383a1627d147", data));
        System.out.println(URLEncoder.encode(Base64.encodeBase64String(hmacSHA1(data, "9521b0b61af374456979383a1627d147")), "utf-8"));
    }
}
