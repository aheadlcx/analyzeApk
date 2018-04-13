package qsbk.app.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.activity.ActionBarLoginActivity;

public class LoginPermissionClickDelegate implements OnClickListener {
    final OnClickListener a;
    final String b;

    public LoginPermissionClickDelegate(OnClickListener onClickListener, String str) {
        this.a = onClickListener;
        this.b = str;
    }

    public static void startLoginActivity(Context context) {
        if (context != null) {
            context.startActivity(new Intent(context, ActionBarLoginActivity.class));
        }
    }

    public static void startLoginActivity(Activity activity, int i) {
        if (activity != null) {
            activity.startActivityForResult(new Intent(activity, ActionBarLoginActivity.class), i);
        }
    }

    public void onClick(View view) {
        if (QsbkApp.currentUser == null) {
            if (!TextUtils.isEmpty(this.b)) {
                ToastAndDialog.makeNeutralToast(view.getContext(), this.b, Integer.valueOf(0)).show();
            }
            startLoginActivity(view.getContext());
        } else if (this.a != null) {
            this.a.onClick(view);
        }
    }
}
