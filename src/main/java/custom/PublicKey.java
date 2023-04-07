package custom;

import java.math.BigInteger;

class PublicKey {
    private final BigInteger d;
    private final BigInteger n;

    public PublicKey(BigInteger d, BigInteger n) {
        super();
        this.d = d;
        this.n = n;
    }

    public BigInteger getD() {
        return d;
    }

    public BigInteger getN() {
        return n;
    }
}
