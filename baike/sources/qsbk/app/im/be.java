package qsbk.app.im;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.NewsWebActivity;

class be implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ az b;

    be(az azVar, String str) {
        this.b = azVar;
        this.a = str;
    }

    public void onClick(View view) {
        if (!TextUtils.isEmpty(this.a)) {
            NewsWebActivity.launch(view.getContext(), this.a, true);
        }
    }
}
