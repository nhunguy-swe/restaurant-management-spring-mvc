package servlet;

import dao.HoaDonDao;
import dao.KhachHangDao;
import dao.MonAnDao;
import entities.HoaDon;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@WebServlet("/add-hoa-don")
public class AddHoaDonServlet extends HttpServlet {
    private HoaDonDao hdDao = new HoaDonDao();
    private KhachHangDao khDao = new KhachHangDao();
    private MonAnDao monDao = new MonAnDao();

    // Hiển thị Form lập hóa đơn và load danh sách từ CSDL
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("listKH", khDao.searchKhachHang(""));
            request.setAttribute("listMon", monDao.findAll());
            request.getRequestDispatcher("add-hoa-don.jsp").forward(request, response);
        } catch (Exception e) { e.printStackTrace(); }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String ngayLapStr = request.getParameter("ngayLap");
            LocalDate ngayLap = LocalDate.parse(ngayLapStr);

            // Ràng buộc: Ngày lập không được sau ngày hiện tại
            if (ngayLap.isAfter(LocalDate.now())) {
                request.setAttribute("error", "Ngày lập không được ở tương lai!");
                doGet(request, response);
                return;
            }

            String[] selectedMon = request.getParameterValues("selectedMon");
            if (selectedMon == null) {
                request.setAttribute("error", "Vui lòng chọn ít nhất một món ăn!");
                doGet(request, response);
                return;
            }

            HoaDon hd = new HoaDon();
            hd.setMaKhach(Integer.parseInt(request.getParameter("maKhach")));
            hd.setNgayLap(Date.valueOf(ngayLap));
            hd.setTongTien(Integer.parseInt(request.getParameter("tongTien")));
            hd.setTrangThai(Byte.parseByte(request.getParameter("trangThai")));

            // Gọi DAO để lưu Transaction (Hóa đơn + Chi tiết)
            hdDao.addHoaDonFull(hd, selectedMon,
                    request.getParameterValues("soLuongList"),
                    request.getParameterValues("donGiaList"));

            response.sendRedirect("list-hoa-don");
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi: " + e.getMessage());
            doGet(request, response);
        }
    }
}