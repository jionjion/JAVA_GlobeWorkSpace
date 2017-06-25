package timedTask.timer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;


/**���Զ���ʱ��������е���*/
public class TimerTest {
	
	/**�����÷�*/
	public void testOne() {
		//1.��������ʵ��
		Timer timer = new Timer();
		//2.��������ʵ��
		TimerTaskTest task = new TimerTaskTest();
		task.setName("JION.JION");
		//3.ִ�ж�ʱ����:��ǰʱ����е���,���û�������һ��.
		//ִ�������run����,��ǰʱ�����ٺ���,��������ٺ���
		timer.schedule(task, 2000L, 1000L);	
	}
	

	/**�趨ʱ��,�����е���*/
	public void testTwo() {
		Timer timer = new Timer();
		TimerTaskTest task = new TimerTaskTest();
		task.setName("JION.JION");
		//����ʱ�����
		Calendar calendar = Calendar.getInstance();//����ģʽ
		//����ʱ���ʽ���� 
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd : hh:mm:ss");
		//��ӡ��ǰʱ��
		System.out.println("ִ��ʱ��"+simpleDateFormat.format(calendar.getTime()));
		//Ϊ��ǰʱ���5��
		calendar.add(Calendar.SECOND, 5);
		//ִ�������run����,��ǰʱ�����ٺ���,����߳�����
		timer.schedule(task, calendar.getTime());
	}
	
	/**�趨ʱ��,�����е���,ÿ������������*/
	public void testThree() {
		Timer timer = new Timer();
		TimerTaskTest task = new TimerTaskTest();
		task.setName("JION.JION");
		//����ʱ�����
		Calendar calendar = Calendar.getInstance();//����ģʽ
		//����ʱ���ʽ���� 
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd : hh:mm:ss");
		//��ӡ��ǰʱ��
		System.out.println("ִ��ʱ��"+simpleDateFormat.format(calendar.getTime()));
		//Ϊ��ǰʱ���5��
		calendar.add(Calendar.SECOND, 5);
		//ִ�������run����,��ǰʱ�����ٺ���,�����2000����,����ִ��
		timer.schedule(task, calendar.getTime(),2000L);
	}
	
	/**�趨ʱ��,�����е���,ÿ������������*/
	public void testFour() {
		Timer timer = new Timer();
		TimerTaskTest task = new TimerTaskTest();
		task.setName("JION.JION");
		//����ʱ�����
		Calendar calendar = Calendar.getInstance();//����ģʽ
		//����ʱ���ʽ���� 
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd : hh:mm:ss");
		//��ӡ��ǰʱ��
		System.out.println("ִ��ʱ��"+simpleDateFormat.format(calendar.getTime()));
		//ִ�������run����,��ʱ2000����,����ִ��
		timer.schedule(task,2000L);
	}
	
	/**�趨ʱ��,�����е���,ÿ������������*/
	public void testFive() {
		Timer timer = new Timer();
		TimerTaskTest task = new TimerTaskTest();
		task.setName("JION.JION");
		//����ʱ�����
		Calendar calendar = Calendar.getInstance();//����ģʽ
		//����ʱ���ʽ���� 
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd : hh:mm:ss");
		//��ӡ��ǰʱ��
		System.out.println("ִ��ʱ��"+simpleDateFormat.format(calendar.getTime()));
		//ִ�������run����,��ʱ2000����,����ִ��,���ÿ5sִ��
		timer.schedule(task,2000L,5000L);
	}
	
	/**�趨ʱ��,���ָ��ʱ����е���,ÿ������������*/
	public void testSix() {
		Timer timer = new Timer();
		TimerTaskTest task = new TimerTaskTest();
		task.setName("JION.JION");
		//����ʱ�����
		Calendar calendar = Calendar.getInstance();//����ģʽ
		//����ʱ���ʽ���� 
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd : hh:mm:ss");
		//��ӡ��ǰʱ��
		System.out.println("ִ��ʱ��"+simpleDateFormat.format(calendar.getTime()));
		//Ϊ��ǰʱ���5��
		calendar.add(Calendar.SECOND, 5);
		//ִ�������run����,ִ��ʱ��Ϊ��ǰʱ����5s,���ÿ2sִ��
		timer.scheduleAtFixedRate(task, calendar.getTime(),2000L);
	}
	
	/**�趨ʱ��,�����е���,ÿ������������*/
	public void testSeven() {
		Timer timer = new Timer();
		TimerTaskTest task = new TimerTaskTest();
		task.setName("JION.JION");
		//����ʱ�����
		Calendar calendar = Calendar.getInstance();//����ģʽ
		//����ʱ���ʽ���� 
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd : hh:mm:ss");
		//��ӡ��ǰʱ��
		System.out.println("ִ��ʱ��"+simpleDateFormat.format(calendar.getTime()));
		//Ϊ��ǰʱ���5��
		calendar.add(Calendar.SECOND, 5);
		//ִ�������run����,��ʱ5s��ִ��,���ÿ2sִ��
		timer.scheduleAtFixedRate(task, 5000L,2000L);
	}
	
	/**����ִ��ʱ��Ϊ��ǰʱ��֮ǰ.
	 * schedule:ʵ��ִ��ʱ��Ϊ��ǰʱ��,��Ϊ�˸��Ͻ��ȶ��ں�����ִ��
	 * scheduleAtFixedRate:ʵ��ִ��Ϊ��ǰʱ��,��Ϊ�˸��Ͻ��ȶ�������β���ִ��.
	 * ����ִ��ʱ�䳬�����ʱ��
	 * schedule:��һ�ε�ִ��ʱ��ӱ���ʵ�ʵĿ�ʼ����,������񲻶ϵ���ʵ��ִ��ʱ��
	 * scheduleAtFixedRate:������������ʱ�����,�в�����*/
	public void testEight() {
		Timer timer = new Timer();
		//����ʱ�����
		Calendar calendar = Calendar.getInstance();//����ģʽ
		//����ʱ���ʽ���� 
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd : hh:mm:ss");
		//��ӡ��ǰʱ��
		System.out.println("ִ��ʱ��"+simpleDateFormat.format(calendar.getTime()));
		//Ϊ��ǰʱ��5��֮ǰ
		calendar.add(Calendar.SECOND, -5);
		//ִ�������run����,��ǰʱ�����ٺ���,�����2000����,����ִ��
		timer.scheduleAtFixedRate(new TimerTask() {
//		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd : hh:mm:ss");
				System.out.println("�����ڲ������ڽ���....");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.err.println("�߳�ִ�г�������...");
				}
				System.out.println("���ִ��ʱ��"+simpleDateFormat.format(scheduledExecutionTime()));
			}
		}, calendar.getTime(),2000L);	
	}
	
	public static void main(String[] args) {
		new TimerTest().testEight();
	}
}
