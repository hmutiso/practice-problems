import static org.junit.Assert.*;

public class BinarySearch {
	
    /*
     * Assumes the input array is sorted in ascending order
     * @parameter inputArray
     * @parameter toFind : integer to search for in "inputArray"
     * @return boolean
     * 		Returns "true" if "toFind" is found, and "false" otherwise
     * Time complexity: O(log n)
     * Space complexity: O(1)
     */
    public static boolean binarySearch(int[] inputArray, int toFind) {
        if (inputArray.length == 0) {
            throw new IllegalArgumentException("non-zero length array required");
        }
        int start = 0;
        int end = inputArray.length -1;
        int mid;
        
        while (start <= end) {
            mid = start + ((end-start)/2);
            if (inputArray[mid] == toFind) {
                    return true;
            } else if (inputArray[mid] < toFind) {
                    start = mid + 1;
            } else {
                    end = mid - 1;
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        int[] inputArray = {1, 2, 3, 4, 5, 6, 7};
        assertTrue(binarySearch(inputArray, 5));
        assertTrue(binarySearch(inputArray, 1));
        assertFalse(binarySearch(inputArray, 10));
        assertTrue(binarySearch(inputArray, 7));
        
        System.out.println("All unit tests passed!");
    }
}
