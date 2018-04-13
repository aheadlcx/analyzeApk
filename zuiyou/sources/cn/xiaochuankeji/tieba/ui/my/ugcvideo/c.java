package cn.xiaochuankeji.tieba.ui.my.ugcvideo;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout.LayoutParams;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.post.Moment;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import com.iflytek.aiui.AIUIConstant;
import com.marshalchen.ultimaterecyclerview.d;
import com.marshalchen.ultimaterecyclerview.e;
import java.util.ArrayList;

public class c extends e<d> {
    private Context a;
    private ArrayList<Moment> l;
    private ArrayList<cn.xiaochuankeji.tieba.ui.videomaker.a.a> m = new ArrayList();

    class a extends d {
        final /* synthetic */ c a;
        private WebImageView b;

        public a(final c cVar, View view) {
            this.a = cVar;
            super(view);
            this.b = (WebImageView) view.findViewById(R.id.wivThumb);
            view.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    MyUgcVideoDraftActivity.a(this.b.a.a);
                }
            });
        }

        public void a() {
            this.b.setImageURI("file://" + ((cn.xiaochuankeji.tieba.ui.videomaker.a.a) this.a.m.get(0)).c);
        }
    }

    public /* synthetic */ ViewHolder b(ViewGroup viewGroup) {
        return a(viewGroup);
    }

    public /* synthetic */ ViewHolder c(View view) {
        return b(view);
    }

    public /* synthetic */ ViewHolder d(View view) {
        return a(view);
    }

    public /* synthetic */ ViewHolder f(View view) {
        return e(view);
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((d) viewHolder, i);
    }

    public c(Context context, ArrayList<Moment> arrayList) {
        this.a = context;
        this.l = arrayList;
        b();
    }

    private void b() {
        com.marshalchen.ultimaterecyclerview.UltimateRecyclerView.a aVar = new com.marshalchen.ultimaterecyclerview.UltimateRecyclerView.a(this.a);
        aVar.addView(new View(this.a), new LayoutParams(-1, cn.xiaochuankeji.tieba.ui.utils.e.a(41.0f)));
        a(aVar);
    }

    public void a(ArrayList<cn.xiaochuankeji.tieba.ui.videomaker.a.a> arrayList) {
        this.m.clear();
        this.m.addAll(arrayList);
        notifyDataSetChanged();
    }

    private boolean k() {
        return this.m.size() > 0;
    }

    public d a(View view) {
        return new d(view);
    }

    public int a() {
        int size = this.l.size();
        return k() ? size + 1 : size;
    }

    public d a(ViewGroup viewGroup) {
        return new cn.xiaochuankeji.tieba.ui.post.postitem.c(this.a, viewGroup, AIUIConstant.USER);
    }

    public void a(d dVar, int i) {
        if (getItemViewType(i) == 0) {
            ((cn.xiaochuankeji.tieba.ui.post.postitem.c) dVar).a((Moment) this.l.get(k() ? i - 2 : i - 1));
            ((cn.xiaochuankeji.tieba.ui.post.postitem.c) dVar).b();
        } else if (getItemViewType(i) == 4) {
            ((a) dVar).a();
        }
    }

    public d b(View view) {
        return new d(view);
    }

    protected boolean a(int i) {
        if (k() && 1 == i) {
            return true;
        }
        return false;
    }

    public d e(View view) {
        return new a(this, LayoutInflater.from(this.a).inflate(R.layout.view_item_draft_item, null));
    }
}
