package dao;

import entities.MonAn;
import utils.DBUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MonAnDao {
    public void addMonAn(MonAn mon) throws Exception {
        String sql = "INSERT INTO mon_an (ten_mon, loai_mon, don_gia, mo_ta) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mon.getTenMon());
            ps.setString(2, mon.getLoaiMon());
            ps.setInt(3, mon.getDonGia());
            ps.setString(4, mon.getMoTa());
            ps.executeUpdate();
        }
    }

    public List<MonAn> findAll() throws Exception {
        List<MonAn> list = new ArrayList<>();
        String sql = "SELECT * FROM mon_an";
        try (Connection conn = DBUtils.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new MonAn(rs.getInt("ma_mon"), rs.getString("ten_mon"),
                        rs.getString("loai_mon"), rs.getInt("don_gia"), rs.getString("mo_ta")));
            }
        }
        return list;
    }

    public List<MonAn> getAllMonAn() throws Exception {
        List<MonAn> list = new ArrayList<>();
        // Truy vấn lấy tất cả món ăn từ bảng mon_an
        String sql = "SELECT * FROM mon_an";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                MonAn mon = new MonAn();
                mon.setMaMon(rs.getInt("ma_mon"));
                mon.setTenMon(rs.getString("ten_mon"));
                mon.setLoaiMon(rs.getString("loai_mon"));
                mon.setDonGia(rs.getInt("don_gia"));
                mon.setMoTa(rs.getString("mo_ta"));
                list.add(mon);
            }
        }
        return list;
    }
}