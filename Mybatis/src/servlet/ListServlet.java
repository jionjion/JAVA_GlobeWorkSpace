package servlet;
/**
 * 	消息列表界面*/
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Message;
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
		//获取传入模糊查询
		request.setCharacterEncoding("UTF-8");
		String command = request.getParameter("command");
		String description = request.getParameter("description");
		MessageListService messageListService = new MessageListService();
		List<Message> messagesList = messageListService.queryMessagesList(command, description);
		request.setAttribute("messagesList",messagesList);
		System.out.println(messagesList.toString());
		
		//跳转转发页面
		request.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
