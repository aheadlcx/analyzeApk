package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.live.R;

class id implements OnClickListener {
    final /* synthetic */ RedEnvelopesResultDialog a;

    id(RedEnvelopesResultDialog redEnvelopesResultDialog) {
        this.a = redEnvelopesResultDialog;
    }

    public void onClick(View view) {
        this.a.e.setVisibility(8);
        this.a.f.setVisibility(0);
        this.a.d.setText(R.string.live_red_envelopes_title);
        this.a.j.setVisibility(8);
    }
}
