package com.budejie.www.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import com.budejie.www.R;

public class LoadingView extends LinearLayout {
    Handler a = new Handler(this) {
        final /* synthetic */ LoadingView a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 0:
                    if (this.a.c >= 100) {
                        this.a.b = -Math.abs(this.a.b);
                    } else if (this.a.c <= 0) {
                        this.a.b = Math.abs(this.a.b);
                    }
                    this.a.c = this.a.c + this.a.b;
                    this.a.g.setProgress(this.a.c);
                    this.a.h.setProgress(this.a.c);
                    if (!this.a.d) {
                        this.a.a.sendEmptyMessageDelayed(0, 10);
                        return;
                    }
                    return;
                case 1:
                    this.a.d = true;
                    this.a.f.setVisibility(8);
                    return;
                default:
                    return;
            }
        }
    };
    private int b = 4;
    private int c = 0;
    private boolean d;
    private Context e;
    private LinearLayout f;
    private ProgressBar g;
    private ProgressBar h;

    public LoadingView(Context context) {
        super(context);
        a(context);
    }

    public LoadingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public LoadingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        this.e = context;
        this.f = (LinearLayout) LayoutInflater.from(this.e).inflate(R.layout.loading_view_layout, null);
        this.g = (ProgressBar) this.f.findViewById(R.id.progress_left);
        this.h = (ProgressBar) this.f.findViewById(R.id.progress_right);
        addView(this.f, new LayoutParams(-1, -1));
    }

    public void a() {
        this.d = false;
        this.f.setVisibility(0);
        this.a.sendEmptyMessage(0);
    }

    public void b() {
        this.a.sendEmptyMessageDelayed(1, 100);
    }
}
