<%-- 
    Document   : checkout.jsp
    Created on : Feb 24, 2025, 6:54:06 PM
    Author     : Ainzle
--%>

<%@page import="gameshop.model.Game"%>
<%@page import="gameshop.model.Cart"%>
<%@page import="java.util.List"%>
<%@page import="gameshop.DAO.CartDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header1.jsp" %>
<header class="header">
    <div class="header__wrap">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="header__content">
                        <button class="header__menu" type="button">
                            <span></span>
                            <span></span>
                            <span></span>
                        </button>

                        <a href="${pageContext.servletContext.contextPath}/home" class="header__logo">
                            <img src="${pageContext.servletContext.contextPath}/assets/img/logo.png" alt="">
                        </a>

                        <ul class="header__nav">
                            <li class="header__nav-item">
                                <a class="header__nav-link" href="${pageContext.servletContext.contextPath}/home">Home</a>
                            </li>								
                            <li class="header__nav-item">
                                <a class="header__nav-link" href="${pageContext.servletContext.contextPath}/catalog">Catalog</a>
                            </li>
                            <li class="header__nav-item">
                                <a class="header__nav-link header__nav-link--more" href="#" role="button" id="dropdownMenu3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'><circle cx='256' cy='256' r='32' style='fill:none; stroke-miterlimit:10;stroke-width:32px'/><circle cx='416' cy='256' r='32' style='fill:none;stroke-miterlimit:10;stroke-width:32px'/><circle cx='96' cy='256' r='32' style='fill:none;stroke-miterlimit:10;stroke-width:32px'/></svg>
                                </a>

                                <ul class="dropdown-menu header__nav-menu header__nav-menu--scroll" aria-labelledby="dropdownMenu3">
                                    <li><a href="${pageContext.servletContext.contextPath}/checkout">Checkout</a></li>
                                    <li><a href="${pageContext.servletContext.contextPath}/about">About</a></li>
                                    <li><a href="${pageContext.servletContext.contextPath}/profile">Profile</a></li>
                                    <li><a href="${pageContext.servletContext.contextPath}/login">Sign in</a></li>
                                    <li><a href="${pageContext.servletContext.contextPath}/signup">Sign up</a></li>
                                    <li><a href="${pageContext.servletContext.contextPath}/forgot">Forgot password</a></li>
                                    <li><a href="${pageContext.servletContext.contextPath}/privacy">Privacy policy</a></li>
                                    <li><a href="${pageContext.servletContext.contextPath}/404">404 Page</a></li>
                                </ul>
                            </li>
                        </ul>
                        <%
                            int cartCount = (request.getAttribute("cartCount") != null) ? (int) request.getAttribute("cartCount") : 0;
                            double totalPrice = (request.getAttribute("totalPrice") != null) ? (double) request.getAttribute("totalPrice") : 0.0;
                        %>
                        <div class="header__actions">
                            <div class="header__lang">
                                <c:choose>
                                    <c:when test="${not empty user}">
                                        <a href="${pageContext.servletContext.contextPath}/CheckoutServlet" class="header__link">
                                            <svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 512 512'>
                                                <circle cx='176' cy='416' r='16' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/>
                                                <circle cx='400' cy='416' r='16' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/>
                                                <polyline points='48 80 112 80 160 352 416 352' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/>
                                                <path d='M160,288H409.44a8,8,0,0,0,7.85-6.43l28.8-144a8,8,0,0,0-7.85-9.57H128' style='fill:none;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px'/>
                                            </svg>
                                            <span id="cart-total-price">$${totalPrice}</span>
                                            <span id="cart-count">(${cartCount})</span>     
                                </c:choose>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
</header>
<!-- page title -->
<section class="section section--first section--last section--head" data-bg="<%= getServletContext().getContextPath()%>/assets/img/bg.jpg">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="section__wrap">
                    <!-- section title -->
                    <h2 class="section__title">Checkout</h2>
                    <!-- end section title -->

                    <!-- breadcrumb -->
                    <ul class="breadcrumb">
                        <li class="breadcrumb__item"><a href="index.html">Home</a></li>
                        <li class="breadcrumb__item breadcrumb__item--active">Checkout</li>
                    </ul>
                    <!-- end breadcrumb -->
                </div>
            </div>
        </div>
    </div>
</section>
<!-- end page title -->

