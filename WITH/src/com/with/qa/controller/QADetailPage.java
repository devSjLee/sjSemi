package com.with.qa.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.with.qa.model.service.QAService;
import com.with.qa.model.vo.QA;
import com.with.qa.model.vo.QAComment;

/**
 * Servlet implementation class QADetailPage
 */
@WebServlet("/QA/detailPage")
public class QADetailPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QADetailPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int num=Integer.parseInt(request.getParameter("QANum"));
		
		Cookie[] cookies=request.getCookies();
		String QAHistory="";
		boolean hasRead=false;
		
		if(cookies!=null) {
			for(Cookie c : cookies) {
				String name=c.getName();
				String value=c.getValue();
				if("QAHistory".equals(name)) {
					QAHistory=value;
					if(value.contains("|"+num+"|")) {
						hasRead=true;
						break;
					}
				}
			}
		}
		
		if(!hasRead) {
			Cookie c=new Cookie("QAHistory",QAHistory+"|"+num+"|");
			c.setMaxAge(-1);
			response.addCookie(c);
		}
		
		
		
		QA qa=new QAService().selectQAOne(num,hasRead);
		
		//댓글
		List<QAComment> list = new QAService().selectQACommentList(num);
		String view="";
		if(qa==null) {
			request.setAttribute("msg", "조회된 게시판 글이 없습니다. ");
			request.setAttribute("loc", "/QA/QAList");
			view="/common/msg.jsp";
		}else {
			request.setAttribute("qa", qa);
			request.setAttribute("list", list);
			view="/views/QA/QADetailPage.jsp";
		}
		
		
		
		request.getRequestDispatcher(view).forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
