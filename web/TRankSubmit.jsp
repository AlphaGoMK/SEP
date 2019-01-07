<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: China
  Date: 1/7/2019
  Time: 4:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <ul class="nav flex-column nav-pills">
                <li class="nav-item" >
                    <a class="nav-link disabled" href="javascript:void(0);" onclick="showGroup();">
                        查看小组
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="javascript:void(0);" >
                        创建课程
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="javascript:void(0)" onclick="setHome()" id="setHomeBtn">设置作业</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="javascript:void(0)" id="setStuBtn">设置学生名单</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link disabled" href="javascript:void(0)" id="checkHomeBtn">批改作业</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="javascript:void(0)" id="showList">查看学生列表</a>
                </li>
            </ul>
        </div>
        <div class="col-md-10">
            <br/>
            <br/>
            <div class="col-md-9">
                <h3 class="text-primary">
                    已提交的作业
                </h3>
                <br/>
                <br/>

                <div class="row" id="showSubmitDiv">
                    <div class="col-md-3">
                        <h5 class="text-muted">
                            作业名
                        </h5>
                        <br/>
                        <h5 class="text-muted">
                            文件名
                        </h5>
                        <br/>
                        <h5 class="text-muted">
                            提交人
                        </h5>
                        <br/>
                        <h5 class="text-muted">
                            提交时间
                        </h5>
                    </div>
                    <div class="col-md-9">
                        <h5>
                            <s:property value="homeworkname"/>
                        </h5>
                        <br/>
                        <h5>
                            <s:property value="filename"/>
                        </h5>
                        <br/>
                        <h5>
                            <s:property value="uploadername"/>
                        </h5>
                        <br/>
                        <h5>
                            <s:property value="submittime"/>
                        </h5>
                    </div>
                </div>
                <br/>
                <br/>
                <div class="row" id="scoreBtnDiv">
                    <div class="col-md-3">

                        <form class="form-horizontal" role="form" action="downloadhomework" id="downloadForm">
                            <div class="form-group">

                                <button type="button" id="downloadhomeworkbtn" class="btn btn-info" onclick="downloadBtnFn()">
                                    下载
                                </button>
                            </div>
                        </form>
                    </div>
                    <div class="col-md-3">
                    </div>
                    <div class="col-md-6">
                        <form class="form-inline" role="form" action="scorehomework" id="scoreForm">
                            <div class="form-group">
                                <div class="col-md-8">
                                    <s:if test="#session.IS_RANKED == 1">
                                        <a> <s:property value="score"/> </a>
                                    </s:if>
                                    <s:else>
                                        <input type="text" class="form-control" id="scoreinput" name="score" placeholder="得分"/>
                                    </s:else>

                                </div>
                                <div class="col-md-4">
                                    <s:if test="#session.IS_RANKED == 1">
                                        <button type="button" id="scorehomeworkbtn" class="btn btn-warning" disabled="disabled" onclick="scoreBtnFn()">
                                            评分
                                        </button>
                                    </s:if>
                                    <s:else>
                                        <button type="button" id="scorehomeworkbtn" class="btn btn-warning" onclick="scoreBtnFn()">
                                            评分
                                        </button>
                                    </s:else>

                                </div>

                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="col-md-3">

            </div>


        </div>
    </div>
</div>


<script type="text/javascript">
    function downloadBtnFn(){

        var a = document.createElement('a');
        var url = '<s:property value="downloadUrl"/>';
        var filename = '<s:property value="grpId"/>' +'_'+ '<s:property value="filename"/>';
        window.alert(url);

        a.href = url;
        a.download = filename;
        a.click();
    }
    function scoreBtnFn(){
        document.getElementById('scoreForm').submit();
    }
</script>
</body>
</html>
