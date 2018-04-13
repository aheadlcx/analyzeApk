package qsbk.app.nearby.ui;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v4.app.DialogFragment;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.baidu.mobstat.Config;
import java.util.Timer;
import pl.droidsonroids.gif.GifImageView;
import qsbk.app.R;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.LogUtil;

public class ShakeDialogFragment extends DialogFragment implements OnClickListener, OnTouchListener {
    public static final String KEY_NAME = "name";
    private static final String l = ShakeDialogFragment.class.getSimpleName();
    private static long[] m = new long[]{100, 300, 100, 500};
    private int A = 3;
    private int B = 0;
    private int C = 0;
    private final Runnable D = new as(this);
    private boolean E = false;
    private boolean F = false;
    private boolean G = false;
    private float H;
    private Vibrator I;
    private Button J;
    private int K;
    private ImageView L;
    private int M;
    private LayoutParams N;
    private final Runnable O = new at(this);
    private final Runnable P = new au(this);
    Handler j;
    Timer k;
    private final Runnable n = new ar(this);
    private String o;
    private GifImageView p;
    private View q;
    private View r;
    private View s;
    private View t;
    private OnShakedListener u;
    private boolean v = true;
    private TextView w;
    private TextView x;
    private TextView y;
    private long z = SystemClock.elapsedRealtime();

    public interface OnShakedListener {
        void onSuccess(int i, int i2);
    }

    public static ShakeDialogFragment newInstance(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("name", str);
        ShakeDialogFragment shakeDialogFragment = new ShakeDialogFragment();
        shakeDialogFragment.setArguments(bundle);
        return shakeDialogFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.j = new Handler();
        this.H = getResources().getDisplayMetrics().density;
        if (getArguments() != null) {
            this.o = getArguments().getString("name");
        }
        this.I = (Vibrator) getActivity().getSystemService("vibrator");
        DebugUtil.debug(l, "onCreate");
        this.K = 0;
    }

    public OnShakedListener getOnShakedListener() {
        return this.u;
    }

    public void setOnShakedListener(OnShakedListener onShakedListener) {
        this.u = onShakedListener;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onResume() {
        super.onResume();
    }

    public void onStop() {
        DebugUtil.debug(l, "onStop");
        dismissAllowingStateLoss();
        super.onStop();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        DebugUtil.debug(l, "onCreateDialog");
        View inflate = View.inflate(getActivity(), R.layout.layout_shake, null);
        inflate.findViewById(R.id.close).setOnClickListener(this);
        this.J = (Button) inflate.findViewById(R.id.long_click_button);
        this.J.setOnTouchListener(this);
        this.s = inflate.findViewById(R.id.shake_start);
        this.w = (TextView) inflate.findViewById(R.id.progress);
        this.L = (ImageView) inflate.findViewById(R.id.shake_fg_iv);
        this.N = this.L.getLayoutParams();
        this.M = getResources().getDimensionPixelSize(R.dimen.shake_ball);
        LogUtil.d("mMaxHeight = ==" + this.M);
        this.q = inflate.findViewById(R.id.shake_failed);
        inflate.findViewById(R.id.retry).setOnClickListener(this);
        this.r = inflate.findViewById(R.id.shake_success);
        this.x = (TextView) inflate.findViewById(R.id.shake_cost);
        this.y = (TextView) inflate.findViewById(R.id.shake_success_sub_title);
        this.p = (GifImageView) inflate.findViewById(R.id.shake_success_gif);
        a();
        b(0);
        inflate.setMinimumHeight((int) (300.0f * this.H));
        return new Builder(getActivity()).setView(inflate).create();
    }

    private void a() {
        if (!this.E) {
            this.w.setText("0%");
            this.E = true;
        }
    }

    private void a(int i) {
        int i2 = this.N.height;
        this.N.height = (int) ((0.01d * ((double) i)) * ((double) this.M));
        this.L.setLayoutParams(this.N);
    }

    private void b() {
        if (!this.F) {
            this.p.setImageResource(R.drawable.shake_success);
            this.F = true;
        }
    }

    private void c() {
        b(0);
        this.B = 0;
        this.z = SystemClock.elapsedRealtime();
        this.A = 3;
        this.w.setText("0%");
        this.v = true;
    }

    private void d() {
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.z;
        this.y.setText(String.format(getResources().getString(R.string.shake_success_sub_title), new Object[]{this.o}));
        this.I.vibrate(m, -1);
        this.j.postDelayed(this.n, Config.BPLUS_DELAY_TIME);
        if (this.u != null) {
            this.u.onSuccess(this.A, (int) (elapsedRealtime / 1000));
        }
    }

    private void b(int i) {
        int i2 = 0;
        if (i != this.C) {
            this.C = i;
            int[] iArr = new int[]{8, 8, 8, 8};
            iArr[i] = 0;
            View[] viewArr = new View[]{this.s, this.t, this.r, this.q};
            int length = iArr.length;
            while (i2 < length) {
                if (viewArr[i2] != null) {
                    viewArr[i2].setVisibility(iArr[i2]);
                }
                i2++;
            }
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        DebugUtil.debug(l, "onDestroyView");
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.close:
                c();
                dismissAllowingStateLoss();
                return;
            case R.id.retry:
                c();
                return;
            default:
                return;
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (view.getId()) {
            case R.id.long_click_button:
                switch (motionEvent.getAction()) {
                    case 0:
                        if (this.K == 0 && this.v) {
                            this.z = SystemClock.elapsedRealtime();
                        } else {
                            this.v = false;
                        }
                        if (VERSION.SDK_INT >= 16) {
                            this.J.setBackground(getResources().getDrawable(R.drawable.fan_long_click_btn_pressed));
                        } else {
                            this.J.setBackgroundResource(R.drawable.fan_long_click_btn_pressed);
                        }
                        this.j.removeCallbacks(this.P);
                        if (this.k != null) {
                            this.k.cancel();
                        }
                        this.k = new Timer();
                        if (this.K < 0 || this.K >= 100) {
                            if (this.K != 100) {
                                if (this.K > 100) {
                                    this.v = true;
                                    this.j.post(this.D);
                                    break;
                                }
                            }
                            if (this.k != null) {
                                this.k.cancel();
                            }
                            this.j.post(this.O);
                            break;
                        }
                        this.k.schedule(new av(this), 20, 20);
                        break;
                        break;
                    case 1:
                        this.J.setBackgroundDrawable(getResources().getDrawable(R.drawable.fan_long_click_btn));
                        this.j.removeCallbacks(this.O);
                        if (this.k != null) {
                            this.k.cancel();
                        }
                        this.k = new Timer();
                        if (this.K <= 0 || this.K >= 100) {
                            if (this.K != 0) {
                                if (this.K < 0) {
                                    this.v = true;
                                    this.j.post(this.D);
                                    break;
                                }
                            }
                            this.v = true;
                            if (this.k != null) {
                                this.k.cancel();
                            }
                            this.j.post(this.P);
                            break;
                        }
                        this.k.schedule(new aw(this), 20, 20);
                        break;
                        break;
                    default:
                        break;
                }
        }
        return true;
    }
}
