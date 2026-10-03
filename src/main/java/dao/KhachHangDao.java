package dao;

import entities.KhachHang;
import utils.DBUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDao {
    public void addKhachHang(KhachHang kh) throws Exception {
        String sql = "INSERT INTO khach_hang (ho_ten, so_dien_thoai, dia_chi) VALUES (?, ?, ?)";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kh.getHoTen());
            ps.setString(2, kh.getSoDienThoai());
            ps.setString(3, kh.getDiaChi());
            ps.executeUpdate();
        }
    }

    // Form 1: Tìm kiếm khách hàng (mã, tên, sđt)
    public List<KhachHang> searchKhachHang(String keyword) throws Exception {
        List<KhachHang> list = new ArrayList<>();
        String sql = "SELECT * FROM khach_hang WHERE ma_khach LIKE ? OR ho_ten LIKE ? OR so_dien_thoai LIKE ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String key = "%" + keyword + "%";
            ps.setString(1, key);
            ps.setString(2, key);
            ps.setString(3, key);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new KhachHang(rs.getInt("ma_khach"), rs.getString("ho_ten"),
                        rs.getString("so_dien_thoai"), rs.getString("dia_chi")));
            }
        }
        return list;
    }

    public List<KhachHang> getAllKhachHang() throws Exception {
        List<KhachHang> list = new ArrayList<>();
        // Câu lệnh SQL lấy toàn bộ khách hàng
        String sql = "SELECT * FROM khach_hang";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKhach(rs.getInt("ma_khach"));
                kh.setHoTen(rs.getString("ho_ten"));
                kh.setSoDienThoai(rs.getString("so_dien_thoai"));
                kh.setDiaChi(rs.getString("dia_chi"));
                list.add(kh);
            }
        }
        return list;
    }

    // Lấy thông tin 1 khách hàng theo mã để đổ lên form Sửa
    public KhachHang getKhachHangById(int id) throws Exception {
        String sql = "SELECT * FROM khach_hang WHERE ma_khach = ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new KhachHang(
                            rs.getInt("ma_khach"),
                            rs.getString("ho_ten"),
                            rs.getString("so_dien_thoai"),
                            rs.getString("dia_chi")
                    );
                }
            }
        }
        return null;
    }

    // Cập nhật thông tin khách hàng sau khi người dùng sửa
    public void updateKhachHang(KhachHang kh) throws Exception {
        String sql = "UPDATE khach_hang SET ho_ten = ?, so_dien_thoai = ?, dia_chi = ? WHERE ma_khach = ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kh.getHoTen());
            ps.setString(2, kh.getSoDienThoai());
            ps.setString(3, kh.getDiaChi());
            ps.setInt(4, kh.getMaKhach());
            ps.executeUpdate();
        }
    }

    // Phương thức xóa khách hàng theo mã
    public void deleteKhachHang(int id) throws Exception {
        String sql = "DELETE FROM khach_hang WHERE ma_khach = ?";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Truyền tham số ID vào dấu hỏi chấm (?) trong câu lệnh SQL
            ps.setInt(1, id);

            // Thực thi lệnh xóa dữ liệu
            ps.executeUpdate();
        }
    }

    public void insertKhachHang(KhachHang kh) throws Exception {
        String sql = "INSERT INTO khach_hang (ho_ten, so_dien_thoai, dia_chi) VALUES (?, ?, ?)";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, kh.getHoTen());
            ps.setString(2, kh.getSoDienThoai());
            ps.setString(3, kh.getDiaChi());

            ps.executeUpdate();
        }
    }
}