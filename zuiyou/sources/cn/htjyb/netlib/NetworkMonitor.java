package cn.htjyb.netlib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences.Editor;
import com.izuiyou.a.a.b;
import java.util.ArrayList;
import java.util.Iterator;

public class NetworkMonitor extends BroadcastReceiver {
    public static ArrayList<a> a = new ArrayList();
    private static boolean b;
    private static boolean c = false;
    private static Context d;
    private final String e = "common_lib";
    private final String f = "network_state_key";
    private final String g = "network_conn_key";

    public interface a {
        void a(int i);
    }

    public static boolean a() {
        return b;
    }

    public static void a(Context context) {
        d = context;
        if (!c) {
            BroadcastReceiver networkMonitor = new NetworkMonitor();
            b = h.a(context);
            context.registerReceiver(networkMonitor, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            c = true;
        }
    }

    public void onReceive(Context context, Intent intent) {
        b = h.a(context);
        boolean b = b();
        a(b);
        Iterator it;
        if (b) {
            b.c("网络连接状态,last:" + b + " 当前:" + b);
            if (b) {
                String c = h.c(context);
                if (c == null) {
                    b.e("当前网络类型:没获取到,直接返回");
                    return;
                }
                String str = c.equals("wifi") ? c : "nowifi";
                c = c();
                b.c("当前网路类型:" + str + "  之前一次网络类型:" + c);
                if (!str.equals(c)) {
                    b.c("网络类型切换,发送dns请求");
                    Iterator it2 = a.iterator();
                    while (it2.hasNext()) {
                        ((a) it2.next()).a(1);
                    }
                    a(str);
                    return;
                }
                return;
            }
            b.c("连接上了，发送dns请求");
            it = a.iterator();
            while (it.hasNext()) {
                ((a) it.next()).a(1);
            }
            return;
        }
        b.c("网络连接断开");
        it = a.iterator();
        while (it.hasNext()) {
            ((a) it.next()).a(0);
        }
    }

    private boolean b() {
        if (d.getSharedPreferences("common_lib", 0).getInt("network_conn_key", 0) == 1) {
            return true;
        }
        return false;
    }

    private void a(boolean z) {
        int i = 0;
        Editor edit = d.getSharedPreferences("common_lib", 0).edit();
        String str = "network_conn_key";
        if (z) {
            i = 1;
        }
        edit.putInt(str, i).apply();
    }

    private String c() {
        return d.getSharedPreferences("common_lib", 0).getString("network_state_key", null);
    }

    private void a(String str) {
        d.getSharedPreferences("common_lib", 0).edit().putString("network_state_key", str).apply();
    }

    public static void a(a aVar) {
        a.add(aVar);
    }
}
