package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.utils.UIHelper;

class abv implements OnClickListener {
    final /* synthetic */ SearchQiuYouActivity a;

    abv(SearchQiuYouActivity searchQiuYouActivity) {
        this.a = searchQiuYouActivity;
    }

    public void onClick(View view) {
        UIHelper.hideKeyboard(this.a);
        this.a.finish();
    }
}
