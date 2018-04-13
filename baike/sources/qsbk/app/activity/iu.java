package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.graphics.Rect;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.model.ImageInfo;
import qsbk.app.model.media.MediaFormat;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.Util;

class iu implements OnClickListener {
    final /* synthetic */ CircleTopicActivity a;

    iu(CircleTopicActivity circleTopicActivity) {
        this.a = circleTopicActivity;
    }

    public void onClick(View view) {
        if (this.a.U) {
            new Builder(this.a).setTitle("选择头像").setItems(new String[]{"拍照", "相册"}, new iw(this)).setNegativeButton("取消", new iv(this)).show();
            return;
        }
        Rect rectOnScreen = UIHelper.getRectOnScreen(this.a.k);
        ImageViewer.launch(Util.getActivityOrContext(this.a.k), new ImageInfo(QsbkApp.absoluteUrlOfCircleWebpImage(this.a.g.icon.url, this.a.g.createAt), MediaFormat.IMAGE_STATIC), rectOnScreen);
    }
}
