<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.with.member.model.vo.Member"%>
<%	
	
	
%>
  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/headerstyle.css" type="text/css">
</head>
<body>
    <div class="navbar">
        <!-- walk ㅁㅔ뉴 눌렀을때 walk 글자 자체가 방방뛴다거나 밑에 줄이 생긴다거나 하는 이펙트 있었으면 좋겠다리 -->
        <a href="#" id="walk">산책등록</a>
        <a href="#" id="festival">산책참여</a>
        <a href="#" id="ground">공지질문</a>
        
        <a href="<%=request.getContextPath() %>/QA/QAList" id="QA">Q&A</a>
        
    </div>
    
  
    
    
    
    