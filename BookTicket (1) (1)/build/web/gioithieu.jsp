<a href="url"></a><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
  Integer sessionPassengerId = (Integer) session.getAttribute("passengerId");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Công ty ABC - Giới thiệu</title>
        <link rel="apple-touch-icon" href="apple.jsp">
        <link rel="stylesheet" href="bootstrap.css">
        <link rel="stylesheet" href="bootstrap2.css">
        <link rel="stylesheet" href="font.css">
        <link rel="stylesheet" href="hero.css">
        <link rel="stylesheet" href="index.css">
        <link rel="stylesheet" href="date.css">
        <link rel="stylesheet" href="home.css">
        <link rel="Shortcut Icon" href="img/logo-i.png" type="img/x-icon" /> 
        <link rel="stylesheet" href="gioithieu.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800" rel="stylesheet">
        <style>
            #customer-id-input {
                display: flex;
                align-items: center;
            }
            .makhachhang {
                margin-left: 30%;
                display: flex;
                align-items: center;
            }
            h2 {
                margin: 0;
            }
            input[type="text"] {
                margin-left: 10px;
                padding: 5px;
            }
            button {
                margin-left: 20px;
                background-color: yellow;
                border: none;
                padding: 5px 10px;
                cursor: pointer;
            }
            button:hover {
                background-color: gold;
            }
            .rated span {
                color: #FFD700;
                font-size: 20px;
            }
            .edit-button,
            .delete-button {
                background-color: yellow;
                color: black;
                border: none;
                padding: 10px 15px;
                cursor: pointer;
                border-radius: 5px;
                margin: 5px;
                transition: background-color 0.3s;
            }
            .edit-button:hover,
            .delete-button:hover {
                background-color: orange;
            }
            /* Thiết lập cho bảng chỉnh sửa */
            .edit-table-container {
                display: none;
                position: absolute;
                top: 0;
                left: 100%;
                margin-left: 20px;
                background-color: #f9f9f9;
                border: 1px solid #ddd;
                padding: 15px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
                width: 250px;
                z-index: 100;
                border-radius: 8px;
            }

            .edit-table-container h3 {
                margin-top: 0;
                font-size: 1.1em;
                color: #333;
            }

            .close-edit-table {
                background-color: transparent;
                color: #777;
                border: none;
                font-size: 18px;
                font-weight: bold;
                cursor: pointer;
                float: right;
            }

            .close-edit-table:hover {
                color: #333;
            }

            .edit-table-container input[type="text"] {
                width: 100%;
                padding: 8px;
                margin-top: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            .edit-table-container button {
                background-color: #28a745;
                color: #fff;
                border: none;
                padding: 8px 12px;
                cursor: pointer;
                border-radius: 4px;
                margin-top: 10px;
            }

            .edit-table-container button:hover {
                background-color: #218838;
            }
            /* Kiểu dáng bảng */
            table {
                width: 100%;
                border-collapse: collapse;
                margin: 20px 0;
                font-size: 16px;
                text-align: left;
            }

            th, td {
                padding: 12px 15px;
                border: 1px solid #ddd;
            }

            th {
                background-color: #f4f4f4;
                font-weight: bold;
            }

            tr:nth-child(even) {
                background-color: #f9f9f9;
            }

            .star-rating {
                display: flex;
                justify-content: center;
            }

            .star-rating input {
                display: none;
            }

            .star-rating label {
                color: gold;
                font-size: 1.5em;
                cursor: pointer;
            }

            .star-rating input:checked ~ label {
                color: gold;
            }
            .table-dg {
                border: 3px solid;
                border-radius: 10px;
                margin-top: 20px;
                width: 96%;
                margin-left: 2%;
                margin-right: 2%;
            }
            .binhluan{
                margin-top: 2%;
                margin-left: 20%;
            }
            .delete{
                background: yellow;
                width: 15%;
            }


        </style>
    </head>
    <body>
        <section class="page-heading" id="top">
            <div class="container">
                <div class="logo">
                    <img src="img/logo.png" alt="Flight Template">
                    <span class="logo-text"></span>
                    <div class="page-direction-button">
                        <c:choose>
                            <c:when test="${not empty sessionScope.account}">
                                <a href="home_dangnhap.jsp"><i class="fa fa-home"></i> Đi Đến Trang Chủ</a>
                            </c:when>
                            <c:otherwise>
                                <a href="home.jsp"><i class="fa fa-home"></i> Đi Đến Trang Chủ</a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </section>

        <section id="about-container">
            <div class="about-left">
                <img src="img/nhom1.jpg" alt="Hình ảnh công ty ABC">
            </div>
            <div class="about-right" id="about">
                <p>Công ty ABC được thành lập với sứ mệnh mang đến cho khách hàng những trải nghiệm tốt nhất khi đặt vé máy bay...</p>
            </div>
        </section>

        <section id="images">
            <h2>Hình ảnh công ty</h2>
            <div class="image-gallery">
                <img src="img/P1.png" alt="Hình ảnh 1" class="image">
                <img src="img/P2.jpg" alt="Hình ảnh 2" class="image">
                <img src="img/P3.jpg" alt="Hình ảnh 3" class="image">
            </div>
        </section>

        <h2 class="binhluan">Đánh giá của khách hàng</h2>
        <form action="comment" method="get">
            <section id="reviews">
                <ul class="fb-comments">
                    <h2 style="color: red">${error}</h2>
                    <c:forEach items="${list}" var="l">
                        <li>
                            <div class="comment-avatar">
                                <img src="img/xmt2.jpg" alt="Avatar">
                            </div>
                            <div class="comment-content">
                                <strong>${l.username}</strong>
                                <div class="rated">
                                    <c:forEach var="i" begin="1" end="5">
                                        <span>${i <= l.rating ? '★' : '☆'}</span>
                                    </c:forEach>
                                </div>
                                <p class="feedback-text">${l.feedbackText}</p>
                                <span class="comment-time">${l.feedbackDate}</span>
                                <div class="comment-actions">


                                    <a href="comment?id=${l.feedbackId}" onclick="return confirm('Bạn có chắc chắn muốn xóa?')">Xóa</a>


                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </section>




        </form>




        <footer>
            <h2>Thông tin liên hệ</h2>
            <p>Địa chỉ: 123 Đường ABC, TP.HCM, Việt Nam</p>
            <p>Điện thoại: (028) 1234 5678</p>
            <p>Email: contact@abc.com</p>
            <p>&copy; 2024 Công ty ABC. Tất cả các quyền được bảo lưu.</p>
        </footer>
    </body>
</html>
