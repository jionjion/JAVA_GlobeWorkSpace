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
 *	ѧ����Ĳ���
 */
public class PupilTest {

	private  SessionFactory sessionFactory;
	private  Session session;
	private  Transaction transaction;
	/*
	 * 	���Է���ǰ
	 */
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
	/*
	 * 	���Է�����ִ��
	 */
	@After
	public void destory() {
		//�����ύ
		transaction.commit();
		//�رջỰ 
		session.close();
		//�رչ���
		sessionFactory.close();
	}
	
	/*���Զ��һ������*/
	@Test
	public void addPupil(){
		//�����༶
		Grade grade = new Grade();
		grade.setGid(3);
		grade.setGname("����");
		grade.setGdescribe("������");
		//����ѧ��
		Pupil pupil1 = new Pupil(3, "����", "��", 3);
		Pupil pupil2 = new Pupil(4, "����", "��", 3);
		//���ù�����ϵ
		pupil1.setGrade(grade);
		pupil2.setGrade(grade);
		//����
		session.save(grade);
		session.save(pupil1);
		session.save(pupil2);
	}
	
	/*����һ/���໥��ϵ.*/
	@Test
	public void addPupilByReciprocal(){
		//�����༶
		Grade grade = new Grade();
		grade.setGid(4);
		grade.setGname("����");
		grade.setGdescribe("�ù�����");
		//����ѧ��
		Pupil pupil1 = new Pupil(5, "����", "��", 3);
		Pupil pupil2 = new Pupil(6, "����", "��", 3);
		//���ù�����ϵ,�໥��ϵ
		grade.getPupils().add(pupil1);
		grade.getPupils().add(pupil2);
		pupil1.setGrade(grade);
		pupil2.setGrade(grade);
		//����
		session.save(grade);
//		session.save(pupil1);
//		session.save(pupil2);
	}	
	
	/*��ѯѧ�����ڰ༶����Ϣ*/
	@Test
	public void findGradeByPupil(){
		Pupil pupil = (Pupil) session.get(Pupil.class, 5);
		Grade grade = pupil.getGrade();
		System.out.println("ѧ����Ϣ:"+pupil.toString());
		System.out.println("�༶��Ϣ"+grade.toString());
	}
}
