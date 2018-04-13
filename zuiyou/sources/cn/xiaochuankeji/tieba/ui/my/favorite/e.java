package cn.xiaochuankeji.tieba.ui.my.favorite;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.favorite.Favorite;
import java.util.LinkedList;
import java.util.List;

public class e extends Adapter {
    private b a;
    private List<Favorite> b;
    private Activity c;

    interface b {
        void a(Favorite favorite);

        void onClick(Favorite favorite);
    }

    private class a extends ViewHolder {
        final /* synthetic */ e a;
        private TextView b;
        private TextView c;

        a(e eVar, View view) {
            this.a = eVar;
            super(view);
            this.b = (TextView) view.findViewById(R.id.my_favor_item_name);
            this.c = (TextView) view.findViewById(R.id.my_favor_item_info);
        }

        @SuppressLint({"SetTextI18n"})
        void a(Favorite favorite) {
            this.b.setText(favorite.getName());
            this.c.setText(favorite.getPostCount() + "条内容");
        }
    }

    e(Activity activity) {
        this.c = activity;
    }

    void a(b bVar) {
        this.a = bVar;
    }

    void a(List<Favorite> list) {
        this.b = list;
        notifyDataSetChanged();
    }

    void a(Favorite favorite) {
        if (favorite != null) {
            if (this.b == null) {
                this.b = new LinkedList();
            }
            this.b.add(favorite);
            notifyDataSetChanged();
        }
    }

    void a(long j, String str) {
        if (this.b != null) {
            for (Favorite favorite : this.b) {
                if (favorite.getId() == j) {
                    favorite.setName(str);
                    break;
                }
            }
            notifyDataSetChanged();
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new a(this, LayoutInflater.from(this.c).inflate(R.layout.layout_favor_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final Favorite favorite = (Favorite) this.b.get(i);
        ((a) viewHolder).a(favorite);
        viewHolder.itemView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ e b;

            public void onClick(View view) {
                if (this.b.a != null) {
                    this.b.a.onClick(favorite);
                }
            }
        });
        viewHolder.itemView.setOnLongClickListener(new OnLongClickListener(this) {
            final /* synthetic */ e b;

            public boolean onLongClick(View view) {
                if (this.b.a == null) {
                    return false;
                }
                this.b.a.a(favorite);
                return true;
            }
        });
    }

    public int getItemCount() {
        return this.b == null ? 0 : this.b.size();
    }
}
