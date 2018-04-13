package qsbk.app.core.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import qsbk.app.core.R;

public class SimpleDialog extends Dialog {
    private Context a;
    private TextView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private View f;
    private RadioGroup g;

    public static class Builder implements qsbk.app.core.widget.DialogFragment.Builder {
        private Context a;
        private String b;
        private String c;
        private String d;
        private String e;
        private View f;
        private int g;
        private SimpleDialog h;
        private int i;
        private String[] j;
        private boolean k;

        public Builder(int i) {
            applyStyleId(i);
        }

        public Builder(Context context) {
            this(context, 0);
        }

        public Builder(Context context, int i) {
            this.a = context;
            this.g = i;
        }

        public void applyStyleId(int i) {
            this.g = i;
        }

        public SimpleDialog build(Context context) {
            if (this.h == null) {
                this.h = a(context, this.g);
            }
            return this.h;
        }

        private SimpleDialog a(Context context, int i) {
            SimpleDialog simpleDialog = new SimpleDialog(context, i);
            this.f = simpleDialog.getContent();
            simpleDialog.setTitle(this.b);
            simpleDialog.setMessage(this.c);
            simpleDialog.setPositiveText(this.d);
            simpleDialog.setNegativeText(this.e);
            simpleDialog.setItems(this.j, this.i);
            simpleDialog.setRadioButtonListener(new w(this));
            simpleDialog.setCancelable(this.k);
            return simpleDialog;
        }

        public void onDismiss(DialogInterface dialogInterface) {
            this.h.dismiss();
        }

        public void onCancel(DialogInterface dialogInterface) {
        }

        public void onNegativeActionClicked(DialogFragment dialogFragment) {
            dialogFragment.dismiss();
        }

        public void onPositiveActionClicked(DialogFragment dialogFragment) {
            dialogFragment.dismiss();
        }

        public void onNeutralActionClicked(DialogFragment dialogFragment) {
            dialogFragment.dismiss();
        }

        public Builder title(String str) {
            this.b = str;
            return this;
        }

        public Builder message(String str) {
            this.c = str;
            return this;
        }

        public Builder positiveAction(String str) {
            this.d = str;
            return this;
        }

        public Builder negativeAction(String str) {
            this.e = str;
            return this;
        }

        public View getContentView() {
            return this.f;
        }

        public Builder items(String[] strArr, int i) {
            this.j = strArr;
            this.i = i;
            return this;
        }

        public int getSelectedIndex() {
            return this.i;
        }

        public int getStyleId() {
            return this.g;
        }

        public Builder setCancelable(boolean z) {
            this.k = z;
            return this;
        }
    }

    public SimpleDialog(Context context) {
        this(context, R.style.SimpleDialog);
    }

    public SimpleDialog dimAmount(float f) {
        Window window = getWindow();
        if (f > 0.0f) {
            window.addFlags(2);
            LayoutParams attributes = window.getAttributes();
            attributes.dimAmount = f;
            window.setAttributes(attributes);
        } else {
            window.clearFlags(2);
        }
        return this;
    }

    public SimpleDialog(Context context, int i) {
        super(context, i);
        this.a = context;
        contentView(View.inflate(this.a, R.layout.simpledialog, null));
    }

    public void contentView(View view) {
        setContentView(view);
        this.f = view;
        this.b = (TextView) view.findViewById(R.id.title);
        this.c = (TextView) view.findViewById(R.id.message);
        this.d = (TextView) view.findViewById(R.id.positive);
        this.e = (TextView) view.findViewById(R.id.negative);
        this.g = (RadioGroup) view.findViewById(R.id.choice);
    }

    public View getContent() {
        return this.f;
    }

    public void setTitle(String str) {
        if (str != null && str.length() != 0) {
            this.b.setVisibility(0);
            this.b.setText(str);
        }
    }

    public void setMessage(String str) {
        if (str != null && str.length() != 0) {
            this.c.setVisibility(0);
            this.c.setText(str);
        }
    }

    public void setPositiveText(String str) {
        this.d.setVisibility(0);
        this.d.setText(str);
    }

    public void setNegativeText(String str) {
        this.e.setVisibility(0);
        this.e.setText(str);
    }

    public void setPositiveListener(OnClickListener onClickListener) {
        this.d.setOnClickListener(onClickListener);
    }

    public void setNegativeListener(OnClickListener onClickListener) {
        this.e.setOnClickListener(onClickListener);
    }

    public void setItems(String[] strArr, int i) {
        if (strArr != null && strArr.length != 0) {
            this.g.setVisibility(0);
            for (int i2 = 0; i2 < strArr.length; i2++) {
                View radioButton = new RadioButton(this.a);
                radioButton.setPadding(80, 0, 0, 0);
                radioButton.setText(strArr[i2]);
                radioButton.setTextColor(Color.parseColor("#8a000000"));
                radioButton.setTextSize(2, 15.0f);
                this.g.addView(radioButton, -1, -2);
                if (i == i2) {
                    this.g.check(radioButton.getId());
                }
            }
        }
    }

    public void setRadioButtonListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.g.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    public SimpleDialog title(String str) {
        setTitle(str);
        return this;
    }

    public SimpleDialog message(String str) {
        setMessage(str);
        return this;
    }

    public SimpleDialog positiveAction(String str) {
        setPositiveText(str);
        return this;
    }

    public SimpleDialog negativeAction(String str) {
        setNegativeText(str);
        return this;
    }

    public SimpleDialog positiveActionClickListener(OnClickListener onClickListener) {
        setPositiveListener(onClickListener);
        return this;
    }

    public SimpleDialog negativeActionClickListener(OnClickListener onClickListener) {
        setNegativeListener(onClickListener);
        return this;
    }

    public SimpleDialog cancelable(boolean z) {
        setCancelable(z);
        return this;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LayoutParams attributes = getWindow().getAttributes();
        attributes.height = -2;
        attributes.width = (int) (((double) getWindow().getWindowManager().getDefaultDisplay().getWidth()) * 0.8d);
        attributes.gravity = 17;
        getWindow().setAttributes(attributes);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        setCanceledOnTouchOutside(true);
        dimAmount(0.5f);
    }
}
