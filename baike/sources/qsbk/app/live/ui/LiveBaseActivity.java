package qsbk.app.live.ui;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.util.Pair;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.text.Html;
import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.baidu.mobstat.Config;
import com.facebook.drawee.drawable.ScalingUtils.ScaleType;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imageutils.JfifUtil;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.qiushibaike.statsdk.StatSDK;
import com.qq.e.comm.constants.ErrorCode$OtherError;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.sina.weibo.sdk.statistic.i;
import com.xiaomi.mipush.sdk.Constants;
import io.agora.rtc.PublisherConfiguration;
import io.agora.rtc.RtcEngine;
import io.agora.rtc.RtcEngineEx;
import io.agora.rtc.video.VideoCanvas;
import io.agora.rtc.video.VideoCompositingLayout;
import io.agora.rtc.video.VideoCompositingLayout.Region;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.LinkedBlockingDeque;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONObject;
import qsbk.app.activity.publish.Publish_Image;
import qsbk.app.cafe.plugin.PayUniversalPlugin;
import qsbk.app.core.model.Activity;
import qsbk.app.core.model.BarrageData;
import qsbk.app.core.model.CommonVideo;
import qsbk.app.core.model.CustomButton;
import qsbk.app.core.model.GiftData;
import qsbk.app.core.model.RedEnvelopesConfig;
import qsbk.app.core.model.User;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.AudioManagerUtils;
import qsbk.app.core.utils.ConfigInfoUtil;
import qsbk.app.core.utils.DeviceUtils;
import qsbk.app.core.utils.KeyBoardUtils;
import qsbk.app.core.utils.LogUtils;
import qsbk.app.core.utils.NetworkUtils;
import qsbk.app.core.utils.PictureGetHelper;
import qsbk.app.core.utils.PreferenceUtils;
import qsbk.app.core.utils.TextLengthFilter;
import qsbk.app.core.utils.TimeDelta;
import qsbk.app.core.utils.TimeUtils;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.core.web.NativeJsPluginManager;
import qsbk.app.core.web.js.ExposeApi;
import qsbk.app.core.web.plugin.Plugin;
import qsbk.app.core.web.ui.FullScreenWebActivity;
import qsbk.app.core.web.ui.QsbkWebView;
import qsbk.app.core.web.ui.QsbkWebViewClient;
import qsbk.app.core.web.ui.WebActivity;
import qsbk.app.core.web.ui.WebInterface;
import qsbk.app.core.widget.CommonEditText;
import qsbk.app.core.widget.FrameAnimationView;
import qsbk.app.core.widget.SimpleDialog.Builder;
import qsbk.app.core.widget.VolumeControllerView;
import qsbk.app.live.LivePullLauncher;
import qsbk.app.live.R;
import qsbk.app.live.adapter.BarrageListAdapter;
import qsbk.app.live.adapter.GiftRankRichAdapter;
import qsbk.app.live.adapter.LiveMessageAdapter;
import qsbk.app.live.model.GiftRankData;
import qsbk.app.live.model.GiftRankRichData;
import qsbk.app.live.model.LiveActivityMessage;
import qsbk.app.live.model.LiveBalanceMessage;
import qsbk.app.live.model.LiveBarrageMessage;
import qsbk.app.live.model.LiveBeautyMessageContent;
import qsbk.app.live.model.LiveCommentMsg;
import qsbk.app.live.model.LiveCommonMessage;
import qsbk.app.live.model.LiveCommonMessageContent;
import qsbk.app.live.model.LiveDataMessageContent;
import qsbk.app.live.model.LiveDollActionMessage;
import qsbk.app.live.model.LiveDollMessage;
import qsbk.app.live.model.LiveEnterMessage;
import qsbk.app.live.model.LiveFreeGiftAvailableMessage;
import qsbk.app.live.model.LiveFreeGiftGetMessage;
import qsbk.app.live.model.LiveFreeGiftStartMessage;
import qsbk.app.live.model.LiveGameBetMessage;
import qsbk.app.live.model.LiveGameMessage;
import qsbk.app.live.model.LiveGiftMessage;
import qsbk.app.live.model.LiveGiftWeekRankHintMessage;
import qsbk.app.live.model.LiveGiftWeekRankMessage;
import qsbk.app.live.model.LiveGiftZhouxingMessage;
import qsbk.app.live.model.LiveGlobalRedEnvelopesMessage;
import qsbk.app.live.model.LiveLoveMessage;
import qsbk.app.live.model.LiveMessage;
import qsbk.app.live.model.LiveMessageBase;
import qsbk.app.live.model.LiveMicMessage;
import qsbk.app.live.model.LiveNewerDiscountMessage;
import qsbk.app.live.model.LiveReconnectMessageContent;
import qsbk.app.live.model.LiveRedEnvelopesMessage;
import qsbk.app.live.model.LiveRichMessage;
import qsbk.app.live.model.LiveRobRedEnvelopesResultMessage;
import qsbk.app.live.model.LiveRoom;
import qsbk.app.live.model.LiveSendErrorMessageContent;
import qsbk.app.live.model.LiveSystemMessage;
import qsbk.app.live.model.LiveUser;
import qsbk.app.live.receiver.PhoneStateReceiver;
import qsbk.app.live.receiver.PhoneStateReceiver.OnPhoneStateListener;
import qsbk.app.live.ui.helper.LiveMessageListener;
import qsbk.app.live.ui.helper.StopLiveHelper;
import qsbk.app.live.ui.mic.MicAGEventHandler;
import qsbk.app.live.ui.mic.MicConnectBaseActivity;
import qsbk.app.live.ui.mic.MicConnectDialog;
import qsbk.app.live.ui.share.ShareCallbackHelper;
import qsbk.app.live.ui.share.ShareCallbackHelper.ShareCallback;
import qsbk.app.live.utils.LiveWebSocketHandler;
import qsbk.app.live.utils.ThemeUtils;
import qsbk.app.live.widget.BarrageLayout;
import qsbk.app.live.widget.CustomButtonView;
import qsbk.app.live.widget.DiscountDialog;
import qsbk.app.live.widget.DollCatchView;
import qsbk.app.live.widget.DollResultDialog;
import qsbk.app.live.widget.FamilyCreatorEnterView;
import qsbk.app.live.widget.FavorLayout;
import qsbk.app.live.widget.FollowTipsDialog;
import qsbk.app.live.widget.GameView;
import qsbk.app.live.widget.GiftBox;
import qsbk.app.live.widget.GiftLayout;
import qsbk.app.live.widget.GiftRichRecyclerView;
import qsbk.app.live.widget.GlobalBarrageLayout;
import qsbk.app.live.widget.GlobalGiftView;
import qsbk.app.live.widget.HighLevelUserEnterView;
import qsbk.app.live.widget.LargeGiftLayout;
import qsbk.app.live.widget.LevelUpView;
import qsbk.app.live.widget.LoadingBar;
import qsbk.app.live.widget.ProTopRankView;
import qsbk.app.live.widget.RedEnvelopesDialog;
import qsbk.app.live.widget.RedEnvelopesResultDialog;
import qsbk.app.live.widget.SendRedEnvelopesDialog;
import qsbk.app.live.widget.SuperUserEnterAnimLayout;
import qsbk.app.live.widget.UserCardDialog;
import qsbk.app.live.widget.UserCardDialog.OnUserCardBtnClickListener;
import qsbk.app.widget.RefreshTipView;

public abstract class LiveBaseActivity extends MicConnectBaseActivity implements OnClickListener, OnTouchListener, WebInterface, OnPhoneStateListener, LiveMessageListener, MicAGEventHandler, OnUserCardBtnClickListener {
    public static final long INNER = 172800000;
    public static final int REQUEST_CODE_PAY_ACTIVITY = 103;
    protected static final String a = LiveBaseActivity.class.getSimpleName();
    protected GiftBox A;
    protected ImageView B;
    protected CustomButtonView C;
    protected ImageView D;
    protected ImageView E;
    protected ImageView F;
    protected ImageView G;
    protected FrameLayout H;
    protected SimpleDraweeView I;
    protected FrameLayout J;
    protected View K;
    protected RecyclerView L;
    protected View M;
    protected LinearLayoutManager N;
    protected LiveMessageAdapter O;
    protected List<LiveMessage> P = new LinkedList();
    protected List<GiftRankRichData> Q = new ArrayList();
    protected LinkedBlockingDeque<LiveMessage> R = new LinkedBlockingDeque();
    protected LinkedBlockingDeque<LiveLoveMessage> S = new LinkedBlockingDeque();
    protected LinkedBlockingDeque<LiveGiftMessage> T = new LinkedBlockingDeque();
    protected TextView U;
    protected RelativeLayout V;
    protected LinearLayout W;
    protected TextView X;
    protected CommonEditText Y;
    protected Button Z;
    protected String aA;
    protected TextView aB;
    protected TextView aC;
    protected LiveWebSocketHandler aD;
    protected long aE;
    protected String aF;
    protected volatile boolean aG;
    protected int aH = 0;
    protected int aI = 0;
    protected int aJ = 0;
    protected boolean aK = false;
    protected TimeDelta aL;
    protected TimeDelta aM;
    protected TimeDelta aN;
    protected long aO = 1;
    protected long aP;
    protected long aQ;
    protected long aR;
    protected long aS;
    protected long aT;
    protected int aU;
    protected Map<String, String> aV = new HashMap();
    protected int aW = 0;
    protected boolean aX = true;
    protected volatile long aY = 0;
    protected volatile int aZ = 0;
    protected String aa;
    protected FavorLayout ab;
    protected GiftLayout ac;
    protected BarrageLayout ad;
    protected GlobalBarrageLayout ae;
    protected HighLevelUserEnterView af;
    protected LargeGiftLayout ag;
    protected VolumeControllerView ah;
    protected ProTopRankView ai;
    protected GameView aj;
    protected GameView ak;
    protected SuperUserEnterAnimLayout al;
    protected LevelUpView am;
    protected DollCatchView an;
    protected boolean ao = false;
    protected boolean ap = false;
    protected String aq = "";
    protected User ar = null;
    protected UserCardDialog as;
    protected FollowTipsDialog at;
    protected GiftRichRecyclerView au;
    protected LinearLayoutManager av;
    protected GiftRankRichAdapter aw;
    protected User ax;
    protected LiveUser ay;
    protected boolean az;
    protected int b = 0;
    private int bA = 0;
    private SurfaceView bB;
    private View bC;
    private ImageView bD;
    private MicConnectDialog bE;
    private int bF = 0;
    private FrameLayout bG;
    private FrameLayout bH;
    private FrameLayout bI;
    private ImageView bJ;
    private SimpleDraweeView bK;
    private TextView bL;
    private TextView bM;
    private String bN;
    private String bO;
    private String bP;
    private String bQ;
    private String bR;
    private BroadcastReceiver bS;
    private PhoneStateReceiver bT;
    private RelativeLayout bU;
    private FrameLayout bV;
    private ImageView bW;
    private ImageView bX;
    private SimpleDraweeView bY;
    private TextView bZ;
    protected volatile int ba = 0;
    protected Runnable bb = new s(this);
    protected String bc;
    protected int bd;
    protected KeyBoardUtils be;
    protected double bf;
    protected double bg;
    protected TextView bh;
    protected FrameLayout bi;
    protected LinearLayout bj;
    protected SimpleDraweeView bk;
    protected TextView bl;
    protected int bm;
    protected RelativeLayout bn;
    protected TextView bo;
    protected TextView bp;
    protected FrameLayout bq;
    protected FrameLayout br;
    protected Runnable bs = new cz(this);
    private boolean bt = true;
    private View bu;
    private FrameLayout bv;
    private List<QsbkWebView> bw = new ArrayList();
    private Plugin bx;
    private FamilyCreatorEnterView by;
    private FrameAnimationView bz;
    protected boolean c = false;
    private Runnable cA = new cb(this);
    private ValueAnimator cB = null;
    private Runnable cC = new ci(this);
    private Runnable cD = new cu(this);
    private int cE;
    private int cF;
    private int cG;
    private int cH;
    private int cI;
    private int cJ;
    private int cK = WindowUtils.getScreenWidth();
    private int cL = WindowUtils.getScreenHeight();
    private int cM = WindowUtils.dp2Px(154);
    private int cN = WindowUtils.dp2Px(20);
    private int cO = WindowUtils.dp2Px(193);
    private boolean cP = false;
    private boolean cQ = true;
    private Runnable cR = new dg(this);
    private Runnable cS = new di(this);
    private TextView ca;
    private volatile boolean cb = false;
    private volatile boolean cc = false;
    private Map<Long, Boolean> cd = new HashMap();
    private Map<Long, Boolean> ce = new HashMap();
    private Map<Long, Boolean> cf = new HashMap();
    private Map<Long, Boolean> cg = new HashMap();
    private Map<String, String> ch = new HashMap();
    private boolean ci;
    private boolean cj;
    private long ck = 0;
    private ShareCallback cl = new d(this);
    private boolean cm = false;
    private CountDownTimer cn;
    private CountDownTimer co;
    private MediaPlayer cp;
    private final HashMap<Integer, SurfaceView> cq = new HashMap();
    private Runnable cr = new af(this);
    private Runnable cs = new ag(this);
    private Runnable ct = new al(this);
    private Runnable cu = new am(this);
    private Runnable cv = new bg(this);
    private Runnable cw = new bh(this);
    private Runnable cx = new bi(this);
    private Runnable cy = new bj(this);
    private Runnable cz = new bk(this);
    protected CommonVideo d;
    protected LiveRoom e;
    protected View f;
    protected TextureView g;
    protected FrameLayout h;
    protected GestureDetectorCompat i;
    protected LoadingBar j;
    protected ImageView k;
    protected RelativeLayout l;
    protected GlobalGiftView m;
    public Dialog mDialog;
    public double mDistance = -1.0d;
    protected LinearLayout n;
    protected ImageView o;
    protected TextView p;
    protected ViewFlipper q;
    protected TextView r;
    protected TextView s;
    protected LinearLayout t;
    protected TextView u;
    protected TextView v;
    protected FrameLayout w;
    protected LinearLayout x;
    protected LinearLayout y;
    protected LinearLayout z;

    protected abstract void B();

    protected abstract void F();

    protected abstract void J();

    protected abstract void a(Boolean bool);

    protected abstract void ae();

    public abstract User getLiveUser();

    protected abstract void n();

    protected abstract void x();

    protected void onCreate(Bundle bundle) {
        ThemeUtils.setTheme(this);
        EventBus.getDefault().register(this);
        super.onCreate(bundle);
        this.aK = System.currentTimeMillis() % 100 < ((long) ConfigInfoUtil.instance().getQbaPercent());
        Window window = getWindow();
        window.setFlags(128, 128);
        window.setBackgroundDrawableResource(R.color.transparent);
        window.getDecorView().setBackgroundColor(-16777216);
    }

