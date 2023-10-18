<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<title>list</title>
</head>
<body>
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