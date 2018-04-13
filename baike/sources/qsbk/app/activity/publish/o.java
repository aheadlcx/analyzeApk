package qsbk.app.activity.publish;

import android.content.Intent;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import qsbk.app.model.ImageInfo;
import qsbk.app.utils.ResultActivityListener;

class o implements ResultActivityListener {
    final /* synthetic */ CirclePublishActivity a;

    o(CirclePublishActivity circlePublishActivity) {
        this.a = circlePublishActivity;
    }

    public void onResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            ArrayList arrayList = (ArrayList) intent.getSerializableExtra("paths");
            if (arrayList != null && arrayList.size() > 0) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ImageInfo imageInfo = (ImageInfo) it.next();
                    if (new File(imageInfo.getFilePath(this.a)).exists()) {
                        this.a.y.add(imageInfo);
                    }
                }
            }
            this.a.x();
            this.a.y();
        }
    }
}
