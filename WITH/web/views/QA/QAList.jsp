<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp" %>
<%@ include file="/common/iconbar.jsp" %>
<%@ page import="java.util.List, com.with.qa.model.vo.QA" %>

<%
	List<QA> list=(List)request.getAttribute("QA");
	String pageBar=(String)request.getAttribute("pageBar");
	
%>

<style>
	#QA-container{width:70%;}
	#QA-container h2{text-align: center; margin-bottom:80px;}
	table#tbl-QA{width: 50%; border:1px black solid; border-collapse: collapse;
					margin-top:200px;margin-left:45%;}
	table#tbl-QA th, table#tbl-QA td{border:1px solid; }
	div#pageBar{margin-top:10px; margin-left:65%; }
	
	td[colspan="5"]{text-align: center; }
	
</style>

<section id="QA-container">
	<h2>Q&A</h2>
	
		<%if(loginUser!=null) {%>
		<button type="button" onclick="location.assign('<%=request.getContextPath()%>/QA/QAForm')">글쓰기</button>
		<%} %>
		<table id="tbl-QA">
			
			<tr>
				<th>번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>첨부파일</th>
				<th>작성날짜</th>
				<th>조회수</th>
			</tr>
			<% if(list.isEmpty()){%>
			<tr>
				<td colspan="6">
					등록된 게시글이 없습니다. 
				</td>
			<tr>
			<%}else{ 
					for(QA qa : list){%>
			<tr>
				<td><%=qa.getQuestionIdx() %></td>
				<td><%=qa.getQuestionWriter() %></td>
				<td><a href="<%=request.getContextPath()%>/QA/detailPage?QANum=<%=qa.getQuestionIdx()%>"><%=qa.getQuestionTitle() %></a></td>
				<td>
					<%if(qa.getQuestionOriginalFilename()!=null) {%>
								<img src="<%=request.getContextPath()%>/images/file.png>" width="20" height="20">
							<%} %>
				</td>
				<td><%=qa.getQuestionEnrollDate() %></td>
				<td><%=qa.getQuestionReadCount()%></td>
				
			</tr>
			<%}
			}%>
			
		</table>
		
		<div id="pageBar">
        	<%=request.getAttribute("pageBar")%>
        </div>
	
	
	
</section>
</body>
</html>