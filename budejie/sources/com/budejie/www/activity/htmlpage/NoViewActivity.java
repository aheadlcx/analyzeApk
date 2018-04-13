package com.budejie.www.activity.htmlpage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.budejie.www.util.aa;
import com.budejie.www.util.w;

public class NoViewActivity extends Activity {
    private static final String a = NoViewActivity.class.getSimpleName();

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String dataString = getIntent().getDataString();
        try {
            aa.a(a, "lunchPage-->" + dataString);
            w.a((Activity) this, true).a(dataString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        w.a((Activity) this, true).a(i, i2, intent);
    }
}
