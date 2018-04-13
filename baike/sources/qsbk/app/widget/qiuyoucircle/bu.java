package qsbk.app.widget.qiuyoucircle;

import android.view.View;
import android.view.View.OnClickListener;
import com.baidu.mobstat.StatService;
import com.qiushibaike.statsdk.StatSDK;
import qsbk.app.QsbkApp;
import qsbk.app.activity.SimpleWebActivity;
import qsbk.app.model.CircleArticle;
import qsbk.app.utils.Util;

class bu implements OnClickListener {
    final /* synthetic */ CircleArticle a;
    final /* synthetic */ WebAdCell b;

    bu(WebAdCell webAdCell, CircleArticle circleArticle) {
        this.b = webAdCell;
        this.a = circleArticle;
    }

    public void onClick(View view) {
        SimpleWebActivity.launch(Util.getActivityOrContext(view), this.a.adUrl);
        StatService.onEvent(QsbkApp.mContext, "circle_ad", "click_" + this.a.id);
        StatSDK.onEvent(QsbkApp.mContext, "circle_ad", "click_" + this.a.id);
    }
}
