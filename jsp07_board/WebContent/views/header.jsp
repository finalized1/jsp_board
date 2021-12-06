<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./include/includefile.jsp" %>
<%@ include file="./include/msg.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	header {
		display: flex;
		justify-content: space-around;
	}
</style>
</head>
<body>

	<header>
		<div> <img alt="" src="${path}/img/lion.png"> </div>
		<div> 이젠컴퓨터 </div>
		<div> 
			<span><a href="${path}/member/myinfo">${sessionScope.email}</a></span>
			<c:if test="${empty sessionScope.email}">
				<a href="${path}/views/member/login.jsp">로그인</a> 
				<a href="${path}/views/member/add.jsp">회원등록</a>
			</c:if>
			 <c:if test="${not empty sessionScope.email}">
				<a href="${path}/member/logout">로그아웃</a> 
			</c:if>
		</div> 
	</header>	
	<hr>
	<nav>
		<a href="${path}/views/home.jsp">홈</a>
		<a href="${path}/board/list">리스트</a>
		<a href="${path}/views/board/add.jsp">등록</a>
	</nav>
	<hr>
</body>
</html>