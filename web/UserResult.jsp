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
User Type <s:property value="flag"/>

<%--TODO: additional info to be added--%>

<%--TODO: QUESTIONED?--%>
<%--default value--%>
<input type="text" value="${uname}"
       onfocus="if(value=='${uname}'){value=''}"
       onblur="if(value==''){value='${uname}'}"/>

<form action="FindResultAct">
    <input type="button" name="ACT0" value="Edit" onclick="editInfo"/>
    <input type="button" name="ACT1" value="Remove" onclick="removeInfo"/>
</form>

<%--TODO: edit info, remove info--%>

</body>
</html>
