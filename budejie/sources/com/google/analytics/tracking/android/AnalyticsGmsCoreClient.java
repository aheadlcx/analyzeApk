package com.google.analytics.tracking.android;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.analytics.internal.Command;
import com.google.android.gms.analytics.internal.IAnalyticsService;
import com.google.android.gms.analytics.internal.IAnalyticsService.Stub;
import java.util.List;
import java.util.Map;

class AnalyticsGmsCoreClient implements AnalyticsClient {
    private static final int BIND_ADJUST_WITH_ACTIVITY = 128;
    public static final int BIND_FAILED = 1;
    public static final String KEY_APP_PACKAGE_NAME = "app_package_name";
    public static final int REMOTE_EXECUTION_FAILED = 2;
    static final String SERVICE_ACTION = "com.google.android.gms.analytics.service.START";
    private static final String SERVICE_DESCRIPTOR = "com.google.android.gms.analytics.internal.IAnalyticsService";
    private ServiceConnection mConnection;
    private Context mContext;
    private OnConnectedListener mOnConnectedListener;
    private OnConnectionFailedListener mOnConnectionFailedListener;
    private IAnalyticsService mService;

    final class AnalyticsServiceConnection implements ServiceConnection {
        AnalyticsServiceConnection() {
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.v("service connected, binder: " + iBinder);
            try {
                if (AnalyticsGmsCoreClient.SERVICE_DESCRIPTOR.equals(iBinder.getInterfaceDescriptor())) {
                    Log.v("bound to service");
                    AnalyticsGmsCoreClient.this.mService = Stub.asInterface(iBinder);
                    AnalyticsGmsCoreClient.this.onServiceBound();
                    return;
                }
            } catch (RemoteException e) {
            }
            AnalyticsGmsCoreClient.this.mContext.unbindService(this);
            AnalyticsGmsCoreClient.this.mConnection = null;
            AnalyticsGmsCoreClient.this.mOnConnectionFailedListener.onConnectionFailed(2, null);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            Log.v("service disconnected: " + componentName);
            AnalyticsGmsCoreClient.this.mConnection = null;
            AnalyticsGmsCoreClient.this.mOnConnectedListener.onDisconnected();
        }
    }

    public interface OnConnectedListener {
        void onConnected();

        void onDisconnected();
    }

    public interface OnConnectionFailedListener {
        void onConnectionFailed(int i, Intent intent);
    }

    public AnalyticsGmsCoreClient(Context context, OnConnectedListener onConnectedListener, OnConnectionFailedListener onConnectionFailedListener) {
        this.mContext = context;
        if (onConnectedListener == null) {
            throw new IllegalArgumentException("onConnectedListener cannot be null");
        }
        this.mOnConnectedListener = onConnectedListener;
        if (onConnectionFailedListener == null) {
            throw new IllegalArgumentException("onConnectionFailedListener cannot be null");
        }
        this.mOnConnectionFailedListener = onConnectionFailedListener;
    }

    public void connect() {
        Intent intent = new Intent(SERVICE_ACTION);
        intent.setComponent(new ComponentName("com.google.android.gms", "com.google.android.gms.analytics.service.AnalyticsService"));
        intent.putExtra(KEY_APP_PACKAGE_NAME, this.mContext.getPackageName());
        if (this.mConnection != null) {
            Log.e("Calling connect() while still connected, missing disconnect().");
            return;
        }
        this.mConnection = new AnalyticsServiceConnection();
        boolean bindService = this.mContext.bindService(intent, this.mConnection, 129);
        Log.v("connect: bindService returned " + bindService + " for " + intent);
        if (!bindService) {
            this.mConnection = null;
            this.mOnConnectionFailedListener.onConnectionFailed(1, null);
        }
    }

    public void disconnect() {
        this.mService = null;
        if (this.mConnection != null) {
            try {
                this.mContext.unbindService(this.mConnection);
            } catch (IllegalStateException e) {
            } catch (IllegalArgumentException e2) {
            }
            this.mConnection = null;
            this.mOnConnectedListener.onDisconnected();
        }
    }

    public void sendHit(Map<String, String> map, long j, String str, List<Command> list) {
        try {
            getService().sendHit(map, j, str, list);
        } catch (RemoteException e) {
            Log.e("sendHit failed: " + e);
        }
    }

    public void clearHits() {
        try {
            getService().clearHits();
        } catch (RemoteException e) {
            Log.e("clear hits failed: " + e);
        }
    }

    private IAnalyticsService getService() {
        checkConnected();
        return this.mService;
    }

    protected void checkConnected() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    public boolean isConnected() {
        return this.mService != null;
    }

    private void onServiceBound() {
        onConnectionSuccess();
    }

    private void onConnectionSuccess() {
        this.mOnConnectedListener.onConnected();
    }
}
