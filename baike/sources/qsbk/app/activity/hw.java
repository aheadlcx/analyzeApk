package qsbk.app.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.widget.BaseGroupDialog;

class hw extends BaseGroupDialog {
    final /* synthetic */ String a;
    final /* synthetic */ CircleTopicActivity b;

    hw(CircleTopicActivity circleTopicActivity, Context context, String str) {
        this.b = circleTopicActivity;
        this.a = str;
        super(context);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.dialog_edit_text);
        EditText editText = (EditText) findViewById(R.id.edittext);
        TextView textView = (TextView) findViewById(R.id.left_count);
        editText.setText(this.a);
        editText.setSelection(this.a.length());
        textView.setText(String.valueOf(140 - this.a.length()));
        editText.addTextChangedListener(new hx(this, textView));
        findViewById(R.id.cancel).setOnClickListener(new hy(this));
        findViewById(R.id.submit).setOnClickListener(new hz(this, editText));
    }
}
