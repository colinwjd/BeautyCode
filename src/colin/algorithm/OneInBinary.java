package colin.algorithm;

/**
 * 给定一个整数，输出该整数二进制表示中1的个数
 * Created by Colin Wang on 2015-04-09.
 */
public class OneInBinary {
	// 该方法当输入为负值时，会导致死循环。因为在进行右移时，正数会在高位补0，负数则会在高位补1。
	public static int oneInBinary(int n) {
		int oneCount = 0;
		while (n != 0) {
			if ((n & 1) == 1)
				oneCount++;
			n >>= 1;
		}
		return oneCount;
	}

	public static int oneInBinaryNice(int n) {
		int count = 0;
		while (n != 0) {
			// 通过这种方式可以清除n的最低有效位，即把n的二进制表示中最右边的1变成0。
			n = n & (n - 1);
			count++;
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(oneInBinary(2));
		System.out.println(oneInBinaryNice(15));
		System.out.println(oneInBinaryNice(-1));
	}
}
