package kr.co.green.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.co.green.member.model.dto.MemberDTO;

public class MemberDAO {

	private PreparedStatement pstmt;

	/// 회원가입
	public int memberEnroll(Connection con, MemberDTO memberDTO) {
		String query = "INSERT INTO member VALUES(?,?,?, sysdate)";
		int result = 0;

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberDTO.getId());
			pstmt.setString(2, memberDTO.getPwd());
			pstmt.setString(3, memberDTO.getName());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 로그인
	public MemberDTO memberLogin(Connection con, String id) {
		String query = "SELECT MEMBER_ID, MEMBER_PWD, MEMBER_NAME, MEMBER_DATE"
				+ "		from member"
				+ "		WHERE MEMBER_ID = ?";

		MemberDTO result = new MemberDTO();

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String resultId = rs.getString("MEMBER_ID");
				String resultPwd = rs.getString("MEMBER_PWD");
				
				String resultName = rs.getString("MEMBER_NAME");
				String resultIndate = rs.getString("MEMBER_DATE");
				
				System.out.println(resultName);

				result.setId(resultId);
				result.setPwd(resultPwd);
				result.setName(resultName);
				result.setIndate(resultIndate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//아이디 중복 검사
	public boolean duplicateId(Connection con, String id) {
		String query = "SELECT member_id FROM MEMBER WHERE member_id = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			
			//쿼리가 조회된 결과(resiltSet)가 있다면 true
			//없다면 false
			return rs.next();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	//회원정보 조회
	public void selectMember(Connection con, MemberDTO memberDTO) {
		String query = "SELECT member_id,"
				+"				member_name,"
				+"				member_date"
				+"		FROM	member"
				+"		WHERE member_name = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberDTO.getName());
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString("MEMBER_ID");
				String name = rs.getString("MEMBER_NAME");
				String indate = rs.getString("MEMBER_DATE");
				
				memberDTO.setId(id);
				memberDTO.setName(name);
				memberDTO.setIndate(indate);
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	}
	//회원 정보 수정
	public int memberUpdate(Connection con, MemberDTO memberDTO, String brforeName) {
		
		String query = "UPDATE MEMBER SET MEMBER_ID = ?,"
				+"							MEMBER_NAME = ?"
				+"						WHERE MEMBER_NAME = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberDTO.getId());
			pstmt.setString(2, memberDTO.getName());
			pstmt.setString(3, brforeName);
			
			
//			result = pstmt.executeUpdate();
			int result = pstmt.executeUpdate();
			return result;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public int memberDelete(Connection con, String name) {
		String query = "DELETE FROM member WHERE member_name =?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, name);
			
			int result = pstmt.executeUpdate();
			return result;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return 0;
	}


}
