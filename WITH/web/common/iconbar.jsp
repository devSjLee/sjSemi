<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.with.member.model.vo.Member"%>
<%	
	Member loginUser=(Member)session.getAttribute("loginUser");
	
%>
<script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/iconbarstyle.css" type="text/css">
<script src="https://kit.fontawesome.com/d41f04266a.js" crossorigin="anonymous"></script>
    
    <section>
      
        
        <div id="iconBox"> 
        <!-- 로그인창 DIV -->
            <%if(loginUser==null) {%>
           <form action="<%=request.getContextPath() %>/login" method="post">    
            <div id="login-client">
	                <label class="login-show">W I T H</label><br>
	                <ul class="login-show">
	                    <li id="login-option-one"><p id="login-title-one">I&nbsp;D</p><input type="text" id="id" name="Id" placeholder="아이디를 입력해주세요."></li>
	                    <li id="login-option-two"><p id="login-title-two">PW</p><input type="password" id="pw" name="Password"></li>
	                </ul>
	                <div id="login-find" class="login-show">
	                    <a href="#" id="find-id" class="find">Find ID</a><label class="find">|</label><a href="#" id="find-pw" class="find">Find PW</a>
	                </div><br>
	                <div id="login-buttons" class="login-show">
	                <input type="submit" value="로그인" id="login-button">	                
	                <button type="button" id="member-add" onclick="location.replace('<%=request.getContextPath()%>/memberAdd');">회원가입</button><br>
	         		</div>
	        	</form>
	        <%}else {%>
	        	<ul class="logined-show">
	        		<li><label><%=loginUser.getNickname()%>님 정보</label></li>
	        		<li><label>회원등급 : <%=loginUser.getGrade()%></label></li>
	      
	        		<li><button id="toMyPage">마이페이지</button></li>
	        		<li><button id="logout" type="button" onclick="location.replace('<%=request.getContextPath()%>/logout');">로그아웃</button></li>
	        	</ul>
	        <%} %>
        </div>
        	<div id="iconZe" class="icon">
        		<i id="home" class="fa fa-home"></i>
        		<label id="iconZe-word">Home</label>
        	</div>
            <div id="iconFi" class="icon">
                <% if(loginUser==null){%>
                <i id="login" class="fa fa-sign-in"></i>
                <label id="iconFi-word">Login</label>
                <%}else { %>
                <i id="login" class="fas fa-file"></i>
                <label id="iconFi-word">My Page</label>
                <%} %>
            </div>
            <div id="iconSe" class="icon">
                <i id="check-pet" class="fa fa-paw"></i> 
                <label id="iconSe-word">My Pet</label>
            </div>
            <div id="iconTh" class="icon">
                <i id="calender" class="fa fa-calendar-check-o"></i>
                <label id="iconTh-word">Today</label>
            </div>
            <div id="iconFo" class="icon">
                <i id="message" class="fa fa-envelope"></i>
                <label id="iconFo-word">Message</label>
            </div>
        </div>
    </section>
    <script>
    
    $(function(){ // 로그인 아이콘 눌럿을때
        let LoginCount=0;
        $("#iconFi").click(function(){
            if(LoginCount==0){
                $("#login-client").css("opacity","1");
                $("#login-client").css("width","350");
                $(".login-show").css("opacity","1");
                $(".login-show").css("transition","10s");
                LoginCount++;
            }else{
                $("#login-client").css("opacity","0");
                $("#login-client").css("width","1");
                $(".login-show").css("transition","0.2s");
                LoginCount=0;
            }
        });
    })
    $(function(){
    	<%if(loginUser==null){%>
    	$("#login").css("margin-left","12px");
    	$("#login").css("transition","0.1s");
    	<%}else {%>
    	$("#login").css("margin-left","15px");
    	$("#login").css("transition","0.1s");
    	<%}%>
    })
    $(function(){ // 마우스가 들어갓을때  Home 아이콘 
        $("#iconZe").hover(function(){
            $("#iconZe").css("width","135px");
            $("#iconZe").css("text-align","left");
            $("#home").css("color","skyblue");
            $("#home").css("opacity","0.8");
            $("#iconZe-word").css("opacity","1");
            $("#iconZe").css("transition","2s");
            $("#login-client").css("right","150px");
        },
        function(){ // 마우스가 나갓을때
            $("#iconZe").css("width","50px");
            $("#home").css("color","black");
            $("#home").css("opacity","1");
            $("#iconZe-word").css("opacity","0");
            $("#login-client").css("right","65px");
        });
    })
    $(function(){ // 마우스가 들어갓을때 Login 아이콘
        $("#iconFi").hover(function(){
        	<%if(loginUser==null){%>
            $("#iconFi").css("width","135px");
            $("#iconFi").css("text-align","left");
            $("#login").css("color","skyblue");
            $("#login").css("opacity","0.8");
            $("#login").css("transition","2s");
            $("#iconFi-word").css("opacity","1");
            $("#iconFi").css("transition","2s");
            $("#login-client").css("right","150px");
            <%}else {%>
            $("#iconFi").css("width","175px");
            $("#iconFi").css("text-align","left");
            $("#login").css("color","skyblue");
            $("#login").css("opacity","0.8");
            $("#iconFi-word").css("opacity","1");
            $("#iconFi").css("transition","2s");
            $("#login-client").css("right","190px");
            <%}%>
        },
        function(){ // 마우스가 나갓을때
            $("#iconFi").css("width","50px");
            $("#login").css("color","black");
            $("#login").css("opacity","1");
            $("#iconFi-word").css("opacity","0");
            $("#login-client").css("right","65px");
        });
    })
    $(function(){ // 마우스가 들어갓을때  My Pet 아이콘
        $("#iconSe").hover(function(){
            $("#iconSe").css("width","160px");
            $("#iconSe").css("text-align","left");
            $("#check-pet").css("color","skyblue");
            $("#check-pet").css("opacity","0.8");
            $("#iconSe-word").css("opacity","1");
            $("#iconSe").css("transition","2s");
            $("#login-client").css("right","175px");
        },
        function(){ // 마우스가 나갓을때
            $("#iconSe").css("width","50px");
            $("#check-pet").css("color","black");
            $("#check-et").css("opacity","1");
            $("#iconSe-word").css("opacity","0");
            $("#login-client").css("right","65px");
        });
    })
    $(function(){ // 마우스가 들어갓을때  Calendar 아이콘
        $("#iconTh").hover(function(){
            $("#iconTh").css("width","140px");
            $("#iconTh").css("text-align","left");
            $("#calender").css("color","skyblue");
            $("#calender").css("opacity","0.8");
            $("#iconTh-word").css("opacity","1");
            $("#iconTh").css("transition","2s");
            $("#login-client").css("right","175px");
        },
        function(){ // 마우스가 나갓을때
            $("#iconTh").css("width","50px");
            $("#calender").css("color","black");
            $("#calender").css("opacity","1");
            $("#iconTh-word").css("opacity","0");
            $("#login-client").css("right","65px");
        });
    })
    $(function(){ // 마우스가 들어갓을때  Message 아이콘
        $("#iconFo").hover(function(){
            $("#iconFo").css("width","180px");
            $("#iconFo").css("text-align","left");
            $("#message").css("color","skyblue");
            $("#message").css("opacity","0.8");
            $("#iconFo-word").css("opacity","1");
            $("#iconFo").css("transition","2s");
            $("#login-client").css("right","200px");
        },
        function(){ // 마우스가 나갓을때
            $("#iconFo").css("width","50px");
            $("#message").css("color","black");
            $("#message").css("opacity","1");
            $("#iconFo-word").css("opacity","0");
            $("#login-client").css("right","65px");
        });
    })
    
    
    </script>