package qsbk.app.activity.publish;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.PopupWindow;
import qsbk.app.R;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.UIHelper;

class au implements Runnable {
    final /* synthetic */ PublishActivity a;

    au(PublishActivity publishActivity) {
        this.a = publishActivity;
    }

    public void run() {
        if (!SharePreferenceUtils.getSharePreferencesBoolValue("showed_qiushi_topic_bubble")) {
            int[] iArr = new int[2];
            this.a.f.getLocationOnScreen(iArr);
            Drawable drawable = this.a.getResources().getDrawable(R.drawable.ic_qiushi_topic_bubble);
            View imageView = new ImageView(this.a);
            imageView.setImageDrawable(drawable);
            imageView.setLayoutParams(new LayoutParams(-2, -2));
            this.a.O = new PopupWindow(imageView, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            imageView.setOnClickListener(new av(this));
            this.a.O.setOutsideTouchable(true);
            this.a.O.setBackgroundDrawable(new BitmapDrawable());
            this.a.O.showAtLocation(this.a.getWindow().getDecorView(), 51, UIHelper.dip2px(this.a, 22.0f), (iArr[1] - drawable.getIntrinsicHeight()) - UIHelper.dip2px(this.a, 4.0f));
            this.a.O.setOnDismissListener(new aw(this));
        }
    }
}
