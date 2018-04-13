package qsbk.app.activity;

import android.os.Bundle;
import java.util.ArrayList;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.utils.UIHelper;

public abstract class BaseCreateGroupActivity extends BaseActionBarActivity {
    private static ArrayList<BaseCreateGroupActivity> a = null;

    public static void notifyToExit() {
        if (a != null) {
            for (int i = 0; i < a.size(); i++) {
                ((BaseCreateGroupActivity) a.get(i)).finish();
            }
        }
    }

    protected void c_() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night);
        } else {
            setTheme(R.style.Day.GroupInfo);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        register();
    }

    protected void onDestroy() {
        super.onDestroy();
        unregister();
    }

    public void register() {
        if (a == null) {
            a = new ArrayList();
        }
        a.add(this);
    }

    public void unregister() {
        if (a != null) {
            a.remove(this);
            if (a.size() == 0) {
                a = null;
            }
        }
    }
}
