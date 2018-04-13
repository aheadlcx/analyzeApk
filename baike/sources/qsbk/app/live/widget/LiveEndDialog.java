package qsbk.app.live.widget;

import android.content.Context;
import android.view.View.OnClickListener;
import android.widget.Button;
import qsbk.app.core.widget.BaseDialog;
import qsbk.app.live.R;

public abstract class LiveEndDialog extends BaseDialog {
    protected Button c;
    protected OnClickListener d;

    public LiveEndDialog(Context context, OnClickListener onClickListener) {
        super(context, R.style.SimpleDialog_Fullscreen);
        this.d = onClickListener;
    }

    protected int a() {
        return 17;
    }

    protected boolean g() {
        return false;
    }

    protected void d() {
        this.c = (Button) findViewById(R.id.btn_confirm);
        this.c.setOnClickListener(this.d);
        setOnDismissListener(new ha(this));
        setOnCancelListener(new hb(this));
    }
}
