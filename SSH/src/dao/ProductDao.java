package dao;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/***
 * 	��Ʒ�Ĺ�����
 * 	ʹ��Spring��������HibernateDaoSupport
 * 	��Spring��xml��ע��SessionFactory�����ֱ��ʹ��
 */
import bean.Product;

public class ProductDao extends HibernateDaoSupport {

	public void save(Product product) {
		
		this.getHibernateTemplate().save(product);
		System.out.println("Dao���б����˶���");
	}

}
