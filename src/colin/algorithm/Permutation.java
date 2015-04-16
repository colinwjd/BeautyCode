/**
 * 
 */
package colin.algorithm;

/**
 * 生成字符串中字符的全排列
 * 
 * @author Colin Wang Created on Apr 16, 2015
 */
public class Permutation {

	public static void printPermutationOfStr(String str) {
		if (str == null) {
			return;
		}
		char[] chars = str.toCharArray();
		permutation(chars, 0);
	}

	public static void permutation(char[] chars, int start) {
		if (start >= chars.length) {
			return;
		}
		if (start == chars.length - 1) {
			System.out.println(new String(chars));
		} else {
			for (int i = start; i < chars.length; i++) {
				swap(chars, start, i);
				permutation(chars, start + 1);
				swap(chars, start, i);
			}
		}
	}

	private static void swap(char[] chars, int m, int n) {
		char temp = chars[m];
		chars[m] = chars[n];
		chars[n] = temp;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String test = "012";
		Permutation.printPermutationOfStr(test);
	}

}
