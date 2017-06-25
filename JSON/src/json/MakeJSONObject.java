package json;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import bean.Student;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**创建JSON对象*/
public class MakeJSONObject {
	/* 
   {
	"name" 	: 	"张谦",
	"age"	:	24.2,
	"birthday":	"1994-4-12",
	"school":	"河南科技学院",
	"major"	:	["机械设计制造及其自动化","电气工程及其自动化专业","软件工程"],
	"single":	false,
	"car"	:	null
	}
	 */
	/**使用JSONObject类实现对象的创建*/
	public static void createJSONObjectByJSONObject() throws JSONException{
		JSONObject jsonObject = new JSONObject();
		Object nullObject = null;	//创建空对象
		jsonObject.put("name", "张谦");
		jsonObject.put("age", 24.2);
		jsonObject.put("birthday", "1994-4-12");
		jsonObject.put("school", "河南科技学院");
		jsonObject.put("marjor", new String[]{"机械设计制造及其自动化","电气工程及其自动化专业","软件工程"});
		jsonObject.put("single", false);
		jsonObject.put("car", nullObject);	//不可以直接放入null,这里封装对象
		//输出
		System.out.println("JSON对象:");
		System.out.println(jsonObject.toString());
		System.out.println("-------------------");
		
	}
	
	/**使用Map创建JSON对象*/
	public static void createJSONObjectByMap() throws JSONException{
		Map<String, Object> map = new HashMap<String, Object>();
		Object nullObject = null;	//创建空对象
		map.put("name", "张谦");
		map.put("age", 24.2);
		map.put("birthday", "1994-4-12");
		map.put("school", "河南科技学院");
		map.put("major", new String[]{"机械设计制造及其自动化","电气工程及其自动化专业","软件工程"});
		map.put("single", false);
		map.put("car", nullObject);	//不可以直接放入null,这里封装对象
		//创建JSON对象
		JSONObject jsonObject = new JSONObject();
		jsonObject.accumulateAll(map);
		//输出
		System.out.println("JSON对象:");
		System.out.println(jsonObject.toString());
		System.out.println("-------------------");
		
	}
	
	/**使用javaBean创建JSON对象*/
	@SuppressWarnings("deprecation")
	public static void createJSONObjectByJavaBean() throws Exception {
		Student student = new Student();
		student.setName("张谦");
		student.setAge(24.2);
		student.setBirthday(new SimpleDateFormat("YYYY-MM-DD").parse("1994-04-12"));	//日期格式不友善
		student.setSchool("河南科技学院");
		student.setSingle(false);
		student.setCar(null);
		//创建JSON对象
		JSONObject jsonObject = JSONObject.fromObject(student);		//工厂模式
		System.out.println("JSON对象:");
		System.out.println(jsonObject.toString());
		System.out.println("-------------------");
	}
	
	public static void main(String[] args) throws Exception {
//		createJSONObjectByJSONObject();
		createJSONObjectByMap();
//		createJSONObjectByJavaBean();
	}
}

