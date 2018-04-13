package cz.msebera.android.httpclient.conn;

import java.net.InetAddress;
import java.net.UnknownHostException;

public interface DnsResolver {
    InetAddress[] resolve(String str) throws UnknownHostException;
}
