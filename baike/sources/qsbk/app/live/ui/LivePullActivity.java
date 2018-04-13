package qsbk.app.live.ui;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.core.map.Location;
import qsbk.app.core.model.CommonVideo;
import qsbk.app.core.model.GiftData;
import qsbk.app.core.model.User;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.ConfigInfoUtil;
import qsbk.app.core.utils.Constants;
import qsbk.app.core.utils.LogUtils;
import qsbk.app.core.utils.TimeDelta;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.core.widget.EmptyPlaceholderView;
import qsbk.app.core.widget.EmptyPlaceholderView.OnEmptyClickListener;
import qsbk.app.live.LivePullLauncher;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveGiftMessage;
import qsbk.app.live.model.LiveMessage;
import qsbk.app.live.model.LiveRoom;
import qsbk.app.live.model.LiveRoomStatus;
import qsbk.app.live.model.VideoFilterData;
import qsbk.app.live.widget.GiftBox;
import qsbk.app.live.widget.GiftBox.GiftBoxListener;
import qsbk.app.live.widget.LivePullEndDialog;
import qsbk.app.live.widget.SendContinueButton;
import qsbk.app.utils.ListViewHelper;
import qsbk.app.widget.RefreshTipView;
import qsbk.app.ye.videotools.player.VideoPlayer;

public class LivePullActivity extends LiveBaseActivity implements OnEmptyClickListener, GiftBoxListener {
    public static final String TAG = LivePullActivity.class.getSimpleName();
    private String bA;
    private long bB;
    private EmptyPlaceholderView bC;
    private GiftData bD;
    private ImageView bE;
    private FrameLayout bF;
    private SendContinueButton bG;
    private CountDownTimer bH;
    private String bI;
    private boolean bJ;
    private long bK = 0;
    private TimeDelta bL;
    private int bM = 0;
    private boolean bN = false;
    private int bO = 0;
    private int bP = 0;
    private LivePullEndDialog bQ;
    private FrameLayout bR;
    private LinearLayout bS;
    private TextView bT;
    private TextView bU;
    private BroadcastReceiver bV = new dj(this);
    private Runnable bW = new dv(this);
    private long bX;
    private Runnable bY = new dn(this);
    long bt = 0;
    private String bu;
    private VideoPlayer bv = null;
    private Surface bw = null;
    private int bx = 0;
    private int by = 0;
    private String bz;

    protected void onCreate(Bundle bundle) {
        this.bX = System.currentTimeMillis();
        super.onCreate(bundle);
        this.bK = System.currentTimeMillis();
        this.bL = new TimeDelta();
    }

    protected void initView() {
        super.initView();
        this.A = (GiftBox) $(R.id.gift_box);
        this.A.initGiftView();
        this.A.setGiftBoxListener(this);
        this.bC = (EmptyPlaceholderView) findViewById(R.id.empty);
        this.bE = (ImageView) findViewById(R.id.btn_add_ten);
        this.bE.setOnClickListener(this);
        this.bR = (FrameLayout) findViewById(R.id.fl_send_continue);
        this.bF = (FrameLayout) findViewById(R.id.send_continue_container);
        this.bG = (SendContinueButton) findViewById(R.id.btn_send_continue);
        this.bG.setOnClickListener(this);
    }

    protected int getLayoutId() {
        return R.layout.live_pull_activity;
    }

    public void updateBalance(long j) {
        super.updateBalance(j);
        this.A.setBalanceView(j);
    }

