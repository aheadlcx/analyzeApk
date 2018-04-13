package qsbk.app.share;

import android.view.View;
import qsbk.app.model.Article;

public interface ShareAble {
    int getShareRequestCode();

    void setCollectionIcon(View view);

    void setSelectedArticle(Article article);
}
