package qsbk.app.live.ui.mic;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.view.SurfaceView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import io.agora.rtc.RtcEngine;
import io.agora.rtc.video.VideoCanvas;
import io.agora.videoprp.AgoraYuvEnhancer;
import java.io.File;
import org.json.JSONObject;
import qsbk.app.core.utils.LogUtils;

public class MicWorkerThread extends Thread {
    private final Context a;
    private a b;
    private boolean c;
    private RtcEngine d;
    private AgoraYuvEnhancer e = null;
    private MicEngineConfig f;
    private final MicEngineEventHandler g;

    private static final class a extends Handler {
        private MicWorkerThread a;

        a(MicWorkerThread micWorkerThread) {
            this.a = micWorkerThread;
        }

        public void release() {
            this.a = null;
        }

        public void handleMessage(Message message) {
            if (this.a == null) {
                LogUtils.w("MicWorkerThread", "handler is already released! " + message.what);
                return;
            }
            Object[] objArr;
            switch (message.what) {
                case 4112:
                    this.a.exit();
                    return;
                case 8208:
                    String[] strArr = (String[]) message.obj;
                    this.a.joinChannel(strArr[0], message.arg1, strArr[1], message.arg2 == 1);
                    return;
                case 8209:
                    this.a.leaveChannel((String) message.obj);
                    return;
                case 8210:
                    objArr = (Object[]) message.obj;
                    this.a.configEngine(((Integer) objArr[0]).intValue(), ((Integer) objArr[1]).intValue());
                    return;
                case 8212:
                    objArr = (Object[]) message.obj;
                    this.a.preview(((Boolean) objArr[0]).booleanValue(), (SurfaceView) objArr[1], ((Integer) objArr[2]).intValue());
                    return;
                default:
                    return;
            }
        }
    }

