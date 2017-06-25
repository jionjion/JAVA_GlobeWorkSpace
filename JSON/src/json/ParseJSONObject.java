package json;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**解析为JSON对象*/
public class ParseJSONObject {

	/**使用文件解析JSON对象*/
	public static void parseJSONObjectByFile() throws Exception{
		File file = new File("F:\\JAVA_WorkSpace\\JSON\\src\\json\\JSON对象.json");	//使用类路径,完成加载
		//将文件整体读取为一个String
		String content = FileUtils.readFileToString(file,"utf8");
		//创建JSON对象
		JSONObject jsonObject = JSONObject.fromObject(content);
		//读取JSON属性
		String name = jsonObject.getString("name");
		System.out.println("姓名为:"+name);
		float age = (float) jsonObject.getDouble("age");
		System.out.println("年龄为:"+age);
		Date birthday = new SimpleDateFormat("YYYY-MM-DD").parse(jsonObject.getString("birthday"));
		System.out.println("生日为:"+birthday);
		String school = jsonObject.getString("school");
		System.out.println("学校为:"+school);
		JSONArray jsonArray = jsonObject.getJSONArray("major");
		for (Object object : jsonArray) {
			System.out.println("所学专业有:"+object.toString());
		}
		boolean single = jsonObject.getBoolean("single");
		System.out.println("是否单身:"+single);
		
		
	}
	
	public static void main(String[] args) throws Exception{
		parseJSONObjectByFile();
	}
}
