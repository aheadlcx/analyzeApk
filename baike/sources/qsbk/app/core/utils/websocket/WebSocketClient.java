package qsbk.app.core.utils.websocket;

import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.util.Base64;
import com.alipay.sdk.util.h;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.StatusLine;
import cz.msebera.android.httpclient.message.BasicLineParser;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import java.io.IOException;
import java.net.Socket;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import qsbk.app.core.utils.LogUtils;
import qsbk.app.core.utils.websocket.HybiParser.HappyDataInputStream;

public class WebSocketClient {
    private static TrustManager[] k = new TrustManager[]{new a()};
    private URI a;
    private Listener b;
    private Socket c;
    private HandlerThread d;
    private Handler e;
    private List<BasicNameValuePair> f;
    private HybiParser g;
    private volatile int h;
    private ExecutorService i;
    private final Object j = new Object();

    public interface Listener {
        void onConnect();

        void onDisconnect(int i, String str);

        void onError(Exception exception);

        void onMessage(String str);

        void onMessage(byte[] bArr);
    }

    public static void setTrustManagers(TrustManager[] trustManagerArr) {
        k = trustManagerArr;
    }

    public WebSocketClient(URI uri, Listener listener, List<BasicNameValuePair> list) {
        this.a = uri;
        this.b = listener;
        this.f = list;
        this.g = new HybiParser(this);
        this.d = new HandlerThread("websocket-thread");
        this.d.start();
        this.e = new Handler(this.d.getLooper());
        this.i = Executors.newSingleThreadExecutor();
    }

    public Listener getListener() {
        return this.b;
    }

    public boolean isConnecting() {
        return this.h == 1;
    }

    public boolean isConnected() {
        return this.h == 2;
    }

    public synchronized void connect() {
        if (!(isConnected() || isConnecting())) {
            this.h = 1;
            if (this.i != null) {
                try {
                    this.i.execute(new b(this));
                } catch (Exception e) {
                    e.printStackTrace();
                } catch (OutOfMemoryError e2) {
                    e2.printStackTrace();
                    System.gc();
                }
            }
        }
    }

    private void a() {
        if (this.c != null) {
            try {
                this.c.close();
                this.c = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void a(int i, String str) {
        if (!isDisconnected()) {
            this.h = 0;
        }
        this.b.onDisconnect(i, str);
    }

    public String parseResponseError(HappyDataInputStream happyDataInputStream) throws Exception {
        String str = "";
        if (happyDataInputStream == null) {
            return str;
        }
        try {
            StringBuffer stringBuffer = new StringBuffer();
            String a;
            do {
                a = a(happyDataInputStream);
                if (a == null) {
                    break;
                }
                stringBuffer.append(a);
                if (a.endsWith(h.d)) {
                    break;
                }
            } while (!a.endsWith("</html>"));
            str = stringBuffer.toString().trim();
            if (str.startsWith("<html>")) {
                return "";
            }
            if (str.indexOf("{") != -1) {
                return str.substring(str.indexOf("{"));
            }
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public void destroy() {
        if (this.e != null) {
            this.e.post(new c(this));
        }
    }

    public synchronized void disconnect() {
        LogUtils.d("websocket", "websocket disconnecting");
        this.h = 3;
        if (this.c != null) {
            this.e.post(new d(this));
        }
    }

    public void send(String str) {
        a(this.g.frame(str));
    }

    public void send(byte[] bArr) {
        a(this.g.frame(bArr));
    }

    private StatusLine a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return BasicLineParser.parseStatusLine(str, new BasicLineParser());
    }

    private Header b(String str) {
        return BasicLineParser.parseHeader(str, new BasicLineParser());
    }

    private String a(HappyDataInputStream happyDataInputStream) throws IOException {
        int read = happyDataInputStream.read();
        if (read == -1) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder("");
        while (read != 10) {
            if (read != 13) {
                stringBuilder.append((char) read);
            }
            read = happyDataInputStream.read();
            if (read == -1) {
                return null;
            }
        }
        return stringBuilder.toString();
    }

    private String b() {
        byte[] bArr = new byte[16];
        for (int i = 0; i < 16; i++) {
            bArr[i] = (byte) ((int) (Math.random() * 256.0d));
        }
        return Base64.encodeToString(bArr, 0).trim();
    }

    private String c(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.update((str + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").getBytes());
            return Base64.encodeToString(instance.digest(), 0).trim();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    void a(byte[] bArr) {
        this.e.post(new e(this, bArr));
    }

    public boolean isDisconnected() {
        return this.h == 3;
    }

    private SSLSocketFactory c() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext instance = SSLContext.getInstance("TLS");
        instance.init(null, k, null);
        return instance.getSocketFactory();
    }
}
