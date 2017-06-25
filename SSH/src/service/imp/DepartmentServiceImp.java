package service.imp;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import bean.Department;
import bean.PageBean;
import dao.DepartmentDao;
import service.DepartmentService;

@Transactional
public class DepartmentServiceImp implements DepartmentService {

	private DepartmentDao departmentDao;
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	
	@Override
	public PageBean<Department> findByPage(Integer currentPage) {
		
		PageBean<Department> pageBean = new PageBean<Department>();
		//��װÿҳ��ʾ��¼����
		int pageSize = 3;
		pageBean.setPageSize(pageSize);
		//��װ�ܼ�¼��
		int totalCount = departmentDao.findCount();
		pageBean.setTotalCount(totalCount);
		//��װ��ҳ��
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		//��װ��ǰҳ��
		pageBean.setCurrentPage(currentPage);
		//��װÿҳ��ʾ������
		int begin = (currentPage -1) * pageSize;	//MySQL��ʼ��ѯ��λ��
		List<Department> list = departmentDao.findByPage(begin,pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	public void save(Department department) {

		departmentDao.save(department);
	}

	@Override
	public Department findById(Integer did) {
		return departmentDao.findById(did);
	}

	@Override
	public void update(Department department) {
		departmentDao.update(department);
	}

	@Override
	public void delete(Integer did) {
		departmentDao.delete(did);
	}

	@Override
	public List<Department> findAll() {
		return departmentDao.findAll();
	}
	
	
}
