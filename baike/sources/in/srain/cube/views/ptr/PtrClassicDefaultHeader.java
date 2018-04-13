package in.srain.cube.views.ptr;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;
import in.srain.cube.views.ptr.indicator.PtrIndicator;
import java.text.SimpleDateFormat;
import java.util.Date;
import qsbk.app.R;

public class PtrClassicDefaultHeader extends FrameLayout implements PtrUIHandler {
    private static SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private int b = 150;
    private RotateAnimation c;
    private RotateAnimation d;
    private TextView e;
    private View f;
    private View g;
    private long h = -1;
    private TextView i;
    private String j;
    private boolean k;
    private a l = new a();

    private class a implements Runnable {
        final /* synthetic */ PtrClassicDefaultHeader a;
        private boolean b;

        private a(PtrClassicDefaultHeader ptrClassicDefaultHeader) {
            this.a = ptrClassicDefaultHeader;
            this.b = false;
        }

        private void a() {
            if (!TextUtils.isEmpty(this.a.j)) {
                this.b = true;
                run();
            }
        }

        private void b() {
            this.b = false;
            this.a.removeCallbacks(this);
        }

        public void run() {
            this.a.d();
            if (this.b) {
                this.a.postDelayed(this, 1000);
            }
        }
    }

    public PtrClassicDefaultHeader(Context context) {
        super(context);
        a(null);
    }

    public PtrClassicDefaultHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet);
    }

    public PtrClassicDefaultHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet);
    }

    protected void a(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.PtrClassicHeader, 0, 0);
        if (obtainStyledAttributes != null) {
            this.b = obtainStyledAttributes.getInt(0, this.b);
        }
        a();
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.cube_ptr_classic_default_header, this);
        this.f = inflate.findViewById(R.id.ptr_classic_header_rotate_view);
        this.e = (TextView) inflate.findViewById(R.id.ptr_classic_header_rotate_view_header_title);
        this.i = (TextView) inflate.findViewById(R.id.ptr_classic_header_rotate_view_header_last_update);
        this.g = inflate.findViewById(R.id.ptr_classic_header_rotate_view_progressbar);
        b();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.l != null) {
            this.l.b();
        }
    }

    public void setRotateAniTime(int i) {
        if (i != this.b && i != 0) {
            this.b = i;
            a();
        }
    }

    public void setLastUpdateTimeKey(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.j = str;
        }
    }

    public void setLastUpdateTimeRelateObject(Object obj) {
        setLastUpdateTimeKey(obj.getClass().getName());
    }

    private void a() {
        this.c = new RotateAnimation(0.0f, -180.0f, 1, 0.5f, 1, 0.5f);
        this.c.setInterpolator(new LinearInterpolator());
        this.c.setDuration((long) this.b);
        this.c.setFillAfter(true);
        this.d = new RotateAnimation(-180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        this.d.setInterpolator(new LinearInterpolator());
        this.d.setDuration((long) this.b);
        this.d.setFillAfter(true);
    }

    private void b() {
        c();
        this.g.setVisibility(4);
    }

    private void c() {
        this.f.clearAnimation();
        this.f.setVisibility(4);
    }

    public void onUIReset(PtrFrameLayout ptrFrameLayout) {
        b();
        this.k = true;
        d();
    }

    public void onUIRefreshPrepare(PtrFrameLayout ptrFrameLayout) {
        this.k = true;
        d();
        this.l.a();
        this.g.setVisibility(4);
        this.f.setVisibility(0);
        this.e.setVisibility(0);
        if (ptrFrameLayout.isPullToRefresh()) {
            this.e.setText(getResources().getString(R.string.cube_ptr_pull_down_to_refresh));
        } else {
            this.e.setText(getResources().getString(R.string.cube_ptr_pull_down));
        }
    }

    public void onUIRefreshBegin(PtrFrameLayout ptrFrameLayout) {
        this.k = false;
        c();
        this.g.setVisibility(0);
        this.e.setVisibility(0);
        this.e.setText(R.string.cube_ptr_refreshing);
        d();
        this.l.b();
    }

    public void onUIRefreshComplete(PtrFrameLayout ptrFrameLayout) {
        c();
        this.g.setVisibility(4);
        this.e.setVisibility(0);
        this.e.setText(getResources().getString(R.string.cube_ptr_refresh_complete));
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("cube_ptr_classic_last_update", 0);
        if (!TextUtils.isEmpty(this.j)) {
            this.h = new Date().getTime();
            sharedPreferences.edit().putLong(this.j, this.h).commit();
        }
    }

    private void d() {
        if (TextUtils.isEmpty(this.j) || !this.k) {
            this.i.setVisibility(8);
            return;
        }
        CharSequence lastUpdateTime = getLastUpdateTime();
        if (TextUtils.isEmpty(lastUpdateTime)) {
            this.i.setVisibility(8);
            return;
        }
        this.i.setVisibility(0);
        this.i.setText(lastUpdateTime);
    }

    private String getLastUpdateTime() {
        if (this.h == -1 && !TextUtils.isEmpty(this.j)) {
            this.h = getContext().getSharedPreferences("cube_ptr_classic_last_update", 0).getLong(this.j, -1);
        }
        if (this.h == -1) {
            return null;
        }
        long time = new Date().getTime() - this.h;
        int i = (int) (time / 1000);
        if (time < 0 || i <= 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getContext().getString(R.string.cube_ptr_last_update));
        if (i < 60) {
            stringBuilder.append(i + getContext().getString(R.string.cube_ptr_seconds_ago));
        } else {
            i /= 60;
            if (i > 60) {
                i /= 60;
                if (i > 24) {
                    stringBuilder.append(a.format(new Date(this.h)));
                } else {
                    stringBuilder.append(i + getContext().getString(R.string.cube_ptr_hours_ago));
                }
            } else {
                stringBuilder.append(i + getContext().getString(R.string.cube_ptr_minutes_ago));
            }
        }
        return stringBuilder.toString();
    }

    public void onUIPositionChange(PtrFrameLayout ptrFrameLayout, boolean z, byte b, PtrIndicator ptrIndicator) {
        int offsetToRefresh = ptrFrameLayout.getOffsetToRefresh();
        int currentPosY = ptrIndicator.getCurrentPosY();
        int lastPosY = ptrIndicator.getLastPosY();
        if (currentPosY >= offsetToRefresh || lastPosY < offsetToRefresh) {
            if (currentPosY > offsetToRefresh && lastPosY <= offsetToRefresh && z && b == (byte) 2) {
                a(ptrFrameLayout);
                if (this.f != null) {
                    this.f.clearAnimation();
                    this.f.startAnimation(this.c);
                }
            }
        } else if (z && b == (byte) 2) {
            b(ptrFrameLayout);
            if (this.f != null) {
                this.f.clearAnimation();
                this.f.startAnimation(this.d);
            }
        }
    }

    private void a(PtrFrameLayout ptrFrameLayout) {
        if (!ptrFrameLayout.isPullToRefresh()) {
            this.e.setVisibility(0);
            this.e.setText(R.string.cube_ptr_release_to_refresh);
        }
    }

    private void b(PtrFrameLayout ptrFrameLayout) {
        this.e.setVisibility(0);
        if (ptrFrameLayout.isPullToRefresh()) {
            this.e.setText(getResources().getString(R.string.cube_ptr_pull_down_to_refresh));
        } else {
            this.e.setText(getResources().getString(R.string.cube_ptr_pull_down));
        }
    }
}
