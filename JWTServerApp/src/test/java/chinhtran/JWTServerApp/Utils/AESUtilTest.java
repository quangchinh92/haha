package chinhtran.JWTServerApp.Utils;

import static org.junit.jupiter.api.Assertions.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.junit.jupiter.api.Test;

import chinhtran.JWTServerApp.utils.AESUtil;

public class AESUtilTest {

    @Test
    public void encryptTest() {
        IvParameterSpec iv = AESUtil.getIvFromString("ivText_test_1234");
        SecretKey secretKey = AESUtil.getKeyFromPassword("password_test", "salt_test");
        assertEquals("jNvrg8sDaQ1LZKrbcecH2bBXR92VML7AKfIWXhS6jFE=",
                AESUtil.encrypt("plaintext_example", secretKey, iv));
    }

    @Test
    public void decryptTest() {
        IvParameterSpec iv = AESUtil.getIvFromString("ivText_test_1234");
        SecretKey secretKey = AESUtil.getKeyFromPassword("password_test", "salt_test");
        assertEquals(
                "plaintext_example",
                AESUtil.decrypt("jNvrg8sDaQ1LZKrbcecH2bBXR92VML7AKfIWXhS6jFE=", secretKey, iv));
    }
}
