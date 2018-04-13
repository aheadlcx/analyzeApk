package cn.xiaochuankeji.tieba.ui.widget;

import android.app.Activity;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import cn.xiaochuankeji.tieba.R;
import java.util.ArrayList;
import java.util.LinkedList;

public class SDGuideDialog extends FrameLayout {
    private Activity a;
    private FrameLayout b;
    private d c;
    private c d;
    private LinkedList<ArrayList<b>> e = new LinkedList();
    private ArrayList<a> f;

    public interface c {
        void a(SDGuideDialog sDGuideDialog);
    }

    public interface d {
        void a(SDGuideDialog sDGuideDialog);
    }

    private static class a {
        d a;
        c b;
        LinkedList<ArrayList<b>> c;

        private a() {
        }
    }

    private static class b {
        int a;
        int b;
        int c;
        int d;
        int e;
        int f;

        private b() {
        }
    }

    public SDGuideDialog(Activity activity) {
        super(activity);
        this.a = activity;
        LayoutInflater.from(activity).inflate(R.layout.dialog_guide, this);
        getViews();
        setId(R.id.sd_guide_dialog);
    }

    public void setBackgroundColor(int i) {
        this.b.setBackgroundColor(i);
    }

    private void getViews() {
        this.b = (FrameLayout) findViewById(R.id.rlContainer);
    }

    private static SDGuideDialog b(Activity activity) {
        View c = c(activity);
        if (c == null) {
            return null;
        }
        return (SDGuideDialog) c.findViewById(R.id.sd_guide_dialog);
    }

    private static ViewGroup c(Activity activity) {
        return (ViewGroup) activity.findViewById(16908290);
    }

    public static boolean a(Activity activity) {
        SDGuideDialog b = b(activity);
        if (b == null || !b.a()) {
            return false;
        }
        if (!b.d()) {
            b.c();
        }
        return true;
    }

    public boolean a() {
        return getVisibility() == 0;
    }

    public void b() {
        SDGuideDialog b = b(cn.htjyb.ui.b.a(this.a));
        if (b == null) {
            e();
            d();
            return;
        }
        a aVar = new a();
        aVar.a = this.c;
        aVar.b = this.d;
        aVar.c = this.e;
        b.a(aVar);
    }

    private void a(a aVar) {
        if (this.f == null) {
            this.f = new ArrayList();
        }
        this.f.add(aVar);
    }

    private boolean d() {
        if (this.e.isEmpty()) {
            if (this.d != null) {
                this.d.a(this);
                this.d = null;
            }
            if (!(this.f == null || this.f.isEmpty())) {
                a aVar = (a) this.f.remove(0);
                this.c = aVar.a;
                this.d = aVar.b;
                this.e.addAll(aVar.c);
            }
        }
        if (this.e.isEmpty()) {
            return false;
        }
        if (this.c != null) {
            this.c.a(this);
            this.c = null;
        }
        final ArrayList arrayList = (ArrayList) this.e.removeFirst();
        this.b.post(new Runnable(this) {
            final /* synthetic */ SDGuideDialog b;

            public void run() {
                int i = 0;
                while (i < arrayList.size()) {
                    ImageView imageView;
                    b bVar = (b) arrayList.get(i);
                    ImageView imageView2 = (ImageView) this.b.b.getChildAt(i);
                    if (imageView2 == null) {
                        View imageView3 = new ImageView(this.b.a);
                        this.b.b.addView(imageView3, new LayoutParams(-2, -2));
                        imageView = imageView3;
                    } else {
                        imageView = imageView2;
                    }
                    imageView.setVisibility(0);
                    LayoutParams layoutParams = (LayoutParams) imageView.getLayoutParams();
                    layoutParams.gravity = bVar.f;
                    layoutParams.leftMargin = bVar.b;
                    layoutParams.rightMargin = bVar.c;
                    layoutParams.topMargin = bVar.d;
                    layoutParams.bottomMargin = bVar.e;
                    imageView.setLayoutParams(layoutParams);
                    imageView.setBackgroundResource(bVar.a);
                    i++;
                }
                while (i < this.b.b.getChildCount()) {
                    this.b.b.getChildAt(i).setVisibility(4);
                    i++;
                }
            }
        });
        return true;
    }

    private void e() {
        ViewGroup c = c(cn.htjyb.ui.b.a(this.a));
        setLayoutParams(new LayoutParams(-1, -1));
        c.addView(this);
    }

    public void setOnShownListener(d dVar) {
        this.c = dVar;
    }

    public void setOnDismissListener(c cVar) {
        this.d = cVar;
    }

    public void c() {
        c(cn.htjyb.ui.b.a(this.a)).removeView(this);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 && !d()) {
            c();
        }
        return true;
    }

    public void a(View view, int i, int i2, int i3, int i4) {
        a(view, i, i2, i3, i4, true);
    }

    public void a(View view, int i, int i2, int i3, int i4, boolean z) {
        Rect rect;
        ArrayList arrayList;
        int i5 = 1;
        Rect rect2 = null;
        int i6 = 3;
        int i7 = 0;
        b bVar = new b();
        bVar.a = c.a.d.a.a.a().d(i);
        if (view != null) {
            rect = new Rect();
            view.getGlobalVisibleRect(rect);
            rect2 = new Rect();
            view.getWindowVisibleDisplayFrame(rect2);
        } else {
            rect = null;
        }
        int i8 = i2 & 7;
        int i9 = i2 & 112;
        if (i8 == 3) {
            bVar.b = (view != null ? rect.left - rect2.left : 0) + i3;
            bVar.c = 0;
        } else if (i8 == 5) {
            bVar.b = 0;
            if (view != null) {
                i5 = rect2.right - rect.right;
            } else {
                i5 = 0;
            }
            bVar.c = i5 + i3;
            i6 = 5;
        } else if (i8 == 1) {
            if (view != null) {
                bVar.b = ((rect.centerX() - (view.getContext().getResources().getDrawable(i).getIntrinsicWidth() / 2)) - rect2.left) + i3;
                i5 = 3;
            } else {
                bVar.b = i3;
            }
            bVar.c = 0;
            i6 = i5;
        } else {
            i6 = 0;
        }
        if (i9 == 48) {
            if (view != null) {
                i5 = rect.top - rect2.top;
            } else {
                i5 = 0;
            }
            bVar.d = i5 + i4;
            bVar.e = 0;
            i5 = i6 | 48;
        } else if (i9 == 80) {
            bVar.d = 0;
            if (view != null) {
                i7 = rect2.bottom - rect.bottom;
            }
            bVar.e = i7 + i4;
            i5 = i6 | 80;
        } else if (i9 == 16) {
            if (view != null) {
                bVar.d = ((rect.centerY() - (view.getContext().getResources().getDrawable(i).getIntrinsicHeight() / 2)) - rect2.top) + i4;
                i5 = i6 | 48;
            } else {
                bVar.d = i4;
                i5 = i6 | 16;
            }
            bVar.e = 0;
        } else {
            i5 = i6;
        }
        bVar.f = i5;
        if (this.e.isEmpty() || z) {
            arrayList = new ArrayList();
            this.e.add(arrayList);
        } else {
            arrayList = (ArrayList) this.e.getLast();
        }
        arrayList.add(bVar);
    }

    public void a(int i, int i2) {
        a(null, i, i2, 0, 0);
    }
}
