package com.budejie.www.activity.mycomment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.budejie.www.R;
import com.budejie.www.activity.base.BaseActvityWithLoadDailog;
import com.budejie.www.activity.video.p;
import com.budejie.www.util.ai;

public class MyCommentActivity extends BaseActvityWithLoadDailog {
    private String a;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_mycomment_layout);
        d(R.id.navigation_bar);
        a(null);
        setTitle((int) R.string.mycomment);
        p.a(this, (FrameLayout) findViewById(R.id.myCommentFragment));
        this.a = ai.b(this);
        Fragment aVar = new a();
        Bundle bundle2 = new Bundle();
        bundle2.putString(HistoryOpenHelper.COLUMN_UID, this.a);
        aVar.setArguments(bundle2);
        getSupportFragmentManager().beginTransaction().add((int) R.id.myCommentFragment, aVar).commit();
    }
}
