package masterclass.course;

public class Main {

    public static void main(String[] args) {
        System.out.println(sumDigits(1234567890L));
        System.out.println(sumDigits(1234567890123456789L));
        System.out.println(sumDigits(125L));
        System.out.println(sumDigits(325L));
        System.out.println(sumDigits(10L));
        System.out.println(sumDigits(30L));
        System.out.println(sumDigits(9L));
        System.out.println(sumDigits(0L));
        System.out.println(sumDigits(-15L));
        System.out.println(sumDigits(15L));

        System.out.println(isPalindrome(505));
        System.out.println(isPalindrome(25));
        System.out.println(isPalindrome(20));

        System.out.println(hasSharedDigit(51, 15));

        System.out.println(getEvenDigitSum(542778));

        System.out.println(hasSameLastDigit(41, 22, 71));
        System.out.println(hasSameLastDigit(23, 32, 42));
        System.out.println(hasSameLastDigit(99, 9, 999));

        System.out.println(getGreatestCommonDivisor(12, 30));
        System.out.println(getGreatestCommonDivisor(153, 81));
        System.out.println(getGreatestCommonDivisor(5, 81));
    }

    public static long sumDigits(long num) {
        long sum = 0;
        if (num < 10) {
            sum = -1;
        } else {
            while (num > 0) {
                sum += (num % 10L);
                num /= 10L;
            }
        }
        return sum;
    }
    public static boolean isPalindrome(int number) {
        int theInitNum = number;
        int reverseNum = 0;
        while (number != 0) {
            reverseNum = (reverseNum * 10) + (number % 10);
            number /= 10;
        }
        return theInitNum == reverseNum;
    }

    public static boolean hasSharedDigit(int num1, int num2) {
        if (num1 < 10 || num1 > 99 || num2 < 10 || num2 > 99) {
            return false;
        }

        while (num1 > 0) {
            int tempInt = (num1 % 10);
            int cpNum2 = num2;

            while (cpNum2 > 0) {
                int tempInt2 = (cpNum2 % 10);

                if (tempInt2 == tempInt) {
                    return true;
                }

                cpNum2 /= 10;
            }

            num1 /= 10;
        }

        return false;
    }

    public static int getEvenDigitSum(int number) {
        int sum = 0;
        if (number < 0) {
            sum = -1;
        } else {
            while (number > 0) {
                sum += (number % 2 == 0) ? (number % 10L) : 0;
                number /= 10L;
            }
        }
        return sum;
    }

    public static boolean hasSameLastDigit(int num1, int num2, int num3) {
        return isValid(num1) && isValid(num2) && isValid(num3) &&
                (hasSameLastHelper(num1, num2) ||
                hasSameLastHelper(num2, num3) ||
                hasSameLastHelper(num1, num3));
    }

    public static boolean isValid(int num) {
        return (num >= 10) && (num <= 1000);
    }

     public static boolean hasSameLastHelper(int one, int two) {
         return (one % 10) == (two % 10);
     }

    public static int getGreatestCommonDivisor(int first, int second) {
        if (first < 10 || second < 10)
            return -1;
        int divider = 1;
        int endCondition = (first > second) ? second : first;
        for (int step = 2; step <= endCondition; ++step) {
            if ((first % step) == 0 && (second % step) == 0) {
                divider = step;
            }
        }
        return divider;
    }

}
