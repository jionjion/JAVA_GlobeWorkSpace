package servletBase.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 	ServletContext对象的创建销毁和属性修改方法
 */
public class ContextListener implements ServletContextListener, ServletContextAttributeListener {

    public ContextListener() {
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
    	System.out.println("context容器的初始化方法");    
    }
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	System.out.println("context容器的销毁方法");
    }
    
    public void attributeAdded(ServletContextAttributeEvent arg0)  { 
    	System.out.println("context容器的对象属性增加");
    }
    public void attributeReplaced(ServletContextAttributeEvent arg0)  { 
    	System.out.println("context容器的对象属性修改");
    }
    public void attributeRemoved(ServletContextAttributeEvent arg0)  {
    	System.out.println("context容器的对象属性删除");
    }
	
}
