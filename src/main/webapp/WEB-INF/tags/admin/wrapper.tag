<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Admin Panel wrapper tag" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Admin Panel - Filmoteka</title>
    <script src="<c:url value="/resources/js/jquery-3.2.1.js"/>"></script>
    <script src="<c:url value="/resources/js/vue.js"/>"></script>
    <script src="<c:url value="/resources/js/admin.js"/>"></script>
</head>
<body>
<div id="panel">
    <div><a href="/admin/films">Films</a></div>
    <div><a href="/admin/actors">Actors</a></div>
    <div><a href="/admin/directors">Directors</a></div>
    <div><a href="/admin/categories">Categories</a></div>
    <div><a href="/admin/networks">Networks</a></div>
    <div><a href="/admin/studios">Studios</a></div>
</div>
<jsp:doBody/>
</body>
</html>