    protected void initView() {
        this.be = KeyBoardUtils.build();
        this.be.setOnKeyboadHiddenChangedListener(this, new ae(this));
        this.bn = (RelativeLayout) findViewById(R.id.top_fhk_view);
        this.bo = (TextView) findViewById(R.id.fhk_num);
        this.bp = (TextView) findViewById(R.id.fhk_time);
        this.bq = (FrameLayout) findViewById(R.id.fhk_frame);
        this.br = (FrameLayout) findViewById(R.id.time_frame);
        this.bC = findViewById(R.id.doll_catching_tips);
        this.bD = (ImageView) findViewById(R.id.doll_switch_camera);
        if (this.bD != null) {
            this.bD.setOnClickListener(this);
        }
        this.bG = (FrameLayout) findViewById(R.id.surfaceViewContainer1);
        this.g = (TextureView) findViewById(R.id.textureview);
        this.h = (FrameLayout) findViewById(R.id.touch_view);
        this.h.setClickable(true);
        this.h.setOnTouchListener(this);
        this.j = (LoadingBar) findViewById(R.id.iv_connecting);
        this.k = (ImageView) findViewById(R.id.iv_background);
        this.l = (RelativeLayout) findViewById(R.id.dynamic_adjust_position_contain);
        this.m = (GlobalGiftView) findViewById(R.id.broadbast_marquee);
        this.n = (LinearLayout) findViewById(R.id.ll_live_user_info);
        this.n.setOnClickListener(this);
        this.o = (ImageView) findViewById(R.id.iv_avatar);
        this.p = (TextView) findViewById(R.id.tv_name);
        this.q = (ViewFlipper) findViewById(R.id.vf_gift_count);
        this.q.setOnClickListener(this);
        this.r = (TextView) $(R.id.tv_gift_count_total);
        this.r.setOnClickListener(this);
        this.s = (TextView) $(R.id.tv_gift_count_week);
        this.s.setOnClickListener(this);
        this.t = (LinearLayout) findViewById(R.id.ll_living);
        this.L = (RecyclerView) findViewById(R.id.recyclerview);
        this.K = findViewById(R.id.chat_placeholder);
        this.M = findViewById(R.id.live_chatroom_bg);
        this.J = (FrameLayout) findViewById(R.id.fl_scroll);
        this.u = (TextView) findViewById(R.id.tv_online_user_count);
        this.v = (TextView) findViewById(R.id.btn_follow_anchor);
        this.w = (FrameLayout) findViewById(R.id.fl_follow_anchor);
        this.w.setOnClickListener(this);
        this.U = (TextView) findViewById(R.id.tv_has_new_message);
        this.U.setOnClickListener(this);
        this.y = (LinearLayout) findViewById(R.id.ll_action);
        this.z = (LinearLayout) findViewById(R.id.ll_custom_buttons);
        this.B = (ImageView) findViewById(R.id.btn_close);
        this.B.setOnClickListener(this);
        this.C = (CustomButtonView) findViewById(R.id.btn_custom);
        this.D = (ImageView) findViewById(R.id.btn_gift);
        this.D.setOnClickListener(this);
        this.E = (ImageView) findViewById(R.id.btn_comment);
        this.E.setOnClickListener(this);
        this.F = (ImageView) findViewById(R.id.btn_share);
        this.F.setOnClickListener(this);
        this.G = (ImageView) findViewById(R.id.btn_more);
        this.G.setOnClickListener(this);
        this.I = (SimpleDraweeView) findViewById(R.id.btn_first_charge);
        this.I.setImageResource(R.drawable.live_discount_icon);
        this.I.setOnClickListener(this);
        this.V = (RelativeLayout) findViewById(R.id.ll_comment);
        this.W = (LinearLayout) findViewById(R.id.ll_barrage_switch);
        this.W.setOnClickListener(this);
        this.X = (TextView) findViewById(R.id.tv_barrage_switch);
        this.Y = (CommonEditText) findViewById(R.id.et_comment);
        this.Y.setOnEditorActionListener(new ap(this));
        this.Y.setOnKeyListener(new ba(this));
        this.Z = (Button) findViewById(R.id.btn_send);
        this.Z.setOnClickListener(this);
        this.bv = (FrameLayout) findViewById(R.id.fl_activities);
        this.ab = (FavorLayout) findViewById(R.id.fl_favor_anim);
        this.ac = (GiftLayout) findViewById(R.id.fl_gift_anim);
        this.ac.setLiveMessageListener(this);
        this.ad = (BarrageLayout) findViewById(R.id.fl_barrage_anim);
        this.ad.setLiveMessageListener(this);
        this.ae = (GlobalBarrageLayout) findViewById(R.id.fl_global_barrage_anim);
        this.ae.setLiveMessageListener(this);
        this.af = (HighLevelUserEnterView) findViewById(R.id.enter_marquee);
        this.af.setLiveMessageListener(this);
        this.by = (FamilyCreatorEnterView) findViewById(R.id.family_creator_enter_view);
        this.bz = (FrameAnimationView) findViewById(R.id.family_enter_bg);
        this.bz.getLayoutParams().height = (this.cK * 8) / 15;
        this.bz.setFramesInAssets("family_creator_enter_effect");
        this.ag = (LargeGiftLayout) findViewById(R.id.fl_large_gift_anim);
        this.ag.setLiveMessageListener(this);
        this.ah = (VolumeControllerView) findViewById(R.id.vc);
        this.aC = (TextView) findViewById(R.id.tv_nick_id);
        this.ai = (ProTopRankView) findViewById(R.id.top_three_view);
        this.ai.setLiveMessageListener(this);
        this.am = (LevelUpView) $(R.id.level_up_view);
        this.bW = (ImageView) $(R.id.iv_bg_zhouxing);
        this.bV = (FrameLayout) $(R.id.fl_zhouxing);
        this.bX = (ImageView) $(R.id.iv_zhouxing);
        this.bV.setOnClickListener(this);
        this.bY = (SimpleDraweeView) $(R.id.iv_gift_zhouxing);
        this.bZ = (TextView) $(R.id.tv_gift_zhouxing);
        this.bU = (RelativeLayout) $(R.id.rl_gift_count);
        this.ca = (TextView) $(R.id.tv_zhouxing_rank);
        this.ca.setOnClickListener(this);
        this.bi = (FrameLayout) $(R.id.fl_family_support);
        this.bh = (TextView) $(R.id.tv_family_support);
        this.au = (GiftRichRecyclerView) findViewById(R.id.live_giftrank_rich);
        this.x = (LinearLayout) findViewById(R.id.tips_follow);
        this.H = (FrameLayout) findViewById(R.id.layout_content);
        this.aB = (TextView) findViewById(R.id.tv_stat);
        this.aB.setOnLongClickListener(new bm(this));
        postDelayed(this.cR, i.MIN_UPLOAD_INTERVAL);
        AppUtils.addSupportForTransparentStatusBar(findViewById(R.id.adjust_contain));
        AppUtils.addSupportForTransparentStatusBarMargin(this.ah);
        String deviceModel = DeviceUtils.getDeviceModel();
        if (deviceModel.contains("Meitu") || deviceModel.contains("meitu")) {
            this.L.setVerticalFadingEdgeEnabled(false);
        }
        this.bj = (LinearLayout) findViewById(R.id.ll_free_gift);
        this.bk = (SimpleDraweeView) findViewById(R.id.iv_free_gift);
        this.bl = (TextView) findViewById(R.id.tv_free_gift);
    }

    protected void initData() {
        if (AppUtils.getInstance().isLiving()) {
            StopLiveHelper.setStopLive();
        }
        this.ax = an();
        if (this.ax == null) {
            this.ax = new User();
            postDelayed(this.bb, 60000);
        }
        this.i = new GestureDetectorCompat(this, new LiveBaseActivity$SingleTapGestureListener(this));
        this.N = new by(this, getActivity());
        this.N.setStackFromEnd(true);
        this.L.setLayoutManager(this.N);
        this.O = new LiveMessageAdapter(getActivity(), this.P, isAnchor());
        this.O.setOnItemClickLitener(new ck(this));
        this.L.setAdapter(this.O);
        this.L.setItemAnimator(new DefaultItemAnimator());
        this.L.setHasFixedSize(true);
        this.L.setOnTouchListener(this);
        this.L.addOnScrollListener(new cv(this));
        AppUtils.weakenRecyclerViewAnimations(this.L);
        this.av = new LinearLayoutManager(getActivity(), 0, false);
        this.au.setLayoutManager(this.av);
        this.aw = new GiftRankRichAdapter(this, this.Q);
        this.au.setAdapter(this.aw);
        this.au.setHasFixedSize(true);
        this.aw.setOnItemClickLitener(new e(this));
        this.Y.setFilters(new InputFilter[]{new TextLengthFilter(60, R.string.comment_too_long)});
        this.bS = new LiveBaseActivity$LiveReceiver(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PHONE_STATE");
        this.bT = new PhoneStateReceiver(this);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.bT, intentFilter);
        aI();
        this.aD = LiveWebSocketHandler.create();
        this.aD.attach(this, this.aV);
        this.aN = new TimeDelta();
        this.bN = getString(R.string.live_chat_room_fetch_ip_address_failed_and_retrying);
        this.bO = getString(R.string.live_chat_room_connecting);
        this.bP = getString(R.string.live_chat_room_connect_failed);
        this.bQ = getString(R.string.live_chat_room_connection_lost);
        this.bR = getString(R.string.live_chat_room_connect_success);
        this.aW = D();
        aB();
        String networkDetailType = NetworkUtils.getInstance().getNetworkDetailType();
        if (!(networkDetailType == null || networkDetailType.equals("WiFi") || !NetworkUtils.getInstance().isNetworkAvailable())) {
            ToastUtil.Short(R.string.netstate_mobile);
        }
        ConfigInfoUtil.instance().setUpdateConfigCallback(new j(this));
        ConfigInfoUtil.instance().deleteConfigAndUpdate();
    }

    public boolean forwardIfNotLogin() {
        this.mHandler.removeCallbacks(this.bb);
        boolean z = !AppUtils.getInstance().getUserInfoProvider().isLogin();
        if (z) {
            AppUtils.getInstance().getUserInfoProvider().toLogin(getActivity(), 1001);
        }
        return z;
    }

    private User an() {
        return AppUtils.getInstance().getUserInfoProvider().getUser();
    }

    protected void a() {
        LayoutParams layoutParams = (LayoutParams) this.H.getLayoutParams();
        layoutParams.bottomMargin = this.be.getNavigationHideHeight();
        this.H.setLayoutParams(layoutParams);
    }

    protected void a(int i, boolean z) {
        if (z) {
            Y();
        } else {
            this.V.setVisibility(0);
        }
        this.l.clearAnimation();
        if ((this.l.getVisibility() != 0 && z) || (this.l.getVisibility() == 0 && !z)) {
            Animation loadAnimation = AnimationUtils.loadAnimation(getActivity(), z ? R.anim.trans_fade_in : R.anim.trans_fade_out);
            loadAnimation.setAnimationListener(new k(this, z));
            this.l.startAnimation(loadAnimation);
        }
        if (this.V.getHeight() > 0) {
            int i2;
            int navigationHideHeight = i - this.be.getNavigationHideHeight();
            LayoutParams layoutParams = (LayoutParams) this.V.getLayoutParams();
            layoutParams.bottomMargin = navigationHideHeight;
            this.V.setLayoutParams(layoutParams);
            int height = ((z ? this.y.getHeight() : this.V.getHeight()) + navigationHideHeight) + this.L.getHeight();
            if (Z() && this.aj.isVisible()) {
                if (z) {
                    height += this.cO;
                } else {
                    navigationHideHeight -= this.cO;
                }
            }
            if (!aa()) {
                i2 = navigationHideHeight;
                navigationHideHeight = height;
            } else if (z) {
                i2 = navigationHideHeight;
                navigationHideHeight = height + this.cM;
            } else {
                i2 = navigationHideHeight - this.cM;
                navigationHideHeight = height;
            }
            layoutParams = (LayoutParams) this.af.getLayoutParams();
            layoutParams.bottomMargin = navigationHideHeight;
            this.af.setLayoutParams(layoutParams);
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.K.getLayoutParams();
            layoutParams2.bottomMargin = i2;
            this.K.setLayoutParams(layoutParams2);
            layoutParams = (LayoutParams) this.L.getLayoutParams();
            if (z) {
                navigationHideHeight = this.y.getHeight();
            } else {
                navigationHideHeight = this.V.getHeight();
            }
            layoutParams.bottomMargin = navigationHideHeight + i2;
            this.L.setLayoutParams(layoutParams);
            layoutParams = (LayoutParams) this.M.getLayoutParams();
            layoutParams.bottomMargin = i2;
            this.M.setLayoutParams(layoutParams);
            if (this.t.getHeight() > 0) {
                layoutParams = (LayoutParams) this.t.getLayoutParams();
                if (z) {
                    layoutParams.height = this.be.getPreviousKeyboardHeight() > 0 ? this.t.getHeight() - (this.be.getPreviousKeyboardHeight() - this.be.getNavigationHideHeight()) : this.t.getHeight();
                } else {
                    layoutParams.height = (i - this.be.getPreviousKeyboardHeight()) + this.t.getHeight();
                }
                this.t.setLayoutParams(layoutParams);
            }
            if (!AppUtils.isSupportForTransparentStatusBar()) {
                return;
            }
            if (z) {
                RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.ai.getLayoutParams();
                layoutParams3.topMargin = WindowUtils.dp2Px(8);
                this.ai.setLayoutParams(layoutParams3);
                return;
            }
            AppUtils.addSupportForTransparentStatusBarMargin(this.ai);
        }
    }

    private void ao() {
        int i;
        int height = this.L.getHeight() + this.y.getHeight();
        int dp2Px = WindowUtils.dp2Px(60);
        if (Z() && this.aj.isVisible()) {
            height += this.cO;
            dp2Px += this.cO;
        }
        if (aa()) {
            i = height + this.cM;
            height = dp2Px + this.cM;
        } else {
            i = height;
            height = dp2Px;
        }
        LayoutParams layoutParams = (LayoutParams) this.af.getLayoutParams();
        layoutParams.bottomMargin = i;
        this.af.setLayoutParams(layoutParams);
        if (this.bI != null && A()) {
            layoutParams = (LayoutParams) this.bI.getLayoutParams();
            layoutParams.bottomMargin = height;
            this.bI.setLayoutParams(layoutParams);
        }
        if (this.bH != null) {
            layoutParams = (LayoutParams) this.bH.getLayoutParams();
            layoutParams.bottomMargin = height;
            this.bH.setLayoutParams(layoutParams);
        }
    }

    protected void b() {
        this.y.setVisibility(0);
    }

    protected void c() {
        if (!isFinishing()) {
            if (this.cp != null) {
                this.cp.release();
                this.cp = null;
            }
            this.mHandler.removeCallbacks(this.bb);
            if (this.bE != null && this.bE.isShowing()) {
                this.bE.dismiss();
            }
            this.j.releaseResource();
            Y();
            aA();
            this.g.setVisibility(8);
            this.y.setVisibility(8);
            this.z.setVisibility(8);
            this.V.setVisibility(4);
            this.L.setVisibility(8);
            this.t.setVisibility(8);
            this.q.setVisibility(8);
            this.ab.setVisibility(8);
            this.ac.setVisibility(8);
            this.af.setVisibility(8);
            this.by.setVisibility(8);
            this.bz.setVisibility(8);
            this.bn.setVisibility(8);
            this.aC.setVisibility(8);
            this.bj.setVisibility(8);
            if (this.al != null) {
                this.al.setVisibility(8);
            }
            this.ag.setVisibility(8);
            this.U.setVisibility(8);
            this.aB.setVisibility(8);
            this.x.setVisibility(8);
            this.au.setVisibility(8);
            this.n.setVisibility(8);
            this.k.setVisibility(8);
            c(8);
            this.bV.setVisibility(8);
            this.ca.setVisibility(8);
            this.bi.setVisibility(8);
            if (Z()) {
                this.aj.setVisibility(8);
                WindowUtils.setTransparentNavigationBar(this);
            }
            j();
            if (l()) {
                this.d.live_type = 0;
            }
            if (this.f != null) {
                this.f.setVisibility(8);
            }
            getActivity().getWindow().clearFlags(128);
        }
    }

    protected void d() {
        this.t.setVisibility(0);
        this.u.setVisibility(0);
        this.q.setVisibility(0);
        if (!this.ao) {
            a(false);
            ap();
        }
        b();
        KeyBoardUtils.hideSoftInput(getActivity());
    }

