<%--
  Created by IntelliJ IDEA.
  User: AlphaGoMK
  Date: 12/22/2018
  Time: 3:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Menu</title>
</head>
<body>

    <script type="text/javascript">
        function initTeacher(){
            window.location = "/AdminInitTeacher.jsp";
        }
        function initStu(){
            window.location = "/AdminInitStudent.jsp";
        }
        function addUser(){
            window.location.replace("/AddUser.jsp");
        }
        function findUser(){
            window.location = "/FindUser.jsp";
        }
    </script>

    <form action="selectAdminAction">
        <label>Select your action</label>
        <input type="button" name="ACT0" value="初始化老师" onclick="initTeacher()"/>
        <input type="button" name="ACT1" value="初始化学生" onclick="initStu()"/>
        <input type="button" name="ACT2" value="添加用户" onclick="addUser()"/>
        <input type="button" name="ACT4" value="查找用户" onclick="findUser()"/>
    </form>

    <br/>


</body>
</html>
