package cn.xiaochuankeji.tieba.ui.ugcvideodetail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout.LayoutParams;
import cn.xiaochuankeji.tieba.R;

public class c extends FrameLayout {
    private Context a;
    private View b;

    public c(@NonNull Context context) {
        super(context);
        this.a = context;
        a();
    }

    private void a() {
        LayoutInflater.from(this.a).inflate(R.layout.view_ugcvideo_play_guide, this);
        this.b = findViewById(R.id.vMarginBottom);
    }

    public void setBottomMargin(int i) {
        this.b.setLayoutParams(new LayoutParams(-1, i));
    }
}
