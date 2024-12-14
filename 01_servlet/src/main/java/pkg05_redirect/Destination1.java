package pkg05_redirect;

import java.io.IOException;
import java.net.URLEncoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Destination1 extends HttpServlet {
  
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	   /*
     * redirect
     * 
     * 1. 이동할 때 요청과 응답 모두 전달핮 ㅣ않는다.
     * 2. 이동 경로를 작성할 때 contextPath 부터 작성해야 한다. (전체 경로 작성)
     * 3. 클라이언트는 forward 경로를 확인할 수 있다.
     * 4. redirect 하는 경우 
     *     1) Query insert 
     *     2) Query update  
     *     3) Query delete  
     */
    
	  request.setCharacterEncoding("UTF-8");
	  String luggage = request.getParameter("luggage");
	  
	  response.sendRedirect("/servlet/destination2?luggage=" + URLEncoder.encode(luggage, "UTF-8"));
	  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
