<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: zhangsz
  Date: 1/6/2019
  Time: 11:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
                    <a class="nav-link " href="javascript:void(0)" id="checkHomeBtn">批改作业</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link " href="/sep/Action/showStuList.action" id="showList">查看学生列表</a>
                </li>
            </ul>
        </div>

        <div class="col-md-10" id="showStuListDiv" style="display: block;">
            </br>
            </br>
            <table class="table table-striped">
                <thead>
                <tr class="row">
                    <th class="col-1">姓名</th>
                    <th class="col-1">班级</th>
                    <th class="col-1">学号</th>
                    <th class="col-7">作业成绩</th>
                    <th class="col-2">组长打分</th>
                </tr>
                </thead>

                <tbody>
                <tr class="row">
                    <s:iterator value="stuInfo" var="info">
                        <td class="col-1"><s:property value="#info.name"/></td>
                        <td class="col-1"><s:property value="#info.stuId"/></td>
                        <td class="col-1"><s:property value="#info.classid"/></td>
                        <td class="col-7">
                            <s:iterator value="hwList" var="hw" status="status">
                                <s:property value="#hw.name"/>:
                                <s:set var="score" value="#info.hwScore[#status.index]"/>
                                <s:if test="#score == -1.0">
                                     暂无评分
                                </s:if>
                                <s:else>
                                    <s:property value="#score"/>
                                </s:else>
                            </s:iterator>
                        </td>
                        <td class="col-2">
                            <s:set var="contrib" value="#info.grpContrib"/>
                            <s:if test="#contrib == -1.0">
                                暂无评价
                            </s:if>
                            <s:elseif test="#contrib == -2.0">
                                该生尚未加入小组
                            </s:elseif>
                            <s:else>
                                <s:property value="#info.grpContrib"/>
                            </s:else>
                        </td>
                    </s:iterator>
                </tr>
                </tbody>
            </table>
        </div>
        </div>

    </div>
    <s:debug/>
</div>
</body>
</html>
