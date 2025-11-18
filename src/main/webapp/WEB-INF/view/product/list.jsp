<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách sản phẩm</title>
    <style>
        body { font-family: Arial, sans-serif; max-width: 900px; margin: 40px auto; }
        .topbar { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; }
        a.btn { padding: 8px 12px; background: #0d6efd; color: white; text-decoration: none; border-radius: 4px; }
        a.btn:hover { background: #0b5ed7; }
        table { width: 100%; border-collapse: collapse; }
        th, td { padding: 10px; border-bottom: 1px solid #ddd; text-align: left; }
        .danger { background: #dc3545; }
        .danger:hover { background: #bb2d3b; }
        .secondary { background: #6c757d; }
        .secondary:hover { background: #5c636a; }
        form { display: inline; }
    </style>
</head>
<body>
<div class="topbar">
    <h2>Quản lý sản phẩm</h2>
    <div>
        <a class="btn" href="${pageContext.request.contextPath}/products/create">+ Thêm sản phẩm</a>
        <a class="btn secondary" href="${pageContext.request.contextPath}/home">Về trang chủ</a>
    </div>
</div>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Tên</th>
        <th>Giá</th>
        <th>Mô tả</th>
        <th>Hành động</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${products}" var="p">
        <tr>
            <td>${p.id}</td>
            <td>${p.name}</td>
            <td>${p.price}</td>
            <td><c:out value="${p.description}"/></td>
            <td>
                <a class="btn" href="${pageContext.request.contextPath}/products/edit?id=${p.id}">Sửa</a>
                <form method="post" action="${pageContext.request.contextPath}/products/delete" onsubmit="return confirm('Xóa sản phẩm này?');">
                    <input type="hidden" name="id" value="${p.id}">
                    <button type="submit" class="btn danger">Xóa</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    <c:if test="${empty products}">
        <tr><td colspan="5">Chưa có sản phẩm nào.</td></tr>
    </c:if>
    </tbody>
</table>
</body>
</html>