package cn.xiaochuankeji.tieba.ui.post;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;
import c.a.d.a.a;
import c.a.i.u;
import cn.htjyb.ui.widget.headfooterlistview.header.State;
import cn.htjyb.ui.widget.headfooterlistview.header.d;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.utils.e;

public class i extends d implements u {
    private ImageView e;
    private AnimationDrawable f;

    public i(Context context) {
        super(context);
    }

    protected void a(Context context) {
        this.e = new ImageView(context);
        addView(this.e, new LayoutParams(-1, e.a(53.0f)));
        this.e.setScaleType(ScaleType.CENTER);
        d();
    }

    public void setState(State state) {
        d();
        switch (state) {
            case kStateReleaseToRefresh:
                this.f.start();
                break;
            case kStateRefreshing:
                this.f.start();
                break;
            case kStateHide:
                this.f.stop();
                break;
        }
        super.setState(state);
    }

    public void d() {
        setBackgroundColor(a.a().a((int) R.color.CL));
        this.f = (AnimationDrawable) a.a().b(R.drawable.anim_recommend_refresh);
        this.e.setImageDrawable(this.f);
    }
}
