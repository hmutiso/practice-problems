import static org.junit.Assert.*;

public class MaxStockMarketGain {
	
	/*
	 * Given an array of stock prices represented as integers, determine
	 * the maximum gain that can be made by buying the stock on any day,
	 * and then selling the stock on a subsequent day.
	 * If no positive gain is possible, return -1.
	 * Time Complexity: O(n)
	 * Space Complexity: O(1)
	 */
	static int getMaxProfit(int[] stockPrices) {
		int arrLength = stockPrices.length;
		int maxProfit = Integer.MIN_VALUE;
		int minPrice = stockPrices[0];
		for (int i = 1; i < arrLength; i++) {
			if ((stockPrices[i] - minPrice) > maxProfit) {
				/* Buying at minPrice and selling at stockPrices[i]
				 * returns a better profit than what we currently have.
				 */
				maxProfit = stockPrices[i] - minPrice;
			}
			if (stockPrices[i] < minPrice) {
				// Update the min_price seen so far.
				minPrice = stockPrices[i];
			}
		}
		return maxProfit;
	}

	public static void main(String[] args) {
		int[][] stockPriceLists = { {10, 7, 5, 8, 11, 9},
		                            {10, 9, 8, 7, 6, 5},
		                            {1, 2, 3, 4, 5, 6, 7, 8} };
		int[] solutions = {6, -1, 7};
		
		int result = 0;
		for (int k = 0; k < stockPriceLists.length; k++) {
			result = getMaxProfit(stockPriceLists[k]);
			assertEquals(result, solutions[k]);
		}
		System.out.println("All unit tests passed!");
	}
}
