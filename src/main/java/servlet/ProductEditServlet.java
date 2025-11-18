package servlet;

import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Product;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/products/edit")
public class ProductEditServlet extends HttpServlet {
    private final ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String idStr = req.getParameter("id");
        if (idStr == null) {
            resp.sendRedirect(req.getContextPath() + "/products");
            return;
        }
        int id = Integer.parseInt(idStr);
        Product p = productDAO.findById(id);
        if (p == null) {
            resp.sendRedirect(req.getContextPath() + "/products");
            return;
        }
        req.setAttribute("product", p);
        req.setAttribute("mode", "edit");
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
        String idStr = req.getParameter("id");
        String name = req.getParameter("name");
        String priceStr = req.getParameter("price");
        String description = req.getParameter("description");

        int id = Integer.parseInt(idStr);
        BigDecimal price;
        try {
            price = new BigDecimal(priceStr.trim());
        } catch (Exception e) {
            req.setAttribute("error", "Giá không hợp lệ");
            Product fallback = productDAO.findById(id);
            req.setAttribute("product", fallback);
            req.setAttribute("mode", "edit");
            req.getRequestDispatcher("/WEB-INF/view/product/form.jsp").forward(req, resp);
            return;
        }

        Product p = new Product(id, name, price, description);
        boolean ok = productDAO.update(p);
        if (ok) {
            resp.sendRedirect(req.getContextPath() + "/products");
        } else {
            req.setAttribute("error", "Không thể cập nhật sản phẩm");
            req.setAttribute("product", productDAO.findById(id));
            req.setAttribute("mode", "edit");
            req.getRequestDispatcher("/WEB-INF/view/product/form.jsp").forward(req, resp);
        }
    }
}