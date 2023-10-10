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
	<form action="/member/save" method="post">
        <input type="text" name="memberEmail" placeholder="이메일" id="memberEmail" onblur="emailCheck()"> 
        <p id="check-result"></p>
        <input type="password" name="memberPassword" placeholder="비밀번호">
        <input type="text" name="memberName" placeholder="이름">
        <input type="text" name="memberAge" placeholder="나이">
        <input type="text" name="memberMobile" placeholder="전화번호">
        <input type="button" id="submit" value="회원가입버튼">
    </form>
</body>

	<script type="text/javascript">

	
	
	
	// 이메일 입력값을 가져오고,
	// 입력값을 서버로 전송하고 똑같은 이메일이 있는지 체크한 후
	// 사용 가능 여부를 이메일 입력창 아래에 표시
	    const emailCheck = function() {
	        const email = document.getElementById("memberEmail").value;
	        const checkResult = document.getElementById("check-result");
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
	                if (res == "ok") {
	                    console.log("사용가능한 이메일");
	                    checkResult.style.color = "green";
	                    checkResult.innerHTML = "사용가능한 이메일";
	                    $("#submit").removeAttr("disabled");
	                } else {
	                    console.log("이미 사용중인 이메일");
	                    checkResult.style.color = "red";
	                    checkResult.innerHTML = "이미 사용중인 이메일";
	                    $("#submit").attr("disabled", "disabled");
	                }
	            },
	            error: function(err) {
	                console.log("에러발생", err);
	            }
	        });
	    }
	</script>


</html>