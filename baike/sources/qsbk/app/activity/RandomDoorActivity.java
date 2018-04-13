package qsbk.app.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.Button;
import java.util.Random;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.fragments.RandomDoorStartFragment;
import qsbk.app.fragments.RandomDoorStartFragment.AnimationEndListener;
import qsbk.app.fragments.RandomDoorUsersFragment;
import qsbk.app.nearby.api.RandomLocationMgr;
import qsbk.app.nearby.api.RandomLocationMgr$Location;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;

public class RandomDoorActivity extends BaseActionBarActivity implements AnimationEndListener {
    private static final String a = RandomDoorActivity.class.getSimpleName();
    private RandomDoorStartFragment b;
    private RandomDoorUsersFragment c;
    private RandomLocationMgr$Location d = null;
    private Button e;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public void openStart(boolean z) {
        if (this.e != null) {
            if (z) {
                this.e.setVisibility(8);
                this.e.setEnabled(false);
            } else {
                this.e.setVisibility(0);
                this.e.setEnabled(true);
            }
        }
        RandomLocationMgr$Location[] allLocations = RandomLocationMgr.getInstance().getAllLocations();
        if (allLocations == null) {
            ToastAndDialog.makeNegativeToast(this, "迷路了，找不到门，请稍后再试。").show();
            return;
        }
        int nextInt = new Random().nextInt(allLocations.length);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        this.b = RandomDoorStartFragment.newInstance(allLocations, nextInt, z);
        this.d = allLocations[nextInt];
        beginTransaction.replace(R.id.container, this.b);
        beginTransaction.commit();
    }

    public void openUsers() {
        if (this.e != null) {
            this.e.setVisibility(8);
        }
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        this.c = RandomDoorUsersFragment.newInstance(this.d.getLongitude(), this.d.getLatitude());
        beginTransaction.setCustomAnimations(R.anim.scale_in, R.anim.scale_out);
        beginTransaction.replace(R.id.container, this.c);
        beginTransaction.commitAllowingStateLoss();
    }

    protected String f() {
        return "任意门";
    }

    protected int a() {
        return R.layout.activity_randomdoor;
    }

    protected void c_() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night);
        } else {
            setTheme(R.style.Day);
        }
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        RandomLocationMgr.getInstance().checkUpdate();
        if (bundle == null) {
            openStart(false);
        }
        this.e = (Button) findViewById(R.id.start);
        this.e.setOnClickListener(new aan(this));
        if (UIHelper.isNightTheme()) {
            findViewById(R.id.layerMask).setVisibility(0);
        } else {
            findViewById(R.id.layerMask).setVisibility(8);
        }
    }

    public void onAnimationEnd() {
        this.e.postDelayed(new aap(this), 400);
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onStop() {
        super.onStop();
    }
}
