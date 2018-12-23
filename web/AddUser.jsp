<%--
  Created by IntelliJ IDEA.
  User: AlphaGoMK
  Date: 12/23/2018
  Time: 1:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add User</title>
</head>
<body>


    <form action="AddUser">
        Id: <input type="text" name="usr.id"/>
        Name: <input type="text" name="usr.name"/>
        Password: <input id="pwd1" type="text" name="usr.password"/>
        Confirm Password: <input id="pwd2" type="text" name="repwd"/>
        <input type="submit" value="ADD" />
    </form>

</body>
</html>
