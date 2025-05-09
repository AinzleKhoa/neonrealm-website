<%-- 
    Document   : edit-coupon
    Created on : Mar 6, 2025, 7:14:16 AM
    Author     : Pham Van Hoai - CE181744
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../WEB-INF/include/admin-head.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!--begin::App Main-->
<main class="app-main">
    <!--begin::App Content Header-->
    <div class="app-content-header">
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-6"><h3 class="mb-0">Edit Discount Code</h3></div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-end">
                        <li class="breadcrumb-item"><a href="<%= getServletContext().getContextPath()%>">Home</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Edit Coupon</li>
                    </ol>
                </div>
            </div>
        </div>
    </div>
    <!--end::App Content Header-->

    <!--begin::App Content-->
    <div class="app-content">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-8 offset-md-2">
                    <div class="card">
                        <div class="card-header">
                            <h3 class="card-title">Edit Discount Code</h3>
                        </div>
                        <div class="card-body">
                            <!-- Kiểm tra nếu coupon không tồn tại -->
                            <c:choose>
                                <c:when test="${empty coupon}">
                                    <div class="alert alert-danger">Discount code not found.</div>
                                </c:when>
                                <c:otherwise>
                                    <form action="${pageContext.request.contextPath}/admin/coupons" method="post">
                                        <input type="hidden" name="action" value="edit">
                                        <input type="hidden" name="couponId" value="${coupon.couponId}">

                                        <div class="mb-3">
                                            <label for="code" class="form-label">Coupon Code</label>
                                            <input type="text" class="form-control" id="code" name="code" value="${coupon.code}" required>
                                        </div>

                                        <div class="mb-3">
                                            <label for="discount" class="form-label">Discount Percentage (%)</label>
                                            <input type="number" class="form-control" id="discount" name="discount_percentage" min="1" max="100" value="${coupon.discountPercentage}" required>
                                        </div>

                                        <div class="mb-3">
                                            <label for="expiration_date" class="form-label">Expiration Date</label>
                                            <input type="date" class="form-control" id="expiration_date" name="expiration_date" value="${coupon.expirationDate}" required>
                                        </div>

                                        <div class="mb-3">
                                            <label for="usage_limit" class="form-label">Usage Limit</label>
                                            <input type="number" class="form-control" id="usage_limit" name="usage_limit" min="1" value="${coupon.usageLimit}" required>
                                        </div>

                                        <button type="submit" class="btn btn-primary">Update Coupon</button>
                                        <a href="${pageContext.request.contextPath}/admin/coupons" class="btn btn-secondary">Cancel</a>
                                    </form>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>      
</main>
<!--end::App Main-->

<%@ include file="../WEB-INF/include/admin-foot.jsp" %>
