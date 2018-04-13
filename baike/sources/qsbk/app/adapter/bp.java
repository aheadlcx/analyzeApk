package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.BaseUserInfo;

class bp implements OnClickListener {
    final /* synthetic */ BaseUserInfo a;
    final /* synthetic */ a b;

    bp(a aVar, BaseUserInfo baseUserInfo) {
        this.b = aVar;
        this.a = baseUserInfo;
    }

    public void onClick(View view) {
        this.b.m.myQiuyouOperation(3, this.a);
    }
}
