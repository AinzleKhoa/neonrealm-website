<%-- 
    Document   : coupons
    Created on : Mar 5, 2025, 1:28:03 AM
    Author     : Pham Van Hoai - CE181744
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<%@include file="../WEB-INF/include/admin-head.jsp" %>
<!--begin::App Main-->
<main class="app-main">
    <!--begin::App Content Header-->
    <div class="app-content-header">
        <!--begin::Container-->
        <div class="container-fluid">

            <%-- Kiểm tra nếu có thông báo thành công --%>
            <%
                String successMessage = (String) session.getAttribute("successMessage");
                if (successMessage != null) {
                    session.removeAttribute("successMessage"); // Xóa thông báo sau khi hiển thị
%>
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                <h5><i class="bi bi-check2-circle"></i> <%= successMessage%></h5>
            </div>
            <%
                }
            %>
            <!--begin::Row-->
            <div class="row">
                <div class="col-sm-6"><h3 class="mb-0">Coupons List</h3></div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-end">
                        <li class="breadcrumb-item"><a href="<%= getServletContext().getContextPath()%>">Home</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Coupons List</li>
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
                            <h3 class="card-title">Coupons List</h3>
                            <a href="?add" class="btn btn-primary float-end">Add Coupon</a>
                        </div>
                        <!-- /.card-header -->
                        <div class="card-body">
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Code</th>
                                        <th>Discount (%)</th>
                                        <th>Expiration Date</th>
                                        <th>Usage Limit</th>
                                        <th>Creation Date</th>
                                        <th>Actions</th>

                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="coupon" items="${coupons}">                                    
                                        <tr class="align-middle">
                                            <td>${coupon.couponId}</td>
                                            <td>${coupon.code}</td>
                                            <td>${coupon.discountPercentage}%</td>
                                            <td>${coupon.expirationDate}</td>
                                            <td>${coupon.usageLimit}</td>
                                            <td>${coupon.createdAt}</td>
                                            <td>
                                                <!-- Nút Edit -->
                                                <a href="${pageContext.request.contextPath}/admin/coupons?editId=${coupon.couponId}" class="btn btn-warning btn-sm">
                                                    <i class="fas fa-edit"></i> Edit
                                                </a>
                                                <form action="${pageContext.request.contextPath}/admin/coupons" method="post" class="d-inline">
                                                    <input type="hidden" name="action" value="delete">
                                                    <input type="hidden" name="couponId" value="${coupon.couponId}">
                                                    <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete?')">Delete</button>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.card-body -->



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