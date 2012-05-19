package anno;

public class MyUtils {
  public static String getterFrom(String fieldName) {
    return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1).toLowerCase();
  }
  
  public static String setterFrom(String fieldName) {
    return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1).toLowerCase();
  }
}
