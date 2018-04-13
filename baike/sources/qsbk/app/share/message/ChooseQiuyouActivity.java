package qsbk.app.share.message;

import android.content.Intent;
import android.os.Bundle;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.model.BaseUserInfo;

public class ChooseQiuyouActivity extends BaseActionBarActivity {
    private Bundle a;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    protected String f() {
        return getResources().getString(R.string.choose_qiuyou);
    }

    protected int a() {
        return R.layout.activity_fragment_container;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        this.a = getIntent().getExtras();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, ChooseQiuyouFragment.newInstance(this.a)).commit();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 87 && i2 == -1) {
            BaseUserInfo baseUserInfo = (BaseUserInfo) intent.getSerializableExtra(QsbkDatabase.USER_TABLE_NAME);
            Intent intent2 = new Intent();
            intent2.putExtra(QsbkDatabase.USER_TABLE_NAME, baseUserInfo);
            setResult(-1, intent2);
            finish();
        }
    }
}
