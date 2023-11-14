package kr.co.green.board.controller;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.green.board.model.dto.BoardDTO;
import kr.co.green.board.model.service.BoardServiceImpl;


@WebServlet("/freeDetail.do")
public class freeDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public freeDetailController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//idx받기
		int idx = Integer.parseInt(request.getParameter("idx"));
		//조회수 증가(UPDATE)
		BoardServiceImpl boardService = new BoardServiceImpl();
		int result = boardService.boardView(idx);
		
		if(result > 0) {
			//idx로 게시판 조회(SELECT)
			BoardDTO board = new BoardDTO();
			board.setIdx(idx);
			//업로드 된 파일 가져오기
			boardService.boardSelect(board);
			
			if(!Objects.isNull(board.getIdx())) {
				
				//
				
				//파일이름 바인딩
				request.setAttribute("board", board);
				RequestDispatcher view = request.getRequestDispatcher("views/board/free/freeDetail.jsp");
				view.forward(request, response);
			}
			
			}else {
			
		}
		
		//freeDetail.jsp로 포워딩
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
