<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
Created by IntelliJ IDEA.
User: wangwei
Date: 2017/9/18
Time: 下午10:29
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<pre>
    页面未找到


Failed URL: ${url2}
Exception: ${exception.message}
<c:forEach items="${exception.stackTrace}" var="ste">${ste}<br/></c:forEach>

</pre>
</body>
</html>