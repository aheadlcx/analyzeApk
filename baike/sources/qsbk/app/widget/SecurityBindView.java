package qsbk.app.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import qsbk.app.R;

public class SecurityBindView extends RelativeLayout {
    public static final int STATUS_BINDED = 3;
    public static final String[] STATUS_DESCRIPTION = new String[]{"", "未绑定", "未验证", "已绑定", "已验证"};
    public static final int STATUS_NOT_BIND = 1;
    public static final int STATUS_NOT_VERIFY = 2;
    public static final int STATUS_VERIFIED = 4;
    private ImageView a;
    private TextView b;
    private TextView c;
    private ImageView d;
    private int e;
    private ColorStateList f;
    private View g;

    public SecurityBindView(Context context) {
        this(context, null);
    }

    public SecurityBindView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SecurityBindView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = 0;
        a(context, attributeSet, i);
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        setMinimumHeight(context.getResources().getDimensionPixelSize(R.dimen.list_item_height_small));
        LayoutInflater.from(context).inflate(R.layout.security_bind_view, this, true);
        this.a = (ImageView) findViewById(R.id.icon);
        this.b = (TextView) findViewById(R.id.main);
        this.c = (TextView) findViewById(R.id.status);
        this.d = (ImageView) findViewById(R.id.ic_status);
        this.g = findViewById(R.id.tip);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SecurityBind, 0, 0);
        Drawable drawable = obtainStyledAttributes.getDrawable(0);
        if (drawable != null) {
            this.a.setVisibility(0);
            this.a.setImageDrawable(drawable);
        } else {
            this.a.setVisibility(8);
        }
        CharSequence string = obtainStyledAttributes.getString(2);
        this.f = this.c.getTextColors();
        if (!TextUtils.isEmpty(string)) {
            this.c.setText(string);
        }
        string = obtainStyledAttributes.getString(1);
        if (!TextUtils.isEmpty(string)) {
            this.b.setText(string);
        }
        setStatus(obtainStyledAttributes.getInt(2, 1));
        obtainStyledAttributes.recycle();
    }

    public void setIcon(Drawable drawable) {
        this.a.setImageDrawable(drawable);
    }

    public void setIcon(int i) {
        this.a.setImageResource(i);
    }

    public CharSequence getMainText() {
        return this.b.getText();
    }

    public void setMainText(String str) {
        this.b.setText(str);
    }

    public void setStatus(int i) {
        if (i < 1 || i > STATUS_DESCRIPTION.length - 1) {
            throw new IllegalArgumentException("Wrong status");
        } else if (this.e != i) {
            this.e = i;
            setDescription(STATUS_DESCRIPTION[i]);
            switch (this.e) {
                case 1:
                case 2:
                    this.c.setTextColor(this.f);
                    setStatusIconVisibility(8);
                    this.d.setImageDrawable(null);
                    return;
                case 3:
                case 4:
                    this.c.setTextColor(getResources().getColor(R.color.security_verified));
                    setStatusIconVisibility(0);
                    this.d.setImageResource(R.drawable.ic_security_binded);
                    return;
                default:
                    return;
            }
        }
    }

    public void setDescription(String str) {
        this.c.setText(str);
    }

    public void setStatusIcon(int i) {
        this.d.setImageResource(i);
    }

    public void setStatusIconVisibility(int i) {
        this.d.setVisibility(i);
    }

    public void setStatusIcon(Drawable drawable) {
        this.d.setImageDrawable(drawable);
    }

    public void setTipVisibility(boolean z) {
        this.g.setVisibility(z ? 0 : 8);
    }
}
