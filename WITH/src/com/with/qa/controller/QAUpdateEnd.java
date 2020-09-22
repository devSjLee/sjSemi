package com.with.qa.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.with.qa.model.service.QAService;
import com.with.qa.model.vo.QA;

/**
 * Servlet implementation class QAUpdateEnd
 */
@WebServlet("/QA/QAUpdateEnd")
public class QAUpdateEnd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QAUpdateEnd() {
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
		
		QA qa=new QA();
		qa.setQuestionIdx(Integer.parseInt(request.getParameter("no")));
		qa.setQuestionTitle(request.getParameter("title"));
		qa.setQuestionWriter(request.getParameter("writer"));
		qa.setQuestionOriginalFilename("upfile");
		qa.setQuestionContent(request.getParameter("content"));
		System.out.println("qa  :"+qa);
		
		
		int result=new QAService().qaUpdate(qa);
		System.out.println("result: "+result);
		String msg="";
		String loc="";
		if(result>0) {
			msg="성공적으로 수정되었습니다";
			loc="/QA/QAList";
		}else {
			msg="수정에 실패하였습니다";
			loc="/QA/QAList";
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