    public final void waitForReady() {
        while (!this.c) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LogUtils.d("MicWorkerThread", "wait for " + MicWorkerThread.class.getSimpleName());
        }
    }

    public void run() {
        LogUtils.d("MicWorkerThread", "start to run");
        Looper.prepare();
        this.b = new a(this);
        a();
        this.c = true;
        Looper.loop();
    }

    public final void enablePreProcessor() {
        if (this.f.mClientRole == 1 && MicConstant.PRP_ENABLED && this.e == null) {
            this.e = new AgoraYuvEnhancer(this.a);
            this.e.SetLighteningFactor(MicConstant.PRP_DEFAULT_LIGHTNESS);
            this.e.SetSmoothnessFactor(MicConstant.PRP_DEFAULT_SMOOTHNESS);
            this.e.StartPreProcess();
        }
    }

    public final void setPreParameters(float f, float f2) {
        if (this.f.mClientRole == 1 && MicConstant.PRP_ENABLED) {
            if (this.e == null) {
                this.e = new AgoraYuvEnhancer(this.a);
            }
            this.e.StartPreProcess();
        }
        MicConstant.PRP_DEFAULT_LIGHTNESS = f;
        MicConstant.PRP_DEFAULT_SMOOTHNESS = f2;
        if (this.e != null) {
            this.e.SetLighteningFactor(MicConstant.PRP_DEFAULT_LIGHTNESS);
            this.e.SetSmoothnessFactor(MicConstant.PRP_DEFAULT_SMOOTHNESS);
        }
    }

    public final void disablePreProcessor() {
        if (this.e != null) {
            this.e.StopPreProcess();
            this.e = null;
        }
    }

    public final void joinChannel(String str, int i, String str2, boolean z) {
        int i2 = 1;
        if (Thread.currentThread() != this) {
            LogUtils.w("MicWorkerThread", "joinChannel() - worker thread asynchronously " + str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + i);
            Message message = new Message();
            message.what = 8208;
            message.obj = new String[]{str, str2};
            message.arg1 = i;
            if (!z) {
                i2 = 0;
            }
            message.arg2 = i2;
            this.b.sendMessage(message);
            return;
        }
        a();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("streamName", str2);
            jSONObject.put("owner", z);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.d.joinChannel(null, str, jSONObject.toString(), i);
        this.f.mChannel = str;
        enablePreProcessor();
        LogUtils.d("MicWorkerThread", "joinChannel " + str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + i);
    }

    public final void leaveChannel(String str) {
        if (Thread.currentThread() != this) {
            LogUtils.w("MicWorkerThread", "leaveChannel() - worker thread asynchronously " + str);
            Message message = new Message();
            message.what = 8209;
            message.obj = str;
            this.b.sendMessage(message);
            return;
        }
        if (this.d != null) {
            this.d.leaveChannel();
        }
        disablePreProcessor();
        int i = this.f.mClientRole;
        this.f.reset();
        LogUtils.d("MicWorkerThread", "leaveChannel " + str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + i);
    }

    public final MicEngineConfig getEngineConfig() {
        return this.f;
    }

    public final void configEngine(int i, int i2) {
        if (Thread.currentThread() != this) {
            LogUtils.w("MicWorkerThread", "configEngine() - worker thread asynchronously " + i + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + i2);
            Message message = new Message();
            message.what = 8210;
            message.obj = new Object[]{Integer.valueOf(i), Integer.valueOf(i2)};
            this.b.sendMessage(message);
            return;
        }
        a();
        this.f.mClientRole = i;
        this.f.mVideoProfile = i2;
        this.d.setVideoProfile(this.f.mVideoProfile, true);
        this.d.setClientRole(i, "");
        LogUtils.d("MicWorkerThread", "configEngine " + i + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.f.mVideoProfile);
    }

    public final void preview(boolean z, SurfaceView surfaceView, int i) {
        if (Thread.currentThread() != this) {
            LogUtils.w("MicWorkerThread", "preview() - worker thread asynchronously " + z + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + surfaceView + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + (((long) i) & 4294967295L));
            Message message = new Message();
            message.what = 8212;
            message.obj = new Object[]{Boolean.valueOf(z), surfaceView, Integer.valueOf(i)};
            this.b.sendMessage(message);
            return;
        }
        a();
        if (z) {
            this.d.setupLocalVideo(new VideoCanvas(surfaceView, 1, i));
            this.d.startPreview();
            return;
        }
        this.d.stopPreview();
    }

    public static String getDeviceID(Context context) {
        return Secure.getString(context.getContentResolver(), "android_id");
    }

    private RtcEngine a() {
        if (this.d == null) {
            if (TextUtils.isEmpty(MicConstant.APP_ID)) {
                throw new RuntimeException("NEED TO use your App ID, get your own ID at https://dashboard.agora.io/");
            }
            this.d = RtcEngine.create(this.a, MicConstant.APP_ID, this.g.a);
            this.d.setChannelProfile(1);
            this.d.enableVideo();
            this.d.setLogFile(Environment.getExternalStorageDirectory() + File.separator + this.a.getPackageName() + "/log/agora-rtc.log");
            this.d.enableDualStreamMode(MicConstant.DUAL_STREAM_MODE);
        }
        return this.d;
    }

    public MicEngineEventHandler eventHandler() {
        return this.g;
    }

    public RtcEngine getRtcEngine() {
        return this.d;
    }

    public final void exit() {
        if (Thread.currentThread() != this) {
            LogUtils.w("MicWorkerThread", "exit() - exit app thread asynchronously");
            this.b.sendEmptyMessage(4112);
            return;
        }
        this.c = false;
        this.e = null;
        LogUtils.d("MicWorkerThread", "exit() > start");
        Looper.myLooper().quit();
        this.b.release();
        LogUtils.d("MicWorkerThread", "exit() > end");
    }

    public MicWorkerThread(Context context) {
        this.a = context;
        this.f = new MicEngineConfig();
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.f.mUid = defaultSharedPreferences.getInt(MicConstant.PREF_PROPERTY_UID, 0);
        this.g = new MicEngineEventHandler(this.a, this.f);
    }
}
