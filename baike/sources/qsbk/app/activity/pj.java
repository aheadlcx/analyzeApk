package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import java.io.Serializable;
import java.util.ArrayList;
import qsbk.app.model.ImageInfo;

class pj implements OnClickListener {
    final /* synthetic */ a a;
    final /* synthetic */ c b;

    pj(c cVar, a aVar) {
        this.b = cVar;
        this.a = aVar;
    }

    public void onClick(View view) {
        if (this.b.a.d.getItemViewType(this.a.a) == 0) {
            Intent intent = new Intent();
            intent.setClass(this.b.a, ImagePreviewActivity.class);
            intent.putExtra(ImagePreviewActivity.KEY_CHECKED_ARRAY, this.b.a.e);
            int max = Math.max(1, this.a.a - 50);
            int min = Math.min(this.b.a.b.size(), max + 100);
            Serializable arrayList = new ArrayList();
            for (int i = max; i < min; i++) {
                arrayList.add((ImageInfo) this.b.a.b.get(i));
            }
            intent.putExtra(ImagePreviewActivity.KEY_PIC_ALL, arrayList);
            if (this.a.a >= 1) {
                intent.putExtra(ImagePreviewActivity.KEY_POSITION, (this.a.a - max) % 100);
            }
            intent.putExtra("KEY_PICK_IAMGE", this.b.a.u);
            this.b.a.startActivityForResult(intent, 99);
        }
    }
}
