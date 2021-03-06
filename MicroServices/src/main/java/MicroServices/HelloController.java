package MicroServices;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
/**
 * 	在最初创立项目时,默认文件在同一个目录下*/
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController		//等于@Controller[类注解] + @ResponseBody[方法注解]
@RequestMapping("/demo")	//类注解,增加命名空间
public class HelloController {

	@Value("${author}")				//从配置文件中获取自定义的常量
	private String author;
	
	/**	http://localhost:8080/MicroServices/demo/helloWorld	*/
	@RequestMapping(value="/helloWorld")			//默认使用get和post.每一个URL必须唯一
	public String say() {
		
		return "<h4>this is micro services , building with springboot and freemarker！！！! </h4><br>"
				+ "	author :"+author;
	}
	
	/**	http://localhost:8080/MicroServices/demo/hello/param11	*/
	@RequestMapping(value="/hello/{param}")						//参数名称要求一致
	public String getPathVariable(@PathVariable("param") String param) {//参数如果是数字,使用包装类.汉字则需要转码
		
		return "获得参数:"+param;
	}
	
	/**	http://localhost:8080/MicroServices/demo/hello?param=汉字	*/
	@GetMapping(value="/hello")				//使用get方式的注解,参数如果是数字,使用包装类.汉字则需要转码
	public String getRequestParam(@RequestParam(value="param",required=false,defaultValue="默认参数",name="param") String param) {	//传参和形参名称不需要一致
		
		return "获得参数:"+param;
	}
}
