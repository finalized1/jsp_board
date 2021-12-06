<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includefile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.reply{
		display: flex;
	}
	img {
		color: red;
	}
</style>
<script type="text/javascript">
	function removeCheck() {
		if ('${sessionScope.email}' != '${map.board.email}'){
			alert('권한이 없습니다.');
			return ;
		}
		var result = confirm('삭제하시겠습니까?');
		if (result){
			location.href = '${path}/board/remove?bnum=${map.board.bnum}';
		}
	}
	//수정폼으로 이동
	function modiformCheck() {
		//권한체크
		if ('${sessionScope.email}' == '${map.board.email}'){
			location.href='${path}/board/modiform?bnum=${map.board.bnum}'
		}else{
			alert('권한이 없습니다.');
		}
		
	}
	//댓글삭제
	//파라메터 : detail.jsp로 이동위해 두번호 다 넘겨줌
	// 	(rnum) :댓글의 번호
	//	(bnum) : 원본의 번호
	function replyremoveCheck(email, rnum) {
		//파라메터 (rnum) : 댓글의 번호, bnum은 원본의 번호
		if ('${sessionScope.email}' != '${map.board.email}'){
			alert('권한이 없습니다.');
			return ;
		}
		
		var result = confirm('삭제하시겠습니까?');
		if (result){
			location.href = '${path}/reply/remove?bnum=${map.board.bnum}&rnum=' + rnum;
		}
	}
	//댓글의 수정폼으로 이동
	function replymodiformCheck(email, bnum) {
		if ('${sessionScope.email}' == email){
			location.href='${path}/reply/modiform?bnum='+bnum;
		}else{
			alert('권한이없습니다');
		}
		
	}
</script>
</head>
<body>
	<%@ include file="../header.jsp" %>
	<h2>상세조회</h2>
	${map.board} <br>
	<hr>
	${map.bflist} <br>
	<hr>
	<table border="1">
		<tr>
			<th>번호</th>
			<td>${map.board.bnum}</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${map.board.email}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${map.board.subject}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td> <pre>${map.board.content}</pre> </td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<c:forEach var="boardfile" items="${map.bflist}">
					<li>${boardfile.filename} <button onclick="location.href='${path}/file/filedown?filename=${boardfile.filename}'">다운로드</button> </li>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td><pre>${map.board.readcnt}</pre></td>
		</tr>
		<tr>
			<th>등록일자</th>
			<td><fmt:formatDate value="${map.board.regidate}" pattern="yyyy-MM-dd HH:mm"/> </td>
		</tr>
		<tr>
			<th>수정일자</th>
			<td><fmt:formatDate value="${map.board.modidate}" pattern="yyyy-MM-dd HH:mm"/></td>
		</tr>
		<tr>
			<td colspan="2" align="center"> 
				<button onclick="modiformCheck()">수정</button> 
				<button onclick="removeCheck()">삭제</button> 
				<button onclick="location.href='${path}/views/board/replyadd.jsp?ref=${map.board.ref}&restep=${map.board.restep}&relevel=${map.board.relevel}'">댓글</button>
			</td>
		</tr>
	</table>
	<hr>
	<c:forEach var="reply" items="${map.rlist}">
		<div Class="reply">
			<div>
				<c:forEach var="i" begin="1" end="${reply.relevel}">
					<img alt="" src="${path}/views/images/1470493.png" width="20px">
				</c:forEach>
			</div>
			<div>
				  번호 : ${reply.bnum} <br>
				작성자 : ${reply.email} <br>
				  제목 : ${reply.subject} <br>
				  내용 : ${reply.content} <br>
			  작성일자 : <fmt:formatDate value="${reply.regidate}" pattern="yyyy-MM-dd HH:mm"/> <br>
			  수정일자 : <fmt:formatDate value="${reply.modidate}" pattern="yyyy-MM-dd HH:mm"/> <br>
			  <button onclick="location.href='${path}/views/board/replyadd.jsp?ref=${reply.ref}&restep=${reply.restep}&relevel=${reply.relevel}'">댓글</button>
			  <button onclick="replymodiformCheck('${reply.email}', ${reply.bnum})">수정</button>
			  <button onclick="replyremoveCheck('${reply.email}', ${reply.bnum})">삭제</button>
			</div>
		</div>
		<hr>
	</c:forEach>
	
</body>
</html>