package servlet;

import dao.MonAnDao;
import entities.MonAn;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-mon-an")
public class AddMonAnServlet extends HttpServlet {
    private MonAnDao monAnDao = new MonAnDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String ten = request.getParameter("tenMon");
            String loai = request.getParameter("loaiMon");
            int gia = Integer.parseInt(request.getParameter("donGia"));

            // Kiểm tra ràng buộc: số nguyên dương tròn 1000
            if (gia <= 0 || gia % 1000 != 0) {
                request.setAttribute("error", "Đơn giá phải là số nguyên dương tròn 1000!");
                request.getRequestDispatcher("add-mon-an.jsp").forward(request, response);
                return;
            }

            MonAn mon = new MonAn(0, ten, loai, gia, request.getParameter("moTa"));
            monAnDao.addMonAn(mon);
            response.sendRedirect("index.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}