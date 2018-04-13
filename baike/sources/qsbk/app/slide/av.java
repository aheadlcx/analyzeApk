package qsbk.app.slide;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.utils.UIHelper;

class av implements OnClickListener {
    final /* synthetic */ SingleArticleFragment a;

    av(SingleArticleFragment singleArticleFragment) {
        this.a = singleArticleFragment;
    }

    public void onClick(View view) {
        this.a.aE = null;
        this.a.aA.setVisibility(8);
        if (TextUtils.isEmpty(this.a.a.getText().toString())) {
            this.a.u.setEnabled(false);
        }
        this.a.a.requestFocus();
        UIHelper.hideKeyboard(this.a.getActivity());
    }
}
