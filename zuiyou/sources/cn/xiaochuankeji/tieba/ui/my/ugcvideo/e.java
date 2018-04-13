package cn.xiaochuankeji.tieba.ui.my.ugcvideo;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.videomaker.VideoProcessActivity;
import cn.xiaochuankeji.tieba.ui.videomaker.VideoRecordActivity;
import cn.xiaochuankeji.tieba.ui.videomaker.a.b;
import cn.xiaochuankeji.tieba.ui.widget.f;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import com.marshalchen.ultimaterecyclerview.d;
import java.util.ArrayList;

public class e extends com.marshalchen.ultimaterecyclerview.e<d> {
    private Context a;
    private LayoutInflater l;
    private ArrayList<cn.xiaochuankeji.tieba.ui.videomaker.a.a> m;
    private b n = new b();

    class a extends d {
        final /* synthetic */ e a;
        private WebImageView b;
        private View c;
        private View d;
        private View e;
        private cn.xiaochuankeji.tieba.ui.videomaker.a.a f;

        public a(final e eVar, View view) {
            this.a = eVar;
            super(view);
            this.b = (WebImageView) view.findViewById(R.id.wivThumb);
            this.c = view.findViewById(R.id.ivFollowFlag);
            this.d = view.findViewById(R.id.tvDelete);
            this.e = view.findViewById(R.id.tvEdit);
            this.b.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    VideoProcessActivity.a(this.b.e(), this.b.f);
                }
            });
            this.d.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    this.b.a();
                }
            });
            this.e.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    VideoRecordActivity.a(this.b.e(), this.b.f);
                }
            });
        }

        private void a() {
            f.a("提示", "确定删除此草稿?", (Activity) this.a.a, new cn.xiaochuankeji.tieba.ui.widget.f.a(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void a(boolean z) {
                    if (z) {
                        this.a.a.m.remove(this.a.f);
                        this.a.a.n.b(this.a.f);
                        this.a.a.notifyDataSetChanged();
                        if (this.a.a.m.size() == 0) {
                            ((Activity) this.a.a.a).finish();
                        }
                    }
                }
            });
        }

        public void a(cn.xiaochuankeji.tieba.ui.videomaker.a.a aVar) {
            this.f = aVar;
            this.b.setImageURI("file://" + this.f.c);
            this.c.setVisibility(aVar.b != 0 ? 0 : 8);
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

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((d) viewHolder, i);
    }

    public e(Context context, ArrayList<cn.xiaochuankeji.tieba.ui.videomaker.a.a> arrayList) {
        this.a = context;
        this.l = LayoutInflater.from(context);
        this.m = arrayList;
    }

    public d a(ViewGroup viewGroup) {
        return new a(this, this.l.inflate(R.layout.view_item_draft, null));
    }

    public void a(d dVar, int i) {
        if (dVar instanceof a) {
            ((a) dVar).a((cn.xiaochuankeji.tieba.ui.videomaker.a.a) this.m.get(i));
        }
    }

    public int a() {
        return this.m.size();
    }

    public d a(View view) {
        return null;
    }

    public d b(View view) {
        return null;
    }
}
