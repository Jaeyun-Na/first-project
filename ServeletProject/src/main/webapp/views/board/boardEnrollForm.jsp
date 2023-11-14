<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

	<%@ include file="../common/head.jsp" %>

</head>
<body>

	<%@ include file="../common/header.jsp" %>

	<%@ include file="../common/nav.jsp" %>


  <section id="post-form">
    <h2>새 글 작성</h2>
    <form>
        <label for="title">제목:</label>
        <input type="text" id="title" name="title" required>
        
        <label for="author">글쓴이:</label>
        <input type="text" id="author" name="author" required>
        
        <label for="content">내용:</label>
        <textarea id="content" name="content" rows="4" required></textarea>
        
        <button type="submit">작성</button>
    </form>
  </section>


  
	<%@ include file="../common/footer.jsp" %>
 
</body>
</html>