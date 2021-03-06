package annotationBean;


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
 *	多对一,一方的测试
 */
public class AGradeTest {

	private  SessionFactory sessionFactory;
	private  Session session;
	private  Transaction transaction;
	/*
	 * 	测试方法前
	 */
	@Before
	public void init(){
		//创建Configuration对象,读取Hibernate的哦配置文件
		Configuration configuration = new Configuration().configure();
		
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		//创建会话工厂
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		//创建会话对象
		session = sessionFactory.openSession();
		//开启事物
		transaction = session.beginTransaction();
		
	}

	
	/*
	 * 	测试方法后执行
	 */
	@After
	public void destory() {
		//事物提交
		transaction.commit();
		//关闭会话 
		session.close();
		//关闭工厂
		sessionFactory.close();
	}
	
	/*增加学生*/
	@Test
	public void	 addAPupil() {
		//创建班级
		AGrade grade = new AGrade();
		grade.setGid(1);
		grade.setGname("大一");
		grade.setGdescribe("新来的小鲜肉");
		
		//创建学生
		APupil pupil1 = new APupil(1,"张三","男");
		APupil pupil2 = new APupil(2,"李四","男");
		Set<APupil> pupils = new HashSet<APupil>();
		pupils.add(pupil1);				
		pupils.add(pupil2);
		
		//添加学生们
		grade.setPupils(pupils);
		
		//保存
		session.save(pupil1);
		session.save(pupil2);
		session.save(grade);							
	}
	
	
	/*查询学生的信息,通过多方查询单方的信息,进行导航查询*/
	@Test
	public void findPupilByGrade() {
		AGrade grade = (AGrade)session.get(AGrade.class, 1);
		System.out.println("查询到班级"+grade.toString());
		Set<APupil> pupils = grade.getPupils();
		for (APupil pupil : pupils) {
			System.out.println("每个学生信息"+pupil.toString());
		}
	}
	
	/*修改学生表的一个人,变为其他年级*/
	@Test
	public void updatePupil(){
		//创建二年级
		AGrade grade = new AGrade();
		grade.setGid(2);
		grade.setGname("大二");
		grade.setGdescribe("变成老油条了");
		//获得学生
		APupil pupil = (APupil) session.get(APupil.class, 1);
		//增加学生到二年级
		grade.getPupils().add(pupil);
		//保存主表和从表
		session.save(grade);
		session.save(pupil);
	}
	
	/*删除学生表的信息,删除从表*/
	@Test
	public void deletePupil(){
		APupil pupil = (APupil) session.get(APupil.class, 2);
		session.delete(pupil);
	}
}
