package qsbk.app.video;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.widget.SeekView;

public class CircleVideoPlayerView extends VideoPlayerView {
    private View l;
    private ImageView m;
    private TextView n;
    private TextView o;
    private SeekView p;
    private boolean q = false;
    private ImageView r;
    private Animation s;
    private Animation t;
    private OnFullScreenClick u;
    private OnControlBarListener v;
    private Runnable w = new a(this);
    private Runnable x = new b(this);

    public interface OnControlBarListener {
        void onControlBarStateChange(boolean z);
    }

    public interface OnFullScreenClick {
        void onFullScreenClick();
    }

    public CircleVideoPlayerView(Context context) {
        super(context);
        d();
    }

    public CircleVideoPlayerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        d();
    }

    public CircleVideoPlayerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        d();
    }

    private void d() {
        this.e.setLoop(false);
    }

    protected void a(int i) {
        super.a(i);
        e();
        h();
        this.l = findViewById(R.id.video_control_bar);
        if (this.l != null) {
            setControlBar(this.l);
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (this.c != null) {
            e();
        }
    }

    public void setWidget(ProgressBar progressBar, View view, View view2) {
        super.setWidget(progressBar, view, view2);
        e();
    }

    private void e() {
        if (this.c == null) {
            return;
        }
        if (this.c instanceof ImageView) {
            this.r = (ImageView) this.c;
            this.r.setImageResource(R.drawable.video_play);
            this.c = null;
            this.r.setOnClickListener(new c(this));
            return;
        }
        throw new IllegalArgumentException("布局中播放按钮必须是ImageView");
    }

    public void setControlBar(View view) {
        this.l = view;
        view.setVisibility(4);
        this.m = (ImageView) view.findViewById(R.id.play_pause);
        this.m.setOnClickListener(new d(this));
        this.n = (TextView) view.findViewById(R.id.current_time);
        this.o = (TextView) view.findViewById(R.id.total_time);
        this.p = (SeekView) view.findViewById(R.id.seek_view);
        this.p.setOnSeekViewChangeListener(new e(this));
        view.findViewById(R.id.full_screen).setOnClickListener(new f(this));
    }

    public void setOnFullScreenClick(OnFullScreenClick onFullScreenClick) {
        this.u = onFullScreenClick;
    }

    public void setOnControlBarListener(OnControlBarListener onControlBarListener) {
        this.v = onControlBarListener;
    }

    public void showControlBar(boolean z, boolean z2) {
        g();
        if (!(this.l == null || this.l.getVisibility() == 0)) {
            this.l.clearAnimation();
            this.l.setVisibility(0);
            this.l.startAnimation(this.s);
        }
        if (z2 && this.v != null) {
            this.v.onControlBarStateChange(true);
        }
        if (isPlaying()) {
            f();
        }
    }

    public void hideControlBar() {
        g();
        if (this.l != null && this.l.getVisibility() == 0) {
            this.l.clearAnimation();
            this.l.setVisibility(4);
            this.l.startAnimation(this.t);
        }
        if (this.v != null) {
            this.v.onControlBarStateChange(false);
        }
        if (this.r != null && this.r.getVisibility() == 0) {
            this.r.setVisibility(4);
        }
    }

    private void f() {
        if (this.l != null && this.l.getVisibility() == 0) {
            this.l.postDelayed(this.w, 3000);
        }
    }

    private void g() {
        if (this.l != null) {
            this.l.removeCallbacks(this.w);
        }
    }

    private void h() {
        Animation translateAnimation = new TranslateAnimation(0, 0.0f, 0, 0.0f, 1, 1.0f, 0, 0.0f);
        translateAnimation.setDuration(250);
        this.s = translateAnimation;
        translateAnimation = new TranslateAnimation(0, 0.0f, 0, 0.0f, 0, 0.0f, 1, 1.0f);
        translateAnimation.setDuration(250);
        this.t = translateAnimation;
    }

    public void resetWidget() {
        super.resetWidget();
        if (this.l != null) {
            this.o.setText("0:00");
            this.n.setText("0:00");
            this.p.setProgress(0);
            this.m.setImageResource(R.drawable.video_bar_play);
        }
        if (this.r != null) {
            this.r.setImageResource(R.drawable.video_play);
        }
        showControlBar(true, false);
    }

    public void onVideoPrepared(SimpleVideoPlayer simpleVideoPlayer) {
        super.onVideoPrepared(simpleVideoPlayer);
        if (this.l != null) {
            int duration = (int) this.e.getPlayer().getDuration();
            int i = duration / 1000;
            int i2 = i / 60;
            i %= 60;
            this.o.setText(String.format("%d:%02d", new Object[]{Integer.valueOf(i2), Integer.valueOf(i)}));
            this.p.setMax(duration);
            this.p.setProgress((int) simpleVideoPlayer.getStartMs());
        }
    }

    public void setStartMs(long j) {
        super.setStartMs(j);
        if (this.e.getDuration() > 0 && this.p != null) {
            this.p.setProgress((int) j);
        }
    }

    protected void a() {
        super.a();
        if (this.l != null) {
            this.x.run();
            this.m.setImageResource(R.drawable.video_bar_pause);
        }
        if (this.r != null) {
            this.r.setImageResource(R.drawable.video_pause);
            this.r.setVisibility(4);
        }
        showControlBar(false, true);
    }

    protected void b() {
        super.b();
        if (this.l != null) {
            removeCallbacks(this.x);
            this.m.setImageResource(R.drawable.video_bar_play);
        }
        g();
    }

    public void onClick(View view) {
        if (this.l != null && this.l.getVisibility() != 0) {
            showControlBar(true, true);
        } else if (this.l != null && this.l.getVisibility() == 0) {
            hideControlBar();
        }
    }
}
