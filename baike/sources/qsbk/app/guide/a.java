package qsbk.app.guide;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.UIHelper;

class a extends Handler {
    final /* synthetic */ GuideUtil a;

    a(GuideUtil guideUtil, Looper looper) {
        this.a = guideUtil;
        super(looper);
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 101:
                LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.type = 2002;
                layoutParams.format = 1;
                layoutParams.gravity = 51;
                layoutParams.width = UIHelper.dip2px(this.a.e, 173.0f);
                layoutParams.height = UIHelper.dip2px(this.a.e, 38.0f);
                layoutParams.x = UIHelper.dip2px(this.a.e, 3.0f);
                layoutParams.y = GuideUtil.b - UIHelper.dip2px(this.a.e, 52.0f);
                DebugUtil.debug("luolong", "mHandler Guide, mImgView=" + this.a.f + ",y=" + layoutParams.y);
                this.a.g.addView(this.a.f, layoutParams);
                this.a.i.postDelayed(new b(this), 3000);
                return;
            default:
                return;
        }
    }
}
