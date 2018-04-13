package qsbk.app.activity.publish;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.QiushiTopicListActivity;

class at implements OnClickListener {
    final /* synthetic */ PublishActivity a;

    at(PublishActivity publishActivity) {
        this.a = publishActivity;
    }

    public void onClick(View view) {
        QiushiTopicListActivity.launchForResult(this.a, 2, 3);
    }
}
