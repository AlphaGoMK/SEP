<%@ page import="sep.Entity.Course" %>
<%@ page import="sep.Entity.Student" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
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
                    <a class="nav-link active"
                       href="/sep/Action/showGroupAndHome.action?courseId=<s:property value="courseId"/>"
                       onclick="showGroupAndHomeDiv()"
                       id="hwInfoBtn">
                        作业信息
                    </a>
                </li>
                <li class="nav-item">
                    <s:if test="#has_group == 'true'">
                        <a class="nav-link disabled">创建小组</a>
                    </s:if>
                    <s:else>
                        <a class="nav-link" onclick="showCreateGroup()"
                           href="javascript:void(0)"
                           id="createGroupBtn">
                            创建小组
                        </a>
                    </s:else>
                </li>
            </ul>
        </div>
        <div class="col-md-7">
            <br/>
            <br/>
            <div id="groupAndHomeDiv">
            <s:if test="#has_group == 'true'">
                <h3>Group <s:property value="g.groupId"/></h3>
                <table class="table table-striped" style="font-size: 13px">
                    <thead>
                    <tr class="row">
                        <th class="col-1">作业名</th>
                        <th class="col-2">作业描述</th>
                        <th class="col-1">截止时间</th>
                        <th class="col-1">发布时间</th>
                        <th class="col-1">成绩占比</th>
                        <th class="col-1">提交状态</th>
                        <th class="col-2">已提交文件</th>
                        <th class="col-1">成绩</th>
                        <th class="col-2">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                    <tr class="row">
                        <s:iterator value="c.homework" status="status">
                            <td class="col-1"><s:property value="name"/></td>
                            <td class="col-2"><s:property value="content"/></td>
                            <td class="col-1"><s:property value="deadline"/></td>
                            <td class="col-1"><s:property value="assigntime"/></td>
                            <td class="col-1"><s:property value="percentage"/></td>
                            <td class="col-1">
                                <s:set var="submitted" value="hwState[#status.index]"/>
                                <%--<s:property value="#submitted"/>--%>
                                <s:if test="#submitted == 'true'">
                                    <a>已提交</a>
                                </s:if>
                                <s:else>
                                    <a>未提交</a>
                                </s:else>
                            </td>
                            <th class="col-2">
                                <s:if test="hwFileLists[#status.index] != null">
                                    <s:iterator value="hwFileLists[#status.index]">
                                        <s:property/>
                                    </s:iterator>
                                </s:if>
                            </th>
                            <td class="col-1">
                                <s:set var="score" value="hwScore[#status.index]"/>
                                <%--<s:property value="#score"/>--%>
                                <s:if test="#score == -1.0">
                                    <a>尚无评分</a>
                                </s:if>
                                <s:else>
                                    <a><s:property value="#score"/></a>
                                </s:else>
                            </td>
                            <td class="col-2">
                                <s:if test="#score != -1.0" >
                                    <a>重新提交（评分将清零）</a>
                                </s:if>
                                <s:else>
                                    <s:if test="#submitted == 'true'">
                                        <form action="submitHomework.action?hwName=<s:property value="name"/>" method="post" enctype="multipart/form-data">
                                            <input type="file" name="hwFile" id="file1" class="inputfile" />
                                            <label for="file1" id="file1Label">再次提交</label>
                                            <br/>
                                            <input type="submit" value="上传" />
                                        </form>
                                    </s:if>
                                    <s:else>
                                        <form action="submitHomework.action?hwName=<s:property value="name"/>" method="post" enctype="multipart/form-data">
                                            <input type="file" name="hwFile" id="file2" class="inputfile" />
                                            <label for="file2" id="file2Label">选择提交文件</label>
                                            <br/>
                                            <input type="submit" value="上传" />
                                        </form>
                                    </s:else>
                                </s:else>
                            </td>
                        </s:iterator>
                    </tr>
                    </tbody>
                </table>
            </s:if>
            <s:else>
                <div class="alert alert-primary" role="alert">
                    您尚未加入本课程的任何小组，请创建小组或加入已有小组。
                </div>
            </s:else>
            </div>
            <div id="createGroupDiv">
                <form role="form" action="createGroup">
                    <%--<div class="row">--%>
                        <%--<div class="form-group col-4">--%>
                            <%--<label for="groupIdInput">--%>
                                <%--小组编号--%>
                            <%--</label>--%>
                            <%--<input type="text" class="form-control" id="groupIdInput" readonly="readonly"--%>
                                   <%--placeholder="<s:property value="0"/>"/>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%
                        int maxCrew = ((Course)request.getAttribute("c")).getMaxcrew();
                        Student s = ((Student)request.getAttribute("s"));
                        int stuId = s.getStuId();
                        String stuName = s.getName();
                        int i = 0;

                        while (i < maxCrew) {
                            if (i % 3 == 0) {
                                out.println("<div class=\"row\">");
                                for (int j = 0; j + i < maxCrew && j < 3; j ++) {
                                    if(i == 0 && j == 0) {
                                        out.println("<div class=\"form-group col-4 grpMebInput\">\n" +
                                                "    <div class=\"text-center\">\n" +
                                                "        组员" + (i + j + 1) + "\n" +
                                                "    </div>\n" +
                                                "    <label for=\"studentNameInput" + (i + j + 1) + "\">\n" +
                                                "        姓名\n" +
                                                "    </label>\n" +
                                                "    <input type=\"text\" class=\"form-control form-control-sm\"\n" +
                                                "           placeholder=\"" + stuName + "\"\n" +
                                                "           value=\"" + stuName + "\"\n" +
                                                "           readonly=\"readonly\"\n" +
                                                "           name=\"studentNameInput\"" +
                                                "           id=\"studentNameInput" + (i + j + 1) + "\"/>\n" +
                                                "    </label>\n" +
                                                "    <div>\n" +
                                                "        <ul class=\"nameSuggestList\" id=\"stuNameSuggest" + (i + j + 1) + "\">\n" +
                                                "        </ul>\n" +
                                                "    </div>\n" +
                                                "    <label for=\"studentIdInput" + (i + j + 1) + "\">\n" +
                                                "        学号\n" +
                                                "    </label>\n" +
                                                "    <input type=\"text\" class=\"form-control form-control-sm\"\n"+
                                                "           placeholder=\"" + stuId + "\"\n" +
                                                "           value=\"" + stuId + "\"\n" +
                                                "           readonly=\"readonly\"\n" +
                                                "           name=\"studentIdInput\"" +
                                                "           id=\"studentIdInput" + (i + j + 1) + "\"/>\n" +
                                                "    <br/>\n" +
                                                "</div>");
                                    } else {
                                        out.println("<div class=\"form-group col-4 grpMebInput\">\n" +
                                                "    <div class=\"text-center\">\n" +
                                                "        组员" + (i + j + 1) + "\n" +
                                                "    </div>\n" +
                                                "    <label for=\"studentNameInput" + (i + j + 1) + "\">\n" +
                                                "        姓名\n" +
                                                "    </label>\n" +
                                                "    <input type=\"text\" class=\"form-control form-control-sm\" " +
                                                "           name=\"studentNameInput\"" +
                                                "           id=\"studentNameInput" + (i + j + 1) + "\"/>\n" +
                                                // 搜索提示
                                                "    <div class=\"search_suggest\">\n" +
                                                "        <ul class=\"nameSuggestList\" id=\"nameSearchSuggest" + (i + j + 1) + "\">\n" +
                                                "        </ul>\n" +
                                                "    </div>\n" +

                                                "    </label>\n" +
                                                "    <label for=\"studentIdInput" + (i + j + 1) + "\">\n" +
                                                "        学号\n" +
                                                "    </label>\n" +
                                                "    <input type=\"text\" class=\"form-control form-control-sm\"" +
                                                "           name=\"studentIdInput\"" +
                                                "           id=\"studentIdInput" + (i + j + 1) + "\"/>\n" +
                                                "    <br/>\n" +
                                                "</div>");
                                    }
                                }
                                out.println("</div>");
                            }
                            i += 3;
                        }
                    %>

                    <div class="row">
                        <div class="form-group col-4">
                            <label for="contactInput">
                                小组联系方式
                            </label>
                            <input type="text" class="form-control" id="contactInput" name="contact"/>
                        </div>
                        <div class="form-group col-4">
                            <label>
                                <label for="grpLeaderInput">
                                    组长学号
                                </label>
                                <%--<input type="text" class="form-control" id="grpLeaderInput" name="leaderId"/>--%>
                                <select class="form-control" id="grpLeaderInput" name="leaderId">
                                    <option selected="selected" value="<s:property value="s.stuId"/>">
                                        <s:property value="s.stuId"/>
                                    </option>
                                </select>
                            </label>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary" id="createGroupSubmit">
                        提交
                    </button>
                </form>
                <s:fielderror style="color: red"/>
            </div>
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


