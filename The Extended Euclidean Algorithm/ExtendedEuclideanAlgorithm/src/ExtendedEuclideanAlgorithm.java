//*******************************************************************
//  @Drew Erskine
//
//  In this code, the extendedEuclidean function takes in two integers a and b, and returns an array of three integers representing the GCD and the coefficients x and y such that ax + by = GCD(a, b).
//  The function is implemented recursively, and uses the fact that the GCD of two numbers a and b is the same as the GCD of b and a % b.
//*******************************************************************

public class ExtendedEuclideanAlgorithm {
    // Recursive function to calculate GCD and coefficients
    // ax + by = gcd(a, b)
    public static int[] extendedEuclidean(int a, int b) {
        if (a == 0) {
            return new int[] {b, 0, 1};
        }
        int[] values = extendedEuclidean(b % a, a);
        int gcd = values[0];
        int x = values[2] - (b / a) * values[1];
        int y = values[1];
        return new int[] {gcd, x, y};
    }

    public static void main(String[] args) {
        int a = 15;
        int b = 35;
        int[] values = extendedEuclidean(a, b);
        int gcd = values[0];
        int x = values[1];
        int y = values[2];
        System.out.printf("gcd(%d, %d) = %d\n", a, b, gcd);
        System.out.printf("%d * %d + %d * %d = %d\n", a, x, b, y, gcd);
    }
}
