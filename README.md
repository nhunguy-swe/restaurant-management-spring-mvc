# HỆ THỐNG QUẢN LÝ NHÀ HÀNG (Restaurant Management)

<p>
  <img src="https://img.shields.io/badge/Java-17%2B-orange" alt="Java">
  <img src="https://img.shields.io/badge/Spring%20MVC-brightgreen" alt="Spring MVC">
  <img src="https://img.shields.io/badge/Hibernate%2FJPA-blue" alt="Hibernate/JPA">
  <img src="https://img.shields.io/badge/Build-Maven-red" alt="Maven">
</p>

## 1. Giới thiệu

Hệ thống Quản lý Nhà hàng được xây dựng nhằm hỗ trợ quản lý thông tin món ăn, khách hàng và hóa đơn. Hệ thống cho phép lưu trữ dữ liệu món ăn, lập hóa đơn đặt món và tra cứu thông tin khách hàng cũng như lịch sử hóa đơn.

---

## 2. Công nghệ sử dụng

- Java
- Spring MVC
- Hibernate/JPA
- MySQL hoặc SQL Server
- JSP/Servlet
- Bootstrap 5 (tùy chọn)
- Maven

---

## 3. Thiết kế cơ sở dữ liệu

### Bảng MON_AN

| Tên cột | Kiểu dữ liệu              | Mô tả                                     |
| ------- | ------------------------- | -------------------------------------------|
| maMon   | INT (PK, AUTO_INCREMENT)  | Mã món ăn                                    |
| tenMon  | VARCHAR(255)                | Tên món ăn                                     |
| loaiMon | VARCHAR(50)                   | Món chính, Món khai vị, Món tráng miệng          |
| donGia  | BIGINT                           | Đơn giá                                            |
| moTa    | VARCHAR(500)                        | Mô tả món ăn                                          |

### Bảng KHACH_HANG

| Tên cột     | Kiểu dữ liệu              | Mô tả         |
| ----------- | ------------------------- | ---------------|
| maKhachHang | INT (PK, AUTO_INCREMENT)  | Mã khách hàng    |
| hoTen       | VARCHAR(100)                | Họ tên             |
| soDienThoai | VARCHAR(10)                   | Số điện thoại         |
| diaChi      | VARCHAR(255)                     | Địa chỉ                   |

### Bảng HOA_DON

| Tên cột            | Kiểu dữ liệu              | Mô tả                              |
| ------------------ | ------------------------- | -------------------------------------|
| maHoaDon           | INT (PK, AUTO_INCREMENT)  | Mã hóa đơn                             |
| maKhachHang        | INT (FK)                    | Khách hàng                               |
| ngayLap            | DATE                           | Ngày lập hóa đơn                           |
| tongGiaTri         | BIGINT                            | Tổng giá trị hóa đơn                         |
| trangThaiThanhToan | VARCHAR(50)                          | Đã thanh toán / Chưa thanh toán                |

### Bảng CHI_TIET_HOA_DON

| Tên cột   | Kiểu dữ liệu | Mô tả          |
| --------- | ------------ | ---------------|
| maHoaDon  | INT (FK)     | Hóa đơn          |
| maMon     | INT (FK)       | Món ăn             |
| soLuong   | INT              | Số lượng             |
| giaTriMon | BIGINT             | Giá trị món ăn        |

### Quan hệ dữ liệu

- Một khách hàng có thể có nhiều hóa đơn.
- Một hóa đơn có thể chứa nhiều món ăn.
- Một món ăn có thể xuất hiện trong nhiều hóa đơn.
- Quan hệ N-N giữa MON_AN và HOA_DON được xử lý thông qua bảng CHI_TIET_HOA_DON.

---

## 4. Chức năng hệ thống

### 4.1 Quản lý Món ăn

Cho phép thêm mới: Tên món, Loại món, Đơn giá, Mô tả.

**Danh sách Loại món:** Món chính, Món khai vị, Món tráng miệng.

**Validation — Đơn giá:** số nguyên dương, phải chia hết cho 1.000.
```java
@Min(value = 1000)
private Long donGia;
```
Hợp lệ: `90000`, `120000`, `250000`

### 4.2 Quản lý Khách hàng

Cho phép thêm mới: Họ tên, Số điện thoại, Địa chỉ.

**Validation — Số điện thoại:** bắt đầu bằng số 0, gồm đúng 10 chữ số.
```
^0[0-9]{9}$
```
Ví dụ: `0912345678`, `0987654321`

