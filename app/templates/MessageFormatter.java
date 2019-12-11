package <%=packageName%>.<%=baseName%>.common;

import java.util.HashMap;
import java.util.Map;

final public class MessageFormatter {
    private static final char DELIM_START = '{';
    private static final String DELIM_STR = "{}";
    private static final char ESCAPE_CHAR = '\\';

    /**
     * 类SLF4J 替换{}
     *
     * @param messagePattern
     * @param argArray
     * @return
     */
    public static String format(final String messagePattern, final Object... argArray) {
        if (messagePattern == null || messagePattern.length() == 0) {
            return messagePattern;
        }
        if (argArray == null || argArray.length == 0) {
            return messagePattern;
        }
        int startIndex = 0;
        int searchIndex;
        StringBuilder sb = new StringBuilder(messagePattern.length() + 50);
        for (int L = 0; L < argArray.length; L++) {
            searchIndex = messagePattern.indexOf(DELIM_STR, startIndex);
            if (searchIndex == -1) {
                // 参数比 {} 多才会出现此情况
                // 找不到 {}
                if (startIndex == 0) {
                    // 从一开始就找不到{}
                    return messagePattern;
                } else {
                    // 放入剩余的字符床, 返回
                    sb.append(messagePattern.substring(startIndex, messagePattern.length()));
                    return sb.toString();
                }
            } else {
                if (isEscapedDelimeter(messagePattern, searchIndex)) {
                    // {}前面有\
                    if (isDoubleEscaped(messagePattern, searchIndex)) {
                        // {}前面有两个\\
                        sb.append(messagePattern.substring(startIndex, searchIndex - 1));
                        deeplyAppendParameter(sb, argArray[L], new HashMap<Object[], Object>());
                        startIndex = searchIndex + 2;
                    } else {
                        L--;
                        sb.append(messagePattern.substring(startIndex, searchIndex - 1));
                        sb.append(DELIM_START);
                        startIndex = searchIndex + 1;
                    }
                } else {
                    sb.append(messagePattern.substring(startIndex, searchIndex));
                    deeplyAppendParameter(sb, argArray[L], new HashMap<Object[], Object>());
                    startIndex = searchIndex + 2;
                }
            }
        }
        // 参数比{}少的情况
        sb.append(messagePattern.substring(startIndex, messagePattern.length()));
        return sb.toString();
    }

    private static void deeplyAppendParameter(StringBuilder sbuf, Object o,
                                              Map<Object[], Object> seenMap) {
        if (o == null) {
            sbuf.append("null");
            return;
        }
        if (!o.getClass().isArray()) {
            safeObjectAppend(sbuf, o);
        } else {
            // check for primitive array types because they
            // unfortunately cannot be cast to Object[]
            if (o instanceof boolean[]) {
                booleanArrayAppend(sbuf, (boolean[]) o);
            } else if (o instanceof byte[]) {
                byteArrayAppend(sbuf, (byte[]) o);
            } else if (o instanceof char[]) {
                charArrayAppend(sbuf, (char[]) o);
            } else if (o instanceof short[]) {
                shortArrayAppend(sbuf, (short[]) o);
            } else if (o instanceof int[]) {
                intArrayAppend(sbuf, (int[]) o);
            } else if (o instanceof long[]) {
                longArrayAppend(sbuf, (long[]) o);
            } else if (o instanceof float[]) {
                floatArrayAppend(sbuf, (float[]) o);
            } else if (o instanceof double[]) {
                doubleArrayAppend(sbuf, (double[]) o);
            } else {
                objectArrayAppend(sbuf, (Object[]) o, seenMap);
            }
        }
    }

    private static void objectArrayAppend(StringBuilder sbuf, Object[] a,
                                          Map<Object[], Object> seenMap) {
        sbuf.append('[');
        if (!seenMap.containsKey(a)) {
            seenMap.put(a, null);
            final int len = a.length;
            for (int i = 0; i < len; i++) {
                deeplyAppendParameter(sbuf, a[i], seenMap);
                if (i != len - 1)
                    sbuf.append(", ");
            }
            // allow repeats in siblings
            seenMap.remove(a);
        } else {
            sbuf.append("...");
        }
        sbuf.append(']');
    }

