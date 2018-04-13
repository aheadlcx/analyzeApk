package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.CircleTopicWeeklyActivity;
import qsbk.app.model.CircleTopicEpisode;

class bb implements OnClickListener {
    final /* synthetic */ CircleTopicEpisode a;
    final /* synthetic */ a b;

    bb(a aVar, CircleTopicEpisode circleTopicEpisode) {
        this.b = aVar;
        this.a = circleTopicEpisode;
    }

    public void onClick(View view) {
        CircleTopicWeeklyActivity.launch(this.b.d.k, this.a.id);
    }
}
