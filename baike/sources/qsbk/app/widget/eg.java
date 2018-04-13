package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.HighLightQiushiActivity;
import qsbk.app.model.Qsjx;

class eg implements OnClickListener {
    final /* synthetic */ Qsjx a;
    final /* synthetic */ QsjxCell b;

    eg(QsjxCell qsjxCell, Qsjx qsjx) {
        this.b = qsjxCell;
        this.a = qsjx;
    }

    public void onClick(View view) {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.a.articleIds != null) {
            for (String append : this.a.articleIds) {
                stringBuilder.append(append).append(',');
            }
        }
        if (stringBuilder.length() > 0) {
            HighLightQiushiActivity.luanchActivity(view.getContext(), stringBuilder.substring(0, stringBuilder.length() - 1), this.a.title, this.a.date * 1000);
        }
    }
}
