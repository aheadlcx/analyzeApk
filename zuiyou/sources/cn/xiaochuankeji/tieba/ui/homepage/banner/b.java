package cn.xiaochuankeji.tieba.ui.homepage.banner;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.utils.e;

public class b extends FrameLayout {
    private LayoutInflater a;
    private UgcEventAnimWebImageView b;
    private long c;
    private int d = e.a(10.0f);
    private int e;
    private int f;
    private float g;

    public b(Context context) {
        int a;
        super(context);
        this.a = LayoutInflater.from(context);
        this.a.inflate(R.layout.view_ugc_event_anim, this);
        this.b = (UgcEventAnimWebImageView) findViewById(R.id.picEvent);
        this.e = e.a(10.0f);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.navbar_height);
        if (VERSION.SDK_INT >= 21) {
            a = e.a(10.0f);
        } else {
            a = e.a(5.0f);
        }
        this.f = a + dimensionPixelSize;
    }

    public void a(long j, float f) {
        this.g = f;
        if (this.c != j) {
            this.c = j;
            this.b.setWebImage(cn.xiaochuankeji.tieba.background.f.b.b(this.c));
        }
        if (f >= 0.7f) {
            setPadding(this.d, (int) (((float) this.f) - (((f - 0.7f) / 0.3f) * ((float) (this.f - this.e)))), this.d, 0);
        }
        this.b.setPercent(f);
    }
}
