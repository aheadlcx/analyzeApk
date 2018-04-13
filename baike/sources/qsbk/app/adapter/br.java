package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.BaseUserInfo;

class br implements OnClickListener {
    final /* synthetic */ BaseUserInfo a;
    final /* synthetic */ c b;

    br(c cVar, BaseUserInfo baseUserInfo) {
        this.b = cVar;
        this.a = baseUserInfo;
    }

    public void onClick(View view) {
        this.b.m.myQiuyouOperation(2, this.a);
    }
}
