package cn.xiaochuankeji.tieba.ui.my;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PointerIconCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.xiaochuankeji.aop.permission.PermissionItem;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.b.f;
import cn.xiaochuankeji.tieba.background.AppController;
import cn.xiaochuankeji.tieba.background.beans.Medal;
import cn.xiaochuankeji.tieba.background.beans.Member;
import cn.xiaochuankeji.tieba.background.member.d;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent.MessageEventType;
import cn.xiaochuankeji.tieba.background.upload.j;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.background.utils.h;
import cn.xiaochuankeji.tieba.json.ModifyMemberCoverJson;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.push.proto.Push.Packet;
import cn.xiaochuankeji.tieba.ui.base.a;
import cn.xiaochuankeji.tieba.ui.follow.UserBeFollowedActivity;
import cn.xiaochuankeji.tieba.ui.follow.UserFollowActivity;
import cn.xiaochuankeji.tieba.ui.hollow.detail.MyHollowActivity;
import cn.xiaochuankeji.tieba.ui.member.ViewHeaderMemberDetail;
import cn.xiaochuankeji.tieba.ui.my.account.AccountInfoActivity;
import cn.xiaochuankeji.tieba.ui.my.assessor.UserAssessActivity;
import cn.xiaochuankeji.tieba.ui.my.download.MyDownloadActivity;
import cn.xiaochuankeji.tieba.ui.my.favorite.MyFavorListActivity;
import cn.xiaochuankeji.tieba.ui.my.followpost.MyFollowPostActivity;
import cn.xiaochuankeji.tieba.ui.my.history.MyHistoryPostListActivity;
import cn.xiaochuankeji.tieba.ui.my.liked.MyLikedPostListActivity;
import cn.xiaochuankeji.tieba.ui.my.mypost.PostActivity;
import cn.xiaochuankeji.tieba.ui.my.ugcvideo.MyUgcVideoShowActivity;
import cn.xiaochuankeji.tieba.ui.selectlocalmedia.LocalMedia;
import cn.xiaochuankeji.tieba.ui.widget.SDCheckSheet;
import cn.xiaochuankeji.tieba.ui.widget.SDGuideDialog;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import cn.xiaochuankeji.tieba.ui.widget.pullzoom.PullToZoomScrollViewEx;
import cn.xiaochuankeji.tieba.webview.WebActivity;
import cn.xiaochuankeji.tieba.widget.daynight.DayNightSwitch;
import cn.xiaochuankeji.tieba.widget.daynight.b;
import com.alibaba.fastjson.JSONObject;
import com.android.a.a.c;
import com.tencent.bugly.beta.tinker.TinkerReport;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;
import rx.e;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class MeActivity extends a implements OnClickListener, d.a, cn.xiaochuankeji.tieba.background.modules.a.a.a {
    private AppCompatImageView A;
    private AppCompatImageView B;
    private AppCompatTextView C;
    private AppCompatTextView D;
    private String E;
    private LocalMedia F;
    private Uri G;
    private cn.xiaochuankeji.tieba.api.topic.a H = new cn.xiaochuankeji.tieba.api.topic.a();
    private cn.xiaochuankeji.tieba.api.account.a I = new cn.xiaochuankeji.tieba.api.account.a();
    private long J = 0;
    private int[] K = new int[]{R.drawable.img_default_avatar_1, R.drawable.img_default_avatar_2, R.drawable.img_default_avatar_3, R.drawable.img_default_avatar_4, R.drawable.img_default_avatar_5};
    private WebImageView a;
    private WebImageView b;
    private AppCompatTextView c;
    private AppCompatImageView d;
    private AppCompatImageView e;
    private AppCompatTextView f;
    private AppCompatImageView g;
    private AppCompatImageView h;
    private View i;
    private AppCompatImageView j;
    private AppCompatImageView k;
    private AppCompatTextView l;
    private AppCompatTextView m;
    private AppCompatTextView n;
    private AppCompatTextView o;
    private AppCompatTextView p;
    private AppCompatTextView q;
    private AppCompatTextView r;
    private AppCompatTextView s;
    @BindView
    PullToZoomScrollViewEx scrollView;
    @BindView
    AppCompatImageView setting_crumb;
    @BindView
    View setting_layout;
    private AppCompatTextView t;
    private AppCompatTextView u;
    private AppCompatTextView v;
    private AppCompatTextView w;
    private AppCompatTextView x;
    private DayNightSwitch y;
    private LinearLayout z;

    public boolean h() {
        return false;
    }

    protected void onResume() {
        super.onResume();
        if (cn.xiaochuankeji.tieba.background.a.g().d()) {
            t();
        } else {
            if ((System.currentTimeMillis() - this.J >= 10000 ? 1 : null) != null) {
                this.J = System.currentTimeMillis();
                e();
            }
            d.a().b();
        }
        v();
    }

    protected void onDestroy() {
        super.onDestroy();
        d.a().f();
        cn.xiaochuankeji.tieba.background.a.g().b(this);
    }

    protected int a() {
        return R.layout.activity_my;
    }

    protected boolean a(Bundle bundle) {
        if (bundle != null) {
            this.F = (LocalMedia) bundle.getSerializable("key_saved_local_media");
        }
        return true;
    }

    protected void c() {
        ButterKnife.a((Activity) this);
        if (!c.a()) {
            this.setting_layout.setPadding(this.setting_layout.getPaddingLeft(), getResources().getDimensionPixelOffset(R.dimen.status_bar_height), this.setting_layout.getPaddingRight(), 0);
        }
        View inflate = LayoutInflater.from(this).inflate(R.layout.activity_my_new_header, null, false);
        this.a = (WebImageView) inflate.findViewById(R.id.member_cover);
        this.b = (WebImageView) inflate.findViewById(R.id.member_avatar);
        this.j = (AppCompatImageView) inflate.findViewById(R.id.member_assessor);
        this.k = (AppCompatImageView) inflate.findViewById(R.id.member_community);
        this.c = (AppCompatTextView) inflate.findViewById(R.id.member_name);
        this.f = (AppCompatTextView) inflate.findViewById(R.id.member_sign);
        this.d = (AppCompatImageView) inflate.findViewById(R.id.member_gender);
        this.e = (AppCompatImageView) inflate.findViewById(R.id.member_official);
        this.h = (AppCompatImageView) inflate.findViewById(R.id.account);
        this.z = (LinearLayout) inflate.findViewById(R.id.ll_talent);
        this.A = (AppCompatImageView) inflate.findViewById(R.id.iv_talent);
        this.B = (AppCompatImageView) inflate.findViewById(R.id.iv_talent_text);
        this.C = (AppCompatTextView) inflate.findViewById(R.id.tv_talent);
        View findViewById = inflate.findViewById(R.id.my_hollow_icon);
        findViewById.setOnClickListener(this);
        if (cn.xiaochuankeji.tieba.background.a.g().d()) {
            findViewById.setVisibility(8);
        }
        View inflate2 = LayoutInflater.from(this).inflate(R.layout.activity_my_new_content, null, false);
        View findViewById2 = inflate2.findViewById(R.id.post);
        inflate2.findViewById(R.id.like);
        View findViewById3 = inflate2.findViewById(R.id.follow);
        View findViewById4 = inflate2.findViewById(R.id.fans);
        this.m = (AppCompatTextView) inflate2.findViewById(R.id.post_count);
        this.n = (AppCompatTextView) inflate2.findViewById(R.id.like_count);
        this.o = (AppCompatTextView) inflate2.findViewById(R.id.follow_count);
        this.p = (AppCompatTextView) inflate2.findViewById(R.id.fans_count);
        this.g = (AppCompatImageView) inflate2.findViewById(R.id.fans_crumb);
        this.y = (DayNightSwitch) inflate2.findViewById(R.id.night_mode);
        this.y.setDuration(TinkerReport.KEY_LOADED_PACKAGE_CHECK_SIGNATURE);
        this.i = inflate2.findViewById(R.id.shentie);
        View findViewById5 = inflate2.findViewById(R.id.my_ugc_video);
        View findViewById6 = inflate2.findViewById(R.id.my_post);
        this.l = (AppCompatTextView) inflate2.findViewById(R.id.shentie_text);
        this.q = (AppCompatTextView) inflate2.findViewById(R.id.my_shentie_count);
        this.r = (AppCompatTextView) inflate2.findViewById(R.id.my_ugc_count);
        this.s = (AppCompatTextView) inflate2.findViewById(R.id.my_post_count);
        this.t = (AppCompatTextView) inflate2.findViewById(R.id.my_pinglun_count);
        this.u = (AppCompatTextView) inflate2.findViewById(R.id.my_favor_count);
        this.v = (AppCompatTextView) inflate2.findViewById(R.id.my_like_count);
        this.w = (AppCompatTextView) inflate2.findViewById(R.id.my_history_count);
        this.D = (AppCompatTextView) inflate2.findViewById(R.id.my_black_text);
        this.x = (AppCompatTextView) inflate2.findViewById(R.id.my_follow_post_count);
        findViewById = inflate2.findViewById(R.id.my_comment);
        View findViewById7 = inflate2.findViewById(R.id.my_favor);
        View findViewById8 = inflate2.findViewById(R.id.my_like);
        View findViewById9 = inflate2.findViewById(R.id.my_history);
        View findViewById10 = inflate2.findViewById(R.id.my_group);
        View findViewById11 = inflate2.findViewById(R.id.my_share);
        View findViewById12 = inflate2.findViewById(R.id.my_feedback);
        View findViewById13 = inflate2.findViewById(R.id.my_family);
        View findViewById14 = inflate2.findViewById(R.id.my_black_house);
        View findViewById15 = inflate2.findViewById(R.id.my_download);
        View findViewById16 = inflate2.findViewById(R.id.my_follow_post);
        if (!cn.xiaochuankeji.tieba.background.a.q().e()) {
            findViewById16.setVisibility(8);
        }
        findViewById2.setOnClickListener(this);
        findViewById3.setOnClickListener(this);
        findViewById4.setOnClickListener(this);
        this.i.setOnClickListener(this);
        findViewById5.setOnClickListener(this);
        findViewById6.setOnClickListener(this);
        findViewById.setOnClickListener(this);
        findViewById7.setOnClickListener(this);
        findViewById8.setOnClickListener(this);
        findViewById9.setOnClickListener(this);
        findViewById10.setOnClickListener(this);
        findViewById11.setOnClickListener(this);
        findViewById12.setOnClickListener(this);
        findViewById13.setOnClickListener(this);
        findViewById14.setOnClickListener(this);
        findViewById15.setOnClickListener(this);
        findViewById16.setOnClickListener(this);
        if (AppController.instance().packageChannel().contains("4399")) {
            findViewById11.setVisibility(8);
        }
        this.y.a(c.a.c.e().c(), false);
        this.y.setListener(new b(this) {
            final /* synthetic */ MeActivity a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
                if (z) {
                    c.a.c.b();
                } else {
                    c.a.c.a();
                }
                h.a(this.a, "zy_event_my_tab", z ? "夜间模式 开" : "夜间模式 关");
                c.a.c.e().a(this.a.getWindow());
            }
        });
        this.scrollView.setZoomView(inflate);
        this.scrollView.a(getResources().getDisplayMetrics().widthPixels, (int) TypedValue.applyDimension(1, 210.0f, getResources().getDisplayMetrics()));
        this.scrollView.setScrollContentView(inflate2);
        this.scrollView.setParallax(false);
    }

    protected void i_() {
        cn.xiaochuankeji.tieba.background.modules.a.a g = cn.xiaochuankeji.tieba.background.a.g();
        if (!g.d()) {
            w();
        }
        if (cn.xiaochuankeji.tieba.background.assessor.b.a().b()) {
            if (cn.xiaochuankeji.tieba.background.a.a().getBoolean("key_first_assessor_remind", true)) {
                TextViewCompat.setCompoundDrawablesRelativeWithIntrinsicBounds(this.l, 0, 0, (int) R.drawable.icon_badge_dot, 0);
            }
            c(0);
        } else {
            c(8);
        }
        this.g.setVisibility(4);
        if (g.d()) {
            t();
        } else {
            u();
        }
    }

    protected void j_() {
        this.a.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.A.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.k.setOnClickListener(this);
        d.a().a((d.a) this);
        cn.xiaochuankeji.tieba.background.a.g().a(this);
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.F != null) {
            bundle.putSerializable("key_saved_local_media", this.F);
        }
    }

    private void e() {
        this.I.c().a(rx.a.b.a.a()).b(rx.f.a.c()).a(new e<JSONObject>(this) {
            final /* synthetic */ MeActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((JSONObject) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                this.a.J = 0;
                if (cn.xiaochuankeji.tieba.background.a.g().d()) {
                    this.a.t();
                } else {
                    this.a.u();
                }
            }

            public void a(JSONObject jSONObject) {
                cn.xiaochuankeji.tieba.background.a.i().a(jSONObject);
                cn.xiaochuankeji.tieba.background.a.i().t();
                if (cn.xiaochuankeji.tieba.background.a.g().d()) {
                    this.a.t();
                } else {
                    this.a.u();
                }
            }
        });
    }

    public void onClick(View view) {
        int id = view.getId();
        cn.xiaochuankeji.tieba.background.modules.a.a g = cn.xiaochuankeji.tieba.background.a.g();
        switch (id) {
            case R.id.post:
            case R.id.my_post:
                if (cn.xiaochuankeji.tieba.ui.auth.a.a(this, "me_tab", 1001, IMediaPlayer.MEDIA_INFO_UNSUPPORTED_SUBTITLE)) {
                    PostActivity.a((Context) this);
                    h.a(this, "zy_event_my_tab", "我发表帖子点击");
                    return;
                }
                return;
            case R.id.like:
            case R.id.my_like:
                if (cn.xiaochuankeji.tieba.ui.auth.a.a(this, "me_tab", 1004, IMediaPlayer.MEDIA_INFO_UNSUPPORTED_SUBTITLE)) {
                    MyLikedPostListActivity.a((Context) this);
                    h.a(this, "zy_event_my_tab", "我顶过的帖子点击");
                    return;
                }
                return;
            case R.id.follow:
                if (cn.xiaochuankeji.tieba.ui.auth.a.a(this, "me_tab", PointerIconCompat.TYPE_VERTICAL_TEXT, IMediaPlayer.MEDIA_INFO_UNSUPPORTED_SUBTITLE)) {
                    UserFollowActivity.a(this, g.c());
                    h.a(this, "zy_event_my_tab", "我关注的人点击");
                    return;
                }
                return;
            case R.id.fans:
                if (cn.xiaochuankeji.tieba.ui.auth.a.a(this, "me_tab", PointerIconCompat.TYPE_TEXT, IMediaPlayer.MEDIA_INFO_UNSUPPORTED_SUBTITLE)) {
                    this.g.setVisibility(4);
                    UserBeFollowedActivity.a(this, g.c());
                    h.a(this, "zy_event_my_tab", "我的粉丝点击");
                    return;
                }
                return;
            case R.id.shentie:
                p();
                return;
            case R.id.my_ugc_video:
                if (cn.xiaochuankeji.tieba.ui.auth.a.a(this, "me_tab", 1006, IMediaPlayer.MEDIA_INFO_UNSUPPORTED_SUBTITLE)) {
                    MyUgcVideoShowActivity.a(this, g.c());
                    h.a(this, "zy_event_my_tab", "我的跟拍点击");
                    return;
                }
                return;
            case R.id.my_follow_post:
                if (cn.xiaochuankeji.tieba.ui.auth.a.a(this, "me_tab", PointerIconCompat.TYPE_CROSSHAIR)) {
                    MyFollowPostActivity.a((Context) this);
                    h.a(this, "zy_event_my_tab", "我的跟帖点击");
                    return;
                }
                return;
            case R.id.my_comment:
                if (cn.xiaochuankeji.tieba.ui.auth.a.a(this, "me_tab", 1002, IMediaPlayer.MEDIA_INFO_UNSUPPORTED_SUBTITLE)) {
                    MyCommentActivity.a((Context) this, g.c(), g.i());
                    h.a(this, "zy_event_my_tab", "我的评论点击");
                    return;
                }
                return;
            case R.id.my_favor:
                if (cn.xiaochuankeji.tieba.ui.auth.a.a(this, "me_tab", 1003, IMediaPlayer.MEDIA_INFO_UNSUPPORTED_SUBTITLE)) {
                    g.o();
                    MyFavorListActivity.a((Context) this);
                    h.a(this, "zy_event_my_tab", "我的收藏夹点击");
                    return;
                }
                return;
            case R.id.my_history:
                if (cn.xiaochuankeji.tieba.ui.auth.a.a(this, "me_tab", Packet.CLIENTVER_FIELD_NUMBER, IMediaPlayer.MEDIA_INFO_UNSUPPORTED_SUBTITLE)) {
                    MyHistoryPostListActivity.a((Context) this);
                    h.a(this, "zy_event_my_tab", "浏览历史点击");
                    return;
                }
                return;
            case R.id.my_download:
                MyDownloadActivity.a((Context) this);
                h.a(this, "zy_event_my_tab", "我的下载点击");
                return;
            case R.id.my_group:
                ZYGroupActivity.a(this);
                h.a(this, "zy_event_my_tab", "右友圈点击");
                return;
            case R.id.my_black_house:
                TextViewCompat.setCompoundDrawablesRelativeWithIntrinsicBounds(this.D, null, null, getResources().getDrawable(R.color.transparent), null);
                String d = cn.xiaochuankeji.tieba.background.utils.d.a.d("https://$$/help/darkroom");
                long id2 = g.q() != null ? g.q().getId() : 0;
                if (id2 > 0) {
                    d = d + "?mid=" + id2;
                }
                WebActivity.a(this, cn.xiaochuan.jsbridge.b.a("小黑屋", d));
                return;
            case R.id.my_family:
                WebActivity.a(this, cn.xiaochuan.jsbridge.b.a("最右Family", cn.xiaochuankeji.tieba.background.utils.d.a.d("https://$$/help/family")));
                h.a(this, "zy_event_my_tab", "最右Family点击");
                return;
            case R.id.my_share:
                cn.xiaochuankeji.tieba.ui.utils.d.a((Context) this);
                h.a(this, "zy_event_my_tab", "推荐给好友点击");
                return;
            case R.id.my_feedback:
                cn.xiaochuan.jsbridge.b a = cn.xiaochuan.jsbridge.b.a("反馈建议", cn.xiaochuankeji.tieba.background.utils.d.a.d("https://$$/feedback/"));
                Bundle bundle = new Bundle();
                bundle.putBoolean("show_feed_back", true);
                a.e = bundle;
                WebActivity.a(this, a);
                h.a(this, "zy_event_my_tab", "反馈建议点击");
                return;
            case R.id.member_cover:
                if (cn.xiaochuankeji.tieba.ui.auth.a.a(this, "me_tab", 1000)) {
                    PermissionItem permissionItem = new PermissionItem("android.permission.READ_EXTERNAL_STORAGE");
                    permissionItem.runIgnorePermission = false;
                    permissionItem.settingText = "设置";
                    permissionItem.deniedMessage = "开启以下权限才能正常浏览图片和视频";
                    permissionItem.needGotoSetting = true;
                    cn.xiaochuankeji.aop.permission.a.a((Context) this).a(permissionItem, new cn.xiaochuankeji.aop.permission.e(this) {
                        final /* synthetic */ MeActivity a;

                        {
                            this.a = r1;
                        }

                        public void permissionGranted() {
                            this.a.j();
                        }

                        public void permissionDenied() {
                            g.a("开启以下权限才能正常浏览图片和视频");
                        }
                    });
                    return;
                }
                return;
            case R.id.my_hollow_icon:
                if (cn.xiaochuankeji.tieba.ui.auth.a.a(this, "me_tab", 41)) {
                    MyHollowActivity.a((Activity) this);
                    return;
                }
                return;
            case R.id.member_name:
            case R.id.member_gender:
            case R.id.account:
            case R.id.member_sign:
            case R.id.member_avatar:
                if (cn.xiaochuankeji.tieba.ui.auth.a.a(this, "me_tab", 1000)) {
                    AccountInfoActivity.a((Context) this);
                    return;
                }
                return;
            case R.id.member_official:
                cn.xiaochuankeji.tieba.ui.utils.e.d(this);
                return;
            case R.id.member_assessor:
                a(g.q().getAssessorRole());
                return;
            case R.id.member_community:
                b(g.q().getCommunityRole());
                return;
            case R.id.iv_talent:
                if (!TextUtils.isEmpty(this.E)) {
                    WebActivity.a(this, cn.xiaochuan.jsbridge.b.a(null, cn.xiaochuankeji.tieba.background.utils.d.a.d("https://$$" + this.E)));
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void a(int i) {
        int i2 = 0;
        if (i == 0) {
            i2 = R.drawable.img_assessor_primary_detail;
        } else if (i == 1) {
            i2 = R.drawable.img_assessor_middle_detail;
        } else if (i == 2) {
            i2 = R.drawable.img_assessor_senior_detail;
        }
        if (i2 != 0 && !isFinishing()) {
            SDGuideDialog sDGuideDialog = new SDGuideDialog(this);
            sDGuideDialog.a(i2, 17);
            sDGuideDialog.b();
        }
    }

    private void b(int i) {
        int i2 = 0;
        if (i == 1) {
            i2 = R.drawable.personal_shixizly_bg;
        } else if (i == 2) {
            i2 = R.drawable.personal_zhengshizly_bg;
        }
        if (i2 != 0 && !isFinishing()) {
            SDGuideDialog sDGuideDialog = new SDGuideDialog(this);
            sDGuideDialog.a(i2, 17);
            sDGuideDialog.b();
        }
    }

    private void j() {
        SDCheckSheet sDCheckSheet = new SDCheckSheet(this, new SDCheckSheet.a(this) {
            final /* synthetic */ MeActivity a;

            {
                this.a = r1;
            }

            public void a(int i) {
                if (i == 1) {
                    this.a.k();
                }
            }
        });
        sDCheckSheet.a("更换封面", 1, false);
        sDCheckSheet.a("取消", 2, true);
        sDCheckSheet.b();
    }

    private void k() {
        ArrayList arrayList = new ArrayList();
        cn.xiaochuankeji.tieba.ui.selectlocalmedia.b.f(this, IMediaPlayer.MEDIA_INFO_SUBTITLE_TIMED_OUT);
    }

    private void p() {
        TextViewCompat.setCompoundDrawablesRelativeWithIntrinsicBounds(this.l, null, null, null, null);
        Editor edit = cn.xiaochuankeji.tieba.background.a.a().edit();
        edit.putBoolean("key_first_assessor_remind", false);
        edit.apply();
        if (cn.xiaochuankeji.tieba.background.assessor.b.a().b()) {
            int assessorRole = cn.xiaochuankeji.tieba.background.a.g().q().getAssessorRole();
            if (assessorRole == 0 || assessorRole == 1 || assessorRole == 2) {
                q();
            } else {
                r();
            }
            h.a(this, "zy_event_my_tab", "审帖专区点击");
            return;
        }
        WebActivity.a(getApplicationContext(), cn.xiaochuan.jsbridge.b.a(null, cn.xiaochuankeji.tieba.background.utils.d.a.d("https://$$/help/assessor/show")));
    }

    private void q() {
        SharedPreferences c = cn.xiaochuankeji.tieba.background.a.c();
        if (1 == c.getInt("key_first_assess_flag", 1)) {
            WebActivity.a(getApplicationContext(), cn.xiaochuan.jsbridge.b.a(null, cn.xiaochuankeji.tieba.background.utils.d.a.d("https://$$/help/startexamine2?opencategory=1")));
        } else if (1 == c.getInt("key_first_assess_category_flag", 1)) {
            WebActivity.a(getApplicationContext(), cn.xiaochuan.jsbridge.b.a(null, cn.xiaochuankeji.tieba.background.utils.d.a.d("https://$$/help/assessor/selecttags2?viewAssess=1")));
        } else {
            UserAssessActivity.a((Context) this);
        }
    }

    private void r() {
        WebActivity.a(getApplicationContext(), cn.xiaochuan.jsbridge.b.a(null, cn.xiaochuankeji.tieba.background.utils.d.a.d("https://$$/help/askforexamine2")));
    }

    @l(a = ThreadMode.MAIN)
    public void message(MessageEvent messageEvent) {
        if (messageEvent.getEventType() == MessageEventType.MESSAGE_APP_UPDATE_STATE) {
            v();
        } else if (messageEvent.getEventType() == MessageEventType.MESSAGE_BE_FOLLOWED) {
            this.g.setVisibility(0);
        }
    }

    @l(a = ThreadMode.MAIN)
    public void newFans(cn.xiaochuankeji.tieba.push.c.d dVar) {
        this.g.setVisibility(0);
    }

    @l(a = ThreadMode.MAIN)
    public void updateTabBadge(f fVar) {
        v();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 != -1) {
            return;
        }
        String str;
        if (i == IMediaPlayer.MEDIA_INFO_SUBTITLE_TIMED_OUT) {
            Iterator it = ((ArrayList) cn.xiaochuankeji.tieba.ui.selectlocalmedia.b.b(intent)).iterator();
            while (it.hasNext()) {
                LocalMedia localMedia = (LocalMedia) it.next();
                if (localMedia.type != 1 && cn.htjyb.c.a.b.c(localMedia.path)) {
                    this.F = localMedia;
                    str = localMedia.path;
                    try {
                        Uri parse = Uri.parse("file://" + str);
                        Uri fromFile = Uri.fromFile(new File(cn.xiaochuankeji.tieba.background.a.e().B(), new File(parse.getPath()).getName()));
                        if (parse != null && parse.isAbsolute()) {
                            cn.xiaochuan.image.a.b.a((Activity) this, parse, fromFile, "剪裁封面");
                            return;
                        }
                        return;
                    } catch (Exception e) {
                        this.G = Uri.parse("file://" + str);
                        s();
                        return;
                    }
                }
            }
        } else if (i == 69) {
            this.G = cn.xiaochuan.image.a.b.a(intent);
            str = this.G.getPath();
            if (this.F != null) {
                this.F.path = str;
                s();
            }
        }
    }

    private void s() {
        if (this.F != null) {
            cn.xiaochuankeji.tieba.ui.widget.g.a((Activity) this);
            j jVar = new j();
            List arrayList = new ArrayList();
            arrayList.add(this.F);
            jVar.a(arrayList, "", null, new cn.xiaochuankeji.tieba.background.upload.f(this) {
                final /* synthetic */ MeActivity a;

                {
                    this.a = r1;
                }

                public void a(List<Long> list, List<Long> list2, HashMap<String, LocalMedia> hashMap) {
                    long j = 0;
                    if (list2.size() > 0) {
                        j = ((Long) list2.get(0)).longValue();
                    }
                    this.a.H.a(j).a(rx.a.b.a.a()).b(new rx.j<ModifyMemberCoverJson>(this) {
                        final /* synthetic */ AnonymousClass5 a;

                        {
                            this.a = r1;
                        }

                        public /* synthetic */ void onNext(Object obj) {
                            a((ModifyMemberCoverJson) obj);
                        }

                        public void onCompleted() {
                            cn.xiaochuankeji.tieba.ui.widget.g.c(this.a.a);
                            if (this.a.a.G != null) {
                                this.a.a.a.setImageURI(this.a.a.G.toString());
                                this.a.a.a.setScaleType(ScaleType.CENTER_CROP);
                            }
                            this.a.a.F = null;
                        }

                        public void onError(Throwable th) {
                            cn.xiaochuankeji.tieba.ui.widget.g.c(this.a.a);
                            if (th instanceof ClientErrorException) {
                                g.a(th.getMessage());
                            }
                        }

                        public void a(ModifyMemberCoverJson modifyMemberCoverJson) {
                        }
                    });
                }

                public void a(String str) {
                    g.a(str);
                    cn.xiaochuankeji.tieba.ui.widget.g.c(this.a);
                }
            });
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    public void a(long j) {
        this.w.setText(String.valueOf(j));
    }

    private void t() {
        this.z.setVisibility(8);
        this.i.setVisibility(8);
        this.m.setText(String.valueOf(0));
        this.n.setText(String.valueOf(0));
        this.o.setText(String.valueOf(0));
        this.p.setText(String.valueOf(0));
        this.r.setText(String.valueOf(0));
        this.s.setText(String.valueOf(0));
        this.t.setText(String.valueOf(0));
        this.u.setText(String.valueOf(0));
        this.v.setText(String.valueOf(0));
        this.w.setText(String.valueOf(0));
        this.x.setText(String.valueOf(0));
        TextViewCompat.setCompoundDrawablesRelativeWithIntrinsicBounds(this.D, null, null, null, null);
        Member q = cn.xiaochuankeji.tieba.background.a.g().q();
        if (q != null) {
            this.b.setImageResource(this.K[(int) (q.getId() % 5)]);
        } else {
            this.b.setImageResource(R.drawable.img_default_avatar_3);
        }
        this.a.setImageResource(R.drawable.img_cover_guest);
        this.c.setText("未登录");
        this.f.setText("点击头像登录");
        this.j.setVisibility(8);
        this.k.setVisibility(8);
        this.d.setVisibility(8);
        this.e.setVisibility(8);
    }

    private void u() {
        int atts;
        this.i.setVisibility(0);
        cn.xiaochuankeji.tieba.background.modules.a.a g = cn.xiaochuankeji.tieba.background.a.g();
        Member q = g.q();
        this.m.setText(String.valueOf(g.g()));
        this.n.setText(cn.xiaochuankeji.tieba.ui.utils.d.b(g.m() > 0 ? g.m() : 0));
        AppCompatTextView appCompatTextView = this.o;
        if (q.getAtts() > 0) {
            atts = q.getAtts();
        } else {
            atts = 0;
        }
        appCompatTextView.setText(String.valueOf(atts));
        appCompatTextView = this.p;
        if (q.getFans() > 0) {
            atts = q.getFans();
        } else {
            atts = 0;
        }
        appCompatTextView.setText(String.valueOf(atts));
        this.r.setText(String.valueOf(g.j()));
        this.s.setText(String.valueOf(g.g()));
        this.t.setText(String.valueOf(g.i()));
        this.u.setText(String.valueOf(g.o()));
        this.v.setText(String.valueOf(g.l()));
        this.x.setText(String.valueOf(g.k()));
        CharSequence sign = q.getSign();
        this.c.setText(q.getName());
        appCompatTextView = this.f;
        if (TextUtils.isEmpty(sign)) {
            sign = "这家伙很懒，什么都没有写~";
        }
        appCompatTextView.setText(sign);
        if (q != null) {
            this.b.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(q.getId(), q.getAvatarID()));
        } else {
            this.b.setImageResource(R.drawable.img_default_avatar_3);
        }
        if (g.d()) {
            this.a.setImageResource(R.drawable.img_cover_guest);
        } else {
            ViewHeaderMemberDetail.a(this.a, q.getId(), q.getCoverId());
        }
        atts = q.getAssessorRole();
        if (atts == 0) {
            this.j.setImageResource(R.drawable.ic_assessor_primary);
            this.j.setVisibility(0);
        } else if (atts == 1) {
            this.j.setImageResource(R.drawable.ic_assessor_middle);
            this.j.setVisibility(0);
        } else if (atts == 2) {
            this.j.setImageResource(R.drawable.ic_assessor_senior);
            this.j.setVisibility(0);
        } else {
            this.j.setVisibility(8);
        }
        atts = q.getCommunityRole();
        if (atts == 1) {
            this.k.setImageResource(R.drawable.personal_shixizly);
            this.k.setVisibility(0);
        } else if (atts == 2) {
            this.k.setImageResource(R.drawable.personal_zhengshizly);
            this.k.setVisibility(0);
        } else {
            this.k.setVisibility(8);
        }
        this.d.setImageResource(q.getGender() == 2 ? R.drawable.ic_female : R.drawable.ic_male);
        AppCompatImageView appCompatImageView = this.e;
        if (q.isOfficial()) {
            atts = 0;
        } else {
            atts = 8;
        }
        appCompatImageView.setVisibility(atts);
        x();
        Medal medal = q.getMedal();
        if (medal != null) {
            this.E = medal.click_url;
            this.z.setVisibility(0);
            if (medal.original == 1) {
                this.A.setImageResource(c.a.d.a.a.a().d(R.drawable.talent_original));
                this.B.setVisibility(0);
            } else if (medal.original == 2) {
                this.A.setImageResource(c.a.d.a.a.a().d(R.drawable.talent));
                this.B.setVisibility(8);
            } else if (medal.original == 3) {
                this.A.setImageResource(c.a.d.a.a.a().d(R.drawable.topic_talent_small_icon));
                this.B.setVisibility(8);
            } else {
                this.z.setVisibility(8);
                this.B.setVisibility(8);
            }
            this.C.setText(medal.name);
        } else {
            this.z.setVisibility(8);
        }
        if (g.s()) {
            TextViewCompat.setCompoundDrawablesRelativeWithIntrinsicBounds(this.D, 0, 0, (int) R.drawable.icon_badge_dot, 0);
        }
    }

    private void v() {
        this.setting_crumb.setVisibility(cn.xiaochuankeji.tieba.background.h.c.a().c() ? 0 : 8);
    }

    @OnClick
    public void setting() {
        SettingActivity.a((Context) this, 0);
        cn.xiaochuankeji.tieba.background.h.c.a().d();
        h.a(this, "zy_event_my_tab", "设置点击");
    }

    private void w() {
        cn.xiaochuankeji.tieba.background.assessor.b.a().a(new cn.xiaochuankeji.tieba.background.assessor.b.a(this) {
            final /* synthetic */ MeActivity a;

            {
                this.a = r1;
            }

            public void a(boolean z, boolean z2, String str) {
                if (!z) {
                    this.a.c(8);
                    g.a(str);
                } else if (z2) {
                    this.a.c(0);
                } else {
                    this.a.c(8);
                }
            }
        });
    }

    private void c(int i) {
        if (i == 0) {
            x();
        } else {
            this.q.setText("");
        }
    }

    private void x() {
        int h = cn.xiaochuankeji.tieba.background.a.g().h();
        if (h == 0) {
            this.q.setText("");
            return;
        }
        int x = cn.xiaochuankeji.tieba.background.utils.c.a.c().x();
        if (x <= 0 || h > x) {
            this.q.setText("今日已审" + h + "条");
        } else {
            this.q.setText("今日已审" + h + "/" + x + "条");
        }
    }

    public void onTokenChanged() {
        cn.xiaochuankeji.tieba.ui.tag.a.a(true);
        rx.a.b.a.a().a().a(new rx.b.a(this) {
            final /* synthetic */ MeActivity a;

            {
                this.a = r1;
            }

            public void call() {
                if (cn.xiaochuankeji.tieba.background.a.g().d()) {
                    this.a.a.setImageResource(R.drawable.img_cover_guest);
                    this.a.d.setVisibility(8);
                    this.a.c(8);
                    this.a.J = 0;
                    return;
                }
                long c = cn.xiaochuankeji.tieba.background.a.g().c();
                cn.htjyb.netlib.b.a().a(c);
                if (c != cn.xiaochuankeji.tieba.background.a.a().getLong("key_last_user_id", 0)) {
                    cn.xiaochuankeji.tieba.background.assessor.c.a().d();
                } else {
                    this.a.x();
                }
                this.a.w();
                Member q = cn.xiaochuankeji.tieba.background.a.g().q();
                ViewHeaderMemberDetail.a(this.a.a, q.getId(), q.getCoverId());
                this.a.d.setVisibility(0);
            }
        });
    }
}
