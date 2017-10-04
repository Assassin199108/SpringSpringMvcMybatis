<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wangwei
  Date: 2017/10/3
  Time: 下午1:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>SSE DEMO</title>


    <script type="text/javascript" src="<c:url value='../assets/js/jquery.js'/>"></script>

    <script type="text/javascript">

        /**
         * EventSource对象只有新式的浏览器才有，是SSE的客户端
         */
        if (!!window.EventSource){
            var source = new EventSource('push');
            s = '';

            /**
             * 添加SSE客户端监听，在此获得服务器端推送的消息
             */
            source.addEventListener('message',function(e){
                s+=e.data+"<br/>";
                $("#msgFromPush").html(s);
            });

            source.addEventListener('open',function(e) {
                console.log("连接打开");
            },false);

            source.addEventListener('error',function(e) {

                if (e.readyState == EventSource.CLOSED){
                    console.log("连接关闭");
                }else{
                    console.log(e.readyState);
                }

            },false);
        }else {
            console.log("你的浏览器不支持SSE");
        }

    </script>

</head>
<body>

    <div id="msgFromPush"></div>


</body>
</html>