import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.math.BigInteger;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;

class ExtendedEuclideanAlgorithmTest {
    /**
     * Method under test: {@link ExtendedEuclideanAlgorithm#extendedEuclidean(BigInteger, BigInteger)}
     */
    @Test
    void testExtendedEuclidean() {
        BigInteger a = BigInteger.valueOf(42L);
        BigInteger b = BigInteger.valueOf(42L);
        EuclideanResult actualExtendedEuclideanResult = ExtendedEuclideanAlgorithm.extendedEuclidean(a, b);
        BigInteger gcd = actualExtendedEuclideanResult.getGcd();
        assertEquals(b, gcd);
        BigInteger expectedY = b.ZERO;
        BigInteger y = actualExtendedEuclideanResult.getY();
        assertSame(expectedY, y);
        BigInteger expectedX = b.ONE;
        BigInteger x = actualExtendedEuclideanResult.getX();
        assertSame(expectedX, x);
        assertEquals("0", y.toString());
        assertEquals("1", x.toString());
        assertEquals("42", gcd.toString());
    }
}

