package service;

import bean.Department;
import bean.Employee;
import bean.PageBean;

/**Ա��ҵ����*/
public interface EmployeeService {

	public Employee login(Employee employee);

	public PageBean<Employee> findByPage(Integer currentPage);

	public void save(Employee employee);

	public Employee findById(Integer eid);

	public void update(Employee employee);

	public void delete(Integer eid);

}
