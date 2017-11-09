package db;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/***
 *	�������ݿ����
 */
public class DBAccess {

	/**��ȡSQLSession�������*/
	public SqlSession getSqlSession() throws Exception{
		//1.��ȡ�����ļ�
		Reader reader = Resources.getResourceAsReader("config/Configuration.xml");
		//2.����SQLSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		//3.ͨ��SQLSessionFactory����һ�����ݿ�Ự
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//����һ���Ựʵ��
		return sqlSession;
	}
}
