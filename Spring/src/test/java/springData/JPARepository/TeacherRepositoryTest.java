package springData.JPARepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import base.UnitTestBase;
import springData.bean.Teacher;

/**
 *	使用SpringTemplate模板方法访问数据库
 *	Spring-Data框架
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class TeacherRepositoryTest extends UnitTestBase {
	
	
	private TeacherRepository teacherRepository;
	
	public TeacherRepositoryTest() {
		super("classpath:spring-data.xml");
	}

	/**
	 * 	在测试方法执行前,初始化
	 */
	@Before
	public void init() {
		//避免重复创建加载
		if (this.teacherRepository == null) {
			this.teacherRepository = super.getBean(TeacherRepository.class);
		}
	}
	
	@Test
	public void testFindByName() {
		try {
			Teacher teacher = teacherRepository.findByName("JionJion");
			System.out.println(teacher);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindByNameStartsWithAndAgeLessThan() {
		try {
			List<Teacher> teachers = teacherRepository.findByNameStartsWithAndAgeLessThan("Jion", 30);
			System.out.println(teachers);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindByNameEndingWithAndAgeGreaterThanEqual() {
		try {
			List<Teacher> teachers = teacherRepository.findByNameEndingWithAndAgeGreaterThanEqual("Jion", 23);
			System.out.println(teachers);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindByNameInOrAddressNotIn() {
		try {
			List<String> names = new ArrayList<String>();
			names.add("JionJion");
			names.add("Arise");
			List<String> addresses = new ArrayList<String>();
			addresses.add("鹤壁");
			addresses.add("信阳");
			List<Teacher> teachers = teacherRepository.findByNameInOrAddressNotIn(names,addresses);
			System.out.println(teachers);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	@Test
	public void testfindByWorkdayBetween() {
		try {
			List<Teacher> teachers = teacherRepository.findByWorkdayBetween(
					new Date(new GregorianCalendar(2017, 10, 15).getTimeInMillis()),
					new Date(new GregorianCalendar(2017, 10, 22).getTimeInMillis()));
			System.out.println(teachers);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindByIdNotNullAndNameIsNotIn() {
		try {
			List<Teacher> teachers = teacherRepository.findByIdNotNullAndNameIsNull();
			System.out.println(teachers);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}