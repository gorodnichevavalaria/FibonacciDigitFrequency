import java.util.HashMap;
import java.util.Map;

public class FibonacciDigitFrequency {
    
    public static void main(String[] args) {
        int i = 100000;
        String fibonacciNumber = fibonacci(i);
        System.out.println("F(" + i + ") = " + fibonacciNumber);
        Map.Entry<Integer, Integer> result = mostFrequentDigit(fibonacciNumber);
        System.out.println("Наиболее частая цифра: " + result.getKey() + " встречается " + result.getValue() + " раз(а).");
    }

    public static String fibonacci(int n) {
        if (n <= 1) return String.valueOf(n);

        long[][] F = new long[][]{{1, 1}, {1, 0}};
        power(F, n - 1);
        return String.valueOf(F[0][0]);
    }

    private static void power(long[][] F, int n) {
        if (n == 0 || n == 1)
            return;

        long[][] M = new long[][]{{1, 1}, {1, 0}};
        
        power(F, n / 2);
        multiply(F, F);
        
        if (n % 2 != 0)
            multiply(F, M);
    }

    private static void multiply(long[][] F, long[][] M) {
        long x = F[0][0] * M[0][0] + F[0][1] * M[1][0];
        long y = F[0][0] * M[0][1] + F[0][1] * M[1][1];
        long z = F[1][0] * M[0][0] + F[1][1] * M[1][0];
        long w = F[1][0] * M[0][1] + F[1][1] * M[1][1];

        F[0][0] = x;
        F[0][1] = y;
        F[1][0] = z;
        F[1][1] = w;
    }

    public static Map.Entry<Integer, Integer> mostFrequentDigit(String number) {
        int[] count = new int[10];
        
        for (char c : number.toCharArray()) {
            count[c - '0']++;
        }
        
        int maxFrequency = -1;
        int digitWithMaxFrequency = -1;
        
        for (int i = 0; i < 10; i++) {
            if (count[i] > maxFrequency) {
                maxFrequency = count[i];
                digitWithMaxFrequency = i;
            } else if (count[i] == maxFrequency && i > digitWithMaxFrequency) {
                digitWithMaxFrequency = i;
            }
        }
        
        return new HashMap.SimpleEntry<>(digitWithMaxFrequency, maxFrequency);
    }
}