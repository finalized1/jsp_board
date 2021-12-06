<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includefile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function modiCheck() {
		var bnum = frmModify.bnum.value;
		var email = frmModify.email.value;
		var subject = frmModify.subject.value;
		var content = frmModify.content.value;
		console.log(bnum);
		console.log(email);
		console.log(subject);
		console.log(content);
		if (bnum==''){
			alert('번호를 입력하세요.');
			frmModify.bnum.focus();
		}else if (email==''){
			alert('이메일을 입력하세요.');
			frmModify.email.focus();
		}else if (subject==''){
			alert('제목을 입력해주세요.');
			frmModify.subject.focus();
		}else if (content==''){
			alert('내용을 입력하세요.');
			frmModify.content.focus();
		}else{
			frmModify.submit();
		}
	}	
</script>
</head>
<body>
	<%@ include file="../header.jsp" %>
	<h2>수정</h2>
	<form name="frmModify" action="${path}/board/modify" method="post" enctype="multipart/form-data">
		<table border="1">
			<tr>
				<th>번호</th>
				<td> <input type="text" name="bnum" value="${map.board.bnum}" readonly > </td>
			</tr>
			<tr>
				<th>이메일</th>
				<td> <input type="text" name="email" value="${map.board.email}" readonly> </td>
			</tr>
			<tr>
				<th>제목</th>
				<td> <input type="text" name="subject" value="${map.board.subject}"> </td>
			</tr>
			<tr>
				<th>내용</th>
				<td> <textarea rows="5" cols="22" name="content" >${map.board.content}</textarea> </td>
			</tr>
			<tr>
				<th>파일</th>
				<td>
					<c:forEach var="boardfile" items="${map.bflist}">
						<li>${boardfile.filename} <input type="checkbox" name="filedel" value="${boardfile.fnum}">삭제 </li>
					</c:forEach>  
					<input type="file" name="file1"> <br>
					<input type="file" name="file2"> <br>
					<input type="file" name="file3"> <br>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="button" onclick="modiCheck()">등록</button>
					<button type="reset">취소</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>