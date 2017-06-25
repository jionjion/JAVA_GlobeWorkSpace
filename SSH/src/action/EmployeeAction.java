package action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import bean.Department;
import bean.Employee;
import bean.PageBean;
import service.DepartmentService;
import service.EmployeeService;

/***
 *	Ա���Ĺ�����
 */
public class EmployeeAction extends ActionSupport implements ModelDriven<Employee>{

	
	
	//ģ������ʹ�õĶ���
	private  Employee employee = new Employee(); 
	@Override
	public Employee getModel() {
		return employee;
	}
	
	private EmployeeService employeeService;
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	private DepartmentService departmentService;
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	/**��¼ִ�еķ���*/
	public String login() {
		
		Employee existEmployee = employeeService.login(employee);
		
		if (existEmployee == null) {
			//��¼ʧ��
			this.addActionError("�û��������������");
			return this.INPUT;
		}else{
			//��¼�ɹ�
			ActionContext.getContext().getSession().put("existEmployee", existEmployee);
			return this.SUCCESS;
		}
	}
	
	//��ǰҳ��
	private Integer currentPage = 1;
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
	/**���ݵ�ǰҳ���ж�������,��Bean����ֵջ��,ֻ��ֱ�ӵ�������������*/
	public String findAll() {
		
		PageBean<Employee> pageBean = employeeService.findByPage(currentPage);
		//������뵽ֵջ��
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	/**��ת��������Ա�����б�**/
	public String savePage() {
		
		List<Department> list = departmentService.findAll();
		//����ֵջ,������set,������put
		ActionContext.getContext().getValueStack().set("list", list);
		return "savePage";
	}
	
	/**�����Ա��**/
	public String save() {
		//��ʹ�ü�������,����URL�ж��������Ե��޸�
		employeeService.save(employee);
		return "save";
	}
	
	/**�޸�Ա��,������.Ĭ��ģ��������ֱ����ֵջ����ʾ,��ǰ̨ʹ��model.���Խ��л�ȡ*/
	public String edit(){
		
		List<Department> list = departmentService.findAll();
		//����ֵջ,������set,������put
		ActionContext.getContext().getValueStack().set("list", list);
		//ʹ��ģ������,�����ѯҪ�޸ĵĶ���
		employee = employeeService.findById(employee.getEid());
		return "edit";
	}
	
	/**�޸�Ա��**/
	public String update() {
		employeeService.update(employee);
		return "update";
	}
	
	/**ɾ��Ա��*/
	public String delete() {
		employeeService.delete(employee.getEid());
		return "delete";
	}
}
