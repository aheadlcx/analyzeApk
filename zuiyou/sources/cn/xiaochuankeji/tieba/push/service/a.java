package cn.xiaochuankeji.tieba.push.service;

import android.text.TextUtils;
import android.util.Pair;
import cn.htjyb.netlib.NetworkMonitor;
import cn.xiaochuan.push.c;
import cn.xiaochuankeji.tieba.background.AppController;
import cn.xiaochuankeji.tieba.push.proto.Push.Packet;
import cn.xiaochuankeji.tieba.push.proto.Push.Packet.Builder;
import cn.xiaochuankeji.tieba.push.proto.Push.Packet.PacketType;
import com.tencent.bugly.BuglyStrategy$a;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.Locale;

class a extends Thread {
    private static final byte[] b = new byte[]{(byte) 0};
    private static c f = null;
    private final int a = 2;
    private InputStream c;
    private OutputStream d;
    private Socket e;
    private int g;
    private boolean h;
    private d i;
    private Runnable j = new Runnable(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void run() {
            com.izuiyou.a.a.a.a("Daemon", (Object) "fast heartbeat ");
            c.a().a(this.a.k, 10000);
            this.a.a(a.l());
            c.a().b(this.a.j);
        }
    };
    private Runnable k = new Runnable(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void run() {
            com.izuiyou.a.a.a.a("Daemon", (Object) "re-create thread  ");
            this.a.g();
        }
    };

    a(d dVar) {
        super("DaemonThread");
        this.i = dVar;
    }

    public void run() {
        while (!this.h) {
            int i = this.g;
            this.g = i + 1;
            if (i > 0) {
                com.izuiyou.a.a.a.a("Daemon", String.format("sleep hold %s millisecond", new Object[]{Long.valueOf((long) Math.min(60000.0d, Math.pow(2.0d, (double) this.g) * 1000.0d))}));
                try {
                    sleep((long) Math.min(60000.0d, Math.pow(2.0d, (double) this.g) * 1000.0d));
                } catch (InterruptedException e) {
                    com.izuiyou.a.a.a.a("Daemon", "sleep interrupted" + e);
                }
            }
            try {
                if (NetworkMonitor.a()) {
                    if (f == null) {
                        com.izuiyou.a.a.a.a("Daemon", (Object) "start fetch route");
                        f = j();
                        com.izuiyou.a.a.a.a("Daemon", String.format("fetch route:%s", new Object[]{f}));
                        if (this.i != null) {
                            this.i.a(f);
                        }
                    }
                    e();
                    try {
                        this.e = a(f);
                        this.c = this.e.getInputStream();
                        this.d = this.e.getOutputStream();
                        i();
                        f();
                    } catch (Exception e2) {
                        com.izuiyou.a.a.a.b("Daemon", "socket connect exception:" + e2);
                        f = null;
                    }
                }
            } catch (Exception e22) {
                com.izuiyou.a.a.a.b("Daemon", "send begin.bug hava a exception " + e22);
            }
        }
    }

    private Socket a(c cVar) throws IOException {
        Socket socket = new Socket();
        socket.setReuseAddress(false);
        socket.setKeepAlive(true);
        socket.setTcpNoDelay(true);
        socket.setSoTimeout(300000);
        socket.setSoLinger(true, 0);
        socket.connect(new InetSocketAddress(cVar.b, cVar.c), BuglyStrategy$a.MAX_USERDATA_VALUE_LENGTH);
        return socket;
    }

    private void e() {
        if (this.e != null && this.e.isConnected()) {
            com.izuiyou.a.a.a.a("Daemon", (Object) "origin socket connected,force close");
            e.a(this.e);
        }
    }

    private synchronized void a(Packet packet) {
        try {
            byte[] toByteArray = packet.toByteArray();
            this.d.write(e.a(e.a(e.a(toByteArray.length), toByteArray), b));
            this.d.flush();
            com.izuiyou.a.a.a.b("Daemon", "finish write data->packet:" + packet.getType().name());
        } catch (Exception e) {
            e.printStackTrace();
            com.izuiyou.a.a.a.b("Daemon", "write data with error:" + e);
            e();
        }
    }

