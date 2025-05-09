<%-- 
    Document   : games
    Created on : Mar 7, 2025, 1:54:15 AM
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
                <div class="col-sm-6"><h3 class="mb-0">Games List</h3></div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-end">
                        <li class="breadcrumb-item"><a href="${pageContext.servletContext.contextPath}">Home</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Games List</li>
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
            <c:if test="${not empty sessionScope.successMessage}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    <h5><i class="bi bi-check2-circle"></i> ${sessionScope.successMessage}</h5>
                </div>
                <c:remove var="successMessage" scope="session"/>
            </c:if>

            <div class="row">
                <div class="col-md-12">
                    <div class="card mb-4">
                        <div class="card-header">
                            <div class="card-title">
                                <form class="row row-cols-lg-auto g-3 align-items-center" method="GET" action="${pageContext.servletContext.contextPath}/admin/games">
                                    <!-- Lọc theo thể loại -->
                                    <div class="col-12">
                                        <label for="genre">Select Genre:</label>
                                        <select name="genre" id="genre">
                                            <option value="">All</option>
                                            <c:forEach var="genre" items="${requestScope.allGenres}">
                                                <option value="${genre}" ${genre eq param.genre ? 'selected' : ''}>${genre}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="col-12">
                                        <!-- Search by name -->
                                        <input type="text" name="search" value="${requestScope.searchQuery}" placeholder="Search for a game..." />
                                    </div>

                                    <div class="col-12">
                                        <!-- Submit -->
                                        <button type="submit" class="btn btn-success">Apply</button>
                                        <!-- Reset Button -->
                                        <a class="btn btn-warning" href="${pageContext.servletContext.contextPath}/admin/games">Reset</a>
                                    </div>

                                </form>
                            </div>

                            <a href="?add" class="btn btn-primary float-end">Add Game</a>
                        </div>
                        <!-- /.card-header -->
                        <div class="card-body table-responsive">
                            <table class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Image</th>
                                        <th>Title</th>
                                        <th>Description</th>
                                        <th>Price</th>
                                        <th>Release Date</th>
                                        <th>Created At</th>
                                        <th>Genres</th>
                                        <th>Publishers</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="game" items="${requestScope.games}">
                                        <tr class="align-middle">
                                            <td>${game.gameId}</td>
                                            <td>
                                                <img src="${pageContext.request.contextPath}/assets/img/cards/${game.imageUrl}" 
                                                     alt="Game Image" width="100">
                                            </td>
                                            <td><a target="_blank" style="text-decoration: none;" href="<%= getServletContext().getContextPath()%>/gamedetails?id=${game.gameId}">${game.title}</a></td>
                                            <td>${game.description}</td>
                                            <td>$${game.price}</td>
                                            <td>${game.releaseDate}</td>
                                            <td>${game.createdAt}</td>
                                            <td>
                                                <c:forEach var="genre" items="${game.genres}" varStatus="status">
                                                    ${genre}<c:if test="${!status.last}">, </c:if>
                                                </c:forEach>
                                            </td>
                                            <td>
                                                <c:forEach var="publisher" items="${game.publishers}" varStatus="status">
                                                    ${publisher}<c:if test="${!status.last}">, </c:if>
                                                </c:forEach>
                                            </td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/admin/games?editId=${game.gameId}" class="btn btn-warning btn-sm">
                                                    <i class="fas fa-edit"></i> Edit
                                                </a>
                                                <form action="${pageContext.request.contextPath}/admin/games" method="post" class="d-inline">
                                                    <input type="hidden" name="action" value="delete">
                                                    <input type="hidden" name="gameId" value="${game.gameId}">
                                                    <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete?')">Delete</button>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>

                        <!-- /.card-body -->

                        <!-- /.pagination -->
                        <div class="card-footer clearfix">
                            <div class="paginator">
                                ${requestScope.currentTotalGames} from ${requestScope.totalGames}
                            </div>

                            <c:set var="paginationUrl" value="${pageContext.servletContext.contextPath}/admin/games" />
                            <c:if test="${not empty param.genre}">
                                <c:set var="paginationUrl" value="${paginationUrl}?genre=${param.genre}" />
                            </c:if>
                            <neon:adminpagination currentPage="${param.page}" totalPages="${requestScope.numOfPages}" url="${paginationUrl}" />
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