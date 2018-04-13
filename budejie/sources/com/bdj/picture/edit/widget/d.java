package com.bdj.picture.edit.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.EditText;
import android.widget.ImageButton;
import com.bdj.picture.edit.a.e;
import com.bdj.picture.edit.a.h;
import com.bdj.picture.edit.a.i;
import java.util.Timer;

public class d extends Dialog implements OnClickListener {
    public EditText a;
    private Context b;
    private ImageButton c;
    private a d;

    public interface a {
        void a(String str);
    }

    public d(Context context) {
        super(context, i.Dialog_edittext);
        this.b = context;
        a();
    }

    private void a() {
        setContentView(LayoutInflater.from(this.b).inflate(e.dialog_edittext_view, null));
        this.a = (EditText) findViewById(com.bdj.picture.edit.a.d.editText);
        this.c = (ImageButton) findViewById(com.bdj.picture.edit.a.d.editTextCommit);
        this.c.setOnClickListener(this);
        Window window = getWindow();
        window.addFlags(2);
        LayoutParams attributes = window.getAttributes();
        attributes.gravity = 80;
        attributes.height = -2;
        attributes.width = -1;
        window.setAttributes(attributes);
        setCanceledOnTouchOutside(true);
    }

    public void a(a aVar) {
        this.d = aVar;
    }

    public void a(String str) {
        if (str != null) {
            if (this.b.getResources().getString(h.click_input_text).equals(str)) {
                this.a.setText("");
            } else {
                this.a.setText(str);
                this.a.setSelection(str.length());
            }
        }
        show();
        this.a.setFocusable(true);
        this.a.setFocusableInTouchMode(true);
        this.a.requestFocus();
        new Timer().schedule(new d$1(this), 200);
    }

    public void onClick(View view) {
        if (view == this.c) {
            String obj = this.a.getText().toString();
            if (this.d != null) {
                this.d.a(obj);
            }
            dismiss();
        }
    }
}
