package action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 	ͨ��ʹ��Action�Ĳ�ͬ����ֵ,���з�����ִ�к�ҳ�����ת
 */
public class WildcardAction extends ActionSupport {

	
	
	@Override
	public String execute() throws Exception {
		return super.execute();
	}

	/**�����ķ��� */
	public String add() {
		System.out.println("ִ����������");
		return "add";
	}
	
	/**�޸ĵķ���*/
	public String update() {
		System.out.println("ִ���޸ķ���");
		return "update";
	}
}
