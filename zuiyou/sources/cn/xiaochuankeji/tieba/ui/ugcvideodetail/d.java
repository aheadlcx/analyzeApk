package cn.xiaochuankeji.tieba.ui.ugcvideodetail;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.base.j;

public class d extends j {
    private ImageView a;
    private AnimationDrawable b;

    protected d(Context context) {
        super(context);
    }

    protected View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.view_no_follow_ugcvideo, null);
    }

    protected void a(View view) {
        this.a = (ImageView) view.findViewById(R.id.ivAnim);
        this.b = (AnimationDrawable) this.a.getDrawable();
    }

    public void c() {
        f_().setVisibility(0);
        this.b.start();
    }

    public void d() {
        if (f_().getVisibility() == 0) {
            this.b.stop();
            f_().setVisibility(8);
        }
    }
}