<script type="text/javascript">
    function showCreateGroup(){
        var createGroupDiv = document.getElementById('createGroupDiv');
        createGroupDiv.style.display = "block";
        var showGroupAndHomeDiv = document.getElementById('groupAndHomeDiv');
        showGroupAndHomeDiv.style.display = "none";
        var createGroupBtn = document.getElementById('createGroupBtn');
        createGroupBtn.className = "nav-link active";
        var hwInfoBtn = document.getElementById('hwInfoBtn');
        hwInfoBtn.className = "nav-link";
    }
    
    function showGroupAndHomeDiv() {
        var createGroupDiv = document.getElementById('createGroupDiv');
        createGroupDiv.style.display = "none";
        var showGroupAndHomeDiv = document.getElementById('groupAndHomeDiv');
        showGroupAndHomeDiv.style.display = "block";
        var createGroupBtn = document.getElementById('createGroupBtn');
        createGroupBtn.className = "nav-link";
        var hwInfoBtn = document.getElementById('hwInfoBtn');
        hwInfoBtn.className = "nav-link active";
    }

    function nameDetermination(nameInput, idInput) {
        idInput.keyup(function(){
            var stuId = $(this).val();
            $.ajax({
                url: '${pageContext.request.contextPath}/fetchName',
                type: 'POST',
                data:{
                    "stuId": stuId
                },
                datatype: 'json',
                success: function(data) {
                    if(data) {
                        nameInput.val(data);
                    }
                }
            })
        })
    }

    function suggestLeaderId(IdInput, leaderIdSelect) {
        IdInput.bind('input porpertychange', function(){
            leaderIdSelect.children().remove();
            var idInputList = $("input[name='studentIdInput']");
            for (var i = 0; i < idInputList.length; i ++) {
               if(idInputList[i] && idInputList[i].value.length != 0) {
                   if (i == 0) {
                       leaderIdSelect.append("<option selected='selected' value='" + idInputList[i].value + "'>" + idInputList[i].value + "</option>")
                   } else {
                       leaderIdSelect.append("<option value='" + idInputList[i].value + "'>" + idInputList[i].value + "</option>")
                   }
               }
            }
        })
    }

    //搜索提示
    function suggestName(nameInput, suggestWrap) {
        nameInput.keyup(function () {
            suggestWrap.children().remove();
            var name = $(this).val();
            var courseId = <s:property value="courseId"/>

            $.ajax({

                url:'${pageContext.request.contextPath}/getSearchSuggest',
                type:'POST',
                data:{
                    "name":name,
                    "courseId": courseId
                },
                datatype:'json',
                success:function(data){
                    if (data && data.length > 0) {
                        suggestWrap.show();
                        for(i = 0; i < data.length; i ++){
                            if(data[i] != "<s:property value="s.name"/>") {
                                suggestWrap.append("<li>" + data[i] + "</li>");
                            }
                        }
                        suggestWrap.find('li').hover(function(){
                            suggestWrap.find('li').removeClass('hover');
                            $(this).addClass('hover');
                        },function(){
                            $(this).removeClass('hover');
                        }).bind('click',function() {
                            nameInput.val(this.innerHTML);
                            suggestWrap.hide();
                        })
                    } else {
                        suggestWrap.hide();
                    }
                }
            })
        });
    }

    $(document).ready(function () {
        for (var i =2; i <= <s:property value="c.maxcrew"/>; i++) {
            var suggestWrap = $("#nameSearchSuggest" + i);
            var nameInput = $("#studentNameInput" + i);
            var idInput = $("#studentIdInput" + i);
            suggestName(nameInput, suggestWrap);
            nameDetermination(nameInput, idInput);
        }
        var leaderSelector = $("#grpLeaderInput")
        for (var i = 1; i <= <s:property value="c.maxcrew"/>; i++) {
            var idInput = $("#studentIdInput" + i);
            suggestLeaderId(idInput, leaderSelector);
        }
        var fileinput1 = $("#file1"), label1 = $("#file1Label"), labelVal1 = label1.html();
        var fileinput2 = $("#file2"), label2 = $("#file2Label"), labelVal2 = label2.html();
        showFileName(label1, fileinput1);
        showFileName(label2, fileinput2);
    });

    function showFileName(label, input, labelVal){
        input.on('change', function(e){
            if (this.files && this.files.length > 1) {
                fileName = ( this.getAttribute( 'data-multiple-caption' ) || '' ).replace( '{count}', this.files.length )
            } else if (e.target.value) {
                fileName = e.target.value.split( '\\' ).pop();
            }
            if(fileName)
                label.html(fileName);
            else
                label.html(labelVal);
        });
    }

</script>

</body>
<%
    Map sess = ActionContext.getContext().getSession();
    if (sess.containsKey("CREATE_GROUP_STATE") && sess.get("CREATE_GROUP_STATE").equals("error")) {
        out.print("<script>showCreateGroup()</script>");
    }
%>

</html>
