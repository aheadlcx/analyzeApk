package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.live.R;

class ic implements OnClickListener {
    final /* synthetic */ RedEnvelopesResultDialog a;

    ic(RedEnvelopesResultDialog redEnvelopesResultDialog) {
        this.a = redEnvelopesResultDialog;
    }

    public void onClick(View view) {
        this.a.e.setVisibility(0);
        this.a.f.setVisibility(8);
        this.a.d.setText(R.string.live_red_envelopes_title_success);
        this.a.j.setVisibility(0);
        this.a.j();
    }
}
