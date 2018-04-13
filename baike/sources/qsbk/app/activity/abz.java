package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class abz implements OnClickListener {
    final /* synthetic */ SearchQiuYouActivity a;

    abz(SearchQiuYouActivity searchQiuYouActivity) {
        this.a = searchQiuYouActivity;
    }

    public void onClick(View view) {
        this.a.a(this.a.e.getText().toString(), true);
    }
}
