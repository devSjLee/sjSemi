<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp" %>

<%@ include file="/common/iconbar.jsp" %>
<%@page import="com.with.qa.model.vo.QA" %>
<%
	QA qa=(QA)request.getAttribute("qa");
%>

<style>
	#QA-container{width:70%;}
	#QA-container h2{text-align: center; margin-bottom:80px;}
	table#tbl-QA{width: 50%; border:1px black solid; border-collapse: collapse;
					margin-top:200px;margin-left:45%;}
	table#tbl-QA th
	{
		width:125px;
		border:1px solid;
		padding:5px 0;
		text-align:center;
	}
	table#tbl-QA td
	{
		border:1px solid;
		padding:5px 0 5px 10px;
		text-align:left;
	}

</style>
<section id="QA-container">
	<form action="<%=request.getContextPath() %>/QA/QAUpdateEnd" method="post">
		<table id="tbl-QA">
			<tr>
				<th>제목</th>
				<td>
					<input type="text" name="title" value="<%=qa.getQuestionTitle() %>">
					<input type="hidden" name="no" value="<%=qa.getQuestionIdx()%>">
				</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer" value="<%=qa.getQuestionWriter() %>" readonly></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<input type="file" name="upfile">
				</td>
			</tr>
			
			<tr>
				<th>내용</th>
				<td><textarea name="content" rows="10" cols="70"><%=qa.getQuestionContent() %></textarea></td>
			</tr>
			<tr>
				<th colspan="2">
					<input type="submit" value="수정완료">
					<button>삭제하기</button>
					<button id="list">목록</button>
				</th>
				
			</tr>
			
		</table>
	</form>
</section>
</body>
</html>