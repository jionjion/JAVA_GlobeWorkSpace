package interceptor;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
/**
 * 	Ȩ�޵�¼����������
 */
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class PrivilegeManagementInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		
		//��ȡ������
		ActionContext actionContext = ActionContext.getContext();
		//��ȡ��װ��session��map�е��û���Ϣ
		Map<String, Object> session = actionContext.getSession();
		if (session.get("userInfo") != null ) {
			String result =actionInvocation.invoke();
			return result;	//�û��ѵ�¼
		}else{
			return "login";	//�û�δ��¼,��Ҫ��¼
		}
	}
}
