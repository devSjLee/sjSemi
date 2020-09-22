package com.with.qa.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.with.common.MyFileRename;
import com.with.qa.model.service.QAService;
import com.with.qa.model.vo.QA;

/**
 * Servlet implementation class QAFormEnd
 */
@WebServlet("/qa/qaFormEnd")
public class QAFormEnd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QAFormEnd() {
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
		
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "관리자에게 문의하세요!");
			request.setAttribute("loc", "/QA/QAList");
			request.getRequestDispatcher("/common/msg.jsp").forward(request, response);
			return;
		}
		
		String path = getServletContext().getRealPath("/upload/qa");
		int maxSize=1024*1024*10;
		
		MultipartRequest mr = new MultipartRequest(request,path,maxSize,"UTF-8",new MyFileRename());
		
		QA qa=new QA();
		qa.setQuestionTitle(mr.getParameter("title"));
		qa.setQuestionWriter(mr.getParameter("writer"));
		qa.setQuestionContent(mr.getParameter("content"));
		qa.setQuestionOriginalFilename(mr.getOriginalFileName("upfile"));
		qa.setQuestionRenamedFilename(mr.getFilesystemName("upfile"));
		
		
		System.out.println("qa:"+qa);
		int result=new QAService().insertQA(qa);
		String msg="";
		String loc="";
		if(result>0) {
			msg="QA등록 성공!";
			loc="/QA/QAList";
		}else {
			msg="QA등록 실패!";
			loc="/QA/QAList";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher("/common/msg.jsp").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
