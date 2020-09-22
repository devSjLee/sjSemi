<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp" %>
<%@ include file="/common/iconbar.jsp" %>
<%
	String msg=(String)request.getAttribute("msg");
	String loc=(String)request.getAttribute("loc");
%>
<section>
	<script>
		alert("<%=msg%>");
		location.replace('<%=request.getContextPath()%><%=loc%>');
	</script>
</section>
</body>
</html>