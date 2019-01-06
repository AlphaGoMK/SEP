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
            window.location = "/InitTeacher.jsp";
        }
        function initStu(){
            window.location = "/InitStudent.jsp";
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
        <input type="button" name="ACT0" value="Init Teacher" onclick="initTeacher()"/>
        <input type="button" name="ACT1" value="Init Student" onclick="initStu()"/>
        <input type="button" name="ACT2" value="Add User" onclick="addUser()"/>
        <input type="button" name="ACT4" value="Find User" onclick="findUser()"/>
    </form>

    <br/>


</body>
</html>
