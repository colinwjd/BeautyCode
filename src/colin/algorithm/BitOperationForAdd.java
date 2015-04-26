/**
 * 
 */
package colin.algorithm;

/**
 * 不用算数运算符实现加法
 * 
 * @author Colin Wang Created on Apr 26, 2015
 */
public class BitOperationForAdd {

	public static int addIntegerByBit(int a, int b) {
		// 递归终止条件
		if (b == 0) {
			return a;
		}
		// 只相加不进位
		int sum = a ^ b;
		// 只进位
		int carry = (a & b) << 1;
		// 递归执行
		return addIntegerByBit(sum, carry);
	}

	public static void main(String[] args) {
		System.out.println(addIntegerByBit(10, 12));
	}
}
