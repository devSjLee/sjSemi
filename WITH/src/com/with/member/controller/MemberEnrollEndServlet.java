package com.with.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.with.member.model.service.MemberService;
import com.with.member.model.vo.Member;

/**
 * Servlet implementation class MemberEnrollEndServlet
 */
@WebServlet("/enrollMember")
public class MemberEnrollEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberEnrollEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String msg="";
		String loc="/";
		
		String check=request.getParameter("duplicate");
		if(check.equals("true")) {			
			Member m=new Member();
			m.setId(request.getParameter("Id"));
			m.setPassword(request.getParameter("Password"));
			m.setNickname(request.getParameter("Nickname"));
			m.setPhone(request.getParameter("Phone"));
			m.setAddress(request.getParameter("Address"));
			m.setEmail(request.getParameter("Email"));
			
			MemberService ms=new MemberService();
			int result=ms.enrollMember(m);
			
			
			if(result>0) {
				msg="회원가입성공";
			}else {
				msg="회원가입실패";
				loc="/";
			}
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.getRequestDispatcher("/common/msg.jsp")
		.forward(request, response);
		}else {
			loc="/memberAdd";
			request.setAttribute("msg", "회원가입 전 아이디 중복확인을 해주시길 바랍니다.");
			request.setAttribute("loc", loc);
			request.getRequestDispatcher("/common/msg.jsp")
			.forward(request, response);	
		}
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
