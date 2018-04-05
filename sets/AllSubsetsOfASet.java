import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;
import static org.junit.Assert.*;

public class AllSubsetsOfASet {
	
        /*
         * A recursive routine that generates all subsets of a set.
         */
	public static Set<Set<String>> getSubsetsRecursive(Set<String> inputSet) {
		if (inputSet == null) {
			throw new IllegalArgumentException("null set is invalid input");
		}
		Set<Set<String>> subsets = new HashSet<Set<String>>();
		subsets.add(inputSet);
		if (inputSet.size() == 0) {
			// the inputSet is an empty set; nothing more to do.
			return subsets;
		}
		// inputSet is NOT an empty set, so add the input set to the results
		subsets.add(new HashSet<String>());
		
		for (String str : inputSet) {
			Set<String> temp = new HashSet<String>(inputSet);
			assertTrue(temp.remove(str));
			subsets.addAll(getSubsetsRecursive(temp));
		}
		return subsets;
	}
	
        /*
         * Here's an iterative algorithm to generate all subsets of a set.
         */
	public static Set<Set<String>> getSubsetsIterative(Set<String> inputSet) {
		if (inputSet == null) {
			throw new IllegalArgumentException("null Set is invalid input");
		}
		Set<Set<String>> subsets = new HashSet<Set<String>>();
		subsets.add(inputSet);
		if (inputSet.size() == 0) {
			// inputSet is an empty set; nothing more to do
			return subsets;
		}
		subsets.add(new HashSet<String>()); // add an empty set
		/*
		 * subsets contains the empty set {0} and the {inputSet}.
		 * Now generate and add all other possible sets...
		 */
		int inputSetSize = inputSet.size();
		Set<Set<String>> setsOfSizeN = new HashSet<Set<String>>();
		setsOfSizeN.add(inputSet);
		for (int n = inputSetSize-1; n > 0; n--) {
			Set<Set<String>> setsOfSizeNMinus1 = new HashSet<Set<String>>();
			// Use all sets of size "n" to generate all sets of size "n-1"
			for (Set<String> setOfSizeN : setsOfSizeN) {
				for (String strToRemove : setOfSizeN) {
					Set<String> setOfSizeNMinus1 = new HashSet<String>(setOfSizeN);
					setOfSizeNMinus1.remove(strToRemove);
					setsOfSizeNMinus1.add(setOfSizeNMinus1);
				}
			}
			// Add all newly generated sets of size "n-1" to the final results
			subsets.addAll(setsOfSizeNMinus1);
			// setsOfSizeNMinus1 will be used to generate 'setsOfSizeNMinus2'
			setsOfSizeN = setsOfSizeNMinus1;
		}
		return subsets;
	}

	public static void main(String[] args) {

                /* Test both the iterative and recursive solutions above. */
				
		// Test 1: Set of size 2:
		Set<String> test1Set = new HashSet<String>();
		test1Set.add("a");
		test1Set.add("b");
		Set<Set<String>> result1SetRecursive = getSubsetsRecursive(test1Set);
		Set<Set<String>> result1SetIter = getSubsetsIterative(test1Set);
		assertEquals(4, result1SetRecursive.size());
		assertEquals(4, result1SetIter.size());
		System.out.println("input 1: " + Arrays.toString(test1Set.toArray()) +
				"\nresult 1: " + Arrays.toString(result1SetIter.toArray()));
		
		// Test 2: Set of size 3:
		Set<String> test2Set = new HashSet<String>();
		test2Set.add("a");
		test2Set.add("b");
		test2Set.add("c");
		Set<Set<String>> result2SetRecursive = getSubsetsRecursive(test2Set);
		Set<Set<String>> result2SetIter = getSubsetsIterative(test2Set);
		assertEquals(8, result2SetRecursive.size());
		assertEquals(8, result2SetIter.size());
		System.out.println("input 2: " + Arrays.toString(test2Set.toArray()) +
				"\nresult 2: " + Arrays.toString(result2SetIter.toArray()));
		
		// Test 3: Set of size 4:
		Set<String> test3Set = new HashSet<String>();
		test3Set.add("a");
		test3Set.add("b");
		test3Set.add("c");
		test3Set.add("d");
		Set<Set<String>> result3SetRecursive = getSubsetsRecursive(test3Set);
		Set<Set<String>> result3SetIter = getSubsetsIterative(test3Set);
		assertEquals(16, result3SetRecursive.size());
		assertEquals(16, result3SetIter.size());
		System.out.println("input 3: " + Arrays.toString(test3Set.toArray()) +
				"\nresult 3: " + Arrays.toString(result3SetIter.toArray()));
		
		// Test 4: Set of size 1:
		Set<String> test4Set = new HashSet<String>();
		test4Set.add("a");
		Set<Set<String>> result4SetRecursive = getSubsetsRecursive(test4Set);
		Set<Set<String>> result4SetIter = getSubsetsIterative(test4Set);
		assertEquals(2, result4SetRecursive.size());
		assertEquals(2, result4SetIter.size());
		System.out.println("input 4: " + Arrays.toString(test4Set.toArray()) +
				"\nresult 4: " + Arrays.toString(result4SetIter.toArray()));
		
		// Test 5: Empty set: 
		Set<String> test5Set = new HashSet<String>();
		Set<Set<String>> result5SetRecursive = getSubsetsRecursive(test5Set);
		Set<Set<String>> result5SetIter = getSubsetsIterative(test5Set);
		assertEquals(1, result5SetRecursive.size());
		assertEquals(1, result5SetIter.size());
		System.out.println("input 5: " + Arrays.toString(test5Set.toArray()) +
				"\nresult 5: " + Arrays.toString(result5SetIter.toArray()));
	}
}
