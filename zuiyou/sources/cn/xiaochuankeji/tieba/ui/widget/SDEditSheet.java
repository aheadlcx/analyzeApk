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

public class SDEditSheet extends FrameLayout implements OnClickListener {
    private LinearLayout a;
    private TextView b;
    private View c;
    private Activity d;
    private a e;
    private String f;
    private b g;

    public interface a {
        void a(int i);
    }

    public interface b {
        void a(boolean z);
    }

    public SDEditSheet(Activity activity, a aVar, String str) {
        super(activity);
        this.d = activity;
        this.f = str;
        this.e = aVar;
        LayoutInflater.from(activity).inflate(R.layout.dialog_sheet, this);
        getViews();
        e();
        setId(R.id.sd_edit_sheet);
    }

    public void setTitle(String str) {
        this.f = str;
        this.b.setText(this.f);
    }

    private void getViews() {
        this.a = (LinearLayout) findViewById(R.id.vgActionContainer);
        this.c = findViewById(R.id.viewBlueLine);
        this.b = (TextView) findViewById(R.id.tvTitle);
        if (this.f == null || this.f.trim().equals("")) {
            this.c.setVisibility(8);
            this.b.setVisibility(8);
        }
    }

    private void d() {
        ViewGroup c = c(cn.htjyb.ui.b.a(this.d));
        setLayoutParams(new LayoutParams(-1, -1));
        c.addView(this);
    }

    private void e() {
        this.b.setText(this.f);
    }

    private static SDEditSheet b(Activity activity) {
        View c = c(activity);
        if (c == null) {
            return null;
        }
        return (SDEditSheet) c.findViewById(R.id.sd_edit_sheet);
    }

    private static ViewGroup c(Activity activity) {
        return (ViewGroup) activity.findViewById(16908290);
    }

    public static boolean a(Activity activity) {
        SDEditSheet b = b(activity);
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
        if (b(this.d) == null) {
            d();
        }
        if (this.g != null) {
            this.g.a(true);
        }
    }

    public void c() {
        c(cn.htjyb.ui.b.a(this.d)).removeView(this);
        if (this.g != null) {
            this.g.a(false);
        }
    }

    public void setOnVisibleChangeListener(b bVar) {
        this.g = bVar;
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

    public void a(String str, int i) {
        a(str, i, false);
    }

    public void a(String str, int i, boolean z) {
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.view_textview_sheet, null);
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
        this.e.a(((Integer) view.getTag()).intValue());
    }
}
