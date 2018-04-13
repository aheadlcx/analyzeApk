package org.apache.commons.httpclient.protocol;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.params.HttpConnectionParams;

public interface ProtocolSocketFactory {
    Socket createSocket(String str, int i) throws IOException, UnknownHostException;

    Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException, UnknownHostException;

    Socket createSocket(String str, int i, InetAddress inetAddress, int i2, HttpConnectionParams httpConnectionParams) throws IOException, UnknownHostException, ConnectTimeoutException;
}
