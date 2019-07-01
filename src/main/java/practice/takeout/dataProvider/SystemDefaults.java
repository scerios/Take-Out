package practice.takeout.dataProvider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Component
public class SystemDefaults {
  private Properties properties;
  private final String PROPERTY_FILE_PATH = "configs/Configuration.properties";
  private final String NOT_SPECIFIED_ERR_MSG = " is not specified in the Configuration.properties file.";

  public SystemDefaults() {
    try (BufferedReader reader = new BufferedReader(new FileReader(PROPERTY_FILE_PATH))) {
      properties = new Properties();
      properties.load(reader);
    } catch (FileNotFoundException e) {
      throw new RuntimeException("Cannot find specific file.");
    } catch (IOException e) {
      throw new RuntimeException("Cannot read/write specific file.");
    } catch (Exception e) {
      throw new RuntimeException("Cannot identify the problem.");
    }
  }

  public String getJdbcDriver() {
    String jdbcDriver = properties.getProperty("jdbcDriver");
    if (jdbcDriver != null) {
      return jdbcDriver;
    }
    throw new RuntimeException("JDBC Driver" + NOT_SPECIFIED_ERR_MSG);
  }

  @Value("${DB_URL}")
  public String getDbUrl() {
    String dbUrl = properties.getProperty("dbUrl");
    if (dbUrl != null) {
      return dbUrl;
    }
    throw new RuntimeException("DB URL" + NOT_SPECIFIED_ERR_MSG);
  }

  @Value("${DB_USERNAME}")
  public String getUserName() {
    String userName = properties.getProperty("userName");
    if (userName != null) {
      return userName;
    }
    throw new RuntimeException("Username" + NOT_SPECIFIED_ERR_MSG);
  }

  @Value("${DB_PWD}")
  public String getPwd() {
    String pwd = properties.getProperty("pwd");
    if (pwd != null) {
      return pwd;
    }
    throw new RuntimeException("Password" + NOT_SPECIFIED_ERR_MSG);
  }
}
