package entities;

public class MonAn {
    private int maMon;
    private String tenMon;
    private String loaiMon; // MON_CHINH, KHAI_VI, TRANG_MIENG
    private int donGia;
    private String moTa;

    public MonAn() {}

    public MonAn(int maMon, String tenMon, String loaiMon, int donGia, String moTa) {
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.loaiMon = loaiMon;
        this.donGia = donGia;
        this.moTa = moTa;
    }

    // Getters và Setters
    public int getMaMon() { return maMon; }
    public void setMaMon(int maMon) { this.maMon = maMon; }
    public String getTenMon() { return tenMon; }
    public void setTenMon(String tenMon) { this.tenMon = tenMon; }
    public String getLoaiMon() { return loaiMon; }
    public void setLoaiMon(String loaiMon) { this.loaiMon = loaiMon; }
    public int getDonGia() { return donGia; }
    public void setDonGia(int donGia) { this.donGia = donGia; }
    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }
}