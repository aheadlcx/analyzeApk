package qsbk.app.im.CollectEmotion;

import android.app.AlertDialog.Builder;
import android.view.View;
import android.view.View.OnClickListener;
import java.util.ArrayList;
import qsbk.app.im.LatestUsedCollectionData;

class e implements OnClickListener {
    final /* synthetic */ ManageCollectActivity a;

    e(ManageCollectActivity manageCollectActivity) {
        this.a = manageCollectActivity;
    }

    public void onClick(View view) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.a.d.size(); i++) {
            arrayList.add(Integer.valueOf(((LatestUsedCollectionData) this.a.d.get(i)).id));
        }
        if (arrayList.size() > 0) {
            new Builder(view.getContext()).setMessage("确认删除所选择的图片？").setNegativeButton("删除", new g(this, arrayList)).setPositiveButton("取消", new f(this)).show();
        } else {
            this.a.finish();
        }
    }
}
