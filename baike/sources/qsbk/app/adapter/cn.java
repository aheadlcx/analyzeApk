package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.BaseUserInfo;

class cn implements OnClickListener {
    final /* synthetic */ BaseUserInfo a;
    final /* synthetic */ QiuYouAdapter b;

    cn(QiuYouAdapter qiuYouAdapter, BaseUserInfo baseUserInfo) {
        this.b = qiuYouAdapter;
        this.a = baseUserInfo;
    }

    public void onClick(View view) {
        this.b.myQiuyouOperation(1, this.a);
    }
}
