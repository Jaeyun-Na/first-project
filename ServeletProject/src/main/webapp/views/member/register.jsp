<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
   <%@ include file="../common/head.jsp" %>
   <script src="/resources/js/member/register.js"></script>
</head>
<body>
   <%@ include file="../common/header.jsp" %>
   <%@ include file="../common/nav.jsp" %>

  <section>
    <div class="signup-form">
        <h2>회원가입</h2>
        <form action="/register.do" method="post">
          <div class="input-container2">
        </div>
        <div class="input-container2">
          <label for="new-username">이름:</label>
          <input type="text" id="new-username" name="new-username" onkeyup="nameCheck()" required>
          <span id="nameMsg"></span>
        </div>


        <br>
        <div class="input-container2">
          <label for="new-userid">아이디:</label>
          <input type="text" id="new-userid" name="new-userid" onkeyup="duplicateId2()" required>
          <span id="idcheck"></span>
        </div>
        <br>
        <div class="input-container2">
          <label for="new-password">비밀번호:</label>
          <input type="password" id="new-password" name="new-password" onkeyup="validatePassword()"required>
          <span id="passwordMsg"></span>
          </div>
          <br>
          <div class="input-container2">
            <label for="confirm-password">비밀번호 확인:</label>
            <input type="password" id="confirm-password" name="confirm-password"  onkeyup="validatePassword()" required>
          <span id="passwordChkMsg"></span>
          </div>
  <br>
          <button type="submit">회원가입</button>
        </form>
      </div>
  </section>

   <%@ include file="../common/footer.jsp" %>
</body>
</html>
<script>
	//아이디 중복 검사
	function duplicateId2(){
		const id = document.getElementById("new-userid").value;
		const idcheck = document.getElementById("idcheck");
		
		$.ajax({
			type: "POST",
			url: "/duplicateId.do",
			data: {id : id},
			success : function(response){		
				if(response === 'true'){
					idcheck.style.color = "red";
					idcheck.innerHTML = '사용 불가능';
				}else{
					idcheck.style.color = "green";
					idcheck.innerHTML = '사용 가능';
				}
			},
			error : function(response){
			}
		})
	}
	//이름 유효성 검사
	  function validateEmail() {   
          const emailRegex = /^[가-힣]{2,}$/;
          const email = document.getElementById("new-userid").value;
          const emailMsg = document.getElementById("nameMsg");

          if(email == "") {
              emailMsg.innerHTML = "이름을 입력해주세요.";
              emailMsg.style.color = "red";
          } else if(emailRegex.test(email)) {
              emailMsg.innerHTML = "가입가능";
              emailMsg.style.color = "green";
          } else {
              emailMsg.innerHTML = "정책에 맞게 입력해주세요.";
              emailMsg.style.color = "red";
          }

      }
	//패스워드 검사
      function validatePassword() {

          const passwordRegex = /^(?=.*[a-zA-Z])(?=.*[@$!%*?&\#])[A-Za-z\d@$!%*?&\#]{6,20}$/;
          const password = document.getElementById("new-password").value;
          const msg = document.getElementById("passwordMsg");
          
          if(password == "") {
              msg.innerHTML = "비밀번호를 입력하세요.";
              msg.style.color = "red";
          } else if(passwordRegex.test(password)) {
              msg.innerHTML = "사용 가능한 비밀번호 입니다.";
              msg.style.color = "green";
          } else {
              msg.innerHTML = "패스워드 정책에 맞지 않습니다.";
              msg.style.color = "red";
          }
          
          const passwordChk = document.getElementById("confirm-password").value;
          const msg2 = document.getElementById("passwordChkMsg");

          if(password === passwordChk) {
              msg2.innerHTML = "패스워드가 동일합니다.";
              msg2.style.color = "green";
          } else {
              msg2.innerHTML = "패스워드가 동일하지 않습니다..";
              msg2.style.color = "red";
          }
	}
</script>