    private static void booleanArrayAppend(StringBuilder sbuf, boolean[] a) {
        sbuf.append('[');
        final int len = a.length;
        for (int i = 0; i < len; i++) {
            sbuf.append(a[i]);
            if (i != len - 1)
                sbuf.append(", ");
        }
        sbuf.append(']');
    }

    private static void safeObjectAppend(StringBuilder sbuf, Object o) {
        try {
            String oAsString = o.toString();
            sbuf.append(oAsString);
        } catch (Throwable t) {
            System.err
                    .println("SLF4J: Failed toString() invocation on an object of type ["
                            + o.getClass().getName() + "]");
            t.printStackTrace();
            sbuf.append("[FAILED toString()]");
        }

    }

    private static void byteArrayAppend(StringBuilder sbuf, byte[] a) {
        sbuf.append('[');
        final int len = a.length;
        for (int i = 0; i < len; i++) {
            sbuf.append(a[i]);
            if (i != len - 1)
                sbuf.append(", ");
        }
        sbuf.append(']');
    }

    private static void charArrayAppend(StringBuilder sbuf, char[] a) {
        sbuf.append('[');
        final int len = a.length;
        for (int i = 0; i < len; i++) {
            sbuf.append(a[i]);
            if (i != len - 1)
                sbuf.append(", ");
        }
        sbuf.append(']');
    }

    private static void shortArrayAppend(StringBuilder sbuf, short[] a) {
        sbuf.append('[');
        final int len = a.length;
        for (int i = 0; i < len; i++) {
            sbuf.append(a[i]);
            if (i != len - 1)
                sbuf.append(", ");
        }
        sbuf.append(']');
    }

    private static void intArrayAppend(StringBuilder sbuf, int[] a) {
        sbuf.append('[');
        final int len = a.length;
        for (int i = 0; i < len; i++) {
            sbuf.append(a[i]);
            if (i != len - 1)
                sbuf.append(", ");
        }
        sbuf.append(']');
    }

    private static void longArrayAppend(StringBuilder sbuf, long[] a) {
        sbuf.append('[');
        final int len = a.length;
        for (int i = 0; i < len; i++) {
            sbuf.append(a[i]);
            if (i != len - 1)
                sbuf.append(", ");
        }
        sbuf.append(']');
    }

    private static void floatArrayAppend(StringBuilder sbuf, float[] a) {
        sbuf.append('[');
        final int len = a.length;
        for (int i = 0; i < len; i++) {
            sbuf.append(a[i]);
            if (i != len - 1)
                sbuf.append(", ");
        }
        sbuf.append(']');
    }

    private static void doubleArrayAppend(StringBuilder sbuf, double[] a) {
        sbuf.append('[');
        final int len = a.length;
        for (int i = 0; i < len; i++) {
            sbuf.append(a[i]);
            if (i != len - 1)
                sbuf.append(", ");
        }
        sbuf.append(']');
    }

    /**
     * 判断{}前面是否有\符号
     *
     * @param messagePattern
     * @param delimeterStartIndex
     * @return
     */
    private static boolean isEscapedDelimeter(String messagePattern, int delimeterStartIndex) {
        if (delimeterStartIndex == 0) {
            return false;
        }
        char potentialEscape = messagePattern.charAt(delimeterStartIndex - 1);
        return potentialEscape == ESCAPE_CHAR;
    }

    /**
     * 判断前面是否有两个\符号
     *
     * @param messagePattern
     * @param delimeterStartIndex
     * @return
     */
    private static boolean isDoubleEscaped(String messagePattern, int delimeterStartIndex) {
        return delimeterStartIndex >= 2
                && messagePattern.charAt(delimeterStartIndex - 2) == ESCAPE_CHAR;
    }
}
