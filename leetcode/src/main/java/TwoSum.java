import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public static void main(String[] args) {
        int[] nums = { 2, 7, 11, 15 };
        int target = 9;
        int[] result = twoSum2(nums, target);
        System.out.println("Indices: " + result[0] + ", " + result[1]);
    }

    private static  int[] twoSum1(int[] nums, int target) {
        int[] sortedNums = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sortedNums);
        int i = 0;
        while (i < sortedNums.length) {
            int diff = target - sortedNums[i];
            int j = Arrays.binarySearch(sortedNums, diff);
            if (j >= 0 && j != i) {
                for (int k = 0; k < nums.length; ++k) {
                    if (sortedNums[i] == nums[k]) {
                        i = k;
                        break;
                    }
                }
                for (int l = 0; l < nums.length; ++l) {
                    if (sortedNums[j] == nums[l] && l != i) {
                        j = l;
                        break;
                    }
                }
                int[] result = new int[2];
                result[0] = i;
                result[1] = j;
                return result;
            }
            ++i;
        }

        return new int[] {};
    }

    private static  int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map =  new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            map.put(nums[i], i);
        }

        int i = 0;
        while (i < nums.length) {
            int diff = target - nums[i];
            if (map.containsKey(diff) && map.get(diff) != i) {
                int[] result = new int[2];
                result[0] = i;
                result[1] = map.get(diff);
                return result;
            }
            ++i;
        }

        return new int[] {};
    }
}
