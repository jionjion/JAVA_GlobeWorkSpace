package dao.imp;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
/**�־û��࣬�Զ�ע��SessionFactory*/
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bean.Department;
import bean.Employee;
import dao.EmployeeDao;

public class EmployeeDaoImp extends HibernateDaoSupport implements EmployeeDao {

	/**�����û����������ѯ,�����ص�һ�����*/
	@Override
	public Employee findByUser(Employee employee) {
		
		String hql = "from Employee where ename = ? and epassword = ? ";
		//���������HQL���в�ѯ
		List<Employee> employees = this.getHibernateTemplate().find(hql,employee.getEname(),employee.getEpassword());
		if(employees.size()>0){
			return employees.get(0);
		}
		return null; 
	}

	@Override
	public int findCount() {
		String hql = "select count(*) from Employee";
		List<Long> list = this.getHibernateTemplate().find(hql);
		//���������
		if(list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}

	@Override
	public List<Employee> findByPage(int begin, int pageSize) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
		//MySQL�ķ�ҳ��ѯ���
		List<Employee> list = this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
		return list;
	}

	@Override
	public void save(Employee employee) {
		this.getHibernateTemplate().save(employee);
	}

	@Override
	public Employee findById(Integer eid) {
		return this.getHibernateTemplate().get(Employee.class, eid);
	}

	@Override
	public void update(Employee employee) {
		this.getHibernateTemplate().update(employee);
	}

	@Override
	public void delete(Employee employee) {
	
		this.getHibernateTemplate().delete(employee);
	}

}
