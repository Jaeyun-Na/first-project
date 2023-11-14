package kr.co.green.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.green.member.model.service.MemberServiceImpl;


@WebServlet("/memberDelete.do")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteController() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      request.setCharacterEncoding("UTF-8");
	      response.setContentType("text/html; charset=utf-8");
		
	      
		//세션에서 name 가져오기
	      HttpSession session = request.getSession();
	      String name = (String)session.getAttribute("name");
	      
	    //String name = (String)request.getSession().getAttribute("name");
	      
		//서비스 객체 생성 -> 호출
	      MemberServiceImpl memberService = new MemberServiceImpl();
			int result = memberService.memberDelete(name);
		//성공시"/"
		//실패시"/"
			if (result == 0) {
				response.sendRedirect("/views/common/error.jsp");
			} else {
				session.removeAttribute("name");
				session.invalidate(); // 세션 무효화
				RequestDispatcher view = request.getRequestDispatcher("/");
				view.forward(request, response);
			}
			//이동하는 페이지가 같을 경우
//			if(result !=0) {
//				RequestDispatcher view = request.getRequestDispatcher("/");
//			}
//			RequestDispatcher view = request.getRequestDispatcher("/");
//			view.forward(request, response);
		}

}
