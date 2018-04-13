package qsbk.app.live.widget;

import android.content.Context;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import qsbk.app.core.widget.BaseDialog;
import qsbk.app.live.R;
import qsbk.app.live.ui.family.FamilyDetailActivity;

public class FamilyGatherDialog extends BaseDialog {
    FamilyDetailActivity c;
    private ImageView d;
    private TextView e;
    private EditText f;

    public FamilyGatherDialog(Context context, FamilyDetailActivity familyDetailActivity) {
        super(context);
        this.c = familyDetailActivity;
    }

    protected int c() {
        return R.layout.dialog_familygather;
    }

    protected void d() {
        this.d = (ImageView) a(R.id.iv_close);
        this.e = (TextView) a(R.id.tv_publish);
        this.f = (EditText) a(R.id.et_publish);
        this.d.setOnClickListener(new af(this));
    }

    protected void e() {
        this.e.setOnClickListener(new ag(this));
    }

    protected float f() {
        return 0.5f;
    }

    protected int a() {
        return 17;
    }
}
