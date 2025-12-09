package base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyReader {

  private static final Properties PROPERTIES = new Properties();
  private static final String CONFIG_FILE = "config.properties";

  static {
    try (InputStream stream =
        PropertyReader.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
      if (stream != null) {
        PROPERTIES.load(stream);
      } else {
        throw new IllegalStateException("Unable to locate " + CONFIG_FILE + " on the classpath.");
      }
    } catch (IOException e) {
      throw new IllegalStateException("Unable to load configuration from " + CONFIG_FILE, e);
    }
  }

  private PropertyReader() {}

  public static String get(String key) {
    String sys = System.getProperty(key);
    if (sys != null && !sys.isBlank()) {
      return sys.trim();
    }

    String env = System.getenv(key);
    if (env != null && !env.isBlank()) {
      return env.trim();
    }

    String value = PROPERTIES.getProperty(key);
    return value != null ? value.trim() : null;
  }

  public static String getOrDefault(String key, String defaultValue) {
    String value = get(key);
    return (value == null || value.isBlank()) ? defaultValue : value;
  }
}
