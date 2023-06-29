package cipher;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

public class SymmetricKeyEncryptionBC {
    private SecretKey secretKey;

    public SymmetricKeyEncryptionBC() throws NoSuchAlgorithmException {
        Security.addProvider(new BouncyCastleProvider());
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        secretKey = keyGenerator.generateKey();
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }

    public byte[] encrypt(String data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(data.getBytes());
    }

    public byte[] encrypt(String data, SecretKey customKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, customKey);
        return cipher.doFinal(data.getBytes());
    }


    public byte[] decrypt(byte[] encryptedData) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(encryptedData);
    }

    public byte[] decrypt(byte[] encryptedData, SecretKey customKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, customKey);
        return cipher.doFinal(encryptedData);
    }
}
