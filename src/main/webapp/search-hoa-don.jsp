<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tra Cứu Hóa Đơn - Nhà Hàng 365</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .search-section { background: #f8f9fa; padding: 25px; border-radius: 10px; border: 1px solid #dee2e6; margin-bottom: 30px; }
        .invoice-card { transition: transform 0.2s; border-left: 5px solid #007bff; }
        .invoice-card:hover { transform: scale(1.01); box-shadow: 0 4px 15px rgba(0,0,0,0.1); }
        .paid { border-left-color: #28a745; }
        .unpaid { border-left-color: #ffc107; }
        .table-detail { font-size: 0.9rem; }
        .empty-state { text-align: center; padding: 50px; color: #6c757d; }
    </style>
</head>
<body>
<%@ include file="header.jsp" %>

<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h3>Tra Cứu & Quản Lý Hóa Đơn</h3>
        <a href="add-hoa-don" class="btn btn-primary">+ Lập Hóa Đơn Mới</a>
    </div>

    <div class="search-section shadow-sm">
        <form action="search-hoa-don" method="GET" class="form-inline">
            <div class="input-group w-100">
                <input type="text" name="keyword" class="form-control form-control-lg"
                       value="${param.keyword}"
                       placeholder="Nhập Mã hóa đơn hoặc Tên khách hàng để tra cứu...">
                <div class="input-group-append">
                    <button type="submit" class="btn btn-primary px-4">
                        <i class="fa fa-search"></i> Tìm kiếm
                    </button>
                    <a href="search-hoa-don" class="btn btn-outline-secondary">Tất cả</a>
                </div>
            </div>
            <small class="form-text text-muted mt-2">
                Hệ thống sẽ tự động hiển thị toàn bộ hóa đơn nếu bạn không nhập từ khóa.
            </small>
        </form>
    </div>

    <c:choose>
        <c:when test="${not empty listHoaDonFull}">
            <c:forEach items="${listHoaDonFull}" var="hd">
                <div class="card mb-4 invoice-card ${hd.trangThai == 1 ? 'paid' : 'unpaid'}">
                    <div class="card-header bg-white d-flex justify-content-between align-items-center">
                        <div>
                            <span class="badge badge-dark">#${hd.maHoaDon}</span>
                            <span class="ml-2 text-muted"><i class="fa fa-calendar"></i> ${hd.ngayLap}</span>
                        </div>
                        <span class="badge ${hd.trangThai == 1 ? 'badge-success' : 'badge-warning'} p-2">
                                ${hd.trangThai == 1 ? 'ĐÃ THANH TOÁN' : 'CHƯA THANH TOÁN'}
                        </span>
                    </div>
                    <div class="card-body">
                        <div class="row mb-3">
                            <div class="col-md-6">
                                <h6><small class="text-uppercase text-muted">Khách hàng:</small></h6>
                                <h5>${hd.hoTenKhach}</h5>
                            </div>
                        </div>

                        <table class="table table-sm table-hover table-detail border">
                            <thead class="thead-light">
                            <tr>
                                <th>Tên món ăn</th>
                                <th class="text-center">Số lượng</th>
                                <th class="text-right">Đơn giá</th>
                                <th class="text-right">Thành tiền</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${hd.chiTietList}" var="ct">
                                <tr>
                                    <td>${ct.tenMon}</td>
                                    <td class="text-center">${ct.soLuong}</td>
                                    <td class="text-right">${ct.donGia}</td>
                                    <td class="text-right font-weight-bold">${ct.soLuong * ct.donGia}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                            <tfoot>
                            <tr class="table-warning">
                                <td colspan="3" class="text-right font-weight-bold">TỔNG CỘNG:</td>
                                <td class="text-right text-danger font-weight-bold" style="font-size: 1.1rem;">
                                        ${hd.tongTien} VNĐ
                                </td>
                            </tr>
                            </tfoot>
                        </table>
                        <div class="text-right mt-3">
                            <button class="btn btn-sm btn-outline-info">In hóa đơn</button>
                            <c:if test="${hd.trangThai == 0}">
                                <a href="update-status?id=${hd.maHoaDon}" class="btn btn-sm btn-success">Xác nhận thanh toán</a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <div class="empty-state card">
                <div class="card-body">
                    <i class="fa fa-folder-open fa-3x mb-3 text-muted"></i>
                    <p class="lead">Không tìm thấy hóa đơn nào phù hợp.</p>
                    <c:if test="${not empty param.keyword}">
                        <p>Từ khóa tìm kiếm: <strong>"${param.keyword}"</strong></p>
                    </c:if>
                    <a href="search-hoa-don" class="btn btn-primary">Xem tất cả hóa đơn</a>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<%@ include file="footer.jsp" %>
</body>
</html>