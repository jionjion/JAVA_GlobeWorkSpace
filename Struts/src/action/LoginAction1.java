package action;
/**
 * 	ͨ��Action�����Բ������н���ǰ̨
 */
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction1 extends ActionSupport {
//�̳к���Ե��ó������ؽ��
	
	private String username;
	private String password;
	
	/**URL:http://localhost:8080/Struts/jsp/login1.jsp */
	public String login() {
		System.out.println("���ǰ̨�������");
		System.out.println("�û�:"+username);
		System.out.println("����:"+password);
		return SUCCESS;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
