package qsbk.app.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.utils.LogUtil;

public class ButtonBar extends RelativeLayout {
    private ViewGroup a;
    private ViewGroup b;
    private ImageView c;
    private ImageView d;
    private TextView e;
    private TextView f;

    public ButtonBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.widget_button_bar, this, true);
        a(context, attributeSet);
    }

    public ButtonBar setOnClickListener(OnClickListener onClickListener, OnClickListener onClickListener2) {
        this.a.setOnClickListener(onClickListener);
        this.b.setOnClickListener(onClickListener2);
        return this;
    }

    public ButtonBar setLeftTxt(String str) {
        this.e.setText(str);
        return this;
    }

    public ButtonBar setRightTxt(String str) {
        this.f.setText(str);
        return this;
    }

    private void a(Context context, AttributeSet attributeSet) {
        this.a = (ViewGroup) findViewById(R.id.id_layout_left);
        this.b = (ViewGroup) findViewById(R.id.id_layout_right);
        this.c = (ImageView) findViewById(R.id.id_img_left);
        this.d = (ImageView) findViewById(R.id.id_img_right);
        this.e = (TextView) findViewById(R.id.id_txt_left);
        this.f = (TextView) findViewById(R.id.id_txt_right);
        LogUtil.d("button bar widget");
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ButtonBar, 0, 0);
        int color = obtainStyledAttributes.getColor(1, -1);
        LogUtil.d("background color:" + color);
        if (color != -1) {
            setBackgroundColor(color);
        }
        Object string = obtainStyledAttributes.getString(3);
        if (!TextUtils.isEmpty(string)) {
            setLeftTxt(string);
        }
        string = obtainStyledAttributes.getString(4);
        if (!TextUtils.isEmpty(string)) {
            setRightTxt(string);
        }
        Drawable drawable = obtainStyledAttributes.getDrawable(0);
        if (drawable != null) {
            this.c.setImageDrawable(drawable);
        }
        drawable = obtainStyledAttributes.getDrawable(2);
        if (drawable != null) {
            this.d.setImageDrawable(drawable);
        }
        obtainStyledAttributes.recycle();
    }
}
