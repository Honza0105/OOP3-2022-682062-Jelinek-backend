package custom;

import java.math.BigInteger;

class PrivateKey {
    private final BigInteger e;
    private final BigInteger n;

    public PrivateKey(BigInteger e, BigInteger n) {
        super();
        this.e = e;
        this.n = n;
    }

    public BigInteger getE() {
        return e;
    }

    public BigInteger getN() {
        return n;
    }
}
