package servlet;

import dao.MonAnDao;
import entities.MonAn;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/list-mon-an")
public class MonAnServlet extends HttpServlet {
    private MonAnDao monAnDao = new MonAnDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<MonAn> list = monAnDao.getAllMonAn();
            request.setAttribute("listMon", list);
            request.getRequestDispatcher("list-mon-an.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}