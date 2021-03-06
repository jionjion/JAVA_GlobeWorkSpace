package interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import entity.Page;


/**分页拦截器*/
@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class})})
public class PageInterceptor implements Interceptor{

	/**拦截后的方法*/
	@SuppressWarnings("unchecked")
	@Override
	public Object intercept(Invocation invocation) throws Throwable {

		//获得拦截的对象的代理
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY,SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
		MappedStatement mappedStatement  = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
		//获得SQL语句的ID
		String id = mappedStatement.getId();
		//正则表达式,以ByPageInterceptor结尾的进行拦截器操作
		if (id.matches(".+ByPageInterceptor$")) {
			
			BoundSql boundSql = statementHandler.getBoundSql();
			String SQL = boundSql.getSql();	//获得原始的SQL
			String countSQL = " select count(*) from (" + SQL + ") a";
			Map<String, Object> parameters = (Map<String, Object>) boundSql.getParameterObject();	//获得传入的SQL参数
			Page page = (Page) parameters.get("page");		//获得page实体类
			
			Connection connection = (Connection) invocation.getArgs()[0];	//获得传入参数,只有一个,因此取第一个
			PreparedStatement preparedStatement = connection.prepareStatement(countSQL);
			ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");
			parameterHandler.setParameters(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				page.setTotalNumber(resultSet.getInt(1));	//获得总条数
			}
			String pageSQL = SQL + " limit " + page.getStart() + " , " + page.getPace();
			metaObject.setValue("delegate.boundSql.sql", pageSQL);	//修改SQL为自定义的
			
		}
		return invocation.proceed();
	}

	/**	执行拦截前的方法,将注解声明的类进行拦截
	 * 	如果是符合条件的代理类,则完成返回拦截的对象的代理*/
	@Override
	public Object plugin(Object target) {
		
		return Plugin.wrap(target, this);	//拦截,拦截的类
	}

	/**	获得配置文件执行时的设置属性 */
	@Override
	public void setProperties(Properties properties) {
		String info = properties.getProperty("info");
		System.out.println(info);
	}

	
}
