package cn.xiaochuankeji.tieba.ui.post.postitem;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import cn.htjyb.ui.a;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.post.Moment;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent.MessageEventType;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.post.f;
import cn.xiaochuankeji.tieba.background.post.o;
import cn.xiaochuankeji.tieba.background.topic.Topic;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.background.utils.h;
import cn.xiaochuankeji.tieba.json.UgcVideoInfoBean;
import cn.xiaochuankeji.tieba.ui.homepage.HomePageActivity;
import cn.xiaochuankeji.tieba.ui.member.MemberDetailActivity;
import cn.xiaochuankeji.tieba.ui.my.MyPostActivity;
import cn.xiaochuankeji.tieba.ui.post.LikedUsersActivity;
import cn.xiaochuankeji.tieba.ui.post.postdetail.PostDetailActivity;
import cn.xiaochuankeji.tieba.ui.topic.TopicDetailActivity;
import cn.xiaochuankeji.tieba.ui.topic.holder.HolderCreator;
import cn.xiaochuankeji.tieba.ui.topic.weight.FollowView;
import cn.xiaochuankeji.tieba.ui.topic.weight.PostMemberView.ViewType;
import cn.xiaochuankeji.tieba.ui.ugcvideodetail.UgcVideoActivity;
import cn.xiaochuankeji.tieba.ui.widget.MultipleLineEllipsisTextView;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet.b;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import cn.xiaochuankeji.tieba.ui.widget.k;
import cn.xiaochuankeji.tieba.ui.widget.updown.PostItemUpDownView;
import com.alibaba.fastjson.JSON;
import com.facebook.drawee.a.a.e;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.marshalchen.ultimaterecyclerview.d;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;
import org.json.JSONException;
import org.json.JSONObject;

public class c extends d implements OnClickListener, a {
    WebImageView a;
    View b;
    ImageView c;
    ImageView d;
    TextView e;
    MultipleLineEllipsisTextView f;
    TextView g;
    TextView h;
    PostItemUpDownView i;
    ImageView j;
    private WebImageView o;
    private WebImageView p;
    private TextView q;
    private TextView r;
    private TextView s;
    private FollowView t;
    private View u;
    private TextView v;
    private Moment w;
    private String x = "index";
    private o y;

    public c(Context context, ViewGroup viewGroup, String str) {
        super(LayoutInflater.from(context).inflate(R.layout.view_item_moment, viewGroup, false));
        this.x = str;
        f();
        g();
    }

