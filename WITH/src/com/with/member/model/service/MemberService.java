package com.with.member.model.service;

import static com.with.common.JDBCTemplate.close;
import static com.with.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.with.member.model.dao.MemberDao;
import com.with.member.model.vo.Member;

public class MemberService {
	private MemberDao dao=new MemberDao();
	
	public Member loginMember(String id,String pw) {
		Connection conn=getConnection();
		Member m=dao.loginMember(conn,id,pw);
		close(conn);
		return m;
	}
	
	public int enrollMember(Member m) {
		Connection conn=getConnection();
		int result=dao.enrollMember(conn,m);
		close(conn);
		return result;
	}
	
	public List<Member> checkDuplicate() {
		Connection conn=getConnection();
		List<Member> list=dao.checkDuplicate(conn);
		close(conn);
		return list;
	}
	
	

}
