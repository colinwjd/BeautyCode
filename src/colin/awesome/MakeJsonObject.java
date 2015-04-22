package colin.awesome;

import org.json.JSONObject;

/**
 * 创建Json格式的数据，引入jar包：json-rpc-1.0.jar
 * 
 * @author Colin Wang Created on Apr 22, 2015
 */
public class MakeJsonObject {
	public static void main(String[] args) {
		JSONObject json = new JSONObject();
		json.put("name", "colin");
		json.put("age", "22");
		String jsonString = json.toString();
		System.out.println(jsonString);
	}
}
