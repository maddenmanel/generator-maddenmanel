package <%=packageName%>.<%=baseName%>.exception;

import <%=packageName%>.<%=baseName%>.common.GateWayExceptionConvert;
import <%=packageName%>.<%=baseName%>.common.BindingResultUtils;
import <%=packageName%>.<%=baseName%>.common.SystemIpUtil;
import <%=packageName%>.<%=baseName%>.common.ValidateErrorsVO;
import <%=packageName%>.<%=baseName%>.constants.ErrorCodeConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.Arrays;
import java.util.List;

public class BizExceptionConvert implements GateWayExceptionConvert<BizException>, MessageSourceAware {

    private MessageSource messageSource;

    @Override
    public boolean isException() {
        return true;
    }

    @Override
    public BizException convert(String uuid, Exception e) {
        if (e.getClass() == BindException.class) {
            BindingResult result = ((BindException) e).getBindingResult();
            List<ObjectError> objectErrors = result.getAllErrors();
            ValidateErrorsVO validateErrorsVO = processFieldErrors(objectErrors);
            return BizException.newEx(uuid, SystemIpUtil.ip).setValidateErrorsVO(validateErrorsVO);
        }
        BindingResult result = BindingResultUtils.newEx(e, "e");
        result.reject(ErrorCodeConstants.SYSTEM_ERROR);
        List<ObjectError> objectErrors = result.getAllErrors();
        ValidateErrorsVO validateErrorsVO = processFieldErrors(objectErrors);
        return BizException.newEx(uuid, SystemIpUtil.ip).setValidateErrorsVO(validateErrorsVO);

    }

    private ValidateErrorsVO processFieldErrors(List<ObjectError> fieldErrors) {
        ValidateErrorsVO validateErrorsVO = new ValidateErrorsVO();
        for (ObjectError objectError : fieldErrors) {
            CodeMsg codeMsg = getCodeAndMsg(objectError);
            if (objectError.getClass() == ObjectError.class) {
                validateErrorsVO.setCode(codeMsg.code);
                validateErrorsVO.setMsg(codeMsg.msg);
            } else if (objectError.getClass() == FieldError.class) {
                validateErrorsVO.addFieldError(codeMsg.code, ((FieldError) objectError).getField(), codeMsg.msg);
            }
        }
        return validateErrorsVO;
    }

    private CodeMsg getCodeAndMsg(ObjectError objectError) {
        final CodeMsg codeMsg = tryGetMessageFromProperties(objectError);
        if (codeMsg != null) {
            return codeMsg;
        }
        if (StringUtils.isBlank(objectError.getDefaultMessage())) {
            return CodeMsg.of(objectError.getCode(), objectError.getCode());
        } else {
            return CodeMsg.of(objectError.getCode(), objectError.getDefaultMessage());
        }
    }

    private CodeMsg tryGetMessageFromProperties(ObjectError objectError) {
        Object[] args = convertArgs(objectError.getArguments());
        for (String tmpCode : objectError.getCodes()) {
            String msg = messageSource.getMessage(tmpCode, args, null, LocaleContextHolder.getLocale());
            if (StringUtils.isNotBlank(msg)) {
                return CodeMsg.of(tmpCode, msg);
            }
        }
        return null;
    }

    private static String objectToString(Object object) {
        return object == null ? null : object.toString();
    }

    private Object[] convertArgs(Object[] arguments) {
        if (arguments == null) {
            return new Object[0];
        }
        return Arrays.stream(arguments).map(BizExceptionConvert::objectToString).toArray();
    }


    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    private static class CodeMsg {
        private String code;
        private String msg;

        public static CodeMsg of(String code, String msg) {
            CodeMsg codeMsg = new CodeMsg();
            codeMsg.code = code;
            codeMsg.msg = msg;
            return codeMsg;
        }
    }

}
