package qsbk.app.live.widget;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import qsbk.app.core.widget.BaseDialog;
import qsbk.app.live.R;

public class FamilyCardDialog extends BaseDialog {
    private int c;
    private ImageView d;
    private TextView e;

    public FamilyCardDialog(Context context, int i) {
        super(context);
        this.c = i;
    }

    protected int c() {
        return R.layout.dialog_card_exp;
    }

    protected void d() {
        this.e = (TextView) a(R.id.tv_exp);
        this.d = (ImageView) a(R.id.iv_close);
    }

    protected void e() {
        this.e.setText(getContext().getString(R.string.family_card_get_exp, new Object[]{this.c + ""}));
        this.d.setOnClickListener(new x(this));
    }

    protected int a() {
        return 17;
    }

    protected float f() {
        return 0.5f;
    }
}
