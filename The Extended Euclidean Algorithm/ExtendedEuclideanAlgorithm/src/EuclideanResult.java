import java.math.BigInteger;

public class EuclideanResult {
    private final BigInteger gcd;
    private final BigInteger x;
    private final BigInteger y;

    public EuclideanResult(BigInteger gcd, BigInteger x, BigInteger y) {
        this.gcd = gcd;
        this.x = x;
        this.y = y;
    }

    public BigInteger getGcd() {
        return gcd;
    }

    public BigInteger getX() {
        return x;
    }

    public BigInteger getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("GCD: %d, x: %d, y: %d", gcd, x, y);
    }
}