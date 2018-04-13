package cn.v6.sixrooms.widgets.phone;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import cn.v6.sixrooms.R;

public class HideOrDisplayThePasswordView extends FrameLayout {
    private Context a;
    private View b;
    private ImageView c;
    private CheckBox d;
    private View e;
    private OnHideOrDisplayListener f;
    private FrameLayout g;

    public interface OnHideOrDisplayListener {
        void clickCleanButton();

        void isShowPassword(boolean z);
    }

    public void setOnHideOrDisplayListener(OnHideOrDisplayListener onHideOrDisplayListener) {
        this.f = onHideOrDisplayListener;
    }

    public HideOrDisplayThePasswordView(Context context) {
        super(context);
        this.a = context;
        a();
    }

    public HideOrDisplayThePasswordView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = context;
        a();
    }

    private void a() {
        this.b = LayoutInflater.from(this.a).inflate(R.layout.custom_hide_or_display_the_password_layout, this, false);
        addView(this.b);
        this.c = (ImageView) findViewById(R.id.id_iv_clean_password);
        this.e = findViewById(R.id.id_clean_tag);
        this.d = (CheckBox) findViewById(R.id.id_iv_show_or_hide_password);
        this.g = (FrameLayout) findViewById(R.id.id_fl_show_or_hide_password);
        this.c.setOnClickListener(new t(this));
        this.g.setOnClickListener(new u(this));
        hideCleanTag();
    }

    public void showCleanTag() {
        this.c.setVisibility(0);
        this.e.setVisibility(0);
    }

    public void hideCleanTag() {
        this.c.setVisibility(4);
        this.e.setVisibility(4);
    }
}
