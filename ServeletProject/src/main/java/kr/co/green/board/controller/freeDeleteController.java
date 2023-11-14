package kr.co.green.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.green.board.model.service.BoardServiceImpl;


@WebServlet("/boardDelete.do")
public class freeDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public freeDeleteController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//값 받기(idx,제목,내용)
				int idx = Integer.parseInt(request.getParameter("idx"));
				 	
				//서비스 호출(DTO or 매개변수)
				BoardServiceImpl boardService = new BoardServiceImpl();
				int result = boardService.boardDelete(idx);
				
				
				
				if(result > 0) {
					response.sendRedirect("/freeList.do?cpage=1");
				}else {
					RequestDispatcher view = request.getRequestDispatcher("/views/common/error.jsp");
				      view.forward(request, response);
				}
	}

}
