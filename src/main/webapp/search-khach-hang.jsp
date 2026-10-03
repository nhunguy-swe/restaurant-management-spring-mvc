<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản Lý Khách Hàng</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .search-box { background-color: #f8f9fa; padding: 20px; border-radius: 8px; margin-bottom: 20px; border: 1px solid #dee2e6; }
        .table thead { background-color: #17a2b8; color: white; }
        .empty-msg { padding: 20px; text-align: center; color: #6c757d; font-style: italic; }
    </style>
</head>
<body>
<%@ include file="header.jsp" %>

<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h3>Quản Lý Khách Hàng</h3>
        <a href="add-khach-hang.jsp" class="btn btn-success">+ Thêm Khách Hàng</a>
    </div>

    <div class="search-box">
        <form action="search-khach-hang" method="GET" class="form-inline">
            <div class="input-group w-100">
                <input type="text" name="keyword" class="form-control"
                       value="${param.keyword}"
                       placeholder="Nhập Mã, Họ tên hoặc Số điện thoại để tìm kiếm...">
                <div class="input-group-append">
                    <button type="submit" class="btn btn-info px-4">
                        <i class="fa fa-search"></i> Tìm kiếm
                    </button>
                    <a href="search-khach-hang" class="btn btn-secondary">Tất cả</a>
                </div>
            </div>
        </form>
    </div>

    <div class="card shadow-sm">
        <table class="table table-hover mb-0">
            <thead>
            <tr>
                <th>Mã KH</th>
                <th>Họ tên</th>
                <th>Số điện thoại</th>
                <th>Địa chỉ</th>
                <th class="text-center">Thao tác</th>
            </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${not empty resultKH}">
                    <c:forEach items="${resultKH}" var="kh">
                        <tr>
                            <td class="font-weight-bold">#${kh.maKhach}</td>
                            <td>${kh.hoTen}</td>
                            <td>${kh.soDienThoai}</td>
                            <td>${kh.diaChi}</td>
                            <td class="text-center">
                                <a href="edit-khach-hang?id=${kh.maKhach}" class="btn btn-sm btn-outline-warning">Sửa</a>
                                <button onclick="confirmDelete(${kh.maKhach})" class="btn btn-sm btn-outline-danger">Xóa</button>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="5" class="empty-msg">
                            Không tìm thấy khách hàng nào phù hợp với từ khóa "${param.keyword}"
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>
    </div>
</div>

<%@ include file="footer.jsp" %>

<script>
    function confirmDelete(id) {
        if (confirm('Bạn có chắc chắn muốn xóa khách hàng #' + id + ' không?')) {
            window.location.href = 'delete-khach-hang?id=' + id;
        }
    }
</script>
</body>
</html>