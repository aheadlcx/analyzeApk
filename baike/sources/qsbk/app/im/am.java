package qsbk.app.im;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

class am implements OnClickListener {
    final /* synthetic */ aa a;

    am(aa aaVar) {
        this.a = aaVar;
    }

    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setClassName("qsbk.app", "qsbk.app.nearby.ui.InfoCompleteActivity");
        this.a.a.h.startActivity(intent);
    }
}
