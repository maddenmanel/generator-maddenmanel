package <%=packageName%>.<%=baseName%>;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> implements Serializable {

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 返回状态码
     */
    private String code;

    /**
     * 响应错误消息
     */
    private String message;

    /**
     * 返回的实体信息
     */
    private T data;

    /**
     * 批量返回实体信息
     */
    private List<T> datas;
}
