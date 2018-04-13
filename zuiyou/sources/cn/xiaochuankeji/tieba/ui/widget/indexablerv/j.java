package cn.xiaochuankeji.tieba.ui.widget.indexablerv;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import cn.xiaochuankeji.tieba.ui.widget.indexablerv.c.b;
import cn.xiaochuankeji.tieba.ui.widget.indexablerv.c.c;
import cn.xiaochuankeji.tieba.ui.widget.indexablerv.c.d;
import cn.xiaochuankeji.tieba.ui.widget.indexablerv.c.e;
import java.util.ArrayList;

class j<T extends f> extends Adapter<ViewHolder> {
    private ArrayList<b<T>> a = new ArrayList();
    private ArrayList<b<T>> b;
    private ArrayList<b<T>> c = new ArrayList();
    private ArrayList<b<T>> d = new ArrayList();
    private c<T> e;
    private SparseArray<e> f = new SparseArray();
    private SparseArray<Object> g = new SparseArray();
    private d h;
    private b<T> i;
    private e j;
    private c<T> k;

    j() {
    }

    void a(c<T> cVar) {
        this.e = cVar;
    }

    void a(e eVar) {
        this.c.addAll(0, eVar.e());
        this.a.addAll(0, eVar.e());
        this.f.put(eVar.a(), eVar);
        notifyDataSetChanged();
    }

    void a(ArrayList<b<T>> arrayList) {
        if (this.b != null && this.a.size() > this.c.size() + this.d.size()) {
            this.a.removeAll(this.b);
        }
        this.b = arrayList;
        this.a.addAll(this.c.size(), arrayList);
        notifyDataSetChanged();
    }

    ArrayList<b<T>> a() {
        return this.a;
    }

    public int getItemViewType(int i) {
        return ((b) this.a.get(i)).g();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        ViewHolder a;
        if (i == 2147483646) {
            a = this.e.a(viewGroup);
        } else if (i == Integer.MAX_VALUE) {
            a = this.e.b(viewGroup);
        } else {
            a aVar;
            if (this.f.indexOfKey(i) >= 0) {
                aVar = (a) this.f.get(i);
            } else {
                aVar = (a) this.g.get(i);
            }
            a = aVar.a(viewGroup);
        }
        a.itemView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ j c;

            public void onClick(View view) {
                int adapterPosition = a.getAdapterPosition();
                if (adapterPosition != -1) {
                    b bVar = (b) this.c.a.get(adapterPosition);
                    if (i == 2147483646) {
                        if (this.c.h != null) {
                            this.c.h.a(view, adapterPosition, bVar.b());
                        }
                    } else if (i != Integer.MAX_VALUE) {
                        a aVar;
                        if (this.c.f.indexOfKey(i) >= 0) {
                            aVar = (a) this.c.f.get(i);
                        } else {
                            aVar = (a) this.c.g.get(i);
                        }
                        if (aVar != null) {
                            a c = aVar.c();
                            if (c != null) {
                                c.a(view, adapterPosition, bVar.e());
                            }
                        }
                    } else if (this.c.i != null) {
                        this.c.i.a(view, bVar.f(), adapterPosition, bVar.e());
                    }
                }
            }
        });
        a.itemView.setOnLongClickListener(new OnLongClickListener(this) {
            final /* synthetic */ j c;

            public boolean onLongClick(View view) {
                int adapterPosition = a.getAdapterPosition();
                b bVar = (b) this.c.a.get(adapterPosition);
                if (i == 2147483646) {
                    boolean z;
                    if (this.c.j == null || this.c.j.a(view, adapterPosition, bVar.b())) {
                        z = true;
                    } else {
                        z = false;
                    }
                    return z;
                } else if (i != Integer.MAX_VALUE) {
                    a aVar;
                    if (this.c.f.indexOfKey(i) >= 0) {
                        aVar = (a) this.c.f.get(i);
                    } else {
                        aVar = (a) this.c.g.get(i);
                    }
                    if (aVar == null) {
                        return false;
                    }
                    b d = aVar.d();
                    if (d != null) {
                        return d.a(view, adapterPosition, bVar.e());
                    }
                    return false;
                } else if (this.c.k == null || this.c.k.a(view, bVar.f(), adapterPosition, bVar.e())) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        return a;
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        b bVar = (b) this.a.get(i);
        int itemViewType = getItemViewType(i);
        if (itemViewType == 2147483646) {
            if (4 == viewHolder.itemView.getVisibility()) {
                viewHolder.itemView.setVisibility(0);
            }
            this.e.a(viewHolder, bVar.b());
        } else if (itemViewType == Integer.MAX_VALUE) {
            this.e.a(viewHolder, (f) bVar.e());
        } else {
            a aVar;
            if (this.f.indexOfKey(itemViewType) >= 0) {
                aVar = (a) this.f.get(itemViewType);
            } else {
                aVar = (a) this.g.get(itemViewType);
            }
            aVar.a(viewHolder, bVar.e());
        }
    }

    public int getItemCount() {
        return this.a.size();
    }

    void a(d dVar) {
        this.h = dVar;
    }

    void a(b<T> bVar) {
        this.i = bVar;
    }

    void a(e eVar) {
        this.j = eVar;
    }

    void a(c<T> cVar) {
        this.k = cVar;
    }
}
