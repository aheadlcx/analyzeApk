package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class wy implements OnClickListener {
    final /* synthetic */ MyInfoActivity a;

    wy(MyInfoActivity myInfoActivity) {
        this.a = myInfoActivity;
    }

    public void onClick(View view) {
        QiushiTopicListActivity.launch(view.getContext(), 1);
    }
}
