<%--
  Created by IntelliJ IDEA.
  User: wangwei
  Date: 2017/10/4
  Time: 下午3:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>servlet async support</title>
</head>
<body>
    <script type="text/javascript" src="../assets/js/jquery.js"></script>
    <script type="text/javascript">

        deferred();

        function deferred(){
            $.get('defer',function (data) {

                console.log(data);
                deferred();//一次请求完成后再向后台发送请求
                
            })
        }
    </script>

</body>
</html>
