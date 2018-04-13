package qsbk.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import qsbk.app.R;

public class DefaultNoContentView extends RelativeLayout {
    private ImageView a;
    private TextView b;

    public DefaultNoContentView(Context context) {
        super(context);
        a(context);
    }

    public DefaultNoContentView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public DefaultNoContentView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.default_no_content_view, this);
        this.b = (TextView) findViewById(R.id.text);
        this.a = (ImageView) findViewById(R.id.image);
    }

    public void setText(String str) {
        this.b.setText(str);
    }

    public void setImageResource(int i) {
        this.a.setImageResource(i);
    }
}
