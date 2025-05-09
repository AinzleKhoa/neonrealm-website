<%-- 
    Document   : orders
    Created on : Mar 5, 2025, 1:41:32 PM
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
                <div class="col-sm-6">
                    <h3 class="mb-0">Orders List</h3>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-end">
                        <li class="breadcrumb-item"><a href="<%= getServletContext().getContextPath()%>">Home</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Orders List</li>
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
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <div class="card mb-4">
                        <div class="card-header">
                            <div class="card-title">
                                <!-- Price Filter & Sorting -->
                                <form class="row row-cols-lg-auto g-3 align-items-center" method="GET" action="${pageContext.servletContext.contextPath}/admin/orders">
                                    <div class="col-12">
                                        <label for="sortPrice">Sort by Price:</label>
                                        <select name="sortPrice" id="sortPrice">
                                            <option value="">Default (Ascending ID)</option>
                                            <option value="asc" ${param.sortPrice eq 'asc' ? 'selected' : ''}>Lowest to Highest</option>
                                            <option value="desc" ${param.sortPrice eq 'desc' ? 'selected' : ''}>Highest to Lowest</option>
                                        </select>
                                    </div>
                                    <div class="col-12">
                                        <button type="submit" class="btn btn-success">Filter</button>
                                        <!-- Reset Button -->
                                        <a class="btn btn-warning" href="${pageContext.servletContext.contextPath}/admin/orders">Reset</a>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="card-body table-responsive">
                            <table class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Buyer</th>
                                        <th>Order ID</th>
                                        <th>Game Name</th>
                                        <th>Price</th>
                                        <th>Discount Code</th>
                                        <th>Creation Date</th>

                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="order" items="${requestScope.orders}">
                                        <tr class="align-middle">
                                            <td>${order.order_detail_id}</td>
                                            <td>${order.username}</td>
                                            <td>${order.order_id}</td>
                                            <td><a target="_blank" style="text-decoration: none;" href="<%= getServletContext().getContextPath()%>/gamedetails?id=${order.gameId}">${order.gameTitle}</a></td>
                                            <td>${order.totalPrice}</td>
                                            <td>${empty order.discountCode ? "Null" : order.discountCode}</td>
                                            <td>${order.createdAt}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>


                        <!-- /.pagination -->
                        <div class="card-footer clearfix">
                            <div class="paginator">
                                ${requestScope.currentTotalOrders} from ${requestScope.totalOrders}
                            </div>

                            <c:set var="paginationUrl" value="${pageContext.servletContext.contextPath}/admin/orders" />

                            <c:if test="${not empty param.sortPrice}">
                                <c:set var="paginationUrl" value="${paginationUrl}?sortPrice=${param.sortPrice}" />
                            </c:if>

                            <neon:adminpagination currentPage="${currentPage}" totalPages="${numOfPages}" url="${paginationUrl}" />

                        </div>


                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--end::App Content-->      
</main>
<!--end::App Main-->
<%@include file="../WEB-INF/include/admin-foot.jsp" %>
