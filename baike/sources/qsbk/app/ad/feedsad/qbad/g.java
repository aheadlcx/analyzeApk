package qsbk.app.ad.feedsad.qbad;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.MainActivity;

class g implements OnClickListener {
    final /* synthetic */ QbAdItem a;

    g(QbAdItem qbAdItem) {
        this.a = qbAdItem;
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
