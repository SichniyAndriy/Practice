public class PalindromeNumber {

    public static void main(String[] args) {
        int x = 8;
        boolean isPalindrome = isPalindrome(x);
        System.out.println("Is " + x + " a palindrome? " + isPalindrome);
    }

    private static boolean isPalindrome(final int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int original = x;
        int reversed = 0;
        while (original > 0) {
            reversed = reversed * 10 + original % 10;
            original /= 10;
        }
        return x == reversed;
    }

}
