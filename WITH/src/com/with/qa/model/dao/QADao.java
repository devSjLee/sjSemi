package com.with.qa.model.dao;

import static com.with.common.JDBCTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.with.qa.model.vo.QA;
import com.with.qa.model.vo.QAComment;

public class QADao {
	
	private Properties prop=new Properties();
	
	public QADao() {
		try {
			String file=QADao.class.getResource("/sql/QA.properties").getPath();
			prop.load(new FileReader(file));
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public List<QA> selectQAList(Connection conn, int cPage, int numPerPage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<QA> list=new ArrayList();
		
		
		try {
			
			pstmt=conn.prepareStatement(prop.getProperty("selectQAList"));
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2,cPage*numPerPage );
			rs=pstmt.executeQuery();
			while(rs.next()) {
				QA qa=new QA();
				
				qa.setQuestionIdx(rs.getInt("question_idx"));
				qa.setQuestionWriter(rs.getString("question_writer"));
				qa.setQuestionTitle(rs.getString("question_title"));
				qa.setQuestionContent(rs.getString("question_content"));
				qa.setQuestionOriginalFilename(rs.getString("question_original_filename"));
				qa.setQuestionRenamedFilename(rs.getString("question_renamed_filename"));
				qa.setQuestionEnrollDate(rs.getDate("question_enrolldate"));
				qa.setQuestionReadCount(rs.getInt("question_readcount"));
				list.add(qa);
			}
			System.out.println("야");
			for(QA q : list) {
				System.out.println(q);
			}
				
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public int selectQACount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectQACount"));
			rs=pstmt.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1);
			}
		}catch(SQLException e ) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
			
		}
		return count;
		
	}
	
	public int insertQA(Connection conn, QA qa) {
		PreparedStatement pstmt=null;
		int result=0;
		
		try {
			
			pstmt=conn.prepareStatement(prop.getProperty("insertQA"));
			pstmt.setString(1, qa.getQuestionWriter());
			pstmt.setString(2, qa.getQuestionTitle());
			pstmt.setString(3, qa.getQuestionContent());
			pstmt.setString(4, qa.getQuestionOriginalFilename());
			pstmt.setString(5, qa.getQuestionRenamedFilename());
			result=pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public QA selectQAOne(Connection conn,int num) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		QA qa=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectQAOne"));
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				qa=new QA();
				qa.setQuestionIdx(rs.getInt("question_idx"));
				qa.setQuestionWriter(rs.getString("question_writer"));
				qa.setQuestionTitle(rs.getString("question_title"));
				qa.setQuestionContent(rs.getString("question_content"));
				qa.setQuestionOriginalFilename(rs.getString("question_original_filename"));
				qa.setQuestionRenamedFilename(rs.getString("question_renamed_filename"));
				qa.setQuestionEnrollDate(rs.getDate("question_enrolldate"));
				qa.setQuestionReadCount(rs.getInt("question_readcount"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return qa;
	}
	
	public int updateReadCount(Connection conn, int num) {
		PreparedStatement pstmt = null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("updateReadCount"));
			pstmt.setInt(1, num);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int qaUpdate(Connection conn,QA qa) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("qaUpdate"));
			
			pstmt.setString(1, qa.getQuestionTitle());
			pstmt.setString(2, qa.getQuestionWriter());
			pstmt.setString(3, qa.getQuestionOriginalFilename());
			pstmt.setString(4, qa.getQuestionContent());
			pstmt.setInt(5, qa.getQuestionIdx());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public int qaDelete(Connection conn,int num) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("qaDelete"));
			pstmt.setInt(1, num);
			
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public int insertQaComment(Connection conn, QAComment qc) {
		PreparedStatement pstmt=null;
		int result=0;
		
		try {
			pstmt=conn.prepareStatement(prop.getProperty("insertQaComment"));
			pstmt.setInt(1, qc.getQaCommentLevel());
			pstmt.setString(2, qc.getQaCommentWriter());
			pstmt.setString(3, qc.getQaCommentContent());
			pstmt.setInt(4, qc.getQaRef());
			pstmt.setString(5, qc.getCommentRef()==0?null:String.valueOf(qc.getCommentRef()));
			result=pstmt.executeUpdate();
			System.out.println(qc.getQaCommentContent());
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	
	public List<QAComment> selectQACommentList(Connection conn, int num){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<QAComment> list =new ArrayList();
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectQACommentList"));
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				QAComment qc = new QAComment();
				qc.setQaCommentNo(rs.getInt(1));
				qc.setQaCommentLevel(rs.getInt(2));
				qc.setQaCommentWriter(rs.getString(3));
				qc.setQaCommentContent(rs.getString(4));
				qc.setQaRef(rs.getInt(5));
				qc.setCommentRef(rs.getInt(6));
				qc.setQaCommentDate(rs.getDate(7));
				list.add(qc);
		}
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		close(rs);
		close(pstmt);
	}return list;
	
		
	}
}
