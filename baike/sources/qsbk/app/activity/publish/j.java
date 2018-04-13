package qsbk.app.activity.publish;

import android.app.AlertDialog.Builder;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;

class j implements OnClickListener {
    final /* synthetic */ CirclePublishActivity a;

    j(CirclePublishActivity circlePublishActivity) {
        this.a = circlePublishActivity;
    }

    public void onClick(View view) {
        if (this.a.m.getVisibility() != 0) {
            return;
        }
        if (TextUtils.isEmpty(this.a.n.getText()) && TextUtils.isEmpty(this.a.n.getText())) {
            this.a.m.setVisibility(8);
            this.a.b.requestFocus();
            return;
        }
        new Builder(view.getContext()).setMessage("是否清空投票信息？").setNegativeButton("取消", new l(this)).setPositiveButton("确定", new k(this)).show();
    }
}
