package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.R;
import qsbk.app.utils.UIHelper;

class j implements OnClickListener {
    final /* synthetic */ c a;
    final /* synthetic */ ArticleAdapter b;

    j(ArticleAdapter articleAdapter, c cVar) {
        this.b = articleAdapter;
        this.a = cVar;
    }

    public void onClick(View view) {
        if (this.b.c != null) {
            this.b.c.onTabSelect(0);
        }
        this.a.b.setTextColor(UIHelper.getAttrColor(this.b.k, R.attr.comment_section_text_color));
        this.a.a.setTextColor(UIHelper.getAttrColor(this.b.k, R.attr.comment_section_text_highlight));
    }
}
