package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class aaa implements OnClickListener {
    final /* synthetic */ QiuYouActivity a;

    aaa(QiuYouActivity qiuYouActivity) {
        this.a = qiuYouActivity;
    }

    public void onClick(View view) {
        this.a.onBackPressed();
    }
}
