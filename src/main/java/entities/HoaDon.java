package entities;

import java.sql.Date;
import java.util.List;

public class HoaDon {
    private int maHoaDon;
    private int maKhach; // Thuộc tính này gây ra lỗi nếu thiếu Getter
    private Date ngayLap;
    private int tongTien;
    private byte trangThai;

    // Các thuộc tính hỗ trợ hiển thị (không có trong bảng hoa_don)
    private String hoTenKhach;
    private List<ChiTietHoaDon> chiTietList;

    public HoaDon() {}

    // Phương thức giải quyết lỗi 'getMaKhach'
    public int getMaKhach() {
        return maKhach;
    }

    public void setMaKhach(int maKhach) {
        this.maKhach = maKhach;
    }

    // Các Getter và Setter còn lại
    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public byte getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(byte trangThai) {
        this.trangThai = trangThai;
    }

    public String getHoTenKhach() {
        return hoTenKhach;
    }

    public void setHoTenKhach(String hoTenKhach) {
        this.hoTenKhach = hoTenKhach;
    }

    public List<ChiTietHoaDon> getChiTietList() {
        return chiTietList;
    }

    public void setChiTietList(List<ChiTietHoaDon> chiTietList) {
        this.chiTietList = chiTietList;
    }
}