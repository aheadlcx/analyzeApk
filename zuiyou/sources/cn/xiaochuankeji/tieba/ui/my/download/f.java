package cn.xiaochuankeji.tieba.ui.my.download;

import android.app.Activity;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.htjyb.ui.b;
import cn.xiaochuankeji.tieba.R;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class f extends FrameLayout {
    private View a;
    private boolean b = true;
    private ViewGroup c;
    private LinearLayout d;
    private List<ImageView> e = new ArrayList();

    public interface a {
        void a(e eVar);

        void j();
    }

    public static f a(Activity activity, List<e> list, String str, a aVar) {
        Activity a = b.a(activity);
        f b = b(a);
        if (b != null) {
            b.c();
        }
        b = new f(a, list, str, aVar);
        b.b();
        return b;
    }

    public void setBackgroundColor(int i) {
        findViewById(R.id.alertDlgRoot).setBackgroundColor(i);
    }

    public f(Activity activity, final List<e> list, String str, final a aVar) {
        super(activity);
        LayoutInflater.from(activity).inflate(R.layout.view_select_sd_dlg, this);
        setId(R.id.view_select_dlg);
        setVisibility(8);
        setLayoutParams(new LayoutParams(-1, -1));
        this.c = c(activity);
        this.a = findViewById(R.id.alertDlgFrame);
        this.d = (LinearLayout) findViewById(R.id.ll_content);
        for (int i = 0; i < list.size(); i++) {
            View inflate = LayoutInflater.from(activity).inflate(R.layout.view_select_item, null);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.iv_select);
            this.e.add(imageView);
            ((TextView) inflate.findViewById(R.id.tv_name)).setText(((e) list.get(i)).a);
            ((TextView) inflate.findViewById(R.id.tv_content)).setText(((e) list.get(i)).b);
            if (((e) list.get(i)).b.equals(str)) {
                imageView.setVisibility(0);
            }
            inflate.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ f d;

                public void onClick(View view) {
                    File file = new File(((e) list.get(i)).b);
                    if (file.exists() || file.mkdirs()) {
                        aVar.a((e) list.get(i));
                        for (int i = 0; i < this.d.e.size(); i++) {
                            if (i == i) {
                                ((ImageView) this.d.e.get(i)).setVisibility(0);
                            } else {
                                ((ImageView) this.d.e.get(i)).setVisibility(4);
                            }
                        }
                        this.d.c();
                        return;
                    }
                    aVar.j();
                }
            });
            this.d.addView(inflate);
        }
    }

    public static boolean a(Activity activity) {
        f b = b(activity);
        if (b == null || !b.a()) {
            return false;
        }
        b.c();
        return true;
    }

    private static f b(Activity activity) {
        View c = c(b.a(activity));
        if (c == null) {
            return null;
        }
        return (f) c.findViewById(R.id.view_select_dlg);
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
            this.c.addView(this);
        }
    }

    public void c() {
        if (getVisibility() == 0) {
            setVisibility(8);
            this.c.removeView(this);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.b && motionEvent.getAction() == 0) {
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
}
