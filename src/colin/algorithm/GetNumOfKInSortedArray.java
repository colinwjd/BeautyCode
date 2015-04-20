/**
 * 
 */
package colin.algorithm;

/**
 * 统计一个数字在排序数组中出现的次数
 * 
 * @author Colin Wang Created on Apr 20, 2015
 */
public class GetNumOfKInSortedArray {

	public static int getNumOfK(int[] a, int k) {
		assert a != null && a.length != 0;
		int number = 0;

		int firstIndexOfK = getFirstIndexOfK(a, k, 0, a.length - 1);
		int lastIndexOfK = getLastIndexOfK(a, k, 0, a.length - 1);

		System.out.println(firstIndexOfK);
		System.out.println(lastIndexOfK);

		if (firstIndexOfK > -1 && lastIndexOfK > -1) {
			number = lastIndexOfK - firstIndexOfK + 1;
		}
		return number;
	}

	private static int getFirstIndexOfK(int[] a, int k, int low, int high) {
		int mid = (low + high) >>> 1;
		while (low < high) {
			if (k > a[mid]) {
				return getFirstIndexOfK(a, k, mid + 1, high);
			} else if (k < a[mid]) {
				return getFirstIndexOfK(a, k, low, mid - 1);
			} else {
				if ((mid > 0 && a[mid - 1] != k) || mid == 0) {
					return mid;
				} else {
					return getFirstIndexOfK(a, k, low, mid - 1);
				}
			}
		}
		return -1;
	}

	private static int getLastIndexOfK(int[] a, int k, int low, int high) {
		int mid = (low + high) >>> 1;
		while (low < high) {
			if (k > a[mid]) {
				return getLastIndexOfK(a, k, mid + 1, high);
			} else if (k < a[mid]) {
				return getLastIndexOfK(a, k, low, mid - 1);
			} else {
				if ((mid < high && a[mid + 1] != k) || mid == high) {
					return mid;
				} else {
					return getLastIndexOfK(a, k, mid + 1, high);
				}
			}
		}
		return -1;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] a = { 1, 2, 3, 3, 3, 3, 4, 5, 5 };
		int result = getNumOfK(a, 5);
		System.out.println(result);
	}

}
