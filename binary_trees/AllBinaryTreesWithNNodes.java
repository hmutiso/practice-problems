import java.util.Set;
import java.util.HashSet;

public class AllBinaryTreesWithNNodes {

	public static class BinaryTreeNode {
		private BinaryTreeNode left;
		private BinaryTreeNode right;
		
		public BinaryTreeNode(BinaryTreeNode left, BinaryTreeNode right) {
			this.left = left;
			this.right = right;
		}
		public BinaryTreeNode() {
			this.left = null;
			this.right = null;
		}
	}
	
	public static Set<BinaryTreeNode> getAllBinaryTreesWithNNodes(int nodeCnt) {
		if (nodeCnt < 0) {
			throw new IllegalArgumentException("nodeCnt must be non-negative");
		}
		Set<BinaryTreeNode> result = new HashSet<BinaryTreeNode>();
		if (nodeCnt == 0) {
                        // Return a set containing a null binary tree node
			result.add(null);
			return result;
		}
		if (nodeCnt == 1) {
                        // Return a set containing one binary tree node
			result.add(new BinaryTreeNode());
			return result;
		}
                /*
                 * Generate the various possible binary trees as follows:
                 *  1) Reserve one node for the root node.
                 *  2) Vary the size of the left subtree from 0 to (nodeCnt - 1).
                 *  3) Once leftSubtreeSize is picked, then rightSubtreeSize will be set
                 *     to (nodeCnt - 1 - leftSubtreeSize).
                 *  4) Recursively repeat these steps with each left and right subtree,
                 *     until the subtree size is 1 or 0, then reconstruct the binary
                 *     trees from the bottom up.
                 */
		for (int leftSubTreeSize = 0; leftSubTreeSize < nodeCnt; leftSubTreeSize++) {
                        /*
                         * Each BinaryTreeNode in possibleLeftSubtrees and possibleRightSubtrees
                         * is a root node of a binary tree.
                         */
			Set<BinaryTreeNode> possibleLeftSubtrees =
					getAllBinaryTreesWithNNodes(leftSubTreeSize);
			Set<BinaryTreeNode> possibleRightSubtrees =
					getAllBinaryTreesWithNNodes(nodeCnt - 1 - leftSubTreeSize);
			for (BinaryTreeNode leftSubTree : possibleLeftSubtrees) {
				for (BinaryTreeNode rightSubTree : possibleRightSubtrees) {
					BinaryTreeNode currBinaryTreeNode =
                                                new BinaryTreeNode(leftSubTree, rightSubTree);
					result.add(currBinaryTreeNode);
				}
			}
		}
		return result;
	}
}
