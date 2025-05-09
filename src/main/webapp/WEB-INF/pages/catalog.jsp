<%-- 
    Document   : catalog
    Created on : Feb 23, 2025, 9:41:54 PM
    Author     : Le Anh Khoa - CE190449
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ taglib prefix="neon" uri="/WEB-INF/tags/implicit.tld" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header1.jsp" %>
<%@include file="/WEB-INF/include/header2.jsp" %>

<!-- page title -->
<section class="section section--first section--last section--head" data-bg="${pageContext.servletContext.contextPath}/assets/img/banner/catalog.jpg">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="section__wrap">
                    <!-- section title -->
                    <h2 class="section__title">Catalog</h2>
                    <!-- end section title -->

                    <!-- breadcrumb -->
                    <ul class="breadcrumb">
                        <li class="breadcrumb__item"><a href="${pageContext.servletContext.contextPath}/home">Home</a></li>
                        <li class="breadcrumb__item breadcrumb__item--active">Catalog</li>
                    </ul>
                    <!-- end breadcrumb -->
                </div>
            </div>
        </div>
    </div>
</section>
<!-- end page title -->
<!-- section -->
<section class="section section--last section--catalog">
    <div class="container">
        <!-- catalog -->
        <div class="row catalog">
            <!-- filter wrap -->
            <div class="col-12 col-lg-20">
                <div class="row">
                    <div class="col-12">
                        <div class="filter-wrap">
                            <button class="filter-wrap__btn" type="button" data-toggle="collapse" data-target="#collapseFilter" aria-expanded="false" aria-controls="collapseFilter">Open filter</button>

                            <div class="collapse filter-wrap__content" id="collapseFilter">
                                <!-- filter -->
                                <form class="filter" method="GET" action="${pageContext.servletContext.contextPath}/catalog">

                                    <h4 class="filter__title">Filters <a type="button" href="${pageContext.servletContext.contextPath}/catalog">Clear all</a></h4>
                                    <div class="filter__group">
                                        <label class="filter__label">Keyword:</label>
                                        <input type="text" class="filter__input" name="keyword" placeholder="Keyword">
                                    </div>

                                    <div class="filter__group">
                                        <label class="filter__label">Platforms:</label>
                                        <ul class="filter__checkboxes">
                                            <c:forEach var="platformName" items="${requestScope.platformNames}" varStatus="loop">
                                                <li>
                                                    <input id="platform${loop.index + 1}" type="checkbox" name="platforms" value="${platformName}">
                                                    <label for="platform${loop.index + 1}">${platformName}</label>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>

                                    <div class="filter__group">
                                        <label class="filter__label">Genres:</label>
                                        <ul class="filter__checkboxes">
                                            <c:forEach var="genreName" items="${requestScope.genreNames}" varStatus="loop">
                                                <li>
                                                    <input id="genre${loop.index + 1}" type="checkbox" name="genres" value="${genreName}">
                                                    <label for="genre${loop.index + 1}">${genreName}</label>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>

                                    <div class="filter__group">
                                        <button class="filter__btn" type="submit">APPLY FILTER</button>
                                    </div>
                                </form>
                                <!-- end filter -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- end filter wrap -->

            <!-- content wrap -->
            <div class="col-12 col-lg-80">
                <c:choose>
                    <c:when test="${not empty requestScope.filteredGamesPage}">

                        <c:set var="filterParams" value=""/>
                        <c:if test="${not empty selectedPlatforms}">
                            <c:set var="filterParams" value="${filterParams}${empty filterParams ? '' : '&'}platforms=${fn:join(selectedPlatforms, ',')}" />
                            <p style="color: white;">Platforms: ${fn:join(requestScope.selectedPlatforms, ', ')}</p>
                        </c:if>
                        <c:if test="${not empty selectedGenres}">
                            <c:set var="filterParams" value="${filterParams}${empty filterParams ? '' : '&'}genres=${fn:join(selectedGenres, ',')}" />
                            <p style="color: white;">Genres: ${fn:join(requestScope.selectedGenres, ', ')}</p>
                        </c:if>
                        <c:if test="${not empty keyword}">
                            <c:set var="filterParams" value="${filterParams}${empty filterParams ? '' : '&'}keyword=${keyword}" />
                            <p style="color: white;">Keyword: ${keyword}</p>
                        </c:if>

                        <!-- Update pagination to include filters -->
                        <div class="col-12">
                            <div class="paginator">
                                <neon:pagination currentPage="${param.page}" totalPages="${requestScope.numOfPages}" url="${pageContext.servletContext.contextPath}/catalog${empty filterParams ? '' : '?'}${filterParams}"/>
                                <div class="paginator__counter">
                                    ${requestScope.currentTotalGames} from ${requestScope.totalGames}
                                </div>
                            </div>
                        </div>
                        <!-- end paginator -->
                        <div class="row">
                            <c:forEach var="g" items="${requestScope.filteredGamesPage}">
                                <!-- card -->
                                <div class="col-12 col-sm-6 col-md-4 col-xl-3">
                                    <div class="card card--catalog">
                                        <a href="${pageContext.servletContext.contextPath}/gamedetails?id=${g.gameId}" class="card__cover">
                                            <img src="${pageContext.servletContext.contextPath}/assets/img/cards/${g.imageUrl}" alt="${g.title}">
                                            <%--<span class="card__new">New</span>--%>
                                        </a>

                                        <ul class="card__platforms">
                                            <c:forEach var="platform" items="${g.platforms}">
                                                <c:set var="iconPath" value=""/>
                                                <c:set var="iconName" value=""/>
                                                <c:choose>
                                                    <c:when test="${platform == 'Windows'}">
                                                        <c:set var="iconPath" value="windows.svg" />
                                                        <c:set var="iconName" value="Windows" />
                                                    </c:when>
                                                    <c:when test="${platform == 'PlayStation'}">
                                                        <c:set var="iconPath" value="playstation.svg" />
                                                        <c:set var="iconName" value="PlayStation" />
                                                    </c:when>
                                                    <c:when test="${platform == 'Xbox'}">
                                                        <c:set var="iconPath" value="xbox.svg" />
                                                        <c:set var="iconName" value="Xbox" />
                                                    </c:when>
                                                    <c:when test="${platform == 'Nintendo Switch'}">
                                                        <c:set var="iconPath" value="nintendo.svg" />
                                                        <c:set var="iconName" value="Nintendo Switch" />
                                                    </c:when>
                                                    <c:when test="${platform == 'Mobile'}">
                                                        <c:set var="iconPath" value="mobile.svg" />
                                                        <c:set var="iconName" value="Mobile" />
                                                    </c:when>
                                                    <c:when test="${platform == 'VR'}">
                                                        <c:set var="iconPath" value="vr.svg" />
                                                        <c:set var="iconName" value="VR" />
                                                    </c:when>
                                                    <c:when test="${platform == 'Mac'}">
                                                        <c:set var="iconPath" value="mac.svg" />
                                                        <c:set var="iconName" value="Mac" />
                                                    </c:when>
                                                    <c:when test="${platform == 'Linux'}">
                                                        <c:set var="iconPath" value="linux.svg" />
                                                        <c:set var="iconName" value="Linux" />
                                                    </c:when>
                                                </c:choose>
                                                <li class="ps">
                                                    <img src="${pageContext.servletContext.contextPath}/assets/img/platforms/${iconPath}" alt="${iconName}">
                                                </li>
                                            </c:forEach>
                                        </ul>

                                        <div class="card__title">
                                            <h3><a href="${pageContext.servletContext.contextPath}/gamedetails?id=${g.gameId}">${g.title}</a></h3>
                                            <span>$ ${g.price}</span>
                                        </div>

                                        <div class="card__actions">
                                            <button class="card__buy" game-buy-now-id="${g.gameId}" type="button">Buy</button>

                                            <button class="card__favorite" game-id="${g.gameId}" type="button">
                                                <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><circle cx='176' cy='416' r='16' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><circle cx='400' cy='416' r='16' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><polyline points='48 80 112 80 160 352 416 352' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><path d='M160,288H409.44a8,8,0,0,0,7.85-6.43l28.8-144a8,8,0,0,0-7.85-9.57H128' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/></svg>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <!-- end card -->
                            </c:forEach>

                            <!-- paginator -->
                            <div class="col-12">
                                <div class="paginator">
                                    <div class="paginator__counter">
                                        ${requestScope.currentTotalGames} from ${requestScope.totalGames}
                                    </div>

                                    <neon:pagination currentPage="${param.page}" totalPages="${requestScope.numOfPages}" url="${pageContext.servletContext.contextPath}/catalog?${not empty filterParams ? '?' : ''}${filterParams}"/>

                                    <%--
                                    <ul class="paginator__wrap">
                                        <li class="paginator__item paginator__item--prev">
                                            <a href="#">
                                                <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='244 400 100 256 244 112' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/><line x1='120' y1='256' x2='412' y2='256' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                                            </a>
                                        </li>
                                        <li class="paginator__item paginator__item--active"><a href="#">1</a></li>
                                        <li class="paginator__item"><a href="#">2</a></li>
                                        <li class="paginator__item"><a href="#">3</a></li>
                                        <li class="paginator__item paginator__item--next">
                                            <a href="#">
                                                <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='268 112 412 256 268 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/><line x1='392' y1='256' x2='100' y2='256' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                                            </a>
                                        </li>
                                    </ul>
                                    --%>
                                </div>
                            </div>
                            <!-- end paginator -->
                        </div>
                    </c:when>
                    <c:otherwise>

                        <c:if test="${not empty requestScope.selectedPlatforms}">
                            <p style="color: white;">Platforms: ${fn:join(requestScope.selectedPlatforms, ', ')}</p>
                        </c:if>
                        <c:if test="${not empty requestScope.selectedGenres}">
                            <p style="color: white;">Genres: ${fn:join(requestScope.selectedGenres, ', ')}</p>
                        </c:if>
                        <c:if test="${not empty requestScope.keyword}">
                            <p style="color: white;">Keyword: ${keyword}</p>
                        </c:if>

                        <p style="color:red;">No game found.</p>
                    </c:otherwise>
                </c:choose>
            </div>
            <!-- end content wrap -->
        </div>
        <!-- end catalog -->	
    </div>
</section>
<!-- end section -->
<script src="${pageContext.servletContext.contextPath}/assets/js/cart_addToCart.js?v=<%= System.currentTimeMillis()%>"></script>
<script src="${pageContext.servletContext.contextPath}/assets/js/buy_now.js?v=<%= System.currentTimeMillis()%>"></script>

<%@include file="/WEB-INF/include/footer1.jsp" %>
<%@include file="/WEB-INF/include/footer2.jsp" %>