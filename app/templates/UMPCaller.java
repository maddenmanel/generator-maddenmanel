package <%=packageName%>.<%=baseName%>.common;

import com.jd.ump.profiler.CallerInfo;
import com.jd.ump.profiler.proxy.Profiler;
import <%=packageName%>.<%=baseName%>.constants.UmpConstants;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

@Slf4j
public final class UMPCaller {
    public static <T> T callWithTp(String key, Supplier<T> supplier) {
        CallerInfo callerInfo = Profiler.registerInfo(key, UmpConstants.UMP_APP_KEY, UmpConstants.UMP_DISABLE_HEART, UmpConstants.UMP_ENABLE_TP);
        try {
            return supplier.get();
        } catch (Exception e) {
            Profiler.functionError(callerInfo);
            throw e;
        } finally {
            Profiler.registerInfoEnd(callerInfo);
        }
    }

    public static void callWithTp(String key, VoidCaller caller) {
        CallerInfo callerInfo = Profiler.registerInfo(key, UmpConstants.UMP_APP_KEY, UmpConstants.UMP_DISABLE_HEART, UmpConstants.UMP_ENABLE_TP);
        try {
            caller.call();
        } catch (Exception e) {
            Profiler.functionError(callerInfo);
            throw e;
        } finally {
            Profiler.registerInfoEnd(callerInfo);
        }
    }
}
