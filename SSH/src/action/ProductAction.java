package action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import bean.Product;
/***
 * 	��Ʒ��Action����
 * 	ʹ�ò����,Ĭ�Ͽ������Զ�װ��,����Ҫ��˽�����Ի���set()������ʹ��@AutoWire
 * 
 */
import service.ProductService;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{

	private static final long serialVersionUID = 1L;
	
	/*ʹ��ģ������װ�䴫�����*/
	private Product product = new Product();
	@Override
	public Product getModel() {
		return product;
	}
	
	/*ʹ�ò����,����Զ�װ��*/
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	/**������Ʒ�ķ���,ע�ⷽ������Struts2�������ļ���һ��*/
	public String save() {
		
		System.out.println("Actionִ����:"+product.toString());
		productService.save(product);
		//�޷���ҳ��
		return this.NONE;
	}
	
}
