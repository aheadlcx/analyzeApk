package qsbk.app.widget.qiuyoucircle;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.CircleTopicActivity;
import qsbk.app.model.CircleTopic;

class ad implements OnClickListener {
    final /* synthetic */ CircleTopic a;
    final /* synthetic */ CircleTopicWeeklyCell b;

    ad(CircleTopicWeeklyCell circleTopicWeeklyCell, CircleTopic circleTopic) {
        this.b = circleTopicWeeklyCell;
        this.a = circleTopic;
    }

    public void onClick(View view) {
        CircleTopicActivity.launch(this.b.getContext(), this.a, 0);
    }
}
