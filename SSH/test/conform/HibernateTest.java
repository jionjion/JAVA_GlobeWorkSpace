package conform;

import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HibernateTest{

	private static ApplicationContext applicationContext = null;
	
	/**在类加载时,读取配置文件,完成加载*/
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	@Test
	public void test() {
		//获取SessionFactory工厂
		SessionFactory sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");
		//输出查看
		System.out.println(sessionFactory);
	}

}