    protected void a(boolean z) {
        ViewStub viewStub;
        LayoutParams layoutParams;
        if (this.d.getGameId() == 6) {
            if (z && !Z() && GameView.isSupportedGame(this.d.getGameId())) {
                viewStub = (ViewStub) findViewById(R.id.game_guess_view_stub);
                if (viewStub != null) {
                    this.aj = (GameView) viewStub.inflate();
                    this.aj.setGameId(this.d.getGameId());
                    layoutParams = (LayoutParams) this.J.getLayoutParams();
                    layoutParams.bottomMargin = this.cO;
                    this.J.setLayoutParams(layoutParams);
                    layoutParams = (LayoutParams) this.af.getLayoutParams();
                    layoutParams.bottomMargin += this.cO;
                    this.af.setLayoutParams(layoutParams);
                    this.ad.removeBarrageLine(0);
                    this.ae.removeBarrageLine(0);
                    WindowUtils.setNonTransparentNavigationBar(this);
                } else if (this.d.getGameId() == 6 && this.ak != null) {
                    this.aj = this.ak;
                    this.aj.setGameId(this.d.getGameId());
                    layoutParams = (LayoutParams) this.J.getLayoutParams();
                    layoutParams.bottomMargin = this.cO;
                    this.J.setLayoutParams(layoutParams);
                    layoutParams = (LayoutParams) this.af.getLayoutParams();
                    layoutParams.bottomMargin += this.cO;
                    this.af.setLayoutParams(layoutParams);
                    this.ad.removeBarrageLine(0);
                    this.ae.removeBarrageLine(0);
                    WindowUtils.setNonTransparentNavigationBar(this);
                }
            }
        } else if (!Z() && GameView.isSupportedGame(this.d.getGameId())) {
            viewStub = null;
            if (this.d.getGameId() == 1) {
                viewStub = (ViewStub) findViewById(R.id.game_hlnb_view_stub);
            } else if (this.d.getGameId() == 2) {
                viewStub = (ViewStub) findViewById(R.id.game_ypdx_view_stub);
            } else if (this.d.getGameId() == 3) {
                viewStub = (ViewStub) findViewById(R.id.game_catanddog_view_stub);
            } else if (this.d.getGameId() == 4) {
                viewStub = (ViewStub) findViewById(R.id.game_fanfanle_view_stub);
            } else if (this.d.getGameId() == 5) {
                viewStub = (ViewStub) findViewById(R.id.game_rolltable_view_stub);
            } else if (this.d.getGameId() == 6) {
                viewStub = (ViewStub) findViewById(R.id.game_guess_view_stub);
            }
            if (viewStub != null) {
                this.aj = (GameView) viewStub.inflate();
                this.aj.setGameId(this.d.getGameId());
                layoutParams = (LayoutParams) this.J.getLayoutParams();
                layoutParams.bottomMargin = this.cO;
                this.J.setLayoutParams(layoutParams);
                layoutParams = (LayoutParams) this.af.getLayoutParams();
                layoutParams.bottomMargin += this.cO;
                this.af.setLayoutParams(layoutParams);
                this.ad.removeBarrageLine(0);
                this.ae.removeBarrageLine(0);
                WindowUtils.setNonTransparentNavigationBar(this);
            }
        }
    }

    private void ap() {
        if (!aa() && this.d.isDollRoom()) {
            ViewStub viewStub = (ViewStub) findViewById(R.id.doll_view_stub);
            if (viewStub != null) {
                this.an = (DollCatchView) viewStub.inflate();
                LayoutParams layoutParams = (LayoutParams) this.J.getLayoutParams();
                layoutParams.bottomMargin = this.cM;
                this.J.setLayoutParams(layoutParams);
                layoutParams = (LayoutParams) this.af.getLayoutParams();
                layoutParams.bottomMargin += this.cM;
                this.af.setLayoutParams(layoutParams);
                this.ad.removeBarrageLine(0);
                this.ae.removeBarrageLine(0);
                WindowUtils.setNonTransparentNavigationBar(this);
                layoutParams = (LayoutParams) this.g.getLayoutParams();
                layoutParams.bottomMargin = this.cM;
                this.g.setLayoutParams(layoutParams);
                this.bG.setLayoutParams(layoutParams);
            }
            this.cp = MediaPlayer.create(this, R.raw.doll_bg_music);
            if (this.cp != null) {
                this.cp.setLooping(true);
                this.cp.start();
            }
        }
    }

    protected void e() {
        if (aq() && !this.ao) {
            for (Activity activity : this.e.activities) {
                if (activity.isValid()) {
                    ViewGroup.LayoutParams frameLayoutParams = activity.getFrameLayoutParams(this.cK, this.cL);
                    if (activity.isWebActivity()) {
                        QsbkWebView qsbkWebView = new QsbkWebView(this, NativeJsPluginManager.getInstance().getPluginMap());
                        qsbkWebView.setLayoutParams(frameLayoutParams);
                        qsbkWebView.setBackgroundColor(0);
                        qsbkWebView.setLayerType(1, null);
                        ExposeApi exposeApi = qsbkWebView.getExposeApi();
                        qsbkWebView.setWebViewClient(new QsbkWebViewClient());
                        qsbkWebView.setWebChromeClient(new l(this, exposeApi));
                        new m(this, activity, qsbkWebView).execute(new Void[0]);
                    } else {
                        View simpleDraweeView = new SimpleDraweeView(getActivity());
                        simpleDraweeView.setLayoutParams(frameLayoutParams);
                        if (simpleDraweeView.getHierarchy() != null) {
                            ((GenericDraweeHierarchy) simpleDraweeView.getHierarchy()).setActualImageScaleType(ScaleType.FIT_CENTER);
                        }
                        simpleDraweeView.setOnClickListener(new n(this, activity));
                        AppUtils.getInstance().getImageProvider().loadGift(simpleDraweeView, activity.pic_url, false);
                        this.bv.addView(simpleDraweeView);
                    }
                }
            }
        }
    }

    protected void f() {
    }

    private boolean aq() {
        return this.e != null && this.e.isActivitiesValid();
    }

    protected void g() {
        this.j.show(1000);
    }

    protected void h() {
        this.j.show(0);
    }

    protected void i() {
        this.j.hide();
    }

    protected void j() {
        if (this.d != null && !l()) {
            String thumbUrl = this.d.getThumbUrl();
            if (TextUtils.isEmpty(thumbUrl)) {
                thumbUrl = this.d.getAuthorAvatar();
            }
            AppUtils.getInstance().getImageProvider().loadImage(this.k, thumbUrl);
            this.k.setVisibility(0);
        }
    }

    protected void k() {
        this.aX = false;
        if (this.k.getVisibility() == 0 && !l()) {
            Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(200);
            alphaAnimation.setAnimationListener(new o(this));
            this.k.startAnimation(alphaAnimation);
        }
    }

    protected boolean l() {
        return this.d != null && this.d.isPCLive();
    }

    public void onClick(View view) {
        boolean z = true;
        long id = (long) view.getId();
        if (id == ((long) R.id.ll_live_user_info)) {
            n();
        } else if (id == ((long) R.id.btn_close)) {
            a(Boolean.valueOf(true));
        } else if (id == ((long) R.id.btn_comment)) {
            o();
        } else if (id == ((long) R.id.btn_share)) {
            p();
        } else if (id == ((long) R.id.btn_send)) {
            r();
        } else if (id == ((long) R.id.tv_has_new_message)) {
            this.U.setVisibility(8);
            this.bt = true;
            aE();
        } else if (id == ((long) R.id.ll_barrage_switch)) {
            az();
        } else if (id == ((long) R.id.fl_follow_anchor)) {
            ay();
        } else if (id == ((long) R.id.btn_first_charge)) {
            toPayActivity();
        } else if (id == ((long) R.id.doll_switch_camera)) {
            if (this.bA == PayUniversalPlugin.REQUEST_PAY && this.cq.containsKey(Integer.valueOf(4321))) {
                this.bA = 4321;
            } else if (this.bA == 4321 && this.cq.containsKey(Integer.valueOf(PayUniversalPlugin.REQUEST_PAY))) {
                this.bA = PayUniversalPlugin.REQUEST_PAY;
            }
            if (au() != null) {
                au().setupRemoteVideo(new VideoCanvas(this.bB, 1, this.bA));
                if (aa()) {
                    DollCatchView dollCatchView = this.an;
                    if (this.bA != PayUniversalPlugin.REQUEST_PAY) {
                        z = false;
                    }
                    dollCatchView.setMajorView(z);
                    return;
                }
                return;
            }
            this.bD.setVisibility(8);
        }
    }

    protected void m() {
        User liveUser = getLiveUser();
        if (liveUser != null) {
            Intent intent = new Intent();
            intent.putExtra(ParamKey.PAGE, 1);
            Serializable giftRankData = new GiftRankData();
            giftRankData.p = liveUser.getPlatformId();
            giftRankData.n = liveUser.name;
            giftRankData.l = liveUser.level;
            giftRankData.fl = liveUser.family_level;
            giftRankData.d = liveUser.badge;
            giftRankData.c = this.aT;
            giftRankData.a = liveUser.headurl;
            giftRankData.s = liveUser.getOrigin();
            giftRankData.r = this.aU;
            intent.putExtra("anchor", giftRankData);
            intent.setClassName(this, "qsbk.app.live.ui.LiveRankActivity");
            startActivity(intent);
        }
    }

    private void b(View view) {
        if (!forwardIfNotLogin()) {
            disShowBottomFollowTipsDialog();
            CustomButtonView customButtonView = (CustomButtonView) view;
            if (customButtonView != null) {
                CustomButton bundle = customButtonView.getBundle();
                if (bundle == null) {
                    return;
                }
                if (CustomButton.EVENT_TYPE_LOVE.equals(bundle.event_type)) {
                    q();
                } else if (CustomButton.EVENT_TYPE_GIFT.equals(bundle.event_type)) {
                    if (this.d.author != null && bundle.event_param != null && bundle.event_param.gift_id > 0) {
                        sendLiveMessageAndRefreshUI(LiveMessage.createGiftMessage(this.ax.getOriginId(), bundle.event_param.gift_id, this.d.author.getOrigin(), this.d.author.getOriginId()));
                    }
                } else if ("web".equals(bundle.event_type)) {
                    if (bundle.event_param != null && !TextUtils.isEmpty(bundle.event_param.url)) {
                        WebActivity.launch(getActivity(), bundle.event_param.url);
                    }
                } else if ("charge".equals(bundle.event_type)) {
                    toPayActivity();
                } else if ("user".equals(bundle.event_type)) {
                    if (bundle.event_param != null && bundle.event_param.source > 0 && bundle.event_param.source_id > 0 && bundle.event_param.platform_id > 0) {
                        User user = new User();
                        user.origin_id = bundle.event_param.source_id;
                        user.origin = bundle.event_param.source;
                        user.platform_id = bundle.event_param.platform_id;
                        AppUtils.getInstance().getUserInfoProvider().toUserPage(getActivity(), user);
                    }
                } else if (CustomButton.EVENT_TYPE_INTERFACE.equals(bundle.event_type) && bundle.event_param != null && !TextUtils.isEmpty(bundle.event_param.url) && !TextUtils.isEmpty(bundle.event_param.method)) {
                    int i = "GET".equalsIgnoreCase(bundle.event_param.method) ? 0 : CustomButton.REQUEST_METHOD_POST.equalsIgnoreCase(bundle.event_param.method) ? 1 : -1;
                    if (i != -1) {
                        NetRequest.getInstance().executeNetworkInvoke(bundle.event_param.url, i, new p(this, bundle), "live_custom_button_interface", false);
                    }
                }
            }
        }
    }

    private void ax() {
        this.z.removeAllViews();
        this.C.setVisibility(8);
        if (this.e != null) {
            List list = this.e.buttons;
            if (list != null && list.size() > 0) {
                int dp2Px = WindowUtils.dp2Px(40);
                int dp2Px2 = WindowUtils.dp2Px(8);
                CustomButtonView customButtonView = null;
                int i = 0;
                while (i < list.size()) {
                    CustomButtonView customButtonView2;
                    CustomButton customButton = (CustomButton) list.get(i);
                    if (customButton != null) {
                        CustomButtonView customButtonView3;
                        Pair ratio = customButton.getRatio(dp2Px);
                        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(((Integer) ratio.first).intValue(), ((Integer) ratio.second).intValue());
                        if (CustomButton.POSITION_BOTTOM.equals(customButton.position)) {
                            customButtonView3 = this.C;
                            customButtonView3.setLayoutParams(layoutParams);
                        } else if (CustomButton.POSITION_RIGHT.equals(customButton.position)) {
                            if (this.z.getVisibility() != 0) {
                                this.z.setVisibility(0);
                            }
                            customButtonView3 = new CustomButtonView(this);
                            layoutParams.topMargin = dp2Px2;
                            this.z.addView(customButtonView3, layoutParams);
                        } else {
                            customButtonView3 = customButtonView;
                        }
                        if (customButtonView3 != null) {
                            customButtonView3.show(customButton);
                            customButtonView3.setOnClickListener(new q(this));
                        }
                        customButtonView2 = customButtonView3;
                    } else {
                        customButtonView2 = customButtonView;
                    }
                    i++;
                    customButtonView = customButtonView2;
                }
            }
        }
    }

    private void ay() {
        doFollowAnchor(false);
    }

    public void doFollowAnchor(boolean z) {
        if (!forwardIfNotLogin()) {
            this.x.setVisibility(8);
            this.v.setVisibility(8);
            User liveUser = getLiveUser();
            if (liveUser != null) {
                NetRequest.getInstance().post(UrlConstants.USER_FOLLOW_NEW, new r(this, liveUser, z), "follow", true);
            }
        }
    }

    private void az() {
        String aC = aC();
        if (g(aC)) {
            aC = "off";
        } else {
            List barrageList = ConfigInfoUtil.instance().getBarrageList();
            if (barrageList.size() == 1) {
                aC = ((BarrageData) barrageList.get(0)).t;
            } else if (barrageList.size() > 1) {
                a(barrageList);
            } else {
                aC = "on";
            }
        }
        h(aC);
        PreferenceUtils.instance().putString("live_barrage_switch", aC);
    }

