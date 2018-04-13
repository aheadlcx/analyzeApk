package cn.xiaochuankeji.tieba.ui.ugcvideodetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import cn.xiaochuankeji.tieba.R;

public class j extends FrameLayout {
    private RotateAnimation a;

    public j(Context context) {
        super(context);
        a();
    }

    private void a() {
        LayoutInflater.from(getContext()).inflate(R.layout.follow_videos_thumb_load_more, this, true);
        this.a = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        this.a.setInterpolator(new LinearInterpolator());
        this.a.setDuration(1000);
        this.a.setRepeatCount(-1);
        findViewById(R.id.imgRefreshing).startAnimation(this.a);
    }
}
