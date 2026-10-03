# HỆ THỐNG QUẢN LÝ NHÀ HÀNG

## 1. Giới thiệu

Hệ thống Quản lý Nhà hàng được xây dựng nhằm hỗ trợ quản lý thông tin món ăn, khách hàng và hóa đơn. Hệ thống cho phép lưu trữ dữ liệu món ăn, lập hóa đơn đặt món và tra cứu thông tin khách hàng cũng như lịch sử hóa đơn.

---

## 2. Công nghệ sử dụng

* Java
* Spring MVC
* Hibernate/JPA
* MySQL hoặc SQL Server
* JSP/Servlet
* Bootstrap 5 (tùy chọn)
* Maven

---

## 3. Thiết kế cơ sở dữ liệu

### Bảng MON_AN

| Tên cột | Kiểu dữ liệu             | Mô tả                                   |
| ------- | ------------------------ | --------------------------------------- |
| maMon   | INT (PK, AUTO_INCREMENT) | Mã món ăn                               |
| tenMon  | VARCHAR(255)             | Tên món ăn                              |
| loaiMon | VARCHAR(50)              | Món chính, Món khai vị, Món tráng miệng |
| donGia  | BIGINT                   | Đơn giá                                 |
| moTa    | VARCHAR(500)             | Mô tả món ăn                            |

---

### Bảng KHACH_HANG

| Tên cột     | Kiểu dữ liệu             | Mô tả         |
| ----------- | ------------------------ | ------------- |
| maKhachHang | INT (PK, AUTO_INCREMENT) | Mã khách hàng |
| hoTen       | VARCHAR(100)             | Họ tên        |
| soDienThoai | VARCHAR(10)              | Số điện thoại |
| diaChi      | VARCHAR(255)             | Địa chỉ       |

---

### Bảng HOA_DON

| Tên cột            | Kiểu dữ liệu             | Mô tả                           |
| ------------------ | ------------------------ | ------------------------------- |
| maHoaDon           | INT (PK, AUTO_INCREMENT) | Mã hóa đơn                      |
| maKhachHang        | INT (FK)                 | Khách hàng                      |
| ngayLap            | DATE                     | Ngày lập hóa đơn                |
| tongGiaTri         | BIGINT                   | Tổng giá trị hóa đơn            |
| trangThaiThanhToan | VARCHAR(50)              | Đã thanh toán / Chưa thanh toán |

---

### Bảng CHI_TIET_HOA_DON

| Tên cột   | Kiểu dữ liệu | Mô tả          |
| --------- | ------------ | -------------- |
| maHoaDon  | INT (FK)     | Hóa đơn        |
| maMon     | INT (FK)     | Món ăn         |
| soLuong   | INT          | Số lượng       |
| giaTriMon | BIGINT       | Giá trị món ăn |

### Quan hệ dữ liệu

* Một khách hàng có thể có nhiều hóa đơn.
* Một hóa đơn có thể chứa nhiều món ăn.
* Một món ăn có thể xuất hiện trong nhiều hóa đơn.
* Quan hệ N-N giữa MON_AN và HOA_DON được xử lý thông qua bảng CHI_TIET_HOA_DON.

---

## 4. Chức năng hệ thống

### 4.1 Quản lý Món ăn

Cho phép thêm mới món ăn:

* Tên món
* Loại món
* Đơn giá
* Mô tả

#### Danh sách Loại món

* Món chính
* Món khai vị
* Món tráng miệng

#### Validation

**Đơn giá**

* Là số nguyên dương.
* Phải chia hết cho 1.000.

Ví dụ hợp lệ:

```java
90000
120000
250000
```

Ví dụ Validation:

```java
@Min(value = 1000)
private Long donGia;
```

---

### 4.2 Quản lý Khách hàng

Cho phép thêm mới khách hàng:

* Họ tên
* Số điện thoại
* Địa chỉ

#### Validation

**Số điện thoại**

* Bắt đầu bằng số 0.
* Gồm đúng 10 chữ số.

Regex:

```java
^0[0-9]{9}$
```

Ví dụ:

```java
0912345678
0987654321
```

---

### 4.3 Quản lý Hóa đơn

Cho phép lập hóa đơn đặt món.

Thông tin gồm:

* Khách hàng (ComboBox từ CSDL)
* Ngày lập hóa đơn
* Tổng giá trị
* Trạng thái thanh toán
* Danh sách món ăn

Chi tiết hóa đơn:

* Tên món (Dropdown)
* Số lượng
* Đơn giá

#### Validation

**Ngày lập hóa đơn**

* Không được lớn hơn ngày hiện tại.

Ví dụ:

```java
@PastOrPresent
private LocalDate ngayLap;
```

**Số lượng**

* Là số nguyên dương.
* Lớn hơn 0.

Ví dụ:

```java
@Min(1)
private Integer soLuong;
```

#### Trạng thái thanh toán

* Đã thanh toán
* Chưa thanh toán

---

## 5. Chức năng tìm kiếm

### 5.1 Tìm kiếm Khách hàng

Cho phép tìm kiếm:

* Mã khách hàng
* Họ tên
* Số điện thoại

Kết quả hiển thị:

* Mã khách hàng
* Họ tên
* Số điện thoại
* Địa chỉ

---

### 5.2 Tra cứu Hóa đơn

Tìm kiếm hóa đơn theo khách hàng.

Kết quả hiển thị:

#### Thông tin hóa đơn

* Mã hóa đơn
* Ngày lập hóa đơn
* Tổng giá trị
* Trạng thái thanh toán

#### Chi tiết món ăn

* Tên món ăn
* Số lượng
* Giá trị món ăn

---

## 6. Kiến trúc dự án

```text
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

### Framework

* Spring MVC
* Hibernate/JPA

### Mô hình MVC

* Controller xử lý request.
* Service xử lý nghiệp vụ.
* DAO thao tác dữ liệu.
* JSP hiển thị giao diện.

### Cơ sở dữ liệu

* MySQL hoặc SQL Server.

### Coding Convention

* Class sử dụng PascalCase.
* Biến sử dụng camelCase.
* Tách riêng Controller, Service, DAO, Entity.
* Code rõ ràng, dễ bảo trì.

---

## 8. Giao diện

Khuyến khích sử dụng:

* Bootstrap 5
* Responsive Design
* Navbar điều hướng
* Form nhập liệu đẹp
* Bảng dữ liệu trực quan

Điểm cộng tối đa:

* 1.0 điểm

---

## 9. Kết luận

Hệ thống đáp ứng đầy đủ các yêu cầu:

✔ Quản lý Món ăn

✔ Quản lý Khách hàng

✔ Quản lý Hóa đơn

✔ Quản lý Chi tiết Hóa đơn

✔ Xử lý quan hệ N-N giữa Món ăn và Hóa đơn

✔ Validation dữ liệu đầu vào

✔ Tìm kiếm Khách hàng

✔ Tra cứu Hóa đơn

✔ Áp dụng Spring MVC và Hibernate

✔ Tuân thủ Java Coding Convention

✔ Có thể mở rộng giao diện bằng Bootstrap
