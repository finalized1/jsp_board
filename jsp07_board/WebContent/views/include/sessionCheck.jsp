<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./includefile.jsp" %>

<script>
	if ('%{sessionScope.email}' == ''){
		alert('로그인을 해주세요.');
		location.href = '${path}/views/member/login.jsp';
	}
</script>