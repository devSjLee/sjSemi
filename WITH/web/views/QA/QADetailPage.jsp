<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp" %>

<%@ include file="/common/iconbar.jsp" %>
<%@page import="com.with.qa.model.vo.*,java.util.List" %>
<script	src="<%=request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
<%
	QA qa=(QA)request.getAttribute("qa");
	List<QAComment> list=(List)request.getAttribute("list");
	
%>

<style>
	#QA-container{width:80%; margin-left:13%; margin-bottom:60px;}
	
	table#tbl-QA{width: 50%; border:1px black solid; border-collapse: collapse;
					margin-top:200px;margin-left:15%;min-width: 600px;}
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
	
	 /*댓글테이블*/
	div#comment-container{text-align:center;margin-top:60px; border:1px gray solid; width:40%; margin-left:25%; min-width: 600px;}
	div#comment-container>p{float:left;margin-left:18px;margin-top:10px;margin-bottom:10px;}
	div#comment-editor form textarea{margin-bottom: 10px;}
    table#tbl-comment{width:580px; margin:0 auto; border-collapse:collapse; clear:both; margin-left:25%; margin-top:10px;} 
    table#tbl-comment tr td{border-bottom:1px solid;  padding:5px; text-align:left; line-height:120%;}
    table#tbl-comment tr td:first-of-type{padding: 5px 5px 5px 30px;}
    table#tbl-comment tr td:last-of-type {text-align:left; width: 100px;}
    table#tbl-comment tr td:first-of-type>p{padding-bottom: 5px;}
    table#tbl-comment tr td:last-of-type>p{padding-bottom: 5px;}
    table#tbl-comment button.btn-reply{display:none;}
    table#tbl-comment button.btn-delete{display:none;}
    
    table#tbl-comment tr:hover button.btn-reply{display:inline;}
    table#tbl-comment tr:hover button.btn-delete{display:inline;}
    table#tbl-comment tr.level2 {color:gray; font-size: 14px;}
    table#tbl-comment p.comment-writer { font-size:14px}
    table#tbl-comment p.comment-date { font-size:10px}
    table#tbl-comment tr.level2 td:first-of-type{padding-left:80px;background-color:#F5F5F5	;}
    table#tbl-comment tr.level2 p.comment-writer {font-size:14px}
    table#tbl-comment tr.level2 p.comment-date {font-size:10px}
    /*답글관련*/
    table#tbl-comment textarea{margin: 4px 0 0 0;}
    table#tbl-comment button.btn-insert2{width:60px; height:23px; color:white; background:#3300ff; position:relative; top:-5px; left:10px;}

