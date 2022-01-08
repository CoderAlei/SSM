<%--
  Created by IntelliJ IDEA.
  User: HUAWEI
  Date: 2022/1/4
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>



<head>
    <title>员工列表</title>
</head>
<% pageContext.setAttribute("APP_PATH",request.getContextPath()); %>
<%--不以/开始的相对路径，找资源，以当前资源路径为基准--%>
<%--以/开始的相对路径，找资源，以服务器的路径为标准--%>
<body>
<%--搭建页面--%>
    <div class="container">
<%--        标题--%>
            <div class="row">
                <div class="col-md-12">
                    <h1>SSM-CRUD</h1>
                </div>
            </div>
<%--            按钮--%>
            <div class="row"></div>
<%--        显示表格数据        --%>
            <div class="row"></div>
<%--            显示分页信息        --%>
            <div class="row"></div>

    </div>
</body>
<%--引入jquery--%>
<script type="text/javascript" src="${APP_PATH}/static/js/jquery-1.12.4.min.js"></script>
<%--引入样式--%>
<link href="${APP_PATH}/static/bootstrap-5.1.3-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="${APP_PATH}/static/bootstrap-5.1.3-dist/js/bootstrap.min.js"></script>
</html>
