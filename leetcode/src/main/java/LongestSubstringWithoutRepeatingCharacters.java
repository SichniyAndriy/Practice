import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        String s = "abcabcbb";
        int length = lengthOfLongestSubstring(s);
        System.out.println("Length of longest substring without repeating characters: " + length);
    }

    private static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> lastIndexes = new HashMap<>();
        int max = 0;
        int left = 0;
        for (int right = 0; right < s.length(); ++right) {
            Character currentCharacter = s.charAt(right);
            Integer index = lastIndexes.get(currentCharacter);
            if (index != null && index >= left) {
                left = index + 1;
            }
            lastIndexes.put(currentCharacter, right);
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}
