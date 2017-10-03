<%--
  Created by IntelliJ IDEA.
  User: wangwei
  Date: 2017/10/3
  Time: 上午9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>HttpMessageConverter Demo</title>
</head>
<body>
    <div id="resp"></div>
    <input type="button" value="请求" onclick="req();"/>

<script type="text/javascript" src="../assets/js/jquery.js"></script>
<script type="text/javascript">
    function req(){
        $.ajax({
            url:"convert",
            data:"1-wangwei",
            type:'POST',
            contentType:"application/x-wisely",
            success:function (data) {
                $("#resp").html(data);
            }
        });
    }
</script>
</body>
</html>
