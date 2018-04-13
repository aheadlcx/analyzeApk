package qsbk.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import pl.droidsonroids.gif.GifImageView;
import qsbk.app.ConfigManager;
import qsbk.app.QsbkApp;
import qsbk.app.R;

@Deprecated
public class UserGuideActivity extends Activity {
    private static final String a = UserGuideActivity.class.getSimpleName();
    private boolean b = true;
    private Handler c;
    private int d = 0;
    private int e = 0;
    private int f = 0;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.activity_user_guide);
        GifImageView gifImageView = (GifImageView) findViewById(R.id.shit);
        gifImageView.setBackgroundResource(R.drawable.user_guide_shit);
        TextView textView = (TextView) findViewById(R.id.text);
        this.c = new Handler();
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        List arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        UserGuideActivity$a userGuideActivity$b = new UserGuideActivity$b(this);
        arrayList2.add(userGuideActivity$b);
        arrayList.add(userGuideActivity$b.mView);
        userGuideActivity$b = new UserGuideActivity$c(this);
        arrayList2.add(userGuideActivity$b);
        arrayList.add(userGuideActivity$b.mView);
        userGuideActivity$b = new UserGuideActivity$d(this);
        arrayList2.add(userGuideActivity$b);
        arrayList.add(userGuideActivity$b.mView);
        userGuideActivity$b = new UserGuideActivity$e(this);
        arrayList2.add(userGuideActivity$b);
        arrayList.add(userGuideActivity$b.mView);
        userGuideActivity$b = new UserGuideActivity$f(this);
        arrayList2.add(userGuideActivity$b);
        arrayList.add(userGuideActivity$b.mView);
        viewPager.setAdapter(new UserGuideActivity$GuidePageAdapter(this, arrayList));
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new adz(this, gifImageView, arrayList2, textView));
    }

    protected void onStop() {
        super.onStop();
        finish();
    }

    private boolean a(int i) {
        boolean z = i - this.f > 0;
        this.f = i;
        return z;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.b) {
            return super.dispatchTouchEvent(motionEvent);
        }
        return true;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 3) {
            Process.killProcess(Process.myPid());
        }
        return super.onKeyDown(i, keyEvent);
    }

    private void b() {
        if (ConfigManager.getInstance().isPromoteChannel()) {
            long delta = QsbkApp.getInstance().startTimeDelta.getDelta();
            if (delta > 2000) {
                new Handler().postDelayed(new UserGuideActivity$g(this, null), 0);
                return;
            } else {
                new Handler().postDelayed(new UserGuideActivity$g(this, null), 2000 - delta);
                return;
            }
        }
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
