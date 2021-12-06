<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includefile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function replymodifyCheck() {
		var bnum = frmReplyModify.bnum.value;
		var email = frmReplyModify.email.value;
		var subject = frmReplyModify.subject.value;
		var content = frmReplyModify.content.value;
		console.log(bnum);
		console.log(email);
		console.log(subject);
		console.log(content);
		if (bnum==''){
			alert('번호를 입력하세요.');
			frmReplyModify.bnum.focus();
		}else if (email==''){
			alert('이메일을 입력하세요.');
			frmReplyModify.email.focus();
		}else if (subject==''){
			alert('제목을 입력해주세요.');
			frmReplyModify.subject.focus();
		}else if (content==''){
			alert('내용을 입력하세요.');
			frmReplyModify.content.focus();
		}else{
			frmReplyModify.submit();
		}
	}	
</script>
</head>
<body>
	<h2>댓글수정</h2>
		<form name="frmReplyModify" action="${path}/reply/modify" method="post">
			<input type="hidden" name="ref" value="${board.ref}">
			<table border="1">
				<tr>
					<th>번호</th>
					<td> <input type="text" name="bnum" value="${board.bnum}" readonly ></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td> <input type="text" name="email" value="${board.email}" readonly></td>
				</tr>
				<tr>
					<th>제목</th>
					<td> <input type="text" name="subject" value="${board.subject}"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td> <textarea rows="5" cols="25" name="content">${board.content}</textarea> </td>
				</tr>
				<tr>
					<td colspan="2" align="center"> 
						<button type="button" onclick="replymodifyCheck()">수정</button>
						<button type="reset">취소</button>
					</td>
				</tr>
			</table>
		
		</form>	
</body>
</html>