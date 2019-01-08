<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: AlphaGoMK
  Date: 12/23/2018
  Time: 1:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Result</title>
</head>
<body>

    User ID <s:property value="uid"/>
    <br/>
    User Type <s:property value="flag"/>
    <br/>
    User Name
    <%--default value--%>
    <input type="text" value="${usr.getName()}"
           onfocus="if(value=='${usr.getName()}'){value=''}"
           onblur="if(value==''){value='${usr.getName()}'}"/>


    <form action="EditUser">
        <input type="submit" value="Edit"/>
    </form>
    <form action="RemoveUser">
        <input type="submit" value="Remove"/>
    </form>



    <%--TODO: edit info, remove info--%>

</body>
</html>
