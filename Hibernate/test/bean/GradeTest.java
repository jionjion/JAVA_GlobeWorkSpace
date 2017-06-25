package bean;


import java.util.HashSet;
import java.util.Set;

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
 *	�༶��Ĳ���
 */
public class GradeTest {

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
	
	/*����ѧ��*/
	@Test
	public void	 addPupil() {
		//�����༶
		Grade grade = new Grade();
		grade.setGid(1);
		grade.setGname("��һ");
		grade.setGdescribe("������С����");
		
		//����ѧ��
		Pupil pupil1 = new Pupil(1,"����","��",1);
		Pupil pupil2 = new Pupil(2,"����","��",1);
		Set<Pupil> pupils = new HashSet<Pupil>();
		pupils.add(pupil1);
		pupils.add(pupil2);
		
		//���ѧ����
		grade.setPupils(pupils);
		
		//����
		session.save(grade);
		session.save(pupil1);
		session.save(pupil2);
	}
	
	
	/*��ѯѧ������Ϣ,ͨ���෽��ѯ��������Ϣ,���е�����ѯ*/
	@Test
	public void findPupilByGrade() {
		Grade grade = (Grade)session.get(Grade.class, 1);
		System.out.println("��ѯ���༶"+grade.toString());
		Set<Pupil> pupils = grade.getPupils();
		for (Pupil pupil : pupils) {
			System.out.println("ÿ��ѧ����Ϣ"+pupil.toString());
		}
	}
	
	/*�޸�ѧ�����һ����,��Ϊ�����꼶*/
	@Test
	public void updatePupil(){
		//�������꼶
		Grade grade = new Grade();
		grade.setGid(2);
		grade.setGname("���");
		grade.setGdescribe("�����������");
		//���ѧ��
		Pupil pupil = (Pupil) session.get(Pupil.class, 1);
		//�޸��꼶
		pupil.setGid(2);
		//����ѧ�������꼶
		grade.getPupils().add(pupil);
		//��������ʹӱ�
		session.save(grade);
		session.save(pupil);
	}
	
	/*ɾ��ѧ�������Ϣ,ɾ���ӱ�*/
	@Test
	public void deletePupil(){
		Pupil pupil = (Pupil) session.get(Pupil.class, 2);
		session.delete(pupil);
	}
}
