package com.huawei.hms.api;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.hms.api.Api.ApiOptions;
import com.huawei.hms.api.HuaweiApiClient.ConnectionCallbacks;
import com.huawei.hms.api.HuaweiApiClient.OnConnectionFailedListener;
import com.huawei.hms.api.internal.IPCTransport;
import com.huawei.hms.c.g;
import com.huawei.hms.core.aidl.RequestHeader;
import com.huawei.hms.core.aidl.e;
import com.huawei.hms.core.aidl.f;
import com.huawei.hms.support.api.ResolveResult;
import com.huawei.hms.support.api.client.ApiClient;
import com.huawei.hms.support.api.client.BundleResult;
import com.huawei.hms.support.api.client.ResultCallback;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.client.SubAppInfo;
import com.huawei.hms.support.api.entity.auth.PermissionInfo;
import com.huawei.hms.support.api.entity.auth.Scope;
import com.huawei.hms.support.api.entity.core.CommonCode.ErrorCode;
import com.huawei.hms.support.api.entity.core.ConnectInfo;
import com.huawei.hms.support.api.entity.core.ConnectResp;
import com.huawei.hms.support.api.entity.core.DisconnectInfo;
import com.huawei.hms.support.api.entity.core.DisconnectResp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class HuaweiApiClientImpl extends HuaweiApiClient implements ServiceConnection {
    private final Context a;
    private String b;
    private final String c;
    private volatile e d;
    private String e;
    private AtomicInteger f = new AtomicInteger(1);
    private List<Scope> g;
    private List<PermissionInfo> h;
    private Map<Api<?>, ApiOptions> i;
    private SubAppInfo j;
    private ConnectionCallbacks k;
    private OnConnectionFailedListener l;
    private Handler m = null;

    private class a implements ResultCallback<ResolveResult<ConnectResp>> {
        final /* synthetic */ HuaweiApiClientImpl a;

        private a(HuaweiApiClientImpl huaweiApiClientImpl) {
            this.a = huaweiApiClientImpl;
        }

        public /* synthetic */ void onResult(Object obj) {
            a((ResolveResult) obj);
        }

        public void a(ResolveResult<ConnectResp> resolveResult) {
            new Handler(Looper.getMainLooper()).post(new d(this, resolveResult));
        }
    }

    private class b implements ResultCallback<ResolveResult<DisconnectResp>> {
        final /* synthetic */ HuaweiApiClientImpl a;

        private b(HuaweiApiClientImpl huaweiApiClientImpl) {
            this.a = huaweiApiClientImpl;
        }

        public /* synthetic */ void onResult(Object obj) {
            a((ResolveResult) obj);
        }

        public void a(ResolveResult<DisconnectResp> resolveResult) {
            new Handler(Looper.getMainLooper()).post(new e(this, resolveResult));
        }
    }

    public HuaweiApiClientImpl(Context context) {
        this.a = context;
        this.c = g.a(context);
        this.b = this.c;
    }

    public Context getContext() {
        return this.a;
    }

    public String getPackageName() {
        return this.a.getPackageName();
    }

    public String getAppID() {
        return this.b;
    }

    public String getTransportName() {
        return IPCTransport.class.getName();
    }

    public final SubAppInfo getSubAppInfo() {
        return this.j;
    }

    public List<Scope> getScopes() {
        return this.g;
    }

    public List<PermissionInfo> getPermissionInfos() {
        return this.h;
    }

    public Map<Api<?>, ApiOptions> getApiMap() {
        return this.i;
    }

    public e getService() {
        return this.d;
    }

    public String getSessionId() {
        return this.e;
    }

    public void connect() {
        com.huawei.hms.support.log.a.d("HuaweiApiClientImpl", "====== HMSSDK version: 20502300 ======");
        int i = this.f.get();
        com.huawei.hms.support.log.a.b("HuaweiApiClientImpl", "Enter connect, Connection Status: " + i);
        if (i != 3 && i != 5 && i != 2 && i != 4) {
            this.b = TextUtils.isEmpty(this.c) ? g.a(this.a) : this.c;
            i = com.huawei.hms.api.internal.e.a(this.a);
            com.huawei.hms.support.log.a.b("HuaweiApiClientImpl", "In connect, isHuaweiMobileServicesAvailable result: " + i);
            if (i == 0) {
                a(5);
                if (a()) {
                    b();
                    return;
                }
                a(1);
                com.huawei.hms.support.log.a.d("HuaweiApiClientImpl", "In connect, bind core service fail");
                if (this.l != null) {
                    this.l.onConnectionFailed(new ConnectionResult(6));
                }
            } else if (this.l != null) {
                this.l.onConnectionFailed(new ConnectionResult(i));
            }
        }
    }

    private void a(int i) {
        this.f.set(i);
    }

    private boolean a() {
        Intent intent = new Intent(HuaweiApiAvailability.SERVICES_ACTION);
        intent.setPackage(HuaweiApiAvailability.SERVICES_PACKAGE);
        return this.a.bindService(intent, this, 1);
    }

    private void b() {
        if (this.m != null) {
            this.m.removeMessages(2);
        } else {
            this.m = new Handler(Looper.getMainLooper(), new b(this));
        }
        this.m.sendEmptyMessageDelayed(2, 3000);
    }

    private void c() {
        if (this.m != null) {
            this.m.removeMessages(2);
            this.m = null;
        }
    }

    public void disconnect() {
        int i = this.f.get();
        com.huawei.hms.support.log.a.b("HuaweiApiClientImpl", "Enter disconnect, Connection Status: " + i);
        switch (i) {
            case 2:
                a(4);
                return;
            case 3:
                a(4);
                d();
                return;
            case 5:
                c();
                a(4);
                return;
            default:
                return;
        }
    }

    public boolean isConnected() {
        return this.f.get() == 3;
    }

    public boolean isConnecting() {
        int i = this.f.get();
        return i == 2 || i == 5;
    }

    public void setApiMap(Map<Api<?>, ApiOptions> map) {
        this.i = map;
    }

    public void setScopes(List<Scope> list) {
        this.g = list;
    }

    public void setPermissionInfos(List<PermissionInfo> list) {
        this.h = list;
    }

    public boolean setSubAppInfo(SubAppInfo subAppInfo) {
        if (subAppInfo == null) {
            return false;
        }
        Object subAppID = subAppInfo.getSubAppID();
        if (TextUtils.isEmpty(subAppID)) {
            return false;
        }
        if (subAppID.equals(TextUtils.isEmpty(this.c) ? g.a(this.a) : this.c)) {
            return false;
        }
        this.j = new SubAppInfo(subAppInfo);
        return true;
    }

    public void setConnectionCallbacks(ConnectionCallbacks connectionCallbacks) {
        this.k = connectionCallbacks;
    }

    public void setConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener) {
        this.l = onConnectionFailedListener;
    }

    private void d() {
        com.huawei.hms.support.api.a.a.a((ApiClient) this, e()).setResultCallback(new b());
    }

    private DisconnectInfo e() {
        List arrayList = new ArrayList();
        if (this.i != null) {
            for (Api apiName : this.i.keySet()) {
                arrayList.add(apiName.getApiName());
            }
        }
        return new DisconnectInfo(this.g, arrayList);
    }

    private void a(ResolveResult<DisconnectResp> resolveResult) {
        com.huawei.hms.support.log.a.b("HuaweiApiClientImpl", "Enter onDisconnectionResult, disconnect from server result: " + resolveResult.getStatus().getStatusCode());
        h();
        a(1);
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        com.huawei.hms.support.log.a.b("HuaweiApiClientImpl", "Enter onServiceConnected.");
        c();
        this.d = com.huawei.hms.core.aidl.e.a.a(iBinder);
        if (this.d == null) {
            com.huawei.hms.support.log.a.d("HuaweiApiClientImpl", "In onServiceConnected, mCoreService must not be null.");
            h();
            a(1);
            if (this.l != null) {
                this.l.onConnectionFailed(new ConnectionResult(10));
            }
        } else if (this.f.get() == 5) {
            a(2);
            f();
        } else if (this.f.get() != 3) {
            h();
        }
    }

    private void f() {
        com.huawei.hms.support.log.a.b("HuaweiApiClientImpl", "Enter sendConnectApiServceRequest.");
        com.huawei.hms.support.api.a.a.a((ApiClient) this, g()).setResultCallback(new a());
    }

    private ConnectInfo g() {
        String d = new com.huawei.hms.c.e(this.a).d(this.a.getPackageName());
        String str = d == null ? "" : d;
        List arrayList = new ArrayList();
        if (this.i != null) {
            for (Api apiName : this.i.keySet()) {
                arrayList.add(apiName.getApiName());
            }
        }
        return new ConnectInfo(arrayList, this.g, str, this.j == null ? null : this.j.getSubAppID());
    }

    private void b(ResolveResult<ConnectResp> resolveResult) {
        Object obj;
        ConnectResp connectResp = (ConnectResp) resolveResult.getValue();
        if (connectResp != null) {
            this.e = connectResp.sessionId;
        }
        if (this.j == null) {
            obj = null;
        } else {
            obj = this.j.getSubAppID();
        }
        if (!TextUtils.isEmpty(obj)) {
            this.b = obj;
        }
        int statusCode = resolveResult.getStatus().getStatusCode();
        com.huawei.hms.support.log.a.b("HuaweiApiClientImpl", "Enter onConnectionResult, connect to server result: " + statusCode);
        if (Status.SUCCESS.equals(resolveResult.getStatus())) {
            if (resolveResult.getValue() != null) {
                com.huawei.hms.api.internal.g.a().a(((ConnectResp) resolveResult.getValue()).protocolVersion);
            }
            a(3);
            if (this.k != null) {
                this.k.onConnected();
            }
        } else if (resolveResult.getStatus() == null || resolveResult.getStatus().getStatusCode() != 1001) {
            h();
            a(1);
            if (this.l != null) {
                this.l.onConnectionFailed(new ConnectionResult(statusCode));
            }
        } else {
            h();
            a(1);
            if (this.k != null) {
                this.k.onConnectionSuspended(3);
            }
        }
    }

    private void h() {
        g.a(this.a, (ServiceConnection) this);
    }

    public void onServiceDisconnected(ComponentName componentName) {
        com.huawei.hms.support.log.a.b("HuaweiApiClientImpl", "Enter onServiceDisconnected.");
        this.d = null;
        a(1);
        if (this.k != null) {
            this.k.onConnectionSuspended(1);
        }
    }

    public int asyncRequest(Bundle bundle, String str, int i, ResultCallback<BundleResult> resultCallback) {
        if (resultCallback == null || str == null || bundle == null) {
            return ErrorCode.ARGUMENTS_INVALID;
        }
        if (!isConnected()) {
            return ErrorCode.CLIENT_API_INVALID;
        }
        com.huawei.hms.core.aidl.b bVar = new com.huawei.hms.core.aidl.b(str, i);
        f a = com.huawei.hms.core.aidl.a.a(bVar.c());
        bVar.a(bundle);
        bVar.b = a.a(new RequestHeader(getAppID(), getPackageName(), 20502300, getSessionId()), new Bundle());
        try {
            getService().a(bVar, new c(this, resultCallback));
            return 0;
        } catch (RemoteException e) {
            return ErrorCode.INTERNAL_ERROR;
        }
    }
}
