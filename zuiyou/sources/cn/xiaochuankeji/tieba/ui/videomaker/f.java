package cn.xiaochuankeji.tieba.ui.videomaker;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.base.j;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.d;
import com.marshalchen.ultimaterecyclerview.e;

public class f extends j {
    private View a;
    private UltimateRecyclerView b;
    private a c;
    private int d = 0;
    private c e;

    public interface a {
        void a();

        void a(int i);
    }

    class b extends d {
        final /* synthetic */ f a;
        private WebImageView b;
        private View c;
        private TextView d;
        private int e;

        public b(final f fVar, View view) {
            this.a = fVar;
            super(view);
            this.b = (WebImageView) view.findViewById(R.id.wivVideoFilter);
            this.c = view.findViewById(R.id.vCircle);
            this.d = (TextView) view.findViewById(R.id.tvFilterName);
            view.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ b b;

                public void onClick(View view) {
                    this.b.a.d = this.b.e;
                    this.b.a.e.notifyDataSetChanged();
                    if (this.b.a.c != null) {
                        this.b.a.c.a(this.b.e);
                    }
                }
            });
        }

        public void a(String str, int i, int i2) {
            boolean z = false;
            this.e = i2;
            this.b.setImageResource(i);
            this.d.setText(str);
            this.c.setVisibility(i2 == this.a.d ? 0 : 8);
            TextView textView = this.d;
            if (i2 == this.a.d) {
                z = true;
            }
            textView.setSelected(z);
        }
    }

    class c extends e<b> {
        final /* synthetic */ f a;
        private LayoutInflater l;

        public /* synthetic */ ViewHolder b(ViewGroup viewGroup) {
            return a(viewGroup);
        }

        public /* synthetic */ ViewHolder c(View view) {
            return b(view);
        }

        public /* synthetic */ ViewHolder d(View view) {
            return a(view);
        }

        public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
            a((b) viewHolder, i);
        }

        private c(f fVar) {
            this.a = fVar;
            this.l = LayoutInflater.from(fVar.e_());
        }

        public b a(ViewGroup viewGroup) {
            return new b(this.a, this.l.inflate(R.layout.view_item_filter, null));
        }

        public void a(b bVar, int i) {
            cn.xiaochuankeji.tieba.ui.videomaker.g.c cVar = (cn.xiaochuankeji.tieba.ui.videomaker.g.c) g.a.get(i);
            bVar.a(cVar.a, cVar.b, i);
        }

        public int a() {
            return g.a.size();
        }

        public b a(View view) {
            return null;
        }

        public b b(View view) {
            return null;
        }
    }

    protected f(Context context) {
        super(context);
    }

    protected View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.view_video_filter_select, null);
    }

    protected void a(View view) {
        this.a = view.findViewById(R.id.vRootView);
        this.a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.c();
            }
        });
        this.b = (UltimateRecyclerView) view.findViewById(R.id.recyclerView);
        this.e = new c();
        this.b.setLayoutManager(new LinearLayoutManager(e_(), 0, false));
        this.b.setAdapter(this.e);
    }

    public void a(int i) {
        this.d = i;
        f_().setVisibility(0);
        this.e.notifyDataSetChanged();
        this.b.a(this.d);
    }

    public boolean c() {
        if (f_().getVisibility() == 4) {
            return false;
        }
        this.d = 0;
        f_().setVisibility(4);
        if (this.c != null) {
            this.c.a();
        }
        return true;
    }

    public void a(a aVar) {
        this.c = aVar;
    }
}
