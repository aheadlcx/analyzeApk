package qsbk.app.slide;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.model.Comment;
import qsbk.app.utils.ReportCommon;

class aj implements OnClickListener {
    final /* synthetic */ Comment a;
    final /* synthetic */ SingleArticleFragment b;

    aj(SingleArticleFragment singleArticleFragment, Comment comment) {
        this.b = singleArticleFragment;
        this.a = comment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        ReportCommon.setReportCommon(this.b.l, i, this.a);
        this.b.o.a.remove(this.a);
        this.b.n.a.remove(this.a);
        this.b.p.a.remove(this.a);
        if (this.b.n.a.size() == 0) {
            this.b.q.addFooterView(this.b.M);
        }
        this.b.m.notifyDataSetChanged();
        ReportCommon.reportHandler(true);
    }
}
