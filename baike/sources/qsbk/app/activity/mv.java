package qsbk.app.activity;

import android.graphics.Rect;
import android.view.View;
import android.view.View.OnClickListener;
import java.util.ArrayList;
import qsbk.app.activity.GroupInfoActivity.GridAdapter;
import qsbk.app.model.GroupInfo$PicInfo;
import qsbk.app.model.ImageInfo;
import qsbk.app.model.media.MediaFormat;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.Util;

class mv implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ GridAdapter b;

    mv(GridAdapter gridAdapter, int i) {
        this.b = gridAdapter;
        this.a = i;
    }

    public void onClick(View view) {
        this.b.a.F = this.a;
        ArrayList arrayList = new ArrayList();
        for (GroupInfo$PicInfo groupInfo$PicInfo : this.b.b) {
            arrayList.add(new ImageInfo(groupInfo$PicInfo.picUrl, MediaFormat.IMAGE_STATIC));
        }
        GroupInfoActivity.sActionResult = 0;
        Rect[] rectArr = new Rect[this.b.b.size()];
        rectArr[this.b.a.F] = UIHelper.getRectOnScreen(view);
        ImageViewer.launch(Util.getActivityOrContext(view), this.b.a.F, arrayList, rectArr, this.b.a.H, this.b.a.b.id);
    }
}
