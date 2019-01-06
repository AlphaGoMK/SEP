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
<<<<<<< HEAD
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

    </div>

</body>
</html>





=======
    <title>学生作业管理系统</title>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style type="text/css">
            .inputfile {
                width: 0.1px;
                height: 0.1px;
                opacity: 0;
                overflow: hidden;
                position: absolute;
                z-index: -1;
            }
            .inputfile + label {
                font-size: 1em;
                font-weight: 500;
                color: black;
                background-color: transparent;
                display: inline-block;
            }
            .inputfile:focus + label,
            .inputfile + label:hover {
                background-color: transparent;
                color: #005cbf;
            }
            #createGroupDiv {
                display: none;
            }

            #groupAndHomeDiv {
                display: block;
            }

            .nameSuggestList {
                display: none;
            }

            .nameSuggestList li{
                height:24px;
                overflow:hidden;
                line-height:24px;
                background:#FFFFFF;
                cursor:default;
            }
            .nameSuggestList li.hover{
                background:#DDDDDD;
            }
        </style>
        <link href="./bootstrap-4.0.0-dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="./bootstrap-4.0.0-dist/js/jquery-3.3.1.min.js"></script>
    </head>
</head>
<body>

<div class="row">
    <div class="col-md-4">
        <br/>
    </div>
    <div class="col-md-4">
        <h1 class="display-3" align="center">登录</h1>
        <form action="login">
            <label for="uid">用户名</label>
            <input class='form-control' type="text" name="uid" id="uid"/>
            <label for="passwd">密码</label>
            <input class='form-control' type="password" name="passwd" id="passwd"/>
            <%--<input class='form-control' type="submit" value="login"/>--%>
            <button type="submit" class="btn btn-primary">Login</button>
        </form>
        <s:fielderror style="color: red"/>
    </div>
    <div class="col-md-4">
        <br/>
    </div>
</div>

</body>
</html>
>>>>>>> 9578e81dda81e0abe7284c6a49a0b7bc56245938
