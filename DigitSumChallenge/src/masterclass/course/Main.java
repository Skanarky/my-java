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
        System.out.println(isPalindrome(100));

        System.out.println(hasSharedDigit(51, 15));

        System.out.println(getEvenDigitSum(542778));

        System.out.println(hasSameLastDigit(41, 22, 71));
        System.out.println(hasSameLastDigit(23, 32, 42));
        System.out.println(hasSameLastDigit(99, 9, 999));

        System.out.println(getGreatestCommonDivisor(12, 30));
        System.out.println(getGreatestCommonDivisor(153, 81));
        System.out.println(getGreatestCommonDivisor(5, 81));

        System.out.println(isPerfectNumber(6));
        System.out.println(isPerfectNumber(8));
        System.out.println(isPerfectNumber(10));

        numberToWords(10);
        numberToWords(1234554321);
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
        for (int step = (first > second) ? second : first; step >= 1; --step) {
            if ((first % step) == 0 && (second % step) == 0) {
                divider = step;
                break;
            }
        }

        return divider;
    }

    public static boolean isPerfectNumber(int number) {
        if (number < 1) {
            return false;
        } else {
            int cpNum = 1;
            int sum = 0;
            while (cpNum < number) {
                if ((number % cpNum) == 0)
                    sum += cpNum;
                cpNum++;
            }
            return sum == number;
        }
    }

    public static void numberToWords(int number) {
        if (number < 0) {
            System.out.println("Invalid Value");
        } else if (number == 0) {
            System.out.println("Zero");
        } else {
            StringBuffer str = new StringBuffer();
            int newRevNum = reverse(number);
            int compRevAndInit = (getDigitCount(number) - getDigitCount(newRevNum));
            while (newRevNum != 0) {
                switch ((newRevNum % 10)) {
                    case 0:
                        str.append("Zero ");
                        break;
                    case 1:
                        str.append("One ");
                        break;
                    case 2:
                        str.append("Two ");
                        break;
                    case 3:
                        str.append("Three ");
                        break;
                    case 4:
                        str.append("Four ");
                        break;
                    case 5:
                        str.append("Five ");
                        break;
                    case 6:
                        str.append("Six ");
                        break;
                    case 7:
                        str.append("Seven ");
                        break;
                    case 8:
                        str.append("Eight ");
                        break;
                    case 9:
                        str.append("Nine ");
                        break;
                    default:
                        System.out.println("Invalid Value");
                        break;
                }
                newRevNum /= 10;
            }
            while (compRevAndInit > 0) {
                str.append("Zero");
                --compRevAndInit;
            }
            System.out.println(str.toString().trim());
        }
    }
    public static int reverse(int number) {
        int reverseNum = 0;
        while (number != 0) {
            reverseNum = (reverseNum * 10) + (number % 10);
            number /= 10;
        }
        return reverseNum;
    }
    public static int getDigitCount(int number) {
        if (number < 0)
            return -1;
        int count = 0;
        while (number != 0) {
            count++;
            number /= 10;
        }
        return (count == 0) ? 1 : count;
    }

}
