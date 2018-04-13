package cn.v6.sixrooms.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import cn.v6.sixrooms.R;

public class ImprovedDialogShopCarPrivate extends Dialog implements OnClickListener {
    private TextView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private a e;
    private ImageView f;

    interface a {
        void a();

        void b();
    }

    public ImprovedDialogShopCarPrivate(Context context, boolean z, OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
    }

    public ImprovedDialogShopCarPrivate(Context context, int i) {
        super(context, i);
        getWindow().requestFeature(1);
        setContentView(R.layout.phone_dialog_shop_car);
        this.f = (ImageView) findViewById(R.id.iv_toTheLeftOf_content);
        this.a = (TextView) findViewById(R.id.title);
        this.b = (TextView) findViewById(R.id.content);
        this.c = (TextView) findViewById(R.id.cancel);
        this.d = (TextView) findViewById(R.id.ok);
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        setCanceledOnTouchOutside(false);
    }

    public ImprovedDialogShopCarPrivate(Context context) {
        this(context, R.style.ImprovedDialog);
    }

    public void setImprovedTitle(CharSequence charSequence) {
        this.a.setText(charSequence);
    }

    public void setImprovedContent(CharSequence charSequence) {
        this.b.setText(charSequence);
    }

    public void setImprovedConfirmText(CharSequence charSequence) {
        this.d.setText(charSequence);
    }

    public void setImprovedCancelText(CharSequence charSequence) {
        this.c.setText(charSequence);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.cancel) {
            if (this.e != null) {
                this.e.b();
            }
            dismiss();
        } else if (id == R.id.ok) {
            if (this.e != null) {
                this.e.a();
            }
            dismiss();
        }
    }

    public void setImprovedDialogListener(a aVar) {
        this.e = aVar;
    }

    public void setImprovedImageResource(int i) {
        this.f.setImageResource(i);
    }
}
