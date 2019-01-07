<%--
  Created by IntelliJ IDEA.
  User: AlphaGoMK
  Date: 12/16/2018
  Time: 4:17 PM
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

    <%--datetimepicker--%>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://unpkg.com/gijgo@1.9.11/js/gijgo.min.js" type="text/javascript"></script>
    <link href="https://unpkg.com/gijgo@1.9.11/css/gijgo.min.css" rel="stylesheet" type="text/css" />

</head>

<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-2">
            </br>
            <span class="badge badge-default">
                欢迎,<s:property value="#session.USER_NAME"/>
                <s:if test="%{#session.SELECTED == true}">
                    <s:property value="#session.COURSE_ID"/>
                </s:if>
                <s:else>
                    请选择课程
                </s:else>
            </span>

            </br>
            <ul class="nav flex-column nav-pills">
                <li class="nav-item" >

                    <a class="nav-link " href="javascript:void(0);" onclick="showCourse();">
                        课程信息
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="javascript:void(0);" onclick="createCourse()">
                        创建课程
                    </a>
                </li>
            </ul>
        </div>

        <div class="col-md-10" id="showCourseDiv" style="display: block;">
            </br>
            </br>
            <table class="table table-striped">
                <thead>
                <tr class="row">
                    <th class="col-1">课程编号</th>
                    <th class="col-1">课程名</th>
                    <th class="col-8">课程简介</th>
                </tr>
                </thead>

                <tbody>
                <tr class="row">
                    <s:iterator value="courseList" var="CourseInfo">
                        <td class="col-2">
                            <a href="/sep/Action/teacherviewgrp.action?courseId=<s:property value="courseId"/>">
                                <s:property value="courseId"/>
                            </a>

                        </td>
                        <td class="col-2">
                            <a href="/sep/Action/teacherviewgrp.action?courseId=<s:property value="courseId"/>">
                                <s:property value="name"/>
                            </a>
                        </td>
                        <td class="col-8">
                            <a href="/sep/Action/teacherviewgrp.action?courseId=<s:property value="courseId"/>">
                                <s:property value="description"/>
                            </a>
                        </td>

                    </s:iterator>
                </tr>
                </tbody>
            </table>
        </div>

        <div class="col-md-10" id="createCourseDiv" style="display: none;">
            </br>
            </br>

            <form class="form-horizontal" role="form" action="TeacherCreateCourse">
                <div class="form-group">
                    <label for="firstname" class="col-sm-2 control-label">课程名</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="courseName" id="firstname" placeholder="课程的名字">
                    </div>
                </div>
                <div class="form-group">
                    <label for="lastname" class="col-sm-2 control-label">课程描述</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="description" id="lastname" placeholder="描述课程">
                    </div>
                </div>
                <div class="form-group">
                    <label for="maxcrew" class="col-sm-2 control-label">最大组员数</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="maxcrew" id="maxcrew" placeholder="最多几人同组">
                    </div>
                </div>
                <div class="form-group">
                    <label for="mincrew" class="col-sm-2 control-label">最小组员数</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="mincrew" id="mincrew" placeholder="最少几人同组">
                    </div>
                </div>
                <div class="form-group">
                    <label for="prefix" class="col-sm-2 control-label">组名前缀</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="prefix" id="prefix" >
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">创建</button>
                    </div>
                </div>
            </form>

        </div>

    </div>
    <s:debug/>
</div>



<%--<link href="{% static 'bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css' %}" rel="stylesheet">--%>

<%--<script src="{% static 'bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js' %}"></script>--%>
<%--<script src="{% static 'bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js' %}"></script>--%>


<script type="text/javascript">
    function createCourse(){
        var createGroupDiv = document.getElementById('createCourseDiv');
        createGroupDiv.style.display = "block";
        document.getElementById('showCourseDiv').style.display = "none";
        document.getElementById('setHomeDiv').style.display = "none";
        document.getElementById('setHomeBtn').setAttribute("class","nav-link");
        document.getElementById('setStuBtn').setAttribute("class","nav-link");
        document.getElementById('checkHomeBtn').setAttribute("class","nav-link");
        document.getElementById('showList').setAttribute("class","nav-link");

        flushCourseFn();
    }

    function showCourse() {
        var showCourseDiv = document.getElementById('showCourseDiv');
        showCourseDiv.style.display = "block";
        document.getElementById('createCourseDiv').style.display = "none";
        document.getElementById('setHomeDiv').style.display = "none";
    }

    function flushCourseFn()
    {


    }

    function setHome(){
        document.getElementById('setHomeDiv').style.display = "block";
        document.getElementById('createCourseDiv').style.display = "none";
        document.getElementById('showCourseDiv').style.display = "none";
    }

    // 创建完成/选择完成后在出现btn

    $('#ex1').slider({
        formatter: function(value) {
            return 'Current value: ' + value;
        }
    });

</script>

</body>
</html>


