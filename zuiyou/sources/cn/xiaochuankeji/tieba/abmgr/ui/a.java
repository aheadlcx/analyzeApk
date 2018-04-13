package cn.xiaochuankeji.tieba.abmgr.ui;

import android.app.Activity;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.abmgr.data.RequireStyle;
import cn.xiaochuankeji.tieba.abmgr.data.Requirement;
import cn.xiaochuankeji.tieba.abmgr.ui.a.d;
import java.util.List;

public class a extends Adapter {
    private List<cn.xiaochuankeji.tieba.abmgr.ui.a.a> a;
    private Activity b;
    private c c;

    interface c {
        void onClick(Requirement requirement, RequireStyle requireStyle);
    }

    private class a extends ViewHolder {
        final /* synthetic */ a a;
        private ImageView b;
        private TextView c;

        a(a aVar, View view) {
            this.a = aVar;
            super(view);
            this.b = (ImageView) view.findViewById(R.id.require_style_icon);
            this.c = (TextView) view.findViewById(R.id.require_style_text);
        }

        void a(final cn.xiaochuankeji.tieba.abmgr.ui.a.b bVar) {
            this.c.setText(bVar.a.description);
            this.b.setVisibility(bVar.c ? 0 : 4);
            this.itemView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    if (!bVar.c) {
                        this.b.a.c.onClick(bVar.b, bVar.a);
                    }
                }
            });
        }
    }

    private class b extends ViewHolder {
        final /* synthetic */ a a;
        private TextView b;

        b(a aVar, View view) {
            this.a = aVar;
            super(view);
            this.b = (TextView) view.findViewById(R.id.requirement_description);
        }

        void a(d dVar) {
            this.b.setText(dVar.a.description);
        }
    }

    a(Activity activity) {
        this.b = activity;
    }

    void a(List<cn.xiaochuankeji.tieba.abmgr.ui.a.a> list) {
        if (list != null) {
            this.a = list;
            notifyDataSetChanged();
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 0) {
            return new b(this, LayoutInflater.from(this.b).inflate(R.layout.layout_item_requirement, viewGroup, false));
        }
        return new a(this, LayoutInflater.from(this.b).inflate(R.layout.layout_item_require_style, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (getItemViewType(i) == 0) {
            ((b) viewHolder).a((d) this.a.get(i));
        } else {
            ((a) viewHolder).a((cn.xiaochuankeji.tieba.abmgr.ui.a.b) this.a.get(i));
        }
    }

    public int getItemViewType(int i) {
        return ((cn.xiaochuankeji.tieba.abmgr.ui.a.a) this.a.get(i)).a();
    }

    public int getItemCount() {
        return this.a == null ? 0 : this.a.size();
    }

    void a(c cVar) {
        this.c = cVar;
    }
}
