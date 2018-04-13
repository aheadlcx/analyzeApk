package qsbk.app.activity.publish;

import android.content.Intent;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import qsbk.app.model.ImageInfo;
import qsbk.app.utils.ResultActivityListener;

class ba implements ResultActivityListener {
    final /* synthetic */ PublishActivity a;

    ba(PublishActivity publishActivity) {
        this.a = publishActivity;
    }

    public void onResult(int i, int i2, Intent intent) {
        if (i2 == -1 && intent != null) {
            ArrayList arrayList = (ArrayList) intent.getSerializableExtra("paths");
            this.a.j.clear();
            if (arrayList != null && arrayList.size() > 0) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ImageInfo imageInfo = (ImageInfo) it.next();
                    if (new File(imageInfo.getFilePath(this.a)).exists()) {
                        this.a.j.add(imageInfo);
                    }
                }
                this.a.ai = null;
                this.a.m = false;
                this.a.D();
                this.a.d(true);
            }
            this.a.w();
        }
    }
}
