package <%=packageName%>.<%=baseName%>.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
  /**
   * 主键
   */
  public Integer id;

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
