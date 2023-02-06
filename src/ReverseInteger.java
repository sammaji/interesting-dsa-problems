/**
 * @problem Given a signed 32-bit integer x, return x with its digits reversed.
 *          If reversing x causes the value to go outside the signed 32-bit
 *          integer range [-2^31, 2^31 - 1], then return 0. Assume the
 *          environment
 *          does not allow you to store 64-bit integers (signed or unsigned).
 * 
 * @source LEETCODE NO. 7
 * 
 * @challenge INTEGER OVERFLOW
 *            Java stores int in 32-bit format (-2^31 to 2^31-1). Sometimes, a
 *            number might be within the range of possible values but its
 *            reverse might
 *            exceed 32-bits. In that case, java truncates the front bit (or
 *            most significant bit) resulting in a totally different number.
 * 
 * @solution One possible solution is to take a long value and check if it is
 *           within the integer limit. The second solution is that we can first
 *           reverse the number (and store it), then reverse the number again
 *           and check if both the numbers are equal or not. However, that might
 *           introduce a small bug (incase of numbers ending with zero)
 */
public class ReverseInteger {
    public static void main(String[] args) {
        System.out.println(reverse(134));

        /* EDGE CASES */
        // number ending with zero
        System.out.println(reverse(-120));

        // reverse of num > 2^31
        int num = 1534236469;
        System.out.println(reverse(num));
    }

    public static int reverse(int x) {
        int x_reverse = plain_reverse(x);
        int x_length = length(x) - length(x_reverse);

        // optional -ve check for x_length
        if (x_length < 0)
            x_length = 0;

        /**
         * checking if the reverse of the reversed number equals the original number.
         * considering reverse of 120 is 210 and not 21
         */
        if (x != ((int) (plain_reverse(x_reverse) * Math.pow(10, x_length))))
            return 0; // overflow

        return x_reverse;
    }

    /**
     * It returns the length of the integer passed to it
     * 
     * @param x The number to be reversed
     * @return The length of the number.
     */
    static int length(int x) {
        if (x == 0)
            return 1;

        int length = 0;
        x = Math.abs(x);
        while (x > 0) {
            length++;
            x = x / 10;
        }
        return length;
    }

    /** @return reverse of @param x including negative numbers too */
    static int plain_reverse(int x) {
        int last_digit;
        int x_reverse = 0;
        boolean isNegative = (x < 0);
        x = Math.abs(x);

        while (x > 0) {
            last_digit = x - (x / 10) * 10;
            x_reverse = x_reverse * 10 + last_digit;
            x = x / 10;
        }
        return isNegative ? -x_reverse : x_reverse;
    }
}