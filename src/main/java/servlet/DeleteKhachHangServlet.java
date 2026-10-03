package servlet;

import dao.KhachHangDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delete-khach-hang")
public class DeleteKhachHangServlet extends HttpServlet {
    private KhachHangDao khDao = new KhachHangDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            khDao.deleteKhachHang(id);
            response.sendRedirect("search-khach-hang");
        } catch (Exception e) { e.printStackTrace(); }
    }
}