### 4.3 Quản lý Hóa đơn

Cho phép lập hóa đơn đặt món, gồm: Khách hàng (ComboBox từ CSDL), Ngày lập hóa đơn, Tổng giá trị, Trạng thái thanh toán, Danh sách món ăn.

**Chi tiết hóa đơn:** Tên món (Dropdown), Số lượng, Đơn giá.

**Validation — Ngày lập hóa đơn:** không được lớn hơn ngày hiện tại.
```java
@PastOrPresent
private LocalDate ngayLap;
```

**Validation — Số lượng:** số nguyên dương, lớn hơn 0.
```java
@Min(1)
private Integer soLuong;
```

**Trạng thái thanh toán:** Đã thanh toán, Chưa thanh toán.

---

## 5. Chức năng tìm kiếm

### 5.1 Tìm kiếm Khách hàng

Tìm theo: Mã khách hàng, Họ tên, Số điện thoại. Kết quả: Mã khách hàng, Họ tên, Số điện thoại, Địa chỉ.

### 5.2 Tra cứu Hóa đơn

Tìm hóa đơn theo khách hàng. Kết quả gồm **Thông tin hóa đơn** (Mã hóa đơn, Ngày lập, Tổng giá trị, Trạng thái thanh toán) và **Chi tiết món ăn** (Tên món, Số lượng, Giá trị món).

---

## 6. Kiến trúc dự án

```
src/main/java
│
├── controller
│   ├── MonAnController
│   ├── KhachHangController
│   ├── HoaDonController
│   └── TimKiemController
│
├── entity
│   ├── MonAn
│   ├── KhachHang
│   ├── HoaDon
│   └── ChiTietHoaDon
│
├── dao
│   └── NhaHangDAO
│
├── service
│
└── config
    ├── WebConfig
    └── HibernateConfig
```

---

## 7. Yêu cầu kỹ thuật

- **Framework:** Spring MVC, Hibernate/JPA
- **Mô hình MVC:** Controller xử lý request, Service xử lý nghiệp vụ, DAO thao tác dữ liệu, JSP hiển thị giao diện
- **Database:** MySQL hoặc SQL Server
- **Coding Convention:** PascalCase cho Class, camelCase cho biến, tách riêng Controller/Service/DAO/Entity, code rõ ràng, dễ bảo trì

---

## 8. Giao diện

Khuyến khích sử dụng: Bootstrap 5, Responsive Design, Navbar điều hướng, Form nhập liệu đẹp, Bảng dữ liệu trực quan.

---

## Bắt đầu (Getting Started)

### Yêu cầu

- JDK 17+
- MySQL hoặc SQL Server
- IDE: IntelliJ IDEA / Eclipse

### Cài đặt

```bash
git clone https://github.com/nhunguy-swe/restaurant-management-spring-mvc.git
cd restaurant-management-spring-mvc
```

### Cấu hình Database

1. Tạo các bảng `MON_AN`, `KHACH_HANG`, `HOA_DON`, `CHI_TIET_HOA_DON` theo thiết kế ở trên.
2. Cập nhật thông tin kết nối trong `HibernateConfig.java`.

> ⚠️ Không hard-code mật khẩu database trực tiếp trong code nếu push lên GitHub public — dùng biến môi trường hoặc file cấu hình đã thêm vào `.gitignore`.

### Chạy ứng dụng

```bash
mvn clean install
```

Deploy file `.war` lên **Apache Tomcat**, truy cập tại `http://localhost:8080/quan-ly-nha-hang/`.

---

## 9. Kết luận

Hệ thống đáp ứng đầy đủ các yêu cầu: Quản lý Món ăn · Quản lý Khách hàng · Quản lý Hóa đơn · Quản lý Chi tiết Hóa đơn · Xử lý quan hệ N-N giữa Món ăn và Hóa đơn · Validation dữ liệu đầu vào · Tìm kiếm Khách hàng · Tra cứu Hóa đơn · Áp dụng Spring MVC và Hibernate · Tuân thủ Java Coding Convention · Có thể mở rộng giao diện bằng Bootstrap

---

## Tác giả

- GitHub: [@nhunguy-swe](https://github.com/nhunguy-swe)

---

## Giấy phép

Dự án này được thực hiện cho mục đích học tập/ôn thi cá nhân.
