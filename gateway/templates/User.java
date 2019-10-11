package <%=packageName%>.<%=baseName%>.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
  /**
   * 主键
   */
  public Long id;

  /**
   * 姓名
   */
  public String name;

  /**
   * 年龄
   */
  public Integer age;

  /**
   * 地址
   */
  public String addr;
}
