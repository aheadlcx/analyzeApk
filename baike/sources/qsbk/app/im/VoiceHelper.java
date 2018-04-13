package qsbk.app.im;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import java.io.File;
import java.io.IOException;
import qsbk.app.im.voice.VoiceManager;
import qsbk.app.utils.DeviceUtils;

public class VoiceHelper {
    private static final String c = VoiceHelper.class.getSimpleName();
    private static final float[] d = new float[]{0.12f, 0.15f, 0.18f};
    File a;
    VoiceListener b;
    private final Context e;
    private final Handler f = new Handler(Looper.myLooper());
    private int g = 0;
    private MediaRecorder h;
    private String i;
    private int j = 0;
    private long k = 0;
    private long l;
    private boolean m;
    private boolean n;
    private boolean o = false;
    private final Runnable p = new jn(this);
    private int q = 1;
    private int r = 100;
    private Runnable s = new jo(this);

    public interface VoiceListener {
        void onRecordComplete(File file, long j);

        void onRecordError(String str);

        void onRecordStart();

        void onRecording(double d);
    }

    public VoiceHelper(Context context, VoiceListener voiceListener) {
        this.e = context;
        this.b = voiceListener;
        this.m = DeviceUtils.hasPermission(context, "android.permission.RECORD_AUDIO");
        a();
    }

    private void a() {
    }

    public void onResume() {
        this.m = DeviceUtils.hasPermission(this.e, "android.permission.RECORD_AUDIO");
    }

    public void startRecord() {
        this.o = DeviceUtils.hasPermission(this.e, "android.permission.RECORD_AUDIO");
        this.l = SystemClock.uptimeMillis();
        this.i = this.l + ".up";
        this.a = new File(VoiceManager.getDir(), this.i);
        if (!this.a.exists()) {
            try {
                this.a.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.k = this.a.exists() ? this.a.length() : 0;
        Object obj = null;
        try {
            this.h = new MediaRecorder();
            this.h.setAudioSource(1);
            this.h.setOutputFormat(3);
            this.h.setAudioEncoder(1);
            this.h.setOutputFile(this.a.getPath());
            this.h.prepare();
            try {
                this.h.start();
            } catch (RuntimeException e2) {
                this.o = false;
                e2.printStackTrace();
            }
            this.j = 0;
            a(1);
        } catch (IllegalStateException e3) {
            obj = "录音失败，请检查是否开启录音权限";
        } catch (Exception e4) {
            obj = "录音失败，请检查是否开启录音权限";
        } catch (Throwable th) {
            obj = "录音失败，请检查是否开启录音权限";
        }
        if (this.b != null) {
            if (TextUtils.isEmpty(obj)) {
                this.b.onRecordStart();
            } else {
                this.b.onRecordError(obj);
            }
        }
        this.f.postDelayed(this.p, 1000);
        c();
    }

    public void stopRecord() {
        Object obj;
        long uptimeMillis = SystemClock.uptimeMillis() - this.l;
        try {
            b();
            obj = null;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            if (this.a.exists()) {
                this.a.deleteOnExit();
            }
            obj = "录音失败";
        }
        if (uptimeMillis < 1) {
            obj = "录音时间过短";
        } else if (!(this.o && this.a.exists() && (this.j < 1 || this.a.length() != this.k))) {
            obj = "请检查录音权限是否禁用";
        }
        if (TextUtils.isEmpty(obj)) {
            if (this.g == 1) {
                a(2);
                if (this.b != null) {
                    this.b.onRecordComplete(this.a, uptimeMillis);
                }
                this.g = 0;
            }
        } else if (this.b != null) {
            this.b.onRecordError(obj);
        }
        this.f.removeCallbacksAndMessages(null);
    }

    private void b() {
        if (this.h != null) {
            this.h.setOnErrorListener(null);
            this.h.reset();
            this.h.release();
            this.h = null;
        }
    }

    protected void a(int i) {
        if (i != this.g) {
            this.g = i;
            if (i != 0) {
            }
        }
    }

    private void c() {
        if (this.h != null) {
            double maxAmplitude = ((double) this.h.getMaxAmplitude()) / ((double) this.q);
            double d = 0.0d;
            if (maxAmplitude > 1.0d) {
                d = 20.0d * Math.log10(maxAmplitude);
            }
            if (this.b != null) {
                this.b.onRecording(d);
            }
            this.f.postDelayed(this.s, (long) this.r);
        }
    }
}
