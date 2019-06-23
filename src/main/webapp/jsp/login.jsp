<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/20
  Time: 20:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登陆页面</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/jquery-3.2.1.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h3>用户登陆</h3>
    <div class="row">
        <form class="form-horizontal" id="loginform" action="/user/login" method="post">
            <input  type="hidden" id="error" value="${error}">
            <div class="form-group">
                <label for="username" class="col-sm-2 control-label">用户名</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="username" id="username" placeholder="请输入用户名">
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-2 control-label">密码</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" name="password" id="password" placeholder="请输入密码">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="button" class="btn btn-primary" id="loginbtn">登陆</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    $(function () {

        $("#loginbtn").click(function () {
            $("#loginform").submit();
        });
        if($("#error").val().length>0){
            alert($("#error").val());
        }
    });
</script>
</body>

</html>