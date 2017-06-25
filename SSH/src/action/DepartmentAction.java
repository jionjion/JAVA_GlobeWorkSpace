package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import bean.Department;
import bean.PageBean;
import service.DepartmentService;

public class DepartmentAction extends ActionSupport implements ModelDriven<Department>{

	//ģ������
	private Department department = new Department();
	@Override
	public Department getModel() {
		return department;
	}

	//����ע��
	private DepartmentService departmentService;
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	//��ǰҳ��
	private Integer currentPage = 1;
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}


	/**���ݵ�ǰҳ���ж�������,��Bean����ֵջ��,ֻ��ֱ�ӵ�������������*/
	public String findAll() {
		
		PageBean<Department> pageBean = departmentService.findByPage(currentPage);
		//������뵽ֵջ��
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	/**����²���**/
	public String savePage() {
		
		return "savePage";
	}
	
	/**����²���**/
	public String save() {
		departmentService.save(department);
		return "save";
	}
	
	/**�޸�Ա��,������.Ĭ��ģ��������ֱ����ֵջ����ʾ,��ǰ̨ʹ��model.���Խ��л�ȡ*/
	public String edit(){
		
		department = departmentService.findById(department.getDid());
		return "edit";
	}
	
	/**�޸�Ա��**/
	public String update() {
		departmentService.update(department);
		return "update";
	}
	
	/**ɾ��Ա��*/
	public String delete() {
		departmentService.delete(department.getDid());
		return "delete";
	}
}