    protected void initData() {
        registerReceiver(this.bV, new IntentFilter(Constants.FIRST_CHARGE_STATE));
        Intent intent = getIntent();
        if (intent != null) {
            this.bu = intent.getStringExtra("extraString");
            this.d = (CommonVideo) intent.getSerializableExtra("live");
            this.bz = intent.getStringExtra("liveUserId");
            this.bB = intent.getLongExtra("liveUserSource", 2);
            this.bA = intent.getStringExtra("livePlatformId");
            this.bf = intent.getDoubleExtra(ParamKey.LONGITUDE, (double) Location.NON_LOCATION);
            this.bg = intent.getDoubleExtra(ParamKey.LATITUDE, (double) Location.NON_LOCATION);
            if (intent.getBooleanExtra("fromPush", false)) {
                statEvent("push_follow_live", "click", this.bz, AppUtils.getInstance().getUserInfoProvider().getUserOrigin() + "_" + AppUtils.getInstance().getUserInfoProvider().getUserId(), "");
            }
            this.bc = intent.getStringExtra(LivePullLauncher.STSOURCE);
            this.bd = intent.getIntExtra("tapIndex", 0);
        }
        if ((this.d == null || !this.d.isLiveVideo()) && TextUtils.isEmpty(this.bz)) {
            finish();
            return;
        }
        if (this.d != null) {
            if (this.d.author != null) {
                if (!this.d.author.isFollow()) {
                    this.v.setVisibility(0);
                }
                this.aC.setOnLongClickListener(new dw(this));
            }
            if (this.d.isValidLive()) {
                this.az = true;
                f(false);
            }
            this.aO = this.d.visitors_count;
        }
        super.initData();
        this.bI = VideoFilterData.ORIGIN;
        this.bJ = false;
        this.g.setSurfaceTextureListener(new dx(this));
        if (this.d == null || !this.d.isValidLive()) {
            ao();
        }
        if (this.d != null) {
            ak();
            ap();
        }
    }

    protected void aj() {
        if (this.bS == null && this.d != null && !TextUtils.isEmpty(this.d.getContent())) {
            this.bS = (LinearLayout) $(R.id.ll_anchor_info);
            this.bT = (TextView) $(R.id.tv_title);
            this.bU = (TextView) $(R.id.tv_location);
            this.bS.setVisibility(0);
            if (TextUtils.isEmpty(this.d.location)) {
                this.bU.setVisibility(8);
            } else {
                this.bU.setVisibility(0);
                this.bU.setText(this.d.location + (this.mDistance >= 0.0d ? "·" + new DecimalFormat("#.0").format(this.mDistance) + "km" : ""));
            }
            this.bT.setText(this.d.getContent());
            this.bS.setScaleY(0.1f);
            this.bT.setAlpha(0.0f);
            this.bU.setAlpha(0.0f);
            ObjectAnimator.ofFloat(this.bS, View.X, new float[]{(float) (-WindowUtils.dp2Px(200)), (float) WindowUtils.dp2Px(20)}).setDuration(160);
            ObjectAnimator.ofFloat(this.bS, View.X, new float[]{(float) WindowUtils.dp2Px(20), (float) WindowUtils.dp2Px(10)}).setDuration(80);
            ObjectAnimator.ofFloat(this.bS, View.X, new float[]{(float) WindowUtils.dp2Px(10), (float) WindowUtils.dp2Px(10)}).setDuration(160);
            ObjectAnimator.ofFloat(this.bS, View.SCALE_Y, new float[]{0.1f, 1.0f}).setDuration(240);
            ObjectAnimator.ofFloat(this.bS, View.X, new float[]{(float) WindowUtils.dp2Px(10), (float) WindowUtils.dp2Px(10)}).setDuration(1520);
            ObjectAnimator.ofFloat(this.bS, View.SCALE_Y, new float[]{1.0f, 0.1f}).setDuration(240);
            ObjectAnimator.ofFloat(this.bS, View.X, new float[]{(float) WindowUtils.dp2Px(10), (float) (-WindowUtils.dp2Px(200))}).setDuration(160);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(new Animator[]{r0, r1, r2, r3, r4, r5, r6});
            animatorSet.addListener(new dy(this));
            animatorSet.start();
            ObjectAnimator.ofFloat(this.bU, View.ALPHA, new float[]{0.0f, 1.0f}).setDuration(120);
            ObjectAnimator.ofFloat(this.bU, View.ALPHA, new float[]{1.0f, 1.0f}).setDuration(1520);
            ObjectAnimator.ofFloat(this.bU, View.ALPHA, new float[]{1.0f, 0.0f}).setDuration(120);
            AnimatorSet animatorSet2 = new AnimatorSet();
            animatorSet2.playSequentially(new Animator[]{r0, r1, r2});
            ObjectAnimator.ofFloat(this.bT, View.ALPHA, new float[]{0.0f, 1.0f}).setDuration(120);
            ObjectAnimator.ofFloat(this.bT, View.ALPHA, new float[]{1.0f, 1.0f}).setDuration(1520);
            ObjectAnimator.ofFloat(this.bT, View.ALPHA, new float[]{1.0f, 0.0f}).setDuration(120);
            AnimatorSet animatorSet3 = new AnimatorSet();
            animatorSet3.playSequentially(new Animator[]{r0, r1, r2});
            postDelayed(new dz(this, animatorSet2, animatorSet3), 520);
        }
    }

