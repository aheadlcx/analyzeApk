package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.live.R;
import qsbk.app.live.adapter.SendRedEnvelopesAdapter;

class iq implements OnClickListener {
    final /* synthetic */ SendRedEnvelopesDialog a;

    iq(SendRedEnvelopesDialog sendRedEnvelopesDialog) {
        this.a = sendRedEnvelopesDialog;
    }

    public void onClick(View view) {
        String obj = this.a.i.getText().toString();
        if (obj.length() > 10) {
            ToastUtil.Short(R.string.live_red_envelopes_pwd_max_len);
            return;
        }
        if (this.a.k.onSend(obj, ((SendRedEnvelopesAdapter) this.a.h.getAdapter()).getSelectedItem())) {
            this.a.dismiss();
        }
    }
}
