package com.allenanker.other;

import java.util.Arrays;

public class BinarySearchRelated {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 4, 5, 6, 8, 8, 8, 11, 18};
        System.out.println(findFirst2(nums, 8));
        System.out.println(findLast1(nums, 8));
        System.out.println(findFristGE(nums, 7));
        System.out.println(findLastSE(nums, 12));
        int[] circularNums = new int[] {4, 5, 6, 1, 2, 3};
        System.out.println(findTargetInCircularSortedList1(circularNums, 3));
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

    public static int findLast1(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                if (mid == nums.length - 1 || nums[mid + 1] != target) return mid;
                else left = mid + 1;
            }
        }

        return -1;
    }

    /**
     * Find the first element in the sorted array which is greater or equal to the target.
     *
     * @param nums   the sorted array
     * @param target the target element
     * @return the target's index
     */
    public static int findFristGE(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                if (mid == 0 || nums[mid - 1] < target) return mid;
                else right = mid - 1;
            }
        }

        return -1;
    }

    /**
     * Find the first element in the sorted array which is smaller or equal to the target.
     *
     * @param nums   the sorted array
     * @param target the target element
     * @return the target's index
     */
    public static int findLastSE(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                if (mid == nums.length - 1 || nums[mid + 1] > target) return mid;
                else left = mid + 1;
            }
        }

        return -1;
    }

    /**
     * Find the target's index in a not usual circular "sorted" array which
     * goes like this: [4, 5, 6, 1, 2, 3].
     * @param nums   the circular "sorted" array
     * @param target the target
     * @return the returned index
     */
    public static int findTargetInCircularSortedList1(int[] nums, int target) {
        int start = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] < nums[i]) {
                start =  i + 1;
                break;
            }
        }

        int left = findFirst2(Arrays.copyOfRange(nums, 0, start), target);
        int right = findFirst2(Arrays.copyOfRange(nums, start, nums.length), target);
        right = right != -1 ? right + start : right;

        return right == -1 ? left : right;
    }
}
