package servlet;

import dao.UserDAO;
import model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = userDAO.authenticate(username, password);

        if (user != null) {
            // Đăng nhập thành công → lưu user vào session
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            // Chuyển hướng đến trang chủ (home)
            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            // Đăng nhập thất bại → hiển thị lại login.jsp với lỗi
            req.setAttribute("error", "Sai username hoặc password");
            req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Nếu đã đăng nhập thì chuyển hướng tới /home
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            // Nếu chưa login → hiển thị form
            req.getRequestDispatcher("WEB-INF/view/login.jsp").forward(req, resp);
        }
    }
}
