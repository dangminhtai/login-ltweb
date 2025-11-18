<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang chủ</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 800px;
            margin: 50px auto;
            padding: 20px;
        }
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 30px;
            padding-bottom: 20px;
            border-bottom: 2px solid #ddd;
        }
        .logout-btn {
            background-color: #dc3545;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
        }
        .logout-btn:hover {
            background-color: #c82333;
        }
        .welcome {
            font-size: 24px;
            color: #333;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1 class="welcome">Chào mừng, ${user.username}!</h1>
        <a href="${pageContext.request.contextPath}/logout" class="logout-btn">Đăng xuất</a>
    </div>
    
    <div>
        <h2>Thông tin tài khoản</h2>
        <p><strong>ID:</strong> ${user.id}</p>
        <p><strong>Username:</strong> ${user.username}</p>
    </div>

    <div style="margin-top: 24px;">
        <h2>Chức năng</h2>
        <p>
            <a href="${pageContext.request.contextPath}/products">Quản lý sản phẩm</a>
        </p>
    </div>
</body>
</html>


