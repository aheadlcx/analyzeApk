package qsbk.app.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.fragments.QiushiNotificationListFragment;
import qsbk.app.fragments.QiuyouquanNotificationListFragment;

public class QiushiNotificationListActivity extends BaseActionBarActivity {
    public static final String KEY_IS_PROMOTE = "_is_promote_";
    public static final String KEY_NEW_COUNT = "_new_count";
    public static final String KEY_TYPE = "_title";
    public static final int TYPE_QIUSHI = 0;
    public static final int TYPE_QIUYOUQUAN = 1;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public static Intent gotoQiushiIntent(Context context, int i) {
        return makeIntent(context, 0, i);
    }

    public static Intent gotoQiushiIntent(Context context, int i, boolean z) {
        return makeIntent(context, 0, i, z);
    }

    public static Intent gotoQiuyouquanIntent(Context context, int i) {
        return makeIntent(context, 1, i);
    }

    public static Intent makeIntent(Context context, int i, int i2) {
        if (context == null) {
            return null;
        }
        Intent intent = new Intent(context, QiushiNotificationListActivity.class);
        intent.putExtra("_new_count", i2);
        intent.putExtra("_title", i);
        return intent;
    }

    public static Intent makeIntent(Context context, int i, int i2, boolean z) {
        if (context == null) {
            return null;
        }
        Intent intent = new Intent(context, QiushiNotificationListActivity.class);
        intent.putExtra("_new_count", i2);
        intent.putExtra("_title", i);
        intent.putExtra("_is_promote_", z);
        return intent;
    }

    public static void gotoQiushi(Activity activity, int i) {
        if (activity != null) {
            activity.startActivity(gotoQiushiIntent(activity, i));
        }
    }

    public static void gotoQiuyouquan(Activity activity, int i) {
        if (activity != null) {
            activity.startActivity(makeIntent(activity, 1, i));
        }
    }

    protected String f() {
        return getIntent().getIntExtra("_title", 0) == 1 ? "糗友圈消息" : "糗事消息";
    }

    public void finish() {
        if (isTaskRoot()) {
            startActivity(new Intent(this, MainActivity.class));
        }
        super.finish();
    }

    protected int a() {
        return R.layout.activity_fragment_container;
    }

    protected void a(Bundle bundle) {
        if (bundle == null) {
            setActionbarBackable();
            int intExtra = getIntent().getIntExtra("_new_count", 0);
            int intExtra2 = getIntent().getIntExtra("_title", 0);
            getIntent().getBooleanExtra("_is_promote_", false);
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            if (intExtra2 == 0) {
                beginTransaction.replace(R.id.container, QiushiNotificationListFragment.newInstance(intExtra));
            } else if (intExtra2 == 1) {
                beginTransaction.replace(R.id.container, QiuyouquanNotificationListFragment.newInstance(intExtra));
            }
            beginTransaction.commit();
        }
    }
}
