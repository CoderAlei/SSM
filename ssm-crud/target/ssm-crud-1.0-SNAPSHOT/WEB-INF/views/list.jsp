<%@ page isELIgnored="false" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>员工列表</title>
    <%
        pageContext.setAttribute("APP_PATH", request.getContextPath());
    %>
    <!-- web路径：
    不以/开始的相对路径，找资源，以当前资源的路径为基准，经常容易出问题。
    以/开始的相对路径，找资源，以服务器的路径为标准(http://localhost:3306)；需要加上项目名
            http://localhost:3306/crud
     -->
    <script type="text/javascript"
            src="${APP_PATH}/static/js/jquery-1.12.4.min.js"></script>
    <link
            href="${APP_PATH}/static/bootstrap-5.1.3-dist/css/bootstrap.min.css"
            rel="stylesheet">
    <script
            src="${APP_PATH}/static/bootstrap-5.1.3-dist/js/bootstrap.min.js"></script>
</head>


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
            <div class="row">
                <div class="col-md-4 offset-md-8">
                    <button class="btn btn-primary">新增</button>
                    <button class="btn btn-danger">删除</button>
                </div>
            </div>
            <div>
                <div class="row">
                    <div class="col-md-12">
                        <table class="table table-hover">
                            <tr>
                                <th>#</th>
                                <th>empName</th>
                                <th>gender</th>
                                <th>email</th>
                                <th>deptName</th>
                                <th>操作</th>
                            </tr>
                            <c:forEach items="${pageInfo.list}" var="emp">
                            <tr>
                                <th>${emp.empId}</th>
                                <th>${emp.empName}</th>
                                <th>${emp.gender=="M"?"男":"女"}</th>
                                <th>${emp.email}</th>
                                <th>${emp.department.deptName}</th>
                                <th>
                                    <button class="btn btn-primary btn-sm">
                                        <span class="glyphicon glyphicon-star" aria-hidden="true"></span>
                                        编辑
                                    </button>
                                    <button class="btn btn-danger  btn-sm">
                                        <span class="glyphicon glyphicon-trash " aria-hidden="true"></span>
                                        删除
                                    </button>
                                </th>

                            </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>

                <%--            分页信息--%>
                <div class="row">
                    <%--                    分页文字信息--%>
                    <div class="col-md-6">
                        当前记录数：xxx
                    </div>


                        <div class="col-md-6">
                <nav aria-label="Page navigation example">
                    <ul class="pagination">
                        <li class="page-item"><a class="page-link" href="${APP_PATH}/emps?pn=1">首页</a></li>
                        <c:if test="${pageInfo.hasPreviousPage}">
                            <li class="page-item">
                                <a class="page-link" href="${APP_PATH}/emps?pn=${pageInfo.pageNum-1}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>


                        <c:forEach items="${pageInfo.navigatepageNums}" var="page_Num">
                            <%--                                    当前页--%>
                            <c:if test="${page_Num==pageInfo.pageNum}">
                                <li class="page-item active" aria-current="page"><a class="page-link" href="#">${page_Num}</a></li>
<%--                                <li  class="active"><a href="#"></a></li>--%>
                            </c:if>
                            <c:if test="${page_Num!=pageInfo.pageNum}">
                                <li class="page-item"><a class="page-link" href="${APP_PATH}/emps?pn=${page_Num}">${page_Num}</a></li>
<%--                                <li ><a href="${APP_PATH}/emps?pn=${page_Num}">${page_Num} </a> </li>--%>
                            </c:if>
                        </c:forEach>
                        <c:if test="${pageInfo.hasNextPage}">
                            <li class="page-item">
                                <a class="page-link" href="${APP_PATH}/emps?pn=${pageInfo.pageNum+1}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>

                        <li class="page-item"><a class="page-link" href="${APP_PATH}/emps?pn=${pageInfo.pages}">末页</a></li>
                    </ul>
                </nav>
                </div>
                </div>

    </div>

<%--<div class="container">--%>
<%--    <div class="row">--%>
<%--        <div class="col-md-4">.col-md-4</div>--%>
<%--        <div class="col-md-4 offset-md-4">.col-md-4 .offset-md-4</div>--%>
<%--    </div>--%>
<%--    <div class="row">--%>
<%--        <div class="col-md-3 offset-md-3">.col-md-3 .offset-md-3</div>--%>
<%--        <div class="col-md-3 offset-md-3">.col-md-3 .offset-md-3</div>--%>
<%--    </div>--%>
<%--    <div class="row">--%>
<%--        <div class="col-md-6 offset-md-3">.col-md-6 .offset-md-3</div>--%>
<%--    </div>--%>
<%--</div>--%>


</html>
