package qsbk.app.api;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.EditInfoBaseActivity.REQUEST_KEY;
import qsbk.app.adapter.EditUserInfoAdapter$UserInfoItem;

public class StartActivityListenerForClick implements OnClickListener {
    private final Intent a;
    private final Activity b;
    private final int c;

    public StartActivityListenerForClick(Intent intent, Activity activity, int i) {
        this.a = intent;
        this.b = activity;
        this.c = i;
    }

    public void onClick(View view) {
        if (view.getTag() instanceof EditUserInfoAdapter$UserInfoItem) {
            this.a.putExtra(REQUEST_KEY.KEY_DEFAULT_VALUE, ((EditUserInfoAdapter$UserInfoItem) view.getTag()).getInnerValue());
        }
        this.b.startActivityForResult(this.a, this.c);
    }
}
