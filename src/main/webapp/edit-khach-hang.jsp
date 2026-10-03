<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chỉnh Sửa Khách Hàng</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .form-container { max-width: 600px; margin: 50px auto; padding: 30px; border-radius: 10px; box-shadow: 0 0 15px rgba(0,0,0,0.1); background: #fff; }
        .btn-update { background-color: #ffc107; color: #000; font-weight: bold; }
    </style>
</head>
<body class="bg-light">
<%@ include file="header.jsp" %>

<div class="container">
    <div class="form-container">
        <h3 class="text-center mb-4 text-warning">Chỉnh Sửa Thông Tin Khách Hàng</h3>

        <form action="edit-khach-hang" method="POST">
            <input type="hidden" name="maKhach" value="${khachHang.maKhach}">

            <div class="form-group">
                <label>Mã khách hàng:</label>
                <input type="text" class="form-control" value="${khachHang.maKhach}" disabled>
                <small class="text-muted">Mã khách hàng không thể thay đổi.</small>
            </div>

            <div class="form-group">
                <label for="hoTen">Họ và tên:</label>
                <input type="text" name="hoTen" id="hoTen" class="form-control"
                       value="${khachHang.hoTen}" required>
            </div>

            <div class="form-group">
                <label for="soDienThoai">Số điện thoại:</label>
                <input type="text" name="soDienThoai" id="soDienThoai" class="form-control"
                       value="${khachHang.soDienThoai}" required>
            </div>

            <div class="form-group">
                <label for="diaChi">Địa chỉ:</label>
                <textarea name="diaChi" id="diaChi" class="form-control" rows="3" required>${khachHang.diaChi}</textarea>
            </div>

            <div class="mt-4">
                <button type="submit" class="btn btn-update btn-block">Lưu Thay Đổi</button>
                <a href="search-khach-hang" class="btn btn-secondary btn-block">Hủy Bỏ</a>
            </div>
        </form>
    </div>
</div>

<%@ include file="footer.jsp" %>
</body>
</html>