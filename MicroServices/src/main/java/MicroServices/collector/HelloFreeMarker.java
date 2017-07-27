package MicroServices.collector;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloFreeMarker {

	/**	该模块的显示页面
	 * 	调用freemaker模板
	 *  URL: 
	 * */
    @RequestMapping("hello")
    public String hello(Model model){
    	System.out.println("-----------------------");
        model.addAttribute("message", "freemarker");
        return "hello";
    }
	
}
