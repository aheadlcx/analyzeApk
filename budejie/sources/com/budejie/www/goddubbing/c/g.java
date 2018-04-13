package com.budejie.www.goddubbing.c;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.text.TextUtils;
import com.budejie.www.activity.GodDubbingActivity.c;
import com.budejie.www.util.aa;
import com.budejie.www.widget.VoiceLineView;
import java.io.IOException;

public class g {
    private static g c;
    private MediaRecorder a;
    private MediaPlayer b;
    private a d;
    private VoiceLineView e;
    private VoiceLineView f;
    private g$b g;
    private c h;

    public interface a {
        void a();
    }

    public static g a() {
        if (c == null) {
            synchronized (g.class) {
                if (c == null) {
                    c = new g();
                }
            }
        }
        return c;
    }

    private g() {
    }

    public void a(c cVar) {
        this.h = cVar;
    }

    public void a(VoiceLineView voiceLineView, VoiceLineView voiceLineView2) {
        this.e = voiceLineView;
        this.f = voiceLineView2;
    }

    public void a(String str) {
        try {
            this.a = new MediaRecorder();
            this.a.setAudioSource(1);
            this.a.setOutputFormat(1);
            this.a.setAudioEncoder(3);
            this.a.setOutputFile(str);
            this.a.prepare();
            this.a.start();
            d();
            if (this.h != null) {
                this.h.sendEmptyMessageDelayed(2, 1000);
            }
        } catch (Exception e) {
            if (!TextUtils.isEmpty(e.getMessage())) {
                aa.e("RecordUtil", e.getMessage());
            }
        }
    }

    public void b() {
        if (this.a != null) {
            try {
                this.a.stop();
                this.a.release();
                this.a = null;
                if (this.h != null) {
                    this.h.removeMessages(2);
                }
                this.e.setVolume(0);
                this.f.setVolume(0);
            } catch (Exception e) {
                if (!TextUtils.isEmpty(e.getMessage())) {
                    aa.e("RecordUtil", e.getMessage());
                }
                this.a = null;
            }
        }
    }

    public void b(String str) {
        try {
            if (this.b == null) {
                this.b = new MediaPlayer();
            }
            this.b.reset();
            this.b.setDataSource(str);
            this.b.prepare();
            this.b.start();
            this.b.setOnCompletionListener(new g$1(this));
        } catch (IOException e) {
            if (!TextUtils.isEmpty(e.getMessage())) {
                aa.e("RecordUtil", e.getMessage());
            }
        }
    }

    public void a(a aVar) {
        this.d = aVar;
    }

    public void c() {
        if (this.b != null) {
            this.b.release();
            this.b = null;
        }
    }

    private void d() {
        if (this.a != null) {
            int maxAmplitude = this.a.getMaxAmplitude() / 600;
            double d = 0.0d;
            if (maxAmplitude > 1) {
                d = 20.0d * Math.log10((double) maxAmplitude);
            }
            if (this.h != null) {
                this.h.obtainMessage().arg1 = (int) d;
                this.e.setVolume((int) d);
                this.f.setVolume((int) d);
                if (this.g == null) {
                    this.g = new g$b(this, null);
                }
                this.h.postDelayed(this.g, 100);
            }
        }
    }
}