    private void f() throws IOException {
        byte[] bArr = new byte[0];
        byte[] bArr2 = new byte[1024];
        loop0:
        while (!this.h) {
            int read = this.c.read(bArr2);
            if (read < 0) {
                com.izuiyou.a.a.a.b("Daemon", "read data break,count < 0");
                break;
            }
            byte[] a = e.a(bArr, bArr2, read);
            while (a.length > 0) {
                Pair a2 = e.a(a);
                if (((Long) a2.first).longValue() < 0) {
                    com.izuiyou.a.a.a.b("Daemon", "read data break,variant int.first < 0");
                    break;
                }
                int intValue = (((Integer) a2.second).intValue() + ((Long) a2.first).intValue()) + 1;
                if (a.length < intValue) {
                    com.izuiyou.a.a.a.b("Daemon", "read data break,data.length:" + a.length + "  total:" + intValue);
                    break;
                } else if (a[intValue - 1] != (byte) 0) {
                    com.izuiyou.a.a.a.b("Daemon", String.format(Locale.ENGLISH, "read data break,data[%d - 1]=%d", new Object[]{Integer.valueOf(intValue - 1), Byte.valueOf(a[intValue - 1])}));
                    break loop0;
                } else {
                    Packet parseFrom = Packet.parseFrom(Arrays.copyOfRange(a, ((Integer) a2.second).intValue(), intValue - 1));
                    if (parseFrom != null) {
                        b(parseFrom);
                    } else {
                        com.izuiyou.a.a.a.b("Daemon", "read data,packet is null");
                    }
                    a = Arrays.copyOfRange(a, intValue, a.length);
                }
            }
            bArr = a;
        }
        e.a(this.e);
        com.izuiyou.a.a.a.a("Daemon", (Object) "finish read data");
    }

    private void b(Packet packet) {
        h();
        String name = packet.getType().name();
        switch (packet.getType()) {
            case SYNACK:
                String clientId = packet.getClientId();
                cn.xiaochuankeji.tieba.push.d.c.a(clientId);
                com.izuiyou.a.a.a.c("Daemon", "read data: sync ack->packet::" + name + " client:" + clientId);
                return;
            case ERR:
                com.izuiyou.a.a.a.b("Daemon", "read data: error->packet:" + name);
                g();
                return;
            case REROUTE:
                f = null;
                com.izuiyou.a.a.a.b("Daemon", "read data: re-route->packet:" + name);
                e();
                return;
            case HEART:
                this.g = 0;
                com.izuiyou.a.a.a.b("Daemon", "read data: heart->packet:" + name);
                return;
            case MESSAGE:
                this.g = 0;
                long msgId = packet.getMsgId();
                int bizType = packet.getBizType();
                com.izuiyou.a.a.a.c("Daemon", "read data: business->packet:" + name);
                byte[] toByteArray = packet.getBizData().toByteArray();
                if (packet.getZipType() == 1) {
                    toByteArray = e.b(toByteArray);
                }
                cn.xiaochuankeji.tieba.push.d.c.a(packet.getMsgId(), bizType, toByteArray);
                a(a(msgId, bizType));
                return;
            case ACK:
                com.izuiyou.a.a.a.b("Daemon", "read data: ack->packet:" + name);
                return;
            case SYN:
                com.izuiyou.a.a.a.b("Daemon", "read data: syn->packet:" + name);
                return;
            case ECHO:
                com.izuiyou.a.a.a.b("Daemon", "read data: echo->packet:" + name);
                return;
            case RESET:
                com.izuiyou.a.a.a.b("Daemon", "read data: reset->packet:" + name);
                return;
            default:
                return;
        }
    }

    private void g() {
        if (this.i != null) {
            this.i.a();
        }
    }

    void a() {
        h();
        c.a().a(this.j, 10000);
        a(l());
    }

    private void h() {
        c.a().b(this.j);
        c.a().b(this.k);
    }

    void b() {
        this.g = 0;
        this.h = true;
        e();
    }

    private void i() {
        a(k());
    }

    private c j() throws Exception {
        return new c(e.a("https://api.izuiyou.com/s/route/route"));
    }

    private static Packet k() {
        Builder clientType = Packet.newBuilder().setType(PacketType.SYN).setNetType(e.a()).setInstallId(AppController.instance().deviceID()).setAppId("zuiyou").setClientVer("4.1.8.9").setClientType("android");
        Object a = cn.xiaochuankeji.tieba.background.a.g().a();
        if (!TextUtils.isEmpty(a)) {
            clientType.setAuth(a);
        }
        return clientType.build();
    }

    private static Packet a(long j, int i) {
        return Packet.newBuilder().setType(PacketType.ACK).setAckMsgId(j).setBizType(i).build();
    }

    private static Packet l() {
        return Packet.newBuilder().setType(PacketType.HEART).build();
    }

    void c() {
        f = null;
        i();
    }
}
