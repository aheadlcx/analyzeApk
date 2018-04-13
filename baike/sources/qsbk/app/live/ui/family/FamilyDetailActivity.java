package qsbk.app.live.ui.family;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.r0adkll.slidr.Slidr;
import cz.msebera.android.httpclient.protocol.HTTP;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import qsbk.app.core.model.User;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.ui.BrowseLargeImageActivity;
import qsbk.app.core.ui.base.BaseActivity;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.widget.SimpleDialog.Builder;
import qsbk.app.core.widget.refresh.SwipeRefreshLayoutBoth;
import qsbk.app.live.R;
import qsbk.app.live.adapter.FamilyAnchorAdapter;
import qsbk.app.live.adapter.FamilyEliteAdapter;
import qsbk.app.live.adapter.FamilyGatherRecordAdapter;
import qsbk.app.live.model.FamilyAnchorData;
import qsbk.app.live.model.FamilyGatherRecordData;
import qsbk.app.live.model.FamilyMemberData;
import qsbk.app.live.widget.FamilyGatherDialog;
import qsbk.app.live.widget.FamilyLevelView;

public class FamilyDetailActivity extends BaseActivity implements OnClickListener {
    private static int T = 1001;
    private static int U = 1002;
    private ImageView A;
    private TextView B;
    private int C;
    private TextView D;
    private int E;
    private int F;
    private RelativeLayout G;
    private FamilyLevelView H;
    private String I;
    private String J;
    private String K;
    private String L;
    private User M;
    private int N;
    private int O;
    private int P = 0;
    private int Q = 0;
    private int R = -1;
    private int S = 0;
    private List<FamilyMemberData> V = new ArrayList();
    private List<FamilyAnchorData> W = new ArrayList();
    private List<FamilyGatherRecordData> X = new ArrayList();
    private SwipeRefreshLayoutBoth Y;
    private boolean Z = false;
    int a = 3;
    private int aa;
    private ImageView b;
    private ImageView c;
    private SimpleDraweeView d;
    private SimpleDraweeView e;
    private TextView f;
    private TextView g;
    private SimpleDraweeView h;
    private TextView i;
    private TextView j;
    private TextView k;
    private ImageView l;
    private RecyclerView m;
    private RecyclerView n;
    private RecyclerView o;
    private FamilyEliteAdapter p;
    private FamilyAnchorAdapter q;
    private FamilyGatherRecordAdapter r;
    private TextView s;
    private TextView t;
    private RelativeLayout u;
    private TextView v;
    private long w;
    private LinearLayout x;
    private boolean y = true;
    private ImageView z;

    protected int getLayoutId() {
        return R.layout.activity_family_detail;
    }

