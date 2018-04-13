package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.SimpleWebActivity;

class ao implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ aa b;

    ao(aa aaVar, String str) {
        this.b = aaVar;
        this.a = str;
    }

    public void onClick(View view) {
        SimpleWebActivity.launch(view.getContext(), this.a, "良心夺宝");
    }
}
