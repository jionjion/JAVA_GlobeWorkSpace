package cache;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import bean.Pupil;
import bean.Student;

public class CacheTest {

	//�Ự����
	private SessionFactory sessionFactory;
	//�Ự����
	private Session session;
	//�������
	private Transaction transaction;
	
	/*���Է���ǰִ��*/
	public void init() {
		//�������ö���
		Configuration configuration = new Configuration().configure();
		//��������ע�����
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		//�����Ự����
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		//�����Ự����
		session = sessionFactory.openSession();
		//��������
		transaction = session.beginTransaction();
	}
	
	/*���Է�����ִ��*/
	public void destory() {
		//�����ύ
		transaction.commit();
		//�رջỰ 
		session.close();
		//�رչ���
		sessionFactory.close();
	}
	
	/*�ڶ���ͬһ��session��ѯͬһ������ʱ,�������ٴ�ִ�����ݿ�Ĳ�ѯ,����ʹ�û���;�ڻ�����ÿһ������Ψһ*/
	@Test
	public void testDoubleQueryBySame(){
		init();		//��ʼ��
		Student student1 =(Student) session.get(Student.class,1);
		Student student2 =(Student) session.get(Student.class,1);
		System.out.println("��ѯ��ѧ��:"+student1.toString());
		System.out.println("��ѯ��ѧ��:"+student2.toString());
		destory();	//����
	}
	
	/*�ڶ��β�ѯͬһ����,ʹ�ò�ͬ��session,��û��ʹ�û���*/
	@Test
	public void testDoubleQueryByDifferent(){
		init();		//��ʼ��
		Student student1 =(Student) session.get(Student.class,1);
		System.out.println("��ѯ��ѧ��:"+student1.toString());
		destory();	//����
		init();		//��ʼ��
		Student student2 =(Student) session.get(Student.class,1);
		System.out.println("��ѯ��ѧ��:"+student2.toString());
		destory();	//����
	}
	
	/*�ڶ��β�ѯ����,ʹ����ͬ��session,�Ƿ�ʹ�û���ȡ���ڲ�ѯ��ʽ*/
	@Test
	public void testDoubleQueryListBySame(){
		init();		//��ʼ��
		Query query = session.createQuery("from Student");
		//��һ�β�ѯ
		List<Student> students = query.list();
		for (Student student : students) {
			System.out.println("��ѯ��ѧ��:"+student.toString());
		}
		//�ڶ��β�ѯ,��û��ʵ�ֻ���,�ٴβ�ѯ���ݿ�
		students = query.list();
		for (Student student : students) {
			System.out.println("��ѯ��ѧ��:"+student.toString());
		}		
		
		//ʹ�õ�����,�Ի�����в�ѯƥ��,�����������û���,�����ѯ���ݿ�
		Iterator<Student> iterator = query.iterate();
		while (iterator.hasNext()) {
			Student student = (Student) iterator.next();
			System.out.println("��ѯ��ѧ��:"+student.toString());
		}		
		destory();	//����
	}
	
	/*���һ������Ĳ���*/
	@Test
	public void testFirstLevelCache(){
		init();		//��ʼ��
		Student student1 =(Student) session.get(Student.class,1);
		System.out.println("��ѯ��ѧ��:"+student1.toString());
		//���һ����������ָ������
		session.evict(student1);
		//���һ������������������
		session.clear();
		Student student2 =(Student) session.get(Student.class,1);
		System.out.println("��ѯ��ѧ��:"+student2.toString());
		destory();	//����
	}
	
	/*��Զ�������Ĳ���,ʹ�ò�ͬ��sessionFactory,���ֲ���ʹ�ö�������*/
	@Test
	public void testTwoLevelCacheByDifferentFactory(){
		init();		//��ʼ��
		Pupil pupil1 =(Pupil) session.get(Pupil.class,5);
		System.out.println("��ѯ��ѧ��:"+pupil1.toString());
		destory();	//����
		init();		//��ʼ��
		Pupil pupil2 =(Pupil) session.get(Pupil.class,5);
		System.out.println("��ѯ��ѧ��:"+pupil2.toString());
		destory();	//����
	}
	
	/*��Զ�������Ĳ���,ʹ����ͬ��sessionFactory,����ʹ�ö�������*/
	@Test
	public void testTwoLevelCacheBySameFactory(){

		//�������ö���
		Configuration configuration = new Configuration().configure();
		//��������ע�����
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		//�����Ự����
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		//�����Ự����
		Session session = sessionFactory.openSession();
		//��������
		Transaction transaction = session.beginTransaction();
		//��ѯ
		Pupil pupil1 =(Pupil) session.get(Pupil.class,5);
		System.out.println("��ѯ��ѧ��:"+pupil1.toString());
		//�ύ����
		transaction.commit();
		//�ر�session,���ǲ��رչ���
		session.close();
		
		//�ٴβ�ѯ
		//�����Ự����
		session = sessionFactory.openSession();
		//��������
		transaction = session.beginTransaction();
		//��ѯ
		pupil1 =(Pupil) session.get(Pupil.class,5);
		System.out.println("��ѯ��ѧ��:"+pupil1.toString());
		//�ύ����
		transaction.commit();
		//�ر�session,sessionFactory
		session.close();
		sessionFactory.close();
	}
}
