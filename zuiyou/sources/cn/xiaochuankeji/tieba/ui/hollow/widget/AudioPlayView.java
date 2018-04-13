package cn.xiaochuankeji.tieba.ui.hollow.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.hollow.util.c;
import com.izuiyou.a.a.b;

public class AudioPlayView extends LinearLayout {
    private ObjectAnimator a;
    private ObjectAnimator b;
    private LinearLayout c;
    private ImageView d;
    private ImageView e;
    private TextView f;
    private View g;
    private float h;
    private float i;
    private boolean j;
    private long k;

    public AudioPlayView(Context context) {
        super(context);
        e();
    }

    public AudioPlayView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        e();
    }

    public AudioPlayView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        e();
    }

    private void e() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_audio_play, this);
        f();
        g();
        h();
    }

    private void f() {
        this.c = (LinearLayout) findViewById(R.id.audio_play_fun_ll);
        this.d = (ImageView) findViewById(R.id.audio_play_click);
        this.e = (ImageView) findViewById(R.id.audio_play_loading);
        this.f = (TextView) findViewById(R.id.audio_play_time);
        this.g = findViewById(R.id.audio_play_back);
        this.g.setBackground(getBackground());
        setBackground(null);
    }

    private void g() {
        this.c.setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        setPadding(0, 0, 0, 0);
        this.j = false;
        this.h = 1.0f;
        this.i = 1.0f;
    }

    private void h() {
        this.a = ObjectAnimator.ofFloat(this.e, "rotation", new float[]{0.0f, 360.0f});
        this.a.setInterpolator(new LinearInterpolator());
        this.a.setRepeatCount(-1);
        this.a.setDuration(1000);
    }

    private void i() {
        if (this.b == null) {
            this.b = ObjectAnimator.ofFloat(this.g, "alpha", new float[]{this.h, this.i});
            this.b.setRepeatCount(-1);
            this.b.setRepeatMode(2);
            this.b.setDuration(1000);
            this.b.addListener(new AnimatorListenerAdapter(this) {
                final /* synthetic */ AudioPlayView a;

                {
                    this.a = r1;
                }

                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    this.a.g.setAlpha(1.0f);
                }
            });
            this.b.start();
        }
    }

    public void setPlayDuration(long j) {
        this.f.setText(c.b(j));
        this.k = j;
    }

    public void a(boolean z, float f, float f2) {
        this.j = z;
        this.h = f;
        this.i = f2;
    }

    public void a(long j) {
        this.f.setText(c.b(j));
    }

    public void a() {
        this.d.setImageResource(R.drawable.btn_audio_pause);
        this.e.setVisibility(0);
        this.f.setVisibility(8);
        this.a.start();
    }

    public void b() {
        this.e.setVisibility(8);
        this.a.cancel();
        this.d.setImageResource(R.drawable.btn_audio_pause);
        this.f.setVisibility(0);
        if (this.j) {
            i();
        }
    }

    public void c() {
        d();
        this.f.setText(c.b(this.k));
    }

    public void d() {
        this.d.setImageResource(R.drawable.btn_audio_play);
        this.f.setVisibility(0);
        this.e.setVisibility(8);
        b.c("AudioPlayerPro -> animatorFlash : " + this.b);
        if (this.b != null) {
            this.b.cancel();
            this.b = null;
        }
    }
}
