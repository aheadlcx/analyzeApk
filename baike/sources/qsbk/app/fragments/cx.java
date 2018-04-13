package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.utils.LoginPermissionClickDelegate;

class cx implements OnClickListener {
    final /* synthetic */ HotCommentCircleFragment a;

    cx(HotCommentCircleFragment hotCommentCircleFragment) {
        this.a = hotCommentCircleFragment;
    }

    public void onClick(View view) {
        LoginPermissionClickDelegate.startLoginActivity(this.a.getActivity());
    }
}
