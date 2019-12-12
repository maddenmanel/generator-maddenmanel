package <%=packageName%>.<%=baseName%>.interceptor;

import <%=packageName%>.<%=baseName%>.common.AnnotatedElementCacheUtils;
import <%=packageName%>.<%=baseName%>.common.annotation.EasyValid;
import <%=packageName%>.<%=baseName%>.exception.BindException;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.lang.reflect.Method;

public class EasyValidInterceptor implements MethodInterceptor {
    private final SpringValidatorAdapter validator;

    private final ParameterNameDiscoverer parameterNameDiscoverer;


    public EasyValidInterceptor() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = new SpringValidatorAdapter(validatorFactory.getValidator());
        this.parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        if (checkNeedValidation(invocation)) {
            Method targetMethod = invocation.getThis().getClass().getMethod(invocation.getMethod().getName(), invocation.getMethod().getParameterTypes());
            String[] parameterNames = parameterNameDiscoverer.getParameterNames(targetMethod);
            Object[] arguments = invocation.getArguments();
            for (int i = 0; i < arguments.length; i++) {
                String pName = parameterNames[i];
                Object param = arguments[i];
                DataBinder dataBinder = new DataBinder(param, pName);
                dataBinder.setValidator(this.validator);
                dataBinder.validate();
                BindingResult bindingResult = dataBinder.getBindingResult();
                if (bindingResult.hasErrors()) {
                    throw new BindException(bindingResult, false);
                }
            }
            return invocation.proceed();
        } else {
            return invocation.proceed();
        }
    }

    private boolean checkNeedValidation(MethodInvocation invocation) throws NoSuchMethodException {
        EasyValid easyValid = AnnotatedElementCacheUtils.getMergedAnnotation(
                invocation.getThis().getClass().getMethod(invocation.getMethod().getName(), invocation.getMethod().getParameterTypes()),
                EasyValid.class);
        if (easyValid == null) {
            easyValid = AnnotatedElementCacheUtils.getMergedAnnotation(invocation.getThis().getClass(), EasyValid.class);
            return easyValid != null && easyValid.value();
        } else {
            return easyValid.value();
        }
    }
}
