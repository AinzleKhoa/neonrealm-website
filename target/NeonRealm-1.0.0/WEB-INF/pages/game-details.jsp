<%-- 
    Document   : details.jsp
    Created on : Feb 24, 2025, 7:01:28 PM
    Author     : Ainzle
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header1.jsp" %>
<%@include file="/WEB-INF/include/header2.jsp" %>


<c:set var="thisGame" value="${requestScope.thisGame}"/> <%-- thisGame --%>

<!-- section -->
<section class="section section--first section--bg--details" data-bg="${pageContext.servletContext.contextPath}/assets/img/banner/cityneon.jpg">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="details">
                    <div class="details__head">
                        <div class="details__cover">
                            <img src="${pageContext.servletContext.contextPath}/assets/img/cards/${thisGame.imageUrl}" alt="">
                        </div>

                        <div class="details__wrap">
                            <h3 class="details__title">${thisGame.title}</h3>

                            <ul class="details__list">
                                <li><span>Release date:</span>${thisGame.releaseDate}</li>
                                <li><span>Genres:</span>${thisGame.formattedGenres}<li>
                                <li><span>Categories:</span>${thisGame.formattedCategories}<li>
                                <li><span>Developers:</span>${thisGame.formattedDevelopers}</li>
                                <li><span>Publishers:</span>${thisGame.formattedPublishers}</li>
                            </ul>

                            <div class="details__text">
                                <p>${thisGame.description}</p>
                            </div>
                        </div>
                    </div>

                    <div class="details__gallery">
                        <div class="details__carousel owl-carousel" id="details__carousel">
                            <a href="${pageContext.servletContext.contextPath}/assets/img/details/1-1.jpg" >
                                <img src="${pageContext.servletContext.contextPath}/assets/img/details/1.jpg" alt="">
                            </a>

                            <a href="${pageContext.servletContext.contextPath}/assets/img/details/2-2.jpg" >
                                <img src="${pageContext.servletContext.contextPath}/assets/img/details/2.jpg" alt="">
                            </a>

                            <a href="${pageContext.servletContext.contextPath}/assets/img/details/3-3.jpg" >
                                <img src="${pageContext.servletContext.contextPath}/assets/img/details/3.jpg" alt="">
                            </a>

                            <a href="${pageContext.servletContext.contextPath}/assets/img/details/4-4.jpg" >
                                <img src="${pageContext.servletContext.contextPath}/assets/img/details/4.jpg" alt="">
                            </a>

                            <a href="${pageContext.servletContext.contextPath}/assets/img/details/5-5.jpg" >
                                <img src="${pageContext.servletContext.contextPath}/assets/img/details/5.jpg" alt="">
                            </a>

                            <a href="${pageContext.servletContext.contextPath}/assets/img/details/6-6.jpg" >
                                <img src="${pageContext.servletContext.contextPath}/assets/img/details/6.jpg" alt="">
                            </a>
                        </div>

                        <button class="details__nav details__nav--prev" data-nav="#details__carousel" type="button">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='328 112 184 256 328 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>
                        <button class="details__nav details__nav--next" data-nav="#details__carousel" type="button">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='184 112 328 256 184 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>
                    </div>

                    <div class="details__cart">
                        <span class="details__cart-title">Available on platforms:</span>
                        <ul class="details__platforms">
                            <c:forEach var="platform" items="${thisGame.platforms}">
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

                        <span class="details__cart-title">PRICE</span>
                        <div class="details__price">
                            <span>$ ${thisGame.price}</span>
                        </div>

                        <div class="details__actions">
                            <button class="details__buy" game-buy-now-id="${thisGame.gameId}" type="button">Buy now</button>

                            <button class="details__favorite" game-id="${thisGame.gameId}" type="button">
                                <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><circle cx='176' cy='416' r='16' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><circle cx='400' cy='416' r='16' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><polyline points='48 80 112 80 160 352 416 352' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><path d='M160,288H409.44a8,8,0,0,0,7.85-6.43l28.8-144a8,8,0,0,0-7.85-9.57H128' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/></svg>Add to Carts
                            </button>
                        </div>
                    </div>

                    <div class="details__content">
                        <div class="row">
                            <div class="col-12">
                                <!-- requirements -->
                                <div class="requirements">
                                    <span class="requirements__title">Minimum requirements:</span>
                                    <ul class="requirements__list">
                                        <li><span>OS:</span> Windows 7,Windows 8.1,Windows 10</li>
                                        <li><span>Processor:</span> Intel Core i5-2400s @ 2.5 GHz or AMD FX-6350 @ 3.9 GHz</li>
                                        <li><span>Memory:</span> 6 GB RAM</li>
                                        <li><span>Graphics:</span> NVIDIA GeForce GTX 660 or AMD R9 270 (2048 MB VRAM with Shader Model 5.0)</li>
                                        <li><span>Disk Space:</span> 42 GB available space</li>
                                        <li>Architecture: Requires a 64-bit processor and OS</li>
                                        <li><span>API:</span> DirectX 11</li>
                                        <li><span>Miscellaneous:</span> Video Preset: Lowest (720p)</li>
                                    </ul>

                                    <span class="requirements__title">Recommended requirements:</span>
                                    <ul class="requirements__list">
                                        <li><span>OS:</span> Windows 7,Windows 8.1,Windows 10</li>
                                        <li><span>Processor:</span> Intel Core i5-2400s @ 2.5 GHz or AMD FX-6350 @ 3.9 GHz</li>
                                        <li><span>Memory:</span> 6 GB RAM</li>
                                        <li><span>Graphics:</span> NVIDIA GeForce GTX 660 or AMD R9 270 (2048 MB VRAM with Shader Model 5.0)</li>
                                        <li><span>Disk Space:</span> 42 GB available space</li>
                                        <li>Architecture: Requires a 64-bit processor and OS</li>
                                        <li><span>API:</span> DirectX 11</li>
                                        <li><span>Miscellaneous:</span> Video Preset: Lowest (720p)</li>
                                    </ul>
                                </div>
                                <!-- end requirements -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</section>
