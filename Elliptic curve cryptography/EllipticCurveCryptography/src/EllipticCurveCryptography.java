//*******************************************************************
// @Drew Erskine
// It defines the parameters of an elliptic curve and a random number generator for generating private keys.
// The program generates a random private key and calculates the corresponding public key point on the elliptic curve.
// It then displays the public key and private key to the user.
// The implementation includes functions for adding two points on the elliptic curve and multiplying a point by a scalar value.
//*******************************************************************
import java.math.BigInteger;
import java.security.SecureRandom;

public class EllipticCurveCryptography {

    //parameters
    private static final BigInteger p = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFFFFFFFFFFFF", 16);
    private static final BigInteger a = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFFFFFFFFFFFC", 16);
    private static final BigInteger n = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBAEDCE6AF48A03BBFD25E8CD0364141", 16);
    private static final BigInteger gx = new BigInteger("C82B23D17F2D4D754EBEEDEB82B3C9B88FED8B033573FCF3", 16);
    private static final BigInteger gy = new BigInteger("AD5AF67BB5B5D8C5F5F21EF45FEBD553B5E5B8B8C79F2F0D", 16);

    // Define the random number generator for keys
    private static final SecureRandom random = new SecureRandom();

    public static void main(String[] args) {

        // Generate a random private key in the range [1, n-1]
        BigInteger privateKey = new BigInteger(n.bitLength(), random);
        while (privateKey.signum() == 0 || privateKey.compareTo(n) >= 0) {
            privateKey = new BigInteger(n.bitLength(), random);
        }

        // Calculate the corresponding public key point on the elliptic curve
        BigInteger[] publicKey = pointMultiply(privateKey);

        // Display the public key and private key to the user
        System.out.println("Private key: " + privateKey.toString(16));
        System.out.println("Public key: (" + publicKey[0].toString(16) + ", " + publicKey[1].toString(16) + ")");
    }

    // Function to add two points on the elliptic curve
    private static BigInteger[] pointAdd(BigInteger x1, BigInteger y1, BigInteger x2, BigInteger y2) {
        BigInteger m;
        if (x1.equals(x2) && y1.equals(y2)) {
            m = x1.multiply(x1).multiply(BigInteger.valueOf(3)).add(a).multiply(y1.multiply(BigInteger.valueOf(2)).modInverse(p));
        } else {
            m = y2.subtract(y1).multiply(x2.subtract(x1).modInverse(p));
        }
        BigInteger x3 = m.multiply(m).subtract(x1).subtract(x2).mod(p);
        BigInteger y3 = y1.add(m.multiply(x3.subtract(x1))).mod(p);
        return new BigInteger[] {x3, y3};
    }

    // Function to multiply a point on the elliptic curve by a scalar value
    private static BigInteger[] pointMultiply(BigInteger scalar) {
        BigInteger[] result = new BigInteger[] {EllipticCurveCryptography.gx, EllipticCurveCryptography.gy};
        BigInteger[] temp = new BigInteger[] {EllipticCurveCryptography.gx, EllipticCurveCryptography.gy};
        scalar = scalar.mod(n);
        while (scalar.compareTo(BigInteger.ZERO) > 0) {
            if (scalar.testBit(0)) {
                result = pointAdd(result[0], result[1], temp[0], temp[1]);
            }
            temp = pointAdd(temp[0], temp[1], temp[0], temp[1]);
            scalar = scalar.shiftRight(1);
        }
        return result;
    }
}

