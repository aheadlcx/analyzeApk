package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.BaseUserInfo;

class bo implements OnClickListener {
    final /* synthetic */ BaseUserInfo a;
    final /* synthetic */ a b;

    bo(a aVar, BaseUserInfo baseUserInfo) {
        this.b = aVar;
        this.a = baseUserInfo;
    }

    public void onClick(View view) {
        this.b.m.myQiuyouOperation(2, this.a);
    }
}
