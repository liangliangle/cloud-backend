package test.lianglianglee.cloud.config;

import org.junit.Test;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Set;

public class PropertiesTest {

  @Test
  public void initializing() {

    Connection conn = null;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      // String url = "jdbc:postgresql://172.27.244.94:5432/postgres";
      String url = "jdbc:mysql://localhost:3306/cloud_edit?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Hongkong&useSSL=false&allowPublicKeyRetrieval=true";
      conn = DriverManager.getConnection(url, "root", "root");
      Properties properties = new Properties();
      properties.load(new FileInputStream("D:\\My Project\\config.properties"));
      Set<String> keys = properties.stringPropertyNames();
      for (String key : keys) {
        String sql = "insert into config_properties (" +
          "`_key`,\n" +
          "`_value`,\n" +
          "application,\n" +
          "`profile`,\n" +
          "label\n" +
          ") values( ?, ?, 'eureka-server', 'prod', 'master')";
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.setObject(1, key);
        pre.setObject(2, properties.getProperty(key));
        pre.executeUpdate();
      }

      //System.out.println(count);

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
