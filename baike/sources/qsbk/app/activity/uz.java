package qsbk.app.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import com.tencent.connect.common.Constants;
import qsbk.app.R;
import qsbk.app.widget.BaseGroupDialog;

class uz extends BaseGroupDialog {
    final /* synthetic */ String a;
    final /* synthetic */ Context b;
    final /* synthetic */ MyInfoActivity c;

    uz(MyInfoActivity myInfoActivity, Context context, String str, Context context2) {
        this.c = myInfoActivity;
        this.a = str;
        this.b = context2;
        super(context);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.reset_remark_view);
        EditText editText = (EditText) findViewById(R.id.reset_remark);
        TextView textView = (TextView) findViewById(R.id.left_count);
        if (TextUtils.isEmpty(this.a)) {
            textView.setText(Constants.VIA_SHARE_TYPE_PUBLISHVIDEO);
        } else {
            editText.setText(this.a);
            editText.setSelection(editText.getText().length());
            textView.setText(String.valueOf(8 - this.a.length()));
        }
        editText.addTextChangedListener(new va(this, textView));
        findViewById(R.id.cancel).setOnClickListener(new vb(this));
        findViewById(R.id.submit).setOnClickListener(new vc(this, editText));
    }
}
