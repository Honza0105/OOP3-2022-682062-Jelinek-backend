package custom;

import java.math.BigInteger;

public class Key {
    private final PublicKey publicKey;
    private final PrivateKey privateKey;

    public Key(BigInteger d, BigInteger e, BigInteger n) {
        super();
        this.publicKey = new PublicKey(d, n);
        this.privateKey = new PrivateKey(e, n);
    }

    public PublicKey getPublic() {
        return publicKey;
    }

    public PrivateKey getPrivate() {
        return privateKey;
    }

}

