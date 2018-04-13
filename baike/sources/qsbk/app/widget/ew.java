package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.widget.TopicCell.TopicPagerAdapter;

class ew implements OnClickListener {
    final /* synthetic */ TopicPagerAdapter a;

    ew(TopicPagerAdapter topicPagerAdapter) {
        this.a = topicPagerAdapter;
    }

    public void onClick(View view) {
        this.a.a.onClick();
    }
}
