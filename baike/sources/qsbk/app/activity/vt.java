package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.model.ImageInfo;
import qsbk.app.model.UserInfo;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.Util;

class vt implements OnClickListener {
    final /* synthetic */ UserInfo a;
    final /* synthetic */ MyInfoActivity b;

    vt(MyInfoActivity myInfoActivity, UserInfo userInfo) {
        this.b = myInfoActivity;
        this.a = userInfo;
    }

    public void onClick(View view) {
        ImageViewer.launch(Util.getActivityOrContext(view), new ImageInfo(QsbkApp.absoluteUrlOfMediumUserIcon(this.a.userIcon, this.a.userId), QsbkApp.absoluteUrlOfLargeUserIcon(this.a.userIcon, this.a.userId)), UIHelper.getRectOnScreen(this.b.q));
    }
}
