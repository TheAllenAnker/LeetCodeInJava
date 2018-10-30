package com.allenanker.other;

public class BinarySearchRelated {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 4, 5, 6, 8, 8, 8, 11, 18};
        System.out.println(findFirst2(nums, 8));
    }

    /**
     * Find the first target in the array(From left to right).
     * The numbers with the same value must be clustered together, so...
     *
     * @param nums   the int array, which must be sorted in ascending order
     * @param target the target to be found
     * @return the target's index
     */
    public static int findFirst1(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                break;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        // just go backwards to find the first one
        int i = mid;
        while (i > 0 && nums[i - 1] == target) {
            i--;
        }
        return i;
    }

    public static int findFirst2(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                if (mid == 0 || nums[mid - 1] != target) return mid;
                else right = mid - 1;
            }
        }

        return -1;
    }
}
