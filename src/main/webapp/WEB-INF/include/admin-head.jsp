<%-- 
    Document   : admin-head
    Created on : Mar 4, 2025, 11:29:34 PM
    Author     : Pham Van Hoai - CE181744
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="user" value="${sessionScope.currentUser}"/>
<!doctype html>
<html lang="en">
    <!--begin::Head-->
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Admin | Game Store</title>
        <!--begin::Primary Meta Tags-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta name="title" content="Admin | Game Store" />
        <meta name="author" content="ColorlibHQ" />
        <meta
            name="description"
            content="AdminLTE is a Free Bootstrap 5 Admin Dashboard, 30 example pages using Vanilla JS."
            />
        <meta
            name="keywords"
            content="bootstrap 5, bootstrap, bootstrap 5 admin dashboard, bootstrap 5 dashboard, bootstrap 5 charts, bootstrap 5 calendar, bootstrap 5 datepicker, bootstrap 5 tables, bootstrap 5 datatable, vanilla js datatable, colorlibhq, colorlibhq dashboard, colorlibhq admin dashboard"
            />
        <!--end::Primary Meta Tags-->
        <!--begin::Fonts-->
        <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/@fontsource/source-sans-3@5.0.12/index.css"
            integrity="sha256-tXJfXfp6Ewt1ilPzLDtQnJV4hclT9XuaZUKyUvmyr+Q="
            crossorigin="anonymous"
            />
        <!--end::Fonts-->
        <!--begin::Third Party Plugin(OverlayScrollbars)-->
        <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/overlayscrollbars@2.10.1/styles/overlayscrollbars.min.css"
            integrity="sha256-tZHrRjVqNSRyWg2wbppGnT833E/Ys0DHWGwT04GiqQg="
            crossorigin="anonymous"
            />
        <!--end::Third Party Plugin(OverlayScrollbars)-->
        <!--begin::Third Party Plugin(Bootstrap Icons)-->
        <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"
            integrity="sha256-9kPW/n5nn53j4WMRYAxe9c1rCY96Oogo/MKSVdKzPmI="
            crossorigin="anonymous"
            />
        <!--end::Third Party Plugin(Bootstrap Icons)-->
        <!--begin::Required Plugin(AdminLTE)-->
        <link rel="stylesheet" href="<%= getServletContext().getContextPath()%>/assets/admin-template/css/adminlte.css" />
        <!--end::Required Plugin(AdminLTE)-->
    </head>
    <!--end::Head-->
    <!--begin::Body-->
    <body class="layout-fixed sidebar-expand-lg sidebar-mini bg-body-tertiary">
        <!--begin::App Wrapper-->
        <div class="app-wrapper">
            <!--begin::Header-->
            <nav class="app-header navbar navbar-expand bg-body">
                <!--begin::Container-->
                <div class="container-fluid">
                    <!--begin::Start Navbar Links-->
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" data-lte-toggle="sidebar" href="#" role="button">
                                <i class="bi bi-list"></i>
                            </a>
                        </li>
                        <li class="nav-item d-none d-md-block"><a href="<%= getServletContext().getContextPath()%>" class="nav-link">Home</a></li>
                    </ul>
                    <!--end::Start Navbar Links-->
                    <!--begin::End Navbar Links-->
                    <ul class="navbar-nav ms-auto">

                        <!--begin::Fullscreen Toggle-->
                        <li class="nav-item">
                            <a class="nav-link" href="#" data-lte-toggle="fullscreen">
                                <i data-lte-icon="maximize" class="bi bi-arrows-fullscreen"></i>
                                <i data-lte-icon="minimize" class="bi bi-fullscreen-exit" style="display: none"></i>
                            </a>
                        </li>
                        <!--end::Fullscreen Toggle-->
                        <!--begin::User Menu Dropdown-->
                        <li class="nav-item dropdown user-menu">
                            <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
                                <img
                                    src="<%= getServletContext().getContextPath()%>/assets/admin-template/assets/img/user2-160x160.jpg"
                                    class="user-image rounded-circle shadow"
                                    alt="User Image"
                                    />
                                <span class="d-none d-md-inline">${user.username}</span>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-lg dropdown-menu-end">
                                <!--begin::User Image-->
                                <li class="user-header text-bg-primary">
                                    <img
                                        src="<%= getServletContext().getContextPath()%>/assets/admin-template/assets/img/user2-160x160.jpg"
                                        class="rounded-circle shadow"
                                        alt="User Image"
                                        />
                                    <p>
                                        ${user.username} - Web Developer
                                        <small>${user.createdAt}</small>
                                    </p>
                                </li>
                                <!--end::User Image-->
                                <!--begin::Menu Footer-->
                                <li class="user-footer">
                                    <a href="<%= getServletContext().getContextPath()%>/profile" class="btn btn-default btn-flat">Profile</a>
                                    <a href="<%= getServletContext().getContextPath()%>/logout" class="btn btn-default btn-flat float-end">Sign out</a>
                                </li>
                                <!--end::Menu Footer-->
                            </ul>
                        </li>
                        <!--end::User Menu Dropdown-->
                    </ul>
                    <!--end::End Navbar Links-->
                </div>
                <!--end::Container-->
            </nav>
            <!--end::Header-->
            <!--begin::Sidebar-->
            <aside class="app-sidebar bg-body-secondary shadow" data-bs-theme="dark">
                <!--begin::Sidebar Brand-->
                <div class="sidebar-brand">
                    <!--begin::Brand Link-->
                    <a class="brand-link">
                        <!--begin::Brand Image-->
                        <img
                            src="<%= getServletContext().getContextPath()%>/assets/admin-template/assets/img/AdminLTELogo.png"
                            alt="AdminLTE Logo"
                            class="brand-image opacity-75 shadow"
                            />
                        <!--end::Brand Image-->
                        <!--begin::Brand Text-->
                        <span class="brand-text fw-light">AdminLTE 4</span>
                        <!--end::Brand Text-->
                    </a>
                    <!--end::Brand Link-->
                </div>
                <!--end::Sidebar Brand-->
                <!--begin::Sidebar Wrapper-->
                <div class="sidebar-wrapper">
                    <nav class="mt-2">
                        <!--begin::Sidebar Menu-->
                        <ul
                            class="nav sidebar-menu flex-column"
                            data-lte-toggle="treeview"
                            role="menu"
                            data-accordion="false"
                            >
                            <li class="nav-header">GAMES</li>
                            <li class="nav-item">
                                <a href="<%= getServletContext().getContextPath()%>/admin/games" class="nav-link <%= request.getRequestURI().endsWith("games.jsp") ? "active" : ""%>">
                                    <i class="bi bi-controller"></i>
                                    <p>Games List</p>
                                </a>
                            </li>
                            <li class="nav-header">USERS</li>
                            <li class="nav-item">
                                <a href="<%= getServletContext().getContextPath()%>/admin/users" class="nav-link <%= request.getRequestURI().endsWith("users.jsp") ? "active" : ""%>">
                                    <i class="bi bi-people-fill"></i>
                                    <p>User List</p>
                                </a>
                            </li>
                            <li class="nav-header">COUPONS</li>
                            <li class="nav-item">
                                <a href="<%= getServletContext().getContextPath()%>/admin/coupons" class="nav-link <%= request.getRequestURI().endsWith("coupons.jsp") ? "active" : ""%>">
                                    <i class="bi bi-qr-code"></i>
                                    <p>Coupons List</p>
                                </a>
                            </li>
                            <!--                            <li class="nav-item">
                                                            <a href="#" class="nav-link">
                                                                <i class="nav-icon bi bi-circle-fill"></i>
                                                                <p>
                                                                    Coupons List
                                                                    <i class="nav-arrow bi bi-chevron-right"></i>
                                                                </p>
                                                            </a>
                                                            <ul class="nav nav-treeview">
                                                                <li class="nav-item">
                                                                    <a href="<%= getServletContext().getContextPath()%>/admin/coupons" class="nav-link  <%= request.getRequestURI().endsWith("coupons.jsp") ? "active" : ""%>">
                                                                        <i class="nav-icon bi bi-circle"></i>
                                                                        <p>Coupons List</p>
                                                                    </a>
                                                                </li>
                                                                <li class="nav-item">
                                                                    <a href="<%= getServletContext().getContextPath()%>/admin/add-coupon" class="nav-link  <%= request.getRequestURI().endsWith("add-coupon.jsp") ? "active" : ""%>">
                                                                        <i class="nav-icon bi bi-circle"></i>
                                                                        <p>Add Coupon</p>
                                                                    </a>
                                                                </li>
                                                            </ul>
                                                        </li>-->
                            <li class="nav-header">ORDERS</li>
                            <li class="nav-item">
                                <a href="<%= getServletContext().getContextPath()%>/admin/orders" class="nav-link <%= request.getRequestURI().endsWith("orders.jsp") ? "active" : ""%>">
                                    <i class="bi bi-bag-fill"></i>
                                    <p>Orders List</p>
                                </a>
                            </li>
                        </ul>
                        <!--end::Sidebar Menu-->
                    </nav>
                </div>
                <!--end::Sidebar Wrapper-->
            </aside>
            <!--end::Sidebar-->
