package dao;

import entities.ChiTietHoaDon;
import entities.HoaDon;
import utils.DBUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDao {
    public void addHoaDonFull(HoaDon hd, String[] maMonList, String[] soLuongList, String[] donGiaList) throws Exception {
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
            conn.setAutoCommit(false); // Bắt đầu Transaction

            // 1. Chèn vào bảng hoa_don
            String sqlHD = "INSERT INTO hoa_don (ma_khach, ngay_lap, tong_tien, trang_thai) VALUES (?, ?, ?, ?)";
            PreparedStatement psHD = conn.prepareStatement(sqlHD, Statement.RETURN_GENERATED_KEYS);
            psHD.setInt(1, hd.getMaKhach());
            psHD.setDate(2, hd.getNgayLap());
            psHD.setInt(3, hd.getTongTien());
            psHD.setByte(4, hd.getTrangThai());
            psHD.executeUpdate();

            // Lấy mã hóa đơn vừa tự tăng
            ResultSet rs = psHD.getGeneratedKeys();
            int maHD = 0;
            if (rs.next()) maHD = rs.getInt(1);

            // 2. Chèn vào bảng chi_tiet_hoa_don
            String sqlCT = "INSERT INTO chi_tiet_hoa_don (ma_hoa_don, ma_mon, so_luong, don_gia) VALUES (?, ?, ?, ?)";
            PreparedStatement psCT = conn.prepareStatement(sqlCT);

            for (int i = 0; i < maMonList.length; i++) {
                psCT.setInt(1, maHD);
                psCT.setInt(2, Integer.parseInt(maMonList[i]));
                psCT.setInt(3, Integer.parseInt(soLuongList[i]));
                psCT.setInt(4, Integer.parseInt(donGiaList[i]));
                psCT.addBatch();
            }
            psCT.executeBatch();

            conn.commit(); // Hoàn tất
        } catch (Exception e) {
            if (conn != null) conn.rollback(); // Lỗi thì quay lui
            throw e;
        } finally {
            if (conn != null) conn.close();
        }
    }

    public List<HoaDon> searchHoaDonFull(String keyword) throws Exception {
        List<HoaDon> listHD = new ArrayList<>();
        String sql = "SELECT hd.*, kh.ho_ten FROM hoa_don hd " +
                "JOIN khach_hang kh ON hd.ma_khach = kh.ma_khach " +
                "WHERE kh.ho_ten LIKE ? OR hd.ma_hoa_don = ?";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            // Kiểm tra nếu keyword là số thì tìm theo mã
            int ma = keyword.matches("\\d+") ? Integer.parseInt(keyword) : -1;
            ps.setInt(2, ma);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHoaDon(rs.getInt("ma_hoa_don"));
                hd.setHoTenKhach(rs.getString("ho_ten"));
                hd.setNgayLap(rs.getDate("ngay_lap"));
                hd.setTongTien(rs.getInt("tong_tien"));
                hd.setTrangThai(rs.getByte("trang_thai"));

                // Lấy thêm chi tiết món ăn cho hóa đơn này
                hd.setChiTietList(getChiTietByMaHD(hd.getMaHoaDon(), conn));
                listHD.add(hd);
            }
        }
        return listHD;
    }

    private List<ChiTietHoaDon> getChiTietByMaHD(int maHD, Connection conn) throws Exception {
        List<ChiTietHoaDon> listCT = new ArrayList<>();
        String sql = "SELECT ct.*, m.ten_mon FROM chi_tiet_hoa_don ct " +
                "JOIN mon_an m ON ct.ma_mon = m.ma_mon WHERE ct.ma_hoa_don = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maHD);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietHoaDon ct = new ChiTietHoaDon();
                ct.setTenMon(rs.getString("ten_mon"));
                ct.setSoLuong(rs.getInt("so_luong"));
                ct.setDonGia(rs.getInt("don_gia"));
                listCT.add(ct);
            }
        }
        return listCT;
    }

    public List<HoaDon> getAllHoaDon() throws Exception {
        List<HoaDon> listHD = new ArrayList<>();
        // Câu lệnh SQL lấy thông tin hóa đơn và tên khách hàng bằng cách JOIN bảng
        String sql = "SELECT hd.*, kh.ho_ten FROM hoa_don hd " +
                "JOIN khach_hang kh ON hd.ma_khach = kh.ma_khach " +
                "ORDER BY hd.ma_hoa_don DESC";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHoaDon(rs.getInt("ma_hoa_don"));
                hd.setMaKhach(rs.getInt("ma_khach"));
                hd.setHoTenKhach(rs.getString("ho_ten")); // Gán tên khách hàng để hiển thị
                hd.setNgayLap(rs.getDate("ngay_lap"));
                hd.setTongTien(rs.getInt("tong_tien"));
                hd.setTrangThai(rs.getByte("trang_thai"));
                listHD.add(hd);
            }
        }
        return listHD;
    }
}