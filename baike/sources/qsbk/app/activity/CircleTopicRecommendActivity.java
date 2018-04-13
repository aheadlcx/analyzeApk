package qsbk.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.fragments.CircleTopicRecommendFragment;

public class CircleTopicRecommendActivity extends BaseActionBarActivity {
    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, CircleTopicRecommendActivity.class));
    }

    protected CharSequence getCustomTitle() {
        return "往期每周话题推荐";
    }

    protected int a() {
        return R.layout.activity_circle_topic_collections;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        getSupportFragmentManager().beginTransaction().replace(R.id.content, CircleTopicRecommendFragment.newInstance()).commitAllowingStateLoss();
    }
}
