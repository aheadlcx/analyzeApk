package qsbk.app.core.utils.websocket;

import android.os.Handler;
import android.text.TextUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;
import org.msgpack.jackson.dataformat.MessagePackFactory;
import qsbk.app.core.ui.base.BaseActivity;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.LogUtils;

public abstract class WebSocketHandler {
    protected static int a = 10000;
    protected ObjectMapper b = new ObjectMapper(new MessagePackFactory());
    protected LinkedBlockingDeque<Object> c = new LinkedBlockingDeque();
    protected LinkedBlockingDeque<Object> d = new LinkedBlockingDeque();
    protected List<Object> e = new ArrayList();
    private WebSocketClient f;
    private String g;
    private BaseActivity h;
    private Handler i;
    private OnMessageListener j;
    private Runnable k = new k(this);

    public static abstract class OnMessageListener {
        public abstract void onConnect();

        public abstract void onDisconnect(int i, String str);

        public abstract void onError(Exception exception);

        public abstract void onReceiveMessage(Object obj);
    }

    protected abstract Object a(byte[] bArr) throws IOException;

    protected abstract String a();

    protected abstract Object b();

    public WebSocketHandler() {
        this.b.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public void attach(BaseActivity baseActivity) {
        LogUtils.d("websocket", a() + " websocket attach");
        this.h = baseActivity;
        this.i = this.h.mHandler;
        if (isConnected()) {
            a(this.k);
        }
    }

    public boolean isConnected() {
        return this.f != null && this.f.isConnected();
    }

    public boolean isConnecting() {
        return this.f != null && this.f.isConnecting();
    }

    public boolean isDisconnected() {
        return this.f != null && this.f.isDisconnected();
    }

    public void disconnect() {
        LogUtils.d("websocket", a() + " websocket disconnect");
        if (this.i != null) {
            this.i.removeCallbacks(this.k);
        }
        if (this.f != null) {
            this.f.disconnect();
        }
    }

    protected void connect(String str) {
        this.g = str;
        c();
    }

    private synchronized void c() {
        if (!(TextUtils.isEmpty(this.g) || isConnected() || isConnecting())) {
            LogUtils.d("websocket", a() + " websocket status: try connect to room");
            if (this.f == null) {
                this.f = new WebSocketClient(URI.create(this.g), new f(this), null);
            }
            this.f.connect();
        }
    }

    private void c(Object obj) {
        if (obj != null) {
            Object obj2 = null;
            try {
                if (obj instanceof String) {
                    a("received raw", obj);
                    obj2 = a((String) obj);
                } else if (obj instanceof byte[]) {
                    a("received raw", (byte[]) obj);
                    obj2 = a((byte[]) obj);
                    a("received", obj2);
                }
                a(obj2);
            } catch (Throwable e) {
                LogUtils.e("websocket", "parse msg error", e);
            }
        }
    }

    protected Object a(String str) throws IOException {
        return null;
    }

    protected void a(Object obj) {
        if (obj != null) {
            a(new j(this, obj));
        }
    }

    public void sendMessage(Object obj) {
        if (obj != null) {
            boolean isConnected = isConnected();
            if (isConnected) {
                a("sending", obj);
                byte[] d = d(obj);
                this.f.send(d);
                a("sending raw", new String(d));
                this.d.add(obj);
            } else if (!b(obj)) {
                this.c.add(obj);
            }
            int i = !this.c.isEmpty() ? isConnected ? 100 : 1200 : a;
            b(this.k);
            a(this.k, i);
        }
    }

    protected boolean b(Object obj) {
        return true;
    }

    private byte[] d(Object obj) {
        byte[] bArr = null;
        try {
            bArr = this.b.writeValueAsBytes(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return bArr;
    }

    protected void a(String str, byte[] bArr) throws IOException {
        if (LogUtils.LOGGABLE) {
            a(str, ((Map) this.b.readValue(bArr, Map.class)).toString());
        }
    }

    protected void a(String str, Object obj) {
        a(str, AppUtils.toJson(obj));
    }

    protected void a(String str, String str2) {
        LogUtils.d("websocket", a() + " websocket " + str + " message: " + str2);
    }

    private void a(Runnable runnable) {
        a(runnable, 0);
    }

    private void a(Runnable runnable, int i) {
        if (this.i != null) {
            this.i.postDelayed(runnable, (long) i);
        }
    }

    private void b(Runnable runnable) {
        if (this.i != null) {
            this.i.removeCallbacks(runnable);
        }
    }

    public void detach() {
        LogUtils.d("websocket", a() + " websocket detach");
        if (this.f != null) {
            this.f.destroy();
            this.f = null;
        }
        this.c.clear();
        this.d.clear();
        this.e.clear();
        this.i = null;
        this.h = null;
        this.j = null;
    }

    public void setOnMessageListener(OnMessageListener onMessageListener) {
        this.j = onMessageListener;
        if (this.j != null && !this.e.isEmpty()) {
            a(new l(this));
        }
    }

    public LinkedBlockingDeque<Object> getSendedQueue() {
        return this.d;
    }
}
