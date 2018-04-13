package cn.xiaochuankeji.tieba.ui.member;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.beans.Member;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.member.c;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.utils.h;
import cn.xiaochuankeji.tieba.ui.CustomReportReasonActivity;
import cn.xiaochuankeji.tieba.ui.chat.ChatActivity;
import cn.xiaochuankeji.tieba.ui.member.userpost.UserPostFragment;
import cn.xiaochuankeji.tieba.ui.topic.data.PostDataBean;
import cn.xiaochuankeji.tieba.ui.utils.e;
import cn.xiaochuankeji.tieba.ui.voice.b.d;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet;
import cn.xiaochuankeji.tieba.ui.widget.SDCheckSheet;
import cn.xiaochuankeji.tieba.ui.widget.SDPopupMenu.b;
import cn.xiaochuankeji.tieba.ui.widget.StickyNavLayout;
import cn.xiaochuankeji.tieba.ui.widget.f;
import cn.xiaochuankeji.tieba.ui.widget.g;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import cn.xiaochuankeji.tieba.ui.widget.indicator.MagicIndicator;
import cn.xiaochuankeji.tieba.ui.widget.indicator.i;
import cn.xiaochuankeji.tieba.ui.widget.indicator.o;
import cn.xiaochuankeji.tieba.widget.CustomEmptyView;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONObject;

public class MemberDetailActivity extends cn.xiaochuankeji.tieba.ui.base.a implements OnPageChangeListener, OnClickListener, cn.xiaochuankeji.tieba.background.member.b.a, b, cn.xiaochuankeji.tieba.ui.widget.StickyNavLayout.a {
    private static final String[] b = new String[]{"帖子", "跟拍", "评论", "话题"};
    private static final String[] c = new String[]{"帖子", "跟拍", "跟帖", "评论", "话题"};
    private static final int e = e.a(130.0f);
    private static final int f = e.a(150.0f);
    private boolean A = false;
    private cn.xiaochuankeji.tieba.background.e.b B;
    private cn.xiaochuankeji.tieba.background.e.a C;
    private boolean D;
    private int E = 0;
    private long F;
    private long G;
    private long H;
    private UserPostFragment I;
    o a;
    private String[] d;
    private cn.xiaochuankeji.tieba.background.member.b g;
    private c h;
    private long i;
    private a j;
    private int k = 0;
    private ViewPager l;
    private MagicIndicator m;
    private FrameLayout n;
    private StickyNavLayout o;
    private CustomEmptyView p;
    private ViewHeaderMemberDetail q;
    private int r;
    private View s;
    private WebImageView t;
    private ImageView u;
    private ImageView v;
    private ImageView w;
    private ImageView x;
    private View y;
    private TextView z;

    class a extends FragmentPagerAdapter {
        final /* synthetic */ MemberDetailActivity a;

        public a(MemberDetailActivity memberDetailActivity, FragmentManager fragmentManager) {
            this.a = memberDetailActivity;
            super(fragmentManager);
            memberDetailActivity.I = UserPostFragment.a(memberDetailActivity.i);
        }

        public Fragment getItem(int i) {
            if (this.a.d.length == 4) {
                return this.a.f(i);
            }
            return this.a.e(i);
        }

        public int getCount() {
            return this.a.d.length;
        }
    }

    public static void a(Context context, long j, int i, long j2) {
        Intent intent = new Intent(context, MemberDetailActivity.class);
        intent.putExtra("key_member_id", j);
        intent.putExtra("key_is_from_chat", context instanceof ChatActivity);
        intent.putExtra("key_init_index", i);
        intent.putExtra("fromId", j2);
        context.startActivity(intent);
    }

    public static void a(Context context, long j) {
        a(context, j, 0, 0);
    }

    public static void a(Context context, long j, long j2, int i, long j3) {
        Intent intent = new Intent(context, MemberDetailActivity.class);
        intent.putExtra("key_member_id", j);
        intent.putExtra("key_is_from_chat", context instanceof ChatActivity);
        intent.putExtra("key_init_index", 0);
        intent.putExtra("fromId", j2);
        intent.putExtra("from", i);
        intent.putExtra("from_post", j3);
        context.startActivity(intent);
    }

