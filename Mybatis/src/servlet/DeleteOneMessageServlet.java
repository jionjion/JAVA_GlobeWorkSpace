package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MessageListService;

/**
 * Servlet implementation class DeleteOneMessageServlet
 */
//@WebServlet("/DeleteOneMessageServlet")
public class DeleteOneMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteOneMessageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ����
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		MessageListService messageListService = new MessageListService();
		messageListService.deleteOneMessage(id);
		//��תת��ҳ��
		request.getRequestDispatcher("/listServlet").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
