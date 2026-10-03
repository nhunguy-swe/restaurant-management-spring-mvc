package servlet;

import dao.KhachHangDao;
import entities.KhachHang;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// Đường dẫn này phải khớp chính xác với thuộc tính 'action' trong Form JSP của bạn
@WebServlet("/search-khach-hang")
public class SearchKhachHangServlet extends HttpServlet {
    private KhachHangDao khDao = new KhachHangDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String keyword = request.getParameter("keyword");
            List<KhachHang> list;

            // Nếu keyword trống (lần đầu vào trang) -> Hiện tất cả
            if (keyword == null || keyword.trim().isEmpty()) {
                list = khDao.getAllKhachHang();
            } else {
                // Nếu có nhập -> Lọc theo keyword
                list = khDao.searchKhachHang(keyword);
            }

            request.setAttribute("resultKH", list);
            request.getRequestDispatcher("search-khach-hang.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}