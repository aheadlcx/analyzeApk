package qsbk.app.live.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import qsbk.app.core.model.RedEnvelopes;
import qsbk.app.core.model.RedEnvelopesConfig;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.widget.BaseDialog;
import qsbk.app.live.R;
import qsbk.app.live.adapter.SendRedEnvelopesAdapter;

public class SendRedEnvelopesDialog extends BaseDialog {
    private View c;
    private TextView d;
    private TextView e;
    private View f;
    private View g;
    private RecyclerView h;
    private EditText i;
    private TextView j;
    private OnSendListener k;
    private RedEnvelopesConfig l;

    public interface OnSendListener {
        boolean onSend(String str, RedEnvelopes redEnvelopes);
    }

    public SendRedEnvelopesDialog(Context context, RedEnvelopesConfig redEnvelopesConfig) {
        super(context);
        this.l = redEnvelopesConfig;
    }

    protected int c() {
        return R.layout.live_send_red_envelopes_dialog;
    }

    protected void d() {
        this.c = a(R.id.iv_close);
        this.d = (TextView) a(R.id.tv_title);
        this.e = (TextView) a(R.id.tv_help);
        this.f = a(R.id.btn_send);
        this.g = a(R.id.ll_content);
        this.h = (RecyclerView) a(R.id.recyclerview);
        this.i = (EditText) a(R.id.tv_pwd);
        this.j = (TextView) a(R.id.tv_tips);
    }

    protected void e() {
        getWindow().setWindowAnimations(R.style.SimpleDialog_RedEnvelopes);
        this.c.setOnClickListener(new in(this));
        this.f.setOnClickListener(new iq(this));
        this.e.setText(AppUtils.getInstance().getAppContext().getString(R.string.live_red_envelopes_help, new Object[]{Float.valueOf(this.l.minPrecent)}));
        this.j.setText(AppUtils.getInstance().getAppContext().getString(R.string.live_red_envelopes_send_tips, new Object[]{Long.valueOf(this.l.globalMinCoin)}));
        this.h.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        this.h.setAdapter(new SendRedEnvelopesAdapter(getContext(), this.l.items));
        this.f.setEnabled(false);
        this.i.addTextChangedListener(new ir(this));
    }

    protected float b() {
        return 0.0f;
    }

    protected int a() {
        return 17;
    }

    protected float f() {
        return 0.6f;
    }

    public void setOnSendListener(OnSendListener onSendListener) {
        this.k = onSendListener;
    }
}
