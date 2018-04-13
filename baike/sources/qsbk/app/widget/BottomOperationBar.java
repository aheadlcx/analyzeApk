package qsbk.app.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.utils.LogUtil;

@Deprecated
public class BottomOperationBar extends RelativeLayout {
    private ViewGroup a;
    private ViewGroup b;
    private ViewGroup c;
    private View d;
    private View e;

    public BottomOperationBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.widget_bottom_operation_bar, this, true);
        a(context, attributeSet);
    }

    public BottomOperationBar setBtnClickListener(OnClickListener onClickListener, OnClickListener onClickListener2, OnClickListener onClickListener3) {
        this.a.setOnClickListener(onClickListener);
        this.b.setOnClickListener(onClickListener2);
        this.c.setOnClickListener(onClickListener3);
        return this;
    }

    public BottomOperationBar setBtnClickListener(OnClickListener onClickListener, OnClickListener onClickListener2) {
        return setBtnClickListener(onClickListener, onClickListener2, null);
    }

    public BottomOperationBar setBtnClickListener(OnClickListener onClickListener) {
        return setBtnClickListener(onClickListener, null, null);
    }

    private BottomOperationBar a(ViewGroup viewGroup, String str) {
        ((TextView) viewGroup.findViewById(R.id.id_txt)).setText(str);
        return this;
    }

    public BottomOperationBar setIcon(ViewGroup viewGroup, Drawable drawable) {
        ((ImageView) viewGroup.findViewById(R.id.id_icon)).setImageDrawable(drawable);
        return this;
    }

    private void a(Context context, AttributeSet attributeSet) {
        this.a = (ViewGroup) findViewById(R.id.button1);
        this.b = (ViewGroup) findViewById(R.id.button2);
        this.c = (ViewGroup) findViewById(R.id.button3);
        this.d = findViewById(R.id.divide1);
        this.e = findViewById(R.id.divide2);
        LogUtil.d("button bar widget");
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BottomOperationBar, 0, 0);
        String string = obtainStyledAttributes.getString(3);
        if (!TextUtils.isEmpty(string)) {
            a(this.a, string);
        }
        Drawable drawable = obtainStyledAttributes.getDrawable(0);
        if (drawable != null) {
            setIcon(this.a, drawable);
        }
        string = obtainStyledAttributes.getString(4);
        if (!TextUtils.isEmpty(string)) {
            a(this.b, string);
            this.b.setVisibility(0);
            this.d.setVisibility(0);
        }
        drawable = obtainStyledAttributes.getDrawable(1);
        if (drawable != null) {
            setIcon(this.b, drawable);
        }
        string = obtainStyledAttributes.getString(5);
        if (!TextUtils.isEmpty(string)) {
            a(this.c, string);
            this.c.setVisibility(0);
            this.e.setVisibility(0);
        }
        drawable = obtainStyledAttributes.getDrawable(2);
        if (drawable != null) {
            setIcon(this.c, drawable);
        }
        obtainStyledAttributes.recycle();
    }
}
