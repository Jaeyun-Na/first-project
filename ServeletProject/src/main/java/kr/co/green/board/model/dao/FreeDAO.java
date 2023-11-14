package kr.co.green.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.green.board.model.dto.BoardDTO;
import kr.co.green.common.PageInfo;

public class FreeDAO {

   private PreparedStatement pstmt;

   public ArrayList<BoardDTO> boardList(Connection con, PageInfo pi,String searchText) {
      
	   
	   ArrayList<BoardDTO> list = new ArrayList<>();
      //MySQL offset페이징
//    String query = "SELECT fb_idx, fb_title, fb_in_date, fb_views, fb_writer 
//      										FROM free_board LIMIT ? OFFSET?";
      //MySQL cursur페이징
//    String query = "SELECT fb_idx, fb_title, fb_in_date, fb_views, fb_writer 
//      										FROM free_board WHERE fb_idx > ? LIMIT ?";
      //Oracle 문법
//      String query = "SELECT fb2.*"
//           + "		 	     FROM (SELECT rownum AS rnum, fb.* "
//           + " 							          FROM (SELECT fb_idx,"
//           + "          				               fb_title,"
//           + "                       				   fb_in_date,"
//           + "                         				   fb_views,"
//           + "                         				   fb_writer"
//           + "                    					   FROM free_board"
//           + "                   					   ORDER BY fb_idx ASC) fb) fb2"
//           + "         							  WHERE rnum BETWEEN ? AND ?";
      //위 커리를 좀더 간단하게
      String query = "SELECT fb_idx, fb_title, fb_in_date, fb_views, fb_writer from free_board"
      		+ "			WHERE fb_delete_date IS NULL "
      		+ "			AND fb_title LIKE '%'||?||'%'"
      		+ "			ORDER BY fb_in_date DESC"
      		+ "			OFFSET ? ROWS FETCH FIRST ? ROWS ONLY";
      try {
         pstmt = con.prepareStatement(query);
         
         //1page -> 0~5
         //2page -> 5~5
         //3page -? 10~5
         pstmt.setString(1,searchText);
         pstmt.setInt(2,pi.getOffSet());
         pstmt.setInt(3,pi.getBoardLimit());
         
         ResultSet rs = pstmt.executeQuery();
         
         while (rs.next()) {
            int idx = rs.getInt("fb_idx");
            String title = rs.getString("fb_title");
            String inDate = rs.getString("fb_in_date");
            int views = rs.getInt("fb_views");
            String writer = rs.getString("fb_writer");

            BoardDTO board = new BoardDTO();
            board.setIdx(idx);
            board.setTitle(title);
            board.setInDate(inDate);
            board.setViews(views);
            board.setWriter(writer);

            list.add(board);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }

      return list;
   }

public int boardListCount(Connection con, String searchText) {
	String query = "SELECT count(*) AS cnt FROM free_board WHERE fb_delete_date IS NULL"
			+ " 										AND fb_title LIKE '%' || ? || '%'";
	
	try {
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, searchText);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			int result = rs.getInt("cnt");
			return result;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	return 0;
}

public int boardEnroll(Connection con, String title, String content, String name, String fileName, String uploadDirectory) {
		//쿼리 작성
		String query="INSERT INTO free_board VALUES(FB_IDX_SEQ.nextval," //회원번호
				+ "									?,"					 //제목
				+ "									?,"					 //내용
				+ "									SYSDATE,"			 //작성일
				+ "									null,"				 //수정일
				+ "									null,"				 //삭제일
				+ "									0,"					 //조회수
				+ "									?,"					 //작성자
				+ "									?,"					 //파일 경로
				+ "									?)";				 //파일 이름
		
		try {
			//쿼리 사용 준비
			pstmt = con.prepareStatement(query);
			
			//물음표 값
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, name);
			pstmt.setString(4, uploadDirectory);
			pstmt.setString(5, fileName);
			
			//쿼리 실행
			int result = pstmt.executeUpdate();
			
			//DB연결 종료
			pstmt.close();
			con.close();
			
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	return 0;
}
//조회수 증가쿼리
public int boardView(Connection con, int idx) {
	String query = "UPDATE free_board"
			+ "		SET fb_views = fb_views+1"
			+ "		WHERE fb_idx = ?";
	
	try {
		pstmt = con.prepareStatement(query);
		
		pstmt.setInt(1, idx);
		
		int result = pstmt.executeUpdate();
		
//		pstmt.close();
//		con.close();
		
		return result;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return 0;
}

public Object boardSelect(Connection con, BoardDTO board) {
	String query="SELECT fb_idx,"
			+ "			 fb_title,"
			+ "			 fb_writer,"
			+ "			 fb_views,"
			+ "			 fb_in_date,"
			+ "			 fb_content,"
			+ "			 file_name"
			+ " FROM free_board"
			+ " WHERE fb_idx=?";
	
	try {
		pstmt=con.prepareStatement(query);
		
		pstmt.setInt(1,board.getIdx());
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			int idx = rs.getInt("fb_idx");
			String title = rs.getString("FB_TITLE");
			String writer = rs.getString("FB_WRITER");
			int views = rs.getInt("FB_VIEWS");
			String indate = rs.getString("FB_IN_DATE");
			String content = rs.getString("FB_CONTENT");
			String fileName = rs.getString("FILE_NAME");
			
			board.setIdx(idx);
			board.setTitle(title);
			board.setWriter(writer);
			board.setViews(views);
			board.setInDate(indate);
			board.setContent(content);
			board.setFileName(fileName);

		}
		
		pstmt.close();
		con.close();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	return null;
}

public int boardUpdate(Connection con, int idx, String title, String content) {

	String query = "UPDATE free_board SET fb_title = ?,"
			+ "							  fb_content = ?,"
			+ "							  fb_update_date = sysdate"
			+ "						  WHERE fb_idx =?";
	
	try {
		pstmt=con.prepareStatement(query);
		
		pstmt.setString(1,title);
		pstmt.setString(2,content);
		pstmt.setInt(3,idx);
		
		int result = pstmt.executeUpdate();
		
		pstmt.close();
		con.close();
		
		return result;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	return 0;
}

public int boardDelete(Connection con, int idx) {
//	String query = "DELETE FROM free_board WHERE fb_idx =?";
	String query = "UPDATE free_board SET fb_delete_date = sysdate"
			+ "				WHERE fb_idx = ?";
	try {
		pstmt=con.prepareStatement(query);
		
		pstmt.setInt(1,idx);
		
		int result = pstmt.executeUpdate();
		
		pstmt.close();
		con.close();
		
		return result;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return 0;
}




}