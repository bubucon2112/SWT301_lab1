<%-- 
    Document   : tracuu
    Created on : Oct 16, 2024, 7:57:48 PM
    Author     : Administrator
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Giỏ hàng - Vé máy bay</title>

        <link rel="apple-touch-icon" href="apple.jsp">
        <link rel="stylesheet" href="tracuu.css">

        <link rel="stylesheet" href="bootstrap.css">
        <link rel="stylesheet" href="bootstrap2.css">
        <link rel="stylesheet" href="font.css">
        <link rel="stylesheet" href="hero.css">
        <link rel="stylesheet" href="index.css">
        <link rel="stylesheet" href="date.css">
        <link rel="stylesheet" href="home.css">
        <link rel="Shortcut Icon" href="img/logo-i.png"  type="img/x-icon" /> 
        <link rel="stylesheet" href="css/gioithieu.css">
        <link href="https://fonts.googleapis.com/css?family=Spectral:200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <script src="js/vendor/modernizr-2.8.3-respond-1.4.2.min.js"></script>
    </head>
    <body>
        <section class="page-heading" id="top">
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <div class="logo">
                            <img src="img/logo.png" alt="Flight Template">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="page-direction-button">
                            <a href="home_dangnhap.jsp"><i class="fa fa-home"></i>Đi Đến Trang Chủ</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section class="cart-container">
            <div class="cart-item">
                <div class="flight-info">
                    <c:if test="${not empty ticketList}">
                        <div class="ticket-container">
                            <c:forEach var="ticket" items="${ticketList}">
                                <h3>Vé ID: ${ticket.getTicketID()}</h3>
                                <p><span>Hạng vé:</span>
                                    <c:choose>
                                        <c:when test="${ticket.getTicketclass() == 1}">
                                            Thường
                                        </c:when>
                                        <c:when test="${ticket.getTicketclass() == 2}">
                                            Thương gia
                                        </c:when>
                                    </c:choose>                          </p>
                                <p><span>Số hiệu chuyến bay:</span> ${ticket.getFlightNumber()}</p>
                                <p><span>Khởi hành:</span>${ticket.getDeparTime()} ${ticket.getDepart()}</p>
                                <p><span>Đến:</span> ${ticket.getArrivalTime()} ${ticket.getArrival()}</p>
                                <p><span>Giá vé:</span> ${ticket.getPrice()}</p>
                                <form action="thanhtoan" method="post">
                                    <input type="hidden" name="tickettId" value="${ticket.getTicketID()}">
                                    <button class="remove-btn">Xóa</button>
                                </form>
                            </c:forEach>
                        </div>
                    </c:if>
                    <c:if test="${empty ticketList}">
                        <div class="no-orders-message">
                            <h3>Bạn không có đơn hàng nào.</h3>
                        </div>
                    </c:if>
                </div>

            </div>
            <div class="cart-total">
                <form action="thanhtoan" method="post">
                    <input type="hidden" name="action" value="pay">
                    <button class="checkout-btn">Thanh toán</button>
                </form>

            </div>
        </section>

        <footer>
            <h3>Thông tin liên hệ</h3>
            <p>Địa chỉ: 123 Đường ABC, TP.HCM, Việt Nam</p>
            <p>Điện thoại: (028) 1234 5678</p>
            <p>Email: support@traveloka.com</p>
            <p>&copy; 2024 Công ty ABC. Tất cả các quyền được bảo lưu.</p>
        </footer>
    </body>
</html>