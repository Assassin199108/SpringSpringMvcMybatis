<%--
  Created by IntelliJ IDEA.
  User: wangwei
  Date: 2017/9/18
  Time: 下午10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8">
    <title>upload page</title>
</head>
<body>
<div class="upload">
    <form action="upload" enctype="multipart/form-data" method="post">
        <input type="file" name="file"/><br/>
        <input type="submit" value="上传"/>
    </form>
</div>
</body>
</html>