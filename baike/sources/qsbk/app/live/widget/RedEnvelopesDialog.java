package qsbk.app.live.widget;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Map;
import qsbk.app.core.model.User;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.ConfigInfoUtil;
import qsbk.app.core.widget.BaseDialog;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveRedEnvelopes;
import qsbk.app.live.model.LiveRedEnvelopesMessage;
import qsbk.app.live.model.LiveUser;

public class RedEnvelopesDialog extends BaseDialog {
    private ImageView c;
    private TextView d;
    private View e;
    private TextView f;
    private View g;
    private LiveRedEnvelopesMessage h;
    private OnSendListener i;

    public interface OnSendListener {
        void onAvartarClick(User user);

        boolean onSend(String str, long j);
    }

    public RedEnvelopesDialog(Context context, LiveRedEnvelopesMessage liveRedEnvelopesMessage) {
        super(context);
        this.h = liveRedEnvelopesMessage;
    }

    protected int c() {
        return R.layout.live_red_envelopes_dialog;
    }

    protected void d() {
        this.c = (ImageView) a(R.id.iv_avatar);
        this.d = (TextView) a(R.id.tv_name);
        this.e = a(R.id.iv_close);
        this.f = (TextView) a(R.id.tv_pwd);
        this.g = a(R.id.btn_send);
    }

    protected void e() {
        getWindow().setWindowAnimations(R.style.SimpleDialog_RedEnvelopes);
        LiveRedEnvelopes redEnvelopes = this.h.getRedEnvelopes();
        LiveUser liveUser = redEnvelopes.user;
        if (liveUser != null) {
            Map template = ConfigInfoUtil.instance().getTemplate();
            if (!(template == null || !template.containsKey(liveUser.t) || liveUser.av.startsWith("http"))) {
                liveUser.av = ((String) template.get(liveUser.t)).replace("$", liveUser.av);
            }
            AppUtils.getInstance().getImageProvider().loadAvatar(this.c, liveUser.av, true);
            this.d.setText(liveUser.n);
        }
        this.f.setText(redEnvelopes.pwd);
        this.c.setOnClickListener(new hy(this, redEnvelopes));
        this.e.setOnClickListener(new hz(this));
        this.g.setOnClickListener(new ia(this, redEnvelopes));
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
        this.i = onSendListener;
    }
}
