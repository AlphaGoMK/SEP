<%--
  Created by IntelliJ IDEA.
  User: AlphaGoMK
  Date: 12/22/2018
  Time: 2:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="./bootstrap-4.0.0-dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-2">
            </br>
            <span class="badge badge-default">欢迎,<s:property value="#session.USER_NAME"/></span>
            </br>
            <ul class="nav flex-column nav-pills">
                <li class="nav-item" >
                    <a class="nav-link active" href="/sep/Action/showCourse.action">课程信息</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled">
                        作业信息
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled">创建小组</a>
                </li>
            </ul>
        </div>

        <div class="col-md-10">
            </br>
            </br>
            <table class="table table-striped" style="word-break:break-all; word-wrap:break-word;">
                <thead>
                <tr class="row">
                    <th class="col-1">课程编号</th>
                    <th class="col-1">课程名</th>
                    <th class="col-1">创建时间</th>
                    <th class="col-8">课程简介</th>
                    <th class="col-1">授课教师</th>
                </tr>
                </thead>

                <tbody>
                <tr class="row">
                    <s:iterator value="courses" var="currentCourseInfo">
                        <td class="col-1">
                            <a href="/sep/Action/showGroupAndHome.action?courseId=<s:property value="courseId"/>">
                                <s:property value="courseId"/>
                            </a>
                        </td>
                        <td class="col-1">
                            <a href="/sep/Action/showGroupAndHome.action?courseId=<s:property value="courseId"/>">
                                <s:property value="name"/>
                            </a>
                        </td>
                        <td class="col-1">
                            <s:property value="date"/>
                        </td>

                        <td class="col-8">
                            <s:property value="description"/>
                        </td>
                        <td class="col-1">
                            <s:property value="teacherName"/>
                        </td>
                    </s:iterator>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <s:debug/>
</div>
</body>
</html>
