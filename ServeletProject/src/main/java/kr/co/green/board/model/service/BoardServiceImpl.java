package kr.co.green.board.model.service;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;

import kr.co.green.board.model.dao.FreeDAO;
import kr.co.green.board.model.dto.BoardDTO;
import kr.co.green.common.DatabaseConnection;
import kr.co.green.common.PageInfo;

public class BoardServiceImpl implements BoardService {
	private Connection con;
	private DatabaseConnection dc;
	private FreeDAO freeDAO;

	// DB연결
	public BoardServiceImpl() {
		freeDAO = new FreeDAO();
		dc = new DatabaseConnection();
		con = dc.connDB();
	}

	// 게시판 목록 조회
	public ArrayList<BoardDTO> boardList(PageInfo pi,String searchText){
		return freeDAO.boardList(con, pi, searchText);
	}
	//전체 게시글 수
	public int boardListCount(String searchText) {
		return freeDAO.boardListCount(con,searchText);
	}
	//게시글 등록
	public int boardEnroll(String title, String content, String name, String fileName, String uploadDirectory) {
		return freeDAO.boardEnroll(con,title,content,name,fileName,uploadDirectory);
	}
	//조회수 증가
	public int boardView(int idx) {
		return freeDAO.boardView(con,idx);
	}
	//게시글 내용 보기
	public void boardSelect(BoardDTO board) {
		freeDAO.boardSelect(con,board);
		
	}
	//게시글 수정
	public int boardUpdate(int idx, String title, String content) {
		return freeDAO.boardUpdate(con,idx,title,content);
	}
	//게시글 삭제
	public int boardDelete(int idx) {
		return freeDAO.boardDelete(con,idx);
	}
}