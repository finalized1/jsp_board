<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includefile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function goPopup(){
	// 주소검색을 수행할 팝업 페이지를 호출합니다.
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	var pop = window.open("${path}/views/member/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	
	// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
    //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
}


function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.

		document.frmAdd.addr.value = roadAddrPart1; //도로명주소
		
		document.frmAdd.addrdetail.value = addrDetail; //상세주소
		
		document.frmAdd.zipcode.value = zipNo; //우편번호	
}

	function addCheck() {
		var email = frmAdd.email.value;
		var passwd = frmAdd.passwd.value;
		if (email==''){
			alert('이메일을 입력해주세요.');
			frmAdd.email.focus();
		}else if (passwd==''){
			alert('비밀번호를 입력하세요.');
			frmAdd.passwd.focus();
		}else{
			frmAdd.submit();
		}
	}
</script>
</head>
<body>
	<%@ include file="../header.jsp" %>
	<h2>회원 등록</h2>
	<!-- enctype="multipart/form-data" : 파일을 전송 -->
	<form name="frmAdd" action="${path}/member/add" method="post" enctype="multipart/form-data"> <!-- 단순문자열 전송 -->
		<table border="1">
			<tr>
				<th>이메일</th>
				<td> <input type="email" name="email" > </td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td> <input type="password" name="passwd"> </td>
			</tr>
			<tr>
				<th>주소</th>
				<td> 
					<!--api이용해서 주소창 호출-->
					우편번호 <input type="text" name="zipcode" size="5"> 
					<button type="button" onclick="goPopup()">검색</button>
					<hr>
					주소 <input type="text" name="addr" size="30">
					<hr> 
					상세주소 <input type="text" name="addrdetail" size="25"> 
				</td>
			</tr>
			<tr>
				<th>사진</th>
				<td> <input type="file" name="file"> </td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="button" onclick="addCheck()">등록</button>
					<button type="reset">취소</button>
				</td>		
			</tr>
		</table>
	</form>
</body>
</html>