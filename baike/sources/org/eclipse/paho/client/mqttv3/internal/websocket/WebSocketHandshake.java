package org.eclipse.paho.client.mqttv3.internal.websocket;

import com.alipay.android.phone.mrpc.core.Headers;
import com.baidu.mobstat.Config;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WebSocketHandshake {
    InputStream a;
    OutputStream b;
    String c;
    String d;
    int e;

    public WebSocketHandshake(InputStream inputStream, OutputStream outputStream, String str, String str2, int i) {
        this.a = inputStream;
        this.b = outputStream;
        this.c = str;
        this.d = str2;
        this.e = i;
    }

    public void execute() throws IOException {
        String encode = Base64.encode(new StringBuffer("mqtt-").append(System.currentTimeMillis() / 1000).toString());
        a(encode);
        b(encode);
    }

    private void a(String str) throws IOException {
        try {
            String str2 = "/mqtt";
            URI uri = new URI(this.c);
            if (!(uri.getRawPath() == null || uri.getRawPath().isEmpty())) {
                str2 = uri.getRawPath();
                if (!(uri.getRawQuery() == null || uri.getRawQuery().isEmpty())) {
                    str2 = new StringBuffer(String.valueOf(str2)).append("?").append(uri.getRawQuery()).toString();
                }
            }
            PrintWriter printWriter = new PrintWriter(this.b);
            printWriter.print(new StringBuffer("GET ").append(str2).append(" HTTP/1.1").append("\r\n").toString());
            printWriter.print(new StringBuffer("Host: ").append(this.d).append(Config.TRACE_TODAY_VISIT_SPLIT).append(this.e).append("\r\n").toString());
            printWriter.print("Upgrade: websocket\r\n");
            printWriter.print("Connection: Upgrade\r\n");
            printWriter.print(new StringBuffer("Sec-WebSocket-Key: ").append(str).append("\r\n").toString());
            printWriter.print("Sec-WebSocket-Protocol: mqttv3.1\r\n");
            printWriter.print("Sec-WebSocket-Version: 13\r\n");
            printWriter.print("\r\n");
            printWriter.flush();
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    private void b(String str) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.a));
        ArrayList arrayList = new ArrayList();
        String readLine = bufferedReader.readLine();
        if (readLine == null) {
            throw new IOException("WebSocket Response header: Invalid response from Server, It may not support WebSockets.");
        }
        while (!readLine.equals("")) {
            arrayList.add(readLine);
            readLine = bufferedReader.readLine();
        }
        Map a = a(arrayList);
        readLine = (String) a.get(Headers.CONN_DIRECTIVE);
        if (readLine == null || readLine.equalsIgnoreCase("upgrade")) {
            throw new IOException("WebSocket Response header: Incorrect connection header");
        } else if (!((String) a.get("upgrade")).toLowerCase().contains("websocket")) {
            throw new IOException("WebSocket Response header: Incorrect upgrade.");
        } else if (((String) a.get("sec-websocket-protocol")) == null) {
            throw new IOException("WebSocket Response header: empty sec-websocket-protocol");
        } else if (a.containsKey("sec-websocket-accept")) {
            try {
                a(str, (String) a.get("sec-websocket-accept"));
            } catch (NoSuchAlgorithmException e) {
                throw new IOException(e.getMessage());
            } catch (HandshakeFailedException e2) {
                throw new IOException("WebSocket Response header: Incorrect Sec-WebSocket-Key");
            }
        } else {
            throw new IOException("WebSocket Response header: Missing Sec-WebSocket-Accept");
        }
    }

    private Map a(ArrayList arrayList) {
        Map hashMap = new HashMap();
        for (int i = 1; i < arrayList.size(); i++) {
            String[] split = ((String) arrayList.get(i)).split(Config.TRACE_TODAY_VISIT_SPLIT);
            hashMap.put(split[0].toLowerCase(), split[1]);
        }
        return hashMap;
    }

    private void a(String str, String str2) throws NoSuchAlgorithmException, HandshakeFailedException {
        String trim = Base64.encodeBytes(c(new StringBuffer(String.valueOf(str)).append("258EAFA5-E914-47DA-95CA-C5AB0DC85B11").toString())).trim();
        if (!trim.equals(trim)) {
            throw new HandshakeFailedException();
        }
    }

    private byte[] c(String str) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance("SHA1").digest(str.getBytes());
    }
}
