package qsbk.app.im;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.NewsWebActivity;

class bg implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ ba b;

    bg(ba baVar, String str) {
        this.b = baVar;
        this.a = str;
    }

    public void onClick(View view) {
        if (!TextUtils.isEmpty(this.a)) {
            NewsWebActivity.launch(view.getContext(), this.a, true);
        }
    }
}
