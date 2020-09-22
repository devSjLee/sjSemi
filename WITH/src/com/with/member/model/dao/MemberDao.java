package com.with.member.model.dao;

import static com.with.common.JDBCTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.with.member.model.vo.Member;

public class MemberDao {
	private Properties prop=new Properties();
	
	public MemberDao() {
		// TODO Auto-generated constructor stub
		try {
			String file=MemberDao.class.getResource("/member/memberService.properties").getPath();
			prop.load(new FileReader(file));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Member loginMember(Connection conn,String id,String pw) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Member m=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectMember"));
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m=new Member();
				m.setId(rs.getString("id"));
				m.setPassword(rs.getString("password"));
				m.setNickname(rs.getString("nickname"));
				m.setGrade(rs.getString("grade"));
				m.setPhone(rs.getString("phone"));
				m.setEmail(rs.getString("email"));
				m.setAddress(rs.getString("address"));
				m.setEnrollDate(rs.getDate("enrolldate"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return m;
	}
	
	public int enrollMember(Connection conn,Member m) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			//아이디 패스워드 닉네임 폰 주소 이메일
			pstmt=conn.prepareStatement(prop.getProperty("enrollMember"));
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getNickname());
			pstmt.setString(4, m.getPhone());
			pstmt.setString(5, m.getEmail());
			pstmt.setString(6, m.getAddress());
			result=pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public List<Member> checkDuplicate(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Member> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectAllMember"));
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Member m=new Member();
				m.setId(rs.getString("id"));
				m.setPassword(rs.getString("password"));
				m.setNickname(rs.getString("nickname"));
				m.setAddress(rs.getString("address"));
				m.setPhone(rs.getString("phone"));
				m.setEmail(rs.getString("email"));
				m.setGrade(rs.getString("grade"));
				m.setEnrollDate(rs.getDate("enrolldate"));
				list.add(m);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

}
