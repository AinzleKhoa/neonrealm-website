<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header1.jsp" %>
<%@include file="/WEB-INF/include/header2.jsp" %>
<div id="cart-notification" 
     style="display: none; position: fixed; top: 10px; left: 50%; transform: translateX(-50%);
     background: rgba(0, 128, 0, 0.9); color: white; padding: 15px 20px;
     border-radius: 8px; font-size: 16px; text-align: center; z-index: 1000;
     min-width: 250px; box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.2);">
</div>
<!-- banner -->
<section class="banner_main" style="background-image: url('${pageContext.servletContext.contextPath}/assets/img/banner/neonrealm.jpg');>
         <div class="container-fluid">
    <div class="row d_flex">
        <div class="col-md-5">
            <div class="text-bg">
                <h1>NeonRealm</h1>
                <strong>Next-Gen Gaming & Digital Store</strong>
                <span>Dive into the ultimate gaming experience with exclusive deals, cutting-edge tech, and neon-inspired aesthetics. Whether you're a casual gamer or a hardcore enthusiast, NeonRealm is your gateway to the future of gaming.</span>
                <a href="${pageContext.servletContext.contextPath}/catalog">Browse Now</a>
            </div>
        </div>
        <div class="col-md-7 padding_right1">
            <div class="text-img">
                <figure><img src="${pageContext.servletContext.contextPath}/assets/img/banner/spiderman.png" alt="#"/></figure>
            </div>
        </div>
    </div>
</div>
</section>
</div>
</header>
<!-- end banner -->

<!-- best sellers -->
<section class="section section--bg section--first" data-bg="${pageContext.servletContext.contextPath}/assets/img/banner/city.jpg">
    <div class="container">
        <div class="row">
            <!-- title -->
            <div class="col-12">
                <div class="section__title-wrap">
                    <h2 class="section__title section__title--title"><b>Best Sellers</b> of this month</h2>

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
    <div class="owl-carousel owl-carousel-home section__carousel section__carousel--big" id="carousel0">

        <c:set var="games" value="${requestScope.gameList}"/> <%-- Only once --%>

        <c:if test="${not empty games}">
            <c:forEach var="g" items="${games}">
                <c:if test="${g.categories ne null and g.categories.contains('Best Sellers')}">
                    <!-- big card -->
                    <div class="card card--big"> <%-- Ten lay theo ten variable ben model khong phai database --%>
                        <a href="${pageContext.servletContext.contextPath}/gamedetails?id=${g.gameId}" class="card__cover">
                            <img src="${pageContext.servletContext.contextPath}/assets/img/cards/${g.imageUrl}" alt="">
                        </a>

                        <div class="card__wrap">
                            <div class="card__title">
                                <h3><a href="${pageContext.servletContext.contextPath}/pages/details.jsp">${g.title}</a></h3>
                            </div>

                            <ul class="card__list">
                                <li><span>Release date:</span>${g.releaseDate}</li>
                                <li><span>Genres:</span>${g.formattedGenres}</li>
                            </ul>

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

                            <div class="card__price">
                                <span>$ ${g.price}</span>
                            </div>

                            <div class="card__actions">
                                <button class="card__buy" game-buy-now-id="${g.gameId}" type="button">Buy now</button>

                                <button class="card__favorite"  game-id="${g.gameId}" type="button">
                                    <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><circle cx='176' cy='416' r='16' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><circle cx='400' cy='416' r='16' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><polyline points='48 80 112 80 160 352 416 352' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><path d='M160,288H409.44a8,8,0,0,0,7.85-6.43l28.8-144a8,8,0,0,0-7.85-9.57H128' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/></svg>
                                </button>
                            </div>
                        </div>
                    </div>
                    <!-- end big card -->
                </c:if>
            </c:forEach>
        </c:if>
    </div>
    <!-- end carousel -->
</section>
<!-- end best sellers -->

