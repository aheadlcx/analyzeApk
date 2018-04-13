package qsbk.app.im;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.utils.UIHelper;

public class ConversationTitleBar extends RelativeLayout {
    private ImageView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private ImageButton e;
    private View f;
    private ImageButton g;

    public ConversationTitleBar(Context context) {
        super(context);
        a();
    }

    public ConversationTitleBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    @TargetApi(11)
    public ConversationTitleBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    @TargetApi(21)
    public ConversationTitleBar(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a();
    }

    private void a() {
        LayoutInflater.from(getContext()).inflate(R.layout.abc_custom_coversation, this, true);
        this.a = (ImageView) findViewById(R.id.abc_up);
        this.b = (TextView) findViewById(R.id.abc_leftTitle);
        this.c = (TextView) findViewById(R.id.abc_centerTitle);
        this.d = (TextView) findViewById(R.id.abc_subTitle);
        this.e = (ImageButton) findViewById(R.id.abc_menuItem);
        this.g = (ImageButton) findViewById(R.id.abc_menuItem0);
        this.f = findViewById(R.id.abc_homeIcContainer);
        b();
        this.f.setOnClickListener(new dc(this));
    }

    private void b() {
        int color;
        int i;
        int i2;
        Resources resources = getResources();
        if (UIHelper.isNightTheme()) {
            color = resources.getColor(R.color.ab_title_text_color_dark);
            i = R.drawable.ic_arrow_left_padding_dark;
            i2 = R.drawable.session_profile_dark;
        } else {
            color = resources.getColor(R.color.g_txt_big);
            i = R.drawable.ic_arrow_left_padding;
            i2 = R.drawable.session_profile;
        }
        this.a.setImageResource(i);
        this.a.setEnabled(false);
        this.b.setTextColor(color);
        this.b.setEnabled(false);
        this.c.setTextColor(color);
        this.e.setImageResource(i2);
    }

    public void setMenuItemResource(int i) {
        if (this.e != null) {
            this.e.setImageResource(i);
        }
    }

    public void setMenuItem0Resource(int i) {
        if (this.g != null) {
            this.g.setImageResource(i);
        }
    }

    public void setLeftText(String str) {
        this.b.setText(str);
    }

    public void setCenterText(String str) {
        this.c.setText(str);
    }

    public void setSubTitle(CharSequence charSequence) {
        if (this.d != null) {
            this.d.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
            this.d.setText(charSequence);
        }
    }

    public void setUpIndicatorListener(OnClickListener onClickListener) {
        this.f.setOnClickListener(onClickListener);
    }

    public void setMenuItemClickListener(OnClickListener onClickListener) {
        this.e.setOnClickListener(onClickListener);
    }

    public void setMenuItem0ClickListener(OnClickListener onClickListener) {
        this.g.setOnClickListener(onClickListener);
    }

    public void setMenuItemVisible(boolean z) {
        if (z) {
            this.e.setVisibility(0);
        } else {
            this.e.setVisibility(8);
        }
    }

    public void setMenuItem0Visible(boolean z) {
        if (z) {
            this.g.setVisibility(0);
        } else {
            this.g.setVisibility(8);
        }
    }
}
