package qsbk.app.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.Random;
import qsbk.app.R;
import qsbk.app.nearby.api.RandomLocationMgr$Location;
import qsbk.app.nearby.api.Rotate3dAnimation2;

public class RandomDoorStartFragment extends BaseFragment {
    public static final String KEY_AUTO_PLAY = "should_auto_play";
    public static final String KEY_LOCATION_DATA = "location_data";
    public static final String KEY_SELECTED_LOCATION_INDEX = "selected_location_index";
    private RandomLocationMgr$Location[] a;
    private TextView b;
    private a c;
    private int d;
    private boolean e = false;
    private Animation f;
    private Animation g;
    private Animation h;
    private BitmapDrawable i;
    private ImageView j;
    private ImageView k;
    private ImageView l;
    private AnimationEndListener m;
    private Animation n;

    public interface AnimationEndListener {
        void onAnimationEnd();
    }

    private static class a implements Runnable {
        int a;
        WeakReference<TextView> b;
        WeakReference<RandomLocationMgr$Location[]> c;

        public a(TextView textView, RandomLocationMgr$Location[] randomLocationMgr$LocationArr) {
            this.b = new WeakReference(textView);
            this.c = new WeakReference(randomLocationMgr$LocationArr);
            this.a = new Random().nextInt(randomLocationMgr$LocationArr.length);
        }

        private static final void a(WeakReference weakReference) {
            if (weakReference != null) {
                weakReference.clear();
            }
        }

        public void run() {
            TextView textView = (TextView) this.b.get();
            RandomLocationMgr$Location[] randomLocationMgr$LocationArr = (RandomLocationMgr$Location[]) this.c.get();
            if (textView != null && randomLocationMgr$LocationArr != null) {
                textView.setText(randomLocationMgr$LocationArr[this.a].getName());
                this.a++;
                this.a %= randomLocationMgr$LocationArr.length;
                textView.postDelayed(this, 1100);
            }
        }

        void a(long j) {
            TextView textView = (TextView) this.b.get();
            if (textView != null) {
                textView.postDelayed(this, j);
            }
        }

        void a() {
            TextView textView = (TextView) this.b.get();
            if (textView != null) {
                textView.removeCallbacks(this);
            }
        }

        void b() {
            a(this.c);
            a(this.b);
        }
    }

    public static RandomDoorStartFragment newInstance(RandomLocationMgr$Location[] randomLocationMgr$LocationArr, int i, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(KEY_AUTO_PLAY, z);
        bundle.putParcelableArray(KEY_LOCATION_DATA, randomLocationMgr$LocationArr);
        bundle.putInt(KEY_SELECTED_LOCATION_INDEX, i);
        RandomDoorStartFragment randomDoorStartFragment = new RandomDoorStartFragment();
        randomDoorStartFragment.setArguments(bundle);
        return randomDoorStartFragment;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Bundle arguments = getArguments();
        if (arguments == null) {
            throw new RuntimeException("No data available set in arguments.");
        }
        this.a = (RandomLocationMgr$Location[]) arguments.getParcelableArray(KEY_LOCATION_DATA);
        if (this.a == null || this.a.length <= 0) {
            throw new RuntimeException("No data available set in arguments.");
        }
        int length = this.a.length - 1;
        this.d = arguments.getInt(KEY_SELECTED_LOCATION_INDEX, length);
        if (this.d < 0 || this.d > length) {
            throw new RuntimeException(String.format("Selected location index %s is out of bound %s.", new Object[]{Integer.valueOf(this.d), Integer.valueOf(length)}));
        }
        if (activity instanceof AnimationEndListener) {
            this.m = (AnimationEndListener) activity;
        }
        this.e = arguments.getBoolean(KEY_AUTO_PLAY, false);
    }

    public int getSelected() {
        return this.d;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.random_door_start, viewGroup, false);
        this.b = (TextView) inflate.findViewById(R.id.location);
        this.b.setText("任意门");
        this.l = (ImageView) inflate.findViewById(R.id.door);
        this.j = (ImageView) inflate.findViewById(R.id.bg);
        this.k = (ImageView) inflate.findViewById(R.id.bg1);
        this.i = (BitmapDrawable) this.b.getResources().getDrawable(R.drawable.random_door_start_bg);
        this.i.setTileModeXY(TileMode.REPEAT, TileMode.REPEAT);
        this.j.setImageDrawable(this.i);
        this.k.setImageDrawable(this.i);
        return inflate;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (this.e) {
            this.b.postDelayed(new ko(this), 500);
            this.b.postDelayed(new kp(this), 4500);
        }
    }

    @SuppressLint({"NewApi"})
    public void start() {
        if (this.c == null) {
            this.c = new a(this.b, this.a);
        }
        this.c.a();
        if (this.f == null) {
            int i = this.b.getResources().getDisplayMetrics().widthPixels;
            this.f = new TranslateAnimation(0.0f, (float) (-i), 0.0f, 0.0f);
            this.f.setInterpolator(new LinearInterpolator());
            this.f.setDuration(6000);
            this.f.setRepeatCount(-1);
            this.f.setRepeatMode(1);
            this.g = new TranslateAnimation((float) i, 0.0f, 0.0f, 0.0f);
            this.g.setInterpolator(new LinearInterpolator());
            this.g.setDuration(6000);
            this.g.setRepeatCount(-1);
            this.g.setRepeatMode(1);
        }
        this.f.reset();
        this.j.clearAnimation();
        this.j.startAnimation(this.f);
        this.g.reset();
        this.k.clearAnimation();
        this.k.startAnimation(this.g);
        if (this.h == null) {
            this.h = new RotateAnimation(-1.5f, 10.0f, 1, 0.5f, 1, 0.5f);
            this.h.setInterpolator(new CycleInterpolator(1.0f));
            this.h.setDuration(1000);
            this.h.setRepeatCount(-1);
        }
        this.h.reset();
        this.b.clearAnimation();
        this.b.startAnimation(this.h);
        this.c.a(0);
    }

    public void stop() {
        if (this.c != null) {
            this.c.a();
        }
        this.b.postDelayed(new kq(this), 1100);
    }

    private void a() {
        if (this.n == null) {
            this.n = new Rotate3dAnimation2(0.0f, -100.0f, 0.0f, (float) ((this.l.getBottom() - this.l.getTop()) / 2), 0.0f, false, "y");
            this.n.setDuration(500);
            this.n.setFillAfter(true);
        }
        this.n.reset();
        this.l.clearAnimation();
        this.l.startAnimation(this.n);
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.c != null) {
            this.c.b();
        }
    }

    public void onDetach() {
        super.onDetach();
    }
}
