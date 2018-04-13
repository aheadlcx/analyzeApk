package qsbk.app.im;

import android.annotation.SuppressLint;
import android.os.Build.VERSION;
import android.view.animation.BounceInterpolator;
import java.util.Random;

class jq implements Runnable {
    final /* synthetic */ VoiceUIHelper a;

    jq(VoiceUIHelper voiceUIHelper) {
        this.a = voiceUIHelper;
    }

    @SuppressLint({"NewApi"})
    public void run() {
        if (VERSION.SDK_INT >= 11 && this.a.f != null) {
            float min = Math.min(((float) (((((double) this.a.f.getMaxAmplitude()) / 32768.0d) * ((double) (this.a.e.t - 1.1f))) * ((double) this.a.e.t))) + 1.0f, this.a.e.t);
            if (min == this.a.e.t) {
                if (this.a.v == this.a.e.t) {
                    min -= VoiceUIHelper.b[new Random().nextInt(VoiceUIHelper.b.length)];
                }
                this.a.v = this.a.e.t;
            }
            if (min >= 1.0f) {
                this.a.j.animate().scaleX(min).scaleY(min).setInterpolator(new BounceInterpolator()).setDuration(this.a.e.v);
                if (min > 1.0f) {
                    this.a.w = true;
                }
            }
            if (this.a.d == 1) {
                this.a.e.y.postDelayed(this, this.a.e.v);
            }
        }
    }
}
