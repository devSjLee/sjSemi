package com.with.qa.model.service;



import static com.with.common.JDBCTemplate.close;
import static com.with.common.JDBCTemplate.commit;
import static com.with.common.JDBCTemplate.getConnection;
import static com.with.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.with.qa.model.dao.QADao;
import com.with.qa.model.vo.QA;
import com.with.qa.model.vo.QAComment;

public class QAService {
	
	private QADao dao=new QADao();
	
	public List<QA> selectQAList(int cPage, int numPerPage){
		Connection conn=getConnection();
		
		List<QA> list=dao.selectQAList(conn, cPage,numPerPage);
		close(conn);
		return list;
		
	}
	
	public int selectQACount() {
		Connection conn=getConnection();
		int count=dao.selectQACount(conn);
		close(conn);
		return count;
	}
	
	public int insertQA(QA qa) {
		Connection conn=getConnection();
		int result=dao.insertQA(conn,qa);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public QA selectQAOne(int num, boolean hasRead) {
		Connection conn=getConnection();
		QA qa=dao.selectQAOne(conn, num);
		
		if(qa!=null&&!hasRead) {
			int result=dao.updateReadCount(conn,num);
			if(result>0) commit(conn);
			else rollback(conn);
		}
		
		close(conn);
		return qa;
	}
	
	public QA selectQAOne(int num) {
		Connection conn=getConnection();
		QA qa=dao.selectQAOne(conn, num);
		
		
		
		close(conn);
		return qa;
	}
	
	public int qaUpdate(QA qa) {
		Connection conn=getConnection();
		int result=dao.qaUpdate(conn,qa);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
		
		
	}
	
	public int qaDelete(int num) {
		Connection conn=getConnection();
		int result=dao.qaDelete(conn,num);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int insertQaComment(QAComment qc) {
		Connection conn=getConnection();
		int result=dao.insertQaComment(conn, qc);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;
	}
	
	public List<QAComment> selectQACommentList(int num){
		Connection conn=getConnection();
		List<QAComment> list=dao.selectQACommentList(conn,num);
		close(conn);
		return list;
		
	}
	
	
}
