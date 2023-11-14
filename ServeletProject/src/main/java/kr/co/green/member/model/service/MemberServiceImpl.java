package kr.co.green.member.model.service;

import java.sql.Connection;

import kr.co.green.common.DatabaseConnection;
import kr.co.green.member.model.dao.MemberDAO;
import kr.co.green.member.model.dto.MemberDTO;

public class MemberServiceImpl implements MemberService{

	private Connection con;
	private DatabaseConnection dc;
	private MemberDAO memberDAO;
	
	//DB연결
	public MemberServiceImpl() {
		memberDAO = new MemberDAO();
		dc = new DatabaseConnection();
		con = dc.connDB();
	}
	
	//회원가입
	@Override
	public int memberEnroll(MemberDTO memberDTO){
		return memberDAO.memberEnroll(con,memberDTO);
	}
	
	//로그인
	@Override
	public MemberDTO memberLogin(String id) {
		return memberDAO.memberLogin(con, id);
	}

	//중복 검사
	public boolean duplicateId(String id) {
		return memberDAO.duplicateId(con, id);
	}
	//회원정보 조회
	@Override
	public void selectMember(MemberDTO memberDTO) {
		memberDAO.selectMember(con, memberDTO);
	}
	//회원정보 수정
	@Override
	public int memberUpdate(MemberDTO memberDTO, String brforeName) {
		return memberDAO.memberUpdate(con, memberDTO, brforeName);
	}
	//회원 탈퇴
	@Override
	public int memberDelete(String name) {
		return memberDAO.memberDelete(con, name);
	}
}





















