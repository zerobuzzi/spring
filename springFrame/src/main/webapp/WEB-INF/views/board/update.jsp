<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>update.jsp</title>
    
     <script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
</head>
<body>
<div>
    <form action="/board/update" method="post" id="frm" name="updateForm">
        <input type="hidden" name="id" value="${board.id}" readonly>
        <input type="text" name="boardWriter" value="${board.boardWriter}" readonly>
        <input type="text" name="boardPass" id="boardPass" placeholder="비밀번호">
        <input type="text" name="boardTitle" value="${board.boardTitle}">
        <textarea name="boardContents" cols="30" rows="1">${board.boardContents}</textarea>
        <input type="button" value="수정" id="button">
        <!-- onclick="updateReqFn()" -->
    </form>
</div>
</body>
<script>
	$(function(){
		$("#button").click(function(){
			const passDB = '${board.boardPass}';
		    const passInput = $("#boardPass").val();

			//cont passInput = '${"#boardPass"}.val()';
			
			if (passInput == passDB) {
				//frm.action = "/board/update";
				frm.submit();
	        } else {
	            alert("비밀번호가 일치하지 않습니다!!");
	        }
		})
	})

    /* const updateReqFn = function(){
        const passInput = document.getElementById("boardPass").value;
        const passDB = '${board.boardPass}';
        if (passInput == passDB) {
            document.updateForm.submit();
        } else {
            alert("비밀번호가 일치하지 않습니다!!");
        }
    } */
</script>
</html>