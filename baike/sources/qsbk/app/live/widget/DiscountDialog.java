package qsbk.app.live.widget;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.widget.BaseDialog;
import qsbk.app.live.R;

public class DiscountDialog extends BaseDialog {
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private SimpleDraweeView j;
    private TextView k;
    private SimpleDraweeView l;
    private TextView m;
    private TextView n;
    private SimpleDraweeView o;
    private TextView p;
    private TextView q;
    private ClickListener r;

    public interface ClickListener {
        void onClick(View view);
    }

    public DiscountDialog(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        super(context);
        this.c = str;
        this.d = str2;
        this.e = str3;
        this.f = str4;
        this.g = str5;
        this.h = str6;
        this.i = str7;
    }

    protected int c() {
        return R.layout.dialog_discount;
    }

    protected void d() {
        this.j = (SimpleDraweeView) a(R.id.iv_diamond);
        this.k = (TextView) a(R.id.tv_diamond);
        this.l = (SimpleDraweeView) a(R.id.iv_enter);
        this.n = (TextView) a(R.id.tv_enter);
        this.m = (TextView) a(R.id.tv_enter_count);
        this.o = (SimpleDraweeView) a(R.id.iv_gift);
        this.p = (TextView) a(R.id.tv_gift);
        this.q = (TextView) a(R.id.tv_pay);
        this.q.setOnClickListener(new o(this));
    }

    public void setClickListener(ClickListener clickListener) {
        this.r = clickListener;
    }

    protected void e() {
        AppUtils.getInstance().getImageProvider().loadAvatar(this.j, this.c, true);
        AppUtils.getInstance().getImageProvider().loadAvatar(this.l, this.e, true);
        AppUtils.getInstance().getImageProvider().loadAvatar(this.o, this.h, true);
        this.k.setText(this.d);
        this.n.setText(this.g);
        this.m.setText(this.f);
        this.p.setText(this.i);
    }

    protected int a() {
        return 17;
    }

    protected float f() {
        return 0.3f;
    }
}
