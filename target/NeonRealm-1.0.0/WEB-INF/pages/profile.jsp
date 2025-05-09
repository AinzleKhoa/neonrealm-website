<%-- 
    Document   : profile.jsp
    Created on : Feb 24, 2025, 7:01:28 PM
    Author     : Ainzle
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header1.jsp" %>
<%@include file="/WEB-INF/include/header2.jsp" %>

<c:set var="user" value="${sessionScope.currentUser}"/>
<!-- page title -->
<section class="section section--first section--last section--head" data-bg="${pageContext.servletContext.contextPath}/assets/img/banner/cityafar.jpg">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="section__wrap">
                    <!-- section title -->
                    <h2 class="section__title">Profile</h2>
                    <!-- end section title -->

                    <!-- breadcrumb -->
                    <ul class="breadcrumb">
                        <li class="breadcrumb__item"><a href="${pageContext.servletContext.contextPath}/home">Home</a></li>
                        <li class="breadcrumb__item breadcrumb__item--active">Profile</li>
                    </ul>
                    <!-- end breadcrumb -->
                </div>
            </div>
        </div>
    </div>
</section>
<!-- end page title -->

<!-- section -->
<section class="section section--last">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="profile">
                    <div class="profile__user">
                        <div class="profile__avatar">
                            <img src="${pageContext.servletContext.contextPath}/assets/img/user.svg" alt="">
                        </div>
                        <div class="profile__meta">
                            <h3>${fn:toUpperCase(user.username)}</h3>
                            <span>ID: ${user.userId}</span>
                        </div>
                    </div>

                    <c:choose>
                        <c:when test="${empty requestScope.errors}">
                            <c:set var="activeTab" value="tab-1"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="activeTab" value="tab-2"/>
                        </c:otherwise>
                    </c:choose>

                    <ul class="nav nav-tabs profile__tabs" id="profile__tabs" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link ${activeTab == 'tab-1' ? 'active' : ''}" 
                               data-toggle="tab" href="#tab-1" role="tab" 
                               aria-controls="tab-1" aria-selected="${activeTab == 'tab-1' ? 'true' : 'false'}">
                                My purchases
                            </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link ${activeTab == 'tab-2' ? 'active' : ''}" 
                               data-toggle="tab" href="#tab-2" role="tab" 
                               aria-controls="tab-2" aria-selected="${activeTab == 'tab-2' ? 'true' : 'false'}">
                                Settings
                            </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" data-toggle="tab" href="#tab-3" role="tab" aria-controls="tab-3" aria-selected="false">Logout</a>
                        </li>
                    </ul>

                </div>
            </div>	
        </div>

        <div class="container">
            <!-- content tabs -->
            <div class="tab-content">
                <div class="tab-pane fade ${activeTab == 'tab-1' ? 'show active' : ''}" id="tab-1" role="tabpanel">
                    <div class="row">
                        <div class="col-12">
                            <div class="table-responsive table-responsive--border">
                                <table class="profile__table">
                                    <thead>
                                        <tr>
                                            <th><p href="">Product</p></th>
                                            <th><p href="" class="active">Title</p></th>
                                            <th></th>
                                            <th><p href="">Price</p></th>
                                            <th><p href="" class="active">Purchased Date</p></th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <c:forEach var="o" items="${requestScope.orderHistory}">
                                            <tr>
                                                <td>
                                                    <a href="${pageContext.servletContext.contextPath}/gamedetails?id=${o.game.gameId}">
                                                        <div class="profile__img">
                                                            <img src="${pageContext.servletContext.contextPath}/assets/img/cards/${o.game.imageUrl}" alt="">
                                                        </div>
                                                    </a>
                                                </td>
                                                <td>${o.game.title}</td>
                                                <td></td>
                                                <td><span class="profile__price">${o.price}</span></td>
                                                <td>${o.order.createdAt}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <%--
                                                <!-- paginator -->
                                                <div class="col-12">
                                                    <div class="paginator">
                                                        <div class="paginator__counter">
                                                            3 from 9
                                                        </div>

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
                            </div>
                        </div>
                        <!-- end paginator -->
                        --%>
                    </div>
                </div>

                <div class="tab-pane fade ${activeTab == 'tab-2' ? 'show active' : ''}" id="tab-2" role="tabpanel">
                    <div class="row">
                        <!-- details form -->
                        <div class="col-12 col-lg-6">
                            <form action="${pageContext.servletContext.contextPath}/profile" method="POST" class="form">
                                <div class="row">
                                    <div class="col-12">
                                        <h4 class="form__title">Profile details</h4>
                                        <h4 class="form__title">${user.authProvider == 'local' ? '' : 'You can modify your username and email here. These changes will only apply to your account on this platform and will not affect your GitHub/Google account.'}</h4>
                                    </div>
                                    <input hidden name="id" value="${user.userId}"/>
                                    <input hidden name="authProvider" value="${user.authProvider}"/>

                                    <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                                        <label class="form__label" for="username">Username</label>
                                        <input id="username" type="text" name="username" class="form__input" placeholder="${user.username}" value="${user.username}" required>
                                    </div>

                                    <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                                        <label class="form__label" for="email">Email</label>
                                        <input id="email" type="text" name="email" class="form__input" placeholder="${user.email}" value="${user.email}" required>
                                    </div>

                                    <h4 class="form__title">${user.authProvider == 'local' ? '' : 'You will need to reauthorize with GitHub to make changes to your account.'}</h4>

                                    <c:if test="${user.authProvider == 'local'}">
                                        <div class="col-12">
                                            <h4 class="form__title">Change password</h4>
                                        </div>

                                        <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                                            <label class="form__label" for="newpass">New Password</label>
                                            <input id="newpass" type="password" name="newpass" class="form__input">
                                        </div>

                                        <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                                            <label class="form__label" for="confirmpass">Confirm New Password</label>
                                            <input id="confirmpass" type="password" name="confirmnewpass" class="form__input">
                                        </div>

                                        <div class="col-12">
                                            <h4 class="form__title">Your old Password is required to update profile</h4>
                                        </div>

                                        <div class="col-12">
                                            <label class="form__label" for="oldpass">Old Password</label>
                                            <input id="oldpass" type="password" name="oldpass" class="form__input" required>
                                        </div>
                                    </c:if>

                                    <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                                        <c:choose>
                                            <c:when test="${not empty requestScope.errors}">
                                                <div style="color: red;">
                                                    <c:forEach var="error" items="${requestScope.errors}">
                                                        <p>${error}</p>
                                                    </c:forEach>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <c:if test="${not empty requestScope.success}"><p style="color: greenyellow;">${requestScope.success}</p></c:if>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>

                                    <div class="col-12">
                                        <button class="form__btn" type="submit">Save</button>
                                    </div>

                                </div>
                            </form>
                        </div>
                        <!-- end details form -->
                    </div>
                </div>

                <div class="tab-pane fade tab-3" id="tab-3" role="tabpanel">
                    <div class="row">
                        <!-- details form -->
                        <div class="col-12 col-lg-6">
                            <h4 class="form__title">Are you sure you want to log out?</h4>
                            <div class="col-12">
                                <a class="form__btn" type="button" href="${pageContext.servletContext.contextPath}/logout">Yes</a>
                            </div>
                        </div>
                        <!-- end details form -->
                    </div>
                </div>


            </div>
            <!-- end content tabs -->
        </div>
</section>
<!-- end section -->

<%@include file="/WEB-INF/include/footer1.jsp" %>
<%@include file="/WEB-INF/include/footer2.jsp" %>