package cn.xiaochuankeji.tieba.ui.homepage.ugc;

import android.content.Context;
import android.support.media.ExifInterface;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.f.b;
import cn.xiaochuankeji.tieba.d.h;
import cn.xiaochuankeji.tieba.json.MemberInfoBean;
import cn.xiaochuankeji.tieba.json.UgcVideoInfoBean;
import cn.xiaochuankeji.tieba.json.imgjson.ServerImgJson;
import cn.xiaochuankeji.tieba.json.videojson.ServerVideoJson;
import cn.xiaochuankeji.tieba.ui.ugcvideodetail.UgcVideoActivity;
import cn.xiaochuankeji.tieba.ui.utils.d;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView.a;
import com.danikula.videocache.f;
import com.danikula.videocache.q;
import java.math.BigDecimal;
import java.util.List;

public class e extends ViewHolder {
    private FrameLayout a;
    private WebImageView b;
    private WebImageView c;
    private a d;
    private boolean e;
    private Throwable f;
    private LinearLayout g;
    private TextView h;
    private TextView i;
    private TextView j;
    private ImageView k;
    private boolean l = false;
    private LinearLayout m;
    private WebImageView n;
    private WebImageView o;
    private WebImageView p;
    private WebImageView q;
    private ImageView r;
    private TextView s;
    private ImageView t;
    private ServerVideoJson u;

    public e(View view) {
        super(view);
        this.a = (FrameLayout) view.findViewById(R.id.itemRootView);
        this.b = (WebImageView) view.findViewById(R.id.wivCover);
        this.c = (WebImageView) view.findViewById(R.id.wivMemberCover);
        this.g = (LinearLayout) view.findViewById(R.id.llVideoInfo);
        this.h = (TextView) view.findViewById(R.id.tvTime);
        this.i = (TextView) view.findViewById(R.id.tvPlayCount);
        this.j = (TextView) view.findViewById(R.id.tvLikeCount);
        this.k = (ImageView) view.findViewById(R.id.ivFollowFlag);
        this.m = (LinearLayout) view.findViewById(R.id.ll_follow_video);
        this.n = (WebImageView) view.findViewById(R.id.iv_follow1);
        this.o = (WebImageView) view.findViewById(R.id.iv_follow2);
        this.p = (WebImageView) view.findViewById(R.id.iv_follow3);
        this.q = (WebImageView) view.findViewById(R.id.iv_follow4);
        this.r = (ImageView) view.findViewById(R.id.main_video_icon);
        this.s = (TextView) view.findViewById(R.id.tv_mask_count);
        this.t = (ImageView) view.findViewById(R.id.ivBlueOrRed);
        cn.xiaochuankeji.tieba.ui.utils.e.a(this.i, (float) cn.xiaochuankeji.tieba.ui.utils.e.a(8.0f));
        cn.xiaochuankeji.tieba.ui.utils.e.a(this.j, (float) cn.xiaochuankeji.tieba.ui.utils.e.a(8.0f));
    }

    public void a(a aVar) {
        this.d = aVar;
        if (this.e && this.d != null) {
            if (this.f == null) {
                this.d.a(this.b);
            } else {
                this.d.a(this.b, this.f);
            }
        }
    }

