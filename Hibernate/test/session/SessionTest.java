package session;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

import bean.Student;

/**
 *	��session����Ļ�ȡ���в���
 */
public class SessionTest {
	
	
	@Test
	public void testOpenSession(){
	
	//�������ö���
	Configuration configuration = new Configuration().configure();
	//��������ע�����
	ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
	//�����Ự����
	SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	//�����Ự���󣬵�һ�ַ�ʽ��
	Session session = sessionFactory.openSession();
	if(session != null){
		System.out.println("Session�����ɹ�");
		Session session2 = sessionFactory.openSession();
		System.out.println("session�Ƿ���ͬ"+(session==session2));
	}else{
		System.err.println("Session����ʧ��");
	}
	
	
	//��������
 	Transaction transaction = session.beginTransaction();
	
	}
	
	@Test
	public void testGetCurrentSession(){
		//�������ö���
		Configuration configuration = new Configuration().configure();
		//��������ע�����
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		//�����Ự����
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		//�����Ự���󣬵ڶ��ַ�ʽ��
		Session session = sessionFactory.getCurrentSession();
		if(session != null){
			System.out.println("Session�����ɹ�");
			Session session2 = sessionFactory.getCurrentSession();
			System.out.println("session�Ƿ���ͬ"+(session==session2));
		}else{
			System.err.println("Session����ʧ��");
		}
	}
	
	@Test
	public void testSaveStudentWithOpenSession(){
		//�������ö���
		Configuration configuration = new Configuration().configure();
		//��������ע�����
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		//�����Ự����
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		//�����Ự���󣬵�һ�ַ�ʽ��
		Session session1 = sessionFactory.openSession();
		//��������
		Transaction transaction1 = session1.beginTransaction();
		//��������
		Student student = new Student(1, "����", "Ů", new Date(), "�Ϻ�");
		session1.doWork(new Work() {
			
			@Override
			public void execute(Connection connection) throws SQLException {
				System.out.println("���ݿ����ӵ�Hash��:"+connection.hashCode());
			}
		});
		//����,���ǲ��ر�����
		session1.save(student);
		//session1.close();
		//�ύ����
		transaction1.commit();
		
		
		//�����Ự���󣬵�һ�ַ�ʽ��
		Session session2 = sessionFactory.openSession();
		//��������
		Transaction transaction2 = session2.beginTransaction();
		//��������
		Student student2 = new Student(2, "����", "��", new Date(), "�Ϻ�");
		session2.doWork(new Work() {
			
			@Override
			public void execute(Connection connection) throws SQLException {
				System.out.println("���ݿ����ӵ�Hash��:"+connection.hashCode());
			}
		});
		//����,���ǲ��ر�����
		session2.save(student2);
		//session2.close();
		//�ύ����
		transaction2.commit();
		
	}
	
	
	@Test
	public void testSaveStudentWithGetCurrentSession(){
		//�������ö���
		Configuration configuration = new Configuration().configure();
		//��������ע�����
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		//�����Ự����
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		//�����Ự���󣬵�һ�ַ�ʽ��
		Session session1 = sessionFactory.getCurrentSession();
		//��������
		Transaction transaction1 = session1.beginTransaction();
		//��������
		Student student = new Student(1, "����", "Ů", new Date(), "�Ϻ�");
		session1.doWork(new Work() {
			
			@Override
			public void execute(Connection connection) throws SQLException {
				System.out.println("���ݿ����ӵ�Hash��:"+connection.hashCode());
			}
		});
		//����,���ǲ��ر�����
		session1.save(student);
		//session1.close();
		//�ύ����
		transaction1.commit();
		
		//�����Ự���󣬵�һ�ַ�ʽ��
		Session session2 = sessionFactory.getCurrentSession();
		//��������
		Transaction transaction2 = session2.beginTransaction();
		//��������
		Student student2 = new Student(2, "����", "��", new Date(), "�Ϻ�");
		session2.doWork(new Work() {
			
			@Override
			public void execute(Connection connection) throws SQLException {
				System.out.println("���ݿ����ӵ�Hash��:"+connection.hashCode());
			}
		});
		//����,���ǲ��ر�����
		session2.save(student2);
		//session2.close();
		//�ύ����
		transaction2.commit();
	}
}

