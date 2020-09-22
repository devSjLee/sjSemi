package com.with.qa.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.with.member.model.vo.Member;
import com.with.qa.model.service.QAService;
import com.with.qa.model.vo.QA;

/**
 * Servlet implementation class QAListServlet
 */
@WebServlet("/QA/QAList")
public class QAListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QAListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		

		
		
		int cPage;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		int numPerPage=5;
		
		List<QA> list = new QAService().selectQAList(cPage, numPerPage);
		
		int pageBarSize=5;
		int totalData = new QAService().selectQACount();
		int totalPage =(int)(Math.ceil((double)totalData/numPerPage));
		int pageNo = ((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd = pageNo + pageBarSize-1;
		
		String pageBar="";
		if(pageNo==1) {
			pageBar ="<span>[이전]</span>";
			
		}else {
			pageBar = "<a href='"+request.getContextPath()+"/QA/QAList?cPage="+(pageNo-1)+"'>[이전]</a>";

		}
		
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(cPage==pageNo) {
				pageBar+="<span>"+pageNo+"</span>";
				
			}else {
				pageBar+="<a href='"+request.getContextPath()+"/QA/QAList?cPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar+="<span>[다음]</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+"/QA/QAList?cPage="+pageNo+"'>[다음]</a>";
		}
		
		request.setAttribute("QA", list);
		request.setAttribute("pageBar", pageBar);
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/QA/QAList.jsp");
		rd.forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
