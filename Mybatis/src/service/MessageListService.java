package service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import bean.Message;
import dao.MessageDao;
import entity.Page;

/**
 * 	Message的维护
 * */
/**列表服务类*/
public class MessageListService {

	/**传入参数进行模糊查询,如果没有参数则是全部查询**/
	public List<Message> queryMessagesList(String command,String description, Page page ) {
		//封装查询条件,为一个对象,进行总条数的查询
		Message message = new Message();
		message.setCommand(command);
		message.setDescription(description);
		MessageDao messageDao = new MessageDao();
		int totalNumber = messageDao.count(message);	//查询总页数
		page.setTotalNumber(totalNumber);				//初始化分页对象
		page.count();									//计算分页详情
		//由于Mybatis只能封装一种引用数据类型,这里采用Map进行封装
		Map<String, Object> parameters = new HashMap<String,Object>();
		parameters.put("page", page);
		parameters.put("message", message);
		List<Message> messagesList = messageDao.queryMessagesListByInterface(parameters);
		return messagesList;
	}
	
	/**传入参数,实现删除*/
	public void deleteOneMessage(String id) {
		if (id != null && !"".equals(id.trim())) {
		 	MessageDao messageDao = new MessageDao();
		 	messageDao.deleteOneMessage(Integer.parseInt(id));
		}
	}
	
	/**传入参数,实现批量删除*/
	public void deleteBatchMessage(String[] ids) {
		//String字符数组转为List数字数组
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
