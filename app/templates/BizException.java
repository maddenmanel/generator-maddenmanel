package <%=packageName%>.<%=baseName%>.exception;

import <%=packageName%>.<%=baseName%>.common.ValidateErrorsVO;

public class BizException extends RuntimeException{
    private String uuid;

    private String providerIp;

    private ValidateErrorsVO validateErrorsVO;

    public static BizException newEx(String uuid, String providerIp) {
        BizException bizException = new BizException();
        bizException.setUuid(uuid);
        bizException.setProviderIp(providerIp);
        bizException.setStackTrace(new StackTraceElement[0]);
        return bizException;
    }

    public String getUuid() {
        return uuid;
    }

    public String getProviderIp() {
        return providerIp;
    }

    public ValidateErrorsVO getValidateErrorsVO() {
        return validateErrorsVO;
    }

    public BizException setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public BizException setProviderIp(String providerIp) {
        this.providerIp = providerIp;
        return this;
    }

    public BizException setValidateErrorsVO(ValidateErrorsVO validateErrorsVO) {
        this.validateErrorsVO = validateErrorsVO;
        return this;
    }

    @Override
    public String getMessage() {
        return "uuid:" + uuid + " providerIp:" + providerIp + " validateErrorsVO:" + validateErrorsVO;
    }

}
