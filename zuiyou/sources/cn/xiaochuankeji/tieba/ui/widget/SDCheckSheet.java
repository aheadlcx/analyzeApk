package cn.xiaochuankeji.tieba.ui.widget;

import android.app.Activity;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;

public class SDCheckSheet extends FrameLayout implements OnClickListener {
    private LinearLayout a = ((LinearLayout) findViewById(R.id.vgActionContainer));
    private Activity b;
    private a c;
    private b d;

    public interface a {
        void a(int i);
    }

    public interface b {
        void a(boolean z);
    }

    public SDCheckSheet(Activity activity, a aVar) {
        super(activity);
        this.b = activity;
        this.c = aVar;
        LayoutInflater.from(activity).inflate(R.layout.dialog_check_sheet, this);
        d();
        setId(R.id.sd_check_sheet);
    }

    private void d() {
        ViewGroup c = c(cn.htjyb.ui.b.a(this.b));
        setLayoutParams(new LayoutParams(-1, -1));
        setVisibility(8);
        c.addView(this);
    }

    private static SDCheckSheet b(Activity activity) {
        View c = c(activity);
        if (c == null) {
            return null;
        }
        return (SDCheckSheet) c.findViewById(R.id.sd_check_sheet);
    }

    private static ViewGroup c(Activity activity) {
        return (ViewGroup) activity.findViewById(16908290);
    }

    public static boolean a(Activity activity) {
        SDCheckSheet b = b(activity);
        if (b == null || !b.a()) {
            return false;
        }
        b.c();
        return true;
    }

    public boolean a() {
        return getVisibility() == 0;
    }

    public void b() {
        setVisibility(0);
        if (this.d != null) {
            this.d.a(true);
        }
    }

    public void c() {
        c(cn.htjyb.ui.b.a(this.b)).removeView(this);
        if (this.d != null) {
            this.d.a(false);
        }
    }

    public void setOnVisibleChangeListener(b bVar) {
        this.d = bVar;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            Rect rect = new Rect();
            int rawX = (int) motionEvent.getRawX();
            int rawY = (int) motionEvent.getRawY();
            this.a.getGlobalVisibleRect(rect);
            if (!rect.contains(rawX, rawY)) {
                c();
            }
        }
        return true;
    }

    public void a(String str, int i, boolean z) {
        View inflate = LayoutInflater.from(this.b).inflate(R.layout.view_textview_sheet, null);
        TextView textView = (TextView) inflate.findViewById(R.id.tvItem);
        View findViewById = inflate.findViewById(R.id.dividerLine);
        if (z) {
            findViewById.setVisibility(8);
        }
        textView.setText(str);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.linearItem);
        linearLayout.setOnClickListener(this);
        linearLayout.setTag(Integer.valueOf(i));
        this.a.addView(inflate);
    }

    public void onClick(View view) {
        c();
        this.c.a(((Integer) view.getTag()).intValue());
    }
}
