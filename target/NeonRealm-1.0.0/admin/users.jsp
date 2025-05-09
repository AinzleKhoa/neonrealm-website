<%-- 
    Document   : admin-users
    Created on : Mar 3, 2025, 5:30:32 PM
    Author     : Pham Van Hoai - CE181744
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="neon" uri="/WEB-INF/tags/implicit.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<%@include file="../WEB-INF/include/admin-head.jsp" %>
<!--begin::App Main-->
<main class="app-main">
    <!--begin::App Content Header-->
    <div class="app-content-header">
        <!--begin::Container-->
        <div class="container-fluid">
            <!--begin::Row-->
            <div class="row">
                <div class="col-sm-6"><h3 class="mb-0">Users List</h3></div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-end">
                        <li class="breadcrumb-item"><a href="<%= getServletContext().getContextPath()%>">Home</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Users List</li>
                    </ol>
                </div>
            </div>
            <!--end::Row-->
        </div>
        <!--end::Container-->
    </div>
    <!--end::App Content Header-->
    <!--begin::App Content-->
    <div class="app-content">
        <!--begin::Container-->
        <div class="container-fluid">
            <!--begin::Row-->
            <div class="row">
                <div class="col-md-12">
                    <div class="card mb-4">
                        <div class="card-header">
                            <form class="row row-cols-lg-auto g-3 align-items-center" action="${pageContext.servletContext.contextPath}/admin/users" method="get">
                                <div  class="col-12">
                                    <input type="text" name="search" value="${requestScope.searchQuery}" placeholder="Search for a user..." />
                                </div>
                                <div  class="col-12">
                                    <button type="submit" class="btn btn-success">Search</button>
                                    <!-- Reset Button -->
                                    <a class="btn btn-warning" href="${pageContext.servletContext.contextPath}/admin/users">Reset</a>
                                </div>
                            </form>
                        </div>
                        <!-- /.card-header -->
                        <div class="card-body table-responsive">
                            <table class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Username</th>
                                        <th>Email</th>
                                        <th>Role</th>
                                        <th>Status</th>
                                        <th>Last Login</th>
                                        <th>Create At</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="user" items="${users}">
                                        <tr class="align-middle">
                                            <td>${user.user_id}</td>
                                            <td>${user.username}</td>
                                            <td>${user.email}</td>
                                            <td>${user.role}</td>
                                            <td>${user.status}</td>
                                            <td>${user.last_login}</td>
                                            <td>${user.created_at}</td>
                                            <td>
                                                <form action="${pageContext.request.contextPath}/admin/users" method="post" class="d-inline">
                                                    <input type="hidden" name="action" value="delete">
                                                    <input type="hidden" name="userId" value="${user.user_id}">
                                                    <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Bạn có chắc muốn xóa?')">Xóa</button>
                                                </form>
                                            </td>
                                        </tr>
                                    </tbody>
                                </c:forEach>
                            </table>
                        </div>
                        <!-- /.card-body -->
                        <!-- /.pagination -->
                        <div class="card-footer clearfix">
                            <div class="paginator">
                                ${requestScope.currentTotalUsers} from ${requestScope.totalUsers}
                            </div>

                            <c:set var="paginationUrl" value="${pageContext.servletContext.contextPath}/admin/users" />
                            <c:if test="${not empty param.search}">
                                <c:set var="paginationUrl" value="${paginationUrl}?search=${param.search}" />
                            </c:if>

                            <neon:adminpagination currentPage="${requestScope.currentPage}" totalPages="${requestScope.numOfPages}" url="${paginationUrl}" />
                        </div>

                    </div>
                    <!-- /.card -->
                </div>
                <!-- /.col -->
            </div>
            <!--end::Row-->
        </div>
        <!--end::Container-->
    </div>
    <!--end::App Content-->      
</main>
<!--end::App Main-->

<%@include file="../WEB-INF/include/admin-foot.jsp" %>