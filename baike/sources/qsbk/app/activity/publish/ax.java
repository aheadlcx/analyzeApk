package qsbk.app.activity.publish;

import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import qsbk.app.R;
import qsbk.app.utils.UIHelper;

class ax implements OnGlobalLayoutListener {
    final /* synthetic */ PublishActivity a;

    ax(PublishActivity publishActivity) {
        this.a = publishActivity;
    }

    public void onGlobalLayout() {
        if (this.a.O != null && this.a.O.isShowing()) {
            int[] iArr = new int[2];
            this.a.f.getLocationOnScreen(iArr);
            this.a.O.update(UIHelper.dip2px(this.a, 22.0f), (iArr[1] - this.a.getResources().getDrawable(R.drawable.ic_qiushi_topic_bubble).getIntrinsicHeight()) - UIHelper.dip2px(this.a, 4.0f), this.a.O.getWidth(), this.a.O.getHeight());
        }
    }
}
