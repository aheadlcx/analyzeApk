package qsbk.app.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import qsbk.app.R;

public class ShareItem extends LinearLayout {
    private static final int[] a = new int[]{16843087, 16843033};
    private TextView b;
    private ImageView c;

    public ShareItem(Context context) {
        this(context, null);
    }

    public ShareItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    @TargetApi(21)
    public ShareItem(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a(context, attributeSet);
    }

    @TargetApi(11)
    public ShareItem(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        if (this.b == null) {
            setOrientation(1);
            LayoutInflater.from(context).inflate(R.layout.share_item, this, true);
            this.b = (TextView) findViewById(R.id.title);
            this.c = (ImageView) findViewById(R.id.icon);
            if (attributeSet != null) {
                TypedArray obtainStyledAttributes = context.obtainStyledAttributes(a);
                setText(obtainStyledAttributes.getString(0));
                setImageDrawable(obtainStyledAttributes.getDrawable(1));
                obtainStyledAttributes.recycle();
            }
        }
    }

    public void setText(String str) {
        this.b.setText(str);
    }

    public void setImageDrawable(Drawable drawable) {
        this.c.setImageDrawable(drawable);
    }

    public void setImageResource(int i) {
        this.c.setImageResource(i);
    }
}