<!-- New & Trending -->
<section class="section section--bg section--first" data-bg="${pageContext.servletContext.contextPath}/assets/img/banner/city.jpg">
    <div class="container">
        <div class="row">
            <!-- title -->
            <div class="col-12">
                <div class="section__title-wrap">
                    <h2 class="section__title section__title--title"><b>New & Trending</b></h2>

                    <div class="section__nav-wrap">
                        <button class="section__nav section__nav--bg section__nav--prev" type="button" data-nav="#carousel1">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='328 112 184 256 328 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>

                        <button class="section__nav section__nav--bg section__nav--next" type="button" data-nav="#carousel1">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='184 112 328 256 184 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>
                    </div>
                </div>
            </div>
            <!-- end title -->
        </div>
    </div>
    <!-- carousel -->
    <div class="owl-carousel owl-carousel-home section__carousel section__carousel--big" id="carousel1">

        <c:set var="games" value="${requestScope.gameList}"/> <%-- Only once --%>

        <c:if test="${not empty games}">
            <c:forEach var="g" items="${games}">
                <c:if test="${g.categories ne null and g.categories.contains('New & Trending')}">
                    <!-- big card -->
                    <div class="card card--big"> <%-- Ten lay theo ten variable ben model khong phai database --%>
                        <a href="${pageContext.servletContext.contextPath}/gamedetails?id=${g.gameId}" class="card__cover">
                            <img src="${pageContext.servletContext.contextPath}/assets/img/cards/${g.imageUrl}" alt="">
                        </a>

                        <div class="card__wrap">
                            <div class="card__title">
                                <h3><a href="${pageContext.servletContext.contextPath}/pages/details.jsp">${g.title}</a></h3>
                            </div>

                            <ul class="card__list">
                                <li><span>Release date:</span>${g.releaseDate}</li>
                                <li><span>Genres:</span>${g.formattedGenres}</li>
                            </ul>

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

                            <div class="card__price">
                                <span>$ ${g.price}</span>
                            </div>

                            <div class="card__actions">
                                <button class="card__buy" game-buy-now-id="${g.gameId}" type="button">Buy now</button>

                                <button class="card__favorite"  game-id="${g.gameId}" type="button">
                                    <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><circle cx='176' cy='416' r='16' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><circle cx='400' cy='416' r='16' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><polyline points='48 80 112 80 160 352 416 352' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><path d='M160,288H409.44a8,8,0,0,0,7.85-6.43l28.8-144a8,8,0,0,0-7.85-9.57H128' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/></svg>
                                </button>
                            </div>
                        </div>
                    </div>
                    <!-- end big card -->
                </c:if>
            </c:forEach>
        </c:if>
    </div>
    <!-- end carousel -->
</section>
<!-- end New & Trending -->

<!-- Pre-Orders -->
<section class="section section--bg section--first" data-bg="${pageContext.servletContext.contextPath}/assets/img/banner/city.jpg">
    <div class="container">
        <div class="row">
            <!-- title -->
            <div class="col-12">
                <div class="section__title-wrap">
                    <h2 class="section__title section__title--title"><b>Pre-Orders</b></h2>

                    <div class="section__nav-wrap">
                        <button class="section__nav section__nav--bg section__nav--prev" type="button" data-nav="#carousel2">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='328 112 184 256 328 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>

                        <button class="section__nav section__nav--bg section__nav--next" type="button" data-nav="#carousel2">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='184 112 328 256 184 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>
                    </div>
                </div>
            </div>
            <!-- end title -->
        </div>
    </div>
    <!-- carousel -->
    <div class="owl-carousel owl-carousel-home section__carousel section__carousel--big" id="carousel2">

        <c:set var="games" value="${requestScope.gameList}"/> <%-- Only once --%>

        <c:if test="${not empty games}">
            <c:forEach var="g" items="${games}">
                <c:if test="${g.categories ne null and g.categories.contains('Pre-Orders')}">
                    <!-- big card -->
                    <div class="card card--big"> <%-- Ten lay theo ten variable ben model khong phai database --%>
                        <a href="${pageContext.servletContext.contextPath}/gamedetails?id=${g.gameId}" class="card__cover">
                            <img src="${pageContext.servletContext.contextPath}/assets/img/cards/${g.imageUrl}" alt="">
                        </a>

                        <div class="card__wrap">
                            <div class="card__title">
                                <h3><a href="${pageContext.servletContext.contextPath}/pages/details.jsp">${g.title}</a></h3>
                            </div>

                            <ul class="card__list">
                                <li><span>Release date:</span>${g.releaseDate}</li>
                                <li><span>Genres:</span>${g.formattedGenres}</li>
                            </ul>

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

                            <div class="card__price">
                                <span>$ ${g.price}</span>
                            </div>

                            <div class="card__actions">
                                <button class="card__buy" game-buy-now-id="${g.gameId}" type="button">Buy now</button>

                                <button class="card__favorite"  game-id="${g.gameId}" type="button">
                                    <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><circle cx='176' cy='416' r='16' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><circle cx='400' cy='416' r='16' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><polyline points='48 80 112 80 160 352 416 352' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><path d='M160,288H409.44a8,8,0,0,0,7.85-6.43l28.8-144a8,8,0,0,0-7.85-9.57H128' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/></svg>
                                </button>
                            </div>
                        </div>
                    </div>
                    <!-- end big card -->
                </c:if>
            </c:forEach>
        </c:if>
    </div>
    <!-- end carousel -->
