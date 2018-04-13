package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.SimpleWebActivity;

class o implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ a b;

    o(a aVar, String str) {
        this.b = aVar;
        this.a = str;
    }

    public void onClick(View view) {
        SimpleWebActivity.launch(view.getContext(), this.a, "");
    }
}
