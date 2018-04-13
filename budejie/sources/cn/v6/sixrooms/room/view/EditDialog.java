package cn.v6.sixrooms.room.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.widget.EditText;
import android.widget.TextView;
import cn.v6.sixrooms.R;

public class EditDialog extends Dialog {
    private Callback a;
    private TextView b;
    private EditText c;
    private TextView d;
    private TextView e;

    public interface Callback {
        void cancle();

        void ok();
    }

    public EditDialog(Context context) {
        super(context, R.style.EditDialog);
    }

    public void setCallback(Callback callback) {
        this.a = callback;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().requestFeature(1);
        setContentView(R.layout.phone_dialog_input_text_gift);
        this.b = (TextView) findViewById(R.id.title);
        this.c = (EditText) findViewById(R.id.et_input);
        this.d = (TextView) findViewById(R.id.cancel);
        this.e = (TextView) findViewById(R.id.ok);
        this.d.setOnClickListener(new d(this));
        this.e.setOnClickListener(new e(this));
        this.c.setFilters(new InputFilter[]{new LengthFilter(15)});
        setCanceledOnTouchOutside(false);
        this.c.post(new c(this));
    }

    public String getInputText() {
        if (this.c != null) {
            return this.c.getText().toString().trim();
        }
        return "";
    }

    public void setTitle(String str) {
        if (this.b != null) {
            this.b.setText(str);
        }
    }
}
