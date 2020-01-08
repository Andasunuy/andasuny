package member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*
 * 메모: 2020/1/2 -윤석현
 * 
 * 각각의 기능들은 service 서블릿을 만들어 기능을 구현해야합니다
 * service 서블릿은 Action이라는 인터페이스의 excute 메소드를 구현해서 만들어야하며
 * db작업이 필요한경우 MemberDAO 클래스에 메소드를 만들어야합니다
 * 
 * ex) 회원가입 기능을 추가할때
 * 
 * 1. 이 컨트롤러에다 else if문으로 회원가입 요청이 들어올경우 처리할수있게 조건문을 추가합니다
 * 
 * 2. MemberDAO에 db에 회원을 추가하는 메소드를 추가합니다
 * 
 * 3. 실제 회원가입 기능을 실행할 서블릿을 만듭니다 이때 서블릿은 Action이라는 인터페이스위 excute라는 메소드를 구현해서 만듭니다
 * 
 * 
 */


//컨텍스트 패스가 /member 로 시작하는 모든 요청은 이 서블릿에서 처리합니다
@WebServlet("/member/*")
public class MemberControl extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doit(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doit(request,response);
	}
	
	
	protected void doit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		//뷰페이지 주소를 저장할 변수 
		String nextPage = "";
		//이 서블릿을 요청한 페이지 주소얻기		
		String path = request.getPathInfo();
		//조상 인터페이스 
		Action action=null;
		if(path.equals("/memberEmail.me")){
			//이메일 요청이 들어왔을경우 
			/*
			try{	
			action=new MemberGetEmail();		//MemberGetEmail 이라는 객체는 action의 excute를 구현하고있다
			action.excute(request,response)
			catch (Exception e) {
			e.printStackTrace();
			}
			*/
		}//이 뒤에다가 else if문으로 추가하세요
		
	}
	
}
