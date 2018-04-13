package qsbk.app.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.MainActivity;

class bf implements OnClickListener {
    final /* synthetic */ CircleVideoCell a;

    bf(CircleVideoCell circleVideoCell) {
        this.a = circleVideoCell;
    }

    public void onClick(View view) {
        Context context = view.getContext();
        if (context instanceof ContextWrapper) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        if (context instanceof MainActivity) {
            ((MainActivity) context).goTab(MainActivity.TAB_QIUYOUCIRCLE_ID);
        }
    }
}
