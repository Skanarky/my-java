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


}
