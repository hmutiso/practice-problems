import static org.junit.Assert.*;

/*
 * Problem: Given an array of ints of size >= 2, return an output array
 * such that output[i] is the product of all input[j], j != i.
 * Example, if input is {1, 7, 3, 4}, then output is {84, 12, 28, 21}.
 */
public class ProductOfValuesExceptAtIndex {
    /*
     * Time complexity: O(n) : the routine iterates twice over a length 'n' array.
     * Space complexity: O(n) : Need one array to store partial products, and a
                                2nd array to store the full results.
     * Potential optimization, leftToRightProduct can be reused by overwriting
     * it during the 2nd iteration. This means we don't need to create the array
     * result, which saves us size 'n' space.
     */
    static int[] getProductsOfAllNumsExceptAtIndex(int[] nums) {
        if (nums.length < 2) {
            throw new IllegalArgumentException("Input array must have at least 2 elements");
        }
        // Create a new array leftToRightProduct such that elt i is the
        // product of all elements from elt 0 to elt i, inclusive.
        int[] leftToRightProduct = new int[nums.length];
        leftToRightProduct[0] = nums[0];
        for (int i=1; i<nums.length; i++) {
            leftToRightProduct[i] = leftToRightProduct[i-1] * nums[i];
        }
        // In a pass from nums[nums.length-1] to nums[0], compute the
        // rightToLeftProduct. This allows one to compute the result for
        // all indexes i.
        int rightToLeftProduct = 1;
        int result[] = new int[nums.length];
        for (int i=nums.length-1; i > 0; i--) {
            result[i] = rightToLeftProduct * leftToRightProduct[i-1];
            rightToLeftProduct *= nums[i];
        }
        // Don't calculate result[0] in the above /for/ loop, as accessing
        // leftToRightProduct[-1] results in an ArrayIndexOutOfBoundsException
        result[0] = rightToLeftProduct;

        return result;
    }

    public static void main(String[] args) {

        // Test 1:
        int[] input1 = new int[] {5};
        try {
            getProductsOfAllNumsExceptAtIndex(input1);
        } catch (IllegalArgumentException e) {
            System.out.println("Test threw IllegalArgumentException, as expected");
        }
        
        // Test 2:
        int[] input2 = new int[] {1, 7, 3, 4};
        int[] expectedResult2 = new int[] {84, 12, 28, 21};
        int[] actualResult2 = getProductsOfAllNumsExceptAtIndex(input2);
        for (int i=0; i < input2.length; i++) {
            assertEquals(expectedResult2[i], actualResult2[i]);
        }
        
        // Test 3:
        int[] input3 = new int[] {1, 1, 1, 1, 1, 1, 1};
        int[] expectedResult3 = new int[] {1, 1, 1, 1, 1, 1, 1};
        int[] actualResult3 = getProductsOfAllNumsExceptAtIndex(input3);
        for (int i=0; i < input3.length; i++) {
            assertEquals(expectedResult3[i], actualResult3[i]);
        }

        // Test 4:
        int[] input4 = new int[] {0, 0, 0, 0};
        int[] expectedResult4 = new int[] {0, 0, 0, 0};
        int[] actualResult4 = getProductsOfAllNumsExceptAtIndex(input4);
        for (int i=0; i < input4.length; i++) {
            assertEquals(expectedResult4[i], actualResult4[i]);
        }
        
        // Test 5:
        int[] input5 = new int[] {1, 0, 2, 3, 4};
        int[] expectedResult5 = new int[] {0, 24, 0, 0, 0};
        int[] actualResult5 = getProductsOfAllNumsExceptAtIndex(input5);
        for (int i=0; i < input5.length; i++) {
            assertEquals(expectedResult5[i], actualResult5[i]);
        }
        
        System.out.println("All unit tests passed!");
    }
}
