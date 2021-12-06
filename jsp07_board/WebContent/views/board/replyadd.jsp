<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includefile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function replyCheck() {
		var email = frmReply.email.value;
		var subject = frmReply.subject.value;
		var content = frmReply.content.value;
		console.log(email);
		console.log(subject);
		console.log(content);
		if (email==''){
			alert('이메일을 입력하세요.');
			frmReply.email.focus();
		}else if (subject==''){
			alert('제목을 입력해주세요.');
			frmReply.subject.focus();
		}else if (content==''){
			alert('내용을 입력하세요.');
			frmReply.content.focus();
		}else{
			frmReply.submit();
		}
	}
</script>
</head>
<body>
	<%@ include file="../header.jsp" %>
	<h2>댓글작성</h2>
	<form name="frmReply" action="${path}/reply/add" method="post">
		부모그룹(ref)<input type="text" value="${param.ref}" name="ref" size="2">
		부모순서(restep)<input type="text" value="${param.restep}" name="restep" size="2">
		부모레벨(relevel)<input type="text" value="${param.relevel}" name="relevel" size="2">
		
		<table border="1">
			<tr>
				<th>이메일</th>
				<td> <input type="text" name="email" value="${sessionScope.email}" readonly> </td>
			</tr>
			<tr>
				<th>제목</th>
				<td> <input type="text" name="subject"> </td>
			</tr>
			<tr>
				<th>내용</th>
				<td> <textarea rows="5" cols="22" name="content"></textarea> </td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="button" onclick="replyCheck()">등록</button>
					<button type="reset">취소</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>