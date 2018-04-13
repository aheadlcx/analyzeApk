package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.qq.e.ads.nativ.NativeMediaADData;
import qsbk.app.QsbkApp;
import qsbk.app.abtest.ArticleActionStater;
import qsbk.app.model.Article;
import qsbk.app.model.RssArticle;

class aff implements OnItemClickListener {
    final /* synthetic */ VideoImmersionActivity a;

    aff(VideoImmersionActivity videoImmersionActivity) {
        this.a = videoImmersionActivity;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Object item = this.a.n.getAdapter().getItem(i);
        if (!(item instanceof NativeMediaADData)) {
            if (item instanceof RssArticle) {
                ArticleActionStater.getInstance().statAction(ArticleActionStater.ACTION_DETAIL, ((Article) item).getStatType());
            }
            QsbkApp.currentDataSource = this.a.o;
            QsbkApp.currentSelectItem = (int) this.a.n.getAdapter().getItemId(i);
            Intent intent = new Intent(this.a, SingleArticle.class);
            intent.putExtra("source", "video" + (QsbkApp.currentSelectItem + 1));
            intent.putExtra("scroll_to_comment", false);
            intent.addFlags(67108864);
            if (item instanceof Article) {
                intent.putExtra("article", (Article) item);
            }
            this.a.startActivity(intent);
        }
    }
}
