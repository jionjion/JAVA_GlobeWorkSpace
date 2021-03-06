package servletBase.bean;

import java.util.Date;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 *  @author Jion
 *  用户登录信息保存实体类
 */
public class User implements HttpSessionBindingListener{

	private String username;
	
	private String password;
	
	private String email;
	
	private String sex;
	
	private Date birthday;
	
	private String[] hobby;

	private String introduce;	//自我介绍
	
	private boolean flag;	//是否接受协议

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String[] getHobby() {
		return hobby;
	}

	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}


	public User() {
		super();
	}

	public User(String username, String password, String email, String sex, Date birthday, String[] hobby,
			String introduce, boolean flag) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.sex = sex;
		this.birthday = birthday;
		this.hobby = hobby;
		this.introduce = introduce;
		this.flag = flag;
	}

	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		System.out.println("Session绑定该对象");
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		System.out.println("Session解除绑定");
	}
	
	
}
