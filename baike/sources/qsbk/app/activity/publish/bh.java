package qsbk.app.activity.publish;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.EventWindowActivity;
import qsbk.app.activity.QiushiTopicListActivity;
import qsbk.app.model.EventWindow;

class bh implements OnClickListener {
    final /* synthetic */ PublishActivity a;

    bh(PublishActivity publishActivity) {
        this.a = publishActivity;
    }

    public void onClick(View view) {
        if (this.a.P != null && EventWindow.JUMP_QB_POST.equals(this.a.P.actionType)) {
            if (this.a.P.getQiushiTopic() != null) {
                EventWindowActivity.launchForResult(this.a, this.a.P);
                return;
            }
            this.a.j();
            QiushiTopicListActivity.launchForResult(this.a, 2, 3);
        }
    }
}
