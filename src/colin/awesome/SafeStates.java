package colin.awesome;

import java.util.HashMap;
import java.util.Map;

/**
 * 不可变对象的初始化安全性
 * @author Colin Wang
 * Created on Apr 25, 2015
 */
public class SafeStates {
	// 对于含有final域的对象，初始化安全性可以防止对对象的初始引用被重排序到构造过程之前。
	// 所以，初始引用一定会指向一个被完整构造的对象。当构造函数完成时，构造函数对final对象
	// 的所有写入操作，以及对通过这些final域可以到达的任何变量的写入操作，都将被“冻结”，并且
	// 任何获得对象引用的线程都至少能确保看到被冻结的值。
	private final Map<String, String> states;

	public SafeStates() {
		// 对于通过final域可到达的初始变量的写入操作，将不会与构造过程后的操作一起被重排序。
		states = new HashMap<>();
		states.put("Colin", "Wang");
		states.put("Thread", "Safe");
		states.put("Hash", "Map");
	}
	
	public String getAbbreviation(String s) {
		return states.get(s);
	}
}
