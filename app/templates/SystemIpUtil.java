package <%=packageName%>.<%=baseName%>.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class SystemIpUtil {
    private final static Logger logger = LoggerFactory.getLogger(SystemIpUtil.class);
    public static String ip;

    static {
        try {
            for (Enumeration en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = (NetworkInterface) en.nextElement();
                for (Enumeration enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        if (ip != null) {
                            ip = "," + inetAddress.getHostAddress();
                        } else {
                            ip = inetAddress.getHostAddress();
                        }
                    }
                }
            }
            logger.info("system ip address: {}", ip);
        } catch (SocketException ex) {
            logger.error("Socket exception in GetIP Address of Utilities", ex);
        }
    }

}
