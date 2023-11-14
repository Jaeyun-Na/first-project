package kr.co.green.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.green.board.model.service.BoardServiceImpl;


@WebServlet("/boardUpdate.do")
public class freeUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public freeUpdateController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//값 받기(idx,제목,내용)
		int idx = Integer.parseInt(request.getParameter("idx"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		 	
		//서비스 호출(DTO or 매개변수)
		BoardServiceImpl boardService = new BoardServiceImpl();
		int result = boardService.boardUpdate(idx,title,content);
		//성공 유무에 따라 처리
		//성공시 /freeList.do로 이동(sendRedirect)
		//실패시 /views/common/error.jsp
		System.out.println(result);
		
		if(result > 0) {
			response.sendRedirect("/freeList.do?cpage=1");
		}else {
			RequestDispatcher view = request.getRequestDispatcher("/views/common/error.jsp");
		      view.forward(request, response);
		}
//		RequestDispatcher
//		호출할 페이지가 .jsp
//		
//		response.sendRedirect()
//		호출할 페이지가 Controller
		
		
		
		
		
	}

}
