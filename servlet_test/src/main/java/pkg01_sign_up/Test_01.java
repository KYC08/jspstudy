package pkg01_sign_up;

import java.io.IOException;
import java.util.Arrays;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Test_01 extends HttpServlet {
  
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
    String id = request.getParameter("id");
    String pwd = request.getParameter("pwd");
    String name = request.getParameter("name");
    String[] birth = request.getParameterValues("brtday");
    String gender = request.getParameter("gender");
    String email = request.getParameter("email");
    String mobile = request.getParameter("mobile");

    System.out.println("아이디 : " + id);
    System.out.println("비밀번호 : " + pwd);
    System.out.println("이름 : " + name);
    System.out.println("생년월일 : " + Arrays.toString(birth));
    System.out.println("성별 : " + gender);
    System.out.println("이메일 : " + email);
    System.out.println("휴대전화 : " + mobile);

	}
}


