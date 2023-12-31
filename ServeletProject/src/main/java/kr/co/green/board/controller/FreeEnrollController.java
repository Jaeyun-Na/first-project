package kr.co.green.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import kr.co.green.board.model.service.BoardServiceImpl;


@WebServlet("/freeEnroll.do")
@MultipartConfig(
		fileSizeThreshold = 1024*1024,  //1MB
		maxFileSize = 1024*1024*5,		//파일 한개의 최대사이즈 5MB
		maxRequestSize = 1024*1024*5*5	//여러파일을 보낼때 한번에 보낼수 있는 용량 25MB
		)

public class FreeEnrollController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FreeEnrollController() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//내용, 제목 받기
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		//세션에 있는 name받아오기
		HttpSession session = request.getSession();
		String name = (String)session.getAttribute("name");
		
		//파일 업로드
		Collection<Part> parts = request.getParts();
		String uploadDirectory = "C:\\Users\\jaeyun\\eclipse-workspace\\ServeletProject\\src\\main\\webapp\\resources\\uploads";
		
		//파일 업로드 디렉토리(폴더)가 없으면 생성
		File filePath = new File(uploadDirectory);
		if(!filePath.exists()) {
			filePath.mkdirs();
		}
		
		String fileName = null;
		for(Part part : parts) {
			fileName = getFileName(part);
			
			if(fileName != null) {
				part.write(filePath + File.separator + fileName);
			}
		}

		//서비스 호출
		BoardServiceImpl boardService = new BoardServiceImpl();
		int result = boardService.boardEnroll(title,content,name,fileName,uploadDirectory);
		//성공 유무에 따라 처리
		if(result > 0) {
			response.sendRedirect("/freeList.do?cpage=1");
		}
	}

	//파일 이름을 가져오는 메소드(되도록은 common에 작성)
	private String getFileName(Part part) {
		String contentDisposition = part.getHeader("content-disposition");
		String[] tokens = contentDisposition.split(";");
		
		//토큰을 하나씩 꺼내면서 토큰의 이름이 filename으로
		//시작하는걸 찾을때 가지 반복
		for(String token : tokens) {
			//토큰의 이름이 filename으로 시작하는지 확인
			if(token.trim().startsWith("filename")) {
				
				//파일의 이름이"filename=" 다음에 나오기 떄문에
				//"filename="의 다름 문자부터 끝까지 추출
				return token.substring(token.indexOf('=')+2, token.length()-1);
			}
		}
		return null;
	}
	
}
