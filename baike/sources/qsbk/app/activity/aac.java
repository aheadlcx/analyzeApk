package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.baidu.mobstat.StatService;
import qsbk.app.QsbkApp;
import qsbk.app.model.RssArticle.Type;
import qsbk.app.utils.LoginPermissionClickDelegate;

class aac implements OnClickListener {
    final /* synthetic */ QiuYouActivity a;

    aac(QiuYouActivity qiuYouActivity) {
        this.a = qiuYouActivity;
    }

    public void onClick(View view) {
        if (QsbkApp.currentUser != null) {
            this.a.startActivity(new Intent(this.a, NearByActivity.class));
        } else {
            LoginPermissionClickDelegate.startLoginActivity(this.a);
        }
        StatService.onEvent(this.a, "found_click", Type.NEARBY);
    }
}
