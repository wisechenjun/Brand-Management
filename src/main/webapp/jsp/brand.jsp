
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>品牌</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h3>品牌功能</h3>
    <div class="row">
        <form class="form-inline" id="searchform" action="/brand/findByPage/1" method="post">
            <div class="form-group">
                <label class="sr-only" for="cond1">品牌名称:</label>
                <input type="text" class="form-control" name="name" id="cond1" placeholder="请输入品牌名称" value="${name}">
            </div>
            <div class="form-group">
                <label class="sr-only" for="cond2">品牌首字母:</label>
                <input type="text" class="form-control" name="firstChar" id="cond2" placeholder="请输入品牌首字母" value="${firstChar}">
            </div>
            <button type="button" class="btn btn-success" id="searchbtn">搜索</button>
            <button id="add" type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
                新增品牌
            </button>
        </form>
    </div><br>
    <div class="row">
        <table class="table table-bordered table-hover">
            <thead>
            <tr>
                <td>ID</td>
                <td>品牌名称</td>
                <td>品牌首字母</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${info.list}" var="brand" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td>${brand.name}</td>
                    <td>${brand.firstChar}</td>
                    <td>
                        <button  onclick="upd(${brand.id});" class="btn btn-primary" data-toggle="modal" data-target="#myModal">编辑</button>
                        <button class="btn btn-danger" onclick="del(${brand.id});">删除</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <nav aria-label="Page navigation">
            <ul id="page" class="pagination">


                <li onclick="findByPage(1);"><a href="javascript:void(0);">首页</a></li><li onclick="findByPage(${before});" class="threeword"><a href="javascript:void(0);">上一页</a></li>
                <c:forEach begin="${begin}" end="${end}" var="i">
                    <%--对当前页进行判断突显--%>
                    <c:if test="${pageNum==i}">
                        <li class="active" onclick="findByPage(${i});"><a href="javascript:void(0);">${i}</a></li>
                    </c:if>
                    <%--不是当前页则普通显示--%>
                    <c:if test="${pageNum!=i}">
                        <li onclick="findByPage(${i});"><a href="javascript:void(0);">${i}</a></li>
                    </c:if>
                </c:forEach>

                <font size="5"><span class="page_num_inf">共<span id="sp_tp">${info.pages}</span>页<span id="sp_tc">${info.total}</span>条</span></font>


                <li onclick="findByPage(${next});" class="threeword"><a href="javascript:void(0);">下一页</a></li><li onclick="findByPage(${info.pages});" class="threeword"><a href="javascript:void(0);">末页</a></li>
            </ul>
        </nav>
    </div>
</div>
<!--编辑窗口-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">编辑窗口</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="brandForm" action="" method="post">
                    <input id="id" name="id" type="hidden" value="${brand.id}">
                    <div class="form-group">
                        <label for="name" class="col-sm-3 control-label">品牌名称</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="name" id="name" placeholder="请输入品牌名称" value="${brand.name}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="firstChar" class="col-sm-3 control-label">品牌首字母</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="firstChar" id="firstChar" placeholder="请输入品牌首字母" value="${brand.firstChar}">
                        </div>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="editbtn">保存</button>
            </div>
        </div>
    </div>
</div>
<script>
    //避免客户删除搜索条件还点击搜索后的分页，用两个变量存起来
    var cond1=$("#cond1").val();
    var cond2=$("#cond2").val();

    function findByPage(pageNum) {
        var url="/brand/findByPage/"+pageNum;
        if (cond1!=""&&cond2!=""){
            url+="?name="+cond1+"&firstChar="+cond2;
        }else if (cond1!=""){
            url+="?name="+cond1;
        }else if (cond2!=""){
            url+="?firstChar="+cond2;
        }



        location.href=url;
    }


function del(id) {
    if(confirm("您确定要删除此条品牌信息吗？")){

        var url="/brand/deleteById/"+id;
        if (cond1!=""&&cond2!=""){
            url+="?name="+cond1+"&firstChar="+cond2;
        }else if (cond1!=""){
            url+="?name="+cond1;
        }else if (cond2!=""){
            url+="?firstChar="+cond2;
        }
        location.href=url;
    }
}

function upd(id) {

    var url="/brand/findById/"+id;
    if (cond1!=""&&cond2!=""){
        url+="?name="+cond1+"&firstChar="+cond2;
    }else if (cond1!=""){
        url+="?name="+cond1;
    }else if (cond2!=""){
        url+="?firstChar="+cond2;
    }
    location.href=url;

}

$(function () {
    if($("#id").val()!=null&&$("#id").val()!=""){
        $('#myModal').modal('show');
    }

    //点击新增则清空弹窗内容
    $("#add").click(function () {
        $("#id").val("");
        $("#name").val("");
        $("#firstChar").val("");
    });


    //如果id为空则是新增
    $("#editbtn").click(function () {
        if($("#id").val()==""){
            $("#brandForm").prop("action","/brand/save")
            $("#brandForm").submit();
            alert("保存成功");
        }else {
            var url="/brand/update";
            if (cond1!=""&&cond2!=""){
                url+="?name1="+cond1+"&firstChar1="+cond2;
            }else if (cond1!=""){
                url+="?name1="+cond1;
            }else if (cond2!=""){
                url+="?firstChar1="+cond2;
            }

            $("#brandForm").prop("action",url)
            $("#brandForm").submit();
            alert("更新成功");
        }
    });

    $("#searchbtn").click(function () {
        $("#searchform").submit();
    });


});



</script>
</body>
</html>
