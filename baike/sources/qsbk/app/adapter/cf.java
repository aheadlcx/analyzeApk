package qsbk.app.adapter;

import android.text.Layout;
import android.widget.ImageView;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.utils.UIHelper;

class cf implements Runnable {
    final /* synthetic */ ImageView a;
    final /* synthetic */ TextView b;
    final /* synthetic */ ManageMyContentsAdapter c;

    cf(ManageMyContentsAdapter manageMyContentsAdapter, ImageView imageView, TextView textView) {
        this.c = manageMyContentsAdapter;
        this.a = imageView;
        this.b = textView;
    }

    public void run() {
        if (this.a != null) {
            Layout layout = this.b.getLayout();
            if (layout == null || layout.getLineCount() < 10 || layout.getEllipsisCount(9) <= 0) {
                this.a.setVisibility(8);
                return;
            }
            this.a.setImageResource(UIHelper.isNightTheme() ? R.drawable.icon_article_readmore_night : R.drawable.icon_article_readmore);
            this.a.setVisibility(0);
        }
    }
}
