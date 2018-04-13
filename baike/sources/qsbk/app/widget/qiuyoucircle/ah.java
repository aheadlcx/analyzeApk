package qsbk.app.widget.qiuyoucircle;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.activity.NewsWebActivity;
import qsbk.app.activity.SimpleWebActivity;
import qsbk.app.activity.SingleArticle;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.web.ui.WebActivity;
import qsbk.app.live.LivePullLauncher;
import qsbk.app.model.CircleArticle;

class ah implements OnClickListener {
    final /* synthetic */ CircleArticle a;
    final /* synthetic */ ForwardCell b;

    ah(ForwardCell forwardCell, CircleArticle circleArticle) {
        this.b = forwardCell;
        this.a = circleArticle;
    }

    public void onClick(View view) {
        if (this.a.type == 11) {
            LivePullLauncher.from(view.getContext()).doLaunch(0, this.a);
        } else if (this.a.type == 15) {
            NewsWebActivity.launch(view.getContext(), this.a.shareLink, true);
        } else if (this.a.type == 9) {
            SimpleWebActivity.launch(view.getContext(), this.a.shareLink);
        } else if (this.a.type == 12) {
            r0 = new Intent();
            r0.setClass(AppUtils.getInstance().getAppContext(), WebActivity.class);
            r0.putExtra("link", this.a.shareLink);
            r0.setFlags(ClientDefaults.MAX_MSG_SIZE);
            AppUtils.getInstance().getAppContext().startActivity(r0);
        } else {
            r0 = new Intent(view.getContext(), SingleArticle.class);
            r0.putExtra("article_id", this.a.shareLink);
            r0.putExtra("source", "only_article_id");
            view.getContext().startActivity(r0);
        }
    }
}
