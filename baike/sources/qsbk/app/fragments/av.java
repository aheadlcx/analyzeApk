package qsbk.app.fragments;

import android.view.View;
import qsbk.app.utils.Util;
import qsbk.app.widget.NoUnderlineClickableSpan;

class av extends NoUnderlineClickableSpan {
    final /* synthetic */ CircleTopicWeeklyFragment a;

    av(CircleTopicWeeklyFragment circleTopicWeeklyFragment) {
        this.a = circleTopicWeeklyFragment;
    }

    public void onClick(View view) {
        Util.joinQQGroup(this.a.getActivity(), "5j6pvJDs_GaTfOO4JVVEgVBJuDkQ-qSa");
    }
}
