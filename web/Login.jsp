<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: AlphaGoMK
  Date: 12/16/2018
  Time: 4:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        @import url(http://fonts.googleapis.com/css?family=Roboto);

        /****** LOGIN MODAL ******/
        .loginmodal-container {
            padding: 30px;
            max-width: 350px;
            width: 100% !important;
            background-color: #F7F7F7;
            margin: 0 auto;
            border-radius: 2px;
            box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
            overflow: hidden;
            font-family: roboto;
        }

        .loginmodal-container h1 {
            text-align: center;
            font-size: 1.8em;
            font-family: roboto;
        }

        .loginmodal-container input[type=submit] {
            width: 100%;
            display: block;
            margin-bottom: 10px;
            position: relative;
        }

        .loginmodal-container input[type=text], input[type=password] {
            height: 44px;
            font-size: 16px;
            width: 100%;
            margin-bottom: 10px;
            -webkit-appearance: none;
            background: #fff;
            border: 1px solid #d9d9d9;
            border-top: 1px solid #c0c0c0;
            /* border-radius: 2px; */
            padding: 0 8px;
            box-sizing: border-box;
            -moz-box-sizing: border-box;
        }

        .loginmodal-container input[type=text]:hover, input[type=password]:hover {
            border: 1px solid #b9b9b9;
            border-top: 1px solid #a0a0a0;
            -moz-box-shadow: inset 0 1px 2px rgba(0,0,0,0.1);
            -webkit-box-shadow: inset 0 1px 2px rgba(0,0,0,0.1);
            box-shadow: inset 0 1px 2px rgba(0,0,0,0.1);
        }

        .loginmodal {
            text-align: center;
            font-size: 14px;
            font-family: 'Arial', sans-serif;
            font-weight: 700;
            height: 36px;
            padding: 0 8px;
            /* border-radius: 3px; */
            /* -webkit-user-select: none;
            user-select: none; */
        }

        .loginmodal-submit {
            /* border: 1px solid #3079ed; */
            border: 0px;
            color: #fff;
            text-shadow: 0 1px rgba(0,0,0,0.1);
            background-color: #4d90fe;
            padding: 17px 0px;
            font-family: roboto;
            font-size: 14px;
            /* background-image: -webkit-gradient(linear, 0 0, 0 100%,   from(#4d90fe), to(#4787ed)); */
        }

        .loginmodal-submit:hover {
            /* border: 1px solid #2f5bb7; */
            border: 0px;
            text-shadow: 0 1px rgba(0,0,0,0.3);
            background-color: #357ae8;
            /* background-image: -webkit-gradient(linear, 0 0, 0 100%,   from(#4d90fe), to(#357ae8)); */
        }

        .loginmodal-container a {
            text-decoration: none;
            color: #666;
            font-weight: 400;
            text-align: center;
            display: inline-block;
            opacity: 0.6;
            transition: opacity ease 0.5s;
        }

        .login-help{
            font-size: 12px;
        }
    </style>
</head>


<body>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->


    <div class="loginmodal-container">
        <h1>教务系统</h1><br>
        <form action="login">
            <label for="username">用户名</label> <input type="text" id="username" name="uid" placeholder="用户ID">
            <label for="password">密码</label> <input type="password" id="password" name="passwd" placeholder="密码">
            <input type="submit" class="login loginmodal-submit" value="登入">
        </form>
	<s:fielderror style="color: red"/>
    </div>

</body>
</html>





<%--<%@ page language="java" contentType="text/html; charset=ISO-8859-1"--%>
         <%--pageEncoding="ISO-8859-1"%>--%>
<%--<%@ taglib prefix="s" uri="/struts-tags"%>--%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"--%>
<%--"http://www.w3.org/TR/html4/loose.dtd">--%>
<%--<html>--%>
<%--<head>--%>
    <%--<title>Hello World</title>--%>
<%--</head>--%>

<%--<body>--%>
<%--<h1>Hello World From Struts2</h1>--%>
<%--<form action="hello">--%>
    <%--<label for="name">Please enter your name</label><br/>--%>
    <%--<input type="text" name="name"/>--%>
    <%--<input type="submit" value="Say Hello"/>--%>
<%--</form>--%>
<%--</body>--%>
<%--</html>--%>
