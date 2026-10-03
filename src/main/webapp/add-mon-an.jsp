<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Thêm Món Ăn</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<%@ include file="header.jsp" %>
<div class="container mt-4">
    <h2>Nhập Thông Tin Món Ăn</h2>
    <c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>
    <form action="add-mon-an" method="POST">
        <div class="form-group">
            <label>Tên món:</label>
            <input type="text" name="tenMon" class="form-control" required>
        </div>
        <div class="form-group">
            <label>Loại món:</label>
            <select name="loaiMon" class="form-control">
                <option value="MON_CHINH">Món chính</option>
                <option value="KHAI_VI">Món khai vị</option>
                <option value="TRANG_MIENG">Món tráng miệng</option>
            </select>
        </div>
        <div class="form-group">
            <label>Đơn giá (Tròn 1000):</label>
            <input type="number" name="donGia" class="form-control" step="1000" min="1000" required>
        </div>
        <div class="form-group">
            <label>Mô tả:</label>
            <textarea name="moTa" class="form-control"></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Lưu Món Ăn</button>
    </form>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>