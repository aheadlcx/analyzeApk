package cn.xiaochuan.push.huawei;

import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;
import cn.xiaochuan.push.a;
import cn.xiaochuan.push.d;
import com.huawei.hms.api.Api;
import com.huawei.hms.api.Api.ApiOptions.NoOptions;
import com.huawei.hms.api.ConnectionResult;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.api.HuaweiApiClient;
import com.huawei.hms.api.HuaweiApiClient.Builder;
import com.huawei.hms.api.HuaweiApiClient.ConnectionCallbacks;
import com.huawei.hms.api.HuaweiApiClient.OnConnectionFailedListener;
import com.huawei.hms.support.api.client.ResultCallback;
import com.huawei.hms.support.api.push.HuaweiPush;
import com.huawei.hms.support.api.push.HuaweiPushApi;
import com.huawei.hms.support.api.push.TokenResult;
import com.izuiyou.a.a.b;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.concurrent.Executors;

public class c implements d, ConnectionCallbacks, OnConnectionFailedListener {
    private Api<NoOptions> a;
    private HuaweiPushApi b;
    private Context c;
    private HuaweiApiClient d;

    private c() {
        this.a = HuaweiPush.PUSH_API;
        this.b = HuaweiPush.HuaweiPushApi;
        this.c = null;
        this.c = a.a().c();
        MiPushClient.unregisterPush(this.c);
        b();
    }

    private void b() {
        if (this.d != null && this.d.isConnecting()) {
            return;
        }
        if (this.d == null || !this.d.isConnected()) {
            this.d = new Builder(this.c).addApi(this.a).addConnectionCallbacks(this).addOnConnectionFailedListener(this).build();
            this.d.connect();
        }
    }

    public void a(long j) {
        b();
    }

    public void b(long j) {
        Log.e("Huawei", "Push unregister account:" + j);
    }

    public void a(int i) {
        if (this.c != null) {
            NotificationManager notificationManager = (NotificationManager) this.c.getSystemService("notification");
            if (notificationManager != null) {
                notificationManager.cancel(i);
            }
        }
    }

    public void onConnected() {
        Executors.newSingleThreadExecutor().submit(new Runnable(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.d != null) {
                    this.a.b.getToken(this.a.d).setResultCallback(new ResultCallback<TokenResult>(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public /* synthetic */ void onResult(Object obj) {
                            a((TokenResult) obj);
                        }

                        public void a(TokenResult tokenResult) {
                        }
                    });
                    this.a.b.enableReceiveNormalMsg(this.a.d, true);
                    this.a.b.enableReceiveNotifyMsg(this.a.d, true);
                    this.a.d.disconnect();
                }
            }
        });
    }

    public void onConnectionSuspended(int i) {
        b.c("onConnectionSuspended:" + i);
        if (this.d != null && !this.d.isConnected()) {
            this.d.connect();
        }
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
        b.c("onConnectionFailed:" + b.a(connectionResult.getErrorCode()));
    }

    public static d a() {
        return new c();
    }

    public static boolean a(Context context) {
        return HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context) != 0;
    }
}
