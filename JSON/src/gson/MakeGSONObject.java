package gson;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bean.Student;

/**
 * 	创建GSON对象*/
public class MakeGSONObject {

	/**使用javaBean创建JSON对象*/
	public static void createGSONObjectByJavaBean() throws Exception {
		Student student = new Student();
		student.setName("张谦");
		student.setAge(24.2);
		student.setBirthday(new SimpleDateFormat("YYYY-MM-DD").parse("1994-04-12"));	//日期格式不友善
		student.setSchool("河南科技学院");
		student.setSingle(false);
		student.setCar(null);
		//创建GSON对象
//		Gson gson = new Gson();	//可以直接创建GSON对象
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();	//格式化打印
		gsonBuilder.setFieldNamingStrategy(new FieldNamingStrategy() {	//定制返回策略
			//回调函数,对解析时的JSON对象进行处理
			@Override
			public String translateName(Field field) {		//field,反射时的对象
				if (field.getName().equals("school")) {		//对象的属性名字
					return "School是";
				}
				return field.getName();
			}
		});
		Gson gson = gsonBuilder.create();	//创建GSON对象
		System.out.println("GSON对象:");
		System.out.println(gson.toJson(student));
		System.out.println("-------------------");
	}
	
	public static void main(String[] args) throws Exception{
		createGSONObjectByJavaBean();
	}
}
