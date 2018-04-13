package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class aah implements OnClickListener {
    final /* synthetic */ QiushiTopicActivity a;

    aah(QiushiTopicActivity qiushiTopicActivity) {
        this.a = qiushiTopicActivity;
    }

    public void onClick(View view) {
        this.a.onBackPressed();
    }
}
