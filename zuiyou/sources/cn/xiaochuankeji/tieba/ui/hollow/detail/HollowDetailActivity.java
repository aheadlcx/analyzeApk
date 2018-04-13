package cn.xiaochuankeji.tieba.ui.hollow.detail;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.lifecycle.q;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.api.hollow.HollowService;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.network.c;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.ui.CustomReportReasonActivity;
import cn.xiaochuankeji.tieba.ui.base.a;
import cn.xiaochuankeji.tieba.ui.hollow.data.AudioDataBean;
import cn.xiaochuankeji.tieba.ui.hollow.data.HollowRecommendItemBean;
import cn.xiaochuankeji.tieba.ui.hollow.data.MemberDataBean;
import cn.xiaochuankeji.tieba.ui.hollow.data.MsgDataBean;
import cn.xiaochuankeji.tieba.ui.hollow.data.RoomDataBean;
import cn.xiaochuankeji.tieba.ui.hollow.report.ReportPlayHollowJson;
import cn.xiaochuankeji.tieba.ui.hollow.util.IAudioPlayer;
import cn.xiaochuankeji.tieba.ui.hollow.util.IAudioPlayer.PlayerStatus;
import cn.xiaochuankeji.tieba.ui.hollow.widget.AudioPlayView;
import cn.xiaochuankeji.tieba.ui.hollow.widget.HollowNavigationBar;
import cn.xiaochuankeji.tieba.ui.hollow.widget.HollowSoundView;
import cn.xiaochuankeji.tieba.ui.hollow.widget.TouchListenerLayout;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet;
import cn.xiaochuankeji.tieba.ui.widget.SDCheckSheet;
import cn.xiaochuankeji.tieba.ui.widget.f;
import cn.xiaochuankeji.tieba.widget.CustomEmptyView;
import com.alibaba.fastjson.JSONObject;
import com.izuiyou.a.a.b;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.a.h;
import com.scwang.smartrefresh.layout.e.d;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import rx.e;

public class HollowDetailActivity extends a {
    private c a;
    private HollowDetailModel b;
    private HollowNavigationBar c;
    private SmartRefreshLayout d;
    private HollowSoundView e;
    private CustomEmptyView f;
    private View g;
    private PlayerStatus h;
    private IAudioPlayer i;
    private String j;
    private long k;
    private long l;
    private AudioDataBean m;
    private RoomDataBean n;
    private RecyclerView o;
    private boolean p;
    private int q;
    private String r;
    private int s = 16;

    public static void a(Context context, long j, String str) {
        Intent intent = new Intent(context, HollowDetailActivity.class);
        intent.putExtra("roomId", j);
        intent.putExtra("from", str);
        context.startActivity(intent);
    }

    public static void a(Context context, long j, long j2) {
        Intent intent = new Intent(context, HollowDetailActivity.class);
        intent.putExtra("roomId", j);
        intent.putExtra("msgId", j2);
        context.startActivity(intent);
    }

    public static void a(Context context, HollowRecommendItemBean hollowRecommendItemBean, String str) {
        a(context, HollowRecommendItemBean.b(hollowRecommendItemBean), str);
    }

    public static void a(Context context, RoomDataBean roomDataBean, String str) {
        Intent intent = new Intent(context, HollowDetailActivity.class);
        intent.putExtra("roomData", roomDataBean);
        intent.putExtra("from", str);
        context.startActivity(intent);
    }

    protected int a() {
        return R.layout.activity_hollow_detail;
    }

    protected void i_() {
        j();
        k();
        p();
        q();
        s();
        t();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return this.e.onKeyDown(i, keyEvent) || super.onKeyDown(i, keyEvent);
    }

    public boolean h() {
        return true;
    }

    protected void onPause() {
        super.onPause();
        if (this.h != null && this.h.equals(PlayerStatus.PLAYING)) {
            r();
        }
        this.e.a();
    }

    protected void onDestroy() {
        this.i.e();
        this.e.b();
        super.onDestroy();
        cn.xiaochuankeji.tieba.ui.hollow.report.a.a().a("flow_detail");
    }

    private void j() {
        this.c = (HollowNavigationBar) findViewById(R.id.hollow_nav_bar);
        this.c.setBackIcon(R.drawable.ic_arrow_left_white);
        this.i = new cn.xiaochuankeji.tieba.ui.hollow.util.a(this);
    }

    private void k() {
        ((HollowNavigationBar) findViewById(R.id.hollow_nav_bar_fun)).setBackIcon(R.drawable.ic_arrow_left_white);
        this.f = (CustomEmptyView) findViewById(R.id.hollow_detail_empty_view);
        this.f.a(R.drawable.ic_post_empty, " ");
        this.g = findViewById(R.id.hollow_detail_empty_ll);
    }

