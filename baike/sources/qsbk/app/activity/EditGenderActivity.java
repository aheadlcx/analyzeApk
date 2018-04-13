package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.view.View;
import android.widget.RadioGroup;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.EditInfoBaseActivity.REQUEST_KEY;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.utils.LogUtil;

public class EditGenderActivity extends EditInfoBaseActivity {
    private RadioGroup c;
    private String d;
    private int e = -1;

    public int getLayout() {
        return R.layout.layout_edit_gender;
    }

    public void init() {
        this.c = (RadioGroup) findViewById(R.id.gender_group);
        this.c.setOnCheckedChangeListener(new jv(this));
    }

    private void a() {
        new Builder(this).setCancelable(false).setMessage(R.string.edit_gender).setPositiveButton(R.string.edit_gender_dialog_ok, new jw(this)).setTitle(R.string.nearby_pop_title).show();
    }

    public void handleIntent(Intent intent) {
        Serializable serializableExtra = intent.getSerializableExtra(REQUEST_KEY.KEY_DEFAULT_VALUE);
        this.d = serializableExtra != null ? (String) serializableExtra : "";
        if ("F".equalsIgnoreCase(this.d)) {
            this.e = R.id.gender_female;
            this.c.check(R.id.gender_female);
        } else if ("M".equalsIgnoreCase(this.d)) {
            this.e = R.id.gender_male;
            this.c.check(R.id.gender_male);
        } else {
            this.d = BaseUserInfo.GENDER_UNKONW;
        }
    }

    public Map<String, Object> getPostParams() {
        Map<String, Object> hashMap = new HashMap();
        hashMap.put("gender", this.d);
        return hashMap;
    }

    public String getPostUrl() {
        return String.format(Constants.URL_USER_INFO, new Object[]{QsbkApp.currentUser.userId});
    }

    public void onCancel(View view) {
        b("Edit gender cancel.");
    }

    public boolean onSure(View view) {
        LogUtil.d("gender:" + this.d);
        return !BaseUserInfo.GENDER_UNKONW.equalsIgnoreCase(this.d);
    }

    public Intent getResultData() {
        Intent intent = new Intent();
        intent.putExtra(REQUEST_KEY.KEY_RETURN_VALUE, this.d);
        return intent;
    }
}
