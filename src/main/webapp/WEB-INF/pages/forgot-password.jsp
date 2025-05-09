<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header1.jsp" %>
<%@include file="/WEB-INF/include/header2.jsp" %>

<div class="sign section--full-bg" data-bg="<%= getServletContext().getContextPath()%>/assets/img/banner/city.jpg">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="sign__content">
                    <form action="<%= request.getContextPath()%>/forgot-password" method="POST" class="sign__form">
                        <a href="<%= getServletContext().getContextPath()%>/index.jsp" class="sign__logo">
                            <img src="<%= getServletContext().getContextPath()%>/assets/img/logo.png" alt="">
                        </a>
                        <div class="sign__group">
                            <input type="email" name="email" class="sign__input" placeholder="Enter your email" required>
                        </div>
                        <button class="sign__btn" type="submit">Send</button>
                        <span class="sign__text">A new password will be sent to your email!</span>
                        <span class="sign__text" style="text-align: center;">Now login with your new password sent in your email!</span>
                        <a href="${pageContext.servletContext.contextPath}/login" class="page-404__btn" style="margin-top: 40px;">go back</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/include/footer2.jsp" %>
