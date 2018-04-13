package qsbk.app.activity.group;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.baidu.mobstat.StatService;
import com.qiushibaike.statsdk.StatSDK;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.QsbkApp;
import qsbk.app.activity.CircleTopicActivity;
import qsbk.app.activity.SimpleWebActivity;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.web.ui.WebActivity;
import qsbk.app.im.GameWebViewActivity;
import qsbk.app.live.LivePullLauncher;
import qsbk.app.model.CircleTopic;
import qsbk.app.utils.ReportAdForMedalUtil;
import qsbk.app.utils.ReportAdForMedalUtil.AD_PROVIDER;
import qsbk.app.utils.ReportAdForMedalUtil.AD_TYPE;
import qsbk.app.utils.SplashAdManager.SplashAdItem;

class c implements OnClickListener {
    final /* synthetic */ SplashAdActivity a;

    c(SplashAdActivity splashAdActivity) {
        this.a = splashAdActivity;
    }

    public void onClick(View view) {
        if ("topic".equals(this.a.c.type)) {
            this.a.a.removeCallbacks(this.a.e);
            this.a.a();
            CircleTopic circleTopic = new CircleTopic();
            circleTopic.id = this.a.c.topicId;
            circleTopic.content = "";
            CircleTopicActivity.launch(this.a, circleTopic, 0);
            this.a.finish();
        } else if ("web".equals(this.a.c.type)) {
            this.a.a.removeCallbacks(this.a.e);
            this.a.a();
            SimpleWebActivity.launch(view.getContext(), this.a.c.webLink, this.a.c.needShare, this.a.c.need_nav);
            ReportAdForMedalUtil.report(AD_PROVIDER.QIUBAI, AD_TYPE.KAIPING);
            this.a.finish();
        } else if (SplashAdItem.AD_WEB_GAME.equals(this.a.c.type)) {
            this.a.a.removeCallbacks(this.a.e);
            this.a.a();
            if (!(QsbkApp.currentUser == null || TextUtils.isEmpty(this.a.c.webLink))) {
                StringBuilder stringBuilder;
                SplashAdItem f;
                if (this.a.c.webLink.contains("?")) {
                    stringBuilder = new StringBuilder();
                    f = this.a.c;
                    f.webLink = stringBuilder.append(f.webLink).append("&token=").append(QsbkApp.currentUser.token).toString();
                } else {
                    stringBuilder = new StringBuilder();
                    f = this.a.c;
                    f.webLink = stringBuilder.append(f.webLink).append("?token=").append(QsbkApp.currentUser.token).toString();
                }
            }
            GameWebViewActivity.launch(view.getContext(), this.a.c.webLink, "", SplashAdItem.AD_WEB_GAME);
            ReportAdForMedalUtil.report(AD_PROVIDER.QIUBAI, AD_TYPE.KAIPING);
            this.a.finish();
        } else if ("live".equals(this.a.c.type)) {
            this.a.a.removeCallbacks(this.a.e);
            this.a.a();
            LivePullLauncher.from(view.getContext()).with(this.a.c.webLink).with(this.a.c.topicId, true).launch(0);
            this.a.finish();
        } else if (SplashAdItem.AD_WEB_ACT.equals(this.a.c.type)) {
            this.a.a.removeCallbacks(this.a.e);
            this.a.a();
            Intent intent = new Intent();
            intent.setClass(AppUtils.getInstance().getAppContext(), WebActivity.class);
            intent.putExtra("link", this.a.c.webLink);
            intent.putExtra("needShowActionbar", this.a.c.need_nav);
            intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
            AppUtils.getInstance().getAppContext().startActivity(intent);
            ReportAdForMedalUtil.report(AD_PROVIDER.QIUBAI, AD_TYPE.WEBACT);
            this.a.finish();
        } else if ("download".equals(this.a.c.type)) {
            this.a.a(view.getContext(), this.a.c.webLink);
        }
        StatSDK.onEvent(view.getContext(), "splash_ad_click", String.valueOf(this.a.c.id));
        StatService.onEvent(view.getContext(), "splash_ad_click", String.valueOf(this.a.c.id));
    }
}
