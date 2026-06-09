package chinhtran.JWTServerApp.service.impl;

import chinhtran.JWTServerApp.dto.AppAESKeyDto;
import chinhtran.JWTServerApp.service.AppAESService;
import chinhtran.JWTServerApp.utils.AESUtil;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import org.springframework.stereotype.Service;

@Service
public class AppAESServiceImpl implements AppAESService {

  @Override
  public String encrypt(AppAESKeyDto key, String plainText) {
    IvParameterSpec iv = AESUtil.getIvFromString(key.getIvText());
    SecretKey secretKey = AESUtil.getKeyFromPassword(key.getPassword(), key.getSalt());
    return AESUtil.encrypt(plainText, secretKey, iv);
  }

  @Override
  public String decrypt(AppAESKeyDto key, String encryptedText) {
    IvParameterSpec iv = AESUtil.getIvFromString(key.getIvText());
    SecretKey secretKey = AESUtil.getKeyFromPassword(key.getPassword(), key.getSalt());
    return AESUtil.decrypt(encryptedText, secretKey, iv);
  }
}
