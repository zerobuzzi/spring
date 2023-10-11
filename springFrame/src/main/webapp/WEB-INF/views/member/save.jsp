<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;  charset=UTF-8">
	<title>Home</title>
	
	<script
  src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="crossorigin="anonymous"></script>
</head>
<body>
	<form action="/member/save" id="join_form" method="post">
        <input type="text" name="memberEmail" placeholder="이메일" id="memberEmail" onblur="emailCheck()"> 
        <p id="check-result"></p>
        <input type="password" name="memberPassword" id="pass" placeholder="비밀번호">
        <input type="text" name="memberName" id="name" placeholder="이름">
        <input type="text" name="memberAge" id="age" placeholder="나이">
        <input type="text" name="memberMobile" id="phone" placeholder="전화번호">
        <input type="submit" id="submit"  value="회원가입버튼">
    </form>
</body>

	<script type="text/javascript">
	
	
	$(document).ready(function(){
		$('#join_form').submit(function(event){
			var email = $('#memberEmail').val();
			var pass = $('#pass').val();
			var name = $('#name').val();
			var age = $('#age').val();
			var phone = $('#phone').val();
			
			if(email == ""){
				alert("아이디를 입력하세요");
				$('#email').focus();
				event.preventDefault();
			}else if(pass == ""){
				alert("비밀번호를 입력하세요");
				$('#pass').focus();
				event.preventDefault();
			}else if(name == ""){
				alert("이름을 입력하세요");
				$('#name').focus();
				event.preventDefault();
			}else if(age == ""){
				alert("나이를 입력하세요");
				$('#age').focus();
				event.preventDefault();
			}else if(phone == ""){
				alert("폰 번호를 입력하세요");
				$('#phone').focus();
				event.preventDefault();
			}
		});
	});
	
	// 이메일 입력값을 가져오고,
	// 입력값을 서버로 전송하고 똑같은 이메일이 있는지 체크한 후
	// 사용 가능 여부를 이메일 입력창 아래에 표시
	    const emailCheck = function(e) {
	        const email = document.getElementById("memberEmail").value;
	        const checkResult = document.getElementById("check-result")
	        const submit = document.getElementById("submit")
	        console.log("입력한 이메일", email);
	        $.ajax({
	            // 요청방식: post, url: "email-check", 데이터: 이메일
	            type: "post",
	            url: "/member/email-check",
	            data: {
	                "memberEmail": email
	            },
	            success: function(res) {
	                console.log("요청성공", res);
	                if(email.length > 0){
	                	 if (res == 0) {
	 	                    console.log("사용가능한 이메일");
	 	                    checkResult.style.color = "green";
	 	                    checkResult.innerHTML = "사용가능한 이메일";
	 	                    $("#submit").removeAttr("disabled");
	 	                } else if(res == 1){
	 	                    console.log("이미 사용중인 이메일");
	 	                    checkResult.style.color = "red";
	 	                    checkResult.innerHTML = "이미 사용중인 이메일";
	 	                   // $("#submit").attr("disabled", "disabled");
	 	                    $(submit).click(function(e) {
	 	                	   alert("중복된 아이디 입니다");
	 	                      e.preventDefault();
	 	                  }); 
	 	                   
	 	                }
	                }else{
	                	 checkResult.innerHTML = " ";
	                }
	               
	            },
	            error: function(err) {
	                console.log("에러발생", err);
	            }
	        });
	    }
	</script>


</html>