package colin.algorithm;

public class GreatestSumOfSubArray {

	public static int greatestSum(int[] a) {
		if (a == null || a.length == 0) {
			return 0;
		}
		int maxSum = 0;
		int currentSum = 0;
		for (int i = 0; i < a.length; i++) {
			if (currentSum <= 0) {
				// 如果当前最大值为负数，
				// 那么当前最大值+a[i]后必然比a[i]本身要小。
				currentSum = a[i];
			} else {
				currentSum += a[i];
			}

			if (currentSum > maxSum) {
				maxSum = currentSum;
			}
		}
		return maxSum;
	}

	public static void main(String[] args) {
		int[] a = { 1, -2, 3, 10, -4, 7, 2, -5 };
		System.out.println(greatestSum(a));
	}

}
