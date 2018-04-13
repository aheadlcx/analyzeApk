package qsbk.app.slide;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class ai implements OnClickListener {
    final /* synthetic */ SingleArticleFragment a;

    ai(SingleArticleFragment singleArticleFragment) {
        this.a = singleArticleFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
