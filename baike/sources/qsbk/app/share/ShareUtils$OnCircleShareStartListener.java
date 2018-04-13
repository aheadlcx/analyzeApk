package qsbk.app.share;

import android.view.View;
import qsbk.app.model.CircleArticle;

public interface ShareUtils$OnCircleShareStartListener {
    public static final String TYPE_REPORT_OR_COPY = "type_report_or_copy";
    public static final String TYPE_SHARE = "type_share";

    void onCircleShareStart(CircleArticle circleArticle);

    void onCircleShareStart(CircleArticle circleArticle, String str, View view);
}
