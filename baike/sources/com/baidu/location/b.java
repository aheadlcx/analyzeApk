package com.baidu.location;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

class b implements ServiceConnection {
    final /* synthetic */ LocationClient a;

    b(LocationClient locationClient) {
        this.a = locationClient;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.a.mServer = new Messenger(iBinder);
        if (this.a.mServer != null) {
            this.a.mIsStarted = true;
            Log.d("baidu_location_client", "baidu location connected ...");
            if (this.a.isStop) {
                this.a.mHandler.obtainMessage(2).sendToTarget();
                return;
            }
            try {
                Message obtain = Message.obtain(null, 11);
                obtain.replyTo = this.a.mMessenger;
                obtain.setData(this.a.getOptionBundle());
                this.a.mServer.send(obtain);
                this.a.mIsStarted = true;
                if (this.a.mOption != null) {
                    if (this.a.firstConnected.booleanValue()) {
                        this.a.mHandler.obtainMessage(4).sendToTarget();
                    } else {
                        this.a.mHandler.obtainMessage(4).sendToTarget();
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        this.a.mServer = null;
        this.a.mIsStarted = false;
    }
}
