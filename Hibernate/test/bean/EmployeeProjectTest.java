package bean;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *	��Զ�Ĳ�����
 */
public class EmployeeProjectTest {
	private  SessionFactory sessionFactory;
	private  Session session;
	private  Transaction transaction;
	
	/* ���Է���ǰ */
	@Before
	public void init(){
		//����Configuration����,��ȡHibernate��Ŷ�����ļ�
		Configuration configuration = new Configuration().configure();
		
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		//�����Ự����
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		//�����Ự����
		session = sessionFactory.openSession();
		//��������
		transaction = session.beginTransaction();
		
	}
	
	/*���Է�����ִ��*/
	@After
	public void destory() {
		//�����ύ
		transaction.commit();
		//�رջỰ 
		session.close();
		//�رչ���
		sessionFactory.close();
	}
	
	/*������ϵ��Project����ά��,ͬʱ����Projectʱ����м�������*/
	@Test
	public void add(){
		Project project1 = new Project(1, "��Ŀһ");
		Project project2 = new Project(2, "��Ŀ��");
		Employee employee1 = new Employee(1, "����");
		Employee employee2 = new Employee(2, "����");
		
		//�μ���Ŀһ��������������
		project1.getEmployees().add(employee1);
		project1.getEmployees().add(employee2);
		//�μ���Ŀ����ֻ������
		project2.getEmployees().add(employee2);
		//������Ŀ,��������Ա��
		session.save(project1);	
		session.save(project2);
	}
	
}
