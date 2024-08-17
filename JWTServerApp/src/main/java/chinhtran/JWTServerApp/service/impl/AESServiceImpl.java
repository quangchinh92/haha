package chinhtran.JWTServerApp.service.impl;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import chinhtran.JWTServerApp.service.AESService;
import chinhtran.JWTServerApp.utils.AESUtil;

@Service
public class AESServiceImpl implements AESService {

    @Value("${aes.password}")
    private String password;

    @Value("${aes.salt}")
    private String salt;

    @Value("${aes.iv-text}")
    private String ivText;

    @Override
    public String encrypt(String plainText) {
        IvParameterSpec iv = AESUtil.getIvFromString(ivText);
        SecretKey secretKey = AESUtil.getKeyFromPassword(password, salt);
        return AESUtil.encrypt(plainText, secretKey, iv);
    }

    @Override
    public String decrypt(String encryptedText) {
        IvParameterSpec iv = AESUtil.getIvFromString(ivText);
        SecretKey secretKey = AESUtil.getKeyFromPassword(password, salt);
        return AESUtil.decrypt(encryptedText, secretKey, iv);
    }

}
