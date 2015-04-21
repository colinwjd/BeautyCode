/**
 * 
 */
package colin.algorithm;

/**
 * 仿库函数，把字符串转化为整数
 * 
 * @author Colin Wang Created on Apr 21, 2015
 */
public class ATOI {

	public static boolean isValidInput = true;

	public static int atoi(String string) {
		// 非法输入
		if (string == null || string.trim().length() == 0) {
			isValidInput = false;
			return 0;
		}
		char[] chars = string.trim().toCharArray();
		int result = 0;
		boolean minus = false;

		int low = 0;
		if (chars[low] == '+') {
			low++;
		} else if (chars[low] == '-') {
			minus = true;
			low++;
		}
		if (low < chars.length) {
			result = strToInt(chars, low, minus);
		} else {
			isValidInput = false;
			return 0;
		}
		return result;
	}

	private static int strToInt(char[] chars, int start, boolean minus) {
		int result = 0;
		for (int i = start; i < chars.length; i++) {
			if (chars[i] >= '0' && chars[i] <= '9') {
				int flag = minus ? -1 : 1;
				result = result * 10 + flag * (chars[i] - '0');
				if (!minus && result > 0x7FFFFFFF) {
					return Integer.MAX_VALUE;
				} else if (minus && result < 0x80000000) {
					return Integer.MIN_VALUE;
				}
			} else {
				isValidInput = false;
				return 0;
			}
		}
		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String string = "-00023";
		System.out.println(atoi(string));
		System.out.println(isValidInput);
	}

}
