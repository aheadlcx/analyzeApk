package qsbk.app.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class fn implements OnClickListener {
    final /* synthetic */ ManangeMyContentsFragment a;

    fn(ManangeMyContentsFragment manangeMyContentsFragment) {
        this.a = manangeMyContentsFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.reportNewPublishArticle();
        dialogInterface.dismiss();
    }
}
