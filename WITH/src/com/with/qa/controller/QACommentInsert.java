package com.with.qa.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.with.qa.model.service.QAService;
import com.with.qa.model.vo.QAComment;

/**
 * Servlet implementation class QACommentInsert
 */
@WebServlet("/qa/qaCommentInsert")
public class QACommentInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QACommentInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
		
		int qaRef=Integer.parseInt(request.getParameter("qaRef"));
		String qaCommentWriter=request.getParameter("qaCommentWriter");
		int qaCommentLevel = Integer.parseInt(request.getParameter("qaCommentLevel"));
		int qaCommentRef = Integer.parseInt(request.getParameter("qaCommentRef"));
		String qaCommentContent=request.getParameter("qaCommentContent");
		
		QAComment qc=new QAComment(29,qaCommentLevel,qaCommentWriter,qaCommentContent,qaRef,qaCommentRef,null);
		
		int result=new QAService().insertQaComment(qc);
		String msg="";
		String loc="/QA/detailPage?QANum="+qaRef;
		if(result>0) {
			msg="댓글 등록 성공";
		}else {
			msg="댓글 등록 실패";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/common/msg.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
