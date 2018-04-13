package cn.xiaochuankeji.tieba.ui.chat.adapter;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.push.b.e;
import cn.xiaochuankeji.tieba.push.d;
import cn.xiaochuankeji.tieba.push.data.XMessage;
import cn.xiaochuankeji.tieba.push.data.XSession;
import cn.xiaochuankeji.tieba.ui.chat.a.c;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class a extends Adapter<cn.xiaochuankeji.tieba.ui.chat.holder.a> {
    private LinkedList<cn.xiaochuankeji.tieba.push.data.a> a = new LinkedList();
    private XSession b;
    private c c;

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((cn.xiaochuankeji.tieba.ui.chat.holder.a) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return a(viewGroup, i);
    }

    public a() {
        this.a.clear();
    }

    public cn.xiaochuankeji.tieba.ui.chat.holder.a a(ViewGroup viewGroup, int i) {
        return cn.xiaochuankeji.tieba.ui.chat.holder.a.a(viewGroup, i, this.b, this.c, this);
    }

    public void a(cn.xiaochuankeji.tieba.ui.chat.holder.a aVar, int i) {
        aVar.a(a(i), i);
    }

    public cn.xiaochuankeji.tieba.push.data.a a(int i) {
        if (i < 0 || i >= this.a.size()) {
            return null;
        }
        return (cn.xiaochuankeji.tieba.push.data.a) this.a.get(i);
    }

    public int getItemCount() {
        return this.a.size();
    }

    public int getItemViewType(int i) {
        return a(i).i;
    }

    public void a(List<cn.xiaochuankeji.tieba.push.data.a> list) {
        this.a.clear();
        this.a.addAll(list);
        notifyDataSetChanged();
    }

    public LinkedList<cn.xiaochuankeji.tieba.push.data.a> a() {
        return this.a;
    }

    public void a(cn.xiaochuankeji.tieba.push.data.a aVar) {
        cn.xiaochuankeji.tieba.push.data.a aVar2;
        if (this.a.size() > 1) {
            aVar2 = (cn.xiaochuankeji.tieba.push.data.a) this.a.getLast();
            if (aVar2.i != R.layout.view_item_chat_timeline) {
                aVar2 = d.a(aVar2.k, aVar.k);
                if (aVar2 != null) {
                    this.a.add(aVar2);
                }
            }
        }
        this.a.add(aVar);
        notifyDataSetChanged();
        aVar2 = (cn.xiaochuankeji.tieba.push.data.a) this.a.getLast();
        XMessage xMessage = new XMessage();
        xMessage.update(aVar2);
        this.b.x_msg = xMessage;
        this.b.time = aVar2.k;
        e.i(this.b);
        e.g(this.b);
    }

    public void a(long j, cn.xiaochuankeji.tieba.push.data.a aVar) {
        for (int size = this.a.size() - 1; size > 0; size--) {
            cn.xiaochuankeji.tieba.push.data.a aVar2 = (cn.xiaochuankeji.tieba.push.data.a) this.a.get(size);
            if (aVar2.j == j || aVar2.j == aVar.j) {
                aVar2.f = aVar.f;
                aVar2.k = aVar.k;
                aVar2.h = aVar.h;
                aVar2.j = aVar.j;
                break;
            }
        }
        notifyDataSetChanged();
    }

    public void a(XSession xSession) {
        this.b = xSession;
    }

    public void b(cn.xiaochuankeji.tieba.push.data.a aVar) {
        e.a(this.b, aVar);
        d.a(aVar, this.b);
        int indexOf = this.a.indexOf(aVar);
        if (indexOf >= 0) {
            cn.xiaochuankeji.tieba.push.data.a aVar2;
            Object obj;
            XMessage xMessage;
            if (indexOf > 0) {
                aVar2 = (cn.xiaochuankeji.tieba.push.data.a) this.a.get(indexOf - 1);
                if (aVar2.i == R.layout.view_item_chat_timeline) {
                    this.a.remove(aVar2);
                    obj = 1;
                    this.a.remove(aVar);
                    if (obj == null) {
                        notifyItemRangeRemoved(indexOf - 1, 2);
                    } else {
                        notifyItemRemoved(indexOf);
                    }
                    if (this.a.isEmpty()) {
                        aVar2 = (cn.xiaochuankeji.tieba.push.data.a) this.a.getLast();
                        xMessage = new XMessage();
                        xMessage.update(aVar2);
                        this.b.x_msg = xMessage;
                        this.b.time = aVar2.k;
                        e.g(this.b);
                    } else {
                        e.h(this.b);
                        cn.xiaochuankeji.tieba.background.h.d.a().d();
                    }
                    notifyDataSetChanged();
                }
            }
            obj = null;
            this.a.remove(aVar);
            if (obj == null) {
                notifyItemRemoved(indexOf);
            } else {
                notifyItemRangeRemoved(indexOf - 1, 2);
            }
            if (this.a.isEmpty()) {
                aVar2 = (cn.xiaochuankeji.tieba.push.data.a) this.a.getLast();
                xMessage = new XMessage();
                xMessage.update(aVar2);
                this.b.x_msg = xMessage;
                this.b.time = aVar2.k;
                e.g(this.b);
            } else {
                e.h(this.b);
                cn.xiaochuankeji.tieba.background.h.d.a().d();
            }
            notifyDataSetChanged();
        }
    }

    public void b(List<cn.xiaochuankeji.tieba.push.data.a> list) {
        if (list != null && !list.isEmpty()) {
            int size = list.size();
            this.a.addAll(0, list);
            notifyItemRangeInserted(0, size);
        }
    }

    public void b() {
        this.a.clear();
    }

    public boolean a(long j) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            if (((cn.xiaochuankeji.tieba.push.data.a) it.next()).j == j) {
                return true;
            }
        }
        return false;
    }

    public cn.xiaochuankeji.tieba.push.data.a c() {
        for (int i = 0; i < this.a.size(); i++) {
            cn.xiaochuankeji.tieba.push.data.a aVar = (cn.xiaochuankeji.tieba.push.data.a) this.a.get(i);
            if (aVar.i != R.layout.view_item_chat_timeline) {
                return aVar;
            }
        }
        return null;
    }

    public void a(c cVar) {
        this.c = cVar;
    }
}
