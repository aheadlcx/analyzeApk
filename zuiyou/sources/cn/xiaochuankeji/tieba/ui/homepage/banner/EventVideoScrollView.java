package cn.xiaochuankeji.tieba.ui.homepage.banner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import c.a.c;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.post.Moment;
import cn.xiaochuankeji.tieba.json.UgcVideoInfoBean;
import cn.xiaochuankeji.tieba.ui.ugcvideodetail.UgcVideoActivity;
import cn.xiaochuankeji.tieba.ui.utils.d;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import java.util.ArrayList;
import java.util.List;

public class EventVideoScrollView extends HorizontalScrollView {
    private LinearLayout a;
    private Moment b;
    private String c;

    private class a {
        final Context a;
        final int b;
        final ViewGroup c;
        final View d;
        WebImageView e;
        ImageView f;
        ImageView g;
        View h;
        TextView i;
        boolean j = true;
        final /* synthetic */ EventVideoScrollView k;

        public a(EventVideoScrollView eventVideoScrollView, Context context, int i) {
            this.k = eventVideoScrollView;
            this.a = context;
            this.b = i;
            this.c = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.item_event_video, eventVideoScrollView.a, false);
            this.d = this.c.findViewById(R.id.container);
            this.e = (WebImageView) this.c.findViewById(R.id.eventVideoCover);
            this.f = (ImageView) this.c.findViewById(R.id.iv_moment_master);
            this.g = (ImageView) this.c.findViewById(R.id.iv_moment_god);
            this.h = this.c.findViewById(R.id.moreCover);
            this.i = (TextView) this.c.findViewById(R.id.tvMore);
        }

        public void a(UgcVideoInfoBean ugcVideoInfoBean) {
            this.e.setImageURI(cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/view/id/", (long) ugcVideoInfoBean.img.id, "/sz/360"));
            if (ugcVideoInfoBean.pid <= 0) {
                this.f.setVisibility(0);
            } else {
                this.f.setVisibility(8);
            }
            if (ugcVideoInfoBean.isGod == 1) {
                this.g.setVisibility(0);
            } else {
                this.g.setVisibility(8);
            }
            this.c.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    UgcVideoInfoBean ugcVideoInfoBean = (UgcVideoInfoBean) this.a.k.b.ugcVideos.get(this.a.b);
                    List arrayList = new ArrayList();
                    for (UgcVideoInfoBean ugcVideoInfoBean2 : this.a.k.b.ugcVideos) {
                        arrayList.add(Long.valueOf(ugcVideoInfoBean2.id));
                    }
                    UgcVideoActivity.a(this.a.k.getContext(), ugcVideoInfoBean, ugcVideoInfoBean.pid != 0, this.a.k.c, this.a.k.b);
                }
            });
        }

        public void a(boolean z, int i) {
            if (z) {
                this.i.setText("查看更多（" + d.b(i) + "）");
                this.h.setVisibility(0);
                return;
            }
            this.h.setVisibility(8);
        }

        public void a() {
            if (!this.j) {
                this.j = true;
                if (this.c.indexOfChild(this.d) == -1) {
                    this.c.addView(this.d);
                }
            }
        }

        public void b() {
            if (this.j) {
                this.j = false;
                this.c.removeView(this.d);
            }
        }
    }

    public EventVideoScrollView(Context context) {
        super(context);
    }

    public EventVideoScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public EventVideoScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void onViewAdded(View view) {
        super.onViewAdded(view);
        if (view.getId() == R.id.event_video_container) {
            this.a = (LinearLayout) view;
        }
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        a(i, i2);
    }

    private void a(int i, int i2) {
        int width = i + getWidth();
        for (int i3 = 0; i3 < this.a.getChildCount(); i3++) {
            View childAt = this.a.getChildAt(i3);
            a aVar = (a) childAt.getTag();
            if (childAt.getRight() < i || childAt.getLeft() > width) {
                aVar.b();
            } else {
                aVar.a();
            }
        }
    }

    public void a(Moment moment, String str) {
        this.b = moment;
        this.c = str;
        List list = moment.ugcVideos;
        if (list != null) {
            int i = 0;
            while (i < list.size() && i <= 8) {
                a aVar;
                UgcVideoInfoBean ugcVideoInfoBean = (UgcVideoInfoBean) list.get(i);
                View childAt = this.a.getChildAt(i);
                if (childAt == null) {
                    aVar = new a(this, getContext(), i);
                    aVar.c.setTag(aVar);
                    View findViewById = aVar.c.findViewById(R.id.cover_video);
                    if (c.e().c()) {
                        findViewById.setVisibility(0);
                    } else {
                        findViewById.setVisibility(8);
                    }
                    this.a.addView(aVar.c);
                } else {
                    aVar = (a) childAt.getTag();
                }
                aVar.c.setVisibility(0);
                aVar.a(ugcVideoInfoBean);
                if (8 == i) {
                    aVar.a(true, (int) moment.followCount);
                } else {
                    aVar.a(false, 0);
                }
                i++;
            }
            while (i < this.a.getChildCount()) {
                this.a.getChildAt(i).setVisibility(8);
                i++;
            }
            scrollTo(0, 0);
            this.a.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ EventVideoScrollView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    boolean z = false;
                    UgcVideoInfoBean ugcVideoInfoBean = (UgcVideoInfoBean) this.a.b.ugcVideos.get(0);
                    Context context = this.a.getContext();
                    if (ugcVideoInfoBean.pid != 0) {
                        z = true;
                    }
                    UgcVideoActivity.a(context, ugcVideoInfoBean, z, this.a.c, this.a.b);
                }
            });
        }
    }
}
