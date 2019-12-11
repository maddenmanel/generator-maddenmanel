package <%=packageName%>.<%=baseName%>.common;

import <%=packageName%>.<%=baseName%>.exception.BindException;
import org.springframework.validation.BeanPropertyBindingResult;

public class BindingResultUtils {
    private BindingResultUtils() {

    }

    public static BindException newEx(Object obj, String name) {
        return new BindException(new BeanPropertyBindingResult(obj, name), true);
    }

}
