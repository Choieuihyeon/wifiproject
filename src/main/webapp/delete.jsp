<%@ page import="com.example.wifiproject8.service.HistoryService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<%
    HistoryService historyService = new HistoryService();
    int result = historyService.deleteHistory(Integer.parseInt(request.getParameter("id")));

%>
<script>
    location.href="history.jsp";
</script>
</body>
</html>
