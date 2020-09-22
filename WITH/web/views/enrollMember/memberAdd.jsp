<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.with.member.model.vo.Member" %>
<%
	List<Member> list=(List)request.getAttribute("checkDuplicate");

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- <script src="http://code.jquery.com/jquery-3.5.1.min.js"></script> -->
    <link href="https://fonts.googleapis.com/css2?family=Dokdo&family=Gugi&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Sunflower:wght@300&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/d41f04266a.js" crossorigin="anonymous"></script>
    <script src="<%=request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/memberAdd.css" type="text/css">
    <title>애견회원가입</title>
    
    <script>
        $(function(){
            $("#addMember").on("hover",function(e){
                $("#addMember").css("opacity","1");
            }
            ,function(e){
                $("#addMember").css("opacity","0.7");
            });
        })
        let eyeCount=0;
        $(function(){
            $("#eyeOn").on("click",function(e){
                let pw=$("pw");
                console.log("클릭!");
                if(eyeCount==0){
                    // 눈을 감앗을때
                    eyeCount++;
                    alert("눈을 감을거야");
                    $("#eyeOn").attr("class","fas fa-eye-slash");
                    $("#pw").attr("type","text");
                }else{
                    // 눈을 떳을때
                    eyeCount=0;
                    alert("눈을 뜰거야");
                    $("#pw").attr("type","password");
                    $("#eyeOn").attr("class","fas fa-eye");
                }
            });
        })
        
    </script>
</head>
<body>
    <section>
        <div id="bgVideo">
                <video id="video" preload="auto" autoplay="true" loop="loop" muted="muted" src="" volume="0"> 
                </video>
        </div>
        <div id="client">
            <p id="logo">W I T H</p>
            <form name="memberAddFrm"action="<%=request.getContextPath() %>/enrollMember" method="post">
            <ul id="icon">
                <li>
                    <p class="addFont">ID</p>
                    <input type="text" id="id" class="tag" name="Id">
                    <button id="checkId" type="button" onclick="check_duplicate_id();"><i id="checkid-icon"class="fas fa-user-check"></i></button>
                    <input type="hidden" id="duplicate" name="duplicate" value="false">
                </li>
                <li>
                    <p class="addFont">PW</p>
                    <input id="pw" type="password" id="pw" class="tag" name="Password">
                    <div id="eye-icon">
                        <i id="eyeOn" class="fas fa-eye"></i>
                    </div>
                </li>
                <li>
                    <p class="addFont">PW Check</p>
                    <input type="password" id="pwC" class="tag" placeholder="한 번 더 적어주세요">
                </li>
                <li>
                    <p class="addFont">Nick Name</p>
                    <input type="text" id="nickName" class="tag" placeholder="닉네임" name="Nickname">
                </li>
                <li>
                    <p class="addFont">Phone-N</p>
                    <input type="text" id="phone" class="tag" placeholder="핸드폰 번호" name="Phone">
                </li>
                <li>
                    <p class="addFont">Address</p>
                    <input type="text" id="address" class="tag" placeholder="사는 곳" name="Address">
                </li>
                <li>
                    <p class="addFont">EMAIL</p>
                    <input type="text" id="email" class="tag" name="Email">
                </li>
                <li>
                    <div id="buttons">
                        <button type="button" onclick="location.replace('<%=request.getContextPath()%>/')" id="toMain">메인메뉴</button>
                        <button id="addMember">가입</button>
                    </div>
                </li>
            </ul>
            </form>
        </div>
    </section>
    <script>
    	$(function(){
    		let nick=$("#nickName").val();
    		let phone=$("#phone").val();
    		let email=$("#email").val();
            $("#email").blur(e=>{
        		let email=$(e.target).val();
        		let regEmail = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
        		if(!regEmail.test(email)){
        			alert("이메일 형식으로 입력해주세요.");
        			$(e.target).val("");
    	    	}
   	    	})
   	    	$("#pw").blur(e=>{
   	    		let pw=$(e.target).val();
   	    		let regPw = /(?=.*\d{1,16})(?=.*[~`!@#$%\^&*()-+=]{1,16})(?=.*[a-zA-Z]{1,16}).{8,16}$/;
   	    		if(!regPw.test(pw)){
   	    			alert("비밀번호는 8~16자 이내 영문자, 숫자 ,특수기호를 포함해주세요");
   	    			$(e.target).val("");
   	    		}
   	    	})
   	    	$("#nickName").blur(e=>{
   	    		let nick=$(e.target).val();
   	    		let regNick=/[a-zA-z가-힣0-9ㄱ-ㅎㅏ-ㅠ]{2,}/;
   	    		if(!regNick.test(nick)){
   	    			alert("닉네임은 특수기호를 제외하고 2글자 이상으로 만들어주세요");
   	    			$(e.target).val("");
   	    		}
   	    	})
   	    	$("#phone").blur(e=>{
   	    		let phone=$(e.target).val();
   	    		let regPhone = /^01([0|1|6|7|8|9]?)([0-9]{3,4})([0-9]{4})$/;
   	    		if(regPhone.test(phone)){
   	    			alert("-를 포함하여 전화번호를 입력해주세요.");
   	    			$(e.target).val("");
   	    		}
   	    	})
    	});
    	$(function(){
    		$("#pwC").blur(e=>{
    		let pw=$("#pw").val();
    		let pwC=$(e.target).val();
    		if(pw.trim()!=pwC.trim()){
    			alert("비밀번호가 일치하지 않습니다.");
    			$("#pw").val("");
    			$(e.target).val("");
	    		}
	    	})
	    });
        let video=["회원가입영상1.mp4","회원가입영상2.mp4","회원가입영상3.mp4","회원가입영상4.mp4","회원가입영상5.mp4"];
        $(function(){
            let play=Math.floor((Math.random()*5));
            console.log(play);
            let screenVideo=$("#video"); 
            screenVideo.attr("src",'<%=request.getContextPath()%>/views/enrollMember/'+video[play]);
        });
        
        function check_duplicate_id(){
        	let id=$("#id").val().trim();
        	<%for(Member m : list){%>
        		if(id=="<%=m.getId()%>"){
        			alert("중복된 아이디입니다! 다시 입력해주세요!");
        			$("#checkid-icon").css("color","lightcoral");
        			$("#duplicate").val("false");
        			return;
        		}
        	<%}%>
        	alert("등록 가능한 아이디명입니다!");
        	$("#duplicate").val("true");
        	$("#checkid-icon").css("opacity","0.7");
        	$("#checkid-icon").css("transition","4s");
        	$("#checkid-icon").css("color","lime");
        }
    </script>
</body>
</html>