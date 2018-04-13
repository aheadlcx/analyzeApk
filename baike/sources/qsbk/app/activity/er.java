package qsbk.app.activity;

import android.support.v7.app.AlertDialog.Builder;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.R;
import qsbk.app.utils.ToastAndDialog;

class er implements OnClickListener {
    final /* synthetic */ h a;

    er(h hVar) {
        this.a = hVar;
    }

    public void onClick(View view) {
        if (this.a.g.S == -1) {
            this.a.g.k();
        } else if (this.a.g.S > 0) {
            ToastAndDialog.makeText(this.a.g, "点击日历中未签日期进行补签").show();
        } else {
            new Builder(this.a.g, R.style.MyDialogStyleNormal).setTitle("你还没有补签卡，请先购买补签卡").setPositiveButton("购买", new es(this)).create().show();
        }
    }
}
