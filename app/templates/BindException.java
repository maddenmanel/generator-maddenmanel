package <%=packageName%>.<%=baseName%>.exception;

import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.beans.PropertyEditor;
import java.util.List;
import java.util.Map;

public class BindException extends RuntimeException implements BindingResult {
    private boolean functionError;
    private final BindingResult bindingResult;

    public BindException(BindingResult bindingResult, boolean functionError) {
        this.bindingResult = bindingResult;
        this.functionError = functionError;
    }

    public boolean isFunctionError() {
        return functionError;
    }

    public void setFunctionError(boolean functionError) {
        this.functionError = functionError;
    }

    @Override
    public Object getTarget() {
        return bindingResult.getTarget();
    }

    @Override
    public Map<String, Object> getModel() {
        return bindingResult.getModel();
    }

    @Override
    public Object getRawFieldValue(String field) {
        return bindingResult.getRawFieldValue(field);
    }

    @Override
    public PropertyEditor findEditor(String field, Class<?> valueType) {
        return bindingResult.findEditor(field, valueType);
    }

    @Override
    public PropertyEditorRegistry getPropertyEditorRegistry() {
        return bindingResult.getPropertyEditorRegistry();
    }

    @Override
    public void addError(ObjectError error) {
        bindingResult.addError(error);
    }

    @Override
    public String[] resolveMessageCodes(String errorCode) {
        return bindingResult.resolveMessageCodes(errorCode);
    }

    @Override
    public String[] resolveMessageCodes(String errorCode, String field) {
        return bindingResult.resolveMessageCodes(errorCode, field);
    }

    @Override
    public void recordSuppressedField(String field) {
        bindingResult.recordSuppressedField(field);
    }

    @Override
    public String[] getSuppressedFields() {
        return bindingResult.getSuppressedFields();
    }

    @Override
    public String getObjectName() {
        return bindingResult.getObjectName();
    }

    @Override
    public void setNestedPath(String nestedPath) {
        bindingResult.setNestedPath(nestedPath);
    }

    @Override
    public String getNestedPath() {
        return bindingResult.getNestedPath();
    }

    @Override
    public void pushNestedPath(String subPath) {
        bindingResult.pushNestedPath(subPath);
    }

    @Override
    public void popNestedPath() throws IllegalStateException {
        bindingResult.popNestedPath();
    }

    @Override
    public void reject(String errorCode) {
        bindingResult.reject(errorCode);
    }

    @Override
    public void reject(String errorCode, String defaultMessage) {
        bindingResult.reject(errorCode, defaultMessage);
    }

    @Override
    public void reject(String errorCode, Object[] errorArgs, String defaultMessage) {
        bindingResult.reject(errorCode, errorArgs, defaultMessage);
    }

    @Override
    public void rejectValue(String field, String errorCode) {
        bindingResult.rejectValue(field, errorCode);
    }

    @Override
    public void rejectValue(String field, String errorCode, String defaultMessage) {
        bindingResult.rejectValue(field, errorCode, defaultMessage);
    }

    @Override
    public void rejectValue(String field, String errorCode, Object[] errorArgs, String defaultMessage) {
        bindingResult.rejectValue(field, errorCode, errorArgs, defaultMessage);
    }

    @Override
    public void addAllErrors(Errors errors) {
        bindingResult.addAllErrors(errors);
    }

    @Override
    public boolean hasErrors() {
        return bindingResult.hasErrors();
    }

    @Override
    public int getErrorCount() {
        return bindingResult.getErrorCount();
    }

    @Override
    public List<ObjectError> getAllErrors() {
        return bindingResult.getAllErrors();
    }

    @Override
    public boolean hasGlobalErrors() {
        return bindingResult.hasGlobalErrors();
    }

    @Override
    public int getGlobalErrorCount() {
        return bindingResult.getGlobalErrorCount();
    }

    @Override
    public List<ObjectError> getGlobalErrors() {
        return bindingResult.getGlobalErrors();
    }

    @Override
    public ObjectError getGlobalError() {
        return bindingResult.getGlobalError();
    }

    @Override
    public boolean hasFieldErrors() {
        return bindingResult.hasFieldErrors();
    }

    @Override
    public int getFieldErrorCount() {
        return bindingResult.getFieldErrorCount();
    }

    @Override
    public List<FieldError> getFieldErrors() {
        return bindingResult.getFieldErrors();
    }

    @Override
    public FieldError getFieldError() {
        return bindingResult.getFieldError();
    }

    @Override
    public boolean hasFieldErrors(String field) {
        return bindingResult.hasFieldErrors(field);
    }

    @Override
    public int getFieldErrorCount(String field) {
        return bindingResult.getFieldErrorCount(field);
    }

    @Override
    public List<FieldError> getFieldErrors(String field) {
        return bindingResult.getFieldErrors(field);
    }

    @Override
    public FieldError getFieldError(String field) {
        return bindingResult.getFieldError(field);
    }

    @Override
    public Object getFieldValue(String field) {
        return bindingResult.getFieldValue(field);
    }

    @Override
    public Class<?> getFieldType(String field) {
        return bindingResult.getFieldType(field);
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }


    /**
     * Returns diagnostic information about the errors held in this object.
     */
    @Override
    public String getMessage() {
        return this.bindingResult.toString();
    }
}
