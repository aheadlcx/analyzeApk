package qsbk.app.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.fragments.QiushiTopicListFragment;

public class QiushiTopicListActivity extends BaseActionBarActivity {
    public static final String MODE = "mode";
    public static final int MODE_ALL = 0;
    public static final int MODE_MY = 1;
    public static final int MODE_SELECT = 2;
    int a;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public static void launch(Context context, int i) {
        Intent intent = new Intent(context, QiushiTopicListActivity.class);
        intent.putExtra(MODE, i);
        if (!(context instanceof Activity)) {
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        }
        context.startActivity(intent);
    }

    public static void launchForResult(Activity activity, int i, int i2) {
        Intent intent = new Intent(activity, QiushiTopicListActivity.class);
        intent.putExtra(MODE, i);
        activity.startActivityForResult(intent, i2);
    }

    protected String f() {
        switch (this.a) {
            case 0:
                return getString(R.string.all_qiushi_topic);
            case 1:
                return getString(R.string.my_qiushi_topic);
            case 2:
                return getString(R.string.select_topic);
            default:
                return "";
        }
    }

    protected int a() {
        return R.layout.activity_qiushi_topic_list;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        this.a = getIntent().getIntExtra(MODE, -1);
        if (this.a == -1) {
            finish();
        } else if (bundle == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.content_container, QiushiTopicListFragment.newInstance(this.a)).commit();
        }
    }
}
