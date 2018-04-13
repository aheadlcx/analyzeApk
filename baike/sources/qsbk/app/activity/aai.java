package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.share.ShareUtils;

class aai implements OnClickListener {
    final /* synthetic */ QiushiTopicActivity a;

    aai(QiushiTopicActivity qiushiTopicActivity) {
        this.a = qiushiTopicActivity;
    }

    public void onClick(View view) {
        ShareUtils.openShareDialog(this.a, this.a.a, 1);
    }
}
