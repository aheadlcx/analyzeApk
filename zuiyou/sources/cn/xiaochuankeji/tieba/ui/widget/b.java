package cn.xiaochuankeji.tieba.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.htjyb.b.a.d;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.post.postdetail.PostAlbumQueryListView;

public abstract class b implements OnClickListener {
    protected Context a;
    protected View b;
    protected View c;
    protected View d;
    private LayoutInflater e;
    private d f;
    private a g;
    private boolean h = false;
    private View i;
    private PostAlbumQueryListView j;
    private RelativeLayout k;
    private ViewGroup l;

    class a extends BaseAdapter {
        final /* synthetic */ b a;

        a(b bVar) {
            this.a = bVar;
        }

        public int getCount() {
            return this.a.f.itemCount();
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = this.a.e.inflate(R.layout.view_item_select_post_album, null);
            }
            TextView textView = (TextView) view.findViewById(R.id.tvIntroduce);
            ((TextView) view.findViewById(R.id.tvName)).setText(this.a.a(i));
            textView.setText(this.a.b(i));
            if (i == getCount() - 1) {
                view.findViewById(R.id.line).setVisibility(8);
            } else {
                view.findViewById(R.id.line).setVisibility(0);
            }
            return view;
        }
    }

    protected abstract d a();

    protected abstract String a(int i);

    protected abstract String b(int i);

    protected abstract void b();

    protected abstract void c(int i);

    public b(Context context) {
        this.a = context;
        c();
    }

    private void c() {
        this.e = LayoutInflater.from(this.a);
        this.i = this.e.inflate(R.layout.dialog_listview, null);
        this.i.setId(R.id.id_add_to_favorite_view);
        this.c = this.i.findViewById(R.id.tvCancel);
        this.b = this.i.findViewById(R.id.tvAdd);
        this.d = this.i.findViewById(R.id.tv_empty_tip);
        this.l = (ViewGroup) cn.htjyb.ui.b.a((Activity) this.a).findViewById(16908290);
        this.k = (RelativeLayout) this.i.findViewById(R.id.vgActionContainer);
        this.j = (PostAlbumQueryListView) this.i.findViewById(R.id.lvPostAlbum);
        this.j.f();
        this.i.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    Rect rect = new Rect();
                    int rawX = (int) motionEvent.getRawX();
                    int rawY = (int) motionEvent.getRawY();
                    this.a.k.getGlobalVisibleRect(rect);
                    if (!rect.contains(rawX, rawY)) {
                        this.a.f();
                    }
                }
                return true;
            }
        });
        this.f = a();
        this.g = new a(this);
        this.j.a(this.f, this.g);
        if (this.f.itemCount() > 0) {
            this.d.setVisibility(8);
        }
        this.j.m().setHorizontalScrollBarEnabled(false);
        this.j.m().setVerticalScrollBarEnabled(false);
        this.j.m().setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                this.a.c(i - 1);
            }
        });
        this.f.registerOnQueryFinishListener(new cn.htjyb.b.a.b.b(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(boolean z, boolean z2, String str) {
                if (z && z2) {
                    this.a.g.notifyDataSetChanged();
                    this.a.d();
                }
            }
        });
        this.b.setOnClickListener(this);
        this.c.setOnClickListener(this);
    }

    public void e() {
        this.h = true;
        this.l.removeView(this.i);
        this.l.addView(this.i);
        this.g.notifyDataSetChanged();
        if (this.f.itemCount() == 0) {
            this.f.refresh();
        }
        d();
    }

    public void f() {
        this.h = false;
        this.l.removeView(this.i);
        this.j.c();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvCancel:
                f();
                return;
            case R.id.tvAdd:
                b();
                return;
            default:
                return;
        }
    }

    private void d() {
        if (this.g.getCount() == 0) {
            this.d.setVisibility(0);
        } else {
            this.d.setVisibility(8);
        }
    }

    protected void g() {
        d();
        this.g.notifyDataSetChanged();
        this.j.m().post(new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.j.m().setSelection(0);
            }
        });
    }

    public static boolean a(Activity activity) {
        ViewGroup viewGroup = (ViewGroup) cn.htjyb.ui.b.a(activity).findViewById(16908290);
        if (viewGroup == null) {
            return false;
        }
        View findViewById = viewGroup.findViewById(R.id.id_add_to_favorite_view);
        if (findViewById == null) {
            return false;
        }
        viewGroup.removeView(findViewById);
        return true;
    }
}
