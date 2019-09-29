package <%=packageName%>.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;

@Slf4j
public class GsonUtil {


  /**
   * Convert object to json String.
   *
   * @param object
   * @return
   */
  public static String objectToJsonStr(Object object) {
    if (object == null) {
      return null;
    }
    Gson gson = new GsonBuilder().disableHtmlEscaping().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    String ret;
    try {
      ret = gson.toJson(object);
    } catch (Exception e) {
      log.error("ObjectToJsonStr error, the object: {}", object.toString());
      throw new RuntimeException("ObjectToJsonStr error, the object: " + object.toString(), e);
    }
    return ret;
  }


  /**
   * Convert json String to Object.
   *
   * @param jsonStr
   * @param t
   * @param <T>
   * @return
   */
  public static <T> T jsonToObject(String jsonStr, Class<T> t) {
    Gson gson = new GsonBuilder().disableHtmlEscaping().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    Type type = TypeToken.get(t).getType();
    try {
      return gson.fromJson(jsonStr, type);
    } catch (Exception e) {
      log.error("JsonToObject error,the jsonStr: {}", jsonStr, e);
      return null;
    }
  }


}
