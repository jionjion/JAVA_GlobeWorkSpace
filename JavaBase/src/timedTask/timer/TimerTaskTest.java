package timedTask.timer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimerTask;
/**�Զ��嶨ʱ����*/
public class TimerTaskTest extends TimerTask{

	/**��װ����*/
	private String name;
	public  String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	/**ִ�м���*/
	public int count = 0;
	/**�Զ����ӡ����*/
	public void printTask() {
		if (name != null) {
			if (count <= 3) {
				System.out.println("����ִ��.....����:"+name);
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd : hh:mm:ss");
				System.out.println("�´�ִ��ʱ��:"
							+ simpleDateFormat.format(scheduledExecutionTime()));
				count ++;
			}else{
				cancel();		//ȡ��ִ��
				System.out.println("����ȡ��");
				scheduledExecutionTime();
			}
		}
	}

	@Override
	/**����ִ�з���*/
	public void run() {
		//�����������
		printTask();
	}
	
	
}
