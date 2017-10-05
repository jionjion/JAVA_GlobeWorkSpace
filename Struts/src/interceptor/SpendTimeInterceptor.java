package interceptor;
/**
 *	����ִ��Actionʱ�����ѵ�ʱ�� 
 *	1.��������������
 *	2.����������
 */
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class SpendTimeInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		
		//ִ��Action֮ǰ��ʱ��
		long start = System.currentTimeMillis();
		//ִ����һ��������,��������һ��,��ִ��Ŀ��Action
		String result = actionInvocation.invoke();
		//ִ��Action֮��
		long end = System.currentTimeMillis();
		System.out.println("ִ�������ѵ�ʱ��:"+(end-start)+"ms");
		//���ؽ����ͼ
		return result;
	}

}
