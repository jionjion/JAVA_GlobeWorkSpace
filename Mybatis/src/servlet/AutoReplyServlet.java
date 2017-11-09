package servlet;
/**�첽����ĵ�����*/
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.QueryService;

/**
 * Servlet implementation class AutoReplyServlet
 */
@WebServlet("/AutoReplyServlet")
public class AutoReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AutoReplyServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String content = request.getParameter("content");
		response.setContentType("text/html;charset=utf-8");
		QueryService queryService = new QueryService();
		PrintWriter out = response.getWriter();
		//���ݹؼ��ַ��ز�ѯ���
		/*ʹ�õ����ѯ*/
//		String answer = queryService.queryByCommand(content);
		/*ʹ�ö���ѯ*/
		String answer = queryService.queryByCommandTwo(content);
		out.println(answer);
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
