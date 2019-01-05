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
