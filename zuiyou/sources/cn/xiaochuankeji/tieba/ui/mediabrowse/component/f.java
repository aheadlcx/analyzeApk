package cn.xiaochuankeji.tieba.ui.mediabrowse.component;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.FrameLayout;
import cn.xiaochuankeji.tieba.R;

public class f {
    Window a;
    RoundProgressBar b;

    public f(@NonNull Window window) {
        this.a = window;
        Context context = this.a.getContext();
        this.b = new RoundProgressBar(context);
        this.b.setMax(100);
        this.b.setTextIsDisplayable(true);
        this.b.setRoundWidth(TypedValue.applyDimension(1, 2.0f, context.getResources().getDisplayMetrics()));
        this.b.setRoundProgressColor(-13980417);
        this.b.setRoundColor(1728053247);
        this.b.setTextColor(-13980417);
    }

    public void a() {
        this.b.setProgress(0);
        View findViewById = this.a.findViewById(16908290);
        if (findViewById.findViewById(R.id.progressBar) == null) {
            Context context = this.a.getContext();
            if (findViewById instanceof FrameLayout) {
                View frameLayout = new FrameLayout(context);
                frameLayout.setId(R.id.progressBar);
                frameLayout.setBackgroundColor(-872415232);
                int applyDimension = (int) TypedValue.applyDimension(1, 48.0f, context.getResources().getDisplayMetrics());
                LayoutParams layoutParams = new FrameLayout.LayoutParams(applyDimension, applyDimension);
                layoutParams.gravity = 17;
                frameLayout.addView(this.b, layoutParams);
                ((FrameLayout) findViewById).addView(frameLayout, new FrameLayout.LayoutParams(-1, -1));
            }
        }
    }

    public void b() {
        final View findViewById = this.a.findViewById(16908290);
        final View findViewById2 = findViewById.findViewById(R.id.progressBar);
        if (findViewById2 != null && (findViewById instanceof FrameLayout)) {
            findViewById2.post(new Runnable(this) {
                final /* synthetic */ f c;

                public void run() {
                    ((FrameLayout) findViewById).removeView(findViewById2);
                }
            });
        }
    }

    public void a(final int i) {
        if (this.b != null) {
            this.b.post(new Runnable(this) {
                final /* synthetic */ f b;

                public void run() {
                    this.b.b.setProgress(i);
                }
            });
        }
    }
}