</style>
<section id="QA-container">
	
	<div id="QA-container">
		<table id="tbl-QA">
			<tr>
				<th>글번호</th>
				<td>
					<h3><%=qa.getQuestionIdx() %></h3>
					
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td><h2><%=qa.getQuestionTitle() %></h2></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%=qa.getQuestionWriter() %></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<%if(qa.getQuestionOriginalFilename()!=null){ %>
	     			
	     			
	     					<a href="javascript:fn_fileDownload('<%=qa.getQuestionOriginalFilename()%>','<%=qa.getQuestionRenamedFilename()%>');">
								<img src="<%=request.getContextPath()%>/images/file.png>" width="20" height="20">
							</a>
							<script>
								function fn_fileDownload(oriname,rename){
									
									const url="<%=request.getContextPath()%>/qa/qaFileDown";
									let oName=encodeURIComponent(oriname);
									location.assign(url+'?oName='+oName+'&rName='+rename);
									
								}
							</script>
					<%} %>
				</td>
			</tr>
			
			<tr>
				<th>내용</th>
				<td><%=qa.getQuestionContent() %></td>
			</tr>
			 <tr>
				<th colspan="3">
					<%if(loginUser!=null) {%>
						<%if(loginUser.getId().equals("admin")||qa.getQuestionWriter().equals(loginUser.getNickname())){ %>
						<button type="button" onclick="updateQA();">수정하기</button>
						<button type="button" onclick="removeQA();">삭제하기</button>
						<%}
					}%>
					<button id="list">목록</button>
				</th>
				
			</tr> 
			
		</table>
		
		</div>
		
		<hr>
		
		<%if(loginUser!=null){ %>
		<div id="comment-container">
			<p><%=loginUser.getId() %></p>
			<div class="comment-editor">
				<form action="<%=request.getContextPath()%>/qa/qaCommentInsert" method="post">
					<input type="hidden" name="qaRef" value="<%=qa.getQuestionIdx()%>">
					<input type="hidden" name="qaCommentWriter" value="<%=loginUser!=null?loginUser.getId():""%>">
					<input type="hidden" name="qaCommentLevel" value="1">
					<input type="hidden" name="qaCommentRef" value="0">
					<textarea name="qaCommentContent" cols="80" rows="5"></textarea>
					<hr>
					<button type="submit" id="btn-insert">등록</button>
				</form>
			</div>
		</div>
		<%} else{%>
			<div id="comment-container">
			<p></p>
			<div class="comment-editor">
				<form action="<%=request.getContextPath()%>/qa/qaCommentInsert" method="post">
					<input type="hidden" name="qaRef" value="<%=qa.getQuestionIdx()%>">
					<input type="hidden" name="qaCommentWriter" value="<%=loginUser!=null?loginUser.getId():""%>">
					<input type="hidden" name="qaCommentLevel" value="1">
					<input type="hidden" name="qaCommentRef" value="0">
					<textarea name="qaCommentContent" cols="80" rows="5" placeholder="댓글을 작성하시려면 로그인을 해주세요."></textarea>
					<hr>
					<button type="submit" id="btn-insert">등록</button>
				</form>
			</div>
		</div>
		<%} %>
	
	
	 <div>
		<table id="tbl-comment">
		<%for(QAComment qc : list) {
		
			if(qc.getQaCommentLevel()==1){
		%>
			<tr class="level1">
				<td>
					<p class="comment-writer"><%=qc.getQaCommentWriter()%></p>
					<p class="comment-date"><%=qc.getQaCommentDate()%></p>
					
					
					<%=qc.getQaCommentContent() %>
				</td>
				<td>
					<button class="btn-reply" value="<%=qc.getQaCommentNo()%>">답글</button>
					 <%if(loginUser.getId().equals(qc.getQaCommentWriter())||
							loginUser.getId().equals("admin")) %> 
					<button class="btn-delete" value="<%=qc.getQaCommentNo()%>">삭제</button>
				</td>
			</tr>
			 <%}
			else{ %> 
			<tr class="level2">
				<td colspan="3">
					<p><%=qc.getQaCommentWriter()%></p>
					<p><%=qc.getQaCommentDate()%></p>
					
					<p><%=qc.getQaCommentContent() %></p>
				</td>
			</tr>
		 	<% }
		} %> 
		</table>
	</div> 

	
	
	
	
</section>
<script>
$(".btn-delete").click(e=>{
	$.ajax({
		url:"<%=request.getContextPath()%>/ajax/deleteComment",
		type:"post",
		dataType:"json",
		success:function(data){
			console.log(data);
		}
		
	});
});

	
	$("[name=qaCommentContent]").focus(e=>{
		if(<%=loginUser==null%>){
			alert("로그인 후 이용해주세요!");
			
		}
	});
	
	//답글 클릭시 이벤트
	$(".btn-reply").click(e=>{
		<%if(loginUser!=null){%>
			let tr=$("<tr>");
			let form=$(".comment-editor>form").clone();
			form.find("textarea").attr("rows","1");
			form.find("[name=qaCommentLevel]").val("2");
			form.find("[name=qaCommentRef]").val(e.target.value);
			form.find("button").addClass("btn-insert2");
			let td=$("<td>");
			tr.append(td.append(form));
			tr.insertAfter($(e.target).parents("tr"));
			$(e.target).off("click"); 
		 <%}%> 
	});
	
	
	
})
	
	

	

function removeQA(){
	window.location.replace('<%=request.getContextPath()%>/QA/QARemove?QANum=<%=qa.getQuestionIdx()%>');
	}

	function updateQA(){
	window.location.replace('<%=request.getContextPath()%>/QA/QAUpdate?QANum=<%=qa.getQuestionIdx()%>');
	}
	
	$("#list").click(e=>{
		location.replace('<%=request.getContextPath()%>/QA/QAList');
	});
	
	
</script>		
	
	
</body>
</html>
