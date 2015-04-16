package colin.algorithm;

/**
 * Created by Colin Wang on 2015-04-08.
 * 把字符串中的每个空格替换成%20
 */
public class ReplaceSpace {
	public static String replaceSpace(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		// 将字符串转换为char数组
		char[] strChar = str.toCharArray();
		// 统计字符串中有多少个空格
		int spaceCount = 0;
		for (int i = 0; i < strChar.length; i++) {
			if (strChar[i] == ' ')
				spaceCount++;
		}
		// 计算出新的字符串长度
		int newSize = strChar.length + spaceCount * 2;
		char[] newStringChar = new char[newSize];
		int newStringIndex = newStringChar.length - 1;
		for (int i = strChar.length - 1; i >= 0; i--) {
			if (strChar[i] == ' ') {
				newStringChar[newStringIndex--] = '0';
				newStringChar[newStringIndex--] = '2';
				newStringChar[newStringIndex--] = '%';
			} else {
				newStringChar[newStringIndex--] = strChar[i];
			}
		}
		return new String(newStringChar);
	}

	public static void main(String[] args) {
		String test = "I am Colin Wang.";
		System.out.println(test);
		String result = replaceSpace(test);
		System.out.println(result);

	}
}
