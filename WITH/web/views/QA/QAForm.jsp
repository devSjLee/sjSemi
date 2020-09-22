<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp" %>

<%@ include file="/common/iconbar.jsp" %>

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
	div#imgWrap{border:1px red solid; width:200px;height:100px;}
	div
	input#upfile{margin-bottom:5px;}
</style>
<section id="QA-container">
	<form action="<%=request.getContextPath()%>/qa/qaFormEnd" method="post" enctype="multipart/form-data">
	
		<table id="tbl-QA">
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" id="title"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer" value="<%=loginUser.getNickname()%>" readonly></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td><input type="file" name="upfile" id="upfile"><div id="imgWrap"><img id="img"></div></td>
				
				
			</tr>
			
			<tr>
				<th>내용</th>
				<td><textarea name="content" rows="10" cols="70"></textarea></td>
			</tr>
			<tr>
				<th colspan="2">
					<input type="submit" value="등록">
					<input type="reset" value="취소">
				</th>
				
			</tr>
			
		</table>
		
	</form>
	
</section>

<script>



$(document).ready(function(){
    $("#upfile").on("change",preview);
 });
 
 function preview(e){
    let files=e.target.files;
    let filesArr=Array.prototype.slice.call(files);
    console.log(files);
    filesArr.forEach(function(f){
       if(!f.type.match("image.*")){
          alert("확장자가 이미지 형태가 아닙니다.");
          return;
       }
       
       
       
       let reader=new FileReader();
       reader.onload=function(e){
          $("#img").attr("src",e.target.result);
       }
       reader.readAsDataURL(f);
    });
 };
</script>
</body>
</html>