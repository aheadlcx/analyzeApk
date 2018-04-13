package cz.msebera.android.httpclient.util;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

public final class NetUtils {
    public static void formatAddress(StringBuilder stringBuilder, SocketAddress socketAddress) {
        Args.notNull(stringBuilder, "Buffer");
        Args.notNull(socketAddress, "Socket address");
        if (socketAddress instanceof InetSocketAddress) {
            InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddress;
            Object address = inetSocketAddress.getAddress();
            if (address != null) {
                address = address.getHostAddress();
            }
            stringBuilder.append(address).append(':').append(inetSocketAddress.getPort());
            return;
        }
        stringBuilder.append(socketAddress);
    }
}
