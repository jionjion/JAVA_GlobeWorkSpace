package dao;

import java.util.List;

import bean.Employee;

/**Ա���־û���**/
public interface EmployeeDao {


	public Employee findByUser(Employee employee);

	public int findCount();

	public List<Employee> findByPage(int begin, int pageSize);

	public void save(Employee employee);

	public Employee findById(Integer eid);

	public void update(Employee employee);

	public void delete(Employee employee);

}
