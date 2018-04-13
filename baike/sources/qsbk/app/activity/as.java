package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.utils.UIHelper;

class as implements OnClickListener {
    final /* synthetic */ AddQiuYouActivity a;

    as(AddQiuYouActivity addQiuYouActivity) {
        this.a = addQiuYouActivity;
    }

    public void onClick(View view) {
        UIHelper.hideKeyboard(this.a);
        this.a.finish();
    }
}
