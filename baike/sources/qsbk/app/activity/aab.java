package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import com.baidu.mobstat.StatService;
import qsbk.app.QsbkApp;
import qsbk.app.utils.LoginPermissionClickDelegate;
import qsbk.app.utils.SubscribeReportHelper;

class aab implements OnClickListener {
    final /* synthetic */ QiuYouActivity a;

    aab(QiuYouActivity qiuYouActivity) {
        this.a = qiuYouActivity;
    }

    public void onClick(View view) {
        StatService.onEvent(this.a, "found_click", SubscribeReportHelper.TYPE_GROUP);
        if (QsbkApp.currentUser != null) {
            NearByGroupActivity.launch(this.a);
        } else {
            LoginPermissionClickDelegate.startLoginActivity(this.a);
        }
    }
}
