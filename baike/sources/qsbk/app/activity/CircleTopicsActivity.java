package qsbk.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.alipay.sdk.util.j;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.fragments.CircleTopicsFragment;
import qsbk.app.model.CircleTopic;

public class CircleTopicsActivity extends BaseActionBarActivity {
    public static final int REQ = 1000;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return e();
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, CircleTopicsActivity.class);
        intent.addFlags(67108864);
        activity.startActivity(intent);
    }

    public static void launchForResult(Activity activity) {
        Intent intent = new Intent(activity, CircleTopicsActivity.class);
        intent.putExtra(j.c, true);
        activity.startActivityForResult(intent, 1000);
    }

    public static void launchForResult(Activity activity, boolean z) {
        Intent intent = new Intent(activity, CircleTopicsActivity.class);
        intent.putExtra(j.c, true);
        intent.putExtra("is_no_clock", z);
        activity.startActivityForResult(intent, 1000);
    }

    public static CircleTopic getResultFromIntent(Intent intent) {
        return (CircleTopic) intent.getSerializableExtra("topic");
    }

    protected int a() {
        return R.layout.activity_frame;
    }

    protected String e() {
        return null;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        boolean booleanExtra = getIntent().getBooleanExtra(j.c, false);
        boolean booleanExtra2 = getIntent().getBooleanExtra("is_no_clock", false);
        setTitle(booleanExtra ? "选择话题" : "更多话题");
        if (bundle == null) {
            getSupportFragmentManager().beginTransaction().add((int) R.id.frame, CircleTopicsFragment.newInstance(booleanExtra, booleanExtra2)).commit();
        }
    }
}
