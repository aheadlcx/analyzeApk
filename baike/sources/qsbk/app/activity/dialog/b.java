package qsbk.app.activity.dialog;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.GroupInfoActivity;

class b implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ PromoteFlowerDialog b;

    b(PromoteFlowerDialog promoteFlowerDialog, String str) {
        this.b = promoteFlowerDialog;
        this.a = str;
    }

    public void onClick(View view) {
        int parseInt;
        try {
            parseInt = Integer.parseInt(this.a);
        } catch (Exception e) {
            e.printStackTrace();
            parseInt = 0;
        }
        if (parseInt > 0) {
            GroupInfoActivity.launch(this.b, parseInt, null, false);
        }
    }
}
