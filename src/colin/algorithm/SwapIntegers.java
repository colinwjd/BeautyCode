package colin.algorithm;

/**
 * 不使用临时变量交换两个数
 * 
 * @author Colin Wang Created on Apr 26, 2015
 */
public class SwapIntegers {

	/**
	 * 使用位操作，效率更高，并且不会有溢出问题
	 * @param a
	 * @param b
	 */
	public static void swap(int a, int b) {
		System.out.println("交换前" + a + "," + b);
		
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		
		System.out.println("交换后" + a + "," + b);
	}

	/**
	 * 可行方案，但是要注意整数溢出
	 * @param a
	 * @param b
	 */
	public static void exchange(int a, int b) {
		System.out.println("交换前" + a + "," + b);
		
		a = a - b;
		b = a + b;
		a = b - a;
		
		System.out.println("交换后" + a + "," + b);
	}

	public static void main(String[] args) {
		int a = 10;
		int b = 20;
		swap(a, b);
		System.out.println();
		exchange(a, b);
	}
}
