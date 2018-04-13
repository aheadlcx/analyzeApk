package qsbk.app.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.widget.QiushiTopicCell;

class jd implements OnClickListener {
    final /* synthetic */ QiushiTopicCell a;
    final /* synthetic */ QiushiTopicListFragment b;

    jd(QiushiTopicListFragment qiushiTopicListFragment, QiushiTopicCell qiushiTopicCell) {
        this.b = qiushiTopicListFragment;
        this.a = qiushiTopicCell;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.onUpdate();
    }
}
