<%-- 
    Document   : edit-game
    Created on : Mar 10, 2025, 12:23:54 AM
    Author     : Pham Van Hoai - CE181744
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../WEB-INF/include/admin-head.jsp" %>

<main class="app-main">
    <div class="app-content-header">
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-6"><h3 class="mb-0">Edit Game</h3></div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-end">
                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}">Home</a></li>
                        <li class="breadcrumb-item active">Edit Game</li>
                    </ol>
                </div>
            </div>
        </div>
    </div>

    <div class="app-content">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-8 offset-md-2">
                    <div class="card">
                        <div class="card-header"><h3 class="card-title">Edit Game</h3></div>
                        <div class="card-body">

                            <%-- Display error message if any --%>
                            <c:if test="${not empty errorMessage}">
                                <div class="alert alert-danger"><i class="bi bi-exclamation-triangle"></i> ${errorMessage}</div>
                            </c:if>

                            <%-- Display success message --%>
                            <c:if test="${not empty sessionScope.successMessage}">
                                <div class="alert alert-success"><i class="bi bi-check2-circle"></i> ${sessionScope.successMessage}</div>
                                <% session.removeAttribute("successMessage"); %>
                            </c:if>

                            <form action="${pageContext.request.contextPath}/admin/games" method="post" enctype="multipart/form-data">
                                <input type="hidden" name="action" value="edit">
                                <input type="hidden" name="game_id" value="${game.gameId}">

                                <div class="mb-3">
                                    <label for="title" class="form-label">Game Title</label>
                                    <input type="text" class="form-control" id="title" name="title" value="${game.title}" required>
                                </div>

                                <div class="mb-3">
                                    <label for="description" class="form-label">Description</label>
                                    <textarea class="form-control" id="description" name="description">${game.description}</textarea>
                                </div>

                                <div class="mb-3">
                                    <label for="image" class="form-label">Image</label>
                                    <input type="file" class="form-control" id="image" name="image" accept="image/*">
                                    <c:if test="${not empty game.imageUrl}">
                                        <p><img src="${pageContext.request.contextPath}/assets/img/cards/${game.imageUrl}" alt="Game Image" width="100"></p>
                                        </c:if>
                                </div>

                                <div class="mb-3">
                                    <label for="price" class="form-label">Price (VND)</label>
                                    <input type="number" class="form-control" id="price" name="price" min="0" step="0.01" value="${game.price}" required>
                                </div>

                                <div class="mb-3">
                                    <label for="release_date" class="form-label">Release Date</label>
                                    <input type="date" class="form-control" id="release_date" name="release_date" value="${game.releaseDate}">
                                </div>

                                <%-- Dropdowns for categories --%>
                                <c:forEach items="${[ 
                                                    ['genres', 'Genres', allGenres, game.genres], 
                                                    ['categories', 'Categories', allCategories, game.categories], 
                                                    ['developers', 'Developers', allDevelopers, game.developers], 
                                                    ['publishers', 'Publishers', allPublishers, game.publishers], 
                                                    ['platforms', 'Platforms', allPlatforms, game.platforms] 
                                                    ]}" var="attr">
                                           <div class="mb-3">
                                               <label for="${attr[0]}" class="form-label">${attr[1]}</label>
                                               <select class="form-select" id="${attr[0]}" name="${attr[0]}" multiple>
                                                   <c:forEach items="${attr[2]}" var="option">
                                                       <c:set var="isSelected" value="false" />
                                                       <c:forEach items="${attr[3]}" var="selectedValue">
                                                           <c:if test="${selectedValue eq option}">
                                                               <c:set var="isSelected" value="true" />
                                                           </c:if>
                                                       </c:forEach>
                                                       <option value="${option}" ${isSelected ? 'selected' : ''}>${option}</option>
                                                   </c:forEach>
                                               </select>
                                           </div>
                                </c:forEach>

                                <button type="submit" class="btn btn-primary">Update Game</button>
                                <a href="${pageContext.request.contextPath}/admin/games" class="btn btn-secondary">Cancel</a>
                            </form>
                        </div>
                    </div> 
                </div>
            </div>
        </div>
    </div>
</main>

<%@ include file="../WEB-INF/include/admin-foot.jsp" %>