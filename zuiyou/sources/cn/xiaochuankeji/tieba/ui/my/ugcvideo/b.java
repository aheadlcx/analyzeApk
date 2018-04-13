package cn.xiaochuankeji.tieba.ui.my.ugcvideo;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.post.Moment;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl.Type;
import cn.xiaochuankeji.tieba.json.UgcVideoInfoBean;
import cn.xiaochuankeji.tieba.ui.ugcvideodetail.UgcVideoActivity;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import com.iflytek.aiui.AIUIConstant;
import com.marshalchen.ultimaterecyclerview.d;
import com.marshalchen.ultimaterecyclerview.e;
import java.util.ArrayList;

public class b extends e<d> {
    private Context a;
    private LayoutInflater l;
    private ArrayList<Moment> m;
    private ArrayList<cn.xiaochuankeji.tieba.ui.videomaker.a.a> n = new ArrayList();

    class a extends d {
        final /* synthetic */ b a;
        private WebImageView b;
        private TextView c;
        private TextView d;
        private TextView e;
        private ImageView f;
        private ImageView g;
        private ImageView h;

        public a(b bVar, View view) {
            this.a = bVar;
            super(view);
            this.b = (WebImageView) view.findViewById(R.id.wivCover);
            this.c = (TextView) view.findViewById(R.id.tvLikeCount);
            this.d = (TextView) view.findViewById(R.id.tvDrafts);
            this.f = (ImageView) view.findViewById(R.id.ivFollowFlag);
            this.g = (ImageView) view.findViewById(R.id.ivUpFlag);
            this.e = (TextView) view.findViewById(R.id.tvPlayCount);
            this.h = (ImageView) view.findViewById(R.id.cover_draft);
        }

        public void a(final Moment moment) {
            int i = 0;
            final UgcVideoInfoBean ugcVideoInfoBean = (UgcVideoInfoBean) moment.ugcVideos.get(0);
            long j = (long) ugcVideoInfoBean.img.id;
            this.b.setData(new PictureImpl(cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/view/id/", j, null), Type.kChatImg360, j));
            this.c.setVisibility(0);
            this.e.setVisibility(0);
            this.d.setVisibility(4);
            final boolean z = ugcVideoInfoBean.pid != 0;
            ImageView imageView = this.f;
            if (!z) {
                i = 4;
            }
            imageView.setVisibility(i);
            this.c.setText(cn.xiaochuankeji.tieba.ui.utils.d.b(ugcVideoInfoBean.likeCount));
            this.e.setText(cn.xiaochuankeji.tieba.ui.utils.d.b(ugcVideoInfoBean.plays));
            this.itemView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a d;

                public void onClick(View view) {
                    UgcVideoActivity.a(this.d.a.a, ugcVideoInfoBean, z, AIUIConstant.USER, moment);
                }
            });
            this.h.setVisibility(8);
        }

        public void a() {
            this.b.setImageURI("file://" + ((cn.xiaochuankeji.tieba.ui.videomaker.a.a) this.a.n.get(0)).c);
            this.d.setVisibility(0);
            this.c.setVisibility(4);
            this.e.setVisibility(4);
            this.f.setVisibility(4);
            this.itemView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    MyUgcVideoDraftActivity.a(this.a.a.a);
                }
            });
            this.h.setVisibility(0);
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

    public b(Context context, ArrayList<Moment> arrayList) {
        this.a = context;
        this.l = LayoutInflater.from(this.a);
        this.m = arrayList;
        k();
    }

    private void k() {
        com.marshalchen.ultimaterecyclerview.UltimateRecyclerView.a aVar = new com.marshalchen.ultimaterecyclerview.UltimateRecyclerView.a(this.a);
        aVar.addView(new View(this.a), new LayoutParams(-1, cn.xiaochuankeji.tieba.ui.utils.e.a(41.0f)));
        a(aVar);
    }

    public void a(ArrayList<cn.xiaochuankeji.tieba.ui.videomaker.a.a> arrayList) {
        this.n.clear();
        this.n.addAll(arrayList);
        notifyDataSetChanged();
    }

    public boolean b() {
        return this.n.size() > 0;
    }

    public d a(View view) {
        return new d(view);
    }

    public d a(ViewGroup viewGroup) {
        return new a(this, this.l.inflate(R.layout.view_item_member_ugc_video, null));
    }

    public int a() {
        int size = this.m.size();
        return b() ? size + 1 : size;
    }

    public void a(d dVar, int i) {
        if (getItemViewType(i) != 0 || !(dVar instanceof a)) {
            return;
        }
        if (!b()) {
            ((a) dVar).a((Moment) this.m.get(i - 1));
        } else if (1 == i) {
            ((a) dVar).a();
        } else {
            ((a) dVar).a((Moment) this.m.get(i - 2));
        }
    }

    public d b(View view) {
        return new d(view);
    }
}
