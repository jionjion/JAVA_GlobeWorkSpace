package action;

/**
 * 	ͨ��Action�Ķ�����н���ǰ̨
 * 
 * 	�������֤ʧ��,���߶������Ͳ�ƥ��,�ض��򵽵�¼����
 * 	��֤�����ַ�ʽ:
 * 		1.��ҵ���߼�����֤,����INPUT����ACCESS
 * 		2.�̳з���,��validate()����֤
 */
import com.opensymphony.xwork2.ActionSupport;
import bean.User;

public class LoginAction2 extends ActionSupport {
//�̳к���Ե��ó������ؽ��
	
	private User user;
	
	/**URL:http://localhost:8080/Struts/jsp/login2.jsp */
	public String login() {
		System.out.println("���ǰ̨�������:");
		System.out.println("�û�:"+user.getUsername());
		System.out.println("����:"+user.getPassword());
		System.out.println("����:"+user.getAge());
		
//		//����֤����
//		if(user.getUsername() == null || "".equals(user.getUsername().trim())){
//			this.addFieldError("username", "�û���Ϊ��");
//			return INPUT;
//		}
		
		return SUCCESS;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	/**����֤����,���ʧ��,����ת�ص�¼����*/
	@Override
	public void validate() {
		super.validate();
		if(user.getUsername() == null || "".equals(user.getUsername().trim())){
			this.addFieldError("username", "�û���Ϊ��");
		}
	}

	
	
	

}
