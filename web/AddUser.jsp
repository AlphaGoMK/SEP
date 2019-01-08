<%--
  Created by IntelliJ IDEA.
  User: AlphaGoMK
  Date: 12/23/2018
  Time: 1:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add User</title>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <style type="text/css">
        #radioBtn .notActive{
            color: #3276b1;
            background-color: #fff;
        }
    </style>
</head>
<body>

    <div class="loginmodal-container">
        <form class="form-horizontal" role="form" action="AddUser" onsubmit="checkRadioFn()">

            <div class="form-group">
                <label for="happy" class="col-sm-4 col-md-4 control-label text-right">选择用户类别</label>
                <div class="col-sm-7 col-md-7">
                    <div class="input-group">
                        <div id="radioBtnDiv" class="btn-group">

                            <input id="radioBtn1" type="radio" name="flag" value="teacher" onclick="clickTeaFn()"/>
                            <label for="radioBtn1">老师</label>
                            <input id="radioBtn2" type="radio" name="flag" value="student" onclick="clickStuFn()"/>
                            <label for="radioBtn2">学生</label>
                            <%--<a class="btn btn-primary btn-sm active" data-toggle="happy" data-title="Y">老师</a>--%>
                            <%--<a class="btn btn-primary btn-sm notActive" data-toggle="happy" data-title="N">学生</a>--%>
                        </div>
                        <input type="hidden" name="uid" id="happy">
                    </div>
                </div>
            </div>

            <div class="form-group" id="uidDiv" style="display: none;">
                <label for="firstname" class="col-sm-4 col-md-4 control-label">账号</label>
                <div class="input-group">
                    <input type="text" class="form-control" name="uid" id="firstname" placeholder="学号或工号">
                </div>
            </div>
            <div class="form-group">
                <label for="lastname" class="col-sm-4 col-md-4 control-label">姓名</label>
                <div class="input-group">
                    <input type="text" class="form-control" name="name" id="lastname" placeholder="名字">
                </div>
            </div>



            <div class="form-group" id="selectClassDiv" style="display: none;">
                <label for="maxcrew" class="col-sm-4 col-md-4 control-label">班级</label>
                <div class="input-group">
                    <input type="text" class="form-control" name="classid" id="maxcrew" placeholder="班号">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-5 col-sm-4 col-md-4">
                    <button type="submit" class="btn btn-default">创建</button>
                </div>
            </div>
        </form>

        <%--<form action="AddUser">--%>
            <%--账号: <input type="text" name="usr.uid"/>--%>
            <%--姓名: <input type="text" name="usr.name"/>--%>
            <%--班级: <input type="text" name="usr.classid"/>--%>
            <%--<input type="submit" value="添加"/>--%>
        <%--</form>--%>
    </div>

        <script type="text/javascript">
            function clickTeaFn(){
                document.getElementById('selectClassDiv').style.display = "none";
                document.getElementById('uidDiv').style.display = "none";
            }
            function clickStuFn(){
                document.getElementById('selectClassDiv').style.display = "block";
                document.getElementById('uidDiv').style.display = "block";
            }
            function checkRadioFn() {
                var radiobtn=document.getElementByName('flag');
                if(!radiobtn[0].checked&&!radiobtn[1].checked){
                    alert("请选择身份");
                    window.location = "/AddUser.jsp";
                }
            }
            // $('#radioBtn a').on('click', function(){
            //     var sel = $(this).data('title');
            //     var tog = $(this).data('toggle');
            //     $('#'+tog).prop('value', sel);
            //     if (sel=="N"){
            //         document.getElementById('selectClassDiv').style.display = "block";
            //         document.getElementById('uidDiv').style.display = "block";
            //     }
            //     else{
            //         document.getElementById('selectClassDiv').style.display = "none";
            //         document.getElementById('uidDiv').style.display = "none";
            //     }
            //     $('a[data-toggle="'+tog+'"]').not('[data-title="'+sel+'"]').removeClass('active').addClass('notActive');
            //     $('a[data-toggle="'+tog+'"][data-title="'+sel+'"]').removeClass('notActive').addClass('active');
            // })
        </script>

</body>
</html>
