import static org.junit.Assert.*;

public class ReverseWords {
	
	/*
         * Problem: Given a string, reverse the words in the string. Assume
         * the string contains only letters and spaces, and words are
         * separated by a space.
         *
         * Example: Given an input of "pudding chocolate love I", your function
         * should return "I love chocolate pudding".
         *
	 * Time complexity: O(n)
	 * Space complexity: O(n) - needed to allocate the 'chars' array, as 
	 * Java Strings are immutable.
	 */
	public static String reverseWordsInString(String s) {
		if (s == null) {
			return null;
		}
		char[] chars = s.toCharArray();
		// First reverse the entire char array
		reverseWord(0, s.length()-1, chars);
		// Next, reverse individual words
		int wordStartIndex = 0;
		for (int i=0; i <= chars.length; i++) {
			if (i == chars.length || chars[i] == ' ') {
				// A word ends here
				reverseWord(wordStartIndex, i-1, chars);
				wordStartIndex = i+1;
			}
		}
		return new String(chars);
	}

	/*
	 * Reverse the chars from index 'start' to 'end', inclusive.
	 */
	public static void reverseWord(int start, int end, char[] chars) {
		while (start < end) {
			char temp = chars[start];
			chars[start] = chars[end];
			chars[end] = temp;
			start++;
			end--;
		}
	}

	public static void main(String[] args) {

                // Test the function reverseWordsInString()

		String test1 = "now me tell";
		String expected1 = "tell me now";
		assertTrue(expected1.equals(reverseWordsInString(test1)));

		String test2 = "highlander";
		String expected2 = "highlander";
		assertTrue(expected2.equals(reverseWordsInString(test2)));

		String test3 = "";
		String expected3 = "";
		assertTrue(expected3.equals(reverseWordsInString(test3)));

		String test4 = null;
		assertNull(reverseWordsInString(test4));

		String test5 = "you see to good    hello";
		String expected5 = "hello    good to see you";
		assertTrue(expected5.equals(reverseWordsInString(test5)));

		String test6 = "cake pound steal";
		String expected6 = "steal pound cake";
		assertTrue(expected6.equals(reverseWordsInString(test6)));

		String test7 = "pudding chocolate love I";
		String expected7 = "I love chocolate pudding";
		assertTrue(expected7.equals(reverseWordsInString(test7)));

		System.out.println("All tests passed!");
	}
}
