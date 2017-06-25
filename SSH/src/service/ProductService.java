package service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 	ҵ���߼���
 * 	ʹ��ע���������*/
import bean.Product;
import dao.ProductDao;
@Transactional
public class ProductService {

	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public void save(Product product) {
		productDao.save(product);
		System.out.println("Service�ķ���ִ��");
	}
	
}
