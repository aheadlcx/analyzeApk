package cn.xiaochuankeji.tieba.background.screen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import cn.xiaochuankeji.tieba.background.screen.Observer.ScreenStatus;
import java.util.LinkedList;
import java.util.List;

public class a {
    private b a;
    private List<Observer> b;

    private static class a {
        private static a a = new a();
    }

    private class b extends BroadcastReceiver {
        final /* synthetic */ a a;

        private b(a aVar) {
            this.a = aVar;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null) {
                String action = intent.getAction();
                Object obj = -1;
                switch (action.hashCode()) {
                    case -2128145023:
                        if (action.equals("android.intent.action.SCREEN_OFF")) {
                            obj = null;
                            break;
                        }
                        break;
                    case -1454123155:
                        if (action.equals("android.intent.action.SCREEN_ON")) {
                            obj = 1;
                            break;
                        }
                        break;
                    case 823795052:
                        if (action.equals("android.intent.action.USER_PRESENT")) {
                            obj = 2;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case null:
                        this.a.a(ScreenStatus.SCREEN_OFF);
                        return;
                    case 1:
                        this.a.a(ScreenStatus.SCREEN_ON);
                        return;
                    case 2:
                        this.a.a(ScreenStatus.USER_PRESENT);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public static a a() {
        return a.a;
    }

    private a() {
        this.a = new b();
        this.b = new LinkedList();
    }

    private void a(ScreenStatus screenStatus) {
        if (this.b != null && !this.b.isEmpty()) {
            for (Observer a : this.b) {
                a.a(screenStatus);
            }
        }
    }

    private IntentFilter b() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        return intentFilter;
    }

    public void a(Observer observer, Context context) {
        context.registerReceiver(this.a, b());
        this.b.add(observer);
    }

    public void b(Observer observer, Context context) {
        context.unregisterReceiver(this.a);
        this.b.remove(observer);
    }
}
