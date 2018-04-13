package qsbk.app.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import qsbk.app.activity.ActionBarLoginActivity;

public final class ActivityOpener {

    public static class NonMainThreadException extends RuntimeException {
        public NonMainThreadException(String str) {
            super(str);
        }
    }

    private ActivityOpener() {
    }

    public static void login(Activity activity, int i, Bundle bundle) {
        Intent intent = new Intent(activity, ActionBarLoginActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (i >= 0) {
            activity.startActivityForResult(intent, i);
        } else {
            activity.startActivity(intent);
        }
    }
}
