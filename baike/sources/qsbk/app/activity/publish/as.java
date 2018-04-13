package qsbk.app.activity.publish;

import android.view.View;
import qsbk.app.activity.ImagePreviewActivity;
import qsbk.app.model.ImageInfo;
import qsbk.app.widget.QiushiDeleteViewFactory.OnViewOptionClickListener;

class as implements OnViewOptionClickListener {
    final /* synthetic */ PublishActivity a;

    as(PublishActivity publishActivity) {
        this.a = publishActivity;
    }

    public void onDelete(View view, int i) {
        this.a.j.remove(i);
        this.a.w();
    }

    public void onViewClick(View view, ImageInfo imageInfo, int i) {
        ImagePreviewActivity.launchForResult(this.a, this.a.j, this.a.j, i, 10010);
    }
}
