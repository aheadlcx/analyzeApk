package qsbk.app.im;

import android.content.Intent;
import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;

class i extends ClickableSpan {
    final /* synthetic */ String a;
    final /* synthetic */ ChatListAdapter b;

    i(ChatListAdapter chatListAdapter, String str) {
        this.b = chatListAdapter;
        this.a = str;
    }

    public void onClick(View view) {
        if (view.getTag() != null) {
            view.setTag(null);
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this.b.h, IMChatingUrlContentDisplayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("url", this.a);
        intent.putExtras(bundle);
        this.b.h.startActivity(intent);
    }
}