    private void ao() {
        this.u.setVisibility(8);
        this.y.setVisibility(0);
        Map hashMap = new HashMap();
        hashMap.put("creator_id", !TextUtils.isEmpty(this.bz) ? this.bz : this.bA);
        hashMap.put("creator_source", Long.toString(this.bB));
        AppUtils.getInstance().getUserInfoProvider().networkRequest("liveDetail", hashMap, new ea(this, hashMap));
    }

    private void ap() {
        this.aA = this.d.stream_id;
        this.aE = this.d.room_id;
        AppUtils.getInstance().cancelNotification((int) this.d.getAuthorId());
        this.p.setText(this.d.getAuthorName());
        a(this.o, this.d.getAuthorAvatar());
        if (this.d.author.nick_id != 0) {
            this.aC.setText(getString(R.string.user_nick_id, new Object[]{String.valueOf(this.d.author.nick_id)}));
        } else {
            this.aC.setVisibility(8);
        }
        if (this.d.status == 0) {
            e(true);
            return;
        }
        this.az = true;
        d();
        if (l()) {
            postDelayed(new ec(this), 100);
        }
    }

    protected void a() {
        super.a();
        if (l()) {
            aq();
        }
    }

    private void aq() {
        if (Z()) {
            al();
        } else {
            am();
        }
    }

    protected void ab() {
        super.ab();
        if (l()) {
            al();
        }
    }

    protected void d(boolean z) {
        super.d(z);
        if (l()) {
            am();
        }
    }

    protected void J() {
        f(true);
    }

    private void f(boolean z) {
        T();
        if (A()) {
            i();
            return;
        }
        boolean z2 = (this.aJ != 0 || this.bK <= 0) && this.aJ <= 0;
        if (this.aX && (this.bw == null || !z2)) {
            j();
        }
        if (this.bw == null || !this.az || (!(this.isOnResume || z2) || isFinishing())) {
            LogUtils.d(TAG, "toStartLive return on Surface:" + this.bw + ", Living:" + this.az + ", onResume:" + this.isOnResume + ", isPlaying:" + z2 + ", isFinishing:" + isFinishing());
        } else if (this.bv == null || this.bw == null || !z2 || z) {
            h();
            if (this.bv == null) {
                this.bv = VideoPlayer.create();
            }
            if (this.bv == null) {
                a(R.string.video_player_view_create_fail);
                return;
            }
            if (ah()) {
                this.aB.setVisibility(0);
                this.aB.setText(this.d.getLiveUrl());
            }
            this.bv.setOnPreparedListener(new ed(this));
            this.bv.setOnInfoListener(new dk(this));
            this.bv.setOnErrorListener(new dl(this));
            this.bv.setOnCompletionListener(new dm(this));
            if (l()) {
                this.by = (this.bx * 3) / 4;
            }
            this.bv.setSurface(this.bw, this.bx, this.by);
            this.bv.setDataSource(this.d.getLiveUrl());
            this.bv.prepareAsync();
        } else {
            LogUtils.d(TAG, "toStartLive return on Surface:" + this.bw + ", VideoPlayer:" + this.bv + ", isPlaying:" + z2 + ", force:" + z);
        }
    }

