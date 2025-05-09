<%-- 
    Document   : 404.jsp
    Created on : Feb 24, 2025, 7:01:28 PM
    Author     : Ainzle
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header1.jsp" %>
<%@include file="/WEB-INF/include/header2.jsp" %>
<body>
    <!-- page 404 -->
    <div class="page-404 section--full-bg" data-bg="${pageContext.servletContext.contextPath}/assets/img/bg2.jpg">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="page-404__wrap">
                        <div class="page-404__content">
                            <h1 class="page-404__title">404</h1>
                            <p class="page-404__text">The page you are looking for not available!</p>
                            <a href="${pageContext.servletContext.contextPath}/index.jsp" class="page-404__btn">go back</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- end page 404 -->

    <%@include file="/WEB-INF/include/footer2.jsp" %>