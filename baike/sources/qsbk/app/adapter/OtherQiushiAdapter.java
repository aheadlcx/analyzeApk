package qsbk.app.adapter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import qsbk.app.R;
import qsbk.app.adapter.ArticleAdapter.ViewHolder;
import qsbk.app.model.Article;
import qsbk.app.model.RssArticle;

public class OtherQiushiAdapter extends BaseVideoAdapter {
    boolean e;

    private class a extends ViewHolder {
        TextView b;
        final /* synthetic */ OtherQiushiAdapter c;

        public a(OtherQiushiAdapter otherQiushiAdapter, View view) {
            this.c = otherQiushiAdapter;
            super(otherQiushiAdapter, view);
            this.b = (TextView) view.findViewById(R.id.type);
        }
    }

    public OtherQiushiAdapter(Activity activity, ListView listView, ArrayList<Object> arrayList, String str, String str2, boolean z) {
        super(activity, listView, arrayList, str, str2);
        this.e = z;
    }

    protected ViewHolder a(View view) {
        return new a(this, view);
    }

    protected boolean b() {
        return false;
    }

    protected void a(Article article, ViewHolder viewHolder, int i, View view) {
        super.a(article, viewHolder, i, view);
        if ((article instanceof RssArticle) && (viewHolder instanceof a)) {
            a((RssArticle) article, (a) viewHolder);
        } else if (viewHolder instanceof a) {
            ((a) viewHolder).b.setVisibility(8);
        }
    }

    protected int a() {
        return R.layout.layout_other_article_item;
    }

    private void a(RssArticle rssArticle, a aVar) {
        if (!TextUtils.isEmpty(rssArticle.type)) {
            aVar.eventView.setVisibility(4);
        }
        SubscribeAdapter.initType(rssArticle, aVar.b);
        aVar.unlikeView.setVisibility(this.e ? 0 : 8);
    }
}
