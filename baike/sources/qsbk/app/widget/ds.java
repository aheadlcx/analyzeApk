package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.EventWindowActivity;
import qsbk.app.model.QiushiTopic;

class ds implements OnClickListener {
    final /* synthetic */ QiushiTopic a;
    final /* synthetic */ QiushiTopicHeader b;

    ds(QiushiTopicHeader qiushiTopicHeader, QiushiTopic qiushiTopic) {
        this.b = qiushiTopicHeader;
        this.a = qiushiTopic;
    }

    public void onClick(View view) {
        if (this.a.hasEvent()) {
            EventWindowActivity.launch(this.b.getContext(), this.a.eventWindow);
        }
    }
}
