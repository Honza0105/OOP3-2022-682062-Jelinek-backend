package custom;

import cc.redberry.rings.primes.BigPrimes;
import cipher.ICipher;
import domain.util.StringUtils;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class CustomRSA implements ICipher {
    public static int BIT_LENGTH = 2048; // Very weak

    private final String encrypted;

    private final PublicKey publicKey;





    public CustomRSA(String message) throws NoSuchAlgorithmException {
        Key key = generateKey();
        BigInteger messageBI = StringUtils.stringToBigintCustom(message);
        BigInteger encrypted = encrypt(key.getPublic(), messageBI);

        this.encrypted = StringUtils.bigintToStringCustom(encrypted);
        this.publicKey = key.getPublic();

    }

    public String getEncryptedMessage() {
        return encrypted;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        Key key = generateKey();
//        BigInteger message = BigInteger.valueOf(123456);
        //message can be only BIT_LENGTH/5 long... it could be split or sth I guess
        //let's leave it probably and focus on other stuff
        BigInteger message = StringUtils.stringToBigintCustom("Se");
        BigInteger encrypted = encrypt(key.getPublic(), message);
        BigInteger decrypted = decrypt(key.getPrivate(), encrypted);
        BigInteger reencrypted = encrypt(key.getPublic(), decrypted);
        System.out.println(encrypted);
        System.out.println(decrypted);
        System.out.println(reencrypted);
        System.out.println(StringUtils.bigintToStringCustom(decrypted));

        Key crackedKey = crack(key.getPublic());
        encrypted = encrypt(crackedKey.getPublic(), message);
        decrypted = decrypt(crackedKey.getPrivate(), encrypted);
        System.out.println(decrypted);
    }

    public static Key crack(PublicKey publicKey) throws NoSuchAlgorithmException {
        long[] factors = BigPrimes.primeFactors(publicKey.getN().longValue());
        return generateKey(BigInteger.valueOf(factors[0]), BigInteger.valueOf(factors[1]));
    }

    public static Key generateKey() throws NoSuchAlgorithmException {
        Random random = SecureRandom.getInstanceStrong();

        // Generate random primes
        BigInteger p = BigInteger.probablePrime(BIT_LENGTH / 2, random);
        BigInteger q = BigInteger.probablePrime(BIT_LENGTH / 2, random);

        return generateKey(p, q);

    }

    public static Key generateKey(BigInteger p, BigInteger q) throws NoSuchAlgorithmException {
        Random random = SecureRandom.getInstanceStrong();
        // Calculate products
        BigInteger n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        // Generate public and private exponents
        BigInteger e;
        do e = new BigInteger(phi.bitLength(), random);
        while (e.compareTo(BigInteger.ONE) <= 0
                || e.compareTo(phi) >= 0
                || !e.gcd(phi).equals(BigInteger.ONE));
        BigInteger d = e.modInverse(phi);

        return new Key(d, e, n);
    }

    public static BigInteger encrypt(PublicKey key, BigInteger message) {
        return message.modPow(key.getD(), key.getN());
    }

    public static BigInteger decrypt(PrivateKey key, BigInteger enc) {
        return enc.modPow(key.getE(), key.getN());
    }

    @Override
    public synchronized byte[] encrypt(byte[] message) throws Exception {
        return encrypt(new String(message)).getBytes();
    }

    @Override
    public synchronized byte[] decrypt(byte[] encrypted) throws Exception {
        return decrypt(new String(encrypted)).getBytes();
    }

}
