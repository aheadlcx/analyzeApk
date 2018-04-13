package qsbk.app.activity;

import android.app.ProgressDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import com.alipay.sdk.cons.b;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.http.SimpleHttpTask;

class dt implements OnClickListener {
    final /* synthetic */ EditText a;
    final /* synthetic */ TextView b;
    final /* synthetic */ ChangeGroupNameActivity c;

    dt(ChangeGroupNameActivity changeGroupNameActivity, EditText editText, TextView textView) {
        this.c = changeGroupNameActivity;
        this.a = editText;
        this.b = textView;
    }

    public void onClick(View view) {
        String trim = this.a.getText().toString().trim();
        if (trim.length() == 0) {
            this.b.setText("群名不能为空!");
            this.b.setVisibility(0);
        } else if (trim.length() > 12) {
            this.b.setText("群名不能超过12个字");
            this.b.setVisibility(0);
        } else {
            ProgressDialog progressDialog = new ProgressDialog(view.getContext());
            progressDialog.setCancelable(false);
            progressDialog.show();
            String format = String.format(Constants.URL_GROUP_DETAIL, new Object[]{String.valueOf(this.c.a.id)});
            Map hashMap = new HashMap();
            hashMap.put(b.c, String.valueOf(this.c.a.id));
            hashMap.put("name", trim);
            SimpleHttpTask simpleHttpTask = new SimpleHttpTask(format, new du(this, progressDialog, trim));
            simpleHttpTask.setMapParams(hashMap);
            simpleHttpTask.execute();
        }
    }
}
