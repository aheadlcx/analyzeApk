package com.budejie.www.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.budejie.www.R;
import com.lt.a.a.a;

public class e extends Dialog implements OnClickListener {
    private static Context a;
    private static ViewGroup b;
    private ImageView c;
    private TextView d;
    private TextView e;
    private String f;

    public e(Context context, int i, String str) {
        super(context, i);
        a = context;
        this.f = str;
        a();
    }

    private void a() {
        b = (ViewGroup) LayoutInflater.from(a).inflate(R.layout.open_free_flow_dialog_layout, null);
        this.c = (ImageView) b.findViewById(R.id.open_free_flow_close);
        this.d = (TextView) b.findViewById(R.id.open_free_flow_buy);
        this.e = (TextView) b.findViewById(R.id.open_free_flow_activation);
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
        setContentView(b);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.open_free_flow_close:
                dismiss();
                return;
            case R.id.open_free_flow_buy:
            case R.id.open_free_flow_activation:
                a.a((Activity) a, this.f);
                dismiss();
                return;
            default:
                return;
        }
    }
}
