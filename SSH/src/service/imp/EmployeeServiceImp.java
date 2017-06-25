package service.imp;

import java.util.List;

import bean.Department;
import bean.Employee;
import bean.PageBean;
import dao.EmployeeDao;
import service.EmployeeService;

public class EmployeeServiceImp implements EmployeeService {

	private EmployeeDao employeeDao;
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	@Override
	public Employee login(Employee employee) {

		return employeeDao.findByUser(employee);
	}
	@Override
	public PageBean<Employee> findByPage(Integer currentPage) {
		
		PageBean<Employee> pageBean = new PageBean<Employee>();
		//��װÿҳ��ʾ��¼����
		int pageSize = 3;
		pageBean.setPageSize(pageSize);
		//��װ�ܼ�¼��
		int totalCount = employeeDao.findCount();
		pageBean.setTotalCount(totalCount);
		//��װ��ҳ��
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		//��װ��ǰҳ��
		pageBean.setCurrentPage(currentPage);
		//��װÿҳ��ʾ������
		int begin = (currentPage -1) * pageSize;	//MySQL��ʼ��ѯ��λ��
		List<Employee> list = employeeDao.findByPage(begin,pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}
	@Override
	public void save(Employee employee) {
		employeeDao.save(employee);
	}
	@Override
	public Employee findById(Integer eid) {
		return employeeDao.findById(eid);
	}
	@Override
	public void update(Employee employee) {
		employeeDao.update(employee);
	}
	@Override
	public void delete(Integer eid) {
		Employee employee = employeeDao.findById(eid);
		employeeDao.delete(employee);
	}
	
}
