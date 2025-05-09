<%-- 
    Document   : add-game
    Created on : Mar 5, 2025, 5:30:00 PM
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
                <div class="col-sm-6"><h3 class="mb-0">Add Game</h3></div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-end">
                        <li class="breadcrumb-item"><a href="<%= getServletContext().getContextPath()%>">Home</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Add Game</li>
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
                            <h3 class="card-title">Add New Game</h3>
                        </div>
                        <div class="card-body">
                            <!-- Hiển thị lỗi nếu có -->
                            <c:if test="${not empty error}">
                                <div class="alert alert-danger">${error}</div>
                            </c:if>

                            <form action="${pageContext.request.contextPath}/admin/games" method="post" enctype="multipart/form-data">
                                <input type="hidden" name="action" value="add">

                                <div class="mb-3">
                                    <label for="title" class="form-label">Game Title</label>
                                    <input type="text" class="form-control" id="title" name="title" required>
                                </div>

                                <div class="mb-3">
                                    <label for="description" class="form-label">Description</label>
                                    <textarea class="form-control" id="description" name="description"></textarea>
                                </div>

                                <div class="mb-3">
                                    <label for="image" class="form-label">Image</label>
                                    <input type="file" class="form-control" id="image" name="image" accept="image/*">
                                </div>

                                <div class="mb-3">
                                    <label for="price" class="form-label">Price (VND)</label>
                                    <input type="number" class="form-control" id="price" name="price" min="0" step="0.01" required>
                                </div>

                                <div class="mb-3">
                                    <label for="release_date" class="form-label">Release Date</label>
                                    <input type="date" class="form-control" id="release_date" name="release_date">
                                </div>

                                <!-- Dropdowns for Genres, Categories, Developers, Publishers, Platforms -->
                                <div class="mb-3">
                                    <label for="genres" class="form-label">Genres</label>
                                    <select class="form-select" id="genres" name="genres" multiple>
                                        <c:forEach var="genre" items="${allGenres}">
                                            <option value="${genre}">${genre}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="mb-3">
                                    <label for="categories" class="form-label">Categories</label>
                                    <select class="form-select" id="categories" name="categories" multiple>
                                        <c:forEach var="category" items="${allCategories}">
                                            <option value="${category}">${category}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="mb-3">
                                    <label for="developers" class="form-label">Developers</label>
                                    <select class="form-select" id="developers" name="developers" multiple>
                                        <c:forEach var="developer" items="${allDevelopers}">
                                            <option value="${developer}">${developer}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="mb-3">
                                    <label for="publishers" class="form-label">Publishers</label>
                                    <select class="form-select" id="publishers" name="publishers" multiple>
                                        <c:forEach var="publisher" items="${allPublishers}">
                                            <option value="${publisher}">${publisher}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="mb-3">
                                    <label for="platforms" class="form-label">Platforms</label>
                                    <select class="form-select" id="platforms" name="platforms" multiple>
                                        <c:forEach var="platform" items="${allPlatforms}">
                                            <option value="${platform}">${platform}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <button type="submit" class="btn btn-primary">Add Game</button>
                                <a href="${pageContext.request.contextPath}/admin/games" class="btn btn-secondary">Cancel</a>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>      
</main>
<!--end::App Main-->

<%@ include file="../WEB-INF/include/admin-foot.jsp" %>