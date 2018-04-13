package qsbk.app.guide;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import qsbk.app.utils.DebugUtil;

public class GuideUtil {
    private static GuideUtil a = null;
    private static int b = 0;
    private final int c = 101;
    private final int d = 102;
    private Context e;
    private ImageView f;
    private WindowManager g;
    private boolean h = true;
    private Handler i = new a(this, Looper.getMainLooper());

    private GuideUtil() {
    }

    public static GuideUtil getInstance() {
        synchronized (GuideUtil.class) {
            if (a == null) {
                a = new GuideUtil();
            }
        }
        return a;
    }

    public void initGuide(Activity activity, int i) {
        if (this.h) {
            this.e = activity;
            this.g = activity.getWindowManager();
            this.f = new ImageView(activity);
            this.f.setLayoutParams(new LayoutParams(-1, -1));
            this.f.setScaleType(ScaleType.FIT_XY);
            this.f.setImageResource(i);
            this.f.setOnClickListener(new c(this));
        }
    }

    public void showGuideTips(int i) {
        DebugUtil.debug("luolong", "showGuideTips, yPos=" + i);
        b = i;
        this.i.sendEmptyMessageDelayed(101, 50);
    }

    public boolean isFirst() {
        return this.h;
    }

    public void setFirst(boolean z) {
        this.h = z;
    }
}
