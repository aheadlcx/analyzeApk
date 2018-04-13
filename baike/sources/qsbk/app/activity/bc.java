package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.model.GroupRecommend;

class bc implements OnClickListener {
    final /* synthetic */ ay a;

    bc(ay ayVar) {
        this.a = ayVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        GroupRecommend.refresh(this.a.e);
    }
}
