package com.github.greenyears.core.net;

import com.github.greenyears.core.exception.ApiException;
import lombok.experimental.UtilityClass;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Network Util.
 * <p/>
 * NetUtil
 *
 * @author zhoumeiqin
 * @date 2021/2/23
 */
@UtilityClass
public class NetUtil {
    /**
     * 获取本地地址.
     *
     * @return ip address
     */
    public static InetAddress getLocalhostAddress() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) networkInterfaces.nextElement();
                Enumeration<InetAddress> nias = ni.getInetAddresses();
                while (nias.hasMoreElements()) {
                    InetAddress ia = (InetAddress) nias.nextElement();
                    if (!ia.isLinkLocalAddress() && !ia.isLoopbackAddress() && ia instanceof Inet4Address) {
                        return ia;
                    }
                }
            }
        } catch (SocketException e) {
            throw new ApiException("获取当前机器地址失败");
        }
        return null;
    }
}
