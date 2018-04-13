package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.BaseUserInfo;

class bn implements OnClickListener {
    final /* synthetic */ BaseUserInfo a;
    final /* synthetic */ a b;

    bn(a aVar, BaseUserInfo baseUserInfo) {
        this.b = aVar;
        this.a = baseUserInfo;
    }

    public void onClick(View view) {
        this.b.m.myQiuyouOperation(1, this.a);
    }
}
