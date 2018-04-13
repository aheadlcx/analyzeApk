package cn.v6.sixrooms.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import cn.v6.sixrooms.R;

public class ImprovedDialog extends Dialog implements OnClickListener {
    public static final int gravity_center = 1;
    public static final int gravity_left = 2;
    public static final int type_one_button = 1;
    public static final int type_two_button = 2;
    private TextView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private a e;
    private int f;
    private int g;
    private ImageView h;
    private ImprovedDialogCheckboxListener i;
    private CheckBox j;

    public interface ImprovedDialogCheckboxListener {
        void onClickCheckbox(boolean z);
    }

    interface a {
        void a();

        void b();
    }

    public ImprovedDialog(Context context, boolean z, OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
    }

    public ImprovedDialog(Context context, int i, int i2, int i3) {
        super(context, i);
        this.f = i2;
        this.g = i3;
        getWindow().requestFeature(1);
        setContentView(R.layout.phone_dialog_improved);
        this.a = (TextView) findViewById(R.id.title);
        this.b = (TextView) findViewById(R.id.content);
        this.c = (TextView) findViewById(R.id.cancel);
        this.d = (TextView) findViewById(R.id.ok);
        this.j = (CheckBox) findViewById(R.id.id_check_box);
        this.h = (ImageView) findViewById(R.id.onePxLineVertical);
        if (this.f == 1) {
            this.c.setVisibility(8);
            this.h.setVisibility(8);
            this.d.setBackgroundResource(R.drawable.rooms_third_dialog_center_button_selecter);
        }
        if (this.g == 2) {
            this.b.setGravity(3);
        }
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.j.setOnClickListener(this);
        setCanceledOnTouchOutside(false);
    }

    public ImprovedDialog(Context context, int i, int i2) {
        this(context, R.style.ImprovedDialog, i, i2);
        if (getContext().getResources().getConfiguration().orientation == 2) {
            getWindow().addFlags(1024);
        }
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
        } else if (id == R.id.id_check_box && this.i != null) {
            this.i.onClickCheckbox(this.j.isChecked());
        }
    }

    public void setImprovedDialogListener(a aVar) {
        this.e = aVar;
    }

    public void setImprovedDialogCheckboxListener(ImprovedDialogCheckboxListener improvedDialogCheckboxListener) {
        this.i = improvedDialogCheckboxListener;
    }

    public void setCheckboxVisibility(boolean z) {
        if (z) {
            this.j.setVisibility(0);
        }
    }
}
