package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.QiushiTopicActivity;
import qsbk.app.model.QiushiTopic;

class ax implements OnClickListener {
    final /* synthetic */ QiushiTopic a;
    final /* synthetic */ ap b;

    ax(ap apVar, QiushiTopic qiushiTopic) {
        this.b = apVar;
        this.a = qiushiTopic;
    }

    public void onClick(View view) {
        QiushiTopicActivity.Launch(this.b.a.h, this.a);
    }
}
