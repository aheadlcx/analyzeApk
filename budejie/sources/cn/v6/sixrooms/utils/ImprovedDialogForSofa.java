package cn.v6.sixrooms.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import cn.v6.sixrooms.R;

public class ImprovedDialogForSofa extends Dialog implements OnClickListener {
    public static final int gravity_center = 1;
    public static final int gravity_left = 2;
    public static final int type_one_button = 1;
    public static final int type_two_button = 2;
    private TextView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private ImprovedDialogListener e;
    private int f;
    private int g;
    private ImageView h;
    private EditText i;

    public interface ImprovedDialogListener {
        void OnCancelClick();

        boolean OnInputNull();

        boolean OnOkClick(String str);

        void dismisDialog();

        void showDialog();
    }

    public ImprovedDialogForSofa(Context context, boolean z, OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
    }

    public ImprovedDialogForSofa(Context context, int i, int i2, int i3) {
        super(context, i);
        this.f = i2;
        this.g = i3;
        getWindow().requestFeature(1);
        setContentView(R.layout.phone_dialog_improved_sofa);
        this.a = (TextView) findViewById(R.id.title);
        this.b = (TextView) findViewById(R.id.content);
        this.c = (TextView) findViewById(R.id.cancel);
        this.d = (TextView) findViewById(R.id.ok);
        this.i = (EditText) findViewById(R.id.et_num);
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
        setCanceledOnTouchOutside(false);
    }

    public ImprovedDialogForSofa(Context context, int i, int i2) {
        this(context, R.style.ImprovedDialogInput, i, i2);
        getWindow().setWindowAnimations(0);
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
                this.e.OnCancelClick();
            }
            dismiss();
        } else if (id == R.id.ok) {
            boolean z = false;
            if (this.e != null) {
                Object inputText = getInputText();
                if (TextUtils.isEmpty(inputText)) {
                    z = this.e.OnInputNull();
                } else {
                    z = this.e.OnOkClick(inputText);
                }
            }
            if (z) {
                dismiss();
            }
        }
    }

    public void setImprovedDialogListener(ImprovedDialogListener improvedDialogListener) {
        this.e = improvedDialogListener;
    }

    public String getInputText() {
        return this.i.getText().toString().trim();
    }

    public void dismiss() {
        if (this.e != null) {
            this.e.showDialog();
        }
        super.dismiss();
    }

    public void show() {
        if (this.e != null) {
            this.e.dismisDialog();
        }
        super.show();
    }
}
