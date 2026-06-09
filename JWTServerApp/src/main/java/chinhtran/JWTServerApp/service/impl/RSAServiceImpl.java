package chinhtran.JWTServerApp.service.impl;

import chinhtran.JWTServerApp.service.RSAService;
import chinhtran.JWTServerApp.utils.RSAUtil;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import org.springframework.stereotype.Service;

@Service
public class RSAServiceImpl implements RSAService {

  @Override
  public KeyPair createKeyPair() {
    try {
      return RSAUtil.generateKeyPair();
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public String encrypt(String plainText, PublicKey publicKey) {
    try {
      return RSAUtil.encrypt(plainText, publicKey);
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public String decrypt(String encryptedText, PrivateKey privateKey) {
    try {
      return RSAUtil.decrypt(encryptedText, privateKey);
    } catch (Exception e) {
      return null;
    }
  }
}
