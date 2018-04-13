package qsbk.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import qsbk.app.R;

public class LoadingLayout extends FrameLayout {
    private OnLoadingClickListener a;
    private ImageView b;
    private TextView c;
    private View d;
    private boolean e = false;

    public interface OnLoadingClickListener {
        void onLoadingClick();
    }

    public LoadingLayout(Context context) {
        super(context);
        a(context);
    }

    public LoadingLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public LoadingLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        inflate(context, R.layout.loading_layout, this);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.b = (ImageView) findViewById(R.id.image);
        this.c = (TextView) findViewById(R.id.text);
        this.d = findViewById(R.id.progress);
        setOnClickListener(new cn(this));
    }

    public void onLoading() {
        this.d.setVisibility(0);
        this.c.setVisibility(8);
        this.b.setVisibility(8);
        setVisibility(0);
    }

    public void show(CharSequence charSequence, boolean z) {
        show(charSequence, R.drawable.fail_img, z);
    }

    public void show(CharSequence charSequence, int i) {
        show(charSequence, i, false);
    }

    public void show(CharSequence charSequence, int i, boolean z) {
        this.d.setVisibility(8);
        this.b.setVisibility(0);
        this.c.setVisibility(0);
        if (i < 0) {
            this.b.setVisibility(8);
        } else {
            ImageView imageView = this.b;
            if (i == 0) {
                i = R.drawable.fail_img;
            }
            imageView.setImageResource(i);
        }
        this.c.setText(charSequence);
        this.e = z;
        setVisibility(0);
    }

    public void hide() {
        setVisibility(8);
    }

    public void setRefreshable(boolean z) {
        this.e = z;
    }

    public void setOnLoadingClickListener(OnLoadingClickListener onLoadingClickListener) {
        this.a = onLoadingClickListener;
    }
}