</section>
<!-- end Pre-Orders -->

<!-- Top Rated -->
<section class="section section--bg section--first" data-bg="${pageContext.servletContext.contextPath}/assets/img/banner/city.jpg">
    <div class="container">
        <div class="row">
            <!-- title -->
            <div class="col-12">
                <div class="section__title-wrap">
                    <h2 class="section__title section__title--title"><b>Top Rated</b></h2>

                    <div class="section__nav-wrap">
                        <button class="section__nav section__nav--bg section__nav--prev" type="button" data-nav="#carousel3">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='328 112 184 256 328 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>

                        <button class="section__nav section__nav--bg section__nav--next" type="button" data-nav="#carousel3">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='184 112 328 256 184 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>
                    </div>
                </div>
            </div>
            <!-- end title -->
        </div>
    </div>
    <!-- carousel -->
    <div class="owl-carousel owl-carousel-home section__carousel section__carousel--big" id="carousel3">

        <c:set var="games" value="${requestScope.gameList}"/> <%-- Only once --%>

        <c:if test="${not empty games}">
            <c:forEach var="g" items="${games}">
                <c:if test="${g.categories ne null and g.categories.contains('Top Rated')}">
                    <!-- big card -->
                    <div class="card card--big"> <%-- Ten lay theo ten variable ben model khong phai database --%>
                        <a href="${pageContext.servletContext.contextPath}/gamedetails?id=${g.gameId}" class="card__cover">
                            <img src="${pageContext.servletContext.contextPath}/assets/img/cards/${g.imageUrl}" alt="">
                        </a>

                        <div class="card__wrap">
                            <div class="card__title">
                                <h3><a href="${pageContext.servletContext.contextPath}/pages/details.jsp">${g.title}</a></h3>
                            </div>

                            <ul class="card__list">
                                <li><span>Release date:</span>${g.releaseDate}</li>
                                <li><span>Genres:</span>${g.formattedGenres}</li>
                            </ul>

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

                            <div class="card__price">
                                <span>$ ${g.price}</span>
                            </div>

                            <div class="card__actions">
                                <button class="card__buy" game-buy-now-id="${g.gameId}" type="button">Buy now</button>

                                <button class="card__favorite"  game-id="${g.gameId}" type="button">
                                    <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><circle cx='176' cy='416' r='16' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><circle cx='400' cy='416' r='16' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><polyline points='48 80 112 80 160 352 416 352' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><path d='M160,288H409.44a8,8,0,0,0,7.85-6.43l28.8-144a8,8,0,0,0-7.85-9.57H128' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/></svg>
                                </button>
                            </div>
                        </div>
                    </div>
                    <!-- end big card -->
                </c:if>
            </c:forEach>
        </c:if>
    </div>
    <!-- end carousel -->
</section>
<!-- end Top Rated -->