<!-- section -->
a<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-12 col-lg-8">
                <!-- cart -->
                <div class="cart">
                    <div class="table-responsive">
                        <table class="cart__table">
                            <thead>
                                <tr>
                                    <th><a href="#">Product <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"><path d="M9.71,10.21,12,7.91l2.29,2.3a1,1,0,0,0,1.42,0,1,1,0,0,0,0-1.42l-3-3a1,1,0,0,0-1.42,0l-3,3a1,1,0,0,0,1.42,1.42Zm4.58,4.58L12,17.09l-2.29-2.3a1,1,0,0,0-1.42,1.42l3,3a1,1,0,0,0,1.42,0l3-3a1,1,0,0,0-1.42-1.42Z"/></svg></a></th>
                                    <th><a href="#" class="active">Title <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"><path d="M17,13.41,12.71,9.17a1,1,0,0,0-1.42,0L7.05,13.41a1,1,0,0,0,0,1.42,1,1,0,0,0,1.41,0L12,11.29l3.54,3.54a1,1,0,0,0,.7.29,1,1,0,0,0,.71-.29A1,1,0,0,0,17,13.41Z"/></svg></a></th>
                                    <th><a href="#">Platform <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"><path d="M9.71,10.21,12,7.91l2.29,2.3a1,1,0,0,0,1.42,0,1,1,0,0,0,0-1.42l-3-3a1,1,0,0,0-1.42,0l-3,3a1,1,0,0,0,1.42,1.42Zm4.58,4.58L12,17.09l-2.29-2.3a1,1,0,0,0-1.42,1.42l3,3a1,1,0,0,0,1.42,0l3-3a1,1,0,0,0-1.42-1.42Z"/></svg></a></th>
                                    <th><a href="#">Price <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"><path d="M9.71,10.21,12,7.91l2.29,2.3a1,1,0,0,0,1.42,0,1,1,0,0,0,0-1.42l-3-3a1,1,0,0,0-1.42,0l-3,3a1,1,0,0,0,1.42,1.42Zm4.58,4.58L12,17.09l-2.29-2.3a1,1,0,0,0-1.42,1.42l3,3a1,1,0,0,0,1.42,0l3-3a1,1,0,0,0-1.42-1.42Z"/></svg></a></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <%
                                Game game = (Game) request.getAttribute("checkoutGame");
                            %>
                            <tbody>
                                <tr>
                                    <td>
                                        <div class="cart__img">
                                            <img src="<%= getServletContext().getContextPath()%>/assets/img/cards/<%= game.getImageUrl()%>" alt="">
                                        </div>
                                    </td>
                                    <td><a href="#"><%=game.getTitle()%></a></td>
                                    <td>
                                        <ul>
                                            <% if (game != null && game.getPlatforms() != null) { %>
                                            <% for (String platform : game.getPlatforms()) {%>
                                            <li><%= platform%></li>
                                                <% } %>
                                                <% } else { %>
                                            <li>No platforms available</li> <!-- Xử lý trường hợp null -->
                                                <% }%>
                                        </ul>
                                    </td>

                                    <td><span class="cart__price"><%=game.getPrice()%></span></td>
                                    <td>
                                        <form action="CheckoutServlet" method="post">
                                            <input type="hidden" name="action" value="delete">
                                                <input type="hidden" name="gameId" value="<%= game.getGameId()%>">

                                                    </form>
                                                    </td>
                                                    </tr>
                                                    </tbody>
                                                    </table>
                                                    </div>

                                                    <div class="cart__info">
                                                        <div class="cart__total">

                                                            <p>Total:</p>
                                                            <span>$<%= String.format("%.2f", game.getPrice())%></span>
                                                        </div>

                                                        <div class="cart__systems">
                                                            <i class="pf pf-visa"></i>
                                                            <i class="pf pf-mastercard"></i>
                                                            <i class="pf pf-paypal"></i>
                                                        </div>
                                                    </div>
                                                    </div>

                                                    <!-- end cart -->
                                                    </div>

                                                    <div class="col-12 col-lg-4">
                                                        <!-- checkout -->
                                                        <form action="#" class="form form--first form--coupon">
                                                            <input type="text" class="form__input" placeholder="Coupon code">
                                                                <button type="button" class="form__btn">Apply</button>
                                                        </form>
                                                        <!-- end checkout -->

                                                        <!-- checkout -->
                                                        <form action="#" class="form">
                                                            <input type="text" name="name" class="form__input" placeholder="John Doe">
                                                                <input type="text" name="email" class="form__input" placeholder="neon@realm.buy">
                                                                    <input type="text" name="phone" class="form__input" placeholder="+1 234 567-89-00">
                                                                        <select name="systems" class="form__select">
                                                                            <option value="visa">Visa</option>
                                                                            <option value="mastercard">Mastercard</option>
                                                                            <option value="paypal">Paypal</option>
                                                                        </select>
                                                                        <span class="form__text">There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form.</span>
                                                                        <button type="button" class="form__btn">Complete</button>
                                                                        </form>
                                                                        <!-- end checkout -->
                                                                        </div>
                                                                        </div>	
                                                                        </div>
                                                                        </div>
                                                                        <!-- end section -->

                                                                        <!-- section -->
                                                                        <div class="section section--last">
                                                                            <div class="container">
                                                                                <div class="row">
                                                                                    <div class="col-12">
                                                                                        <div class="partners owl-carousel">
                                                                                            <a href="#" class="partners__img">
                                                                                                <img src="<%= getServletContext().getContextPath()%>/assets/img/partners/3docean-light-background.png" alt="">
                                                                                            </a>

                                                                                            <a href="#" class="partners__img">
                                                                                                <img src="<%= getServletContext().getContextPath()%>/assets/img/partners/activeden-light-background.png" alt="">
                                                                                            </a>

                                                                                            <a href="#" class="partners__img">
                                                                                                <img src="<%= getServletContext().getContextPath()%>/assets/img/partners/audiojungle-light-background.png" alt="">
                                                                                            </a>

                                                                                            <a href="#" class="partners__img">
                                                                                                <img src="<%= getServletContext().getContextPath()%>/assets/img/partners/codecanyon-light-background.png" alt="">
                                                                                            </a>

                                                                                            <a href="#" class="partners__img">
                                                                                                <img src="<%= getServletContext().getContextPath()%>/assets/img/partners/photodune-light-background.png" alt="">
                                                                                            </a>

                                                                                            <a href="#" class="partners__img">
                                                                                                <img src="<%= getServletContext().getContextPath()%>/assets/img/partners/themeforest-light-background.png" alt="">
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <!-- end section -->
                                                                        <%@include file="/WEB-INF/include/footer1.jsp" %>
                                                                        <%@include file="/WEB-INF/include/footer2.jsp" %>
