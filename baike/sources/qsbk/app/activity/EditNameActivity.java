package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.EditInfoBaseActivity.REQUEST_KEY;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.utils.ToastAndDialog;

public class EditNameActivity extends SingleEditTextBaseActivity {
    public void init() {
        super.init();
        this.e.setSingleLine(true);
    }

    public String getDefaultInputTips() {
        return getResources().getString(R.string.edit_name);
    }

    public String getDefaultEditTextTips() {
        return getResources().getString(R.string.edit_name);
    }

    public Map<String, Object> getPostParams() {
        Map<String, Object> hashMap = new HashMap();
        hashMap.put(QsbkDatabase.LOGIN, this.e.getText().toString());
        return hashMap;
    }

    public String getPostUrl() {
        return String.format(Constants.URL_USER_INFO, new Object[]{QsbkApp.currentUser.userId});
    }

    public void onCancel(View view) {
        b("Edit name cancel.");
    }

    public boolean onSure(View view) {
        boolean z = 15 >= this.e.getText().length();
        if (!z) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, String.format(getResources().getString(R.string.edit_limit), new Object[]{Integer.valueOf(this.e.getText().length() - 15)})).show();
        }
        return z;
    }

    public Intent getResultData() {
        Intent intent = new Intent();
        intent.putExtra(REQUEST_KEY.KEY_RETURN_VALUE, this.e.getText().toString());
        return intent;
    }

    public int maxLength() {
        return 15;
    }

    public String getDescription() {
        return getResources().getString(R.string.edit_name_title);
    }
}
