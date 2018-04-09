import static org.junit.Assert.*;

public class CountingSortScores {

    /*
     * sortScores() cannot handle arrays with negative numbers.
     * Time complexity: O(n)
     * Space complexity: O(n), since we allocate a new array of sorted scores.
     * The O(n) space cost could be eliminated if we overwrite the input array,
     * but that is not ideal in certain cases.
     *
     * Note the above time and space complexity assumes highestPossibleScore is
     * a constant. If highestPossibleScore is NOT a constant, what is the time
     * and space complexity?
     * 	    Space complexity: O(n + k), where k is the range of possible values
     * 	        - O(n) : allocating the result (sorted) array of length n
     * 		- O(k) : allocating the bucket array of length k
     * 	    Time complexity: O(n + k) :
     * 		- O(n) : iterating over the input array + populating the result array
     * 		- O(k) : initializing the buckets array with all zeros.
     */
    public static int[] sortScores(int[] unsortedScores, int highestPossibleScore) {
        if (unsortedScores == null || highestPossibleScore < 0) {
            throw new IllegalArgumentException("null input array is invalid");
        }
        int[] scoreBuckets = new int[highestPossibleScore + 1];
        for (int j = 0; j < unsortedScores.length; j++) {
            if (unsortedScores[j] < 0) {
                throw new IllegalArgumentException("Non-negative integers not allowed");
            }
            scoreBuckets[unsortedScores[j]]++;
        }
        int[] sortedScores = new int[unsortedScores.length];
        int sortedScoresIndex = 0;
        for (int score = 0; score < scoreBuckets.length; score++) {
            // scoreBuckets[score] = number of times 'score' appears in unsortedScores
            while (scoreBuckets[score] > 0) {
                sortedScores[sortedScoresIndex] = score;
                sortedScoresIndex++;
                scoreBuckets[score]--;
            }
        }
        return sortedScores;
    }

    public static void main(String[] args) {
        // Test 1
        int[] test1 = new int[] {3, 2, 4, 1, 5};
        int highest1 = 5;
        int[] expected1 = new int[] {1, 2, 3, 4, 5};
        int[] results1 = sortScores(test1, highest1);
        for (int i = 0; i < test1.length; i++) {
            assertEquals(results1[i], expected1[i]);
        }

        // Test 2
        int[] test2 = new int[] {1, 2, 3, 4, 5, 6, 7, 8};
        int highest2 = 10;
        int[] expected2 = new int[] {1, 2, 3, 4, 5, 6, 7, 8};
        int[] results2 = sortScores(test2, highest2);
        for (int i = 0; i < test2.length; i++) {
            assertEquals(results2[i], expected2[i]);
        }

        // Test 3
        int[] test3 = new int[] {5};
        int highest3 = 10;
        int[] expected3 = new int[] {5};
        int[] results3 = sortScores(test3, highest3);
        for (int i = 0; i < test3.length; i++) {
            assertEquals(results3[i], expected3[i]);
        }

        // Test 4
        int[] test4 = new int[] {};
        int highest4 = 6;
        int[] expected4 = new int[] {};
        int[] results4 = sortScores(test4, highest4);
        for (int i = 0; i < test4.length; i++) {
            assertEquals(results4[i], expected4[i]);
        }

        System.out.println("All tests passed!");
    }
