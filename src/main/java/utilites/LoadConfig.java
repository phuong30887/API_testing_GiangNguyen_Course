package utilites;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class LoadConfig {
    private final Config config;
    public static final LoadConfig CONFIG = getInstance();

    // biến contractor thành private để từ bên ngoài ko truy cập được vào biến này
    private LoadConfig(){
        this.config = ConfigFactory.load("app.conf");  // app.config là file config chứa các biến môi trường
    }


    public String getProperty(String key){
        String environment = System.getProperty("env"); // env là biến trong pom.xml / maven.surefire
        Config config = this.config.getConfig(environment); // đọc thông tin trong file app.config
        return config.getString(key);  // đọc ra giá trị của biến key trong file app.config : dev.admin-account = "dev-admin"
    }

    public String getProperty(AppProperty key){
        return getProperty(key.getKey());  // đọc ra giá trị của biến key trong file app.config : dev.admin-account = "dev-admin"
    }

    // hàm này giúp chỉ tạo 1 instance và dùng lại về sau.
    public static LoadConfig getInstance() {
        return new LoadConfig();
    }
}
