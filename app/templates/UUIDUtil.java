package <%=packageName%>.<%=baseName%>.common;

import java.util.UUID;

/**
 * @author zhangxuegang@jd.com
 */
public class UUIDUtil {
    /**
     * 根据 Java UUID 获取,并且去除所有 "-" 字符串,去除"-"主要是为了好复制.
     *
     * @return
     */
    public static String uuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }
}
