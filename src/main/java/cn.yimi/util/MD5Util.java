package cn.yimi.util;

import java.security.MessageDigest;

/**
 * MD5加密工具类
 * @author huangzs
 */
public class MD5Util {

    /**
     * 将字符串进行md5加密
     * @return
     */
    public static String stringTomd5(String str) {
        String md5Str = "";
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageByte = str.getBytes("UTF-8");
            byte[] md5Byte = md.digest(messageByte);
            md5Str = byteToHex(md5Byte);
        }  catch (Exception e) {
            e.printStackTrace();
        }

        return md5Str;
    }

    /**
     * 2进制转16进制
     * @return
     */
    public static String byteToHex(byte[] bytes) {
        StringBuffer hexStr = new StringBuffer();
        for (int num : bytes) {
            if (num < 0) {
                num += 256;
            }
            if (num < 16) {
                hexStr.append("0");
            }
            hexStr.append(Integer.toHexString(num));
        }

        return hexStr.toString().toUpperCase();
    }

    /**
     * 工具类测试
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(MD5Util.stringTomd5("test"));
    }
}
