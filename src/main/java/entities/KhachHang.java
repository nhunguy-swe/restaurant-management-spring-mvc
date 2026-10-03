package entities;

public class KhachHang {
    private int maKhach;
    private String hoTen;
    private String soDienThoai;
    private String diaChi;

    public KhachHang() {}

    public KhachHang(int maKhach, String hoTen, String soDienThoai, String diaChi) {
        this.maKhach = maKhach;
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
    }

    // Getters và Setters
    public int getMaKhach() { return maKhach; }
    public void setMaKhach(int maKhach) { this.maKhach = maKhach; }
    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }
    public String getSoDienThoai() { return soDienThoai; }
    public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }
    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }
}