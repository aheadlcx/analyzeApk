package cn.v6.sixrooms.pay.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

final class aw implements OnClickListener {
    final /* synthetic */ PayCardActivity a;

    aw(PayCardActivity payCardActivity) {
        this.a = payCardActivity;
    }

    public final void onClick(View view) {
        if (this.a.m != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("curSelectedMoney", this.a.m);
            bundle.putInt("id", this.a.g.getId());
            Intent intent = new Intent(this.a, MobilePayActivity.class);
            intent.putExtras(bundle);
            this.a.startActivityForResult(intent, 0);
        }
    }
}
