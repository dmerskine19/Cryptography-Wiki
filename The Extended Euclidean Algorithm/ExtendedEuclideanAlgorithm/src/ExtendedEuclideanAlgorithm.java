//*******************************************************************
//  @Drew Erskine
//
//  In this code, the extendedEuclidean function takes in two integers a and b, and returns an array of three integers representing the GCD and the coefficients x and y such that ax + by = GCD(a, b).
//  The function is implemented recursively, and uses the fact that the GCD of two numbers a and b is the same as the GCD of b and a % b.
//*******************************************************************

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class ExtendedEuclideanAlgorithm {
    private static final SecureRandom random = new SecureRandom();

    // Recursive function to calculate GCD and coefficients
// ax + by = gcd(a, b)
    public static EuclideanResult extendedEuclidean(BigInteger a, BigInteger b) {
        if (a.compareTo(BigInteger.ZERO) == 0) {
            return new EuclideanResult(b, BigInteger.ZERO, BigInteger.ONE);
        }
        EuclideanResult values = extendedEuclidean(b.mod(a), a);
        BigInteger gcd = values.getGcd();
        BigInteger x = values.getY().subtract(b.divide(a).multiply(values.getX()));
        BigInteger y = values.getX();
        return new EuclideanResult(gcd, x, y);
    }

    public static BigInteger[] generateCoprimeNumbers(int bitLength) {
        BigInteger p = BigInteger.probablePrime(bitLength / 2, random);
        BigInteger q = BigInteger.probablePrime(bitLength / 2, random);
        BigInteger n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger raBigInteger;
        do {
            raBigInteger = new BigInteger(phi.bitLength(), random);
        } while (raBigInteger.compareTo(BigInteger.ONE) <= 0 || raBigInteger.compareTo(phi) >= 0 || !raBigInteger.gcd(phi).equals(BigInteger.ONE));
        BigInteger d = raBigInteger.modInverse(phi);
        BigInteger[] coprimeNumbers = {raBigInteger, d};
        return coprimeNumbers;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter two integers:");
        BigInteger a = scanner.nextBigInteger();
        BigInteger b = scanner.nextBigInteger();
        EuclideanResult result = extendedEuclidean(a, b);
        BigInteger distance = result.getX().pow(2).add(result.getY().pow(2)).sqrt();
        System.out.printf("gcd(%d, %d) = %d\n", a, b, result.getGcd());
        System.out.printf("%d * %d + %d * %d = %d\n", a, result.getX(), b, result.getY(), result.getGcd());
        System.out.printf("Euclidean distance between coefficients: %d\n", distance);
        System.out.printf("---------------------------------------------------------");
        // testing generateCoprimeNumbers
        System.out.printf("Generating coprime with random numbers:");
        BigInteger[] coprimeNumbers = generateCoprimeNumbers(100);
        BigInteger e = coprimeNumbers[0];
        BigInteger d = coprimeNumbers[1];
        System.out.printf("e = %d, d = %d\n", e, d);
    }
}