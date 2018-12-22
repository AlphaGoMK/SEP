<%--
  Created by IntelliJ IDEA.
  User: AlphaGoMK
  Date: 12/16/2018
  Time: 4:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Teacher Menu</title>
</head>
<body>

    <%--<script type="text/javascript">--%>
        <%--function check(){--%>
            <%--window.alert(111);--%>
            <%--myform.action="check.action";--%>
            <%--myform.submit();--%>
        <%--}--%>
    <%--</script>--%>

    <script type="text/javascript">
        function issue(){
            window.location = "/Issue.jsp";
        }
        function check(){
            window.location = "/Check.jsp";
        }
        function score(){
            window.location.replace("/Score.jsp");
        }
    </script>

    <form method=post action="select">
        <label>select</label>
        <br/>
        <input type="button" name="ACT0" value="ISSUE" onclick="issue()"/>
        <br/>
        <input type="button" name="ACT1" value="CHECK" onclick="check()"/>
        <br/>
        <input type="button" name="ACT2" value="SCORE" onclick="score()"/>
    </form>


</body>
</html>