    private void p() {
        this.k = getIntent().getLongExtra("roomId", 0);
        this.l = getIntent().getLongExtra("msgId", 0);
        if (this.k == 0) {
            this.n = (RoomDataBean) getIntent().getParcelableExtra("roomData");
            if (this.n == null) {
                g.a("没有树洞信息");
                finish();
            }
            this.k = this.n.id;
        }
        this.r = getIntent().getStringExtra("from");
        this.p = false;
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void q() {
        this.o = (RecyclerView) findViewById(R.id.hollow_detail_list);
        this.a = new c(this, findViewById(R.id.hollow_detail_view_line));
        this.o.setAdapter(this.a);
        this.o.setLayoutManager(new LinearLayoutManager(this));
        this.o.setAnimation(null);
        this.a.a(new e(this) {
            final /* synthetic */ HollowDetailActivity a;

            {
                this.a = r1;
            }

            public void onClick(AudioDataBean audioDataBean, AudioPlayView audioPlayView) {
                if (audioDataBean != null) {
                    if (audioDataBean.a(this.a.m)) {
                        this.a.r();
                    } else {
                        this.a.a(audioDataBean, audioPlayView);
                    }
                }
            }
        });
        this.a.a(new d(this) {
            final /* synthetic */ HollowDetailActivity a;

            {
                this.a = r1;
            }

            public void a(MsgDataBean msgDataBean) {
                if (!this.a.p) {
                    this.a.a(1, msgDataBean.room_id, msgDataBean.id, msgDataBean.member.xid, msgDataBean.self == 1);
                }
            }
        });
        if (this.n != null) {
            this.a.a(null, this.n, false);
        }
    }

    private void a(AudioDataBean audioDataBean, final AudioPlayView audioPlayView) {
        b.c("AudioPlayListener -> DetailSetValue -> AudioData : " + audioDataBean + "  audioPlayView : " + audioPlayView);
        this.i.a(audioDataBean, new IAudioPlayer.a(this) {
            final /* synthetic */ HollowDetailActivity b;

            public void a(PlayerStatus playerStatus) {
                b.c("AudioPlayListener -> DetailOnStatusChange -> status : " + playerStatus);
                this.b.h = playerStatus;
                switch (playerStatus) {
                    case LOADING:
                        audioPlayView.a();
                        return;
                    case PLAYING:
                        audioPlayView.b();
                        return;
                    case PAUSE:
                        audioPlayView.d();
                        return;
                    case END:
                        audioPlayView.c();
                        return;
                    default:
                        return;
                }
            }

            public void a(final long j) {
                this.b.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass10 b;

                    public void run() {
                        audioPlayView.a(j);
                        try {
                            long j;
                            ReportPlayHollowJson reportPlayHollowJson = new ReportPlayHollowJson();
                            String str = this.b.b.n.audio.a(this.b.b.m) ? "flow_xroom" : "flow_xmsg";
                            if (str.equals("flow_xroom")) {
                                j = this.b.b.n.id;
                            } else {
                                j = this.b.b.a.a(this.b.b.m);
                            }
                            reportPlayHollowJson.ownerId = j;
                            reportPlayHollowJson.owner = str;
                            reportPlayHollowJson.audioDuration = this.b.b.m.dur;
                            reportPlayHollowJson.audioUri = this.b.b.m.uri;
                            reportPlayHollowJson.deviceType = 0;
                            reportPlayHollowJson.playDur = Math.abs(this.b.b.m.dur - j);
                            reportPlayHollowJson.version = cn.xiaochuankeji.tieba.background.utils.d.a.f;
                            cn.xiaochuankeji.tieba.ui.hollow.report.a.a().a(reportPlayHollowJson);
                        } catch (Exception e) {
                            b.e(e);
                        }
                    }
                });
            }
        });
        this.m = audioDataBean;
        r();
    }

    private void r() {
        b.c("AudioPlayListener -> DetailRefresh -> presentStatus : " + this.h);
        switch (this.h) {
            case LOADING:
            case PLAYING:
                this.i.b();
                return;
            case PAUSE:
                this.i.c();
                return;
            case END:
            case PREPARE:
                this.i.a();
                return;
            default:
                return;
        }
    }

    private void s() {
        this.e = (HollowSoundView) findViewById(R.id.hollow_reply_sound_view);
        this.e.b((Activity) this);
        this.e.setOnSendClickListener(new cn.xiaochuankeji.tieba.ui.hollow.widget.a.a(this) {
            final /* synthetic */ HollowDetailActivity a;

            {
                this.a = r1;
            }

            public void a(String str, String str2, long j, cn.xiaochuankeji.tieba.ui.hollow.widget.a.b bVar) {
                if (cn.xiaochuankeji.tieba.ui.auth.a.a(this.a, "hollow_detail", 42)) {
                    this.a.a(str, str2, j, bVar);
                    this.a.e.c();
                }
            }

            public void a(boolean z) {
                this.a.p = z;
            }
        });
        this.e.post(new Runnable(this) {
            final /* synthetic */ HollowDetailActivity a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.q = this.a.e.getHeight();
            }
        });
        this.d = (SmartRefreshLayout) findViewById(R.id.hollow_detail_refresh);
        this.d.a(new d(this) {
            final /* synthetic */ HollowDetailActivity a;

            {
                this.a = r1;
            }

            public void a(h hVar) {
            }

            public void a_(h hVar) {
                this.a.u();
            }
        });
        this.d.a(new com.scwang.smartrefresh.layout.e.a(this) {
            final /* synthetic */ HollowDetailActivity a;

            {
                this.a = r1;
            }

            public void a(final h hVar) {
                this.a.b.a(this.a.k, this.a.j, new a(this) {
                    final /* synthetic */ AnonymousClass14 b;

                    public void a(boolean z, String str) {
                        if (z) {
                            hVar.u();
                        } else {
                            hVar.t();
                        }
                        this.b.a.j = str;
                    }

                    public void a(Throwable th) {
                        hVar.u();
                        if (th instanceof ClientErrorException) {
                            g.a(th.getMessage());
                        } else {
                            g.a("网络不给力哦~");
                        }
                    }
                });
            }
        });
        ((TouchListenerLayout) findViewById(R.id.hollow_detail_listener_layout)).setOnPressListener(new cn.xiaochuankeji.tieba.ui.hollow.widget.b(this) {
            final /* synthetic */ HollowDetailActivity a;

            {
                this.a = r1;
            }

            public void a() {
                super.a();
                if (!this.a.p && this.a.e.getHeight() > this.a.q) {
                    this.a.e.a(this.a);
                }
            }
        });
    }

    private void a(String str, String str2, long j, cn.xiaochuankeji.tieba.ui.hollow.widget.a.b bVar) {
        this.b.a((Activity) this, this.k, str, j, str2, bVar);
    }

    private void t() {
        this.b = (HollowDetailModel) q.a((FragmentActivity) this).a(HollowDetailModel.class);
        this.b.a(this.a);
        u();
    }

    private void u() {
        this.b.a(this.k, this.l, this.r, new b(this) {
            final /* synthetic */ HollowDetailActivity a;

            {
                this.a = r1;
            }

            public void a(RoomDataBean roomDataBean, @NonNull final MemberDataBean memberDataBean, @Nullable MemberDataBean memberDataBean2) {
                this.a.c.a(memberDataBean.name, -1);
                switch (memberDataBean.gender) {
                    case 1:
                        this.a.c.setTitleIconRight(R.drawable.sexual_male);
                        break;
                    case 2:
                        this.a.c.setTitleIconRight(R.drawable.sexual_female);
                        break;
                }
                final boolean z = memberDataBean2 != null && memberDataBean.xid == memberDataBean2.xid;
                if (z) {
                    this.a.c.setTitleIconRight(R.drawable.sexual_me);
                } else {
                    this.a.c.a(roomDataBean, memberDataBean2);
                }
                this.a.c.a((int) R.drawable.nav_more_white, new OnClickListener(this) {
                    final /* synthetic */ AnonymousClass16 c;

                    public void onClick(View view) {
                        if (!this.c.a.p) {
                            this.c.a.a(0, this.c.a.k, -1, memberDataBean.xid, z);
                        }
                    }
                });
                this.a.e.setUserData(null);
            }
        }, new a(this) {
            final /* synthetic */ HollowDetailActivity a;

            {
                this.a = r1;
            }

            public void a(boolean z, String str) {
                this.a.d.m();
                this.a.g.setVisibility(8);
                this.a.a(z, str);
                this.a.n = this.a.a.a();
            }

            public void a(Throwable th) {
                this.a.d.m();
                this.a.g.setVisibility(0);
                this.a.f.setCustomText(th.getMessage());
                this.a.f.b();
            }
        });
    }

    private void a(int i, long j, long j2, long j3, boolean z) {
        final int i2 = i;
        final long j4 = j;
        final long j5 = j2;
        final long j6 = j3;
        SDBottomSheet sDBottomSheet = new SDBottomSheet(this, new SDBottomSheet.b(this) {
            final /* synthetic */ HollowDetailActivity e;

            @SuppressLint({"SwitchIntDef"})
            public void a(int i) {
                switch (i) {
                    case 9:
                        this.e.a(i2, i2 == 0 ? j4 : j5);
                        return;
                    case 12:
                        boolean z = i2 == 0;
                        this.e.a(z ? j4 : j5, z);
                        return;
                    case 17:
                        cn.xiaochuankeji.tieba.push.b.d.a(j6);
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("xroom_id", Long.valueOf(0));
                        jSONObject.put("block_xid", Long.valueOf(j6));
                        ((HollowService) c.b(HollowService.class)).blockXid(jSONObject).a(rx.f.a.c()).a(new e<EmptyJson>(this) {
                            final /* synthetic */ AnonymousClass3 a;

                            {
                                this.a = r1;
                            }

                            public /* synthetic */ void onNext(Object obj) {
                                a((EmptyJson) obj);
                            }

                            public void onCompleted() {
                            }

                            public void onError(Throwable th) {
                            }

                            public void a(EmptyJson emptyJson) {
                            }
                        });
                        return;
                    default:
                        return;
                }
            }
        });
        ArrayList arrayList = new ArrayList();
        if (z) {
            arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_delete, "删除", 9));
        } else {
            arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_report, "举报", 12));
            arrayList.add(new SDBottomSheet.c(R.drawable.toast_limit_post, "屏蔽此人消息", 17));
        }
        sDBottomSheet.a(arrayList, null);
        sDBottomSheet.b();
    }

    private void a(final long j, boolean z) {
        LinkedHashMap v;
        if (z) {
            v = cn.xiaochuankeji.tieba.background.utils.c.a.c().v();
        } else {
            v = cn.xiaochuankeji.tieba.background.utils.c.a.c().w();
        }
        if (v.size() != 0) {
            final String str = z ? "flow_xroom" : "flow_xmsg";
            SDCheckSheet sDCheckSheet = new SDCheckSheet(this, new SDCheckSheet.a(this) {
                final /* synthetic */ HollowDetailActivity c;

                public void a(int i) {
                    if (i == -123) {
                        CustomReportReasonActivity.a(this.c, j, this.c.s, str);
                    } else {
                        this.c.a(i, j, str);
                    }
                }
            });
            int i = 0;
            for (Entry entry : v.entrySet()) {
                int i2;
                String str2 = (String) entry.getKey();
                str = (String) entry.getValue();
                int parseInt = Integer.parseInt(str2);
                int i3 = i + 1;
                String trim = str.trim();
                if (trim.equals("其他")) {
                    this.s = parseInt;
                    i2 = -123;
                } else {
                    i2 = parseInt;
                }
                if (i3 == v.size()) {
                    sDCheckSheet.a(trim, i2, true);
                } else {
                    sDCheckSheet.a(trim, i2, false);
                }
                i = i3;
            }
            sDCheckSheet.b();
        }
    }

    private void a(final int i, final long j) {
        f.a("提示", "删除后不可恢复，确认删除？", this, new f.a(this) {
            final /* synthetic */ HollowDetailActivity c;

            public void a(boolean z) {
                if (z) {
                    switch (i) {
                        case 0:
                            this.c.b.b(this.c, j);
                            return;
                        case 1:
                            this.c.b.a(this.c, j);
                            return;
                        default:
                            return;
                    }
                }
            }
        });
    }

    private void a(boolean z, String str) {
        this.d.a(z);
        this.d.g(!z);
        this.j = str;
    }

    public void e() {
        if (this.o != null) {
            this.o.scrollToPosition(0);
        }
    }

    public void a(int i, long j, String str) {
        new cn.xiaochuankeji.tieba.background.b.b(0, j, str, i, null, new cn.xiaochuankeji.tieba.background.net.a.b<org.json.JSONObject>(this) {
            final /* synthetic */ HollowDetailActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onResponse(Object obj, Object obj2) {
                a((org.json.JSONObject) obj, obj2);
            }

            public void a(org.json.JSONObject jSONObject, Object obj) {
                g.a("举报成功");
            }
        }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
            final /* synthetic */ HollowDetailActivity a;

            {
                this.a = r1;
            }

            public void onErrorResponse(XCError xCError, Object obj) {
                g.a(xCError.getMessage());
            }
        }).execute();
    }
}
