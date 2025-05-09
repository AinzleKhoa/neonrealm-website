<%-- 
    Document   : signup.jsp
    Created on : Feb 24, 2025, 7:01:28 PM
    Author     : Ainzle
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header1.jsp" %>
<%@include file="/WEB-INF/include/header2.jsp" %>
<head>   


    <script src="https://www.google.com/recaptcha/api.js" async defer></script>


</head>
<body>
    <!-- sign in -->
    <div class="sign section--full-bg" data-bg="${pageContext.servletContext.contextPath}/assets/img/banner/cityneon.jpg">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="sign__content">
                        <!-- registration form -->
                        <form action="${pageContext.servletContext.contextPath}/signup" method="POST" class="sign__form">
                            <a href="${pageContext.servletContext.contextPath}/home" class="sign__logo">
                                <img src="${pageContext.servletContext.contextPath}/assets/img/logo.png" alt="">
                            </a>

                            <div class="sign__group">
                                <input type="text" name="username" class="sign__input" placeholder="Name" value="${param.username}" required>
                            </div>

                            <div class="sign__group">
                                <input type="text" name="email" class="sign__input" placeholder="Email" value="${param.email}" required>
                            </div>

                            <div class="sign__group">
                                <input type="password" name="password" class="sign__input" placeholder="Password" required>
                            </div>

                            <div class="sign__group">
                                <input type="password" name="confirm_password" class="sign__input" placeholder="Confirm Password" required>
                            </div>

                            <div class="g-recaptcha" data-sitekey="6LeIxAcTAAAAAJcZVRqyHh71UMIEGNQ_MXjiZKhI"></div>
                            <p id="captcha_error" style="color: red;"></p>

                            <c:if test="${not empty requestScope.errors}">
                                <div style="color: red;">
                                    <c:forEach var="error" items="${requestScope.errors}">
                                        <p>${error}</p>
                                    </c:forEach>
                                </div>
                            </c:if>

                            <button class="sign__btn" type="submit">Sign up</button>

                            <span class="sign__delimiter">or</span>

                            <div class="sign__social">
                                <a class="gh" href="<c:url value='/auth/github/authorize'/>"><svg xmlns='http://www.w3.org/2000/svg' width='512' height='512' viewBox='0 0 24 24'><path d='M12 .297c-6.63 0-12 5.373-12 12 0 5.303 3.438 9.8 8.207 11.385.6.113.793-.258.793-.577v-2.256c-3.338.724-4.033-1.416-4.033-1.416-.547-1.387-1.337-1.757-1.337-1.757-1.092-.75.083-.735.083-.735 1.207.085 1.841 1.24 1.841 1.24 1.07 1.835 2.807 1.305 3.492.998.108-.776.42-1.305.763-1.605-2.665-.3-5.467-1.332-5.467-5.93 0-1.31.468-2.382 1.24-3.222-.124-.303-.537-1.523.116-3.176 0 0 1.008-.322 3.3 1.23.957-.266 1.983-.399 3.003-.404 1.02.005 2.047.138 3.006.404 2.29-1.552 3.296-1.23 3.296-1.23.655 1.653.242 2.873.118 3.176.775.84 1.237 1.912 1.237 3.222 0 4.608-2.807 5.625-5.48 5.921.43.372.823 1.103.823 2.222v3.293c0 .322.19.694.8.576 4.765-1.582 8.2-6.079 8.2-11.38 0-6.627-5.373-12-12-12z'/></svg></a>
                            </div>
                            <a href="https://accounts.google.com/o/oauth2/auth?client_id=809722736314-p12thbcom86al3s29ob4gulekvb283mr.apps.googleusercontent.com&redirect_uri=http://localhost:8080/NeonRealm/google-callback&response_type=code&scope=email%20profile">
                                <img src="https://developers.google.com/identity/images/btn_google_signin_dark_normal_web.png" alt="Sign in with Google">
                            </a>

                            <span class="sign__text">Already have an account? <a href="${pageContext.servletContext.contextPath}/login">Sign in!</a></span>
                        </form>
                        <script>
                            function validateCaptcha() {
                                var response = grecaptcha.getResponse();
                                if (response.length === 0) {
                                    document.getElementById("captcha_error").innerText = "Please verify that you are not a robot.";
                                    return false;
                                }
                                return true;
                            }
                        </script>
                        <!-- registration form -->
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- end sign in -->
    <%@include file="/WEB-INF/include/footer2.jsp" %>