package com.zeng.utils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-08-21
 **/
public class RSAUtil {

    //默认大小
    private static final int DEFAULT_KEY_SIZE = 2048;

    //保存文件夹路径
    private static final String path="C:\\Users\\hhh\\Desktop\\test";

    /**
     * @使用KeyPairGenerator生成RSA加密的私钥和公钥
     * @Params:
     * @Return:
     *
    */
    public static void generateKey(String secret,int keySize) throws NoSuchAlgorithmException, IOException {
        //实例化一个秘钥生成器KeyPairGenerator
        KeyPairGenerator rsaAlgorithm = KeyPairGenerator.getInstance("RSA");
        //获取一个由随机字符加密的SecureRandom
        SecureRandom secureRandom = new SecureRandom(secret.getBytes());
        //初始化秘钥生成器KeyPairGenerator
        rsaAlgorithm.initialize(Math.max(keySize, DEFAULT_KEY_SIZE), secureRandom);
        //获取秘钥对
        KeyPair keyPair = rsaAlgorithm.generateKeyPair();
        //私钥
        PrivateKey privateKey = keyPair.getPrivate();
        File dest = new File(path+File.separator+"private.txt");
        //base64加密后保存到本地
        Files.write(dest.toPath(), Base64.getEncoder().encode(privateKey.getEncoded()));
        //公钥
        PublicKey publicKey = keyPair.getPublic();
        File file = new File(path+File.separator+"public.txt");
        //base64加密后保存到本地
        Files.write(file.toPath(), Base64.getEncoder().encode(publicKey.getEncoded()));
    }

    /**
     * @将加密后的字符私钥还原成PrivateKey
     * @Params:
     * @Return:
     *
    */
    public static PrivateKey acquirePrivate(String path) throws Exception{
        //读取路径下的私钥.txt
        byte[] bytes = Files.readAllBytes(new File(path).toPath());
        //Base64解密
        byte[] decode = Base64.getDecoder().decode(bytes);
        //加载私钥密文
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decode);
        //生成PrivateKey、并返回
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return factory.generatePrivate(spec);
    }

    /**
     * @将加密后的字符公钥还原成PublicKey
     * @Params:
     * @Return:
     *
    */
    public static PublicKey acquirePublic(String path) throws Exception {
        //读取路径下的私钥.txt
        byte[] bytes = Files.readAllBytes(new File(path).toPath());
        //解密
        byte[] decode = Base64.getDecoder().decode(bytes);
        //加载公钥密文
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decode);
        //生成PublicKey、并返回
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return factory.generatePublic(spec);
    }
}
