package servlet;
/**����ɾ��*/
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MessageListService;

public class DeleteBatchMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteBatchMessageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ����
		request.setCharacterEncoding("UTF-8");
		
		String[] ids = request.getParameterValues("id");
		MessageListService messageListService = new MessageListService();
		messageListService.deleteBatchMessage(ids);
		//��תת��ҳ��
		request.getRequestDispatcher("/listServlet").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
