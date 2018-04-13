package qsbk.app.activity;

import android.view.View;
import qsbk.app.model.Article;
import qsbk.app.widget.QiushiImageLayout;
import qsbk.app.widget.QiushiImageLayout.OnChildClickListener;

class co implements OnChildClickListener {
    final /* synthetic */ Article a;
    final /* synthetic */ QiushiImageLayout b;
    final /* synthetic */ AuditNativeActivity2 c;

    co(AuditNativeActivity2 auditNativeActivity2, Article article, QiushiImageLayout qiushiImageLayout) {
        this.c = auditNativeActivity2;
        this.a = article;
        this.b = qiushiImageLayout;
    }

    public void onViewClicked(View view, int i) {
        ImageViewer.launch(this.c, i, this.a.imageInfos, this.b.getImageLocations());
    }
}
