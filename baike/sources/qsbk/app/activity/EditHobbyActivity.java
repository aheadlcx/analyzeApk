package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.EditInfoBaseActivity.REQUEST_KEY;
import qsbk.app.utils.ToastAndDialog;

public class EditHobbyActivity extends SingleEditTextBaseActivity {
    public String getDefaultInputTips() {
        return getResources().getString(R.string.edit_hobby);
    }

    public String getDefaultEditTextTips() {
        return getResources().getString(R.string.edit_hobby);
    }

    public Map<String, Object> getPostParams() {
        Map<String, Object> hashMap = new HashMap();
        hashMap.put("hobby", this.e.getText().toString());
        return hashMap;
    }

    public void init() {
        super.init();
    }

    public String getPostUrl() {
        return String.format(Constants.URL_USER_INFO, new Object[]{QsbkApp.currentUser.userId});
    }

    public void onCancel(View view) {
        b("Edit hobby cancel.");
    }

    public boolean onSure(View view) {
        boolean z = 140 >= this.e.getText().length();
        if (!z) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, String.format(getResources().getString(R.string.edit_limit), new Object[]{Integer.valueOf(this.e.getText().length() - 140)})).show();
        }
        return z;
    }

    public Intent getResultData() {
        Intent intent = new Intent();
        intent.putExtra(REQUEST_KEY.KEY_RETURN_VALUE, this.e.getText().toString());
        return intent;
    }

    public int maxLength() {
        return 140;
    }

    public String getDescription() {
        return getResources().getString(R.string.edit_hobby_title);
    }
}
