package qsbk.app.widget.qiuyoucircle;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.activity.HighLightQiushiActivity;
import qsbk.app.activity.NewsWebActivity;
import qsbk.app.activity.QiushiTopicActivity;
import qsbk.app.activity.SimpleWebActivity;
import qsbk.app.activity.SingleArticle;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.web.ui.WebActivity;
import qsbk.app.live.LivePullLauncher;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.QiushiTopic;
import qsbk.app.utils.Util;

class bd implements OnClickListener {
    final /* synthetic */ ShareCell a;

    bd(ShareCell shareCell) {
        this.a = shareCell;
    }

    public void onClick(View view) {
        CircleArticle circleArticle = (CircleArticle) this.a.getItem();
        if (circleArticle.type == 11) {
            LivePullLauncher.from(view.getContext()).doLaunch(0, circleArticle);
        } else if (circleArticle.type == 15) {
            NewsWebActivity.launch(view.getContext(), circleArticle.shareLink, true);
        } else if (circleArticle.type == 9) {
            SimpleWebActivity.launch(view.getContext(), circleArticle.shareLink);
        } else if (circleArticle.type == 12) {
            r1 = new Intent();
            r1.setClass(AppUtils.getInstance().getAppContext(), WebActivity.class);
            r1.putExtra("link", circleArticle.shareLink);
            r1.setFlags(ClientDefaults.MAX_MSG_SIZE);
            AppUtils.getInstance().getAppContext().startActivity(r1);
        } else if (16 == circleArticle.type) {
            HighLightQiushiActivity.luanchActivity(Util.getActivityOrContext(view), circleArticle.shareLink);
        } else if (17 == circleArticle.type) {
            try {
                int parseInt = Integer.parseInt(circleArticle.shareLink);
                if (parseInt > 0) {
                    QiushiTopicActivity.Launch(Util.getActivityOrContext(view), new QiushiTopic(parseInt));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            r1 = new Intent(view.getContext(), SingleArticle.class);
            r1.putExtra("article_id", circleArticle.shareLink);
            r1.putExtra("source", "only_article_id");
            view.getContext().startActivity(r1);
        }
    }
}
