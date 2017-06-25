package bean;

import java.util.List;

/***
 * 	��ҳ��װ����
 * @param <T>
 */
public class PageBean<T> {

	//��ǰҳ
	private int currentPage;
	//ÿҳ��ʾ����
	private int pageSize;
	//������
	private int totalCount;
	//��ҳ��
	private int totalPage;
	//ÿҳ��ʾ��
	private List<T> list;
	public int getCurrentPage() {
		return currentPage;
	}
	//��ҳ,����ڵ�ǰҳ����������
	public void setCurrentPage(int currentPage) {
		if (currentPage < 1) {
			this.currentPage = 1;
		}else if (currentPage>totalPage){
			this.currentPage = totalCount;
		}else {
			this.currentPage = currentPage;
		}
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
	
}
