package action;

/**
 * 	ͨ��ModelDriven�ӿ�ʵ��
 * 	����Ҫget/set����,������Ҫʵ����
 */
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import bean.User;

public class LoginAction3 extends ActionSupport implements ModelDriven<User>{
//�̳к���Ե��ó������ؽ��
	
	private User user = new User();
	
	@Override
	public User getModel() {
		return user;
	}
	
	/**URL:http://localhost:8080/Struts/jsp/login3.jsp */
	public String login() {
		System.out.println("���ǰ̨�������:");
		System.out.println("�û�:"+user.getUsername());
		System.out.println("����:"+user.getPassword());
		System.out.println("��һ:"+user.getBooks().get(0));
		System.out.println("���:"+user.getBooks().get(1));
		System.out.println("��һ:"+user.getUsers().get(0).getUsername());
		System.out.println("�˶�:"+user.getUsers().get(1).getUsername());
		return SUCCESS;
	}


}
