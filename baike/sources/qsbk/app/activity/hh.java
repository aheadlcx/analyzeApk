package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

class hh implements OnClickListener {
    final /* synthetic */ CircleTopicActivity a;

    hh(CircleTopicActivity circleTopicActivity) {
        this.a = circleTopicActivity;
    }

    public void onClick(View view) {
        Intent intent = new Intent(this.a, TopicTopActivity.class);
        intent.addFlags(4194304);
        this.a.startActivity(intent);
    }
}
