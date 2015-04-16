package colin.algorithm;

/**
 * 实现一个方法：double power(double base, int e)，求base的e次方。
 * 不得使用库函数，同时不需考虑大数问题。
 * @author Colin Wang
 * Created on Apr 16, 2015
 */
public class Power {
	/*
	 * 功能测试：power(2,3) == 8
	 * 边界测试：power(1,0) == 1，power(2,-3) = 1/8等等
	 * 负面测试：
	 * 程序效率：
	 */
	public static double power(double base, int e) {
		if (e == 0)
			return 1;
		if (e == 1)
			return base;
		if (base == 0 && e < 0)
			throw new RuntimeException();
		int abse = e;
		// 如果指数为负数，则先转化为正数计算，最后结果再取倒数即可
		if (e < 0)
			abse = -e;
		// 如果要求base的4次方，则可以先求base的2次方
		double result = power(base, abse >> 1);
		// 把结果平方，得到base的4次方
		result *= result;
		// e如果为奇数，就再乘一次它本身
		// 使用位与运算判断e是否为奇数
		if ((abse & 0x1) == 1)
			result *= base;

		// 如果指数为负数，需要把计算结果取倒数
		if (e < 0)
			result = 1.0 / result;

		return result;
	}

	public static void main(String[] args) {
		double result = power(2,3);
		double result2 = power(2,-4);
		System.out.println(result);
		System.out.println(result2);
	}
}
