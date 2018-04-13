package qsbk.app.report;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import qsbk.app.QsbkApp;
import qsbk.app.utils.LoginPermissionClickDelegate;
import qsbk.app.utils.ToastAndDialog;

public class ArticleReporter {
    Handler a = new a(this);
    private Activity b;

    public ArticleReporter(Activity activity) {
        this.b = activity;
    }

    public void chooseReportOption() {
        if (QsbkApp.currentUser == null) {
            LoginPermissionClickDelegate.startLoginActivity(this.b);
            return;
        }
        this.b.startActivityForResult(new Intent(this.b, ReportActivity.class), 3);
    }

    public void chooseReportOption(Fragment fragment) {
        if (QsbkApp.currentUser == null) {
            LoginPermissionClickDelegate.startLoginActivity(this.b);
            return;
        }
        Intent intent = new Intent(this.b, ReportActivity.class);
        if (fragment.getParentFragment() != null) {
            fragment = fragment.getParentFragment();
        }
        fragment.startActivityForResult(intent, 3);
    }

    public void reportArticle(String str, int i) {
        if (QsbkApp.currentUser == null) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "登录后才能举报哦！", Integer.valueOf(0)).show();
            LoginPermissionClickDelegate.startLoginActivity(this.b);
            return;
        }
        new b(this, "qbk-ReportArt", i, str).start();
    }
}
