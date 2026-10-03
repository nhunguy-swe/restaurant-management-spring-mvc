package entities;

public class ChiTietHoaDon {
    private int maHoaDon;
    private int maMon;
    private int soLuong;
    private int donGia;

    // Thuộc tính bổ sung để hiển thị tên món ăn
    private String tenMon;

    public ChiTietHoaDon() {}

    public ChiTietHoaDon(int maHoaDon, int maMon, int soLuong, int donGia) {
        this.maHoaDon = maHoaDon;
        this.maMon = maMon;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    // Getters và Setters
    public int getMaHoaDon() { return maHoaDon; }
    public void setMaHoaDon(int maHoaDon) { this.maHoaDon = maHoaDon; }
    public int getMaMon() { return maMon; }
    public void setMaMon(int maMon) { this.maMon = maMon; }
    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
    public int getDonGia() { return donGia; }
    public void setDonGia(int donGia) { this.donGia = donGia; }
    public String getTenMon() { return tenMon; }
    public void setTenMon(String tenMon) { this.tenMon = tenMon; }
}