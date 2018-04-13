package cn.xiaochuankeji.tieba.ui.tag;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.tag.NavigatorTag;
import java.util.ArrayList;

public class c extends Adapter<a> implements OnClickListener, OnLongClickListener, cn.xiaochuankeji.tieba.ui.tag.a.a {
    public ArrayList<NavigatorTag> a = new ArrayList();
    private cn.xiaochuankeji.tieba.ui.tag.a.c b;
    private b c = null;

    public interface b {
        void a();

        void a(View view);

        void b(View view);
    }

    public static class a extends ViewHolder implements cn.xiaochuankeji.tieba.ui.tag.a.b {
        public final TextView a;
        public final View b;

        public a(View view) {
            super(view);
            this.a = (TextView) view.findViewById(R.id.tag_name);
            this.b = view.findViewById(R.id.tag_new);
        }

        public void a() {
        }

        public void b() {
        }
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((a) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return a(viewGroup, i);
    }

    public c(cn.xiaochuankeji.tieba.ui.tag.a.c cVar, ArrayList<NavigatorTag> arrayList) {
        this.b = cVar;
        this.a.addAll(arrayList);
    }

    public a a(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_nav_tag, viewGroup, false);
        a aVar = new a(inflate);
        inflate.setOnClickListener(this);
        inflate.setOnLongClickListener(this);
        return aVar;
    }

    public void a(a aVar, int i) {
        if (i >= 0 && i < this.a.size()) {
            NavigatorTag navigatorTag = (NavigatorTag) this.a.get(i);
            aVar.a.setText(navigatorTag.name);
            if (navigatorTag.frozen == 1) {
                aVar.a.setBackground(c.a.d.a.a.a().b(R.drawable.bg_item_nav_frozen_tag));
                aVar.a.setTextColor(c.a.d.a.a.a().a((int) R.color.CW));
                aVar.itemView.setOnLongClickListener(null);
            }
            if (!navigatorTag.isNew || i <= 4) {
                aVar.b.setVisibility(8);
            } else {
                aVar.b.setVisibility(0);
            }
        }
    }

    public void a(int i) {
    }

    public boolean a(int i, int i2) {
        NavigatorTag navigatorTag = (NavigatorTag) this.a.get(i);
        if (navigatorTag.frozen == 1 || ((NavigatorTag) this.a.get(i2)).frozen == 1) {
            return false;
        }
        if (i2 < i) {
            this.a.add(i2, navigatorTag);
            this.a.remove(i + 1);
        } else {
            this.a.add(i2 + 1, navigatorTag);
            this.a.remove(i);
        }
        try {
            notifyItemMoved(i, i2);
            if (this.c != null) {
                this.c.a();
            }
        } catch (Exception e) {
        }
        return true;
    }

    public int getItemCount() {
        return this.a.size();
    }

    public void a(b bVar) {
        this.c = bVar;
    }

    public void onClick(View view) {
        if (this.c != null) {
            this.c.a(view);
        }
    }

    public boolean onLongClick(View view) {
        if (this.c != null) {
            this.c.b(view);
        }
        return false;
    }
}
