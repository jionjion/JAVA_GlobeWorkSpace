package interceptor;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import bean.User;

public class PrivilegeManagementAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 1L;

	private User user;
	
	private Map<String, Object> session;	//session��ά������,ʵ��SessionAware�ӿڻ��
	
	
	@Override
	public String execute() throws Exception {
		System.out.println("�����̨");
		
		if("admin".equals(user.getUsername().trim()) && "123456".equals(user.getPassword().trim())){
			session.put("userInfo", user);
			System.out.println("�����û�:"+user.toString());
			return SUCCESS;
		}else{
			session.put("loginError", "�û����������!");
			return ERROR;
		}
	}
	
	
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
