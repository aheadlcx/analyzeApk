package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.R;
import qsbk.app.utils.UIHelper;

class k implements OnClickListener {
    final /* synthetic */ c a;
    final /* synthetic */ ArticleAdapter b;

    k(ArticleAdapter articleAdapter, c cVar) {
        this.b = articleAdapter;
        this.a = cVar;
    }

    public void onClick(View view) {
        if (this.b.c != null) {
            this.b.c.onTabSelect(1);
        }
        this.a.a.setTextColor(UIHelper.getAttrColor(this.b.k, R.attr.comment_section_text_color));
        this.a.b.setTextColor(UIHelper.getAttrColor(this.b.k, R.attr.comment_section_text_highlight));
    }
}
