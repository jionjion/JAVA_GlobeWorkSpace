package hql;

import java.util.List;
import java.util.Map;

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

import bean.Grade;
import bean.Student;
@SuppressWarnings({"unchecked","rawtypes"})
public class StudentHQLTest {

	//�Ự����
	private SessionFactory sessionFactory;
	//�Ự����
	private Session session;
	//�������
	private Transaction transaction;
	
	/*���Է���ǰִ��*/
	@Before
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
	@After
	public void destory() {
		//�����ύ
		transaction.commit();
		//�رջỰ 
		session.close();
		//�رչ���
		sessionFactory.close();
	}
	
	/*��ѯ���ж���*/
	@Test
	public void testFromStudent() {
		//����Ҫָ��ȫ����,ֱ����������;ȱʡ��Hibernate��ܵ�ӳ�䲹ȫ
		String hql = "from Student as s";			//����ʹ�ñ���
		Query query = session.createQuery(hql);		//��ѯ�����װ��Query��
		List<Student> students = query.list();		//��֪��ѯ���ΪList
		for (Student student : students) {
			System.out.println("��ѯ��ѧ��:"+student.toString());
		}
	}
	
	/*��ѯһ�����ж���,�����෽����,����Ĭ�ϲ���ѯ,���ǹر�������;������Ϊ�õ�,���Զ���ѯ*/
	@Test
	public void testFromGrade() {
		String hql = "from bean.Grade";		//����ʹ��ȫ����
		Query query = session.createQuery(hql);		//��ѯ�����װ��Query��
		List<Grade> grades = query.list();		//��֪��ѯ���ΪList
		for (Grade grade : grades) {
			System.out.println("��ѯ��ѧ��:"+grade.toString());
		}
	}
	
	/*�Զ���������ʽ����һ��List*/
	@Test
	public void testSelectClauseObjectArray(){
		String hql = "select s.sid,s.sname from Student s";		//ʡ��asʹ�ñ���
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		for (Object[] objects : list) {
			System.out.println("ѧ�����:"+objects[0]+"\t"+"ѧ������:"+objects[1]);
		}
	}
	
	/*�Զ�����ʽ����һ��List	����ѯ��Ϊһ������ʱ,���ص�Ϊ����*/
	@Test
	public void testSelectClauseObject(){
		String hql = "select s.sname from Student s";		//ʡ��asʹ�ñ���
		Query query = session.createQuery(hql);
		List<Object> list = query.list();
		for (Object object : list) {
			System.out.println("ѧ������:"+object);
		}
	}
	
	/*��List������ʽ����һ��List*/
	@Test
	public void testSelectClauseList() {
		String hql = "select new list(s.sid,s.sname) from Student s";
		Query query = session.createQuery(hql);
		List<List> lists = query.list();
		for (List list : lists) {
			System.out.println("ѧ�����:"+list.get(0)+"\t"+"ѧ������:"+list.get(1));
		}
	}
	
	/*��Map������ʽ����һ��List*/
	@Test
	public void testSelectClauseMap() {
		String hql = "select new map(s.sid as id,s.sname) from Student s";
		Query query = session.createQuery(hql);
		List<Map> lists = query.list();
		for (Map map : lists) {		//����ÿһ����װ��map����
			//����ͨ������������Ż�ȡMap�ķ�װ����,ע�⴫�����һ��String���͵�
			System.out.println("ѧ�����:"+map.get("id")+"\t"+"ѧ������:"+map.get("1"));	
		}
	}
	
	/*���Զ������ͷ���һ��List;��Ҫ�ڳ־û����д�����Ӧ�Ĺ�����,��select�Ӿ��е��ö���Ĺ�����*/
	@Test
	public void testSelectClauseConstructor() {
		String hql = "select new Student(s.sid ,s.sname) from Student s";
		Query query = session.createQuery(hql);
		List<Student> students = query.list();
		for (Student student : students) {
			System.out.println("ѧ�����:"+student.getSid()+"\t"+"ѧ������:"+student.getSname());
		}
	}	
	
	/*��ѯȥ��,ʹ��distinct�ؼ���*/
	@Test
	public void testDistinct(){
		String hql = "select distinct s.gender from Student s";
		Query query = session.createQuery(hql);
		List<Object> objects = query.list();
		for (Object object : objects) {
			System.out.println(object);
		}
	}
	
