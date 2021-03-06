package jms.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 	消息队列的消费者,消费生产者生产的消息队列
 * 	验证:同时开启多个消费者,在生产发布消息后,消费者们会平均分食生产者发布的消息.
 * */
public class AppConsumer {
	
	private static final String URI = "tcp://127.0.0.1:61616";
	private static final String queueName = "queue-demo";
	
	public static void main(String[] args) throws Exception {
		//1.创建connectionFactory
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URI);
		//2.创建connection
		Connection connection = connectionFactory.createConnection();
		//3.启动连接
		connection.start();
		//4.创建会话		不使用事务,同时消息为自动应答
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//5.创建一个目标
		Destination destination = session.createQueue(queueName);
		//6.创建消费者,传入监听的位置
		MessageConsumer messageConsumer = session.createConsumer(destination);
		//7.创建一个监听器
		messageConsumer.setMessageListener(new MessageListener() {
			//匿名内部类,重写方法,异步调用
			public void onMessage(Message message) {
				TextMessage textMessage = (TextMessage) message;
				try {
					System.out.println("接收消息:"+textMessage.getText());
				} catch (JMSException e) {
					e.printStackTrace();
					System.err.println("接收消息失败....");
				}
			} 
		});

		//9.关闭连接
		//connection.close();
	}
}
