package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.QiushiTopicActivity;
import qsbk.app.model.QiushiTopic;

class aw implements OnClickListener {
    final /* synthetic */ QiushiTopic a;
    final /* synthetic */ ao b;

    aw(ao aoVar, QiushiTopic qiushiTopic) {
        this.b = aoVar;
        this.a = qiushiTopic;
    }

    public void onClick(View view) {
        QiushiTopicActivity.Launch(this.b.a.h, this.a);
    }
}
