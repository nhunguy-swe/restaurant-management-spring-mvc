package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4J {
    /**
     * Khởi tạo logger cho class này.
     * Log4j 2 sẽ tự động nạp cấu hình từ các file .properties hoặc .xml
     * trong thư mục resources.
     */
    private static final Logger logger = LogManager.getLogger(Log4J.class);

    /**
     * Method để lấy logger.
     * Ở Log4j 2, cấu hình thường được nạp tự động khi ứng dụng khởi chạy.
     * * @return Logger
     */
    public static Logger getLogger() {
        return logger;
    }
}