<!-- Cozy & Casual -->
<section class="section section--bg section--first" data-bg="${pageContext.servletContext.contextPath}/assets/img/banner/city.jpg">
    <div class="container">
        <div class="row">
            <!-- title -->
            <div class="col-12">
                <div class="section__title-wrap">
                    <h2 class="section__title section__title--title"><b>Cozy & Casual</b></h2>

                    <div class="section__nav-wrap">
                        <button class="section__nav section__nav--bg section__nav--prev" type="button" data-nav="#carousel4">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='328 112 184 256 328 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>

                        <button class="section__nav section__nav--bg section__nav--next" type="button" data-nav="#carousel4">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='184 112 328 256 184 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>
                    </div>
                </div>
            </div>
            <!-- end title -->
        </div>
    </div>
    <!-- carousel -->
    <div class="owl-carousel owl-carousel-home section__carousel section__carousel--big" id="carousel4">

        <c:set var="games" value="${requestScope.gameList}"/> <%-- Only once --%>

        <c:if test="${not empty games}">
            <c:forEach var="g" items="${games}">
                <c:if test="${g.categories ne null and g.categories.contains('Cozy & Casual')}">
                    <!-- big card -->
                    <div class="card card--big"> <%-- Ten lay theo ten variable ben model khong phai database --%>
                        <a href="${pageContext.servletContext.contextPath}/gamedetails?id=${g.gameId}" class="card__cover">
                            <img src="${pageContext.servletContext.contextPath}/assets/img/cards/${g.imageUrl}" alt="">
                        </a>

                        <div class="card__wrap">
                            <div class="card__title">
                                <h3><a href="${pageContext.servletContext.contextPath}/pages/details.jsp">${g.title}</a></h3>
                            </div>

                            <ul class="card__list">
                                <li><span>Release date:</span>${g.releaseDate}</li>
                                <li><span>Genres:</span>${g.formattedGenres}</li>
                            </ul>

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

                            <div class="card__price">
                                <span>$ ${g.price}</span>
                            </div>

                            <div class="card__actions">
                                <button class="card__buy" game-buy-now-id="${g.gameId}" type="button">Buy now</button>

                                <button class="card__favorite"  game-id="${g.gameId}" type="button">
                                    <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><circle cx='176' cy='416' r='16' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><circle cx='400' cy='416' r='16' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><polyline points='48 80 112 80 160 352 416 352' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><path d='M160,288H409.44a8,8,0,0,0,7.85-6.43l28.8-144a8,8,0,0,0-7.85-9.57H128' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/></svg>
                                </button>
                            </div>
                        </div>
                    </div>
                    <!-- end big card -->
                </c:if>
            </c:forEach>
        </c:if>
    </div>
    <!-- end carousel -->
</section>
<!-- end Cozy & Casual -->

<!-- Multiplayer & Online -->
<section class="section section--bg section--first" data-bg="${pageContext.servletContext.contextPath}/assets/img/banner/city.jpg">
    <div class="container">
        <div class="row">
            <!-- title -->
            <div class="col-12">
                <div class="section__title-wrap">
                    <h2 class="section__title section__title--title"><b>Multiplayer & Online</b></h2>

                    <div class="section__nav-wrap">
                        <button class="section__nav section__nav--bg section__nav--prev" type="button" data-nav="#carousel5">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='328 112 184 256 328 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>

                        <button class="section__nav section__nav--bg section__nav--next" type="button" data-nav="#carousel5">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='184 112 328 256 184 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>
                    </div>
                </div>
            </div>
            <!-- end title -->
        </div>
    </div>
    <!-- carousel -->
    <div class="owl-carousel owl-carousel-home section__carousel section__carousel--big" id="carousel5">

        <c:set var="games" value="${requestScope.gameList}"/> <%-- Only once --%>

        <c:if test="${not empty games}">
            <c:forEach var="g" items="${games}">
                <c:if test="${g.categories ne null and g.categories.contains('Multiplayer & Online')}">
                    <!-- big card -->
                    <div class="card card--big"> <%-- Ten lay theo ten variable ben model khong phai database --%>
                        <a href="${pageContext.servletContext.contextPath}/gamedetails?id=${g.gameId}" class="card__cover">
                            <img src="${pageContext.servletContext.contextPath}/assets/img/cards/${g.imageUrl}" alt="">
                        </a>

                        <div class="card__wrap">
                            <div class="card__title">
                                <h3><a href="${pageContext.servletContext.contextPath}/pages/details.jsp">${g.title}</a></h3>
                            </div>

                            <ul class="card__list">
                                <li><span>Release date:</span>${g.releaseDate}</li>
                                <li><span>Genres:</span>${g.formattedGenres}</li>
                            </ul>

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

                            <div class="card__price">
                                <span>$ ${g.price}</span>
                            </div>

                            <div class="card__actions">
                                <button class="card__buy" game-buy-now-id="${g.gameId}" type="button">Buy now</button>

                                <button class="card__favorite"  game-id="${g.gameId}" type="button">
                                    <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><circle cx='176' cy='416' r='16' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><circle cx='400' cy='416' r='16' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><polyline points='48 80 112 80 160 352 416 352' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><path d='M160,288H409.44a8,8,0,0,0,7.85-6.43l28.8-144a8,8,0,0,0-7.85-9.57H128' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/></svg>
                                </button>
                            </div>
                        </div>
                    </div>
                    <!-- end big card -->
                </c:if>
            </c:forEach>
        </c:if>
    </div>
    <!-- end carousel -->
