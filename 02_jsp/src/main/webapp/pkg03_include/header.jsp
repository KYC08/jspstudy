<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
request.setCharacterEncoding("UTF-8");
String title = request.getParameter("title");
%>
<title><%=title%></title>
<!-- custom css -->
<!-- 뒤에 붙여서 새로고침 때마다 적용하기 ?dt=<%-- =System.currentTimeMillis() --%> -->
<link rel="stylesheet"
  href="<%=request.getContextPath()%>/assets/css/header.css?dt=<%=System.currentTimeMillis()%>">
<link rel="stylesheet"
  href="<%=request.getContextPath()%>/assets/css/body.css?dt=<%=System.currentTimeMillis()%>">
<link rel="stylesheet"
  href="<%=request.getContextPath()%>/assets/css/footer.css?dt=<%=System.currentTimeMillis()%>">
</head>
<body>
  <div class="header-wrap">
    <div>
      <a href="<%=request.getContextPath()%>/pkg03_include/main1.jsp">main1</a>
      <a href="<%=request.getContextPath()%>/pkg03_include/main2.jsp">main2</a>
    </div>
  </div>
  <div class="body-wrap">