	/*��ѯ�����Ӿ�,�жϴ�С��ϵ*/
	@Test
	public void testWhereClauseMathLogic(){
		String hql = "from Student s where s.sid<2";
		Query query = session.createQuery(hql);
		List<Student> students = query.list();
		for (Student student : students) {
			System.out.println("��ѯ��ѧ��:"+student.toString());
		}
	}
	
	/*��ѯ�����Ӿ�,�ж��Ƿ�Ϊ��*/
	@Test
	public void testWhereClauseDecideNull(){
		String hql = "from Student s where s.sid is not null";
//		String hql = "from Student s where s.sid <> null";		//Ҳ����
		Query query = session.createQuery(hql);
		List<Student> students = query.list();
		for (Student student : students) {
			System.out.println("��ѯ��ѧ��:"+student.toString());
		}
	}
	
	/*��ѯ�����Ӿ�,��Χ����*/
	@Test
	public void testWhereClauseScope(){
		String hql = "from Student s where s.sid between 1 and 2";	//��ѯ�Ǹ�������
//		String hql = "from Student s where s.sid not between 1 and 2";	
		Query query = session.createQuery(hql);
		List<Student> students = query.list();
		for (Student student : students) {
			System.out.println("��ѯ��ѧ��:"+student.toString());
		}
	}
	
	/*��ѯ�����Ӿ�,������������*/
	@Test
	public void testWhereClauseRange(){
		String hql = "from Student s where s.sid in (1,3,5)";
//		String hql = "from Student s where s.sid not in (1,3,5)";
		Query query = session.createQuery(hql);
		List<Student> students = query.list();
		for (Student student : students) {
			System.out.println("��ѯ��ѧ��"+student.toString());
		}
	}
	
	/*��ѯ�����Ӿ�,ģ����ѯ,��Ҫ�õ����Ŷ�����в���*/
	@Test
	public void testWhereClauseLike(){
//		String hql = "from Student s where s.sname like '��_'";
		String hql = "from Student s where s.sname like '%'";	//ȫ����ѯ
		Query query = session.createQuery(hql);
		List<Student> students = query.list();
		for (Student student : students) {
			System.out.println("��ѯ��ѧ��:"+student.toString());
		}
	}
	
	/*��ѯ�����Ӿ�,�߼���ѯ*/
	@Test
	public void testWhereClauseLogic(){
//		String hql = "from Student s where s.sname like '��_' and s.sid=2";		//�߼���
		String hql = "from Student s where s.sname like '��_' or s.sid=1";		//�߼���
		Query query = session.createQuery(hql);
		List<Student> students = query.list();
		for (Student student : students) {
			System.out.println("��ѯ��ѧ��:"+student.toString());
		}
	}
	
	/*��ѯ�����Ӿ�,��������,��һ�Զ�Ķ෽���Ͻ��в�ѯ�Ƿ�Ϊ��*/
	@Test
	public void testWhereClauseAssemble(){
		String hql = "from Grade g where g.pupils is not empty";		
		Query query = session.createQuery(hql);
		List<Grade> grades = query.list();
		for (Grade grade : grades) {
			System.out.println("��ѯ��ѧ��:"+grade.toString());
		}
	}
	
	/*��ѯ�����Ӿ�,��������*/
	@Test
	public void testWhereClauseArithmetic(){
		String hql = "from Student s where s.sid < (2+1)*3";	
		Query query = session.createQuery(hql);
		List<Student> students = query.list();
		for (Student student : students) {
			System.out.println("��ѯ��ѧ��:"+student.toString());
		}
	}
	
	/*��ѯ�����Ӿ�,������ѯ,Ҫ���ѯ���Ϊ��һ����Ϊ��*/
	@Test
	public void testWhereClauseSingle(){
		String hql = "from Student s where s.sid =1";		//ע�������Ŀ���
		Query query = session.createQuery(hql);
		Student student = (Student) query.uniqueResult();
		System.out.println("��ѯ��ѧ��:"+student.toString());
	}
	
	/*��ѯ�����Ӿ�,�������� desc:����	asc:����*/
	@Test
	public void testWhereClauseOrder(){
		String hql = "from Student s order by s.sid asc,s.birthday desc";	//����������ö��ŷָ�	
		Query query = session.createQuery(hql);
		List<Student> students = query.list();
		for (Student student : students) {
			System.out.println("��ѯ��ѧ��:"+student.toString());
		}
	}
}
