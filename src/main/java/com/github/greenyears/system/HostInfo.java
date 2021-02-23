package com.github.greenyears.system;

import com.github.greenyears.core.net.NetUtil;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 当前主机信息.
 * <p/>
 * HostInfo
 *
 * @author zhoumeiqin
 * @date 2021/2/23
 */
@Getter
@ToString
public class HostInfo implements Serializable {
    private static final long serialVersionUID = -8650603489361918331L;
    private final String hostname;
    private final String hostAddress;

    public HostInfo() {
        final InetAddress localhost = NetUtil.getLocalhostAddress();
        this.hostname = this.getLocalHostname();
        if (localhost != null) {
            this.hostAddress = localhost.getHostAddress();
        } else {
            this.hostAddress = null;
        }
    }

    /**
     * 获取本地hostname.
     * <br/>
     * 如果获取失败，则返回null.
     *
     * @return hostname
     */
    private String getLocalHostname() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException var1) {
            return null;
        }
    }
}