<!-- end section -->

<!-- best sellers -->
<section class="section section--bg section--first" data-bg="${pageContext.servletContext.contextPath}/assets/img/banner/city.jpg">
    <div class="container">
        <div class="row">
            <!-- title -->
            <div class="col-12">
                <div class="section__title-wrap">
                    <h2 class="section__title section__title--title"><b>Similar Genres</b></h2>

                    <div class="section__nav-wrap">
                        <button class="section__nav section__nav--bg section__nav--prev" type="button" data-nav="#carousel0">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='328 112 184 256 328 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>

                        <button class="section__nav section__nav--bg section__nav--next" type="button" data-nav="#carousel0">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='184 112 328 256 184 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>
                    </div>
                </div>
            </div>
            <!-- end title -->
        </div>
    </div>
    <!-- carousel -->
    <div class="owl-carousel owl-carousel-gamedetails section__carousel section__carousel--big" id="carousel0">

        <c:forEach var="matchingGame" items="${requestScope.matchingGames}">
            <!-- big card -->
            <div class="card card--big">
                <a href="${pageContext.servletContext.contextPath}/gamedetails?id=${matchingGame.gameId}" class="card__cover">
                    <img src="${pageContext.servletContext.contextPath}/assets/img/cards/${matchingGame.imageUrl}" alt="">
                </a>

                <div class="card__wrap">
                    <div class="card__title">
                        <h3><a href="${pageContext.servletContext.contextPath}/pages/details.jsp">${matchingGame.description}</a></h3>
                    </div>

                    <ul class="card__list">
                        <li><span>Release date:</span>${matchingGame.releaseDate}</li>
                        <li><span>Genres:</span>${matchingGame.formattedGenres}</li>
                    </ul>

                    <ul class="card__platforms">
                        <c:forEach var="platform" items="${matchingGame.platforms}">
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
                    <div class="card__price">
                        <span>$ ${matchingGame.price}</span>
                    </div>
                    <div class="card__actions">
                        <button class="card__buy" game-buy-now-id="${matchingGame.gameId}" type="button">Buy now</button>

                        <button class="card__favorite" game-id="${matchingGame.gameId}" type="button">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><circle cx='176' cy='416' r='16' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><circle cx='400' cy='416' r='16' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><polyline points='48 80 112 80 160 352 416 352' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><path d='M160,288H409.44a8,8,0,0,0,7.85-6.43l28.8-144a8,8,0,0,0-7.85-9.57H128' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/></svg>
                        </button>
                    </div>
                </div>
            </div>
            <!-- end big card -->
        </c:forEach>
    </div>
    <!-- end carousel -->
</section>
<!-- end card -->
<script src="${pageContext.servletContext.contextPath}/assets/js/game_details_addToCart.js?v=<%= System.currentTimeMillis()%>"></script>
<script src="${pageContext.servletContext.contextPath}/assets/js/cart_addToCart.js?v=<%= System.currentTimeMillis()%>"></script>
<script src="${pageContext.servletContext.contextPath}/assets/js/buy_now.js?v=<%= System.currentTimeMillis()%>"></script>
<script src="${pageContext.servletContext.contextPath}/assets/js/game_details_buy_now.js?v=<%= System.currentTimeMillis()%>"></script>

<%@include file="/WEB-INF/include/footer1.jsp" %>
<%@include file="/WEB-INF/include/footer2.jsp" %>