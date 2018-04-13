package qsbk.app.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.Window;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.fragments.CircleTopicWeeklyFragment;

public class CircleTopicWeeklyActivity extends BaseActionBarActivity {
    public static final String ID = "id";

    public static void launch(Context context, String str) {
        Intent intent = new Intent(context, CircleTopicWeeklyActivity.class);
        intent.putExtra("id", str);
        if (!(context instanceof Activity)) {
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        }
        context.startActivity(intent);
    }

    protected CharSequence getCustomTitle() {
        return "每周推荐话题";
    }

    protected int a() {
        return R.layout.activity_circle_topic_weekly;
    }

    protected boolean f_() {
        return false;
    }

    protected void a(Bundle bundle) {
        getSupportActionBar().hide();
        e();
        Intent intent = getIntent();
        String str = "0";
        if (intent != null) {
            str = intent.getStringExtra("id");
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.content, CircleTopicWeeklyFragment.newInstance(str)).commitAllowingStateLoss();
    }

    protected void e() {
        if (VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(67108864);
            window.getDecorView().setSystemUiVisibility(1280);
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(0);
        } else if (VERSION.SDK_INT >= 19) {
            b(true);
        }
    }
}
