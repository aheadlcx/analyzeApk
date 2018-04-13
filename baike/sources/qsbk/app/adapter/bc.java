package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.CircleTopicActivity;
import qsbk.app.model.CircleTopic;

class bc implements OnClickListener {
    final /* synthetic */ CircleTopic a;
    final /* synthetic */ CircleTopicListAdapter b;

    bc(CircleTopicListAdapter circleTopicListAdapter, CircleTopic circleTopic) {
        this.b = circleTopicListAdapter;
        this.a = circleTopic;
    }

    public void onClick(View view) {
        CircleTopicActivity.launch(this.b.k, this.a.id);
    }
}
