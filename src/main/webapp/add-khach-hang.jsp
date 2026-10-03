<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Thêm Khách Hàng</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<%@ include file="header.jsp" %>
<div class="container mt-4">
    <h2>Nhập Thông Tin Khách Hàng</h2>
    <c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>
    <form action="add-khach-hang" method="POST">
        <div class="form-group">
            <label>Họ tên:</label>
            <input type="text" name="hoTen" class="form-control" required>
        </div>
        <div class="form-group">
            <label>Số điện thoại:</label>
            <input type="text" name="soDienThoai" class="form-control" pattern="^0[0-9]{9}$" placeholder="0xxxxxxxxx" required>
        </div>
        <div class="form-group">
            <label>Địa chỉ:</label>
            <input type="text" name="diaChi" class="form-control">
        </div>
        <button type="submit" class="btn btn-success">Lưu Khách Hàng</button>
    </form>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>