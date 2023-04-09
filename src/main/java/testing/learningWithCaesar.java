package testing;

import cipher.CaesarCipher;

import java.util.Arrays;

public class learningWithCaesar {
    public static void main(String[] args) {
        CaesarCipher cipher = new CaesarCipher(2);
        String cipherEncrypted = cipher.encrypt("This is my test, well");

        CaesarCipher decryptor = new CaesarCipher();
//
//        System.out.println(cipherEncrypted);
//        System.out.println(Arrays.toString(cipherEncrypted.getBytes()));
//        System.out.println(cipher.decrypt(cipherEncrypted));
//        System.out.println(cipherEncrypted);


        System.out.println(decryptor.decryptMessage(cipherEncrypted));


    }
}
