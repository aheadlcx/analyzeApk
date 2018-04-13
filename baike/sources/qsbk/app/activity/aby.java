package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class aby implements OnClickListener {
    final /* synthetic */ SearchQiuYouActivity a;

    aby(SearchQiuYouActivity searchQiuYouActivity) {
        this.a = searchQiuYouActivity;
    }

    public void onClick(View view) {
        this.a.e.setText("");
    }
}
