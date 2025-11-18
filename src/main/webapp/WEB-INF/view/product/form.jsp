<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><c:choose><c:when test="${mode eq 'edit'}">Sửa</c:when><c:otherwise>Tạo</c:otherwise></c:choose> sản phẩm</title>
    <style>
        body { font-family: Arial, sans-serif; max-width: 600px; margin: 40px auto; }
        label { display: block; margin-top: 12px; }
        input[type=text], textarea { width: 100%; padding: 8px; margin-top: 6px; }
        .actions { margin-top: 16px; display: flex; gap: 10px; }
        .btn { padding: 8px 12px; background: #0d6efd; color: white; text-decoration: none; border: 0; border-radius: 4px; cursor: pointer; }
        .secondary { background: #6c757d; text-decoration: none; display: inline-block; }
        .error { color: #dc3545; margin-top: 8px; }
    </style>
</head>
<body>
<h2><c:choose><c:when test="${mode eq 'edit'}">Sửa</c:when><c:otherwise>Tạo</c:otherwise></c:choose> sản phẩm</h2>

<c:if test="${not empty error}">
    <div class="error">${error}</div>
</c:if>

<form method="post" action="<c:choose><c:when test='${mode eq \"edit\"}'>${pageContext.request.contextPath}/products/edit</c:when><c:otherwise>${pageContext.request.contextPath}/products/create</c:otherwise></c:choose>">
    <c:if test="${mode eq 'edit'}">
        <input type="hidden" name="id" value="${product.id}"/>
    </c:if>

    <label>Tên sản phẩm</label>
    <input type="text" name="name" required value="<c:out value='${mode eq "edit" ? product.name : ""}'/>"/>

    <label>Giá</label>
    <input type="text" name="price" required value="<c:out value='${mode eq "edit" ? product.price : ""}'/>"/>

    <label>Mô tả</label>
    <textarea name="description" rows="4"><c:out value='${mode eq "edit" ? product.description : ""}'/></textarea>

    <div class="actions">
        <button type="submit" class="btn"><c:choose><c:when test="${mode eq 'edit'}">Cập nhật</c:when><c:otherwise>Tạo mới</c:otherwise></c:choose></button>
        <a class="btn secondary" href="${pageContext.request.contextPath}/products">Hủy</a>
    </div>
</form>
</body>
</html>