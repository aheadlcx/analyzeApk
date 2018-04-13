package qsbk.app.widget;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class dw implements OnClickListener {
    final /* synthetic */ QiushiTopicCell a;
    final /* synthetic */ QiushiTopicRecommendCell b;

    dw(QiushiTopicRecommendCell qiushiTopicRecommendCell, QiushiTopicCell qiushiTopicCell) {
        this.b = qiushiTopicRecommendCell;
        this.a = qiushiTopicCell;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.onUpdate();
    }
}
