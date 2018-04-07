import static org.junit.Assert.*;

public class MaxProductOfThreeArrayElts {
    /*
     * Problem: Given an array of numbers, return the max product of any
     * three numbers in the array.
     * 
     * We solve this problem using a greedy algorithm that keeps track of
     * (and updates) the max product and the min product of any two numbers
     * in the array. This allows calculation of the max product of three
     * numbers in one pass through the array.
     *  
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int maxProductOf3InArray(int[] nums) {
        if (nums.length < 3) {
            throw new IllegalArgumentException("Input array needs at least 3 elts");
        }
        // Initialize values to use
        int[] smallestPP = updateSmallestProductOf2(new int[] {nums[0], nums[1]}, nums[2]);
        int[] largestPP = updateLargestProductOf2(new int[] {nums[0], nums[1]}, nums[2]);
        int maxProductOf3 = nums[0] * nums[1] * nums[2];
        
        for (int i = 3; i < nums.length; i++) {
            if (smallestPP[0] * smallestPP[1] * nums[i] > maxProductOf3) {
                maxProductOf3 = smallestPP[0] * smallestPP[1] * nums[i];
            }
            if (largestPP[0] * largestPP[1] * nums[i] > maxProductOf3) {
                maxProductOf3 = largestPP[0] * largestPP[1] * nums[i];
            }
            
            smallestPP = updateSmallestProductOf2(smallestPP, nums[i]);
            largestPP = updateLargestProductOf2(largestPP, nums[i]);
        }
        return maxProductOf3;
    }

    /*
     * Takes as input an array of 2 ints and an int, and returns an
     * array of the 2 values that result in the smallest pairwise product.
     */
    static int[] updateSmallestProductOf2(int[] numArray, int num) {
        int minProductOf2 = Math.min(numArray[0] * numArray[1],
                                Math.min(num * numArray[1],  numArray[0] * num));
        if (num * numArray[1] == minProductOf2) {
            numArray[0] = num;
        } else if (numArray[0] * num == minProductOf2) {
            numArray[1] = num;
        }
        return numArray;
    }

    /*
     * Takes as input an array of 2 ints and an int, and returns an array
     * of the 2 values that result in the largest pairwise product.
     */
    static int[] updateLargestProductOf2(int[] numArray, int num) {
        int maxProductOf2 = Math.max(numArray[0] * numArray[1],
                                Math.max(num * numArray[1],  numArray[0] * num));
        if (num * numArray[1] == maxProductOf2) {
            numArray[0] = num;
        } else if (numArray[0] * num == maxProductOf2) {
            numArray[1] = num;
        }
        return numArray;
    }

    public static void main(String[] args) {
        // Test 1:
        int[] nums1 = new int[] {3, 4, 5, 6};
        assertEquals(120, maxProductOf3InArray(nums1));

        // Test 2:
        int[] nums2 = new int[] {4, 7};
        try {
            assertEquals(10, maxProductOf3InArray(nums2));
        } catch (IllegalArgumentException e) {
            System.out.println("Throws IllegalArgumentException as expected");
        }

        // Test 3:
        int[] nums3 = new int[] {-2, -4, -5, -3};
        assertEquals(-24, maxProductOf3InArray(nums3));

        // Test 4:
        int[] nums4 = new int[] {-1, -1, -1, 0};
        assertEquals(0, maxProductOf3InArray(nums4));

        // Test 5:
        int[] nums5 = new int[] {1, 10, -5, 1, -100};
        assertEquals(5000, maxProductOf3InArray(nums5));

        // Test 6:
        int[] nums6 = new int[] {1, 10, -5};
        assertEquals(-50, maxProductOf3InArray(nums6));

        System.out.println("All tests passed!");
    }
}
