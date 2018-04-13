package qsbk.app.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.util.List;
import qsbk.app.model.CircleArticle;
import qsbk.app.widget.ArticleMoreOperationbar.Operation;

final class w implements OnClickListener {
    final /* synthetic */ List a;
    final /* synthetic */ Context b;
    final /* synthetic */ CircleArticle c;

    w(List list, Context context, CircleArticle circleArticle) {
        this.a = list;
        this.b = context;
        this.c = circleArticle;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        Operation operation = (Operation) this.a.get(i);
        if (operation != null) {
            switch (operation.a) {
                case 7:
                    ArticleMoreOperationbar.reportDialog(this.b, this.c);
                    return;
                case 11:
                    ArticleMoreOperationbar.deleteDialog(this.b, this.c);
                    return;
                case 14:
                    ArticleMoreOperationbar.forbid(this.b, this.c);
                    return;
                case 16:
                    ArticleMoreOperationbar.unlikeArticle(this.c);
                    return;
                default:
                    return;
            }
        }
    }
}
