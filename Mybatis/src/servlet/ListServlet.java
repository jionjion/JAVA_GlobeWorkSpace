package servlet;
/**
 * 	��Ϣ�б����*/
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Message;
import entity.Page;
import service.MessageListService;

//@WebServlet(name = "listServlet", urlPatterns = { "/listServlet" })
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ����ģ����ѯ
		request.setCharacterEncoding("UTF-8");
		String command = request.getParameter("command");
		String description = request.getParameter("description");
		//��ҳ��ѯ
		String currentPage = request.getParameter("currentPage");
		Page page = new Page();
		Pattern pattern = Pattern.compile("[0-9]{1,9}");	//������ʽ,ǰ��ֻ�ܴ���[0,9]����
		if (currentPage == null || currentPage.trim().equals("") || !pattern.matcher(currentPage).matches()) {
			page.setCurrentPage(1);
		}else{
			page.setCurrentPage(Integer.parseInt(currentPage));
		}
		//��ҳ,ģ����ѯ
		MessageListService messageListService = new MessageListService();
		List<Message> messagesList = messageListService.queryMessagesList(command, description,page);
		request.setAttribute("messagesList",messagesList);
		//��ҳ�洫�ݲ���:����,��ҳ
		request.setAttribute("command", command);
		request.setAttribute("description", description);
		request.setAttribute("page", page);
		//��תת��ҳ��
		request.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