    private void a(List<BarrageData> list) {
        if (this.bu == null) {
            this.bu = LayoutInflater.from(this).inflate(R.layout.live_barrage_list, null);
            RecyclerView recyclerView = (RecyclerView) this.bu.findViewById(R.id.barrage_list);
            Adapter barrageListAdapter = new BarrageListAdapter(this, list);
            recyclerView.setAdapter(barrageListAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            barrageListAdapter.setBarrageItemClickListener(new t(this, list));
            int dp2Px = WindowUtils.dp2Px((list.size() * 36) + 18);
            recyclerView.getLayoutParams().height = dp2Px;
            int[] iArr = new int[2];
            this.W.getLocationOnScreen(iArr);
            ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
            layoutParams.leftMargin = iArr[0] + WindowUtils.dp2Px(5);
            layoutParams.topMargin = (iArr[1] - dp2Px) - WindowUtils.dp2Px(5);
            this.bu.setLayoutParams(layoutParams);
            this.H.addView(this.bu);
        }
    }

    private void aA() {
        if (this.bu != null) {
            this.H.removeView(this.bu);
            this.bu = null;
        }
    }

    private void aB() {
        if (!isBarrageSwitchOpen() || isAnchor()) {
            this.W.setVisibility(8);
        } else {
            h(aC());
        }
    }

    private String aC() {
        return PreferenceUtils.instance().getString("live_barrage_switch", "off");
    }

    private boolean aD() {
        return g(aC()) && isBarrageSwitchOpen();
    }

    private boolean g(String str) {
        return !"off".equals(str);
    }

    public boolean isBarrageSwitchOpen() {
        List barrageList = ConfigInfoUtil.instance().getBarrageList();
        return (barrageList == null || barrageList.isEmpty()) ? false : true;
    }

    private void h(String str) {
        boolean g = g(str);
        this.X.setSelected(g);
        if (g) {
            this.X.setPadding(WindowUtils.dp2Px(6), 0, 0, 0);
            Map barrageMap = ConfigInfoUtil.instance().getBarrageMap();
            if (barrageMap == null || !barrageMap.containsKey(str)) {
                this.Y.setHint(R.string.live_barrage_hint);
                return;
            } else {
                this.Y.setHint(((BarrageData) barrageMap.get(str)).h);
                return;
            }
        }
        this.X.setPadding(0, 0, WindowUtils.dp2Px(6), 0);
        this.Y.setHint(R.string.comment_hint);
    }

    private void aE() {
        if (isMessageOverloadOrLowDevice()) {
            postDelayed(new u(this), 1000);
        } else {
            this.L.smoothScrollToPosition(this.O.getItemCount() - 1);
        }
    }

    public void onBackPressed() {
        a(Boolean.valueOf(false));
    }

    protected void o() {
        this.J.scrollTo(0, 0);
        X();
    }

    protected void p() {
        AppUtils.getInstance().getUserInfoProvider().toShare(getActivity(), this.d);
        statEvent("share_live", this.d.stream_id, "", "button", this.ax.getOrigin() + "_" + this.ax.getOriginId());
    }

    protected void a(String str) {
        sendLiveMessageAndRefreshUI(LiveMessage.createShareMessage(this.ax.getOriginId(), str));
    }

    protected void q() {
        sendLiveMessageAndRefreshUI(LiveMessage.createLoveMessage(this.ax.getOriginId(), this.aW));
    }

    protected void r() {
        String replaceAll = this.Y.getText().toString().trim().replaceAll("\n", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        if (TextUtils.isEmpty(replaceAll)) {
            a(R.string.empty_input);
        } else if ("@@ping".equals(replaceAll)) {
            Intent intent = new Intent(this, NetworkDiagnosisActivity.class);
            intent.putExtra("url", this.d.getLiveUrl());
            startActivity(intent);
        } else {
            Object obj;
            int i;
            if (!aD() || isAnchor()) {
                sendLiveMessageAndRefreshUI(LiveMessage.createCommentMessage(this.ax.getOriginId(), replaceAll));
                i = 1;
            } else {
                String aC = aC();
                long barragePrice = ConfigInfoUtil.instance().getBarragePrice(aC);
                if (a(barragePrice)) {
                    s();
                    obj = null;
                } else {
                    sendLiveMessageAndRefreshUI(LiveMessage.createBarrageMessage(this.ax.getOriginId(), replaceAll, aC, barragePrice));
                    if ("b2".equals(aC)) {
                        obj = null;
                    }
                    i = 1;
                }
            }
            if (obj != null) {
                this.aa = "";
                this.Y.setText("");
            }
        }
    }

    protected boolean a(long j) {
        return AppUtils.getInstance().getUserInfoProvider().getBalance() < j;
    }

    protected void s() {
        Builder vVar = new v(this, R.style.SimpleDialog);
        vVar.message(getString(R.string.live_balance_not_sufficient_hint)).positiveAction(getString(R.string.live_charge)).negativeAction(getString(R.string.setting_cancel));
        AppUtils.showDialogFragment(this, vVar);
    }

    public void toPayActivity() {
        AppUtils.getInstance().getUserInfoProvider().toPay(getActivity(), 103);
    }

    protected boolean isNeedImmersiveStatusBar() {
        return true;
    }

    protected boolean isNeedImmersiveNavigationBar() {
        return true;
    }

    protected void t() {
        User liveUser = getLiveUser();
        if (liveUser != null && this.ax != null) {
            AppUtils.getInstance().getUserInfoProvider().toUserGiftRank(this, liveUser, isAnchor(), this.aP);
        }
    }

    protected boolean u() {
        if (this.bu != null) {
            aA();
        } else if (v()) {
            Y();
        } else if (this.y.getVisibility() == 0) {
            return false;
        } else {
            this.y.setVisibility(0);
            this.z.setVisibility(0);
            return false;
        }
        return true;
    }

    protected void a(User user) {
        if (!forwardIfNotLogin()) {
            disShowBottomFollowTipsDialog();
            if (user != null && user.getOriginId() > 0) {
                if (v()) {
                    if (this.aa == null || this.Y.getText().toString().trim().equals(this.aa.trim())) {
                        onAtTa(user.name);
                    }
                } else if (!u()) {
                    this.as = new UserCardDialog(this, getLiveUser(), this.ax, user, this.aE);
                    this.as.setOnUserCardBtnClickListener(this);
                    this.as.setCanceledOnTouchOutside(true);
                    if (!isFinishing() && this.isOnResume) {
                        this.as.show();
                    }
                    WindowUtils.setNonTransparentNavigationBar(this);
                    this.as.setOnDismissListener(new w(this));
                }
            }
        }
    }

    public void setTransparentNavigationBarIfNeed() {
        if (!aa()) {
            if (this.A != null && this.A.isShowing()) {
                return;
            }
            if (!Z() || !this.aj.isVisible()) {
                WindowUtils.setTransparentNavigationBar(this);
            }
        }
    }

    protected boolean v() {
        return this.V.getVisibility() == 0;
    }

    protected boolean w() {
        return this.as != null && this.as.isShowing();
    }

    public void onAtTa(String str) {
        if (TextUtils.isEmpty(str)) {
            this.aa = "";
            return;
        }
        this.aa = "@" + str.trim() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        postDelayed(new x(this), 100);
    }

    public void onFirstRemoteVideoDecoded(int i, int i2, int i3, int i4) {
        runOnUiThread(new y(this, i, i2, i3, i4));
    }

    public void onJoinChannelSuccess(String str, int i, int i2) {
        runOnUiThread(new aa(this, i, str, i2));
    }

    public void onUserJoined(int i, int i2) {
        LogUtils.d("MicConnect", "onUserJoined uid:" + i + " elapsed:" + i2);
        if (this.bF == 2 && isAnchor()) {
            this.cq.put(Integer.valueOf(i), null);
            aF();
        }
    }

    public void onUserOffline(int i, int i2) {
        runOnUiThread(new ab(this, i));
    }

    private void aF() {
        int i = 2;
        if (this.ao) {
            if (this.ap) {
                VideoCompositingLayout videoCompositingLayout = new VideoCompositingLayout();
                videoCompositingLayout.regions = new Region[1];
                videoCompositingLayout.regions[0] = new Region();
                videoCompositingLayout.regions[0].renderMode = 1;
                videoCompositingLayout.regions[0].uid = PayUniversalPlugin.REQUEST_PAY;
                videoCompositingLayout.regions[0].alpha = 1.0d;
                videoCompositingLayout.regions[0].zOrder = 0;
                videoCompositingLayout.regions[0].x = 0.0d;
                videoCompositingLayout.regions[0].y = 0.0d;
                videoCompositingLayout.regions[0].width = 1.0d;
                videoCompositingLayout.regions[0].height = 1.0d;
                videoCompositingLayout.canvasWidth = 360;
                videoCompositingLayout.canvasHeight = PictureGetHelper.IMAGE_SIZE;
                videoCompositingLayout.backgroundColor = "#FFFFFF";
                au().setVideoCompositingLayout(videoCompositingLayout);
            }
        } else if (this.cq.size() == 2) {
            postDelayed(new ac(this));
            boolean isAnchor = isAnchor();
            int i2 = (isAnchor && this.bF == 3) ? 1 : 0;
            VideoCompositingLayout videoCompositingLayout2 = new VideoCompositingLayout();
            if (i2 == 0) {
                i = 1;
            }
            videoCompositingLayout2.regions = new Region[i];
            for (Entry entry : this.cq.entrySet()) {
                LogUtils.d("MicConnect", "refreshVideoView. uid:" + entry.getKey() + " configUid:" + av().mUid + " isAnchor:" + isAnchor);
                if (!(isAnchor && ((Integer) entry.getKey()).intValue() == av().mUid) && (isAnchor || ((Integer) entry.getKey()).intValue() == av().mUid)) {
                    if (i2 != 0) {
                        videoCompositingLayout2.regions[1] = new Region();
                        videoCompositingLayout2.regions[1].renderMode = 1;
                        videoCompositingLayout2.regions[1].uid = ((Integer) entry.getKey()).intValue();
                        videoCompositingLayout2.regions[1].alpha = 1.0d;
                        videoCompositingLayout2.regions[1].zOrder = 1;
                        videoCompositingLayout2.regions[1].x = 0.72d;
                        if (Z()) {
                            videoCompositingLayout2.regions[1].y = 0.37d;
                        } else {
                            videoCompositingLayout2.regions[1].y = 0.68d;
                        }
                        videoCompositingLayout2.regions[1].width = 0.279d;
                        videoCompositingLayout2.regions[1].height = 0.234d;
                    }
                } else if (isAnchor) {
                    videoCompositingLayout2.regions[0] = new Region();
                    videoCompositingLayout2.regions[0].renderMode = 1;
                    videoCompositingLayout2.regions[0].uid = ((Integer) entry.getKey()).intValue();
                    videoCompositingLayout2.regions[0].alpha = 1.0d;
                    videoCompositingLayout2.regions[0].zOrder = 0;
                    videoCompositingLayout2.regions[0].x = 0.0d;
                    videoCompositingLayout2.regions[0].y = 0.0d;
                    videoCompositingLayout2.regions[0].width = 1.0d;
                    videoCompositingLayout2.regions[0].height = 1.0d;
                } else {
                    au().setRemoteVideoStreamType(((Integer) entry.getKey()).intValue(), 0);
                }
            }
            if (isAnchor) {
                videoCompositingLayout2.canvasWidth = 360;
                videoCompositingLayout2.canvasHeight = PictureGetHelper.IMAGE_SIZE;
                videoCompositingLayout2.backgroundColor = "#FFFFFF";
                au().setVideoCompositingLayout(videoCompositingLayout2);
            }
        }
    }

    public void closeMicConnect() {
        closeMicConnect(false);
    }

    public void closeMicConnect(boolean z) {
        long j = 1000;
        this.aq = "";
        this.bF = 0;
        this.ar = null;
        aP();
        this.bA = 0;
        postDelayed(this.cs, z ? 1000 : 0);
        if (this.bH != null) {
            this.bH.removeAllViews();
        }
        Runnable adVar = new ad(this);
        if (!isAnchor()) {
            j = 0;
        }
        postDelayed(adVar, j);
    }

    public void doMicConnect(String str, boolean z) {
        doMicConnect(str, z, false);
    }

    public void doMicConnect(String str, boolean z, boolean z2) {
        ar();
        aw().addEventHandler(this);
        at().configEngine(1, 30);
        if (z || this.ao) {
            ((RtcEngineEx) at().getRtcEngine()).setVideoProfileEx(Publish_Image.MIN_IMG_WIDTH, Publish_Image.MIN_IMG_HEIGHT, 15, ErrorCode$OtherError.CONTENT_FORCE_EXPOSURE);
        }
        SurfaceView CreateRendererView = RtcEngine.CreateRendererView(getApplicationContext());
        this.cq.put(Integer.valueOf(0), CreateRendererView);
        if (!z2) {
            if (z || this.ao) {
                a(CreateRendererView, 0);
            } else {
                a(CreateRendererView);
            }
            au().setupLocalVideo(new VideoCanvas(CreateRendererView, 1, 0));
            at().preview(true, CreateRendererView, 0);
        }
        if (this.ao) {
            au().disableAudio();
        }
        if (z || this.ap) {
            au().configPublisher(new PublisherConfiguration.Builder().owner(true).size(360, PictureGetHelper.IMAGE_SIZE).frameRate(15).biteRate(ErrorCode$OtherError.CONTENT_FORCE_EXPOSURE).streamLifeCycle(1).publishUrl(str).build());
        }
        au().enableLocalVideo(!z2);
        au().muteLocalVideoStream(z2);
        au().setDefaultAudioRoutetoSpeakerphone(true);
        int i = av().mUid;
        if (this.ao) {
            if (this.ap) {
                i = PayUniversalPlugin.REQUEST_PAY;
            } else {
                i = 4321;
            }
        }
        at().joinChannel(this.aq, i, str, z);
        LogUtils.d("MicConnect", "doMicConnect. channel:" + this.aq + " mUserId:" + this.ax.getOriginId());
    }

    protected void a(SurfaceView surfaceView, int i) {
        removeDelayed(this.cs);
        this.bG.addView(surfaceView);
        this.bG.setAlpha(0.0f);
        postDelayed(new ah(this), i > 0 ? 0 : 2000);
    }

    protected void a(SurfaceView surfaceView) {
        if (this.bH == null) {
            this.bH = (FrameLayout) findViewById(R.id.surfaceViewContainer2);
            if (Z()) {
                LayoutParams layoutParams = (LayoutParams) this.bH.getLayoutParams();
                layoutParams.bottomMargin += WindowUtils.dp2Px(201);
            }
        }
        surfaceView.setZOrderMediaOverlay(true);
        this.bH.addView(surfaceView);
        this.bH.setAlpha(0.0f);
        postDelayed(new ai(this), 2000);
    }

    protected void y() {
        ((LayoutParams) this.L.getLayoutParams()).rightMargin = WindowUtils.dp2Px(62);
        this.L.requestLayout();
        if (this.bI != null) {
            this.bI.setVisibility(8);
        }
    }

    protected void a(User user, int i) {
        ViewStub viewStub = (ViewStub) findViewById(R.id.live_mic_user_info);
        if (viewStub != null) {
            LayoutParams layoutParams;
            if (Z()) {
                layoutParams = (LayoutParams) viewStub.getLayoutParams();
                layoutParams.bottomMargin += WindowUtils.dp2Px(201);
            } else if (aa()) {
                layoutParams = (LayoutParams) viewStub.getLayoutParams();
                layoutParams.bottomMargin += this.cM;
            }
            this.bI = (FrameLayout) viewStub.inflate();
            this.bJ = (ImageView) findViewById(R.id.mic_user_close);
            this.bK = (SimpleDraweeView) findViewById(R.id.mic_user_icon);
            this.bL = (TextView) findViewById(R.id.mic_user_tips);
            this.bM = (TextView) findViewById(R.id.mic_user_name);
        }
        if (this.bI != null) {
            ((LayoutParams) this.L.getLayoutParams()).rightMargin = WindowUtils.dp2Px(110);
            this.L.requestLayout();
            this.bI.setVisibility(0);
            if (i == 2) {
                this.bI.getLayoutParams().width = WindowUtils.dp2Px(75);
                this.bI.getLayoutParams().height = WindowUtils.dp2Px(110);
                ((LayoutParams) this.bK.getLayoutParams()).topMargin = WindowUtils.dp2Px(20);
                ((LayoutParams) this.bL.getLayoutParams()).topMargin = WindowUtils.dp2Px(65);
                this.bK.setVisibility(0);
                this.bL.setVisibility(0);
                AppUtils.getInstance().getImageProvider().loadAvatar(this.bK, user.headurl);
                this.bL.setText(aa() ? R.string.live_mic_doll_catching : R.string.live_mic_connecting);
                this.bL.setCompoundDrawablesWithIntrinsicBounds(aa() ? R.drawable.live_mic_ic_doll : R.drawable.live_mic_ic_audio, 0, 0, 0);
                this.bI.setBackgroundResource(R.drawable.bg_mic_user_info);
            } else {
                this.bI.getLayoutParams().width = WindowUtils.dp2Px(100);
                this.bI.getLayoutParams().height = WindowUtils.dp2Px(150);
                ((LayoutParams) this.bK.getLayoutParams()).topMargin = WindowUtils.dp2Px(31);
                ((LayoutParams) this.bL.getLayoutParams()).topMargin = WindowUtils.dp2Px(73);
                if (i == 1) {
                    this.bK.setVisibility(0);
                    this.bL.setVisibility(0);
                    AppUtils.getInstance().getImageProvider().loadWebpImage(this.bK, "res://raw/" + R.raw.live_mic_icon);
                    this.bL.setText(R.string.live_mic_waiting);
                    this.bL.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    this.bI.setBackgroundResource(R.drawable.bg_mic_user_info);
                } else if (i == 3) {
                    this.bK.setVisibility(8);
                    this.bL.setVisibility(8);
                    this.bI.setBackgroundResource(R.color.transparent);
                }
            }
            this.bM.setText(user.name);
            this.bM.setOnClickListener(new aj(this, user));
            boolean isLogin = AppUtils.getInstance().getUserInfoProvider().isLogin();
            ImageView imageView = this.bJ;
            int i2 = (isLogin && !aa() && (isAnchor() || user.isMe())) ? 0 : 8;
            imageView.setVisibility(i2);
            this.bJ.setOnClickListener(new ak(this));
        }
    }

    public void doMicConnect(User user) {
    }

    public void doMicConnect(int i) {
        removeDelayed(this.ct);
        removeDelayed(this.cu);
    }

    public void doMicClose() {
        Builder anVar = new an(this, R.style.SimpleDialog);
        anVar.message(getString(R.string.live_mic_close)).positiveAction(getString(R.string.setting_confirm)).negativeAction(getString(R.string.setting_cancel));
        AppUtils.showDialogFragment(this, anVar);
    }

    protected void z() {
        if (this.ar != null) {
            removeDelayed(this.ct);
            removeDelayed(this.cu);
            if (!aa()) {
                sendLiveMessageAndRefreshUI(LiveMessage.createMicMessage(this.ax.getOriginId(), 5, this.aq, this.ar.getOrigin(), this.ar.getOriginId()));
            }
            if (!TextUtils.isEmpty(this.aq)) {
                closeMicConnect();
            }
            this.ar = null;
        }
        y();
        if (aa()) {
            this.an.hideDollUserInfo();
        }
    }

    protected boolean A() {
        return (this.ar == null || TextUtils.isEmpty(this.aq)) ? false : true;
    }

    public void doReport(User user) {
        NetRequest.getInstance().post(UrlConstants.LIVE_REPORT, new ao(this, user), "live_report", true);
    }

    public void doSilent(User user, int i) {
        if (i != 0) {
            Builder aqVar = new aq(this, R.style.SimpleDialog, i, user);
            aqVar.message(getString(i == 1 ? R.string.live_silent_hint : R.string.live_silent_cancel_hint, new Object[]{user.name})).positiveAction(getString(R.string.setting_confirm)).negativeAction(getString(R.string.setting_cancel));
            AppUtils.showDialogFragment(this, aqVar);
        }
    }

    public void onFollowAnchorSuccess(User user) {
        if (isAnchor(user)) {
            getLiveUser().is_follow = true;
            this.x.setVisibility(8);
            this.v.setVisibility(8);
            sendLiveMessageAndRefreshUI(LiveMessage.createFollowMessage(this.ax.getOriginId(), user.getOriginId()));
        }
    }

    public void onAddOrCancelAdminSuccess(User user) {
        for (LiveMessage liveMessage : this.P) {
            if (liveMessage.getUserId() == user.getOriginId()) {
                liveMessage.getUser().m = user.is_admin;
            }
        }
        postDelayed(this.cw, E());
        sendLiveMessageAndRefreshUI(LiveMessage.createAdminMessage(this.ax.getOriginId(), user.getOriginId(), user.getOrigin(), user.isAdmin()));
    }

    public boolean isMe(LiveMessage liveMessage) {
        return liveMessage.getUserId() == this.ax.getOriginId() && liveMessage.getOrigin() == this.ax.getOrigin();
    }

    public boolean isAnchor() {
        return isAnchor(this.ax);
    }

    public boolean isAnchor(User user) {
        return isAnchor(user.getOriginId(), user.getOrigin());
    }

    public boolean isAnchor(LiveMessage liveMessage) {
        return isAnchor(liveMessage.getUserId(), liveMessage.getOrigin());
    }

    public boolean isAnchor(long j, long j2) {
        User liveUser = getLiveUser();
        return liveUser != null && liveUser.getOriginId() == j && liveUser.getOrigin() == j2;
    }

    protected void a(LiveMessage liveMessage) {
        a(liveMessage, true);
    }

    protected void a(LiveMessage liveMessage, boolean z) {
        if (liveMessage.getSeqId() <= 0) {
            Object next;
            if (liveMessage.getOnlineUserCount() > 0) {
                this.aO = liveMessage.getOnlineUserCount();
            }
            if (liveMessage.getReceivedGiftCount() > 0) {
                this.aP = liveMessage.getReceivedGiftCount();
            }
            if (liveMessage.getReceivedGiftWeekCount() > 0) {
                this.aT = liveMessage.getReceivedGiftWeekCount();
            }
            aI();
            if (!(this.cj || this.e == null)) {
                if (liveMessage.isHistoryCommentMessage()) {
                    this.ck = 3000;
                }
                this.cj = true;
                postDelayed(this.cv, this.ck);
            }
            if (isMe(liveMessage)) {
                this.ax.is_admin = liveMessage.getUserAdmin();
                LiveUser user = liveMessage.getUser();
                if (user != null && user.isValid()) {
                    int i = 0;
                    if (liveMessage.getMessageType() == 1 || liveMessage.getMessageType() == 4 || liveMessage.getMessageType() == 5 || liveMessage.getMessageType() == 6 || liveMessage.getMessageType() == 24 || liveMessage.getMessageType() == 3 || liveMessage.getMessageType() == 2) {
                        i = user.fl;
                    } else if (this.ay != null) {
                        i = this.ay.fl;
                    }
                    this.ay = user;
                    this.ay.fl = i;
                }
            }
            long userId = liveMessage.getUserId();
            if (!(!z || liveMessage.getMessageType() == -1 || liveMessage.getMessageType() == 4 || liveMessage.getMessageType() == 8 || liveMessage.getMessageType() == 9 || liveMessage.getMessageType() == 11 || liveMessage.getMessageType() == 25 || liveMessage.getMessageType() == 13 || liveMessage.getMessageType() == 14 || liveMessage.getMessageType() == 17 || liveMessage.getMessageType() == 18 || liveMessage.getMessageType() == 41)) {
                if (liveMessage.getMessageType() == 3 && userId > 0) {
                    LiveLoveMessage liveLoveMessage = (LiveLoveMessage) liveMessage;
                    if (liveLoveMessage.getLoveColor() == 0) {
                        liveLoveMessage.setLoveColor(((int) (liveMessage.getUserId() % 10)) + 1);
                    }
                    if (!this.cf.containsKey(Long.valueOf(userId))) {
                        this.cf.put(Long.valueOf(userId), Boolean.valueOf(true));
                        postDelayed(this.cw, this.R.isEmpty() ? 0 : E());
                        this.R.add(liveMessage);
                    }
                }
                LinkedBlockingDeque sendedQueue = this.aD.getSendedQueue();
                if (sendedQueue != null && sendedQueue.size() > 0) {
                    Iterator it = sendedQueue.iterator();
                    while (it.hasNext()) {
                        next = it.next();
                        if (liveMessage.getClientMessageId() == ((LiveMessageBase) next).getClientMessageId()) {
                            sendedQueue.remove(next);
                            if (!liveMessage.isLoveMessage()) {
                                if (liveMessage.isCommentMessage()) {
                                    return;
                                }
                            } else {
                                return;
                            }
                        }
                    }
                }
            }
            switch (liveMessage.getMessageType()) {
                case 3:
                    if (!this.ao) {
                        if (!aa() || !A()) {
                            postDelayed(this.cx, this.S.isEmpty() ? 0 : E());
                            this.S.add((LiveLoveMessage) liveMessage);
                            return;
                        }
                        return;
                    }
                    return;
                case 6:
                case 56:
                    LiveGiftMessage liveGiftMessage = (LiveGiftMessage) liveMessage;
                    postDelayed(new au(this, liveGiftMessage, liveMessage));
                    Iterator it2 = this.T.iterator();
                    GiftData giftDataById = ConfigInfoUtil.instance().getGiftDataById(liveGiftMessage.getGiftId());
                    while (it2.hasNext()) {
                        LiveGiftMessage liveGiftMessage2 = (LiveGiftMessage) it2.next();
                        if (liveGiftMessage2 != null && liveGiftMessage2.getUserId() == liveGiftMessage.getUserId() && liveGiftMessage2.getGiftId() == liveGiftMessage.getGiftId() && liveGiftMessage.getGiftCount() > liveGiftMessage2.getGiftCount()) {
                            if (giftDataById == null || giftDataById.cb) {
                                liveGiftMessage2.setShowTime(System.currentTimeMillis());
                                liveGiftMessage2.setGiftCount(liveGiftMessage.getGiftCount());
                                if (!(liveGiftMessage2.getUser() == null || liveGiftMessage.getUser() == null)) {
                                    liveGiftMessage2.setUserLevel(liveGiftMessage.getUserLevel());
                                }
                                liveGiftMessage2.setGiftShowContent(liveGiftMessage.getGiftShowContent());
                                liveGiftMessage2.setGiftContent(liveGiftMessage.getGiftContent());
                                next = 1;
                                if (next == null) {
                                    liveGiftMessage.setShowTime(System.currentTimeMillis());
                                    this.T.add(liveGiftMessage);
                                }
                                if (liveGiftMessage.getGiftId() != 100) {
                                    postDelayed(this.cy, 11000);
                                } else {
                                    postDelayed(this.cy, 1100);
                                }
                                this.ch.put(liveMessage.getUserId() + "_" + liveMessage.getOrigin(), liveGiftMessage.getUserAvatar());
                                return;
                            }
                        }
                    }
                    next = null;
                    if (next == null) {
                        liveGiftMessage.setShowTime(System.currentTimeMillis());
                        this.T.add(liveGiftMessage);
                    }
                    if (liveGiftMessage.getGiftId() != 100) {
                        postDelayed(this.cy, 1100);
                    } else {
                        postDelayed(this.cy, 11000);
                    }
                    this.ch.put(liveMessage.getUserId() + "_" + liveMessage.getOrigin(), liveGiftMessage.getUserAvatar());
                    return;
                case 7:
                case 8:
                case 10:
                    return;
                case 14:
                    LiveBalanceMessage liveBalanceMessage = (LiveBalanceMessage) liveMessage;
                    if (liveBalanceMessage.getLiveMessageContent() != null) {
                        long balance = liveBalanceMessage.getBalance();
                        AppUtils.getInstance().getUserInfoProvider().setBalance(balance);
                        postDelayed(new ar(this, balance));
                        if (ConfigInfoUtil.instance().getGiftCountById(liveBalanceMessage.getFreeGiftId()) != liveBalanceMessage.getFreeGiftCount()) {
                            ConfigInfoUtil.instance().setGiftCountById(liveBalanceMessage.getFreeGiftId(), liveBalanceMessage.getFreeGiftCount());
                            if (this.A != null) {
                                this.A.initGiftView();
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                case 22:
                    if (!this.ao) {
                        List richData = ((LiveRichMessage) liveMessage).getRichData();
                        if (richData != null && richData.size() > 0) {
                            postDelayed(new as(this, richData));
                            return;
                        }
                        return;
                    }
                    return;
                case 29:
                    if (!this.ao) {
                        postDelayed(new at(this, liveMessage));
                        return;
                    }
                    return;
                case 42:
                case 43:
                case 45:
                case 46:
                case 47:
                case 93:
                case 94:
                case 95:
                case 96:
                case 108:
                    postDelayed(new av(this, liveMessage));
                    return;
                case 44:
                case 49:
                    if (!Z()) {
                        LiveGameMessage liveGameMessage = (LiveGameMessage) liveMessage;
                        if (this.d != null) {
                            this.d.game_id = liveGameMessage.getGameId();
                            a(true);
                        }
                    }
                    postDelayed(new aw(this, liveMessage));
                    return;
                case 57:
                    this.aU = ((LiveGiftWeekRankMessage) liveMessage).getRank();
                    aJ();
                    return;
                case 58:
                    b(((LiveGiftWeekRankHintMessage) liveMessage).getHint(), ((LiveGiftWeekRankHintMessage) liveMessage).getShowTime());
                    return;
                case 59:
                    c(liveMessage);
                    return;
                case 107:
                    LiveCommonMessage liveCommonMessage = (LiveCommonMessage) liveMessage;
                    if (this.aj != null) {
                        this.aj.setGuessHistoryList(liveCommonMessage.getLiveMessageContent().wi);
                    }
                    if (this.ak != null) {
                        this.ak.setGuessHistoryList(liveCommonMessage.getLiveMessageContent().wi);
                        return;
                    }
                    return;
                case 109:
                    LiveCommonMessage liveCommonMessage2 = (LiveCommonMessage) liveMessage;
                    userId = liveCommonMessage2.getLiveMessageContent().ls;
                    long j = liveCommonMessage2.getLiveMessageContent().cr;
                    long j2 = liveCommonMessage2.getLiveMessageContent().cs;
                    if (j2 >= 0) {
                        this.mHandler.postDelayed(new ay(this, userId, j, liveCommonMessage2), 1000 * j2);
                        return;
                    }
                    return;
                case 110:
                    postDelayed(new ax(this));
                    return;
                default:
                    Object obj;
                    if (liveMessage.getMessageType() == 4) {
                        if (this.cd.containsKey(Long.valueOf(userId))) {
                            obj = 1;
                        } else {
                            this.cd.put(Long.valueOf(userId), Boolean.valueOf(true));
                            obj = null;
                        }
                    } else if (liveMessage.getMessageType() == 5) {
                        if (this.ce.containsKey(Long.valueOf(userId))) {
                            obj = 1;
                        } else {
                            this.ce.put(Long.valueOf(userId), Boolean.valueOf(true));
                            obj = null;
                        }
                    } else if (liveMessage.getMessageType() == 2) {
                        if (this.cg.containsKey(Long.valueOf(userId))) {
                            obj = 1;
                        } else {
                            this.cg.put(Long.valueOf(userId), Boolean.valueOf(true));
                            obj = null;
                        }
                    } else if (liveMessage.getMessageType() == 24) {
                        if (!this.ao) {
                            postDelayed(new az(this, (LiveBarrageMessage) liveMessage));
                            obj = null;
                        } else {
                            return;
                        }
                    } else if (liveMessage.getMessageType() == 97) {
                        if (!this.ao) {
                            postDelayed(new bb(this, (LiveGlobalRedEnvelopesMessage) liveMessage));
                            obj = null;
                        } else {
                            return;
                        }
                    } else if (liveMessage.getMessageType() == 1 && liveMessage.getClientMessageId() == -1 && this.ci) {
                        obj = 1;
                    } else {
                        obj = null;
                    }
                    if (obj == null) {
                        postDelayed(this.cw, this.R.isEmpty() ? 0 : E());
                        this.R.add(liveMessage);
                        return;
                    }
                    return;
            }
        }
    }

    protected void a(LiveGiftMessage liveGiftMessage) {
    }

    protected void C() {
    }

    protected void a(boolean z, long j, long j2, LiveMessage liveMessage) {
        if (z) {
            this.bq.setOnClickListener(new bc(this, liveMessage));
            this.bo.setText("" + j2);
            this.bp.setText("距开始还有 " + TimeUtils.secToHour(j * 1000));
            this.bn.setVisibility(0);
            if (this.co != null) {
                this.co.cancel();
            }
            if (j > 0) {
                this.br.setVisibility(0);
                this.co = new be(this, j * 1000, 1000);
                this.co.start();
                return;
            }
            this.br.setVisibility(8);
            return;
        }
        this.bn.setVisibility(8);
    }

    private void aG() {
        Object obj = "";
        for (GiftRankRichData giftRankRichData : this.Q) {
            String str = giftRankRichData.id + "_" + giftRankRichData.source;
            if (this.ch.containsKey(str)) {
                giftRankRichData.avatar = (String) this.ch.get(str);
            } else {
                String str2;
                if (obj.isEmpty()) {
                    str2 = obj;
                } else {
                    str2 = obj + Constants.ACCEPT_TIME_SEPARATOR_SP;
                }
                obj = str2 + str;
            }
        }
        if (!TextUtils.isEmpty(obj)) {
            NetRequest.getInstance().get(UrlConstants.LIVE_USER_AVATAR, new bf(this, obj), "live_user_avatar", true);
        }
        this.aw.notifyDataSetChanged();
    }

    protected int D() {
        return (int) ((System.currentTimeMillis() % 10) + 1);
    }

    public void updateBalance(long j) {
        if (Z()) {
            this.aj.updateBalance(j);
        }
        if (aa()) {
            this.an.updateBalance(j);
        }
    }

    public void sendLiveMessageAndRefreshUI(LiveMessageBase liveMessageBase) {
        if (liveMessageBase != null) {
            this.aD.sendMessage(liveMessageBase);
            if (this.P.size() > 0) {
                LiveMessage liveMessage = null;
                if (liveMessageBase.getMessageType() == 3) {
                    liveMessage = LiveMessage.createLoveLiveMessage(this.ax.getOriginId(), 1, this.aW);
                } else if (liveMessageBase.getMessageType() == 1) {
                    liveMessage = LiveMessage.createCommentLiveMessage(this.ax.getOriginId(), ((LiveCommentMsg) liveMessageBase).m.c);
                }
                if (liveMessage != null) {
                    if ((this.ay == null || !this.ay.isValid()) && this.ax != null) {
                        this.ay = new LiveUser();
                        this.ay.n = this.ax.name;
                        this.ay.av = this.ax.headurl;
                        this.ay.l = this.ax.level;
                        this.ay.fl = this.ax.family_level;
                    }
                    liveMessage.setUser(this.ay);
                    a(liveMessage, false);
                }
            }
        }
    }

    public void removeOldMessages() {
        Iterator it = this.P.iterator();
        int i = 0;
        while (it.hasNext() && i < 200) {
            it.next();
            it.remove();
            i++;
        }
    }

    private boolean a(LiveMessage liveMessage, LiveMessage liveMessage2) {
        boolean z = true;
        if (liveMessage2 == null) {
            return false;
        }
        if (liveMessage2.getMessageType() == 4) {
            if (liveMessage2.getUserLevel() > ConfigInfoUtil.instance().getEnterMarqueeMinLevel()) {
                z = false;
            }
            return z;
        } else if (liveMessage.getMessageType() != 48 || liveMessage2.getMessageType() != 48) {
            return false;
        } else {
            LiveGameBetMessage liveGameBetMessage = (LiveGameBetMessage) liveMessage;
            LiveGameBetMessage liveGameBetMessage2 = (LiveGameBetMessage) liveMessage2;
            if (!(liveGameBetMessage.getGameUserId() == liveGameBetMessage2.getGameUserId() && liveGameBetMessage.getGameUserOrigin() == liveGameBetMessage2.getGameUserOrigin() && liveGameBetMessage.getRoleId() == liveGameBetMessage2.getRoleId() && liveGameBetMessage.getGameRoleBetTotal() >= liveGameBetMessage2.getGameRoleBetTotal())) {
                z = false;
            }
            return z;
        }
    }

    protected long E() {
        return (isMessageOverloadOrLowDevice() || this.ao) ? 500 : 200;
    }

    private boolean b(LiveMessage liveMessage) {
        boolean z = false;
        int errorCode;
        int i;
        LiveMicMessage liveMicMessage;
        int operation;
        User micUser;
        LiveDollMessage liveDollMessage;
        switch (liveMessage.getMessageType()) {
            case -2:
            case -1:
                return false;
            case 1:
            case 104:
                return false;
            case 2:
                return false;
            case 3:
            case 105:
                return false;
            case 4:
                boolean isAnchor = isAnchor(liveMessage);
                if (this.ao) {
                    return isAnchor;
                }
                if ((aa() && A()) || isAnchor) {
                    return isAnchor;
                }
                postDelayed(new bl(this, liveMessage));
                return isAnchor;
            case 5:
                return false;
            case 6:
                return false;
            case 7:
                return false;
            case 8:
            case 10:
            case 14:
            case 29:
            case 57:
            case 58:
            case 59:
            case 89:
                return true;
            case 9:
                LinkedBlockingDeque sendedQueue = this.aD.getSendedQueue();
                if (sendedQueue != null && sendedQueue.size() > 0) {
                    Iterator it = sendedQueue.iterator();
                    while (it.hasNext()) {
                        Object next = it.next();
                        if (liveMessage.getClientMessageId() == ((LiveMessageBase) next).getClientMessageId()) {
                            sendedQueue.remove(next);
                        }
                    }
                }
                LiveSendErrorMessageContent liveSendErrorMessageContent = (LiveSendErrorMessageContent) liveMessage.getLiveMessageContent();
                if (liveSendErrorMessageContent != null) {
                    errorCode = liveSendErrorMessageContent.getErrorCode();
                }
                if (errorCode == -2) {
                    s();
                    return true;
                }
                if (errorCode > -200 && errorCode <= -100) {
                    ConfigInfoUtil.instance().updateConfigInfo(true);
                    ConfigInfoUtil.instance().setUpdateConfigCallback(new bn(this, errorCode));
                }
                if (errorCode == -34) {
                    Builder boVar = new bo(this, R.style.SimpleDialog);
                    boVar.message(liveMessage.getContent()).positiveAction(getString(R.string.bind_now)).negativeAction(getString(R.string.bind_later));
                    AppUtils.showDialogFragment(this, boVar);
                    return true;
                } else if (errorCode >= -250 && errorCode <= -221) {
                    return true;
                } else {
                    String content = liveMessage.getContent();
                    if (TextUtils.isEmpty(content)) {
                        content = getString(R.string.live_send_error);
                    }
                    a(content, 1);
                    return true;
                }
            case 11:
                LiveReconnectMessageContent liveReconnectMessageContent = (LiveReconnectMessageContent) liveMessage.getLiveMessageContent();
                if (liveReconnectMessageContent == null || liveReconnectMessageContent.r <= 0 || TextUtils.isEmpty(liveReconnectMessageContent.i)) {
                    return true;
                }
                this.aE = liveReconnectMessageContent.r;
                this.aF = liveReconnectMessageContent.i;
                i = liveReconnectMessageContent.c;
                if (i < 0) {
                    i = 1;
                }
                if (i > 30) {
                    i = 30;
                }
                this.b = i;
                b(true);
                return true;
            case 12:
                this.az = false;
                postDelayed(new bp(this), getActivity() instanceof LivePushActivity ? 0 : Config.BPLUS_DELAY_TIME);
                return false;
            case 13:
                LiveSystemMessage liveSystemMessage = (LiveSystemMessage) liveMessage;
                if (!liveSystemMessage.isNormalType()) {
                    if (!isAnchor()) {
                        return true;
                    }
                    if (liveSystemMessage.isPopupWindowType()) {
                        a(liveMessage, R.string.i_got_it);
                        return true;
                    }
                }
                break;
            case 16:
                a(liveMessage.getLiveMessageContent());
                return true;
            case 17:
                K();
                return false;
            case 18:
                L();
                return false;
            case 19:
                LiveDataMessageContent liveDataMessageContent = (LiveDataMessageContent) liveMessage.getLiveMessageContent();
                if (liveDataMessageContent == null) {
                    return true;
                }
                this.aO = liveDataMessageContent.i;
                this.aS = liveDataMessageContent.c;
                this.aQ = liveDataMessageContent.d;
                this.aR = liveDataMessageContent.v;
                ai();
                return true;
            case 21:
            case 22:
            case 51:
                return false;
            case 23:
                if (liveMessage.getUser() == null) {
                    return true;
                }
                a(liveMessage.getUser());
                if (liveMessage.getUser() == null || !isMe(liveMessage)) {
                    return true;
                }
                AppUtils.getInstance().getUserInfoProvider().setLevel(liveMessage.getUser().l);
                postDelayed(new bq(this), RefreshTipView.FIRST_REFRESH_INTERVAL);
                return true;
            case 24:
                return false;
            case 25:
                if (TextUtils.isEmpty(liveMessage.getContent())) {
                    H();
                } else {
                    H();
                }
                return true;
            case 27:
            case 28:
                User user = this.ax;
                if (liveMessage.getMessageType() == 27) {
                    i = 1;
                } else {
                    i = 0;
                }
                user.is_admin = i;
                a(liveMessage, R.string.setting_confirm);
                return true;
            case 41:
                postDelayed(new br(this, liveMessage));
                return true;
            case 48:
                if (!(isAnchor() && this.aj.isContentVisible())) {
                    z = true;
                }
                if (Z()) {
                    LiveGameBetMessage liveGameBetMessage = (LiveGameBetMessage) liveMessage;
                    liveGameBetMessage.setContent(this.aj.getRoleName(liveGameBetMessage.getRoleId()));
                    return z;
                }
                break;
            case 52:
                liveMicMessage = (LiveMicMessage) liveMessage;
                operation = liveMicMessage.getOperation();
                if (isAnchor()) {
                    switch (operation) {
                        case 1:
                            postDelayed(this.ct, 24000);
                            postDelayed(this.cu, i.MIN_UPLOAD_INTERVAL);
                            this.ar = liveMicMessage.getMicUser();
                            a(this.ar, operation);
                            return true;
                        case 2:
                        case 3:
                            this.aq = liveMicMessage.getChannel();
                            this.bF = operation;
                            this.ar = liveMicMessage.getMicUser();
                            doMicConnect(operation);
                            a(this.ar, operation);
                            return true;
                        case 4:
                            removeDelayed(this.ct);
                            removeDelayed(this.cu);
                            y();
                            this.ar = null;
                            ToastUtil.Long(R.string.live_mic_user_busy_tips);
                            return true;
                        case 5:
                            if (liveMicMessage.isMicMsgFromMe()) {
                                return true;
                            }
                            ToastUtil.Long(R.string.live_mic_user_close_tips);
                            closeMicConnect();
                            return true;
                        default:
                            return true;
                    }
                } else if (this.ax.getOriginId() != liveMicMessage.getSourceId() || this.ax.getOrigin() != liveMicMessage.getSource()) {
                    return true;
                } else {
                    switch (operation) {
                        case 1:
                            this.aq = liveMicMessage.getChannel();
                            this.bE = new MicConnectDialog(this, new bs(this));
                            this.bE.show();
                            return true;
                        case 2:
                        case 3:
                            boolean z2;
                            this.aq = liveMicMessage.getChannel();
                            this.bF = operation;
                            this.ar = liveMicMessage.getMicUser();
                            ae();
                            h();
                            String str = "";
                            if (operation == 2) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            doMicConnect(str, false, z2);
                            a(this.ar, operation);
                            return true;
                        case 4:
                            return true;
                        case 5:
                            if (liveMicMessage.isMicMsgFromMe()) {
                                return true;
                            }
                            if (this.bE == null || !this.bE.isShowing()) {
                                ToastUtil.Long(R.string.live_mic_user_close_tips);
                                closeMicConnect();
                                return true;
                            }
                            this.bE.dismiss();
                            return true;
                        default:
                            return true;
                    }
                }
            case 53:
                liveMicMessage = (LiveMicMessage) liveMessage;
                switch (liveMicMessage.getOperation()) {
                    case 2:
                    case 3:
                        micUser = liveMicMessage.getMicUser();
                        a(micUser, liveMicMessage.getOperation());
                        b(micUser);
                        return true;
                    case 5:
                        y();
                        b(liveMicMessage.getMicUser());
                        return true;
                    default:
                        return true;
                }
            case 56:
                return false;
            case 61:
                a(((LiveActivityMessage) liveMessage).getWebUrl(), (long) ((LiveActivityMessage) liveMessage).getShowTime());
                return true;
            case 71:
                this.bj.setVisibility(0);
                LiveFreeGiftStartMessage liveFreeGiftStartMessage = (LiveFreeGiftStartMessage) liveMessage;
                AppUtils.getInstance().getImageProvider().loadGift(this.bk, liveFreeGiftStartMessage.getGiftUrl());
                this.bl.setBackgroundResource(R.drawable.live_gifts_bg);
                if (liveFreeGiftStartMessage.getDuration() == 0) {
                    this.bl.setText("可领取");
                    this.bl.setBackgroundResource(R.drawable.bg_freegift);
                    this.bj.setOnClickListener(new bu(this));
                    return true;
                }
                if (this.cn != null) {
                    this.cn.cancel();
                }
                this.cn = new bv(this, liveFreeGiftStartMessage.getDuration() * 1000, 1000);
                this.cn.start();
                return true;
            case 72:
                LiveFreeGiftAvailableMessage liveFreeGiftAvailableMessage = (LiveFreeGiftAvailableMessage) liveMessage;
                if (TextUtils.isEmpty(liveFreeGiftAvailableMessage.getGiftUrl())) {
                    return true;
                }
                this.bj.setVisibility(0);
                AppUtils.getInstance().getImageProvider().loadGift(this.bk, liveFreeGiftAvailableMessage.getGiftUrl());
                this.bl.setText("可领取");
                this.bl.setBackgroundResource(R.drawable.bg_freegift);
                this.bj.setOnClickListener(new bx(this));
                return true;
            case 73:
                this.bj.setClickable(false);
                LiveFreeGiftGetMessage liveFreeGiftGetMessage = (LiveFreeGiftGetMessage) liveMessage;
                LinearLayout linearLayout = this.bj;
                if (!liveFreeGiftGetMessage.hasNextRound()) {
                    errorCode = 8;
                }
                linearLayout.setVisibility(errorCode);
                ConfigInfoUtil.instance().setGiftCountById(liveFreeGiftGetMessage.getGiftId(), liveFreeGiftGetMessage.getGiftCount());
                if (this.A != null) {
                    this.A.initGiftView();
                }
                F();
                return true;
            case 74:
                LiveNewerDiscountMessage liveNewerDiscountMessage = (LiveNewerDiscountMessage) liveMessage;
                DiscountDialog discountDialog = new DiscountDialog(this, liveNewerDiscountMessage.getDiamondUrl(), liveNewerDiscountMessage.getDiamondCount(), liveNewerDiscountMessage.getEnterAnimImage(), liveNewerDiscountMessage.getEnterAnimCount(), liveNewerDiscountMessage.getEnterAnimDiscription(), liveNewerDiscountMessage.getFreeGiftImage(), liveNewerDiscountMessage.getFreeGiftCount());
                discountDialog.setClickListener(new ca(this, discountDialog));
                discountDialog.show();
                z = true;
                break;
            case 86:
                liveDollMessage = (LiveDollMessage) liveMessage;
                switch (liveDollMessage.getOperation()) {
                    case 2:
                        if (A()) {
                            closeMicConnect();
                        }
                        removeDelayed(this.cr);
                        this.aq = liveDollMessage.getChannel();
                        this.ar = liveDollMessage.getMicUser();
                        if (TextUtils.isEmpty(this.aq)) {
                            return true;
                        }
                        LiveDollActionMessage.setRoundId(liveDollMessage.getRoundId());
                        doMicConnect("", false, true);
                        if (aa()) {
                            this.an.setDollConnecting();
                            this.an.doDollCountDown(liveDollMessage.getTimeLimit());
                            postDelayed(new bt(this), 200);
                            postDelayed(this.cA, 4000);
                        }
                        if (this.bD != null) {
                            this.bD.setVisibility(0);
                        }
                        this.J.scrollTo(this.cK, 0);
                        return true;
                    case 4:
                        if (liveDollMessage.getRoundId() != LiveDollActionMessage.getRoundId()) {
                            return true;
                        }
                        postDelayed(this.cr, 4000);
                        if (this.bD != null) {
                            this.bD.setVisibility(8);
                        }
                        if (this.bC != null) {
                            this.bC.setVisibility(8);
                        }
                        this.bA = 0;
                        this.J.scrollTo(0, 0);
                        return true;
                    case 5:
                        operation = liveDollMessage.getResult();
                        if (liveDollMessage.getBackDiamondNum() > 0) {
                            LiveDollMessage.setBackDiamondNum(liveDollMessage.getBackDiamondNum());
                        }
                        if (operation == 1 || operation == 2) {
                            new DollResultDialog(this, operation).show();
                        }
                        if (this.bC == null) {
                            return true;
                        }
                        this.bC.setVisibility(8);
                        return true;
                    default:
                        return true;
                }
            case 87:
            case 88:
                liveDollMessage = (LiveDollMessage) liveMessage;
                if (liveDollMessage.getPrice() > 0 && aa()) {
                    this.an.setPrice(liveDollMessage.getPrice());
                }
                if (liveDollMessage.getBackDiamondNum() > 0) {
                    LiveDollMessage.setBackDiamondNum(liveDollMessage.getBackDiamondNum());
                }
                if (liveDollMessage.getFrequency() > 0) {
                    DollCatchView.INTERVAL_SEND_MESSAGE = liveDollMessage.getFrequency();
                }
                switch (liveDollMessage.getOperation()) {
                    case 3:
                        if (!aa()) {
                            return true;
                        }
                        micUser = liveDollMessage.getMicUser();
                        if (this.ax.getOriginId() == micUser.getOriginId() && this.ax.getOrigin() == micUser.getOrigin()) {
                            this.ar = micUser;
                            this.aq = liveDollMessage.getChannel();
                            doMicConnect("", false, true);
                            if (aa()) {
                                this.an.setDollConnecting();
                                this.an.doDollCountDown(liveDollMessage.getTimeLimit());
                                postDelayed(this.cA, 4000);
                            }
                            if (this.bD != null) {
                                this.bD.setVisibility(0);
                            }
                            this.J.scrollTo(this.cK, 0);
                            return true;
                        } else if (!aa()) {
                            return true;
                        } else {
                            this.an.setRequestEnable(false);
                            this.an.showDollUserInfo(micUser);
                            return true;
                        }
                    case 6:
                        if (!aa()) {
                            return true;
                        }
                        if (A()) {
                            postDelayed(this.cr, 4000);
                        }
                        this.an.setDollPrepare();
                        this.an.setRequestEnable(true);
                        this.an.hideDollUserInfo();
                        return true;
                    case 7:
                        if (!aa()) {
                            return true;
                        }
                        if (A()) {
                            closeMicConnect();
                        }
                        this.an.setDollMaintaining();
                        this.an.hideDollUserInfo();
                        return true;
                    default:
                        return true;
                }
            case 98:
            case 103:
                a((LiveRedEnvelopesMessage) liveMessage);
                return true;
            case 102:
                a((LiveRobRedEnvelopesResultMessage) liveMessage);
                return true;
        }
        if (liveMessage.getMessageType() <= 500) {
            return true;
        }
        return z;
    }

    private void aH() {
        sendLiveMessageAndRefreshUI(LiveMessage.createFreeGiftMessage(this.ax.getOriginId()));
    }

    public void showDollCatchingTips() {
        if (this.bC != null) {
            this.bC.setVisibility(0);
        }
    }

    private void b(User user) {
        if (!user.isMe() && !isAnchor()) {
            postDelayed(new cc(this), 3000);
        }
    }

    protected void G() {
        RedEnvelopesConfig redEnvelopesConfig = ConfigInfoUtil.instance().getRedEnvelopesConfig();
        if (redEnvelopesConfig != null) {
            SendRedEnvelopesDialog sendRedEnvelopesDialog = new SendRedEnvelopesDialog(this, redEnvelopesConfig);
            sendRedEnvelopesDialog.setOnSendListener(new cd(this));
            sendRedEnvelopesDialog.show();
        }
    }

    protected void a(LiveRedEnvelopesMessage liveRedEnvelopesMessage) {
        int i = 8;
        View findViewById = findViewById(R.id.fl_red_envelopes);
        if (this.cB != null) {
            this.cB.cancel();
        }
        int num = liveRedEnvelopesMessage.getNum();
        if (num > 0) {
            Object obj;
            findViewById.setVisibility(0);
            TextView textView = (TextView) findViewById.findViewById(R.id.tv_red_envelopes_num);
            TextView textView2 = (TextView) findViewById.findViewById(R.id.tv_red_envelopes_status);
            if (num > 1) {
                i = 0;
            }
            textView.setVisibility(i);
            textView.setText(String.valueOf(num));
            long j = liveRedEnvelopesMessage.getRedEnvelopes().leftSeconds;
            if (j > 0) {
                textView2.setText(String.valueOf(j));
                this.cB = ValueAnimator.ofInt(new int[]{(int) j, 0});
                this.cB.setDuration(1000 * j);
                this.cB.setInterpolator(new LinearInterpolator());
                this.cB.addUpdateListener(new ce(this, textView2));
                this.cB.addListener(new cf(this, textView2));
                this.cB.start();
            } else if (StopLiveHelper.isRobedRedEnvelopes(liveRedEnvelopesMessage.getRedEnvelopes().id)) {
                textView2.setText(R.string.live_red_envelopes_robed);
            } else {
                textView2.setText(R.string.live_red_envelopes_rob);
            }
            RedEnvelopesConfig redEnvelopesConfig = ConfigInfoUtil.instance().getRedEnvelopesConfig();
            if (redEnvelopesConfig == null || TextUtils.isEmpty(redEnvelopesConfig.position)) {
                String str = "top-right";
            } else {
                obj = redEnvelopesConfig.position;
            }
            LayoutParams layoutParams = (LayoutParams) findViewById.getLayoutParams();
            if ("top-right".equals(obj)) {
                layoutParams.gravity = 53;
            } else if ("bottom-right".equals(obj)) {
                layoutParams.gravity = 85;
            } else {
                layoutParams.gravity = 51;
            }
            findViewById.setLayoutParams(layoutParams);
            findViewById.setOnClickListener(new cg(this, liveRedEnvelopesMessage));
            return;
        }
        findViewById.setVisibility(8);
    }

    protected void b(LiveRedEnvelopesMessage liveRedEnvelopesMessage) {
        if (!StopLiveHelper.isRobedRedEnvelopes(liveRedEnvelopesMessage.getRedEnvelopes().id)) {
            RedEnvelopesDialog redEnvelopesDialog = new RedEnvelopesDialog(this, liveRedEnvelopesMessage);
            redEnvelopesDialog.setOnSendListener(new ch(this));
            redEnvelopesDialog.show();
        }
    }

    protected void a(LiveRobRedEnvelopesResultMessage liveRobRedEnvelopesResultMessage) {
        StopLiveHelper.setRobedRedEnvelopes(liveRobRedEnvelopesResultMessage.getRedEnvelopesId());
        ((TextView) findViewById(R.id.tv_red_envelopes_status)).setText(R.string.live_red_envelopes_robed);
        new RedEnvelopesResultDialog(this, liveRobRedEnvelopesResultMessage).show();
    }

    public boolean isSuperUserComing(LiveEnterMessage liveEnterMessage) {
        return (liveEnterMessage.getUserId() == 578888 && liveEnterMessage.getOrigin() == 1 && ConfigInfoUtil.instance().isJiaMingSpecOn()) || liveEnterMessage.getUserLevel() >= ConfigInfoUtil.instance().getSuperUserMinLevel() || liveEnterMessage.isFrameOrSVGAAnim();
    }

    private void a(LiveMessage liveMessage, int i) {
        Builder builder = new Builder(R.style.SimpleDialog);
        builder.message(liveMessage.getContent()).positiveAction(getString(i));
        AppUtils.showDialogFragment(this, builder);
    }

    protected void H() {
    }

    private void a(LiveUser liveUser) {
        if (liveUser != null) {
            b(liveUser);
        }
    }

    protected void I() {
    }

    protected void K() {
        h();
    }

    protected void L() {
        h();
        postDelayed(this.cC, Config.BPLUS_DELAY_TIME);
    }

    private void a(LiveCommonMessageContent liveCommonMessageContent) {
        if (liveCommonMessageContent != null) {
            LiveBeautyMessageContent liveBeautyMessageContent = (LiveBeautyMessageContent) liveCommonMessageContent;
            a(liveBeautyMessageContent.f, liveBeautyMessageContent.b);
        }
    }

    protected void a(String str, boolean z) {
    }

    private void aI() {
        postDelayed(new cj(this));
    }

    private void aJ() {
        if (this.aP < 0) {
            this.aP = 0;
        }
        if (this.aT < 0) {
            this.aT = 0;
        }
        int indexOf = getString(R.string.live_week_gift).indexOf("%");
        CharSequence string = getString(R.string.live_week_gift, new Object[]{String.valueOf(this.aT)});
        if (this.aT > 0) {
            StringBuilder append = new StringBuilder().append(string).append("  |  ");
            int i = R.string.live_gift_week_rank;
            Object[] objArr = new Object[1];
            objArr[0] = this.aU <= 100 ? this.aU + "" : "100+";
            string = append.append(getString(i, objArr)).toString();
        }
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#FDDB2E"));
        CharSequence spannableStringBuilder = new SpannableStringBuilder(string);
        spannableStringBuilder.setSpan(foregroundColorSpan, indexOf, getString(R.string.live_week_gift, new Object[]{String.valueOf(this.aT)}).length(), 33);
        int indexOf2 = getString(R.string.live_gift_count).indexOf("%");
        Object string2 = getString(R.string.live_gift_count, new Object[]{String.valueOf(this.aP)});
        CharSequence spannableStringBuilder2 = new SpannableStringBuilder(string2);
        spannableStringBuilder2.setSpan(foregroundColorSpan, indexOf2, string2.length(), 33);
        this.r.setText(spannableStringBuilder2);
        this.s.setText(spannableStringBuilder);
    }

    private void c(LiveMessage liveMessage) {
        long giftId = ((LiveGiftZhouxingMessage) liveMessage).getGiftId();
        long rank = (long) ((LiveGiftZhouxingMessage) liveMessage).getRank();
        if (rank <= 0) {
            this.bV.setVisibility(8);
            return;
        }
        this.bV.setOnClickListener(new cl(this, liveMessage));
        Object giftUrl = ((LiveGiftZhouxingMessage) liveMessage).getGiftUrl();
        if (!TextUtils.isEmpty(giftUrl)) {
            this.bV.setVisibility(0);
            AppUtils.getInstance().getImageProvider().loadGift(this.bY, giftUrl);
            this.bZ.setText(rank + "");
            if (!this.cb) {
                this.cb = true;
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.bW, View.TRANSLATION_X, new float[]{0.0f, (float) WindowUtils.dp2Px(51)});
                ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.bX, View.TRANSLATION_Y, new float[]{0.0f, (float) WindowUtils.dp2Px(20)});
                ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.bX, View.TRANSLATION_Y, new float[]{(float) WindowUtils.dp2Px(20), (float) WindowUtils.dp2Px(20)});
                ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.bX, View.TRANSLATION_Y, new float[]{(float) WindowUtils.dp2Px(20), 0.0f});
                ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(this.bY, View.TRANSLATION_X, new float[]{0.0f, (float) WindowUtils.dp2Px(22)});
                ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(this.bZ, View.ALPHA, new float[]{0.0f, 1.0f});
                ofFloat.setDuration(280);
                ofFloat2.setDuration(280);
                ofFloat3.setDuration(600);
                ofFloat4.setDuration(280);
                ofFloat5.setDuration(200);
                ofFloat.addListener(new cm(this, ofFloat2));
                ofFloat2.addListener(new cn(this, ofFloat3));
                ofFloat3.addListener(new co(this, ofFloat4));
                ofFloat4.addListener(new cp(this, ofFloat5));
                ofFloat5.addListener(new cq(this, ofFloat6));
                ofFloat.start();
            }
        } else if (TextUtils.isEmpty(ConfigInfoUtil.instance().getGiftUrlById(giftId))) {
            this.bV.setVisibility(8);
        }
    }

    private void b(String str, long j) {
        this.ca.setText(Html.fromHtml(str));
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.ca, View.TRANSLATION_X, new float[]{(float) (-WindowUtils.dp2Px(20)), (float) WindowUtils.dp2Px(JfifUtil.MARKER_RST0)});
        ofFloat.setDuration(520);
        ofFloat.start();
        ofFloat = ObjectAnimator.ofFloat(this.bU, View.ALPHA, new float[]{1.0f, 0.0f});
        ofFloat.setDuration(520);
        ofFloat.start();
        ofFloat = ObjectAnimator.ofFloat(this.ca, View.TRANSLATION_X, new float[]{(float) WindowUtils.dp2Px(JfifUtil.MARKER_RST0), (float) (-WindowUtils.dp2Px(20))});
        ofFloat.setDuration(520);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.bU, View.ALPHA, new float[]{0.0f, 1.0f});
        ofFloat2.setDuration(520);
        postDelayed(new cr(this, ofFloat, ofFloat2), j);
    }

    protected void M() {
        if (!this.aG && this.az && !isFinishing() && this.aE > 0) {
            this.aL = new TimeDelta();
            NetRequest.getInstance().get(UrlConstants.LIVE_ROOM_SOURCE, new cs(this), "live_room_server_ip", this.aI < 5);
        }
    }

    protected void a(JSONObject jSONObject) {
        this.e = (LiveRoom) new BaseResponse(jSONObject).getResponse(new ct(this));
        if (this.e != null) {
            R();
            if (this.e.roomID > 0 && this.e.roomID != this.aE) {
                this.aE = this.e.roomID;
                this.d.room_id = this.e.roomID;
            }
            this.aF = this.e.srvIP;
            this.aI = 0;
            LogUtils.d(a, "Live chat room id " + this.aE + ", and address " + this.aF);
            e();
            ax();
            if (this.e.room_status != null) {
                a(this.e.room_status.filter, this.e.room_status.beauty);
            }
            if (this.e.room_status == null || this.e.room_status.status != 0) {
                P();
            } else {
                c();
            }
        }
        b(jSONObject);
    }

    protected void b(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("template");
        if (optJSONObject != null) {
            Iterator keys = optJSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                this.aV.put(str, optJSONObject.optString(str));
            }
        }
    }

    public long getRoomId() {
        return this.aE;
    }

    protected void N() {
        a(LiveMessage.createConnectMessage(this.bN));
        if (NetworkUtils.getInstance().isNetworkAvailable() && this.isOnResume) {
            if (this.aI <= 5) {
                this.aI++;
            }
            postDelayed(this.cD, (long) (this.aI * 1000));
        }
    }

    protected boolean O() {
        return this.b > 1 && this.aN.getDelta() >= 3000;
    }

    protected String a(long j, String str) {
        String format;
        if (TextUtils.isEmpty(str) || !str.startsWith(UrlConstants.WEBSOCKET_WSS)) {
            format = String.format(UrlConstants.LIVE_ROOM_ACCESS, new Object[]{str});
        } else {
            format = str.replace(UrlConstants.WEBSOCKET_WSS, "");
            format = String.format(UrlConstants.LIVE_ROOM_ACCESS_WSS, new Object[]{format});
        }
        Map hashMap = new HashMap();
        hashMap.put("room_id", Long.toString(j));
        hashMap.put("encoding", "text");
        if (this.bc != null) {
            hashMap.put(LivePullLauncher.STSOURCE, this.bc);
            hashMap.put("tapIndex", this.bd + "");
        }
        hashMap.put("source", AppUtils.getInstance().getUserInfoProvider().getUserOrigin() + "");
        hashMap.put("source_id", AppUtils.getInstance().getUserInfoProvider().getUserId() + "");
        return NetRequest.convertParams(format, hashMap, 0, NetRequest.LIVE_SALT);
    }

    protected void P() {
        this.mHandler.removeCallbacks(this.bs);
        this.c = false;
        if (!this.aD.isConnected() && !this.aD.isConnecting() && !isFinishing()) {
            if (!((!aM() && !O()) || aK() || aN())) {
                a(LiveMessage.createConnectMessage(this.bO));
            }
            String a = a(this.aE, this.aF);
            this.aH++;
            this.aM = new TimeDelta();
            this.aD.connect(a);
            this.aD.setOnMessageListener(new cw(this, a));
        }
    }

    public void statMessageCountPerSecond() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.aY >= 1000) {
            LogUtils.d(a, "received message count " + this.aZ + "/s and stat message overload count " + this.ba);
            if (this.aZ >= 60) {
                if (this.ba < 10) {
                    this.ba++;
                }
            } else if (this.aZ > 0 && this.ba > 0) {
                this.ba--;
            }
            this.aY = currentTimeMillis;
            this.aZ = 0;
        }
        this.aZ++;
    }

    public boolean isMessageOverloadOrLowDevice() {
        return this.ba >= 5 || AppUtils.getInstance().isLowSpecificationDevice();
    }

    public boolean isMessageOverload() {
        return this.ba >= 5;
    }

    public void onShowSmallGiftAnim(LiveGiftMessage liveGiftMessage) {
        this.ac.addGift(liveGiftMessage);
    }

    public void onShowSpecialAnimWhenMeetACertainNumber(int i, int i2, long j) {
        this.ag.showSpecialAnimation(i, i2, j);
    }

    public void onGlobalBarrageClicked(LiveGlobalRedEnvelopesMessage liveGlobalRedEnvelopesMessage) {
        if (getLiveUser() == null) {
            return;
        }
        if ((liveGlobalRedEnvelopesMessage.getAnchorId() != getLiveUser().getOriginId() || liveGlobalRedEnvelopesMessage.getAnchorOrigin() != getLiveUser().getOrigin()) && !getLiveUser().isMe()) {
            AppUtils.getInstance().getUserInfoProvider().toLive((android.app.Activity) this, String.valueOf(liveGlobalRedEnvelopesMessage.getAnchorId()), liveGlobalRedEnvelopesMessage.getAnchorOrigin());
            finish();
        }
    }

    protected void Q() {
    }

    protected void R() {
        c("success");
    }

    protected void b(String str) {
        c("fail " + str);
    }

    protected void c(String str) {
        if (!(!this.aK || this.ax == null || this.aL == null)) {
            statEventDuration("chat_server_ip_select", Long.toString(this.ax.getOriginId()), this.aL.getDelta(), Integer.toString(this.aI), str, this.aF);
        }
        this.aL = null;
    }

    protected void S() {
        e("success");
    }

    protected void d(String str) {
        e("fail " + str);
    }

    protected synchronized void e(String str) {
        if (this.aK && this.ax != null && this.aM != null && this.aH > 0) {
            statEventDuration("chat_server_ip_connected", Long.toString(this.ax.getOriginId()), this.aM.getDelta(), Integer.toString(this.aH - 1), str, this.aF);
        }
        this.aM = null;
    }

    protected void T() {
        b(false);
    }

    protected void b(boolean z) {
        if (this.az && !isFinishing() && this.isOnResume) {
            LogUtils.d(a, "live chat room reconnect preparing... " + this.b);
            if (z) {
                postDelayed(this.bs, (long) (this.b * 1000));
                this.b = 1;
                this.c = true;
                return;
            } else if (!this.c && !this.aD.isConnected()) {
                this.c = true;
                if (this.aH > 3) {
                    this.b = 1;
                    this.aH = 0;
                    this.aF = null;
                }
                if (!(this.P.size() <= 0 || !O() || this.aD.isConnecting() || aM() || aL())) {
                    String str = this.bQ;
                    if (aK()) {
                        str = this.bP;
                    }
                    a(LiveMessage.createConnectMessage(str));
                }
                postDelayed(this.bs, (long) (this.b * 1000));
                if (this.b < 5) {
                    this.b = (this.b * 2) + 1;
                }
                if (this.b > 5) {
                    this.b = 5;
                    return;
                }
                return;
            } else {
                return;
            }
        }
        this.c = false;
        this.mHandler.removeCallbacks(this.bs);
    }

    protected void a(Runnable runnable) {
        if (this.aJ < 5) {
            this.aJ = (this.aJ * 2) + 1;
        }
        if (this.aJ > 5) {
            this.aJ = 5;
        }
        postDelayed(runnable, (long) (this.aJ * 1000));
    }

    private boolean aK() {
        return aO().equals(this.bO);
    }

    private boolean aL() {
        String aO = aO();
        return aO.equals(this.bP) || aO.equals(this.bQ);
    }

    private boolean aM() {
        return aO().equals(this.bN);
    }

    private boolean aN() {
        return aO().equals(this.bR);
    }

    private String aO() {
        if (this.P != null && this.P.size() > 0) {
            LiveMessage liveMessage = (LiveMessage) this.P.get(this.P.size() - 1);
            if (liveMessage != null && liveMessage.isConnectMessage()) {
                return liveMessage.getContent();
            }
        }
        return "";
    }

    protected void U() {
        if (AppUtils.getInstance().getUserInfoProvider().isLogin()) {
            NetRequest.getInstance().get(UrlConstants.GET_BALANCE, new da(this), "request_balance", true);
        }
    }

    protected void V() {
        c(true);
    }

    protected void c(boolean z) {
        this.c = false;
        if (this.aD != null) {
            this.aD.disconnect();
        }
    }

    protected void W() {
    }

    protected void onRestart() {
        super.onRestart();
        this.aJ = 0;
    }

    protected void onResume() {
        super.onResume();
        if (!(this.cp == null || this.cp.isPlaying())) {
            this.cp.start();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        if (this.bS == null) {
            this.bS = new LiveBaseActivity$LiveReceiver(this);
        }
        registerReceiver(this.bS, intentFilter);
        ShareCallbackHelper.getInstance().setShareCallback(this.cl);
    }

    protected void X() {
        WindowUtils.setNonTransparentNavigationBar(this);
        this.y.setVisibility(4);
        this.z.setVisibility(4);
        this.V.setVisibility(0);
        if (!(TextUtils.isEmpty(this.aa) || this.Y.getText().toString().startsWith(this.aa))) {
            this.Y.setText(this.aa);
        }
        this.Y.setSelection(this.Y.getText().length());
        this.Y.requestFocus();
        c(8);
        ad();
        KeyBoardUtils.showSoftInput(this);
    }

    protected void Y() {
        setTransparentNavigationBarIfNeed();
        this.y.setVisibility(0);
        this.z.setVisibility(0);
        this.V.setVisibility(4);
        c(0);
        aA();
        ac();
        KeyBoardUtils.hideSoftInput(this);
    }

    private void c(int i) {
        this.bv.setVisibility(i);
    }

    protected boolean a(View view) {
        return this.cI < this.cN && view != this.L;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z = false;
        int rawY = (int) motionEvent.getRawY();
        int rawX = (int) motionEvent.getRawX();
        int i = (int) ((((float) (this.cK - rawX)) / ((float) this.cK)) * 200.0f);
        switch (motionEvent.getAction()) {
            case 0:
                this.cE = rawY;
                this.cF = rawY;
                this.cH = rawX;
                this.cI = rawX;
                if (view != this.L) {
                    z = true;
                }
                this.cP = z;
                break;
            case 1:
                if (!a(view)) {
                    rawX = this.cI - rawX;
                    if (this.J.getScrollX() > 0 && rawX > this.cK / 4) {
                        a(this.J, this.cK, 0, (int) Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
                    } else if (this.J.getScrollX() < 0 && rawX < (-this.cK) / 4) {
                        a(this.J, -this.cK, 0, (int) Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
                    } else if (Math.abs(this.J.getScrollX()) < this.cK) {
                        a(this.J, 0, 0, (int) Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
                    }
                    if (this.cP && Z()) {
                        rawY = this.cF - rawY;
                        if (rawY > this.cN && !this.aj.isVisible()) {
                            ab();
                            WindowUtils.setNonTransparentNavigationBar(this);
                        } else if (rawY < (-this.cN) && this.aj.isVisible()) {
                            d(false);
                            WindowUtils.setTransparentNavigationBar(this);
                        }
                    }
                } else if (getWindow().getDecorView().getScrollX() < (-this.cK) / 4) {
                    a(getWindow().getDecorView(), -this.cK, 0, Callback.DEFAULT_SWIPE_ANIMATION_DURATION, new db(this));
                    getWindow().getDecorView().setAnimation(new AlphaAnimation((float) i, 0.0f));
                    ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{i, 0});
                    ofInt.setDuration(250);
                    ofInt.addUpdateListener(new dc(this));
                    ofInt.start();
                } else {
                    a(getWindow().getDecorView(), 0, 0, (int) Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
                }
                this.cH = 0;
                this.cE = 0;
                break;
            case 2:
                if (!a(view)) {
                    if ((this.cJ > 0 && rawX - this.cH < 0) || ((this.cJ < 0 && rawX - this.cH > 0) || this.cI == 0)) {
                        this.cI = rawX;
                    }
                    if ((this.cG > 0 && rawY - this.cE < 0) || ((this.cG < 0 && rawY - this.cE > 0) || this.cF == 0)) {
                        this.cF = rawY;
                    }
                }
                if (this.cH != 0) {
                    this.cJ = rawX - this.cH;
                }
                if (this.cE != 0) {
                    this.cG = rawY - this.cE;
                }
                LogUtils.d("Scroll", "offsetX:" + this.cJ + ", rawX:" + rawX + ", lastX:" + this.cH);
                if (a(view)) {
                    getWindow().getDecorView().scrollBy(-this.cJ, 0);
                    getWindow().getDecorView().getBackground().setAlpha(i);
                } else if (Math.abs(this.cJ) > 10 && Math.abs(this.cG) * 3 < Math.abs(this.cJ)) {
                    this.J.scrollBy(-this.cJ, 0);
                    if (this.J.getScrollX() > this.cK) {
                        this.J.scrollTo(-this.cK, 0);
                    } else if (this.J.getScrollX() < (-this.cK)) {
                        this.J.scrollTo(this.cK, 0);
                    }
                    this.cP = false;
                }
                this.cE = rawY;
                this.cH = rawX;
                break;
        }
        return this.i.onTouchEvent(motionEvent);
    }

    protected boolean Z() {
        return this.aj != null;
    }

    protected boolean aa() {
        return this.an != null;
    }

    protected void ab() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.aj, View.TRANSLATION_Y, new float[]{(float) this.cO, 0.0f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.t, View.TRANSLATION_Y, new float[]{(float) this.cO, 0.0f});
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.J, View.TRANSLATION_Y, new float[]{(float) this.cO, 0.0f});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2, ofFloat3});
        animatorSet.setDuration(300);
        animatorSet.addListener(new dd(this));
        animatorSet.start();
        this.aj.setVisibility(0);
        this.aj.showContent();
    }

    protected void ac() {
        if (Z()) {
            this.aj.showContent();
        }
    }

    protected void d(boolean z) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.aj, View.TRANSLATION_Y, new float[]{0.0f, (float) this.cO});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.t, View.TRANSLATION_Y, new float[]{0.0f, (float) this.cO});
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.J, View.TRANSLATION_Y, new float[]{0.0f, (float) this.cO});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2, ofFloat3});
        animatorSet.setDuration(300);
        animatorSet.start();
        animatorSet.addListener(new de(this, z));
    }

    protected void ad() {
        if (Z()) {
            this.aj.hideContent();
        }
    }

    public void setFuhuokaCount(long j) {
        if (this.bo != null && this.bo.getVisibility() == 0) {
            this.bo.setText("" + j);
        }
    }

    private void a(View view, int i, int i2, int i3) {
        int i4 = i3 / 10;
        int scrollX = (i - view.getScrollX()) / i4;
        for (int i5 = 0; i5 < i4; i5++) {
            postDelayed(new df(this, i5, i4, view, scrollX, i2, i), (long) (10 * i5));
        }
    }

    private void a(View view, int i, int i2, int i3, Runnable runnable) {
        a(view, i, i2, i3);
        if (runnable != null) {
            postDelayed(runnable, (long) i3);
        }
    }

    public void setStopLive() {
        AppUtils.getInstance().setLiving(false);
        z();
        ae();
        V();
        if (this.cp != null) {
            this.cp.pause();
        }
    }

    protected void af() {
        LogUtils.e(a, "live onNetworkDisconnected ");
        a(R.string.net_error);
        h();
        c(false);
    }

    protected void a(int i) {
        a(i, 0);
    }

    protected void a(int i, int i2) {
        a(getString(i), i2);
    }

    protected void f(String str) {
        a(str, 0);
    }

    protected void a(String str, int i) {
        if (i == 0) {
            ToastUtil.Short(str);
        } else {
            ToastUtil.Long(str);
        }
    }

    protected void ag() {
        LogUtils.e(a, "live onNetworkConnected ");
        this.aJ = 0;
        T();
    }

    protected void onPause() {
        super.onPause();
        if (this.bS != null) {
            unregisterReceiver(this.bS);
        }
        LogUtils.d(a, "live onPause " + this.isOnResume);
    }

    public void finish() {
        aQ();
        LogUtils.d(a, "live finish ");
        super.finish();
        if (this.cm) {
            overridePendingTransition(0, 0);
        }
    }

    protected void onStop() {
        if (this.ar != null) {
            z();
        }
        super.onStop();
        LogUtils.d(a, "live onStop ");
    }

    protected void onDestroy() {
        LogUtils.d(a, "live onDestroy ");
        EventBus.getDefault().unregister(this);
        k();
        if (Z()) {
            this.aj.release();
        }
        if (aa()) {
            this.an.release();
        }
        if (this.ak != null) {
            this.ak.release();
        }
        for (QsbkWebView qsbkWebView : this.bw) {
            this.bv.removeView(qsbkWebView);
            qsbkWebView.onDestroy();
            Log.e(a, "hotpatch: webview destroy");
            qsbkWebView.destroy();
        }
        StatSDK.forceReport(this);
        aQ();
        aP();
        super.onDestroy();
        System.gc();
    }

    private void aP() {
        this.cq.clear();
        if (at() != null) {
            at().leaveChannel(av().mChannel);
            at().preview(false, null, 0);
            aw().removeEventHandler(this);
            as();
        }
    }

    private void aQ() {
        this.az = false;
        ShareCallbackHelper.getInstance().setShareCallback(null);
        StopLiveHelper.setLivingActivity(null);
        if (this.cp != null) {
            this.cp.release();
            this.cp = null;
        }
        V();
        if (this.aD != null) {
            this.aD.disconnect();
            this.aD.detach();
        }
        i();
        AppUtils.getInstance().getImageProvider().clearMemoryCaches();
        this.R.clear();
        this.S.clear();
        this.cd.clear();
        this.cg.clear();
        this.cf.clear();
        this.ce.clear();
        this.cg.clear();
        this.aV.clear();
        this.c = false;
        this.mHandler.removeCallbacks(this.cD);
        this.mHandler.removeCallbacks(this.bs);
        this.mHandler.removeCallbacks(this.cw);
        this.mHandler.removeCallbacks(this.cx);
        this.mHandler.removeCallbacks(this.cy);
        this.mHandler.removeCallbacks(this.cz);
        this.mHandler.removeCallbacks(this.bb);
        this.mHandler.removeCallbacks(this.cR);
        this.mHandler.removeCallbacks(this.cS);
        this.mHandler.removeCallbacks(this.cv);
        this.mHandler.removeCallbacks(this.cC);
        this.ab.releaseResource();
        this.ac.releaseResource();
        this.ag.releaseResource();
        this.af.releaseResource();
        this.by.releaseResource();
        this.bz.release();
        this.m.releaseResource();
        this.ad.releaseResource();
        this.ae.releaseResource();
        this.ai.releaseResource();
        if (this.al != null) {
            this.al.releaseResource();
        }
        if (this.bT != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(this.bT);
            this.bT.onDestroy();
            this.bT = null;
        }
        d("exit live");
    }

    public void onAnimAvatarClick(User user) {
        a(user);
    }

    private void b(LiveUser liveUser) {
        this.am.showLevelUp(liveUser.av, liveUser.l);
    }

    protected boolean ah() {
        return PreferenceUtils.instance().getBoolean("show_live_info", false) && AppUtils.getInstance().isTestOnlyChannel();
    }

    protected void a(ImageView imageView, String str) {
        a(imageView, str, true);
    }

    protected void a(ImageView imageView, String str, boolean z) {
        AppUtils.getInstance().getImageProvider().loadAvatar(imageView, str, z);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        switch (i) {
            case 24:
                d(1);
                return true;
            case 25:
                d(-1);
                return true;
            default:
                return super.onKeyDown(i, keyEvent);
        }
    }

    private void d(int i) {
        if (A()) {
            AudioManagerUtils.getInstance().getAudioManager().adjustStreamVolume(3, i, 0);
            AudioManagerUtils.getInstance().getAudioManager().adjustStreamVolume(0, i, 0);
            this.ah.setVolume(AudioManagerUtils.getInstance().getCurrentVolume(3) + AudioManagerUtils.getInstance().getCurrentVolume(0), AudioManagerUtils.getInstance().getMaxVolume(3) + AudioManagerUtils.getInstance().getMaxVolume(0), i);
            return;
        }
        AudioManagerUtils.getInstance().getAudioManager().adjustStreamVolume(3, i, 0);
        this.ah.setVolume(AudioManagerUtils.getInstance().getCurrentVolume(3), AudioManagerUtils.getInstance().getMaxVolume(3), i);
    }

    protected void ai() {
    }

    public void disShowBottomFollowTipsDialog() {
        this.cQ = false;
    }

    public void sendGift(long j) {
        sendLiveMessageAndRefreshUI(LiveMessage.createGiftMessage(this.ax.getOriginId(), j, this.d.author.getOrigin(), this.d.author.getOriginId()));
    }

    public void sendNextRound(long j, long j2, int i) {
        sendLiveMessageAndRefreshUI(LiveMessage.createGameNextRoundMessage(this.ax.getOriginId(), j, j2, i));
    }

    public void dim() {
        this.y.setAlpha(0.2f);
        this.O.setAlpha(0.2f);
    }

    public void light() {
        this.y.setAlpha(1.0f);
        this.O.setAlpha(1.0f);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 103) {
            updateBalance(AppUtils.getInstance().getUserInfoProvider().getBalance());
        }
        if (this.bx != null) {
            this.bx.onActivityResult(i, i2, intent);
        }
    }

    public void startActivityForResult(Plugin plugin, Intent intent, int i) {
        this.bx = plugin;
        startActivityForResult(intent, i);
    }

    public void setFocusPlugin(Plugin plugin) {
        this.bx = plugin;
    }

    public android.app.Activity getCurActivity() {
        return this;
    }

    protected void aj() {
    }

    public void onFamilyEnterEffect(LiveEnterMessage liveEnterMessage) {
        this.by.setViewContentAndStartAnim(liveEnterMessage);
        postDelayed(new f(this), 150);
    }

    protected void ak() {
        User liveUser = getLiveUser();
        if (liveUser != null) {
            NetRequest.getInstance().get(UrlConstants.LIVE_USER_INFO, new g(this, liveUser), "live_family_support", true);
        }
    }

    @Subscribe
    public void onMessageEvent(Map<String, Object> map) {
        if (map != null && this.ax != null) {
            String str = (String) map.get("type");
        }
    }

    protected void a(String str, long j) {
        FullScreenWebActivity.launch(this, str, j);
    }

    protected void b(int i) {
        LayoutParams layoutParams = (LayoutParams) this.L.getLayoutParams();
        layoutParams.height = i;
        this.L.setLayoutParams(layoutParams);
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.K.getLayoutParams();
        layoutParams2.height = i;
        this.K.setLayoutParams(layoutParams2);
    }

    protected void al() {
        b(WindowUtils.dp2Px(72));
    }

    protected void am() {
        if (this.bm > 0) {
            b(this.bm - this.be.getNavigationHideHeight());
        }
    }

    public void share(int i, LiveMessage liveMessage) {
        if (!AppUtils.getInstance().getUserInfoProvider().isLogin()) {
            AppUtils.getInstance().getUserInfoProvider().toLogin(getActivity(), 1001);
        } else {
            NetRequest.getInstance().post(UrlConstants.LIVE_GAME_GUESS_SHARE, new i(this, liveMessage, i), "live_guess_share_" + i, true);
        }
    }
}
