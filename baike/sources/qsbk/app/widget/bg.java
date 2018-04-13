package qsbk.app.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.MainActivity;
import qsbk.app.slide.SlideActivity;

class bg implements OnClickListener {
    final /* synthetic */ CircleVideoCell a;

    bg(CircleVideoCell circleVideoCell) {
        this.a = circleVideoCell;
    }

    public void onClick(View view) {
        Context baseContext;
        Context context = view.getContext();
        if (context instanceof ContextWrapper) {
            baseContext = ((ContextWrapper) context).getBaseContext();
        } else {
            baseContext = context;
        }
        if (baseContext instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) baseContext;
            mainActivity.goTab(MainActivity.TAB_QIUYOUCIRCLE_ID);
            CircleVideoCell.a(this.a).post(new bh(this, mainActivity.getCurrentFragment()));
        }
        if (baseContext instanceof SlideActivity) {
            SlideActivity slideActivity = (SlideActivity) baseContext;
            slideActivity.setResult(SlideActivity.MORE_CIRCLE_VIDEO);
            slideActivity.finish();
        }
    }
}
