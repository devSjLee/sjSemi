package com.with.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.with.member.model.service.MemberService;
import com.with.member.model.vo.Member;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String id=(String)request.getParameter("Id");
		String pw=(String)request.getParameter("Password");
		Member m=new MemberService().loginMember(id,pw);
		System.out.println(id+" : "+pw);
		System.out.println(m);
		if(m!=null) {
			HttpSession session=request.getSession();
			session.setAttribute("loginUser", m);
			request.setAttribute("msg", "로그인에 성공하셨습니다!");
			request.setAttribute("loc", "/");
			
			request.getRequestDispatcher("/common/msg.jsp").forward(request, response);
			return;
		}else {
			request.setAttribute("msg", "아이디나 비밀번호가 일치하지 않습니다!");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/common/msg.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
