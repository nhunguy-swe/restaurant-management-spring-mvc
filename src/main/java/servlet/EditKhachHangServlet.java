package servlet;

import dao.KhachHangDao;
import entities.KhachHang;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/edit-khach-hang")
public class EditKhachHangServlet extends HttpServlet {
    private KhachHangDao khDao = new KhachHangDao();

    // doGet: Lấy dữ liệu cũ đổ lên form sửa
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            KhachHang kh = khDao.getKhachHangById(id);
            request.setAttribute("khachHang", kh);
            request.getRequestDispatcher("edit-khach-hang.jsp").forward(request, response);
        } catch (Exception e) { e.printStackTrace(); }
    }

    // doPost: Cập nhật dữ liệu mới vào CSDL
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("maKhach"));
        String hoTen = request.getParameter("hoTen");
        String sdt = request.getParameter("soDienThoai");
        String diaChi = request.getParameter("diaChi");

        KhachHang kh = new KhachHang(id, hoTen, sdt, diaChi);
        try {
            khDao.updateKhachHang(kh);
            response.sendRedirect("search-khach-hang");
        } catch (Exception e) { e.printStackTrace(); }
    }
}
