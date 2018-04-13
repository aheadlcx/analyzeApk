package com.qiniu.android.dns;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;

public final class Network {
    private static String a = "";

    public static String getIp() {
        try {
            DatagramSocket datagramSocket = new DatagramSocket();
            datagramSocket.connect(InetAddress.getByName("114.114.114.114"), 53);
            InetAddress localAddress = datagramSocket.getLocalAddress();
            datagramSocket.close();
            return localAddress.getHostAddress();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static synchronized boolean isNetworkChanged() {
        boolean z;
        synchronized (Network.class) {
            String ip = getIp();
            if (ip.equals(a)) {
                z = false;
            } else {
                a = ip;
                z = true;
            }
        }
        return z;
    }
}
