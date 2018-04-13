package cn.xiaochuankeji.tieba.ui.ugcvideodetail;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.json.UgcVideoInfoBean;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import com.marshalchen.ultimaterecyclerview.d;
import java.util.ArrayList;

public class e extends com.marshalchen.ultimaterecyclerview.e<d> implements OnClickListener {
    private Context a;
    private ArrayList<UgcVideoInfoBean> l;
    private int m = 0;
    private boolean n = false;
    private a o = null;
    private LayoutInflater p;

    public interface a {
        void a();

        void a(View view, int i);
    }

    class b extends d {
        final /* synthetic */ e a;
        private int b;
        private WebImageView c;

        public b(e eVar, View view) {
            this.a = eVar;
            super(view);
            this.c = (WebImageView) view.findViewById(R.id.wivFlag);
        }

        public void a(int i) {
            this.b = i;
            if (this.a.l.size() == this.b) {
                this.c.setImageResource(R.drawable.img_query_follow_all);
            }
            this.itemView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (this.a.a.o != null && this.a.a.l.size() == this.a.b) {
                        this.a.a.o.a();
                    }
                }
            });
        }
    }

    class c extends d {
        final /* synthetic */ e a;
        private WebImageView b;
        private WebImageView c;
        private View d;
        private View e;
        private View f;
        private View g;
        private View h;

        public c(e eVar, View view) {
            this.a = eVar;
            super(view);
            this.b = (WebImageView) view.findViewById(R.id.wivThumb);
            this.c = (WebImageView) view.findViewById(R.id.wivThumbBig);
            this.d = view.findViewById(R.id.vCover);
            this.e = view.findViewById(R.id.ivGodFlagBig);
            this.f = view.findViewById(R.id.ivGodFlagSmall);
            this.g = view.findViewById(R.id.ivMainFlagBig);
            this.h = view.findViewById(R.id.ivMainFlagSmall);
        }

        public void a(UgcVideoInfoBean ugcVideoInfoBean, boolean z) {
            int i = 0;
            if (z) {
                this.d.setVisibility(8);
                this.b.setVisibility(8);
                this.c.setImageURI(cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/view/id/", (long) ugcVideoInfoBean.img.id, "/sz/200"));
                this.c.setVisibility(0);
                this.f.setVisibility(8);
                this.e.setVisibility(ugcVideoInfoBean.isGod == 1 ? 0 : 8);
                this.h.setVisibility(8);
                View view = this.g;
                if (ugcVideoInfoBean.pid != 0) {
                    i = 8;
                }
                view.setVisibility(i);
                return;
            }
            int i2;
            this.d.setVisibility(0);
            this.c.setVisibility(8);
            String a = cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/view/id/", (long) ugcVideoInfoBean.img.id, "/sz/200");
            this.b.setVisibility(0);
            this.b.setImageURI(a);
            this.e.setVisibility(8);
            View view2 = this.f;
            if (ugcVideoInfoBean.isGod == 1) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            view2.setVisibility(i2);
            this.g.setVisibility(8);
            view = this.h;
            if (ugcVideoInfoBean.pid != 0) {
                i = 8;
            }
            view.setVisibility(i);
        }
    }

    public /* synthetic */ ViewHolder b(ViewGroup viewGroup) {
        return a(viewGroup);
    }

    public /* synthetic */ ViewHolder c(View view) {
        return e(view);
    }

    public /* synthetic */ ViewHolder d(View view) {
        return a(view);
    }

    public /* synthetic */ ViewHolder g(View view) {
        return b(view);
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((d) viewHolder, i);
    }

    public e(Context context, ArrayList<UgcVideoInfoBean> arrayList, boolean z) {
        this.a = context;
        this.p = LayoutInflater.from(this.a);
        this.l = arrayList;
        this.n = z;
    }

    public void b(int i) {
        this.m = i;
        notifyDataSetChanged();
    }

    public void a(boolean z) {
        this.n = z;
    }

    public d a(View view) {
        return new d(view);
    }

    public int getItemViewType(int i) {
        if (this.n && i == this.l.size()) {
            return 5;
        }
        return super.getItemViewType(i);
    }

    public int a() {
        int size = this.l.size();
        if (size == 0) {
            return 0;
        }
        return this.n ? size + 1 : size;
    }

    public d a(ViewGroup viewGroup) {
        return new c(this, this.p.inflate(R.layout.item_follow_video_thumb, viewGroup, false));
    }

    public d b(View view) {
        return new b(this, this.p.inflate(R.layout.item_follow_video_thumb_specific, null));
    }

    public void a(d dVar, int i) {
        if (getItemViewType(i) == 0) {
            ((c) dVar).a((UgcVideoInfoBean) this.l.get(i), i == this.m);
            dVar.itemView.setTag(Integer.valueOf(i));
            dVar.itemView.setOnClickListener(this);
        } else if (getItemViewType(i) == 5) {
            ((b) dVar).a(i);
        }
    }

    public d e(View view) {
        return null;
    }

    public void onClick(View view) {
        int intValue = ((Integer) view.getTag()).intValue();
        if (intValue != this.m && this.o != null) {
            this.o.a(view, intValue);
        }
    }

    public void a(a aVar) {
        this.o = aVar;
    }
}
