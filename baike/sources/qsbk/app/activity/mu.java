package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.GroupInfoActivity.GridAdapter;

class mu implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ GridAdapter b;

    mu(GridAdapter gridAdapter, int i) {
        this.b = gridAdapter;
        this.a = i;
    }

    public void onClick(View view) {
        this.b.a.F = this.a;
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("image/*");
        this.b.a.startActivityForResult(intent, 100);
    }
}
