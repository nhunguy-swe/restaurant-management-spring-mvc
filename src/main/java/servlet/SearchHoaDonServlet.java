package servlet;

import dao.HoaDonDao;
import entities.HoaDon;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/search-hoa-don")
public class SearchHoaDonServlet extends HttpServlet {
    private HoaDonDao hdDao = new HoaDonDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        List<HoaDon> list;

        try {
            if (keyword == null || keyword.trim().isEmpty()) {
                list = hdDao.getAllHoaDon(); // Lấy tất cả hóa đơn
            } else {
                list = hdDao.searchHoaDonFull(keyword); // Tìm kiếm theo mã hoặc tên
            }
            request.setAttribute("listHoaDonFull", list);
            request.getRequestDispatcher("search-hoa-don.jsp").forward(request, response);
        } catch (Exception e) { e.printStackTrace(); }
    }
}