package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.publish.PublishActivity;

class aag implements OnClickListener {
    final /* synthetic */ QiushiTopicActivity a;

    aag(QiushiTopicActivity qiushiTopicActivity) {
        this.a = qiushiTopicActivity;
    }

    public void onClick(View view) {
        if (QsbkApp.currentUser == null) {
            this.a.startActivity(new Intent(this.a, ActionBarLoginActivity.class));
            this.a.overridePendingTransition(R.anim.roll_up, R.anim.still_when_up);
            return;
        }
        Intent intent = new Intent(this.a, PublishActivity.class);
        intent.putExtra("topic", this.a.a);
        this.a.startActivity(intent);
    }
}
