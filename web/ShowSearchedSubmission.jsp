<%--
  Created by IntelliJ IDEA.
  User: zhangsz
  Date: 1/7/2019
  Time: 2:15 PM
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
            <span class="badge badge-default">欢迎,<s:property value="#session.USER_NAME"/></span>
            </br>
            <ul class="nav flex-column nav-pills">
                <li class="nav-item" >

                    <a class="nav-link " href="javascript:void(0);" onclick="showGroup();">
                        查看小组
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="javascript:void(0);" >
                        创建课程
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link " href="javascript:void(0)" onclick="setHome()" id="setHomeBtn">设置作业</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link " href="javascript:void(0)" id="setStuBtn">设置学生名单</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link " href="/sep/Action/listSubmission.action" id="checkHomeBtn">批改作业</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link " href="/sep/Action/showStuList.action" id="showList">查看学生列表</a>
                </li>
            </ul>
        </div>

        <div class="col-md-10" id="setHomeDiv">
            </br>
            </br>
            <%--搜索栏--%>
            <form class="form-inline" action="searchSubmission.action">
                <div class="form-group">
                    <label for="hwNameInput">作业名</label>
                    <input type="text" class="form-control" name="hwName" id="hwNameInput" placeholder="请输入作业名">
                </div>
                <div class="form-group">
                    <label for="startTimeInput">开始时间</label>
                    <input type="date" class="form-control" name="startTime" id="startTimeInput" placeholder="开始时间">
                </div>
                <div class="form-group">
                    <label for="endTimeInput">结束时间</label>
                    <input type="date" class="form-control" name="endTime" id="endTimeInput" placeholder="结束时间">
                </div>
                <div class="form-group">
                    <label for="grpIdInput">小组编号</label>
                    <input type="text" class="form-control" name="grpId" id="grpIdInput" placeholder="请输入小组编号">
                </div>
                <button type="submit" class="btn btn-default">搜索</button>
            </form>
            <s:fielderror/>
            <%--显示栏--%>
            </br>
            <table class="table table-striped" style="word-break:break-all; word-wrap:break-word;">
                <thead>
                <tr class="row">
                    <th class="col-1">作业名</th>
                    <th class="col-1">提交时间</th>
                    <th class="col-1">组名</th>
                    <th class="col-7">文件列表</th>
                    <th class="col-1">提交人</th>
                    <th class="col-1">成绩</th>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="submitList" var="sublist" status="status">
                    <tr class="row">
                        <th class="col-1">
                            <a href="javascript:void(0)">
                                <s:property value="#sublist.homeworkname"/>
                            </a>
                        </th>
                        <th class="col-1">
                            <a href="javascript:void(0)">
                                <s:property value="#sublist.date"/>
                            </a>
                        </th>
                        <th class="col-1">
                            <a href="javascript:void(0)">
                                <s:property value="#sublist.grpId"/>
                            </a>
                        </th>
                        <th class="col-7">
                            <a href="javascript:void(0)">
                                <s:iterator value="uploadedFiles[#status.index]">
                                    <s:property/>
                                </s:iterator>
                            </a>
                        </th>
                        <th class="col-1">
                            <a href="javascript:void(0)">
                                <s:property value="uploaderName[#status.index]"/>
                            </a>
                        </th>
                        <th class="col-1">
                            <a href="javascript:void(0)">
                                <s:set value="scores[#status.index]" var="score"/>
                                <s:if test="#score == -1.0">
                                    暂无评分
                                </s:if>
                                <s:else>
                                    <s:property value="#score"/>
                                </s:else>
                            </a>
                        </th>
                    </tr>
                </s:iterator>
                </tbody>
            </table>

        </div>

    </div>
    <s:debug/>
</div>
</body>
<script type="text/javascript">

</script>
</html>
