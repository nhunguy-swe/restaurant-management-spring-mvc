package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    // Thông tin kết nối MySQL
    private static final String URL = "jdbc:mysql://localhost:3306/quan_ly_nha_hang?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = ""; // Thay bằng username của bạn
    private static final String PASS = ""; // Thay bằng password của bạn

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // Nạp Driver MySQL
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Trả về đối tượng kết nối
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
