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

@WebServlet("/list-hoa-don")
public class ListHoaDonServlet extends HttpServlet {
    private HoaDonDao hdDao = new HoaDonDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<HoaDon> list = hdDao.getAllHoaDon();
            request.setAttribute("listHD", list);
            request.getRequestDispatcher("list-hoa-don.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}