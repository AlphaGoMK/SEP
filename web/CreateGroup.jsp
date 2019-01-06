<%@ page import="sep.Entity.Course" %><%--
  Created by IntelliJ IDEA.
  User: zhangsz
  Date: 12/26/2018
  Time: 9:45 PM
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
    <script src="./bootstrap-4.0.0-dist/js/jquery-3.3.1.min.js"></script>
</head>

<body>
<s:set var="has_group" value="has_group"/>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-2">
            </br>
            <span class="badge badge-default">欢迎,<s:property value="#session.USER_NAME"/></span>
            </br>
            <ul class="nav flex-column nav-pills">
                <li class="nav-item" >
                    <a class="nav-link" href="/sep/Action/showCourse.action">课程信息</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled">
                        作业信息
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="/sep/Action/CreateGroup.action">创建小组</a>
                </li>
            </ul>
        </div>
        <div class="col-md-7">
            <br/>
            <br/>
            <form role="form">
                <div class="form-group">
                    <label for="groupIdInput">
                        小组编号
                    </label>
                    <input type="text" class="form-control" id="groupIdInput" readonly="readonly"
                           placeholder="<s:property value="0"/>"/>
                </div>
                <s:bean name="org.apache.struts2.util.Counter" var="counter">
                    <s:param name="first" value="1" />
                    <s:param name="last" value="3" />
                    <s:iterator>
                        <div class="form-group">
                            <label for="studentIdInput<s:property value="#counter - 1"/>">
                                组员<s:property value="#counter - 1"/>
                            </label>
                            <input type="text" class="form-control" id="studentIdInput<s:property value="#counter - 1"/>"/>
                        </div>
                    </s:iterator>
                </s:bean>
                <div class="form-group">
                    <label for="contactInput">
                        Password
                    </label>
                    <input type="text" class="form-control" id="contactInput"/>
                </div>
                <div class="form-group">
                    <label>
                        <label for="grpLeaderInput">
                            组长
                        </label>
                        <input type="text" class="form-control" id="grpLeaderInput"/>
                    </label>
                </div>
                <button type="submit" class="btn btn-primary">
                    Submit
                </button>
            </form>
        </div>
        <div class="col-md-3">
            <br/>
            <br/>
            <dl>
                <dt>课程编号</dt>
                <dd><s:property value="courseInfo.courseId"/></dd>
                <dt>课程名</dt>
                <dd><s:property value="courseInfo.name"/></dd>
                <dt>创建日期</dt>
                <dd><s:property value="courseInfo.date"/></dd>
                <dt>课程介绍</dt>
                <dd><s:property value="courseInfo.description"/></dd>
                <dt>教师</dt>
                <dd><s:property value="courseInfo.teacherName"/></dd>
            </dl>
        </div>
    </div>
    <s:debug/>
</div>
</body>

</html>