    protected int a() {
        return R.layout.activity_member_detail;
    }

    protected boolean a(Bundle bundle) {
        this.d = cn.xiaochuankeji.tieba.background.a.q().e() ? c : b;
        this.i = getIntent().getExtras().getLong("key_member_id");
        if (0 == this.i) {
            return false;
        }
        this.E = getIntent().getIntExtra("key_init_index", 0);
        this.D = getIntent().getBooleanExtra("key_is_from_chat", false);
        this.F = getIntent().getLongExtra("fromId", 0);
        this.G = (long) getIntent().getIntExtra("from", 0);
        if (this.G == 2) {
            this.H = getIntent().getLongExtra("from_post", 0);
        }
        this.g = new cn.xiaochuankeji.tieba.background.member.b(this.i);
        this.h = new c(this.i);
        return true;
    }

    protected void c() {
        int i;
        this.s = findViewById(R.id.nav_bar);
        if (com.android.a.a.c.a()) {
            i = 0;
        } else {
            int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.status_bar_height);
            this.s.setPadding(this.s.getPaddingLeft(), dimensionPixelOffset, this.s.getPaddingRight(), 0);
            i = dimensionPixelOffset;
        }
        this.o = (StickyNavLayout) findViewById(R.id.stickyNavLayout);
        this.o.setMoveOffset((getResources().getDimensionPixelSize(R.dimen.navbar_height) + i) * -1);
        this.l = (ViewPager) findViewById(R.id.id_stickynavlayout_viewpager);
        this.m = (MagicIndicator) findViewById(R.id.id_stickynavlayout_indicator);
        this.n = (FrameLayout) findViewById(R.id.id_stickynavlayout_topview);
        this.p = (CustomEmptyView) findViewById(R.id.custom_empty_view);
        this.y = findViewById(R.id.divider);
        this.t = (WebImageView) findViewById(R.id.small_avatar);
        this.t.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MemberDetailActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.g.a != null) {
                    MemberAvatarActivity.a(this.a, this.a.g.a);
                }
            }
        });
        this.t.setEnabled(false);
        this.z = (TextView) findViewById(R.id.tvTopName);
        this.u = (ImageView) findViewById(R.id.ivMore);
        this.v = (ImageView) findViewById(R.id.ivSendMsg);
        this.w = (ImageView) findViewById(R.id.ivFollow);
        this.x = (ImageView) findViewById(R.id.ivBack);
        this.s.setTag(Boolean.valueOf(true));
        c(0);
    }

    private void c(int i) {
        boolean z;
        int a;
        int i2 = 1;
        int i3 = 0;
        if (i >= e) {
            z = true;
        } else {
            z = false;
        }
        if ((this.s.getTag() instanceof Boolean) && !(((Boolean) this.s.getTag()).booleanValue() && z)) {
            a = c.a.d.a.a.a().a((int) R.color.CB);
            if (!z) {
                a = 0;
            }
            this.s.setBackgroundColor(a);
            this.s.setTag(Boolean.valueOf(z));
            b(z);
        }
        if (i >= f) {
            this.x.setImageResource(c.a.d.a.a.a().d(R.drawable.ic_arrow_left));
            a = 1;
        } else {
            this.x.setImageResource(c.a.d.a.a.a().d(R.drawable.ic_arrow_left_white));
            a = 0;
        }
        if (this.t.getVisibility() != 0 || a == 0) {
            i2 = 0;
        }
        if (i2 == 0) {
            WebImageView webImageView = this.t;
            if (a == 0) {
                i3 = 4;
            }
            webImageView.setVisibility(i3);
        }
    }

    private void b(boolean z) {
        if (z) {
            this.z.setVisibility(0);
            this.y.setVisibility(0);
            this.u.setVisibility(8);
            this.v.setImageResource(R.drawable.img_member_detail_has_bg_msg);
        } else {
            this.v.setImageResource(R.drawable.img_member_detail_no_bg_msg);
            this.z.setVisibility(8);
            this.y.setVisibility(8);
            this.u.setVisibility(0);
        }
        if (this.A) {
            this.w.setImageResource(z ? R.drawable.img_member_detail_has_bg_followed : R.drawable.img_member_detail_no_bg_followed);
        } else {
            this.w.setImageResource(z ? R.drawable.img_member_detail_has_bg_follow : R.drawable.img_member_detail_no_bg_follow);
        }
    }

    protected void i_() {
        i bVar = new cn.xiaochuankeji.tieba.ui.widget.indicator.b(this);
        bVar.setAdjustMode(true);
        this.a = new o(this.d);
        bVar.setAdapter(this.a);
        this.m.setNavigator(bVar);
        this.m.setVisibility(4);
        this.w.setOnClickListener(this);
        this.v.setOnClickListener(this);
        this.u.setOnClickListener(this);
        this.x.setOnClickListener(this);
        if (!isFinishing()) {
            e();
        }
        h.a(this, "zy_event_memberdetail_page", "页面进入");
        h.a(this, "zy_event_memberdetail_page", "切换到帖子Tab");
    }

    private void e() {
        g.a((Activity) this, true);
        if (this.g != null) {
            this.g.a((cn.xiaochuankeji.tieba.background.member.b.a) this);
        }
    }

    public void a(boolean z, JSONArray jSONArray, ArrayList<Post> arrayList, int i, boolean z2, long j, int i2, String str) {
        g.c(this);
        if (z) {
            this.p.setVisibility(8);
            k();
            this.I.a(a(jSONArray), z2, j);
            this.q = new ViewHeaderMemberDetail(this);
            this.q.setDataBy(this.g);
            this.z.setText(this.g.a.getName());
            this.n.addView(this.q, 0);
            this.k = i2;
            j();
            return;
        }
        cn.xiaochuankeji.tieba.background.utils.g.a(str);
        this.u.setVisibility(8);
        this.p.b();
    }

    private List<cn.xiaochuankeji.tieba.ui.topic.data.c> a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        List<cn.xiaochuankeji.tieba.ui.topic.data.c> linkedList = new LinkedList();
        for (int i = 0; i < jSONArray.length(); i++) {
            linkedList.add(PostDataBean.getPostDataBeanFromJson(jSONArray.optJSONObject(i)));
        }
        return linkedList;
    }

    private void j() {
        boolean z = false;
        Member member = this.g.a;
        if (cn.xiaochuankeji.tieba.background.a.g().c() == member.getId()) {
            this.w.setVisibility(8);
            this.v.setVisibility(8);
            this.u.setVisibility(8);
        } else {
            this.w.setVisibility(0);
            this.v.setVisibility(0);
            this.u.setVisibility(0);
            if (member.atted() == 1 || member.atted() == 2) {
                z = true;
            }
            c(z);
        }
        this.t.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(member.getId(), member.getAvatarID()));
        this.t.setEnabled(true);
    }

    private void c(boolean z) {
        this.A = z;
        org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.ui.topic.data.b(this.g.a.getId(), z));
        boolean z2 = (this.s.getTag() instanceof Boolean) && ((Boolean) this.s.getTag()).booleanValue();
        b(z2);
    }

    private void k() {
        this.j = new a(this, getSupportFragmentManager());
        this.m.setVisibility(0);
        this.l.setVisibility(0);
        this.l.setAdapter(this.j);
        if (1 == this.E) {
            this.l.setCurrentItem(1);
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    public void b(int i) {
    }

    private void p() {
        new cn.xiaochuankeji.tieba.background.b.a(this.i, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
            final /* synthetic */ MemberDetailActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onResponse(Object obj, Object obj2) {
                a((JSONObject) obj, obj2);
            }

            public void a(JSONObject jSONObject, Object obj) {
                this.a.k = 1;
                cn.xiaochuankeji.tieba.background.utils.g.a("已加入黑名单");
            }
        }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
            final /* synthetic */ MemberDetailActivity a;

            {
                this.a = r1;
            }

            public void onErrorResponse(XCError xCError, Object obj) {
                cn.xiaochuankeji.tieba.background.utils.g.a(xCError.getMessage());
            }
        }).execute();
    }

    private void q() {
        new cn.xiaochuankeji.tieba.background.b.c(this.i, 1, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
            final /* synthetic */ MemberDetailActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onResponse(Object obj, Object obj2) {
                a((JSONObject) obj, obj2);
            }

            public void a(JSONObject jSONObject, Object obj) {
                this.a.k = 0;
                cn.xiaochuankeji.tieba.background.utils.g.a("已移出黑名单");
            }
        }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
            final /* synthetic */ MemberDetailActivity a;

            {
                this.a = r1;
            }

            public void onErrorResponse(XCError xCError, Object obj) {
                cn.xiaochuankeji.tieba.background.utils.g.a(xCError.getMessage());
            }
        }).execute();
    }

    private void a(int i, String str) {
        cn.xiaochuankeji.tieba.background.b.b bVar;
        if (str.equalsIgnoreCase("review")) {
            bVar = new cn.xiaochuankeji.tieba.background.b.b(this.H, this.F > 0 ? this.F : this.i, str, i, null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
                final /* synthetic */ MemberDetailActivity a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onResponse(Object obj, Object obj2) {
                    a((JSONObject) obj, obj2);
                }

                public void a(JSONObject jSONObject, Object obj) {
                    cn.xiaochuankeji.tieba.background.utils.g.a("举报成功");
                }
            }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
                final /* synthetic */ MemberDetailActivity a;

                {
                    this.a = r1;
                }

                public void onErrorResponse(XCError xCError, Object obj) {
                    cn.xiaochuankeji.tieba.background.utils.g.a(xCError.getMessage());
                }
            });
        } else {
            long j;
            if (this.F > 0) {
                j = this.F;
            } else {
                j = this.i;
            }
            bVar = new cn.xiaochuankeji.tieba.background.b.b(j, str, i, null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
                final /* synthetic */ MemberDetailActivity a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onResponse(Object obj, Object obj2) {
                    a((JSONObject) obj, obj2);
                }

                public void a(JSONObject jSONObject, Object obj) {
                    cn.xiaochuankeji.tieba.background.utils.g.a("举报成功");
                }
            }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
                final /* synthetic */ MemberDetailActivity a;

                {
                    this.a = r1;
                }

                public void onErrorResponse(XCError xCError, Object obj) {
                    cn.xiaochuankeji.tieba.background.utils.g.a(xCError.getMessage());
                }
            });
        }
        bVar.execute();
    }

    public void a_(int i) {
        c(i);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                finish();
                return;
            case R.id.ivFollow:
                if (cn.xiaochuankeji.tieba.ui.auth.a.a(this, "member_detail", this.A ? -10 : 10) && this.B == null && this.C == null) {
                    u();
                    return;
                }
                return;
            case R.id.ivSendMsg:
                r();
                h.a(this, "zy_event_memberdetail_page", "点私信");
                return;
            case R.id.ivMore:
                s();
                return;
            default:
                return;
        }
    }

    private void r() {
        if (this.D) {
            finish();
        } else if (cn.xiaochuankeji.tieba.ui.auth.a.a(this, "member_detail", 6)) {
            cn.xiaochuankeji.tieba.ui.chat.a.b.a((Context) this, this.g.a, false);
        }
    }

    private void s() {
        if (cn.xiaochuankeji.tieba.background.a.g().c() != this.g.a.getId()) {
            SDBottomSheet sDBottomSheet = new SDBottomSheet(this, new SDBottomSheet.b(this) {
                final /* synthetic */ MemberDetailActivity a;

                {
                    this.a = r1;
                }

                public void a(int i) {
                    if (i == 3) {
                        this.a.t();
                    } else if (i == 2) {
                        if (cn.xiaochuankeji.tieba.ui.auth.a.a(this.a, "member_detail", 21)) {
                            f.a("确定加入黑名单?", "加入黑名单后，你将不再收到对方私信，对方也不能对你的内容进行评论、顶踩等操作。", this.a, new cn.xiaochuankeji.tieba.ui.widget.f.a(this) {
                                final /* synthetic */ AnonymousClass2 a;

                                {
                                    this.a = r1;
                                }

                                public void a(boolean z) {
                                    if (z) {
                                        this.a.a.p();
                                    }
                                }
                            }, true);
                        }
                    } else if (i == 1) {
                        this.a.q();
                    }
                }
            });
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_report, "举报", 3));
            if (this.k == 1) {
                arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_topic_block, "移出黑名单", 1));
            } else {
                arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_topic_block, "加入黑名单", 2));
            }
            sDBottomSheet.a(arrayList, null);
            sDBottomSheet.b();
        }
    }

    private void t() {
        SDCheckSheet sDCheckSheet = new SDCheckSheet(this, new cn.xiaochuankeji.tieba.ui.widget.SDCheckSheet.a(this) {
            final /* synthetic */ MemberDetailActivity a;

            {
                this.a = r1;
            }

            public void a(int i) {
                if (i < 4) {
                    this.a.d(i);
                }
            }
        });
        sDCheckSheet.a("个人资料违规", 0, false);
        sDCheckSheet.a("帖子违规", 1, false);
        sDCheckSheet.a("评论违规", 2, false);
        sDCheckSheet.a("聊天违规", 3, false);
        sDCheckSheet.a("取消", 4, true);
        sDCheckSheet.b();
    }

    private void d(int i) {
        LinkedHashMap k;
        String str = "member";
        LinkedHashMap linkedHashMap = null;
        if (i == 0) {
            linkedHashMap = cn.xiaochuankeji.tieba.background.utils.c.a.c().k();
            str = "member";
            this.F = 0;
        } else if (i == 1) {
            linkedHashMap = cn.xiaochuankeji.tieba.background.utils.c.a.c().m();
            str = "post";
        } else if (i == 2) {
            linkedHashMap = cn.xiaochuankeji.tieba.background.utils.c.a.c().o();
            str = "review";
        } else if (i == 3) {
            linkedHashMap = cn.xiaochuankeji.tieba.background.utils.c.a.c().u();
            str = "chat";
            this.F = 0;
        }
        if ((this.F <= 0 || ((long) i) != this.G) && i != 3) {
            str = "member";
            this.F = 0;
            k = cn.xiaochuankeji.tieba.background.utils.c.a.c().k();
        } else {
            k = linkedHashMap;
        }
        if (k.size() == 0) {
            a(0, str);
            return;
        }
        SDCheckSheet sDCheckSheet = new SDCheckSheet(this, new cn.xiaochuankeji.tieba.ui.widget.SDCheckSheet.a(this) {
            final /* synthetic */ MemberDetailActivity b;

            public void a(int i) {
                if (i != -123) {
                    this.b.a(i, str);
                } else if (str != "review") {
                    CustomReportReasonActivity.a(this.b, this.b.F > 0 ? this.b.F : this.b.i, this.b.r, str);
                } else {
                    CustomReportReasonActivity.a(this.b, this.b.H, this.b.F > 0 ? this.b.F : this.b.i, this.b.r, str);
                }
            }
        });
        int i2 = 0;
        for (Entry entry : k.entrySet()) {
            int i3;
            str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            int parseInt = Integer.parseInt(str);
            int i4 = i2 + 1;
            String trim = str2.trim();
            if (trim.equals("其他")) {
                this.r = parseInt;
                i3 = -123;
            } else {
                i3 = parseInt;
            }
            if (i4 == k.size()) {
                sDCheckSheet.a(trim, i3, true);
            } else {
                sDCheckSheet.a(trim, i3, false);
            }
            i2 = i4;
        }
        sDCheckSheet.b();
    }

    private void u() {
        if (this.A) {
            w();
            h.a(this, "zy_event_memberdetail_page", "取消关注用户");
            return;
        }
        v();
        h.a(this, "zy_event_memberdetail_page", "关注用户");
    }

    private void v() {
        if (this.B == null) {
            final Member member = this.g.a;
            this.B = new cn.xiaochuankeji.tieba.background.e.b(member.getId(), null, null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
                final /* synthetic */ MemberDetailActivity b;

                public /* synthetic */ void onResponse(Object obj, Object obj2) {
                    a((JSONObject) obj, obj2);
                }

                public void a(JSONObject jSONObject, Object obj) {
                    this.b.B = null;
                    int fans = member.getFans() + 1;
                    member.setFans(fans);
                    this.b.q.a(fans);
                    this.b.c(true);
                }
            }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
                final /* synthetic */ MemberDetailActivity a;

                {
                    this.a = r1;
                }

                public void onErrorResponse(XCError xCError, Object obj) {
                    this.a.B = null;
                    cn.xiaochuankeji.tieba.background.utils.g.a(xCError.getMessage());
                }
            });
            this.B.execute();
        }
    }

    private void w() {
        f.a("提示", "确定取消关注吗？", this, new cn.xiaochuankeji.tieba.ui.widget.f.a(this) {
            final /* synthetic */ MemberDetailActivity a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
                if (z) {
                    this.a.x();
                }
            }
        });
    }

    private void x() {
        if (this.C == null) {
            final Member member = this.g.a;
            this.C = new cn.xiaochuankeji.tieba.background.e.a(member.getId(), null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
                final /* synthetic */ MemberDetailActivity b;

                public /* synthetic */ void onResponse(Object obj, Object obj2) {
                    a((JSONObject) obj, obj2);
                }

                public void a(JSONObject jSONObject, Object obj) {
                    this.b.C = null;
                    int fans = member.getFans() - 1;
                    if (fans < 0) {
                        member.setFans(0);
                    } else {
                        member.setFans(fans);
                    }
                    this.b.q.a(fans);
                    this.b.c(false);
                }
            }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
                final /* synthetic */ MemberDetailActivity a;

                {
                    this.a = r1;
                }

                public void onErrorResponse(XCError xCError, Object obj) {
                    this.a.C = null;
                    cn.xiaochuankeji.tieba.background.utils.g.a(xCError.getMessage());
                }
            });
            this.C.execute();
        }
    }

    @Nullable
    private Fragment e(int i) {
        if (i == 0) {
            return this.I;
        }
        if (1 == i) {
            return d.a(this.i);
        }
        if (2 == i) {
            return b.a(this.i);
        }
        if (3 == i) {
            return a.a(this.i);
        }
        if (4 == i) {
            return c.a(this.i);
        }
        return null;
    }

    @Nullable
    private Fragment f(int i) {
        if (i == 0) {
            return this.I;
        }
        if (1 == i) {
            return d.a(this.i);
        }
        if (2 == i) {
            return a.a(this.i);
        }
        if (3 == i) {
            return c.a(this.i);
        }
        return null;
    }

    protected void onResume() {
        super.onResume();
        this.l.addOnPageChangeListener(this);
        this.a.a(this.l);
        this.o.a((cn.xiaochuankeji.tieba.ui.widget.StickyNavLayout.a) this);
    }

    protected void onPause() {
        super.onPause();
        this.l.removeOnPageChangeListener(this);
        this.a.c();
        this.o.b(this);
        if (d.a().h() && d.a().i()) {
            d.a().f();
            cn.xiaochuankeji.tieba.ui.voice.b.a.a().a(d.a().b().a, d.a().b().f);
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public void onPageScrolled(int i, float f, int i2) {
        if (this.m != null) {
            this.m.a(i, f, i2);
        }
    }

    public void onPageSelected(int i) {
        if (i == 0) {
            h.a(this, "zy_event_memberdetail_page", "切换到帖子Tab");
        } else if (1 == i) {
            h.a(this, "zy_event_memberdetail_page", "切换到评论Tab");
        } else if (2 == i) {
            h.a(this, "zy_event_memberdetail_page", "切换到话题Tab");
        }
        if (this.m != null) {
            this.m.a(i);
        }
    }

    public void onPageScrollStateChanged(int i) {
        if (this.m != null) {
            this.m.b(i);
        }
    }
}
