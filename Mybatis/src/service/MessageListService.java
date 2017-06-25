package service;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * 	Message��ά��
 * */
import java.util.List;

import bean.Message;
import dao.MessageDao;

/**�б������*/
public class MessageListService {

	/**�����������ģ����ѯ,���û�в�������ȫ����ѯ**/
	public List<Message> queryMessagesList(String command,String description ) {
		MessageDao messageDao = new MessageDao();
		List<Message> messagesList = messageDao.queryMessagesListByInterface(command, description);
		return messagesList;
	}
	
	/**�������,ʵ��ɾ��*/
	public void deleteOneMessage(String id) {
		if (id != null && !"".equals(id.trim())) {
		 	MessageDao messageDao = new MessageDao();
		 	messageDao.deleteOneMessage(Integer.parseInt(id));
		}
	}
	
	/**�������,ʵ������ɾ��*/
	public void deleteBatchMessage(String[] ids) {
		//String�ַ�����תΪList��������
		List<Integer> list = new ArrayList<Integer>();
		for (String string : ids) {
			list.add(new Integer(string));
		}
		
		if (list != null && list.size()>0) {
		 	MessageDao messageDao = new MessageDao();
		 	messageDao.deleteBatchMessage(list);
		}
	}
}
