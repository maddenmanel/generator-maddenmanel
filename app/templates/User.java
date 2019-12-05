package <%=packageName%>.<%=baseName%>.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

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

  /**
   * 创建日期
   */
  private Date created;

  /**
   * 修改时间
   */
  private Date modified;


}
