import java.util.Arrays;
import static org.junit.Assert.*;

public class SumOfThreeProblem {
	
    /*
     * Given an array of integers, find three integers that sum to zero.
     * @parameter array of integers
     * @return
     * 	    a sub-array of 3 integers that returns 0, if such a sub-array exists.
     * 	    Otherwise returns [-1, -1, -1].
     *
     * Time complexity: O(n^2)
     * Space complexity: O(1)
     */
    public static int[] threeValsThatSumToZero(int[] intArray) {
        int arrayLength = intArray.length;
        if (arrayLength < 3) {
            return new int[] {-1, -1, -1};
        }
        // First, sort the array from smallest to largest. This mutates the
        // input array. Clone the array if mutating the input is not desirable.
        Arrays.sort(intArray);
        
        int i = 0, j = 0, k = 0, sum = 0;
        
        // i goes till (array.length-2) as indices j and k are always larger than i
        for (i = 0; i < (arrayLength-2); i++) {
            // check if a valid 3-tuple exists that includes array[i], array[j] and
            // array[k] such that j > i and k > i:
            j = i+1;
            k = arrayLength - 1; // k starts at the end of the array
            while (k > j) {
                sum = intArray[i] + intArray[j] + intArray[k];
                if (sum == 0) {
                    // Found a solution!
                    return new int[] {intArray[i], intArray[j], intArray[k]};
                } else if (sum > 0) {
                    // sum is too big. How to reduce it? Decrement k
                    k--;
                } else {
                    // sum is too small. How to increase it? Increment j
                    j++;
                }
            }
            // No solution found that includes array[i]. Check next i.
        }
        return new int[] {-1, -1, -1};
    }
    
    public static void main(String[] args) {
        // Test 1
        int[] a = new int[] {-2, 3, -1};
        int[] result = threeValsThatSumToZero(a);
        assertTrue(result.length == 3);
        assertTrue(result[0] + result[1] + result[2] == 0);
        System.out.println("Test 1 passed");
        
        
        // Test 2
        int[] b = new int[] {0, 0};
        int[] result2 = threeValsThatSumToZero(b);
        assertTrue(result2.length == 3);
        assertTrue(result2[0] == -1 && result2[1] == -1 && result2[2] == -1);
        System.out.println("Test 2 passed");
        
        
        // Test 3
        int[] c = new int[] {0, 0, 0};
        int[] result3 = threeValsThatSumToZero(c);
        assertTrue(result3.length == 3);
        assertTrue(result3[0] == 0 && result3[1] == 0 && result3[2] == 0);
        System.out.println("Test 3 passed");
        
        
        // Test 4
        int[] d = new int[] {5, -3, 6, 8, 2, 99, 1};
        int[] result4 = threeValsThatSumToZero(d);
        // Note: array is sorted, so the elements will appear from smallest to largest
        assertTrue(result4.length == 3);
        assertTrue(result4[0] == -3 && result4[1] == 1 && result4[2] == 2);
        System.out.println("Test 4 passed");
        
        // Test 5
        int[] e = new int[] {};
        int[] result5 = threeValsThatSumToZero(e);
        assertTrue(result5.length == 3);
        assertTrue(result5[0] == -1 && result5[1] == -1 && result5[2] == -1);
        System.out.println("Test 5 passed");
        
        // end of tests
        System.out.println("All tests passed!");
    }
}
