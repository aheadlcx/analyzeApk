package qsbk.app.slide;

import android.graphics.Rect;
import android.view.View;
import qsbk.app.activity.NewImageViewer;
import qsbk.app.model.Article;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.QiushiImageLayout.OnChildClickListener;

class x implements OnChildClickListener {
    final /* synthetic */ Article a;
    final /* synthetic */ SingleArticleFragment b;

    x(SingleArticleFragment singleArticleFragment, Article article) {
        this.b = singleArticleFragment;
        this.a = article;
    }

    public void onViewClicked(View view, int i) {
        NewImageViewer.launch(view.getContext(), this.b.aK.getImageLocations(), new Rect[]{UIHelper.getViewVisibleRect(this.b.aK)}, this.a, i);
    }
}
