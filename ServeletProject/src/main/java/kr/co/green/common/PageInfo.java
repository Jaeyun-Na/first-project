package kr.co.green.common;

public class PageInfo {

	//전체 게시글 수
	private int listCount;
	
	//현재 페이지
	private int currentPage;
	
	//한페이지에 보여질 페이지 수
	private int pageLimit;
	
	//한페이지에 글어갈 게시글 수
	private int boardLimit;
	
	//전체 페이지
	private int maxPage;
	
	//시작페이지
	private int startPage;
	
	private int offset;
	
	public PageInfo(int listCount, int currentPage, 
			int pageLimit, int boardLimit, int maxPage, 
			int startPage, int endPage, int offset) {
		super();
		this.listCount = listCount;
		this.currentPage = currentPage;
		this.pageLimit = pageLimit;
		this.boardLimit = boardLimit;
		this.maxPage = maxPage;
		this.startPage = startPage;
		this.endPage = endPage;
		this.offset = offset;
	}

	public int getOffSet() {
		return offset;
	}

	public void setOffSet(int offSet) {
		this.offset = offSet;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageLimit() {
		return pageLimit;
	}

	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}

	public int getBoardLimit() {
		return boardLimit;
	}

	public void setBoardLimit(int boardLimit) {
		this.boardLimit = boardLimit;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	//끝 페이지
	private int endPage;
	
	
	
}





