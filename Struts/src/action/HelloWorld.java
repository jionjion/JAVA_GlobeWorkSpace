package action;

import com.opensymphony.xwork2.ActionSupport;

public class HelloWorld extends ActionSupport {

	/**Ĭ�ϵķ���,Ĭ�ϱ���ִ��*/
	@Override
	public String execute() throws Exception {
		
		System.out.println("ִ�в���");
		return SUCCESS;		//���سɹ��Ľ���
	}
	
	
	/**�����ķ���*/
	public String add() {
		System.out.println("ִ����������");
		return SUCCESS;
	}
	
	/**�޸ĵķ���*/
	public String update() {
		System.out.println("ִ���޸ķ���");
		return SUCCESS;
	}
}
