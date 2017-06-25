package dao;
/**
 * 	1.将命名空间改为接口的路径
 * 	2.将方法名与ID相一致
 * 	3.返回类型与XML配置文件一致
 * 	4.参数类型与XML配置文件一致
 */
import java.util.List;

import bean.Message;

/**和IMessage对应接口式规范*/
public interface IMessage {

	/**查询消息列表的方法*/
	public List<Message> queryMessagesList(Message message);
	
}
