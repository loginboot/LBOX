package cn.ryan.utils;

import java.io.UnsupportedEncodingException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 使用DES加密与解密,可对byte[],String类型进行加密与解密 密文可使用String,byte[]存储. 方法: void
 * getKey(String strKey)从strKey的字条生成一个Key String getEncString(String
 * strMing)对strMing进行加密,返回String密文 String getDesString(String
 * strMi)对strMin进行解密,返回String明文 byte[] getEncCode(byte[] byteS)byte[]型的加密 byte[]
 * getDesCode(byte[] byteD)byte[]型的解密
 */
public class Encrypt {

    private static Key key;
    private static String strKey = "**LyodsDESForFircoTrust==";
    private static String aesStrKey = "**LyodssoftAES=="; // 只能16位整數

    static {
        try {
            /*KeyGenerator _generator = KeyGenerator.getInstance("DES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");//防止linux,windows下 随机生成key
            secureRandom.setSeed(strKey.getBytes());
            _generator.init(secureRandom);*/
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            DESKeySpec keySpec = new DESKeySpec(strKey.getBytes());
            key = keyFactory.generateSecret(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 加密String明文输入,String密文输出
    public static String encodeString(String strMing) throws UnsupportedEncodingException {
        byte[] tmpbytes = strMing.getBytes("UTF8");
        byte[] byteMi = getEncCode(tmpbytes);
        String strMi = Base64.encodeBase64String(byteMi);
        return strMi;
    }

    // 加密以byte[]明文输入,byte[]密文输出
    private static byte[] getEncCode(byte[] byteS) {
        byte[] byteFina = null;
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byteFina = cipher.doFinal(byteS);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cipher = null;
        }

        return byteFina;
    }

    // 解密:以String密文输入,String明文输出
    public static String decodeString(String strMi) throws UnsupportedEncodingException {
        byte[] byteMi = Base64.decodeBase64(strMi);
        byte[] byteMing = getDesCode(byteMi);
        String strM = new String(byteMing, "UTF8");
        return strM;
    }

    // 解密以byte[]密文输入,以byte[]明文输出
    private static byte[] getDesCode(byte[] byteD) {
        Cipher cipher;
        byte[] byteFina = null;
        try {
            cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byteFina = cipher.doFinal(byteD);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cipher = null;
        }
        return byteFina;
    }

    /**
     * AES加密
     * @param encryptKey
     * @param word
     * @return
     * @throws Exception
     */
    public static String encryptAES(String encryptKey, String word) throws Exception {
        // 加密
        byte[] byteWords = word.getBytes("UTF8");
        byte[] byteFinal = aes(Cipher.ENCRYPT_MODE, encryptKey, byteWords);
        String encryptWord = Base64.encodeBase64String(byteFinal);
        return encryptWord;
    }

    /**
     * AES解密
     * @param decryptKey
     * @param encryptWord
     * @return
     * @throws Exception
     */
    public static String decryptAES(String decryptKey, String encryptWord) throws Exception {
        // 解密
        byte[] byteWords = Base64.decodeBase64(encryptWord);
        byte[] byteFinal = aes(Cipher.DECRYPT_MODE, decryptKey, byteWords);
        // 生成明文
        String word = new String(byteFinal, "UTF8");
        return word;
    }

    /**
     * AES單加密
     * @param word
     * @return
     * @throws Exception
     */
    public static String encryptAES(String word) throws Exception {
        // 加密
        byte[] byteWords = word.getBytes("UTF8");
        byte[] byteFinal = aes(Cipher.ENCRYPT_MODE, aesStrKey, byteWords);
        String encryptWord = Base64.encodeBase64String(byteFinal);
        return encryptWord;
    }

    /**
     * AES單解密
     * @param decryptKey
     * @param encryptWord
     * @return
     * @throws Exception
     */
    public static String decryptAES(String encryptWord) throws Exception {
        // 解密
        byte[] byteWords = Base64.decodeBase64(encryptWord);
        byte[] byteFinal = aes(Cipher.DECRYPT_MODE, aesStrKey, byteWords);
        // 生成明文
        String word = new String(byteFinal, "UTF8");
        return word;
    }

    /**
     * AES加解密码操作
     * @param cipherType
     * @param wkey
     * @param datas
     * @return
     * @throws Exception
     */
    private static byte[] aes(int cipherType, String wkey, byte[] datas) throws Exception {
        // 加解密工厂
        String algorithm = "AES";
        SecretKeySpec keySpec = new SecretKeySpec(wkey.getBytes(), algorithm);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(cipherType, keySpec);
        // 执行加解密
        byte[] byteFinal = cipher.doFinal(datas);
        return byteFinal;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        if (args.length == 2) {
            if ("-d".equalsIgnoreCase(args[0])) {
                System.out.println("Ciphertext:" + args[1]);
                String ump = Encrypt.decodeString(args[1]);
                System.out.println("Plaintext:" + ump);
            } else if ("-e".equalsIgnoreCase(args[0])) {
                String strm = "MAXONLINEUSER:" + args[1];
                System.out.println("Plaintext:" + strm);
                String tmp = Encrypt.encodeString(strm);
                System.out.println("Ciphertext:" + tmp);
            } else {
                System.out.println("Please Enter Encrypt Parameters: -d [entrypt string] or -e [concurrent number]");
            }
        } else {
            System.out.println("Please Enter Encrypt Parameters: -d [entrypt string] or -e [concurrent number]");
        }
    }

}
