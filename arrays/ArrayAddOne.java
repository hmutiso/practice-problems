import static org.junit.Assert.*;

public class ArrayAddOne {
	/*
	 * Add 1 to a number represented as an array.
	 *
	 * Examples:
	 * 		[3, 4, 6] + 1 = [3, 4, 7]
	 * 		[1, 9, 9] + 1 = [2, 0, 0]
	 * 		[9, 9, 9] + 1 =  [1, 0, ,0, 0]
	 */
	public static int[] addOneToArrayNum(int[] intArray) {
		if (intArray == null) {
			throw new IllegalArgumentException("invalid input");
		}
		int[] result = new int[intArray.length];
		int carry = 1;
		for (int i = intArray.length-1; i >= 0; i--) {
			result[i] = intArray[i] + carry;
			if (result[i] > 9) {
				result[i] %= 10;
				carry = 1;
			} else {
				carry = 0;
			}
		}
		if (carry == 0) {
			return result;
		} else {
			// need to add an additional digit in the result-array
			int[] newResult = new int[intArray.length+1];
			newResult[0] = 1;
			return newResult;
		}
	}
	
	public static void main(String[] args) {
		
		// Test 1: [9, 9, 9] + 1 = [1, 0, 0, 0];
		
		int[] input1 = new int[] {9, 9, 9};
		int[] result1 = addOneToArrayNum(input1);
		int[] expected1 = new int[] {1, 0, 0, 0};
		assertEquals(expected1.length, result1.length);
		for (int i = 0; i < expected1.length; i++) {
			assertEquals(expected1[i], result1[i]);
		}
		
		// Test 2: [2, 3, 4] + 1 = [2, 3, 5]
		
		int[] input2 = new int[] {2, 3, 4};
		int[] result2 = addOneToArrayNum(input2);
		int[] expected2 = new int[] {2, 3, 5};
		assertEquals(expected2.length, result2.length);
		for (int i = 0; i < expected2.length; i++) {
			assertEquals(expected2[i], result2[i]);
		}
		
		// Test 3: [1, 9, 9] + 1 = [2, 0, 0]
		
		int[] input3 = new int[] {1, 9, 9};
		int[] result3 = addOneToArrayNum(input3);
		int[] expected3 = new int[] {2, 0, 0};
		assertEquals(expected3.length, result3.length);
		for (int i = 0; i < expected3.length; i++) {
			assertEquals(expected3[i], result3[i]);
		}
		
		System.out.println("All tests passed!");
	}
}