    protected void c() {
        e(false);
    }

    protected void e(boolean z) {
        super.c();
        ax();
        this.bR.setVisibility(8);
        aB();
        if (isFinishing() || !(this.isOnResume || z)) {
            finish();
            return;
        }
        this.bQ = new LivePullEndDialog(this, getLiveUser(), this, this.aE);
        this.bQ.show();
    }

    private void ax() {
        this.A.setVisibility(8);
        setTransparentNavigationBarIfNeed();
    }

    protected void b() {
        super.b();
        this.D.setVisibility(0);
        if (ConfigInfoUtil.instance().isFirstCharge()) {
            this.I.setVisibility(0);
        } else {
            this.I.setVisibility(8);
        }
        this.G.setVisibility(8);
    }

    protected void a(String str, boolean z) {
        this.bI = str;
        this.bJ = z;
        if (this.bv != null) {
            if (TextUtils.isEmpty(str)) {
                str = VideoFilterData.ORIGIN;
            }
            this.bv.setFilter(VideoFilterData.getFilter((Context) this, str), z);
        }
    }

    protected void onRestart() {
        super.onRestart();
    }

    protected void onResume() {
        super.onResume();
        f(false);
        if (this.at != null && this.at.isShowing()) {
            aC();
        }
    }

    protected void ae() {
        h(false);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn_close || view.getId() == R.id.btn_confirm || !forwardIfNotLogin()) {
            disShowBottomFollowTipsDialog();
            if (view.getId() == R.id.btn_send_continue) {
                if (a(this.bD.pr)) {
                    s();
                    return;
                }
                ay();
                sendLiveMessageAndRefreshUI(LiveMessage.createGiftMessage(this.ax.getOriginId(), this.bD.gd, this.d.author.getOrigin(), this.d.author.getOriginId()));
                if (!isMessageOverloadOrLowDevice()) {
                    g(false);
                }
            } else if (view.getId() == R.id.btn_add_ten) {
                if (a(this.bD.pr * 10)) {
                    s();
                    return;
                }
                ay();
                sendLiveMessageAndRefreshUI(LiveMessage.createGiftMessage(this.ax.getOriginId(), this.bD.gd, this.d.author.getOrigin(), this.d.author.getOriginId(), 10));
                if (!isMessageOverloadOrLowDevice()) {
                    g(true);
                }
            } else if (view.getId() == R.id.btn_send) {
                r();
            } else if (view.getId() == R.id.tv_gift_count_week) {
                m();
            } else if (view.getId() == R.id.tv_gift_count_total) {
                t();
            } else if (view.getId() == R.id.tv_zhouxing_rank) {
                m();
            } else if (!AppUtils.isFastDoubleClick()) {
                if (view.getId() == R.id.btn_confirm) {
                    doConfirm();
                } else if (view.getId() == R.id.btn_gift) {
                    F();
                }
                super.onClick(view);
            }
        }
    }

    private void g(boolean z) {
        if (System.currentTimeMillis() - this.bt > 100) {
            LayoutParams layoutParams;
            this.bt = System.currentTimeMillis();
            Animation animationSet = new AnimationSet(true);
            Animation scaleAnimation = new ScaleAnimation(1.0f, 1.4f, 1.0f, 1.4f, 1, 0.5f, 1, 0.5f);
            Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            animationSet.addAnimation(scaleAnimation);
            animationSet.addAnimation(alphaAnimation);
            animationSet.setDuration(800);
            View imageView = new ImageView(getActivity());
            imageView.setImageResource(R.drawable.live_ripple_circle);
            if (z) {
                layoutParams = new FrameLayout.LayoutParams(WindowUtils.dp2Px(50), WindowUtils.dp2Px(50));
                layoutParams.rightMargin = WindowUtils.dp2Px(35);
                layoutParams.bottomMargin = WindowUtils.dp2Px(125);
            } else {
                layoutParams = new FrameLayout.LayoutParams(WindowUtils.dp2Px(100), WindowUtils.dp2Px(100));
                layoutParams.rightMargin = WindowUtils.dp2Px(10);
                layoutParams.bottomMargin = WindowUtils.dp2Px(10);
            }
            layoutParams.gravity = 85;
            imageView.setLayoutParams(layoutParams);
            animationSet.setAnimationListener(new do(this, imageView));
            this.bR.addView(imageView);
            imageView.startAnimation(animationSet);
        }
    }

    protected void C() {
        if (this.bE.getVisibility() != 0) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.bE, View.SCALE_X, new float[]{0.0f, 1.0f});
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.bE, View.SCALE_Y, new float[]{0.0f, 1.0f});
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(300);
            animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
            ofFloat = ObjectAnimator.ofFloat(this.bE, View.TRANSLATION_Y, new float[]{(float) WindowUtils.dp2Px(64), (float) (-WindowUtils.dp2Px(5))});
            ofFloat2 = ObjectAnimator.ofFloat(this.bE, View.TRANSLATION_Y, new float[]{(float) (-WindowUtils.dp2Px(5)), (float) WindowUtils.dp2Px(5)});
            ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.bE, View.TRANSLATION_Y, new float[]{(float) WindowUtils.dp2Px(5), 0.0f});
            ofFloat.setDuration(300);
            ofFloat2.setDuration(30);
            ofFloat3.setDuration(20);
            new AnimatorSet().playSequentially(new Animator[]{ofFloat, ofFloat2, ofFloat3});
            AnimatorSet animatorSet2 = new AnimatorSet();
            animatorSet2.playTogether(new Animator[]{animatorSet, r4});
            this.bE.setVisibility(0);
            animatorSet2.start();
        }
    }

    public void doSendGift() {
        if (this.bD == null) {
            return;
        }
        if (a(this.bD.pr)) {
            s();
        } else if (this.bD.selected && this.ax != null) {
            sendGift(this.bD.gd);
            if (this.bD.cb) {
                ay();
            }
        }
    }

    private void ay() {
        aA();
        if (this.bR.getVisibility() != 0) {
            this.bR.setVisibility(0);
            this.bF.setVisibility(0);
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.bF, View.SCALE_X, new float[]{0.0f, 1.1f});
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.bF, View.SCALE_Y, new float[]{0.0f, 1.1f});
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
            animatorSet.setDuration(100);
            ofFloat = ObjectAnimator.ofFloat(this.bF, View.SCALE_X, new float[]{1.1f, 0.9f});
            ofFloat2 = ObjectAnimator.ofFloat(this.bF, View.SCALE_Y, new float[]{1.1f, 0.9f});
            AnimatorSet animatorSet2 = new AnimatorSet();
            animatorSet2.playTogether(new Animator[]{ofFloat, ofFloat2});
            animatorSet2.setDuration(50);
            ofFloat = ObjectAnimator.ofFloat(this.bF, View.SCALE_X, new float[]{0.9f, 1.0f});
            ofFloat2 = ObjectAnimator.ofFloat(this.bF, View.SCALE_Y, new float[]{0.9f, 1.0f});
            AnimatorSet animatorSet3 = new AnimatorSet();
            animatorSet3.playTogether(new Animator[]{ofFloat, ofFloat2});
            animatorSet3.setDuration(50);
            AnimatorSet animatorSet4 = new AnimatorSet();
            animatorSet4.playSequentially(new Animator[]{animatorSet, animatorSet2, animatorSet3});
            animatorSet4.start();
        }
        if (this.A.isShowing()) {
            this.bR.setBackgroundResource(R.drawable.live_gift_sending_mask);
        }
        this.bG.setPercent(1.0f);
        this.bH = new dq(this, 3000, 50);
        this.bH.start();
    }

    private void az() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.bE, View.SCALE_X, new float[]{1.0f, 0.0f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.bE, View.SCALE_Y, new float[]{1.0f, 0.0f});
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.bE, View.TRANSLATION_Y, new float[]{0.0f, (float) WindowUtils.dp2Px(64)});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2, ofFloat3});
        animatorSet.setDuration(160);
        ofFloat = ObjectAnimator.ofFloat(this.bF, View.SCALE_X, new float[]{1.0f, 0.0f});
        ofFloat2 = ObjectAnimator.ofFloat(this.bF, View.SCALE_Y, new float[]{1.0f, 0.0f});
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet2.setDuration(160);
        AnimatorSet animatorSet3 = new AnimatorSet();
        animatorSet3.playSequentially(new Animator[]{animatorSet, animatorSet2});
        animatorSet3.addListener(new dr(this));
        animatorSet3.start();
    }

    private void aA() {
        if (this.bH != null) {
            this.bH.cancel();
            this.bH = null;
        }
    }

    protected void n() {
        if (this.d != null) {
            a(this.d.author);
        }
    }

    protected void a(Boolean bool) {
        if (!u()) {
            if (bool == null) {
                toCloseLive();
            } else if (getLiveUser() != null && !getLiveUser().is_follow && this.bL.getDelta() > ListViewHelper.DEFAULT_TIPS_TO_REFRESH_INTERVAL) {
                r0 = new ds(this, R.style.SimpleDialog);
                r0.message(getString(R.string.live_exit_hint2)).positiveAction(getString(R.string.exit_confirm)).negativeAction(getString(R.string.exit_cancel));
                AppUtils.showDialogFragment(this, r0);
            } else if ((this.bv != null || A()) && this.az && !bool.booleanValue()) {
                r0 = new dt(this, R.style.SimpleDialog);
                r0.message(getString(R.string.live_exit_hint)).positiveAction(getString(R.string.setting_confirm)).negativeAction(getString(R.string.setting_cancel));
                AppUtils.showDialogFragment(this, r0);
            } else {
                toCloseLive();
            }
        }
    }

    public void toCloseLive() {
        z();
        aB();
        V();
        doConfirm();
    }

    protected void Q() {
        if (this.d != null && this.d.author != null && this.d.author.isMe()) {
            sendLiveMessageAndRefreshUI(LiveMessage.createCloseMessage(this.ax.getOriginId()));
        }
    }

    protected void F() {
        f();
        ad();
        this.z.setVisibility(4);
    }

    protected void f() {
        this.A.setVisibility(0);
        WindowUtils.setNonTransparentNavigationBar(this);
    }

    protected void r() {
        super.r();
    }

    public User getLiveUser() {
        return this.d != null ? this.d.author : null;
    }

    protected void B() {
        if (!forwardIfNotLogin() && !u() && this.az) {
            q();
        }
    }

    protected boolean u() {
        if (!this.A.isShowing()) {
            return super.u();
        }
        this.bR.setBackgroundResource(0);
        ax();
        ac();
        this.y.setVisibility(0);
        this.z.setVisibility(0);
        return true;
    }

    protected void ag() {
        super.ag();
        J();
    }

    protected void I() {
        an();
        aB();
        e(true);
    }

    protected void an() {
        if (this.e == null) {
            this.e = new LiveRoom();
        }
        if (this.e.room_status == null) {
            this.e.room_status = new LiveRoomStatus();
        }
        this.e.room_status.status = 0;
    }

    public void doConfirm() {
        doConfirm(true);
    }

    public void doConfirm(boolean z) {
        if (!isFinishing()) {
            Intent intent = new Intent();
            intent.putExtra("liveStreamId", this.aA);
            String str = "liveStatus";
            int i = (this.e == null || this.e.room_status == null) ? -1 : this.e.room_status.status;
            intent.putExtra(str, i);
            User liveUser = getLiveUser();
            if (liveUser != null) {
                intent.putExtra("isFollowLiveUser", liveUser.is_follow);
            }
            intent.putExtra("liveOnlineUserCount", this.aO);
            setResult(-1, intent);
            intent.setAction(Constants.EXIT_LIVE);
            sendBroadcast(intent);
            if (this.bQ != null && this.bQ.isShowing()) {
                this.bQ.dismiss();
            }
            if (!TextUtils.isEmpty(this.bu) && z) {
                AppUtils.getInstance().getUserInfoProvider().toConversation(this, "live", this.bu);
            }
            finish();
            if (isTaskRoot()) {
                AppUtils.getInstance().getUserInfoProvider().toMain(this);
            }
        }
    }

    public void onDestroy() {
        aB();
        ConfigInfoUtil.instance().setUpdateConfigCallback(null);
        if (!(this.bN || ((this.bO <= 0 && this.bP <= 0) || this.d == null || this.ax == null))) {
            statEvent("live_cannot_pull", Long.toString(this.ax.getOriginId()), this.d.getLiveUrl(), Integer.toString(this.bO), Integer.toString(this.bP));
        }
        if (!(!this.aK || this.d == null || this.ax == null)) {
            statEventDuration("live_pull_break_point_count", Long.toString(this.ax.getOriginId()), this.bL.getDelta(), Integer.toString(this.bM), this.d.getLiveUrl(), "");
        }
        this.mHandler.removeCallbacks(this.bY);
        this.A.releaseResource();
        aA();
        try {
            unregisterReceiver(this.bV);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    public void onCallOffHook() {
        if (this.bv != null) {
            this.bv.setVolumeRate(0.0d);
        }
    }

    public void onCallIdle() {
        if (this.bv != null) {
            this.bv.setVolumeRate(1.0d);
        }
    }

    private void aB() {
        h(true);
    }

    private void h(boolean z) {
        if (z) {
            this.az = false;
            AppUtils.getInstance().setLiving(false);
        }
        if (this.bv != null) {
            this.bv.stop();
            this.bv.release();
            this.bv = null;
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1001) {
            if (AppUtils.getInstance().getUserInfoProvider().isLogin()) {
                this.ax = AppUtils.getInstance().getUserInfoProvider().getUser();
                this.aF = "";
                c(false);
                M();
                aC();
                return;
            }
            a(Boolean.valueOf(true));
        } else if (i == 103 && i2 == -1) {
            this.I.setVisibility(8);
            this.A.hideFirstCharge();
            this.A.initGiftView();
        }
    }

    private void aC() {
        NetRequest.getInstance().get(String.format(UrlConstants.USER_QUERY, new Object[0]), new du(this), "user_query", true);
    }

    public void onEmptyClick(View view) {
        this.bC.setVisibility(8);
        if (this.d == null) {
            ao();
        }
    }

    public void onGiftItemClick(GiftData giftData) {
        this.bD = giftData;
        this.bR.setVisibility(8);
        if (giftData.s == 1) {
            G();
        }
    }

    protected void a(LiveGiftMessage liveGiftMessage) {
        if (liveGiftMessage.getComboCount() >= 10 && this.A.isShowing() && !Z() && liveGiftMessage.getUserId() == AppUtils.getInstance().getUserInfoProvider().getUserId() && liveGiftMessage.getOrigin() == AppUtils.getInstance().getUserInfoProvider().getUserOrigin()) {
            this.A.setVisibility(8);
            this.bR.setBackgroundResource(0);
        }
    }

    protected void x() {
        J();
    }

    protected void onStop() {
        super.onStop();
        postDelayed(this.bW, RefreshTipView.SECOND_REFRESH_INTERVAL);
    }

    protected void onStart() {
        super.onStart();
        removeDelayed(this.bW);
    }
}
