package cn.xiaochuankeji.tieba.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import cn.htjyb.ui.b;
import cn.xiaochuankeji.tieba.R;

public class f extends FrameLayout implements OnClickListener {
    private View a;
    private TextView b;
    private TextView c;
    private View d;
    private boolean e = true;
    private a f;
    private ViewGroup g;

    public interface a {
        void a(boolean z);
    }

    public f(Context context) {
        super(context);
    }

    public static f a(String str, String str2, Activity activity, a aVar) {
        return a(str, str2, activity, aVar, true);
    }

    public static f a(String str, String str2, Activity activity, a aVar, boolean z) {
        return a(str, str2, activity, aVar, z, false);
    }

    public static f a(String str, String str2, Activity activity, a aVar, boolean z, boolean z2) {
        Activity a = b.a(activity);
        f b = b(a);
        if (b != null) {
            b.c();
        }
        b = new f(str, str2, a, aVar, z, z2);
        b.b();
        return b;
    }

    public void setBackgroundColor(int i) {
        findViewById(R.id.alertDlgRoot).setBackgroundColor(i);
    }

    public f(String str, String str2, Activity activity, a aVar, boolean z, boolean z2) {
        super(activity);
        LayoutInflater.from(activity).inflate(R.layout.view_alert_dlg, this);
        setId(R.id.view_alert_dlg);
        setVisibility(8);
        setLayoutParams(new LayoutParams(-1, -1));
        this.g = c(activity);
        this.a = findViewById(R.id.alertDlgFrame);
        this.c = (TextView) findViewById(R.id.title);
        this.d = findViewById(R.id.dividerLine);
        this.b = (TextView) findViewById(R.id.textMessage);
        TextView textView = (TextView) findViewById(R.id.bnConfirm);
        if (z2) {
            this.b.setTextIsSelectable(true);
            findViewById(R.id.viewButtonDivider).setVisibility(8);
            findViewById(R.id.bnCancel).setVisibility(8);
            textView.setTextColor(c.a.d.a.a.a().a((int) R.color.CM));
        }
        textView.setOnClickListener(this);
        findViewById(R.id.bnCancel).setOnClickListener(this);
        this.e = z;
        this.f = aVar;
        this.b.setText(str2);
        this.c.setText(str);
        if (str == null) {
            this.c.setVisibility(8);
            this.d.setVisibility(8);
        }
    }

    public void setConfirmTip(String str) {
        ((TextView) findViewById(R.id.bnConfirm)).setText(str);
    }

    public void setCancelTip(String str) {
        ((TextView) findViewById(R.id.bnCancel)).setText(str);
    }

    public static boolean a(Activity activity) {
        f b = b(activity);
        if (b == null || !b.a()) {
            return false;
        }
        if (b.e) {
            b.c();
            if (b.f != null) {
                b.f.a(false);
            }
        }
        return true;
    }

    private static f b(Activity activity) {
        View c = c(b.a(activity));
        if (c == null) {
            return null;
        }
        return (f) c.findViewById(R.id.view_alert_dlg);
    }

    private static ViewGroup c(Activity activity) {
        return (ViewGroup) activity.findViewById(16908290);
    }

    public boolean a() {
        return getVisibility() == 0;
    }

    public void b() {
        if (8 == getVisibility()) {
            setVisibility(0);
            this.g.addView(this);
        }
    }

    public void c() {
        if (getVisibility() == 0) {
            setVisibility(8);
            this.g.removeView(this);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.e && motionEvent.getAction() == 0) {
            Rect rect = new Rect();
            int rawX = (int) motionEvent.getRawX();
            int rawY = (int) motionEvent.getRawY();
            this.a.getGlobalVisibleRect(rect);
            if (!rect.contains(rawX, rawY)) {
                c();
                if (this.f != null) {
                    this.f.a(false);
                }
            }
        }
        return true;
    }

    public void onClick(View view) {
        c();
        if (this.f != null) {
            this.f.a(view.getId() == R.id.bnConfirm);
        }
    }
}
