package cn.xiaochuankeji.tieba.ui.my.followpost;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.json.tale.FollowPostThemeJson;
import cn.xiaochuankeji.tieba.ui.tale.TaleListActivity;
import java.util.ArrayList;
import java.util.List;

public class c extends Adapter<ViewHolder> {
    private Context a;
    private List<FollowPostThemeJson> b = new ArrayList();

    class a extends ViewHolder {
        TextView a;
        TextView b;
        View c;
        final /* synthetic */ c d;

        public a(c cVar, View view) {
            this.d = cVar;
            super(view);
            this.c = view;
            this.a = (TextView) view.findViewById(R.id.tv_subject);
            this.b = (TextView) view.findViewById(R.id.tv_count);
        }
    }

    public c(Context context) {
        this.a = context;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new a(this, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_subject, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        a aVar = (a) viewHolder;
        aVar.a.setText(((FollowPostThemeJson) this.b.get(i)).title);
        aVar.b.setText(this.a.getString(R.string.follow_post_count, new Object[]{String.valueOf(((FollowPostThemeJson) this.b.get(i)).postCount)}));
        aVar.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ c b;

            public void onClick(View view) {
                TaleListActivity.a(this.b.a, "myThemes", ((FollowPostThemeJson) this.b.b.get(i)).id, null);
            }
        });
    }

    public int getItemCount() {
        return this.b.size();
    }

    public void a(List<FollowPostThemeJson> list) {
        this.b = list;
        notifyDataSetChanged();
    }

    public void b(List<FollowPostThemeJson> list) {
        this.b.addAll(list);
        notifyDataSetChanged();
    }
}
