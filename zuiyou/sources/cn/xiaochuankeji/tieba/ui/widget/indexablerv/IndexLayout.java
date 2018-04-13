package cn.xiaochuankeji.tieba.ui.widget.indexablerv;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import c.a.d.a.a;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.indexablerv.a.b;
import cn.xiaochuankeji.tieba.ui.widget.indexablerv.a.d;
import cn.xiaochuankeji.tieba.ui.widget.indexablerv.a.f;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class IndexLayout extends FrameLayout {
    private d<b> A;
    private f B;
    private int a;
    private int b;
    private Context c;
    private boolean d;
    private ExecutorService e;
    private Future f;
    private RecyclerView g;
    private d h;
    private View i;
    private boolean j;
    private ViewHolder k;
    private String l;
    private j m;
    private LayoutManager n;
    private c o;
    private TextView p;
    private int q;
    private int r;
    private float s;
    private float t;
    private float u;
    private Drawable v;
    private b w;
    private int x;
    private Comparator y;
    private Handler z;

    public IndexLayout(Context context) {
        this(context, null);
    }

    public IndexLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public IndexLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = true;
        this.j = true;
        this.x = 0;
        this.A = new d<b>(this) {
            final /* synthetic */ IndexLayout a;

            {
                this.a = r1;
            }
        };
        this.B = new f(this) {
            final /* synthetic */ IndexLayout a;

            {
                this.a = r1;
            }
        };
        a(context, attributeSet);
    }

    public <T extends f> void setAdapter(final c<T> cVar) {
        if (this.n == null) {
            throw new NullPointerException("You must set the LayoutManager first");
        }
        this.o = cVar;
        if (this.w != null) {
            cVar.b(this.w);
        }
        this.w = new b(this) {
            final /* synthetic */ IndexLayout b;

            public void a() {
                a(0);
                this.b.b();
            }

            public void a(int i) {
                if ((i == 1 || i == 0) && cVar.c() != null) {
                    this.b.m.a(cVar.c());
                }
                if ((i == 3 || i == 0) && cVar.d() != null) {
                    this.b.m.a(cVar.d());
                }
                if ((i == 2 || i == 0) && cVar.e() != null) {
                    this.b.m.a(cVar.e());
                }
                if ((i == 4 || i == 0) && cVar.f() != null) {
                    this.b.m.a(cVar.f());
                }
            }
        };
        cVar.a(this.w);
        this.m.a((c) cVar);
        if (this.j) {
            a((c) cVar);
        }
    }

    public <T> void a(e<T> eVar) {
        eVar.a(this.A);
        eVar.a(this.B);
        this.m.a((e) eVar);
    }

    @Deprecated
    public void setFastCompare(boolean z) {
        setCompareMode(z ? 0 : 1);
    }

    public void setCompareMode(int i) {
        this.x = i;
    }

    public <T extends f> void setComparator(Comparator<b<T>> comparator) {
        this.y = comparator;
    }

    public void setStickyEnable(boolean z) {
        this.j = z;
    }

    public void a() {
        if (this.p == null) {
            e();
        }
    }

    public TextView getOverlayView() {
        return this.p;
    }

    public RecyclerView getRecyclerView() {
        return this.g;
    }

    public void setIndexBarVisibility(boolean z) {
        this.h.setVisibility(z ? 0 : 8);
    }

    private void a(Context context, AttributeSet attributeSet) {
        this.c = context;
        this.e = Executors.newSingleThreadExecutor();
        this.a = (int) TypedValue.applyDimension(1, 0.0f, getResources().getDisplayMetrics());
        this.b = (int) TypedValue.applyDimension(1, 1.0f, getResources().getDisplayMetrics());
        this.r = a.a().a((int) R.color.CM);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.IndexRecyclerView);
            this.q = ContextCompat.getColor(context, R.color.default_indexBar_textColor);
            this.s = obtainStyledAttributes.getDimension(1, getResources().getDimension(R.dimen.default_indexBar_textSize));
            this.t = obtainStyledAttributes.getDimension(2, getResources().getDimension(R.dimen.default_indexBar_textSpace));
            this.v = obtainStyledAttributes.getDrawable(3);
            this.u = obtainStyledAttributes.getDimension(4, getResources().getDimension(R.dimen.default_indexBar_layout_width));
            obtainStyledAttributes.recycle();
        }
        if (this.c instanceof Activity) {
            ((Activity) this.c).getWindow().setSoftInputMode(32);
        }
        this.g = new RecyclerView(context);
        this.g.setVerticalScrollBarEnabled(false);
        this.g.setOverScrollMode(2);
        addView(this.g, new LayoutParams(-1, -1));
        this.h = new d(context);
        this.h.a(this.v, this.q, this.r, this.s, this.t);
        ViewGroup.LayoutParams layoutParams = new LayoutParams((int) this.u, -1);
        layoutParams.gravity = 8388629;
        layoutParams.setMargins(0, this.a, this.b, 0);
        addView(this.h, layoutParams);
        this.m = new j();
        this.g.setHasFixedSize(true);
        this.g.setAdapter(this.m);
        c();
    }

    public void setLayoutManager(LayoutManager layoutManager) {
        if (layoutManager == null) {
            throw new NullPointerException("LayoutManager == null");
        }
        this.n = layoutManager;
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new SpanSizeLookup(this) {
                final /* synthetic */ IndexLayout b;

                public int getSpanSize(int i) {
                    if (this.b.m.getItemViewType(i) == 2147483646) {
                        return gridLayoutManager.getSpanCount();
                    }
                    if (this.b.m.getItemViewType(i) == Integer.MAX_VALUE) {
                        return 1;
                    }
                    return 0;
                }
            });
        }
        this.g.setLayoutManager(this.n);
    }

    private void c() {
        this.g.addOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ IndexLayout a;

            {
                this.a = r1;
            }

            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                this.a.d();
            }
        });
        this.h.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ IndexLayout a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                int a = this.a.h.a(motionEvent.getY());
                if (a >= 0 && (this.a.n instanceof LinearLayoutManager)) {
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) this.a.n;
                    switch (motionEvent.getAction()) {
                        case 0:
                        case 2:
                            this.a.a(motionEvent.getY(), a);
                            if (a != this.a.h.a()) {
                                this.a.h.a(a);
                                if (a != 0) {
                                    linearLayoutManager.scrollToPositionWithOffset(this.a.h.b(), 0);
                                    break;
                                }
                                linearLayoutManager.scrollToPositionWithOffset(0, 0);
                                break;
                            }
                            break;
                        case 1:
                        case 3:
                            if (this.a.p != null) {
                                this.a.p.setVisibility(8);
                                break;
                            }
                            break;
                        default:
                            break;
                    }
                }
                return true;
            }
        });
    }

    private void d() {
        if (this.n instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) this.n;
            int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
            if (findFirstVisibleItemPosition != -1) {
                this.h.b(findFirstVisibleItemPosition);
                if (this.j) {
                    ArrayList a = this.m.a();
                    if (this.k != null && a.size() > findFirstVisibleItemPosition) {
                        b bVar = (b) a.get(findFirstVisibleItemPosition);
                        String b = bVar.b();
                        if (2147483646 == bVar.g()) {
                            if (this.i != null && this.i.getVisibility() == 4) {
                                this.i.setVisibility(0);
                                this.i = null;
                            }
                            this.i = linearLayoutManager.findViewByPosition(findFirstVisibleItemPosition);
                            if (this.i != null) {
                                this.i.setVisibility(4);
                            }
                        }
                        if (b == null && this.k.itemView.getVisibility() == 0) {
                            this.l = null;
                            this.k.itemView.setVisibility(4);
                        } else {
                            a(b);
                        }
                        if (this.n instanceof GridLayoutManager) {
                            GridLayoutManager gridLayoutManager = (GridLayoutManager) this.n;
                            if (gridLayoutManager.getSpanCount() + findFirstVisibleItemPosition < a.size()) {
                                for (int i = findFirstVisibleItemPosition + 1; i <= gridLayoutManager.getSpanCount() + findFirstVisibleItemPosition; i++) {
                                    a(linearLayoutManager, a, i, b);
                                }
                            }
                        } else if (findFirstVisibleItemPosition + 1 < a.size()) {
                            a(linearLayoutManager, a, findFirstVisibleItemPosition + 1, b);
                        }
                    }
                }
            }
        }
    }

    private void a(LinearLayoutManager linearLayoutManager, ArrayList<b> arrayList, int i, String str) {
        b bVar = (b) arrayList.get(i);
        View findViewByPosition = linearLayoutManager.findViewByPosition(i);
        if (findViewByPosition != null) {
            if (bVar.g() == 2147483646) {
                if (findViewByPosition.getTop() <= this.k.itemView.getHeight() && str != null) {
                    this.k.itemView.setTranslationY((float) (findViewByPosition.getTop() - this.k.itemView.getHeight()));
                }
                if (4 == findViewByPosition.getVisibility()) {
                    findViewByPosition.setVisibility(0);
                }
            } else if (this.k.itemView.getTranslationY() != 0.0f) {
                this.k.itemView.setTranslationY(0.0f);
            }
        }
    }

    private void a(String str) {
        if (str != null && !str.equals(this.l)) {
            if (this.k.itemView.getVisibility() != 0) {
                this.k.itemView.setVisibility(0);
            }
            this.l = str;
            this.o.a(this.k, str);
        }
    }

    private <T extends f> void a(final c<T> cVar) {
        this.k = cVar.a(this.g);
        this.k.itemView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ IndexLayout b;

            public void onClick(View view) {
                if (cVar.c() != null) {
                    int b = this.b.h.b();
                    ArrayList a = this.b.m.a();
                    if (a.size() > b && b >= 0) {
                        cVar.c().a(view, b, ((b) a.get(b)).b());
                    }
                }
            }
        });
        this.k.itemView.setOnLongClickListener(new OnLongClickListener(this) {
            final /* synthetic */ IndexLayout b;

            public boolean onLongClick(View view) {
                if (cVar.d() != null) {
                    int b = this.b.h.b();
                    ArrayList a = this.b.m.a();
                    if (a.size() > b && b >= 0) {
                        return cVar.d().a(view, b, ((b) a.get(b)).b());
                    }
                }
                return false;
            }
        });
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i) == this.g) {
                this.k.itemView.setVisibility(4);
                addView(this.k.itemView, i + 1);
                return;
            }
        }
    }

    private void a(float f, int i) {
        if (this.h.c().size() > i && this.p != null) {
            if (this.p.getVisibility() != 0) {
                this.p.setVisibility(0);
            }
            String str = (String) this.h.c().get(i);
            if (!this.p.getText().equals(str)) {
                if (str.length() > 1) {
                    this.p.setTextSize(32.0f);
                }
                this.p.setText(str);
            }
        }
    }

    private void e() {
        this.p = new TextView(this.c);
        this.p.setBackgroundResource(R.drawable.indexable_bg_center_overlay);
        this.p.setTextColor(-1);
        this.p.setTextSize(40.0f);
        this.p.setGravity(17);
        int applyDimension = (int) TypedValue.applyDimension(1, 70.0f, getResources().getDisplayMetrics());
        ViewGroup.LayoutParams layoutParams = new LayoutParams(applyDimension, applyDimension);
        layoutParams.gravity = 17;
        this.p.setLayoutParams(layoutParams);
        this.p.setVisibility(4);
        addView(this.p);
    }

    void b() {
        if (this.f != null) {
            this.f.cancel(true);
        }
        this.f = this.e.submit(new Runnable(this) {
            final /* synthetic */ IndexLayout a;

            {
                this.a = r1;
            }

            public void run() {
                final ArrayList a = this.a.a(this.a.o.a());
                if (a != null) {
                    this.a.getSafeHandler().post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass10 b;

                        public void run() {
                            this.b.a.m.a(a);
                            this.b.a.h.a(this.b.a.d, this.b.a.m.a());
                            if (this.b.a.o.b() != null) {
                                this.b.a.o.b().a(a);
                            }
                            this.b.a.d();
                        }
                    });
                }
            }
        });
    }

    private <T extends f> ArrayList<b<T>> a(List<T> list) {
        try {
            List list2;
            TreeMap treeMap = new TreeMap(new Comparator<String>(this) {
                final /* synthetic */ IndexLayout a;

                {
                    this.a = r1;
                }

                public /* synthetic */ int compare(Object obj, Object obj2) {
                    return a((String) obj, (String) obj2);
                }

                public int a(String str, String str2) {
                    if (str.equals("#")) {
                        return str2.equals("#") ? 0 : 1;
                    } else {
                        if (str2.equals("#")) {
                            return -1;
                        }
                        return str.compareTo(str2);
                    }
                }
            });
            for (int i = 0; i < list.size(); i++) {
                b bVar = new b();
                Object obj = (f) list.get(i);
                String a = obj.a();
                String a2 = i.a(a);
                bVar.c(a2);
                if (i.b(a2)) {
                    bVar.a(a2.substring(0, 1).toUpperCase());
                    bVar.d(obj.a());
                } else if (i.c(a2)) {
                    bVar.a(i.d(a2).toUpperCase());
                    bVar.c(i.e(a2));
                    a = i.f(a);
                    bVar.d(a);
                    obj.a(a);
                } else {
                    bVar.a("#");
                    bVar.d(obj.a());
                }
                bVar.b(bVar.a());
                bVar.a(obj);
                bVar.a(i);
                obj.b(bVar.c());
                a = bVar.a();
                if (treeMap.containsKey(a)) {
                    list2 = (List) treeMap.get(a);
                } else {
                    list2 = new ArrayList();
                    list2.add(new b(bVar.a(), 2147483646));
                    treeMap.put(a, list2);
                }
                list2.add(bVar);
            }
            ArrayList<b<T>> arrayList = new ArrayList();
            for (List list22 : treeMap.values()) {
                if (this.y != null) {
                    Collections.sort(list22, this.y);
                } else if (this.x == 0) {
                    Collections.sort(list22, new g());
                } else if (this.x == 1) {
                    Collections.sort(list22, new h());
                }
                arrayList.addAll(list22);
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Handler getSafeHandler() {
        if (this.z == null) {
            this.z = new Handler(Looper.getMainLooper());
        }
        return this.z;
    }
}
