package qsbk.app.activity;

import android.os.Bundle;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.fragments.DiggerFragment;

public class DiggerActivity extends BaseActionBarActivity {
    protected /* synthetic */ CharSequence getCustomTitle() {
        return e();
    }

    protected String e() {
        return "觉得好笑的糗友";
    }

    protected int a() {
        return R.layout.activity_digger;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        if (bundle == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, DiggerFragment.newInstance(getIntent().getStringExtra("article_id"))).commit();
        }
    }
}
