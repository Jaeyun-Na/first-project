package kr.co.green.common;

public class Pagination {
	
	public static PageInfo getPageInfo(int listCount, int currentPage, int pageLimit, int boardLimit) {
		//전체 페이지
		//list count = 101, boardLimit = 5 일때
		//나우었을때 정확히 나눠지지 않음(int 기준)
		//double로 형변환 해서 소수점까지 가지고 올수 있도록
		//Math.ceil : 소수점 가지고 왔을 때 소수점을 올림 처리
		
		int maxPage = (int)Math.ceil((double)listCount/boardLimit);
		
		//시작 페이지
		//상황) 페이지 10개씩 보여줌, 사용자가 16페이지를 보고 있다
		//currentPage = 16,  pageLimit = 10
		
		int startPage = (currentPage-1 ) / pageLimit * pageLimit +1;
		
		//끝페이지 구하기
		//startPage = 11, pageLimit = 10
		int endPage = startPage + pageLimit -1;
		//끝페이지가 전체 페이지보다 클때 
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		int offset = (currentPage -1)*boardLimit;
		
		//새로운 쿼리를 사용할 경우 if부분 생략
//		if(currentPage == 1) {
//			boardLimit = offset+5;
//		}else {
//			boardLimit = offset+4;
//		}
		
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, 
									maxPage, startPage, endPage, offset);
		
		return pi;
				
	}
	
	
	
}
