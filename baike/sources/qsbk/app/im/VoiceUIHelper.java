package qsbk.app.im;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.media.MediaRecorder;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.utils.DeviceUtils;

public class VoiceUIHelper {
    private static final String a = VoiceUIHelper.class.getSimpleName();
    private static final float[] b = new float[]{0.12f, 0.15f, 0.18f};
    private final boolean c;
    private int d = 0;
    private IMChatBaseActivity e;
    private MediaRecorder f;
    private ImageView g;
    private String h;
    private ImageButton i;
    private View j;
    private View k;
    private a l;
    private int m;
    private int n = -1;
    private int o = 0;
    private long p = 0;
    private TextView q;
    private View r;
    private View s;
    private View t;
    private boolean u;
    private float v = 1.0f;
    private boolean w = false;
    private final Runnable x = new jp(this);
    private final Runnable y = new jq(this);
    private final Runnable z = new jr(this);

    private class a {
        final int a;
        final View[] b;
        int c;
        int d;
        final /* synthetic */ VoiceUIHelper e;

        public a(VoiceUIHelper voiceUIHelper, View[] viewArr) {
            this.e = voiceUIHelper;
            this.b = viewArr;
            this.a = ViewConfiguration.get(viewArr[0].getContext()).getScaledTouchSlop();
        }

        void a(MotionEvent motionEvent) {
            if (this.e.d != 1) {
                a(0);
                return;
            }
            int x = (int) motionEvent.getX();
            switch (motionEvent.getActionMasked()) {
                case 0:
                    this.c = x;
                    return;
                case 1:
                case 3:
                    this.e.a(false);
                    a(0);
                    return;
                case 2:
                    this.d = x - this.c;
                    if (Math.abs(this.d) >= this.a && this.d <= 0) {
                        a(this.d);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }

        @SuppressLint({"NewApi"})
        private void a(int i) {
            int i2 = 0;
            if (VERSION.SDK_INT < 11) {
                for (View view : this.b) {
                    Animation translateAnimation = new TranslateAnimation(0, (float) i, 0, (float) i, 1, 0.0f, 1, 0.0f);
                    translateAnimation.setDuration(0);
                    translateAnimation.setFillBefore(true);
                    translateAnimation.setFillAfter(true);
                    view.clearAnimation();
                    view.startAnimation(translateAnimation);
                }
                return;
            }
            View[] viewArr = this.b;
            int length = viewArr.length;
            while (i2 < length) {
                viewArr[i2].setTranslationX((float) i);
                i2++;
            }
        }
    }

    protected VoiceUIHelper(IMChatBaseActivity iMChatBaseActivity) {
        this.e = iMChatBaseActivity;
        this.c = iMChatBaseActivity instanceof GroupConversationActivity;
        this.u = DeviceUtils.hasPermission(this.e, "android.permission.RECORD_AUDIO");
        c();
    }

    private void b() {
        MotionEvent obtain = MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 3, 0.0f, 0.0f, 0);
        this.g.dispatchTouchEvent(obtain);
        obtain.recycle();
    }

    protected void a(boolean z) {
        if (z) {
            this.t.setVisibility(0);
            this.s.setVisibility(0);
            this.i.setVisibility(0);
            this.j.setVisibility(0);
            this.r.setVisibility(8);
            return;
        }
        this.t.setVisibility(8);
        this.i.setVisibility(8);
        this.j.setVisibility(8);
        this.s.setVisibility(8);
        this.r.setVisibility(0);
    }

    protected void a(int i) {
        if (i != this.d) {
            this.d = i;
            if (i == 0) {
                a(false);
                return;
            }
            switch (i) {
                case 1:
                    a(true);
                    return;
                case 2:
                    a(false);
                    return;
                case 3:
                    this.e.y.removeCallbacks(this.y);
                    this.e.y.removeCallbacks(this.z);
                    this.e.u();
                    a(false);
                    return;
                default:
                    Log.e(a, "voice input state " + i + "  is unknown.");
                    return;
            }
        }
    }

    private View b(int i) {
        return this.e.findViewById(i);
    }

    public void setSendVoiceButtonVisibility(boolean z) {
        if (this.g != null) {
            this.g.setVisibility(z ? 0 : 8);
        }
    }

    private void c() {
        this.g = (ImageView) b((int) R.id.sendVoice);
        this.g.setVisibility(0);
        this.q = (TextView) b((int) R.id.voiceTime);
        this.r = b((int) R.id.textInputContainer);
        this.s = b((int) R.id.voiceInputContainer);
        this.t = b((int) R.id.voiceEffectContainer);
        this.t.getViewTreeObserver().addOnGlobalLayoutListener(new js(this));
        this.i = (ImageButton) b((int) R.id.voicePause);
        this.j = b((int) R.id.voiceEffect);
        this.k = b((int) R.id.voiceCancelTips);
        this.l = new a(this, new View[]{this.t, this.k});
        this.n = this.e.getResources().getDisplayMetrics().widthPixels / 2;
        this.g.setOnTouchListener(new jt(this));
    }

    public void onResume() {
        this.u = DeviceUtils.hasPermission(this.e, "android.permission.RECORD_AUDIO");
    }

    private void d() {
        if (this.f != null) {
            this.f.setOnErrorListener(null);
            this.f.reset();
            this.f.release();
            this.f = null;
        }
    }

    private Builder e() {
        Builder builder = new Builder(this.e);
        View inflate = LayoutInflater.from(this.e).inflate(R.layout.update_dialog_message, null);
        ((TextView) inflate.findViewById(R.id.updateMessage)).setText("录音权限已被禁止，请打开手机\"设置 --> 权限管理 --> 应用程序\"，找到\"糗事百科\"，开启录音权限");
        builder.setView(inflate);
        builder.setPositiveButton("设置", new jw(this)).setNegativeButton("取消", new jv(this));
        return builder;
    }
}
