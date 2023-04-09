package testing;

import cipher.CaesarCipher;

import java.util.Arrays;

public class learningWithCaesar {
    public static void main(String[] args) {
        CaesarCipher cipher = new CaesarCipher(2);
        String cipherEncrypted = cipher.encrypt("ABcZz, well");

//
//        System.out.println(cipherEncrypted);
//        System.out.println(Arrays.toString(cipherEncrypted.getBytes()));
//        System.out.println(cipher.decrypt(cipherEncrypted));
//        System.out.println(cipherEncrypted);


        for (int i = 0; i < 30; i++) {
            System.out.println(cipher.decrypt(cipherEncrypted));
            cipher.increaseShift();
        }
        cipher.setShift(2);
        System.out.println(cipher.decrypt(cipherEncrypted));

    }
}
