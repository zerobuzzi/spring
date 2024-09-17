<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<title>list</title>


<style>
        #modal.modal-overlay {
            width: 100%;
            height: 100%;
            position: absolute;
            left: 0;
            top: 0;
            display: none;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            background: rgba(255, 255, 255, 0.25);
            box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.37);
            backdrop-filter: blur(1.5px);
            -webkit-backdrop-filter: blur(1.5px);
            border-radius: 10px;
            border: 1px solid rgba(255, 255, 255, 0.18);
        }
        #modal .modal-window {
            background: rgba( 69, 139, 197, 0.70 );
            box-shadow: 0 8px 32px 0 rgba( 31, 38, 135, 0.37 );
            backdrop-filter: blur( 13.5px );
            -webkit-backdrop-filter: blur( 13.5px );
            border-radius: 10px;
            border: 1px solid rgba( 255, 255, 255, 0.18 );
            width: 400px;
            height: 500px;
            position: relative;
            top: -100px;
            padding: 10px;
        }
        #modal .title {
            padding-left: 10px;
            display: inline;
            text-shadow: 1px 1px 2px gray;
            color: white;
            
        }
        #modal .title h2 {
            display: inline;
        }
        #modal .close-area {
            display: inline;
            float: right;
            padding-right: 10px;
            cursor: pointer;
            text-shadow: 1px 1px 2px gray;
            color: white;
        }
        
        #modal .content {
            margin-top: 20px;
            padding: 0px 10px;
            text-shadow: 1px 1px 2px gray;
            color: white;
        }
    </style>



</head>
<body>



	<div id="container">
        <h2>Lorem Ipsum</h2>
        <button id="btn-modal">모달 창 열기 버튼</button>
        <div id="lorem-ipsum"></div>
    </div>
    <div id="modal" class="modal-overlay">
        <div class="modal-window">
            <div class="title">
                <h2>모달</h2>
            </div>
            <div class="close-area">X</div>
            <div class="content">
                <p>가나다라마바사 아자차카타파하</p>
                <p>가나다라마바사 아자차카타파하</p>
                <p>가나다라마바사 아자차카타파하</p>
                <p>가나다라마바사 아자차카타파하</p>
                
            </div>
        </div>
    </div>
    <script>
    /* const loremIpsum = document.getElementById("lorem-ipsum")
    fetch("https://baconipsum.com/api/?type=all-meat&paras=200&format=html")
        .then(response => response.text())
        .then(result => loremIpsum.innerHTML = result)
    const modal = document.getElementById("modal") */
    
    
    function modalOn() {
        modal.style.display = "flex"
    }
    function isModalOn() {
        return modal.style.display === "flex"
    }
    function modalOff() {
        modal.style.display = "none"
    }
    
    // 버튼 클릭시 모달 켜지기
    const btnModal = document.getElementById("btn-modal")
    btnModal.addEventListener("click", e => {
        modalOn()
    })
    
    // x버튼 누르면 모달 꺼지기
    const closeBtn = modal.querySelector(".close-area")
    closeBtn.addEventListener("click", e => {
        modalOff()
    })
    
    // 모달 바깥 영역 클릭시 모달 꺼짐
    modal.addEventListener("click", e => {
        const evTarget = e.target
        if(evTarget.classList.contains("modal-overlay")) {
            modalOff()
        }
    })
    
    // esc누르면 모달 꺼지기
    window.addEventListener("keyup", e => {
        if(isModalOn() && e.key === "Escape") {
            modalOff()
        }
    }) 
            
    </script>







	<table>
		<tr>
			<!-- <th>id</th> -->
			<th>title</th>
			<th>writer</th>
			<th>date</th>
			<th>hits</th>
		</tr>
		<c:forEach items="${boardList}" var="board">
			<tr>
				<%-- <td>${board.id}</td> --%>
				<td><a href="/board?id=${board.id}&page=${pageMaker.cri.page}&searchType=${searchType }&searchName=${searchName }">${board.boardTitle}</a>
				</td>
				<td>${board.boardWriter}</td>
				<td><fmt:formatDate value="${board.boardCreatedTime}"
						pattern="yyyy-MM-dd HH:mm" /></td>
				<%--   <td>${board.boardCreatedTime}</td> --%>
				<td>${board.boardHits}</td>
			</tr>
		</c:forEach>
	</table>

	<div class="search">
		<form action="/board/list">
			<select name="searchType">
				<option value="1" <c:if test="${searchType eq 1}"> selected</c:if>>작성자</option>
				<option value="2" <c:if test="${searchType eq 2}"> selected</c:if>>글제목</option>
			</select> <input type="text" name="searchName" value="${searchName }">
			<button type="submit">검색</button>
		</form>
	</div>
	<div>
		
		<!-- 페이지 인덱스를 처리한다. -->
		<c:choose>
			<%-- 현재 페이지가 1페이지면 이전 글자만 보여줌 --%>
			<c:when test="${pageMaker.cri.page<=1}">
				<span>[이전]</span>
			</c:when>
			<%-- 1페이지가 아닌 경우에는 [이전]을 클릭하면 현재 페이지보다 1 작은 페이지 요청 --%>
			<c:otherwise>
				<a href="/board/list?page=${pageMaker.cri.page-1}&searchType=${searchType }&searchName=${searchName }">[이전]</a>
			</c:otherwise>
		</c:choose>

		<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}"
			var="i" step="1">
			<c:choose>
				<%-- 요청한 페이지에 있는 경우 현재 페이지 번호는 텍스트만 보이게 --%>
				<c:when test="${i eq pageMaker.cri.page}">
					<span>${i}</span>
				</c:when>

				<c:otherwise>
					<%-- <a href="/board/list?page=${i} ">${i}</a> --%>
					<%-- <a href="/board/list?page=${i}">${i}</a> --%>
					 <a href="/board/list?page=${i}&searchType=${searchType }&searchName=${searchName } ">${i}</a> 
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:choose>
			<c:when test="${!pageMaker.next}">
			
				<span>[다음]</span>
			</c:when>
			<c:otherwise>
				<a href="/board/list?page=${pageMaker.cri.page+1}&searchType=${searchType }&searchName=${searchName }">[다음]</a>
			</c:otherwise>
		</c:choose>


	</div>
</body>
</html>