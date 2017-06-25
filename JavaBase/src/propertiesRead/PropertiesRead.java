package propertiesRead;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**属性文件的读取*/
public class PropertiesRead {

	/**读取配置文件,返回map集合*/
	public static Map<String, String> read(File file) throws Exception{
		
		Map<String, String> map = new HashMap<String,String>();
		//获取输入流
		InputStreamReader inputStream = new InputStreamReader(new FileInputStream(file), "utf-8");
		//创建属性文件对象
		Properties properties = new Properties();
		//传入输入流
		properties.load(inputStream);
		//读取配置文件的key们
		Enumeration<?> enumeration = properties.propertyNames();
		//变量key们,获取每个对象
		while (enumeration.hasMoreElements()) {
			String key = (String) enumeration.nextElement();
			String value = (String) properties.getProperty(key);
			map.put(key, value);
		}
		return map;
	}
	
	public static void main(String[] args) throws Exception{
		File file = new File("F:\\JAVA_WorkSpace\\JavaBase\\src\\propertiesRead\\student.properties");
		Map<String, String> map = read(file);
		//遍历map
		for (Map.Entry<String, String> entry : map.entrySet()) {
			//Map.entry<String,String> 映射项（键-值对）  有几个方法：用上面的名字entry
			//entry.getKey();键的查询
			//entry.getValue(); entry.setValue();值的查询/修改	
			//map.entrySet()  返回此映射中包含的映射关系的 Set视图。
			System.out.println("键:" + entry.getKey() + "   值:"
						+ entry.getValue());
	       }
		
	}
}