    protected void initView() {
        this.b = (ImageView) $(R.id.iv_back);
        this.c = (ImageView) $(R.id.iv_setting);
        this.d = (SimpleDraweeView) $(R.id.iv_family_avatar);
        this.e = (SimpleDraweeView) $(R.id.iv_family_avatar_bg);
        this.f = (TextView) $(R.id.tv_family_name);
        this.H = (FamilyLevelView) $(R.id.fl_level);
        this.g = (TextView) $(R.id.tv_notice);
        this.h = (SimpleDraweeView) $(R.id.iv_head_avatar);
        this.i = (TextView) $(R.id.tv_head_name);
        this.j = (TextView) $(R.id.tv_rank);
        this.k = (TextView) $(R.id.tv_member_num);
        this.m = (RecyclerView) $(R.id.rv_gather_record);
        this.n = (RecyclerView) $(R.id.rv_members);
        this.o = (RecyclerView) $(R.id.rv_anchors);
        this.s = (TextView) $(R.id.tv_all_members);
        this.t = (TextView) $(R.id.tv_all_anchors);
        this.l = (ImageView) $(R.id.iv_arrow_down);
        this.u = (RelativeLayout) $(R.id.rl_avatar);
        this.v = (TextView) $(R.id.tv_apply);
        this.x = (LinearLayout) $(R.id.ll_apply);
        this.Y = (SwipeRefreshLayoutBoth) $(R.id.refresher);
        this.z = (ImageView) $(R.id.iv_arrow);
        this.A = (ImageView) $(R.id.iv_arrow2);
        this.B = (TextView) $(R.id.tv_card_num);
        this.D = (TextView) $(R.id.tv_card_hist);
        this.G = (RelativeLayout) $(R.id.rl_card);
        this.G.setVisibility(this.P == 0 ? 8 : 0);
        Slidr.attach(this, AppUtils.getEdgeSlidrConfig());
    }

    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            this.w = intent.getLongExtra("familyId", 0);
            this.J = intent.getStringExtra("familyAvatar");
            this.K = intent.getStringExtra("familyName");
            this.L = intent.getStringExtra("familyBadge");
            this.I = intent.getStringExtra("familyNotice");
            if (TextUtils.isEmpty(this.I)) {
                this.I = getString(R.string.family_notice_empty);
            }
            this.M = (User) intent.getSerializableExtra("familyHead");
            int i = (this.M == null || !this.M.isMe()) ? 0 : 1;
            this.P = i;
            if (this.M == null || !this.M.isMe()) {
                i = 0;
            } else {
                i = 1;
            }
            this.N = i;
        }
        LayoutManager linearLayoutManager = new LinearLayoutManager(this, 0, false);
        this.p = new FamilyEliteAdapter(this, this.V);
        this.n.setLayoutManager(linearLayoutManager);
        this.n.setAdapter(this.p);
        linearLayoutManager = new LinearLayoutManager(this, 0, false);
        this.q = new FamilyAnchorAdapter(this, this.W);
        this.o.setLayoutManager(linearLayoutManager);
        this.o.setAdapter(this.q);
        linearLayoutManager = new LinearLayoutManager(this, 1, false);
        this.r = new FamilyGatherRecordAdapter(this, this.X);
        this.m.setLayoutManager(linearLayoutManager);
        this.m.setAdapter(this.r);
        this.d.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.s.setOnClickListener(this);
        this.t.setOnClickListener(this);
        this.l.setOnClickListener(this);
        this.z.setOnClickListener(this);
        this.A.setOnClickListener(this);
        this.D.setOnClickListener(this);
        f();
        this.Y.setOnRefreshListener(new n(this));
        a();
    }

    private void a() {
        c();
        b();
        g();
        h();
        i();
    }

    private void b() {
        if (this.w != 0 && AppUtils.getInstance().getUserInfoProvider().isLogin()) {
            NetRequest.getInstance().get(UrlConstants.FAMILY_DETAIL, new ab(this));
        }
    }

    private void c() {
        int i;
        if (this.M != null) {
            AppUtils.getInstance().getImageProvider().loadAvatar(this.h, this.M.headurl);
            this.i.setText(this.M.name);
            this.h.setOnClickListener(new ah(this));
            this.i.setOnClickListener(new ai(this));
        }
        if (this.P == 1) {
            this.c.setVisibility(0);
            this.c.setImageResource(R.drawable.family_more);
        } else if (this.P == 2) {
            this.c.setVisibility(0);
            this.c.setImageResource(R.drawable.family_more);
        } else {
            this.c.setVisibility(8);
        }
        RelativeLayout relativeLayout = this.G;
        if (this.P == 0) {
            i = 8;
        } else {
            i = 0;
        }
        relativeLayout.setVisibility(i);
        AppUtils.getInstance().getImageProvider().loadAvatar(this.d, this.J, 5);
        a(this.J);
        this.f.setText(this.K);
        this.g.setText(this.I);
        l();
        this.k.setText(this.N + "/20");
        if (this.O > 0) {
            this.j.setText(getString(R.string.family_rank, new Object[]{this.O + ""}));
        } else {
            this.j.setText(R.string.family_no_rank);
        }
        this.x.setVisibility(8);
        switch (this.Q) {
            case 0:
                this.x.setVisibility(8);
                break;
            case 1:
                this.x.setVisibility(0);
                this.v.setText(R.string.family_applying);
                this.v.setTextColor(getResources().getColor(R.color.color_ADADAD));
                this.v.setClickable(false);
                this.v.setEnabled(false);
                break;
            case 2:
                this.x.setVisibility(0);
                this.v.setText(R.string.family_apply);
                this.v.setTextColor(getResources().getColor(R.color.color_793D00));
                this.v.setEnabled(true);
                this.v.setOnClickListener(new aj(this));
                break;
            case 3:
                this.x.setVisibility(0);
                this.v.setText(R.string.family_apply_full);
                this.v.setTextColor(getResources().getColor(R.color.color_ADADAD));
                this.v.setClickable(false);
                this.v.setEnabled(false);
                break;
        }
        if (this.R >= 0) {
            this.x.setVisibility(0);
            this.v.setText(getString(R.string.family_detail_publish, new Object[]{this.R + "", this.S + ""}));
            this.v.setTextColor(Color.parseColor("#793D00"));
            if (this.R == 0) {
                this.v.setEnabled(false);
                this.v.setClickable(false);
            } else {
                this.v.setEnabled(true);
                this.v.setOnClickListener(new ak(this));
            }
        }
        this.B.setText(this.C + MqttTopic.TOPIC_LEVEL_SEPARATOR + this.N);
        if (this.aa == 0) {
            this.D.setText(R.string.family_card_exp);
            this.D.setTextColor(Color.parseColor("#7B4600"));
            this.D.setBackgroundResource(R.drawable.btn_yellow_selector);
            this.D.setOnClickListener(new al(this));
        } else {
            this.D.setText(getString(R.string.family_card_exp_days, new Object[]{this.E + ""}));
            this.D.setTextColor(Color.parseColor("#8B8B8B"));
            this.D.setBackgroundResource(R.drawable.bg_corner_f2f2f2_5dp);
            this.D.setClickable(false);
        }
        if (TextUtils.isEmpty(this.L)) {
            this.H.setVisibility(8);
            return;
        }
        this.H.setVisibility(0);
        this.H.setLevelAndName(this.F, this.L);
    }

    private void d() {
        new FamilyGatherDialog(this, this).show();
    }

    private void e() {
        NetRequest.getInstance().post(UrlConstants.FAMILY_APPLY, new am(this));
    }

    private void f() {
        FamilyAnchorData familyAnchorData = new FamilyAnchorData();
        this.W.add(familyAnchorData);
        this.W.add(familyAnchorData);
        this.W.add(familyAnchorData);
        this.W.add(familyAnchorData);
        this.q.notifyDataSetChanged();
        FamilyMemberData familyMemberData = new FamilyMemberData();
        this.V.add(familyMemberData);
        this.V.add(familyMemberData);
        this.V.add(familyMemberData);
        this.V.add(familyMemberData);
        this.p.notifyDataSetChanged();
    }

    private void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            Fresco.getImagePipeline().fetchDecodedImage(ImageRequestBuilder.newBuilderWithSource(Uri.parse(str)).setProgressiveRenderingEnabled(true).build(), this).subscribe(new an(this), CallerThreadExecutor.getInstance());
        }
    }

    private void g() {
        NetRequest.getInstance().get(UrlConstants.FAMILY_MEMBER_WEEK_RANK, new o(this));
    }

    private void h() {
        NetRequest.getInstance().get(UrlConstants.FAMILY_ANCHOR_SUPPORT, new q(this));
    }

    private void i() {
        NetRequest.getInstance().get(UrlConstants.FAMILY_GATHER_RECORD, new s(this));
    }

    public void onClick(View view) {
        long id = (long) view.getId();
        Intent intent;
        if (id == ((long) R.id.iv_family_avatar)) {
            intent = new Intent(this, BrowseLargeImageActivity.class);
            intent.putExtra("url", this.J);
            startActivity(intent);
        } else if (id == ((long) R.id.iv_back)) {
            finish();
        } else if (id == ((long) R.id.iv_setting)) {
            if (this.P == 1) {
                a(view);
            } else if (this.P == 2) {
                b(view);
            }
        } else if (id == ((long) R.id.tv_all_members) || id == ((long) R.id.iv_arrow)) {
            intent = new Intent(this, FamilyMemberActivity.class);
            intent.putExtra("familyId", this.w);
            intent.putExtra(HTTP.IDENTITY_CODING, this.P);
            startActivityForResult(intent, U);
        } else if (id == ((long) R.id.tv_all_anchors) || id == ((long) R.id.iv_arrow2)) {
            intent = new Intent(this, FamilyAnchorActivity.class);
            intent.putExtra("familyId", this.w);
            startActivity(intent);
        } else if (id == ((long) R.id.iv_arrow_down)) {
            if (this.a == 3) {
                this.g.setMaxLines(10);
                this.a = 10;
                this.g.setText(this.I);
                this.l.setImageResource(R.drawable.ic_arrow_up);
            } else {
                this.g.setMaxLines(3);
                this.a = 3;
                this.g.setText(this.I);
                this.l.setImageResource(R.drawable.ic_arrow_down);
            }
            a(this.a);
        }
    }

    private void j() {
        NetRequest.getInstance().get(UrlConstants.FAMILY_CARD, new u(this));
    }

    private void k() {
        Intent intent = new Intent(this, FamilyCreateActivity.class);
        intent.putExtra("familyId", this.w);
        intent.putExtra("familyAvatar", this.J);
        intent.putExtra("famillyName", this.K);
        intent.putExtra("familyBadge", this.L);
        intent.putExtra("familyNotice", this.I);
        startActivityForResult(intent, T);
    }

    private void l() {
        this.g.post(new v(this));
    }

    private void a(int i) {
        this.g.post(new w(this, i));
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == T && i2 == -1) {
            b();
        } else if (i == U && this.M != null && this.M.getOrigin() == AppUtils.getInstance().getUserInfoProvider().getUserOrigin() && this.M.getOriginId() == AppUtils.getInstance().getUserInfoProvider().getUserId()) {
            g();
        }
    }

    private void a(View view) {
        View inflate = View.inflate(this, R.layout.popup_window_family_detail_creator, null);
        PopupWindow popupWindow = new PopupWindow(inflate, -2, -2, true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        popupWindow.getContentView().setFocusableInTouchMode(true);
        popupWindow.getContentView().setFocusable(true);
        popupWindow.getContentView().setOnKeyListener(new x(this, popupWindow));
        TextView textView = (TextView) inflate.findViewById(R.id.tv_quit);
        textView.setText(R.string.family_quit);
        textView.setOnClickListener(new y(this, popupWindow));
        ((TextView) inflate.findViewById(R.id.tv_edit)).setOnClickListener(new z(this, popupWindow));
        a(popupWindow, view);
    }

    private void b(View view) {
        View inflate = View.inflate(this, R.layout.popup_window_family_detail, null);
        PopupWindow popupWindow = new PopupWindow(inflate, -2, -2, true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        popupWindow.getContentView().setFocusableInTouchMode(true);
        popupWindow.getContentView().setFocusable(true);
        popupWindow.getContentView().setOnKeyListener(new aa(this, popupWindow));
        TextView textView = (TextView) inflate.findViewById(R.id.tv_quit);
        textView.setText(R.string.family_quit);
        textView.setOnClickListener(new ad(this, popupWindow));
        a(popupWindow, view);
    }

    private void m() {
        Builder aeVar = new ae(this, R.style.SimpleDialog);
        aeVar.message(getString(R.string.family_quit_confirm)).positiveAction(getString(R.string.setting_confirm)).negativeAction(getString(R.string.setting_cancel));
        AppUtils.showDialogFragment(this, aeVar);
    }

    private void n() {
        NetRequest.getInstance().post(UrlConstants.FAMILY_EXIT, new af(this));
    }

    private void a(PopupWindow popupWindow, View view) {
        if (popupWindow != null && !popupWindow.isShowing()) {
            popupWindow.showAsDropDown(view, 0, 0);
        }
    }

    private void o() {
        String string;
        Builder agVar = new ag(this, R.style.SimpleDialog);
        if (this.N == 1) {
            string = getString(R.string.family_quit_dismiss);
        } else {
            string = getString(R.string.family_quit_assign);
        }
        agVar.message(string).positiveAction(getString(R.string.setting_confirm)).negativeAction(getString(R.string.setting_cancel));
        AppUtils.showDialogFragment(this, agVar);
    }

    public void updateBugleCount(int i) {
        this.R = i;
        c();
    }
}
