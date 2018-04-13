package qsbk.app.widget;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.MainActivity;
import qsbk.app.slide.SlideActivity;

class at implements OnClickListener {
    final /* synthetic */ CircleTopicMoreCell a;

    at(CircleTopicMoreCell circleTopicMoreCell) {
        this.a = circleTopicMoreCell;
    }

    public void onClick(View view) {
        Context context = this.a.getContext();
        if (context instanceof MainActivity) {
            ((MainActivity) context).gotoTab(MainActivity.TAB_QIUYOUCIRCLE_ID, 3);
        } else if (context instanceof SlideActivity) {
            ((SlideActivity) context).finish();
            if (MainActivity.mInstance != null) {
                MainActivity.mInstance.gotoTab(MainActivity.TAB_QIUYOUCIRCLE_ID, 3);
            }
        }
    }
}
