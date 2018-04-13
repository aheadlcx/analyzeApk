package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.nearby.api.RandomLocationMgr;
import qsbk.app.utils.ToastAndDialog;

class aan implements OnClickListener {
    final /* synthetic */ RandomDoorActivity a;

    aan(RandomDoorActivity randomDoorActivity) {
        this.a = randomDoorActivity;
    }

    public void onClick(View view) {
        if (RandomLocationMgr.getInstance().getAllLocations() == null) {
            ToastAndDialog.makeNegativeToast(this.a, "迷路了，找不到门，请稍后再试。").show();
            return;
        }
        this.a.b.start();
        view.setEnabled(false);
        view.postDelayed(new aao(this), 4000);
    }
}
