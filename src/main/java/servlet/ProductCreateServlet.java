package servlet;

import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Product;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/products/create")
public class ProductCreateServlet extends HttpServlet {
    private final ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        req.setAttribute("mode", "create");
        req.getRequestDispatcher("/WEB-INF/view/product/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String priceStr = req.getParameter("price");
        String description = req.getParameter("description");

        BigDecimal price = BigDecimal.ZERO;
        try {
            if (priceStr != null && !priceStr.isBlank()) {
                price = new BigDecimal(priceStr.trim());
            }
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Giá không hợp lệ");
            req.setAttribute("mode", "create");
            req.getRequestDispatcher("/WEB-INF/view/product/form.jsp").forward(req, resp);
            return;
        }

        Product p = new Product(0, name, price, description);
        boolean ok = productDAO.create(p);
        if (ok) {
            resp.sendRedirect(req.getContextPath() + "/products");
        } else {
            req.setAttribute("error", "Không thể tạo sản phẩm");
            req.setAttribute("mode", "create");
            req.getRequestDispatcher("/WEB-INF/view/product/form.jsp").forward(req, resp);
        }
    }
}