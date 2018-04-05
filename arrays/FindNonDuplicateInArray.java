import static org.junit.Assert.*;

public class FindNonDuplicateInArray {

    public static int findNonDuplicateInArray(int[] A) {
        if (A == null || A.length == 0) {
            throw new IllegalArgumentException("array must be non-null and length >= 1");
        }
        int result = 0;
        for (int i = 0; i < A.length; i++) {
            result ^= A[i];
        }
        return result;
    }
    
    public static void main(String[] args) {

        int[] test1 = new int[] {4, 5, 2, 7, 9, 2, 5, 4, 7};
        assertEquals(findNonDuplicateInArray(test1), 9);
        
        int[] test2 = new int[] {2};
        assertEquals(findNonDuplicateInArray(test2), 2);

        int[] test3 = new int[] {};
        try {
            findNonDuplicateInArray(test3);
        } catch (IllegalArgumentException e) {
            System.out.println("Generated IllegalArgumentException as expected");
        }

        System.out.println("All unit tests passed!");
    }
}
