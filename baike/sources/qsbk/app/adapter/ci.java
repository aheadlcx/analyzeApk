package qsbk.app.adapter;

import android.graphics.Rect;
import android.view.View;
import qsbk.app.activity.ImageViewer;
import qsbk.app.activity.NewImageViewer;
import qsbk.app.adapter.ManageMyContentsAdapter.ViewHolder;
import qsbk.app.model.Article;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.QiushiImageLayout.OnChildClickListener;

class ci implements OnChildClickListener {
    final /* synthetic */ Article a;
    final /* synthetic */ ViewHolder b;
    final /* synthetic */ ManageMyContentsAdapter c;

    ci(ManageMyContentsAdapter manageMyContentsAdapter, Article article, ViewHolder viewHolder) {
        this.c = manageMyContentsAdapter;
        this.a = article;
        this.b = viewHolder;
    }

    public void onViewClicked(View view, int i) {
        if ("publish".equals(this.a.state)) {
            NewImageViewer.launch(view.getContext(), this.b.qiushiImageLayout.getImageLocations(), new Rect[]{UIHelper.getViewVisibleRect(this.b.qiushiImageLayout)}, this.a, i);
            return;
        }
        ImageViewer.launch(view.getContext(), i, this.a.imageInfos, this.b.qiushiImageLayout.getImageLocations());
    }
}
