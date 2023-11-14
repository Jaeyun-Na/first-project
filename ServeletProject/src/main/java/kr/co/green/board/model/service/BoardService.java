package kr.co.green.board.model.service;

import java.util.ArrayList;

import kr.co.green.board.model.dto.BoardDTO;
import kr.co.green.common.PageInfo;

public interface BoardService {
	//게시판 목록 조회
	public ArrayList<BoardDTO> boardList(PageInfo pi,String searchText);
	
	//전체 게시글 수
	public int boardListCount(String searchText);
	
	//게시글 등록
	public int boardEnroll(String title, String content, String name, String fileName, String uploadDirectory);
	//조회수 증가
	public int boardView(int idx);
	//게시글 보기
	public void boardSelect(BoardDTO board);
	//게시글 수정
	public int boardUpdate(int idx, String title, String content);
	//게시글 삭제
	public int boardDelete(int idx);
}
