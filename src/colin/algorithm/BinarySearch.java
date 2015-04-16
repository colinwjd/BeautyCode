package colin.algorithm;

/**
 * 二分查找
 * Created by Colin Wang on 2015-04-09.
 */
public class BinarySearch {

	public static int binarySearch(int[] a, int key) {
		if (a == null || a.length == 0) {
			return -1;
		}
		return binarySearch(a, 0, a.length - 1, key);
	}

	private static int binarySearch(int[] a, int low, int high, int key) {
		int mid = (low + high) >>> 1;
		while (low < high) {
			if (a[mid] == key)
				return mid;
			else if (a[mid] < key)
				return binarySearch(a, mid + 1, high, key);
			else if (a[mid] > key)
				return binarySearch(a, low, mid - 1, key);
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] a = new int[]{1, 2, 3, 4, 5};
		int index = binarySearch(a, 3);
		int index2 = binarySearch(a, 9);
		System.out.println(index);
		System.out.println(index2);
	}
}
