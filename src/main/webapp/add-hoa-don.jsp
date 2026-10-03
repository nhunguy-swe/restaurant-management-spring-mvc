<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lập Hóa Đơn</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<%@ include file="header.jsp" %>
<div class="container mt-4">
    <h3>Lập Hóa Đơn Mới</h3>
    <c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>

    <form action="add-hoa-don" method="POST">
        <div class="row">
            <div class="col-md-6 form-group">
                <label>Khách hàng:</label>
                <select name="maKhach" class="form-control" required>
                    <c:forEach items="${listKH}" var="kh">
                        <option value="${kh.maKhach}">${kh.hoTen}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-6 form-group">
                <label>Ngày lập:</label>
                <input type="date" name="ngayLap" class="form-control" max="<%= LocalDate.now() %>" required>
            </div>
        </div>

        <h5>Chi tiết món ăn:</h5>
        <div style="max-height: 250px; overflow-y: auto;" class="border mb-3">
            <table class="table table-sm">
                <thead>
                <tr><th>Chọn</th><th>Tên món</th><th>Đơn giá</th><th>Số lượng</th></tr>
                </thead>
                <tbody id="monAnTable">
                <c:forEach items="${listMon}" var="mon">
                    <tr>
                        <td><input type="checkbox" name="selectedMon" value="${mon.maMon}" class="chk-mon" onchange="calculateTotal()"></td>
                        <td>${mon.tenMon}</td>
                        <td>
                                ${mon.donGia}
                            <input type="hidden" class="don-gia" name="donGiaList" value="${mon.donGia}">
                        </td>
                        <td>
                            <input type="number" name="soLuongList" value="1" min="1" class="form-control form-control-sm so-luong" oninput="calculateTotal()">
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="row">
            <div class="col-md-6 form-group">
                <label>Tổng giá trị:</label>
                <input type="number" name="tongTien" id="tongTien" class="form-control" min="0" readonly required>
            </div>
            <div class="col-md-6 form-group">
                <label>Trạng thái:</label>
                <select name="trangThai" class="form-control">
                    <option value="0">Chưa thanh toán</option>
                    <option value="1">Đã thanh toán</option>
                </select>
            </div>
        </div>
        <button type="submit" class="btn btn-primary btn-block">Tạo Hóa Đơn</button>
    </form>
</div>
<%@ include file="footer.jsp" %>

<script>
    /**
     * Hàm tự động tính tổng tiền dựa trên các món ăn được chọn và số lượng tương ứng.
     */
    function calculateTotal() {
        let total = 0;
        // Lấy tất cả các dòng trong bảng món ăn
        const rows = document.querySelectorAll("#monAnTable tr");

        rows.forEach(row => {
            const checkbox = row.querySelector(".chk-mon");
            // Chỉ tính toán nếu món ăn được tích chọn
            if (checkbox && checkbox.checked) {
                const price = parseFloat(row.querySelector(".don-gia").value) || 0;
                const quantity = parseInt(row.querySelector(".so-luong").value) || 0;

                // Ràng buộc số lượng phải là số nguyên dương
                if (quantity > 0) {
                    total += price * quantity;
                }
            }
        });

        // Cập nhật giá trị vào ô Tổng giá trị
        document.getElementById("tongTien").value = total;
    }

    // Đảm bảo tính toán lại nếu trang được load lại với các giá trị cũ
    window.onload = calculateTotal;
</script>
</body>
</html>