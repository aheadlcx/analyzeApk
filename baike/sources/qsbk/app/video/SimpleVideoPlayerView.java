package qsbk.app.video;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import qsbk.app.R;

public class SimpleVideoPlayerView extends VideoPlayerView {
    private ImageView l;
    private TextView m;
    private String n;
    private AnimationDrawable o;

    public SimpleVideoPlayerView(Context context) {
        super(context);
    }

    public SimpleVideoPlayerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SimpleVideoPlayerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setDisplayMode(int i) {
        super.setDisplayMode(i);
        if (i == 1) {
            if (this.l != null) {
                this.l.setVisibility(8);
            }
            if (this.m != null) {
                this.m.setVisibility(8);
            }
            getPlayBtn().setVisibility(8);
            return;
        }
        this.m.setVisibility(0);
    }

    protected void a(int i) {
        super.a(i);
        this.l = (ImageView) findViewById(R.id.video_play_flag);
        if (this.l != null) {
            Drawable drawable = this.l.getDrawable();
            if (drawable instanceof AnimationDrawable) {
                this.o = (AnimationDrawable) drawable;
            }
        }
        this.m = (TextView) findViewById(R.id.video_duration);
    }

    public void resetWidget() {
        super.resetWidget();
        if (this.m != null) {
            this.m.setText(getDurationText());
            if (this.k == 0) {
                this.m.setVisibility(0);
            } else {
                this.m.setVisibility(8);
            }
        }
        if (this.l != null) {
            this.l.setVisibility(8);
        }
        if (this.o != null && this.o.isRunning()) {
            this.o.stop();
        }
    }

    public void reset() {
        super.reset();
    }

    public void setStrTotalTime(String str) {
        this.n = str;
        if (this.m != null && str != null) {
            this.m.setText(str);
        }
    }

    public String getDurationText() {
        if (this.n != null) {
            return this.n;
        }
        long duration = this.e.getDuration() / 1000;
        if (duration < 0) {
            return "--:--";
        }
        int i = (int) (duration % 60);
        return String.format("%d:%02d", new Object[]{Integer.valueOf((int) (duration / 60)), Integer.valueOf(i)});
    }

    public void onVideoBuffering(SimpleVideoPlayer simpleVideoPlayer, int i) {
        if (i >= 100 && this.m != null) {
            this.m.setText(getDurationText());
        }
    }

    public void setWidget(ProgressBar progressBar, View view, View view2, TextView textView, ImageView imageView) {
        super.setWidget(progressBar, view, view2);
        if (!(this.m == null || this.m.equals(textView))) {
            this.m.setVisibility(8);
        }
        this.l = imageView;
        this.m = textView;
    }

    public void setWidget(ProgressBar progressBar, View view, View view2) {
        super.setWidget(progressBar, view, view2);
    }

    protected void a() {
        super.a();
        if (this.k == 0) {
            if (this.l != null) {
                this.l.setVisibility(0);
            }
            if (!(this.o == null || this.o.isRunning())) {
                this.o.start();
            }
            if (this.m != null) {
                this.m.setVisibility(4);
                return;
            }
            return;
        }
        if (this.l != null) {
            this.l.setVisibility(8);
        }
        if (this.m != null) {
            this.m.setVisibility(8);
        }
    }

    protected void b() {
        super.b();
        if (this.k == 0) {
            if (this.l != null) {
                this.l.setVisibility(4);
            }
            if (this.m != null) {
                this.m.setVisibility(0);
                this.m.setText(getDurationText());
                return;
            }
            return;
        }
        if (this.l != null) {
            this.l.setVisibility(8);
        }
        if (this.m != null) {
            this.m.setVisibility(8);
        }
        getPlayBtn().setVisibility(8);
    }

    public void onClick(View view) {
    }

    public void autoSetVolume() {
        this.e.setVolume(0);
    }
}
