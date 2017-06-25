package gson;

import java.io.File;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bean.Student;

/**解析JSON对象*/
public class ParseGSONObject {

	/**使用文件解析GSON对象*/
	public static void parseGSONObjectByFile() throws Exception{
		File file = new File("F:\\JAVA_WorkSpace\\JSON\\src\\json\\GSON对象.gson");	//使用类路径,完成加载
		//将文件整体读取为一个String
		String content = FileUtils.readFileToString(file,"utf8");
		//创建GSON对象
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();	//创建日期格式
		//创建对象,可以直接将JSON对象转为JavaBean对象
		//可以自动识别List和Set集合
		Student student = gson.fromJson(content, Student.class);
		System.out.println("姓名为:"+student.getName());
		System.out.println("年龄为:"+student.getAge());
		System.out.println("生日为:"+student.getBirthday());
		System.out.println("学校为:"+student.getSchool());
		System.out.println("所学专业有:"+student.getMajor());
		System.out.println("是否单身:"+student.isSingle());
		
	}
	
	public static void main(String[] args) throws Exception{
		parseGSONObjectByFile();
	}
}
