package <%=packageName%>.<%=baseName%>.common;

import java.util.ArrayList;
import java.util.List;

public class ValidateErrorsVO {
    /**
     * 业务逻辑校验
     */
    private String code;

    /**
     * 业务逻辑校验
     *
     */
    private String msg;

    /**
     * 字段校验使用
     */
    private List<FieldErrorVO> fieldErrors = new ArrayList<FieldErrorVO>();

    private boolean hasFieldError() {
        return !fieldErrors.isEmpty();
    }

    public void addFieldError(String code, String field, String message) {
        fieldErrors.add(new FieldErrorVO(code, field, message));
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<FieldErrorVO> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldErrorVO> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    @Override
    public String toString() {
        return "code=" + code +
                ", msg=" + msg +
                ", fieldErrors=" + fieldErrors;
    }
}
