<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <h2>Đăng nhập</h2>

    <!-- Hiện thông báo lỗi nếu có -->
    <%
        String error = (String) request.getAttribute("error");
        if (error != null && !error.isEmpty()) {
    %>
        <div style="color:red"><%= error %></div>
    <%
        }
    %>

    <form action="<%= request.getContextPath() %>/login" method="post">
        <label>
            Username:
            <input type="text" name="username" required>
        </label><br><br>

        <label>
            Password:
            <input type="password" name="password" required>
        </label><br><br>

        <button type="submit">Login</button>
    </form>
</body>
</html>