</section>
<!-- end Multiplayer & Online -->

<!-- Single-Player Favorites -->
<section class="section section--bg section--first" data-bg="${pageContext.servletContext.contextPath}/assets/img/banner/city.jpg">
    <div class="container">
        <div class="row">
            <!-- title -->
            <div class="col-12">
                <div class="section__title-wrap">
                    <h2 class="section__title section__title--title"><b>Single-Player Favorites</b></h2>

                    <div class="section__nav-wrap">
                        <button class="section__nav section__nav--bg section__nav--prev" type="button" data-nav="#carousel6">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='328 112 184 256 328 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>

                        <button class="section__nav section__nav--bg section__nav--next" type="button" data-nav="#carousel6">
                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><polyline points='184 112 328 256 184 400' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:48px'/></svg>
                        </button>
                    </div>
                </div>
            </div>
            <!-- end title -->
        </div>
    </div>
    <!-- carousel -->
    <div class="owl-carousel owl-carousel-home section__carousel section__carousel--big" id="carousel6">

        <c:set var="games" value="${requestScope.gameList}"/> <%-- Only once --%>

        <c:if test="${not empty games}">
            <c:forEach var="g" items="${games}">
                <c:if test="${g.categories ne null and g.categories.contains('Single-Player Favorites')}">
                    <!-- big card -->
                    <div class="card card--big"> <%-- Ten lay theo ten variable ben model khong phai database --%>
                        <a href="${pageContext.servletContext.contextPath}/gamedetails?id=${g.gameId}" class="card__cover">
                            <img src="${pageContext.servletContext.contextPath}/assets/img/cards/${g.imageUrl}" alt="">
                        </a>

                        <div class="card__wrap">
                            <div class="card__title">
                                <h3><a href="${pageContext.servletContext.contextPath}/pages/details.jsp">${g.title}</a></h3>
                            </div>

                            <ul class="card__list">
                                <li><span>Release date:</span>${g.releaseDate}</li>
                                <li><span>Genres:</span>${g.formattedGenres}</li>
                            </ul>

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

                            <div class="card__price">
                                <span>$ ${g.price}</span>
                            </div>

                            <div class="card__actions">
                                <button class="card__buy" game-buy-now-id="${g.gameId}" type="button">Buy now</button>

                                <button class="card__favorite"  game-id="${g.gameId}" type="button">
                                    <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><circle cx='176' cy='416' r='16' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><circle cx='400' cy='416' r='16' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><polyline points='48 80 112 80 160 352 416 352' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/><path d='M160,288H409.44a8,8,0,0,0,7.85-6.43l28.8-144a8,8,0,0,0-7.85-9.57H128' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/></svg>
                                </button>
                            </div>
                        </div>
                    </div>
                    <!-- end big card -->
                </c:if>
            </c:forEach>
        </c:if>
    </div>
    <!-- end carousel -->
</section>
<!-- end Single-Player Favorites -->

<%@include file="/WEB-INF/include/footer1.jsp" %>
<script src="${pageContext.servletContext.contextPath}/assets/js/cdn.bootstrap.bundle.min"></script>
<script src="${pageContext.servletContext.contextPath}/assets/js/jquery-3.5.1.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/assets/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/assets/js/owl.carousel.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/assets/js/jquery.magnific-popup.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/assets/js/wNumb.js"></script>
<script src="${pageContext.servletContext.contextPath}/assets/js/nouislider.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/assets/js/jquery.mousewheel.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/assets/js/jquery.mCustomScrollbar.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/assets/js/main.js"></script>
<script src="${pageContext.servletContext.contextPath}/assets/js/cart_addToCart.js?v=<%= System.currentTimeMillis()%>"></script>
<script src="${pageContext.servletContext.contextPath}/assets/js/buy_now.js?v=<%= System.currentTimeMillis()%>"></script>

