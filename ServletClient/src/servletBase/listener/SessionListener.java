package servletBase.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 	Session对象的监听器
 */
@WebListener//使用注解,完成配置
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener, 
											HttpSessionBindingListener, HttpSessionActivationListener {

    public SessionListener() {
    }

    public void sessionCreated(HttpSessionEvent arg0)  { 
    	System.out.println("Session创建");
    }
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
    	System.out.println("Session销毁");
    }
    public void valueBound(HttpSessionBindingEvent arg0)  { 
    	System.out.println("Session绑定对象");
    }
    public void valueUnbound(HttpSessionBindingEvent arg0)  { 
    	System.out.println("Session解接触绑定对象");
    }
    public void attributeAdded(HttpSessionBindingEvent arg0)  { 
    	System.out.println("Session属性增加");
    }
    public void attributeRemoved(HttpSessionBindingEvent arg0)  { 
    	System.out.println("Session属性删除");
    }
    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
    	System.out.println("Session属性修改");
    }
    public void sessionWillPassivate(HttpSessionEvent arg0)  { 
    	System.out.println("Session执行钝化");
    }
    public void sessionDidActivate(HttpSessionEvent arg0)  { 
    	System.out.println("Session执行活化");
    }
	
}
