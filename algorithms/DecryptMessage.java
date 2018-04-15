import static org.junit.Assert.*;

public class DecryptMessage {
    /*
     * Problem:
     * 
     * An infamous gang of cyber criminals named “The Gray Cyber Mob”, which is
     * behind many hacking attacks and drug trafficking, has recently become a
     * target for the FBI. After intercepting some of their messages, which
     * looked like complete nonsense, the agency learned that they indeed encrypt
     * their messages, and studied their method of encryption.
     * Their messages consist of lowercase latin letters only, and every word is
     * encrypted separately as follows:
     * 	1) Convert every letter to its ASCII value.
     * 	2) Add 1 to the first letter, and then for every letter from the second
     * 	   one to the last one, add the value of the previous letter.
     * 	3) Subtract 26 from every letter until it is in the range of lowercase
     * 	   letters a-z in ASCII.
     *  4) Convert the values back to letters.
     *  
     * For instance, to encrypt the word “crime” :
     * 		Decrypted message:	c	r	i	m	e
     * 		Step 1:	            99	114	105	109	101
     * 		Step 2:	           100	214	319	428	529
     * 		Step 3:	           100	110	111	116	113
     * 		Encrypted message:	d	n	o	t	q
     * 
     * The FBI needs an efficient method to decrypt messages. Write a function
     * named decrypt(word) that receives a string that consists of small latin
     * letters only, and returns the decrypted word. Explain your solution and
     * analyze its time and space complexities.
     */
    public static final int LATIN_ALPHABET_CHAR_CNT = 26;
    
    /*
     * Runtime complexity: O(n)
     * Space complexity: O(n) for allocating inputArr and result array.
     */
    public static String decryptMessage(String encryptedStr) {
        if (encryptedStr == null) {
            throw new IllegalArgumentException("input must be NON-null");
        }
        if (encryptedStr.length() == 0) {
            return encryptedStr;
        }
        char[] encryptedArr = encryptedStr.toCharArray();
        char[] decryptedArr = new char[encryptedArr.length];
        /*
         * For i = 0:
         * enc[0] = dec[0] + 1 - (26 * m), until ['a' <= enc[0] <= 'z']
         * Therefore:
         * dec[0] = enc[0] - 1 + (26 * m), until ['a' <= dec[0] <= 'z']
         */
        decryptedArr[0] = (char)(encryptedArr[0] - 1);
        while (decryptedArr[0] < 'a') {
            decryptedArr[0] += LATIN_ALPHABET_CHAR_CNT;
        }
        /*
         * For i > 0:
         * (a) enc[i] = step2[i-1] + dec[i] - (26 * m), until ['a' <= enc[0] <= 'z']
         * Rearranging expression (a) to solve for dec[i] gives:
         * (b) dec[i] = enc[i] - step2[i-1] + (26 * m), until ['a' <= dec[0] <= 'z']
         * 
         * How do we get step2[i-1] ?
         * 		(c) For i = 1, step2[i-1] = step2[0] = dec[0]   + 1
         * 		(d) For i > 1, step2[i-1] =            dec[i-1] + step2[i-2]
         * 
         * We can combine (c) and (d) as follows:
         * 		(e) step2[i-1] = dec[i-1] + step2[i-2], where step2[-1] = 1
         * 
         * Therefore, combining (b) and (e) results in the expression:
         * (f) For i > 0: dec[i] = enc[i] - dec[i-1] - step2[i-2] + (26 * m),
         * 						- until ['a' <= dec[0] <= 'z']
         * 						- using: step2[-1] = 1
         */
        int step2Prev = 1;
        int decryptedCharAsInt = 0;
        for (int i=1; i < encryptedArr.length; i++) {
            decryptedCharAsInt = (int)(encryptedArr[i] - decryptedArr[i-1] - step2Prev);
            while (decryptedCharAsInt < 'a') {
                decryptedCharAsInt += LATIN_ALPHABET_CHAR_CNT;
            }
            decryptedArr[i] = (char)decryptedCharAsInt;
            step2Prev += decryptedArr[i-1];
        }
        return new String(decryptedArr);
    }
    
    /*
     * Wrote encryptMessage() to facilitate easy testing of decryptMessage().
     */
    public static String encryptMessage(String input) {
        if (input == null) {
            throw new IllegalArgumentException("input must be NON-null");
        }
        if (input.length() == 0) {
            return input;
        }
        char[] result = new char[input.length()];
        
        result[0] = (char)(input.charAt(0) + 1);
        for (int i=1; i < input.length(); i++) {
            result[i] = (char)(input.charAt(i) + result[i-1]);
        }
        for (int i=0; i < result.length; i++) {
            while (result[i] > 'z') {
                result[i] -= LATIN_ALPHABET_CHAR_CNT;
            }
        }
        return new String(result);
    }
    
    public static void main(String[] args) {
        
        // Test encryptMessage()
        assertEquals("dnotq", encryptMessage("crime"));
        assertEquals("flgxswdliefy", encryptMessage("encyclopedia"));
        
        // Test 1:
        String input1 = "crime";
        //System.out.println("Encrypting: " + input1 + " ==> " + encryptMessage(input1));
        assertEquals(input1, decryptMessage(encryptMessage(input1)));
        
        // Test 2:
        String input2 = "encyclopedia";
        //System.out.println("Encrypting: " + input2 + " ==> " + encryptMessage(input2));
        assertEquals(input2, decryptMessage(encryptMessage(input2)));
        
        // Test 3:
        String input3 = "z";
        //System.out.println("Encrypting: " + input3 + " ==> " + encryptMessage(input3));
        assertEquals(input3, decryptMessage(encryptMessage(input3)));
        
        // Test 4:
        String input4 = "zzzzzzz";
        //System.out.println("Encrypting: " + input4 + " ==> " + encryptMessage(input4));
        assertEquals(input4, decryptMessage(encryptMessage(input4)));
        
        // Test 5:
        String input5 = "a";
        //System.out.println("Encrypting: " + input5 + " results in: " + encryptMessage(input5));
        assertEquals(input5, decryptMessage(encryptMessage(input5)));
        
        // Test 6:
        String input6 = "aaaaaaaaaaaa";
        //System.out.println("Encrypting: " + input6 + " results in: " + encryptMessage(input6));
        assertEquals(input6, decryptMessage(encryptMessage(input6)));

        System.out.println("All unit tests passed!");
    }
}