    public void a(final UgcVideoInfoBean ugcVideoInfoBean, boolean z) {
        boolean z2 = true;
        int i = 4;
        boolean z3 = false;
        this.l = z;
        String a = cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/view/id/", (long) ugcVideoInfoBean.img.id, "/sz/360");
        this.e = false;
        this.f = null;
        this.b.a(a, new a(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void a(WebImageView webImageView) {
                this.a.e = true;
                this.a.f = null;
                if (this.a.d != null) {
                    this.a.d.a(webImageView);
                }
            }

            public void a(WebImageView webImageView, Throwable th) {
                this.a.e = true;
                this.a.f = th;
                if (this.a.d != null) {
                    this.a.d.a(webImageView, th);
                }
            }
        });
        MemberInfoBean memberInfoBean = ugcVideoInfoBean.member;
        cn.xiaochuankeji.tieba.ui.widget.image.a a2 = b.a(memberInfoBean.getId(), memberInfoBean.getAvatarId());
        this.i.setText(d.b(ugcVideoInfoBean.plays));
        this.j.setText(d.b(ugcVideoInfoBean.likeCount));
        this.h.setText(h.a(ugcVideoInfoBean.createTime * 1000));
        this.c.setWebImage(a2);
        this.k.setVisibility(4);
        if (ugcVideoInfoBean.isCreatedByUserJustNow) {
            f fVar = new f(this.itemView.getContext(), z);
            fVar.a(ugcVideoInfoBean);
            fVar.f_().setTag("share_controller_view");
            this.a.addView(fVar.f_());
            this.c.setVisibility(4);
            this.g.setVisibility(4);
            this.h.setVisibility(4);
        } else {
            View findViewWithTag = this.a.findViewWithTag("share_controller_view");
            if (findViewWithTag != null) {
                this.a.removeView(findViewWithTag);
            }
            this.c.setVisibility(0);
            if (this.l) {
                this.h.setVisibility(0);
                this.g.setVisibility(4);
            } else {
                this.h.setVisibility(4);
                this.g.setVisibility(0);
            }
        }
        this.itemView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ e b;

            public void onClick(View view) {
                String str = this.b.l ? "attention" : "index-ugcvideo";
                if (ugcVideoInfoBean.pid == 0) {
                    UgcVideoActivity.a(this.b.itemView.getContext(), ugcVideoInfoBean, false, str, null);
                } else {
                    UgcVideoActivity.a(this.b.itemView.getContext(), ugcVideoInfoBean, true, str, null);
                }
            }
        });
        ImageView imageView = this.r;
        if (ugcVideoInfoBean.pid != 0) {
            i = 0;
        }
        imageView.setVisibility(i);
        List list;
        int i2;
        if (ugcVideoInfoBean.pid == 0) {
            list = ugcVideoInfoBean.subImgs;
            i2 = ugcVideoInfoBean.reviews;
            if (ugcVideoInfoBean.hotFlag == 1) {
                z3 = true;
            }
            a(list, i2, z3);
        } else if (ugcVideoInfoBean.mainPost == null) {
            this.m.setVisibility(8);
        } else {
            list = ugcVideoInfoBean.mainPost.subImgs;
            i2 = ugcVideoInfoBean.mainPost.reviews;
            ServerImgJson serverImgJson = ugcVideoInfoBean.mainPost.img;
            if (1 != ugcVideoInfoBean.mainPost.hotFlag) {
                z2 = false;
            }
            a(list, i2, serverImgJson, z2);
        }
        this.u = ugcVideoInfoBean.videoInfo;
    }

    private void a(List<ServerImgJson> list, int i, boolean z) {
        if (list == null || list.size() == 0) {
            this.m.setVisibility(8);
            return;
        }
        this.m.setVisibility(0);
        this.n.setWebImage(b.a());
        this.o.setWebImage(b.a());
        this.p.setWebImage(b.a());
        this.q.setWebImage(b.a());
        for (int i2 = 0; i2 < list.size(); i2++) {
            ServerImgJson serverImgJson = (ServerImgJson) list.get(i2);
            if (serverImgJson != null) {
                if (i2 == 0) {
                    this.n.setWebImage(b.c((long) serverImgJson.id));
                } else if (i2 == 1) {
                    this.o.setWebImage(b.c((long) serverImgJson.id));
                } else if (i2 == 2) {
                    this.p.setWebImage(b.c((long) serverImgJson.id));
                } else if (i2 == 3) {
                    this.q.setWebImage(b.c((long) serverImgJson.id));
                }
            }
        }
        if (i >= 4) {
            this.s.setVisibility(0);
            this.s.setText(a(i));
            this.t.setVisibility(0);
            this.t.setImageResource(z ? R.drawable.img_sub_flag_red : R.drawable.img_sub_flag_blue);
            this.q.setVisibility(8);
            return;
        }
        this.t.setVisibility(8);
        this.s.setVisibility(4);
        this.q.setVisibility(0);
    }

    private void a(List<ServerImgJson> list, int i, ServerImgJson serverImgJson, boolean z) {
        this.m.setVisibility(0);
        this.n.setWebImage(b.c((long) serverImgJson.id));
        this.o.setWebImage(b.a());
        this.p.setWebImage(b.a());
        this.q.setWebImage(b.a());
        for (int i2 = 0; i2 < list.size(); i2++) {
            ServerImgJson serverImgJson2 = (ServerImgJson) list.get(i2);
            if (i2 == 0) {
                this.o.setWebImage(b.c((long) serverImgJson2.id));
            } else if (i2 == 1) {
                this.p.setWebImage(b.c((long) serverImgJson2.id));
            } else if (i2 == 2) {
                this.q.setWebImage(b.c((long) serverImgJson2.id));
            }
        }
        if (i >= 4) {
            this.s.setVisibility(0);
            this.s.setText(a(i));
            this.t.setVisibility(0);
            this.t.setImageResource(z ? R.drawable.img_sub_flag_red : R.drawable.img_sub_flag_blue);
            this.q.setVisibility(8);
            return;
        }
        this.t.setVisibility(8);
        this.s.setVisibility(4);
        this.q.setVisibility(0);
    }

    private static String a(int i) {
        String str = Math.abs(i) + "";
        int abs = Math.abs(i);
        if (abs >= 10000) {
            return new BigDecimal((double) (((float) abs) / 10000.0f)).setScale(1, 4).doubleValue() + ExifInterface.LONGITUDE_WEST;
        } else if (abs < 1000) {
            return str;
        } else {
            return new BigDecimal((double) (((float) abs) / 1000.0f)).setScale(1, 4).doubleValue() + "K";
        }
    }

    public void a(Context context) {
        f b = q.a().b();
        if (!b.e(this.u.url)) {
            b.a(context, this.u.url);
        }
    }

    public void a() {
        q.a().b().f(this.u.url);
    }
}