    private void f() {
        View d = d();
        this.a = (WebImageView) d.findViewById(R.id.pvAvatar);
        this.b = d.findViewById(R.id.open_member);
        this.c = (ImageView) d.findViewById(R.id.iv_user_level);
        this.e = (TextView) d.findViewById(R.id.tvWriterName);
        this.f = (MultipleLineEllipsisTextView) d.findViewById(R.id.tvPostContent);
        this.g = (TextView) d.findViewById(R.id.tvShare);
        this.i = (PostItemUpDownView) d.findViewById(R.id.postItemUpDownView);
        this.h = (TextView) d.findViewById(R.id.tvFollowCount);
        this.j = (ImageView) d.findViewById(R.id.ivTediumPost);
        this.u = d.findViewById(R.id.topicContainer);
        this.v = (TextView) d.findViewById(R.id.tvTopicName);
        this.d = (ImageView) d.findViewById(R.id.iv_more);
        this.t = (FollowView) d.findViewById(R.id.moment_follow_view);
        d.findViewById(R.id.layout_ugcvideo_cover).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                boolean z = false;
                if (this.a.w != null && !this.a.w.ugcVideos.isEmpty()) {
                    UgcVideoInfoBean ugcVideoInfoBean = (UgcVideoInfoBean) this.a.w.ugcVideos.get(0);
                    Context e = this.a.e();
                    if (ugcVideoInfoBean.pid != 0) {
                        z = true;
                    }
                    UgcVideoActivity.a(e, ugcVideoInfoBean, z, this.a.x, this.a.w);
                }
            }
        });
        this.o = (WebImageView) d.findViewById(R.id.ugcvideo_cover_bg);
        this.p = (WebImageView) d.findViewById(R.id.ugcvideo_cover);
        this.q = (TextView) d.findViewById(R.id.tvPlayCount);
        this.r = (TextView) d.findViewById(R.id.tvDanmuCount);
        this.s = (TextView) d.findViewById(R.id.tvVDur);
    }

    private void g() {
        d().findViewById(R.id.llMemberInfo).setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.a.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.v.setOnClickListener(this);
        this.f.setOnExpandableTextViewListener(new cn.xiaochuankeji.tieba.ui.widget.MultipleLineEllipsisTextView.c(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onClick() {
                this.a.h();
            }

            public void a() {
                this.a.a(false, true);
            }
        });
        d().setOnLongClickListener(new OnLongClickListener(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public boolean onLongClick(View view) {
                this.a.a(false, true);
                return true;
            }
        });
    }

    private void a(boolean z, boolean z2) {
        SDBottomSheet sDBottomSheet = new SDBottomSheet((Activity) e(), new b(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(int i) {
                if (i == 4 || i == 2 || i == 3 || i == 5 || i == 1) {
                    this.a.y.a(i);
                } else if (i == 6) {
                    cn.xiaochuankeji.tieba.ui.utils.d.a(this.a.w.title);
                    g.a("已复制");
                } else if (i == 12) {
                    this.a.y.a();
                } else if (i == 18) {
                    this.a.y.c();
                } else if (i == 9) {
                    this.a.y.b();
                }
            }
        });
        ArrayList arrayList = new ArrayList();
        if (!z) {
            if (z2 && !TextUtils.isEmpty(this.w.title)) {
                arrayList.add(new cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet.c(R.drawable.icon_option_copy, "复制文字", 6));
            }
            Object obj = cn.xiaochuankeji.tieba.background.a.g().c() == this.w.member.getId() ? 1 : null;
            if (obj != null) {
                arrayList.add(new cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet.c(R.drawable.icon_option_delete, "删除", 9));
            }
            if (obj == null && !(e() instanceof MyPostActivity)) {
                arrayList.add(new cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet.c(R.drawable.icon_option_report, "举报", 12));
            }
        }
        sDBottomSheet.a(sDBottomSheet.c(), arrayList);
        sDBottomSheet.b();
    }

    public void c() {
        if (org.greenrobot.eventbus.c.a().b(this)) {
            org.greenrobot.eventbus.c.a().c(this);
        }
    }

    public void a() {
        k kVar = new k(e());
        kVar.a(f.a(), new k.b(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(ArrayList<String> arrayList, String str) {
                MessageEvent messageEvent = new MessageEvent(MessageEventType.MESSAGE_POST_DELETE);
                messageEvent.setData(this.a.w);
                org.greenrobot.eventbus.c.a().d(messageEvent);
                new f(f.a(this.a.w.id, this.a.w.member.getId(), (String[]) arrayList.toArray(new String[arrayList.size()])), new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
                    final /* synthetic */ AnonymousClass8 a;

                    {
                        this.a = r1;
                    }

                    public /* synthetic */ void onResponse(Object obj, Object obj2) {
                        a((JSONObject) obj, obj2);
                    }

                    public void a(JSONObject jSONObject, Object obj) {
                    }
                }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
                    final /* synthetic */ AnonymousClass8 a;

                    {
                        this.a = r1;
                    }

                    public void onErrorResponse(XCError xCError, Object obj) {
                        g.a(xCError.getMessage());
                    }
                }).execute();
            }
        });
        kVar.show();
        h.a(e(), "zy_event_theme", "tag3");
    }

    private void h() {
        if (this.w != null) {
            List arrayList = new ArrayList();
            for (UgcVideoInfoBean ugcVideoInfoBean : this.w.ugcVideos) {
                arrayList.add(Long.valueOf(ugcVideoInfoBean.id));
            }
            UgcVideoActivity.a(e(), (UgcVideoInfoBean) this.w.ugcVideos.get(0), false, this.x, this.w);
        }
    }

    private void i() {
        if (this.w.member != null) {
            MemberDetailActivity.a(e(), this.w.member.getId(), 1, 0);
        }
    }

    private void j() {
        Context e = e();
        if (!(e instanceof TopicDetailActivity)) {
            String str = "";
            if (HomePageActivity.class.isInstance(e)) {
                str = "index";
            } else if (PostDetailActivity.class.isInstance(e)) {
                str = "postdetail";
            }
            try {
                TopicDetailActivity.a(e, new Topic(new JSONObject(JSON.toJSONString(this.w.topic))), str);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void a(final Moment moment) {
        this.w = moment;
        if (moment != null && !moment.ugcVideos.isEmpty()) {
            CharSequence charSequence;
            UgcVideoInfoBean ugcVideoInfoBean = (UgcVideoInfoBean) moment.ugcVideos.get(0);
            this.a.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(ugcVideoInfoBean.member.getId(), ugcVideoInfoBean.member.getAvatarId()));
            this.e.setText(ugcVideoInfoBean.member.getNickName());
            this.g.setText(0 == moment.shareCount ? "分享" : moment.shareCount + "");
            TextView textView = this.h;
            if (0 == moment.followCount) {
                charSequence = "跟拍";
            } else {
                charSequence = moment.followCount + "";
            }
            textView.setText(charSequence);
            this.i.a(ugcVideoInfoBean.liked, ugcVideoInfoBean.likeCount, new PostItemUpDownView.a(this) {
                final /* synthetic */ c b;

                public void a(boolean z) {
                    if (moment != null) {
                        LikedUsersActivity.a(this.b.e(), moment.id, z, 3, this.b.x, 0);
                    }
                }

                public void a(int i, int i2, boolean z) {
                    boolean z2 = true;
                    moment.isLiked = i;
                    moment.likeCount = (long) i2;
                    if (z) {
                        if (i != 1) {
                            z2 = false;
                        }
                        this.b.y.a(z2);
                    }
                }
            });
            if (!org.greenrobot.eventbus.c.a().b(this)) {
                org.greenrobot.eventbus.c.a().a(this);
            }
            FollowView followView;
            FollowView.a k;
            ViewType[] viewTypeArr;
            if (!moment.member.isFollowed()) {
                this.j.setVisibility(8);
                this.t.setVisibility(0);
                followView = this.t;
                k = k();
                viewTypeArr = new ViewType[2];
                viewTypeArr[0] = HolderCreator.a(moment.member.getId()) ? null : ViewType.FOLLOW;
                viewTypeArr[1] = ViewType.DELETE;
                followView.a(k, viewTypeArr);
            } else if (moment.hasUpdate) {
                this.j.setVisibility(8);
                this.t.setVisibility(0);
                followView = this.t;
                k = k();
                viewTypeArr = new ViewType[2];
                viewTypeArr[0] = HolderCreator.a(moment.member.getId()) ? null : ViewType.CANCEL_FOLLOW;
                viewTypeArr[1] = ViewType.DELETE;
                followView.a(k, viewTypeArr);
            } else {
                this.j.setVisibility(0);
                this.t.setVisibility(8);
            }
            String a = cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/view/id/", (long) ugcVideoInfoBean.img.id, "/sz/360");
            this.o.setController(((e) com.facebook.drawee.a.a.c.a().b(ImageRequestBuilder.a(Uri.parse(a)).a(new com.facebook.imagepipeline.j.a(50)).n())).k());
            this.p.setImageURI(a);
            if (moment.playCount > 0) {
                this.q.setVisibility(0);
                this.q.setText(cn.xiaochuankeji.tieba.ui.utils.d.b(moment.playCount));
            } else {
                this.q.setVisibility(4);
            }
            if (moment.danmakuCount > 0) {
                this.r.setVisibility(0);
                this.r.setText(cn.xiaochuankeji.tieba.ui.utils.d.b((int) moment.danmakuCount));
            } else {
                this.r.setVisibility(4);
            }
            if (ugcVideoInfoBean.videoInfo.duration > 0) {
                this.s.setText(cn.xiaochuankeji.tieba.ui.utils.d.a(ugcVideoInfoBean.videoInfo.duration * 1000));
            } else {
                this.s.setText(null);
            }
            l();
            CharSequence charSequence2 = moment.topic.topicName;
            if (TextUtils.isEmpty(charSequence2)) {
                this.u.setVisibility(8);
            } else {
                this.u.setVisibility(0);
                this.v.setText(charSequence2);
            }
            if (e() instanceof TopicDetailActivity) {
                this.d.setVisibility(0);
                this.d.setOnClickListener(this);
            }
            this.y = new o(moment, this.x, e());
        }
    }

    private FollowView.a k() {
        return new FollowView.a(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onClick(ViewType viewType) {
                switch (viewType) {
                    case FOLLOW:
                        if (cn.xiaochuankeji.tieba.ui.auth.a.a((HomePageActivity) this.a.itemView.getContext(), "home_tab", 10)) {
                            this.a.b(this.a.w);
                            return;
                        }
                        return;
                    case CANCEL_FOLLOW:
                        if (cn.xiaochuankeji.tieba.ui.auth.a.a((HomePageActivity) this.a.itemView.getContext(), "home_tab", -10)) {
                            this.a.c(this.a.w);
                            return;
                        }
                        return;
                    case DELETE:
                        this.a.a();
                        return;
                    default:
                        return;
                }
            }
        };
    }

    private void b(final Moment moment) {
        new cn.xiaochuankeji.tieba.background.e.b(moment.member.getId(), null, null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
            final /* synthetic */ c b;

            public /* synthetic */ void onResponse(Object obj, Object obj2) {
                a((JSONObject) obj, obj2);
            }

            public void a(JSONObject jSONObject, Object obj) {
                moment.member.setFollowStatus(1);
                moment.hasUpdate = true;
                org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.ui.topic.data.b(moment.member.getId(), true));
            }
        }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onErrorResponse(XCError xCError, Object obj) {
                g.a(xCError.getMessage());
            }
        }).execute();
    }

    private void c(final Moment moment) {
        new cn.xiaochuankeji.tieba.background.e.a(moment.member.getId(), null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
            final /* synthetic */ c b;

            public /* synthetic */ void onResponse(Object obj, Object obj2) {
                a((JSONObject) obj, obj2);
            }

            public void a(JSONObject jSONObject, Object obj) {
                moment.member.setFollowStatus(0);
                moment.hasUpdate = true;
                org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.ui.topic.data.b(moment.member.getId(), false));
            }
        }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onErrorResponse(XCError xCError, Object obj) {
                g.a(xCError.getMessage());
            }
        }).execute();
    }

    private void l() {
        if (TextUtils.isEmpty(this.w.title)) {
            this.f.setVisibility(8);
            return;
        }
        this.f.setMaxLines(4);
        this.f.setVisibility(0);
        this.f.a(this.w.title, null, this.w.id, c.a.d.a.a.a().a((int) R.color.CT_4), 2);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llMemberInfo:
                h();
                return;
            case R.id.pvAvatar:
            case R.id.open_member:
                i();
                return;
            case R.id.tvShare:
                a(true, false);
                return;
            case R.id.ivTediumPost:
                a();
                return;
            case R.id.tvTopicName:
                j();
                return;
            case R.id.iv_more:
                a(false, false);
                return;
            case R.id.tvFollowCount:
                h();
                return;
            default:
                return;
        }
    }

    @l(a = ThreadMode.MAIN)
    public void cancelLikeState(LikedUsersActivity.a aVar) {
        if (aVar.a == this.w.id) {
            this.i.b();
        }
    }

    public void b() {
        this.j.setVisibility(8);
    }
}
