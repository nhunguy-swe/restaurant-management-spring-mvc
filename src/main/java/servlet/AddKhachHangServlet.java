package servlet;

import dao.KhachHangDao;
import entities.KhachHang;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-khach-hang")
public class AddKhachHangServlet extends HttpServlet {
    private KhachHangDao khachHangDao = new KhachHangDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String hoTen = request.getParameter("hoTen");
            String sdt = request.getParameter("soDienThoai");
            String diaChi = request.getParameter("diaChi");

            // Kiểm tra số điện thoại: 10 chữ số, bắt đầu bằng 0
            if (!sdt.matches("^0[0-9]{9}$")) {
                request.setAttribute("error", "Số điện thoại phải có 10 chữ số và bắt đầu bằng 0!");
                request.getRequestDispatcher("add-khach-hang.jsp").forward(request, response);
                return;
            }

            KhachHang kh = new KhachHang(0, hoTen, sdt, diaChi);
            khachHangDao.addKhachHang(kh);
            response.sendRedirect("index.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}