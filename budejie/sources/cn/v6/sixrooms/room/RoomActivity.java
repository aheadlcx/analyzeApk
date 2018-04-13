package cn.v6.sixrooms.room;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.RoomManage;
import cn.v6.sixrooms.avsolution.common.IPlayer;
import cn.v6.sixrooms.avsolution.common.SixPlayer;
import cn.v6.sixrooms.bean.AnchorPrompt;
import cn.v6.sixrooms.bean.AuthKeyBean;
import cn.v6.sixrooms.bean.BroadcastBean;
import cn.v6.sixrooms.bean.FlyTextBean;
import cn.v6.sixrooms.bean.GuardStausBean;
import cn.v6.sixrooms.bean.LiveStateBean;
import cn.v6.sixrooms.bean.LiveinfoBean;
import cn.v6.sixrooms.bean.RoomEventFloatBean;
import cn.v6.sixrooms.bean.RoomUpgradeMsg;
import cn.v6.sixrooms.bean.RoominfoBean;
import cn.v6.sixrooms.bean.RoommsgBean;
import cn.v6.sixrooms.bean.SubLiveListBean;
import cn.v6.sixrooms.bean.UpdateGiftNumBean;
import cn.v6.sixrooms.bean.UserBean;
import cn.v6.sixrooms.bean.UserInfoBean;
import cn.v6.sixrooms.bean.WelcomeBean;
import cn.v6.sixrooms.bean.WrapRoomInfo;
import cn.v6.sixrooms.constants.CommonInts;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.constants.CustomBroadcast;
import cn.v6.sixrooms.constants.GiftIdStrs;
import cn.v6.sixrooms.engine.GetInfoEngine;
import cn.v6.sixrooms.engine.RoomRepertoryGiftEngine;
import cn.v6.sixrooms.pojo.HistroyWatch;
import cn.v6.sixrooms.presenter.FollowPresenter;
import cn.v6.sixrooms.presenter.PropListPresenter;
import cn.v6.sixrooms.room.BaseRoomActivity.WindowColor;
import cn.v6.sixrooms.room.IM.IMMessageLastManager;
import cn.v6.sixrooms.room.engine.CommonEventStatusEngine;
import cn.v6.sixrooms.room.engine.OperatorFlowEngine;
import cn.v6.sixrooms.room.fragment.FullScreenRoomFragment;
import cn.v6.sixrooms.room.fragment.RoomBaseFragment;
import cn.v6.sixrooms.room.gift.BecomeGodSceneFactory;
import cn.v6.sixrooms.room.gift.ConfessionSceneFactory;
import cn.v6.sixrooms.room.gift.Gift;
import cn.v6.sixrooms.room.gift.GiftConfigUtil;
import cn.v6.sixrooms.room.gift.GiftPoseFactory;
import cn.v6.sixrooms.room.gift.GiftSceneFactory;
import cn.v6.sixrooms.room.gift.GiftWebview;
import cn.v6.sixrooms.room.gift.NotificationSceneFactory;
import cn.v6.sixrooms.room.gift.PoseConfig;
import cn.v6.sixrooms.room.gift.SmaillFlyScreenSceneFactory;
import cn.v6.sixrooms.room.gift.SpecialSceneFactory;
import cn.v6.sixrooms.room.gift.SpecialenterFactory;
import cn.v6.sixrooms.room.gift.SuperFireworksSceneFactory;
import cn.v6.sixrooms.room.presenter.InroomPresenter;
import cn.v6.sixrooms.room.presenter.InroomPresenter.Inroomable;
import cn.v6.sixrooms.room.presenter.InroomPresenter.Playerabel;
import cn.v6.sixrooms.room.presenter.InroomPresenter.Socketable;
import cn.v6.sixrooms.room.statistic.StatisticCodeTable;
import cn.v6.sixrooms.room.statistic.StatisticManager;
import cn.v6.sixrooms.room.statistic.StatisticValue;
import cn.v6.sixrooms.room.utils.ConfigUpdataDispatcher;
import cn.v6.sixrooms.room.utils.GiftAnimQueue;
import cn.v6.sixrooms.room.utils.phone.LiveFlvManager;
import cn.v6.sixrooms.room.widgets.CommonEventDialog;
import cn.v6.sixrooms.socket.IM.IMListener;
import cn.v6.sixrooms.socket.IM.IMMsgSocket;
import cn.v6.sixrooms.socket.chat.CommonEventStatusBean;
import cn.v6.sixrooms.surfaceanim.AnimRenderConfig.Builder;
import cn.v6.sixrooms.surfaceanim.AnimViewControl;
import cn.v6.sixrooms.surfaceanim.animinterface.IOnAnimDrawListener;
import cn.v6.sixrooms.surfaceanim.util.FrescoUtil;
import cn.v6.sixrooms.surfaceanim.view.AnimSurfaceView;
import cn.v6.sixrooms.surfaceanim.view.AnimSurfaceViewTouch;
import cn.v6.sixrooms.ui.fragment.FragmentHardwarePlayer;
import cn.v6.sixrooms.ui.phone.room.spirit.FlyTextSpirit;
import cn.v6.sixrooms.ui.phone.room.spirit.SpiritSurfaceView;
import cn.v6.sixrooms.utils.AppCount;
import cn.v6.sixrooms.utils.CheckRoomTypeUtils;
import cn.v6.sixrooms.utils.DensityUtil;
import cn.v6.sixrooms.utils.DialogUtils;
import cn.v6.sixrooms.utils.DisPlayUtil;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.ImprovedProgressDialog;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.utils.NetWorkUtil;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import cn.v6.sixrooms.utils.SharedPreferencesUtils;
import cn.v6.sixrooms.utils.ToastUtils;
import cn.v6.sixrooms.utils.phone.HistoryDbTool;
import cn.v6.sixrooms.view.interfaces.IOnBackPressedListener;
import cn.v6.sixrooms.view.interfaces.IOnKeyDownListener;
import cn.v6.sixrooms.view.interfaces.IRoomHistoryContral;
import cn.v6.sixrooms.view.interfaces.IRoomPlayerContral;
import cn.v6.sixrooms.view.interfaces.IRoomPlayerViewStateListener;
import cn.v6.sixrooms.view.interfaces.OnRoomTypeChangeListener;
import cn.v6.sixrooms.view.interfaces.RoomInputDialogListener;
import cn.v6.sixrooms.view.interfaces.RoomTypeable;
import cn.v6.sixrooms.widgets.phone.RelativeLayoutGift;
import cn.v6.sixrooms.widgets.phone.ShowGuardPopWindow;
import cn.v6.sixrooms.widgets.phone.SurfaceViewGift.InterfaceSurfaceAnimation;
import com.tencent.connect.common.Constants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@TargetApi(17)
public class RoomActivity extends BaseRoomActivity implements OnClickListener, Inroomable, Playerabel, Socketable, IOnAnimDrawListener, IRoomPlayerContral, IRoomPlayerViewStateListener, RoomTypeable {
    public static final String GIFT_ANIM = "gift_anim";
    public static final String KEY_ANIM_CLEAN = "key_anim_clean";
    public static final String TAG = RoomActivity.class.getSimpleName();
    public static final String VIDEOTYPE_COMMON = "1";
    public static final String VIDEOTYPE_LANDSCAPE = "6";
    public static final String VIDEOTYPE_PORTRAIT = "5";
    public static final String VIDEOTYPE_UNKNOWN = "-1";
    private static boolean e = false;
    private RoominfoBean A;
    private String B = "";
    private List<SubLiveListBean> C = new ArrayList();
    private UpdateGiftNumBean D = new UpdateGiftNumBean();
    private RelativeLayoutGift E;
    private LinearLayout F;
    private List<IRoomPlayerViewStateListener> G = new ArrayList();
    private PlayerState H = PlayerState.PLAYLONGIND;
    private int I = -1;
    private AnimViewControl J;
    private AnimViewControl K;
    private SpecialenterFactory L;
    private ImageView M;
    private boolean N;
    private String O;
    private BecomeGodSceneFactory P;
    private SmaillFlyScreenSceneFactory Q;
    private SuperFireworksSceneFactory R;
    private ConfessionSceneFactory S;
    private NotificationSceneFactory T;
    private AnimSurfaceViewTouch U;
    private GiftWebview V;
    private GiftAnimQueue W;
    private boolean X;
    private IPlayer Y = null;
    private boolean Z = false;
    private boolean aa = false;
    private boolean ab = false;
    private String ac = "";
    private int ad;
    private OperatorFlowEngine ae;
    private Handler af = new u(this);
    private InnerReceiver ag;
    private LoginReceive ah;
    private int ai = 2;
    private RoomRepertoryGiftEngine aj;
    private int ak;
    private int al;
    private RoomUpgradeWindowManager am;
    private ShowGuardPopWindow an;
    private RelativeLayout ao;
    private IMListener ap = new aq(this);
    private String aq = VIDEOTYPE_UNKNOWN;
    private Dialog ar;
    private RoomEventFloatBean as;
    private CommonEventStatusEngine at;
    private CommonEventStatusBean au;
    private CommonEventDialog av;
    LiveFlvManager b = new LiveFlvManager();
    String c;
    private Bitmap d;
    private NetworkReceiver f;
    private boolean g = false;
    private Dialog h;
    private RelativeLayout i;
    private boolean j = false;
    private boolean k = false;
    private String l;
    private RelativeLayout m;
    public ImprovedProgressDialog mProDialog;
    public SpiritSurfaceView mSpiritSurfaceView;
    private ImageView n;
    private LinearLayout o;
    private ProgressBar p;
    private TextView q;
    private String r;
    private String s;
    private String t;
    private Drawable u;
    private RoomBaseFragment v;
    private InroomPresenter w;
    private PropListPresenter x;
    private FullScreenRoomFragment y;
    private ArrayList<RoomBaseFragment> z = new ArrayList();

    public class InnerReceiver extends BroadcastReceiver {
        final /* synthetic */ RoomActivity a;

        public InnerReceiver(RoomActivity roomActivity) {
            this.a = roomActivity;
        }

        public void onReceive(Context context, Intent intent) {
            switch (intent.getIntExtra("flag", 0)) {
                case 103:
                    this.a.D = null;
                    this.a.a(this.a.D);
                    if (this.a.x != null) {
                        this.a.x.clearLocalData();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public class LoginReceive extends BroadcastReceiver {
        final /* synthetic */ RoomActivity a;

        public LoginReceive(RoomActivity roomActivity) {
            this.a = roomActivity;
        }

        public void onReceive(Context context, Intent intent) {
            String str;
            LogUtils.e("LoginReceive", RoomActivity.class.getName());
            if (LoginUtils.getLoginUserBean() == null) {
                str = "";
            } else {
                str = LoginUtils.getLoginUserBean().getId();
            }
            if (this.a.w == null) {
                this.a.f();
            }
            this.a.w.getNetRoomInfo(CommonStrs.ROOMINFOENGINE_PRIV, this.a.r, SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()), str, this.a.s);
            if (!(LoginUtils.getLoginUserBean() == null || this.a.A == null)) {
                String id = LoginUtils.getLoginUserBean().getId();
                this.a.stopChatMsgSocket();
                this.a.createChatMsgSocket(id, SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()), this.a.A.getId());
                this.a.g();
                this.a.h();
            }
            this.a.m();
            Iterator it = this.a.z.iterator();
            while (it.hasNext()) {
                RoomBaseFragment roomBaseFragment = (RoomBaseFragment) it.next();
                if (roomBaseFragment != null) {
                    roomBaseFragment.onRooomActivityResult(CommonInts.USER_MANAGER_REQUEST_CODE, 0, null);
                }
            }
        }
    }

    public class NetworkReceiver extends BroadcastReceiver {
        final /* synthetic */ RoomActivity a;

        public NetworkReceiver(RoomActivity roomActivity) {
            this.a = roomActivity;
        }

        public void onReceive(Context context, Intent intent) {
            if (this.a.g) {
                boolean z;
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.a.getSystemService("connectivity")).getActiveNetworkInfo();
                boolean z2;
                if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                    ToastUtils.showToast(this.a.getString(R.string.app_room_net_warn));
                    z2 = false;
                    z = false;
                } else {
                    z = activeNetworkInfo.getType() == 1;
                    if (activeNetworkInfo.getType() == 0) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                }
                if (z || !r3) {
                    GlobleValue.networkRoomChange = true;
                } else if (GlobleValue.networkRoomChange) {
                    ToastUtils.showToast(this.a.getString(R.string.app_room_net_toast_warn));
                    GlobleValue.networkRoomChange = false;
                } else if (!GlobleValue.networkRoomHint) {
                    ToastUtils.showToast(this.a.getString(R.string.app_room_net_toast_warn));
                    GlobleValue.networkRoomHint = true;
                }
            }
        }
    }

    public enum PlayerState {
        PLAYEND,
        PLAYLONGIND,
        PLAYING
    }

    private void a() {
        this.mDialogUtils = new DialogUtils(this);
    }

    protected void onCreate(Bundle bundle) {
        int i;
        String str;
        super.onCreate(bundle);
        Uri data = getIntent().getData();
        if (data != null) {
            this.O = data.getPath();
        }
        RoomManage.getInstance().addActivity(this);
        requestWindowFeature(1);
        setContentView(R.layout.phone_activity_room);
        this.ao = (RelativeLayout) findViewById(R.id.activity_root_view);
        this.f = new NetworkReceiver(this);
        registerReceiver(this.f, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        this.ag = new InnerReceiver(this);
        registerReceiver(this.ag, new IntentFilter(CustomBroadcast.USER_INFO));
        this.ah = new LoginReceive(this);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.ah, new IntentFilter(CustomBroadcast.COOPLOGIN_LOGIN));
        a();
        if (bundle != null) {
            this.mRoomType = bundle.getInt("Room_Type", 0);
            this.Z = true;
        }
        this.F = (LinearLayout) findViewById(R.id.room_loading_ll);
        if (this.u == null) {
            this.u = getResources().getDrawable(R.drawable.transparent);
        }
        setWindow(WindowColor.TRANSPARENT);
        this.m = (RelativeLayout) findViewById(R.id.player_loading);
        this.n = (ImageView) findViewById(R.id.iv_bg);
        this.o = (LinearLayout) this.m.findViewById(R.id.loading_ll);
        this.p = (ProgressBar) this.m.findViewById(R.id.progressBar);
        this.q = (TextView) this.m.findViewById(R.id.tv_live_over);
        if (this.i == null) {
            this.i = (RelativeLayout) findViewById(R.id.video_layout);
        }
        boolean booleanSettings = SharedPreferencesUtils.getBooleanSettings(this, SharedPreferencesUtils.HARD_CODEC_KEY);
        if (SixPlayer.loadLibrary() && SixPlayer.isSupport() == 1 && !e && booleanSettings) {
            LogUtils.w(TAG, "------硬解---");
            Object fragmentHardwarePlayer = new FragmentHardwarePlayer();
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            beginTransaction.replace(R.id.video_layout, fragmentHardwarePlayer);
            beginTransaction.commitAllowingStateLoss();
            this.Y = fragmentHardwarePlayer;
            i = 1;
        } else {
            i = 0;
        }
        if (((Integer) SharedPreferencesUtils.get(this, SharedPreferencesUtils.VIDEO_DECODE_TYPE, 0, "decode_type", Integer.valueOf(0))).intValue() != i) {
            StatisticManager.getInstance().decodeStatistic(i);
            SharedPreferencesUtils.put(this, SharedPreferencesUtils.VIDEO_DECODE_TYPE, 0, "decode_type", Integer.valueOf(i));
        }
        m();
        if (this.mSpiritSurfaceView == null) {
            this.mSpiritSurfaceView = (SpiritSurfaceView) findViewById(R.id.ssf);
        }
        this.X = ((String) SharedPreferencesUtils.get(GiftConfigUtil.H5_FILE, 0, GiftConfigUtil.H5_KEY, "1")).equals("1");
        if (this.X) {
            this.W = new GiftAnimQueue(new ai(this));
        }
        this.E = (RelativeLayoutGift) findViewById(R.id.gift);
        this.E.setRoomTypeable(this);
        this.typeChangeListeners.add(this.E);
        if (this.X) {
            this.V = (GiftWebview) findViewById(R.id.gift_webview);
            this.V.setCallback(new au(this));
        }
        f();
        this.g = true;
        if (LoginUtils.getLoginUserBean() == null) {
            str = "";
        } else {
            str = LoginUtils.getLoginUserBean().getId();
        }
        Intent intent = getIntent();
        this.r = intent.getStringExtra("rid");
        this.s = intent.getStringExtra(RoomBaseFragment.RUID_KEY);
        Object stringExtra = intent.getStringExtra("hall_flv");
        Object stringExtra2 = intent.getStringExtra("hall_secflv");
        this.t = intent.getStringExtra("video_type");
        if (!TextUtils.isEmpty(this.O)) {
            this.s = this.O.replace("/", "");
            this.O = null;
        }
        if (!(TextUtils.isEmpty(this.t) || TextUtils.isEmpty(this.s) || (TextUtils.isEmpty(stringExtra) && TextUtils.isEmpty(stringExtra2)))) {
            playPrepare();
            this.b.init(stringExtra, stringExtra2);
        }
        this.w.getNetRoomInfo(CommonStrs.ROOMINFOENGINE_COMMON, this.r, SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()), str, this.s);
        if (V6Coop.isGetOperatorFlow && NetWorkUtil.isMobileNetwork(this)) {
            if (this.ae == null) {
                this.ae = new OperatorFlowEngine(new w(this));
            }
            this.ae.getOperatorFlow(LoginUtils.getLoginUID(), SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()));
        }
        GetInfoEngine instance = GetInfoEngine.getInstance();
        instance.addCallback(new v(this));
        instance.getInfo();
        this.N = ((Boolean) SharedPreferencesUtils.get(this, GIFT_ANIM, 0, KEY_ANIM_CLEAN, Boolean.valueOf(false))).booleanValue();
        this.M = (ImageView) findViewById(R.id.iv_gift_clean);
        if (this.N) {
            this.M.setImageResource(R.drawable.room_gift_clean_selector);
        } else {
            this.M.setImageResource(R.drawable.room_special_gift_text_clear);
        }
        this.M.setOnClickListener(this);
        this.U = (AnimSurfaceViewTouch) findViewById(R.id.anim_surface_view);
        this.U.setOnAnimCallback(new ax(this));
        this.J = new AnimViewControl(this.U, new GiftSceneFactory(), new ay(this));
        this.K = new AnimViewControl((AnimSurfaceView) findViewById(R.id.anim_surface_view2), new Builder().setFPS(30).setAnimSceneFactory(new SpecialSceneFactory()).build());
        this.K.addAnimDrawListener(this);
        n();
        if (V6Coop.firstGetGift) {
            ConfigUpdataDispatcher.updateConfig();
        }
        AppCount.sendAppCountInfo(StatisticCodeTable.ROOM);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.iv_gift_clean) {
            if (this.K != null) {
                this.K.resetAnimFrame();
            }
            b();
            if (!this.N) {
                this.N = true;
                this.M.setVisibility(8);
                SharedPreferencesUtils.put(this, GIFT_ANIM, 0, KEY_ANIM_CLEAN, Boolean.valueOf(true));
                this.M.setImageResource(R.drawable.room_gift_clean_selector);
            }
        }
    }

    private void b() {
        if (this.W != null) {
            this.W.clean();
        }
        if (this.V != null) {
            this.V.cleanLoadGiftAnimation();
        }
    }

    public void onDrawState(int i) {
        switch (i) {
            case 1:
                this.M.setVisibility(0);
                return;
            case 2:
            case 3:
                c();
                if (this.W != null) {
                    this.W.completeNative();
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void c() {
        this.M.post(new az(this));
    }

    public void onFragmentCreate() {
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        n();
        LogUtils.d(TAG, "onConfigurationChanged---requesetMode---" + this.I);
        if (this.K != null) {
            this.K.resetAnimFrame();
        }
        if (this.J != null) {
            this.J.resetAnimFrame();
        }
        if (this.av != null) {
            this.av = null;
        }
        if (this.I != -1) {
            b(this.I);
            this.I = -1;
        }
        b();
    }

    private void a(RoomBaseFragment roomBaseFragment) {
        if (this.v == null) {
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            if (!roomBaseFragment.isAdded()) {
                beginTransaction.add(R.id.content_layout, (Fragment) roomBaseFragment);
                this.z.add(roomBaseFragment);
            }
            beginTransaction.show(roomBaseFragment);
            if (this.v != null) {
                beginTransaction.hide(this.v);
            }
            beginTransaction.commitAllowingStateLoss();
            this.v = roomBaseFragment;
            LogUtils.d(TAG, "showFragment---mCurrentFragment--" + this.v);
        }
    }

    private void d() {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        if (this.y != null) {
            beginTransaction.remove(this.y);
            removeChatMsgSocketListener(this.y);
            this.y = null;
        }
        beginTransaction.commitAllowingStateLoss();
        this.z.clear();
        this.v = null;
    }

    public void setGiftOffset(int i) {
        if (this.J != null) {
            this.ad = i;
            this.J.setOffset(0, this.ad);
        }
    }

    private void a(int i) {
        switch (i) {
            case 0:
                getWindow().clearFlags(1024);
                if (this.y == null) {
                    this.y = FullScreenRoomFragment.newInstance(this.r, this.s, 0);
                    addChatMsgSocketListener(this.y);
                    e();
                }
                a(this.y);
                break;
            case 1:
                getWindow().clearFlags(1024);
                if (this.y == null) {
                    this.y = FullScreenRoomFragment.newInstance(this.r, this.s, 1);
                    addChatMsgSocketListener(this.y);
                    e();
                }
                a(this.y);
                break;
            case 2:
                getWindow().addFlags(1024);
                if (this.y == null) {
                    this.y = FullScreenRoomFragment.newInstance(this.r, this.s, 2);
                    addChatMsgSocketListener(this.y);
                    e();
                }
                a(this.y);
                break;
            case 3:
                LogUtils.d(TAG, "mFullScreenFragment---" + this.y);
                getWindow().addFlags(1024);
                if (this.y == null) {
                    this.y = FullScreenRoomFragment.newInstance(this.r, this.s, 3);
                    addChatMsgSocketListener(this.y);
                    e();
                }
                a(this.y);
                break;
            case 4:
                getWindow().clearFlags(1024);
                if (this.y == null) {
                    this.y = FullScreenRoomFragment.newInstance(this.r, this.s, 4);
                    addChatMsgSocketListener(this.y);
                    e();
                }
                a(this.y);
                break;
        }
        a(this.D);
        if (!TextUtils.isEmpty(this.B)) {
            this.v.receiveFansTm(this.B);
        }
        if (this.C.size() != 0) {
            this.v.showSongMenuList(this.C);
        }
        this.v.clearGiftList();
        this.v.onIMMsgNumChange(IMMessageLastManager.getInstance().getNewMsgCount());
    }

    private void e() {
        this.y.setFullPopShowListener(new ba(this));
        this.y.setRoomLiveCallBack(new bb(this));
    }

    public void requestType(int i) {
        if (DisPlayUtil.isLandscape(this)) {
            if (i == 3 || i == 2) {
                a(i);
                b(i);
                return;
            }
            this.I = i;
            setRequestedOrientation(1);
            a(i);
        } else if (i == 3 || i == 2) {
            this.I = i;
            setRequestedOrientation(0);
            a(i);
        } else {
            a(i);
            b(i);
        }
    }

    private void b(int i) {
        this.mRoomType = i;
        c(i);
        LayoutParams layoutParams = (LayoutParams) this.o.getLayoutParams();
        if (this.mRoomType == 2 || this.mRoomType == 3) {
            if (VERSION.SDK_INT > 17) {
                layoutParams.removeRule(13);
            }
            layoutParams.topMargin = DensityUtil.dip2px(200.0f);
            layoutParams.addRule(14);
        } else {
            if (VERSION.SDK_INT > 17) {
                layoutParams.removeRule(14);
            }
            layoutParams.addRule(13);
        }
        for (OnRoomTypeChangeListener onRoomTypeChange : this.typeChangeListeners) {
            onRoomTypeChange.onRoomTypeChange(i);
        }
    }

    private void c(int i) {
        this.ao.post(new bc(this, i));
    }

    private void f() {
        this.w = InroomPresenter.getInstance();
        this.w.registerInroom(this);
        this.w.registerPlayer(this);
        this.w.registerSocket(this);
    }

    private void g() {
        if (LoginUtils.getLoginUserBean() != null) {
            this.aj = new RoomRepertoryGiftEngine(new y(this));
            this.aj.getRepertory(this.A.getId(), LoginUtils.getLoginUID(), SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()));
            return;
        }
        a(null);
    }

    private void h() {
        UserBean loginUserBean = LoginUtils.getLoginUserBean();
        if (loginUserBean != null && this.A != null) {
            if (this.x == null) {
                this.x = PropListPresenter.getInstance();
            }
            this.x.getNetData(loginUserBean.getId(), SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()), this.A.getId());
        }
    }

    private void a(UpdateGiftNumBean updateGiftNumBean) {
        if (this.v != null) {
            this.v.updateGiftNum(updateGiftNumBean);
        }
    }

    public void processGiftNumUpdate(UpdateGiftNumBean updateGiftNumBean) {
        this.D = updateGiftNumBean;
    }

    public void processSongMenuList(List<SubLiveListBean> list) {
        this.C = list;
    }

    public void processChatSocketReconnect() {
        stopChatMsgSocket();
        this.af.post(new z(this));
    }

    public void processSpeakStateChange(AuthKeyBean authKeyBean, boolean z) {
        if (this.ab) {
            sendShareRequest(this.ac);
            this.ab = false;
        }
    }

    public void processSocketGift(Gift gift) {
        if (gift != null && !gift.getGtype().equals("5")) {
            if (gift.isShowCoolEffect() || GiftIdStrs.fireworksIds.contains(gift.getId())) {
                LogUtils.d("base", "handleGift");
                a(gift, false);
                return;
            }
            if (gift.getGtype().equals("1") && PoseConfig.getInstance().getPose(gift.getNum()) != null) {
                LogUtils.d("base", "handleGift--GTYPE_ORDINARY");
                a(gift, true);
            }
            if (gift.getGtype().equals("3") || gift.getGtype().equals("4")) {
                LogUtils.d("base", "handleGift--H5");
                a(gift, false);
            }
            if (this.v.getGiftVisibility() && this.J != null) {
                LogUtils.d("base", "handleGift--addAnimScene");
                this.J.addAnimScene(gift);
            }
        }
    }

    private void a(Gift gift, boolean z) {
        if (this.X) {
            this.W.putGift(gift);
        } else if (z) {
            this.K.addAnimScene(gift, new GiftPoseFactory());
        } else {
            this.K.addAnimScene(gift);
        }
    }

    public void processWelcome(WelcomeBean welcomeBean) {
        super.processWelcome(welcomeBean);
        if (this.L == null) {
            this.L = new SpecialenterFactory();
        }
        if (this.J != null && welcomeBean != null && "1".equals(welcomeBean.getSf())) {
            this.J.addAnimScene(welcomeBean, this.L);
        }
    }

    public void processFansTmChange(String str) {
        this.B = str;
    }

    public void processliveState(LiveStateBean liveStateBean) {
        this.b.init(liveStateBean.getFlvtitle(), liveStateBean.getSecflvtitle());
        String content = liveStateBean.getContent();
        if ("0".equals(content)) {
            this.af.sendEmptyMessage(14);
            playOver();
        } else if (Constants.VIA_REPORT_TYPE_SHARE_TO_QQ.equals(content)) {
            Message obtain = Message.obtain();
            obtain.obj = liveStateBean;
            obtain.what = 22;
            this.af.sendMessage(obtain);
        }
    }

    public void processSocketFlyText(FlyTextBean flyTextBean) {
        if (this.mSpiritSurfaceView != null) {
            this.mSpiritSurfaceView.addSpirit(new FlyTextSpirit(this, flyTextBean.from + "说：" + flyTextBean.content));
        }
    }

    public void chatChange() {
        if (this.v != null && (this.v instanceof RoomInputDialogListener)) {
            this.v.chatChange();
        }
    }

    public void showSpeakOverquick() {
        if (this.v != null && (this.v instanceof RoomInputDialogListener)) {
            this.v.showSpeakOverquick();
        }
    }

    public void showChatLengthy() {
        if (this.v != null && (this.v instanceof RoomInputDialogListener)) {
            this.v.showChatLengthy();
        }
    }

    public void refreshChat() {
        if (this.v != null && (this.v instanceof RoomInputDialogListener)) {
            this.v.refreshChat();
        }
    }

    public List<UserInfoBean> initChatListData() {
        if (this.v == null || !(this.v instanceof RoomInputDialogListener)) {
            return null;
        }
        return this.v.initChatListData();
    }

    public void showOpenGuardPage() {
        if (this.v != null) {
            this.v.showOpenGuardPage();
        }
    }

    public void processAnchorPrompt(AnchorPrompt anchorPrompt) {
    }

    protected void onStart() {
        super.onStart();
        if (this.Z) {
            requestType(this.mRoomType);
        }
        this.af.postDelayed(new aa(this), 3000);
    }

    protected void onResume() {
        super.onResume();
        if (this.mSpiritSurfaceView != null) {
            this.mSpiritSurfaceView.onResume();
        }
        if (this.m != null) {
            this.m.setVisibility(0);
        }
        playStart();
        this.g = true;
    }

    protected void onPause() {
        super.onPause();
        if (this.mSpiritSurfaceView != null) {
            this.mSpiritSurfaceView.onPause();
        }
        playPause();
        this.g = false;
    }

    protected void onStop() {
        i();
        b();
        stopChatMsgSocket();
        super.onStop();
        StatisticManager.getInstance().stopWatchTime();
    }

    protected void onRestart() {
        super.onRestart();
        processChatSocketReconnect();
        m();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("Room_Type", this.mRoomType);
    }

    private void i() {
        if (this.an != null && this.an.isShowing()) {
            this.an.onDesdory();
            this.an = null;
        }
    }

    private void j() {
        if (this.am != null) {
            this.am.release();
            this.am = null;
        }
    }

    public void finish() {
        this.k = false;
        this.Y.release();
        if (FollowPresenter.getInstance().getIsChange()) {
            LogUtils.d(TAG, "finish1111");
            Intent intent = new Intent();
            intent.setAction(CustomBroadcast.ATTENTION_STATUS_CHANAGED);
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
            setResult(1);
        } else {
            LogUtils.d(TAG, "finish0000");
            setResult(0);
        }
        super.finish();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.J != null) {
            this.J.release();
        }
        if (this.K != null) {
            this.K.release();
        }
        RoomManage.getInstance().exit();
        k();
        this.g = false;
        playOver();
        if (this.f != null) {
            unregisterReceiver(this.f);
            this.f = null;
        }
        if (this.ag != null) {
            unregisterReceiver(this.ag);
            this.ag = null;
        }
        if (this.ah != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(this.ah);
            this.ah = null;
        }
        this.af.removeCallbacksAndMessages(null);
        V6Coop.flag = false;
        this.u.setCallback(null);
        this.w.onDestroy();
        this.w = null;
        if (this.x != null) {
            this.x.onDestroy();
            this.x = null;
        }
        j();
        if (IMMsgSocket.getInstanceForStop() != null) {
            IMMsgSocket.getInstanceForStop().removeImListener(this.ap);
        }
        if (this.h != null) {
            this.h.dismiss();
        }
        clearPlayerViewStateListeners();
        if (this.d != null) {
            this.d.recycle();
            this.d = null;
        }
        if (this.W != null) {
            this.W.onDesdory();
        }
        if (this.V != null) {
            this.V.uploadService();
            this.V.setCallback(null);
            this.V = null;
        }
        GetInfoEngine.getInstance().removeCallback();
        StatisticValue.getInstance().clearWatchid();
    }

    public void resetData(String str, String str2) {
        this.r = str;
        this.s = str2;
        this.aq = VIDEOTYPE_UNKNOWN;
        this.t = "";
        this.b.init("", "");
        this.g = true;
        this.A = null;
        this.au = null;
        this.as = null;
        this.mBasePublicChatItem.clear();
        this.mBasePrivateChatItem.clear();
        this.mRoomType = 0;
        this.mLiveType = "";
        this.U.cleanData();
        this.af.post(new ab(this));
        k();
        this.mSpiritSurfaceView.clearSapirit();
        i();
        j();
        this.E.closeAllAnimation();
        if (this.J != null) {
            this.J.resetAnimFrame();
        }
        if (this.K != null) {
            this.K.resetAnimFrame();
        }
        playOver();
        stopChatMsgSocket();
        d();
        if (this.av != null) {
            this.av = null;
        }
        this.w.getNetRoomInfo(CommonStrs.ROOMINFOENGINE_COMMON, str, SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()), LoginUtils.getLoginUserBean() == null ? "" : LoginUtils.getLoginUserBean().getId(), str2);
    }

    private void k() {
        if (InroomPresenter.getInstance().getLocalRoomInfo() == null) {
            return;
        }
        if (!(this.v instanceof IRoomHistoryContral) || this.v.addHistory()) {
            RoominfoBean roominfoBean = InroomPresenter.getInstance().getLocalRoomInfo().getRoominfoBean();
            HistroyWatch histroyWatch = new HistroyWatch(null, roominfoBean.getRid(), roominfoBean.getUoption().getPicuser(), roominfoBean.getAlias(), roominfoBean.getWealthrank(), System.currentTimeMillis(), roominfoBean.getId());
            HistoryDbTool.delete(this, histroyWatch.getRid());
            HistoryDbTool.add(this, histroyWatch);
        }
    }

    public void setWindow(WindowColor windowColor) {
        super.setWindow(windowColor);
        switch (aw.a[windowColor.ordinal()]) {
            case 1:
                WindowManager.LayoutParams attributes = getWindow().getAttributes();
                attributes.alpha = 1.0f;
                getWindow().setAttributes(attributes);
                getWindow().setBackgroundDrawableResource(R.drawable.window_bg_white);
                return;
            case 2:
                getWindow().setBackgroundDrawable(this.u);
                return;
            default:
                return;
        }
    }

    public void error(int i) {
        showErrorToast(i);
        finish();
    }

    public void handlerError(String str, String str2) {
        showToast(str2);
        finish();
    }

    public void onBackPressed() {
        if (this.v == null || !(this.v instanceof IOnBackPressedListener)) {
            finish();
        } else {
            this.v.onBackPressed();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.v != null && (this.v instanceof IOnKeyDownListener) && this.v.onKeyDown(i, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    private int l() {
        Rect rect = new Rect();
        if (this.ao == null) {
            this.ao = (RelativeLayout) findViewById(R.id.activity_root_view);
        }
        this.ao.getWindowVisibleDisplayFrame(rect);
        return (rect.bottom - rect.top) + 0;
    }

    public void reSetPlayerHeight() {
        c(4);
    }

    public int getChatHeight(int i) {
        switch (i) {
            case 0:
                return (l() - DisPlayUtil.getPCPlayerHeight(this)) - ((int) getResources().getDimension(R.dimen.room_video_margin_top));
            case 1:
                return (l() - (DensityUtil.getScreenWidth() / 2)) - ((int) getResources().getDimension(R.dimen.room_video_margin_top));
            case 2:
            case 3:
                return (int) getResources().getDimension(R.dimen.room_public_chat_height_lan);
            case 4:
                return (int) getResources().getDimension(R.dimen.room_public_chat_height_por);
            default:
                return 0;
        }
    }

    public int getChatHeight(int i, int i2) {
        switch (i) {
            case 0:
                return ((l() - DisPlayUtil.getPCPlayerHeight(this)) - ((int) getResources().getDimension(R.dimen.room_video_margin_top))) + i2;
            case 1:
                return (l() - (DensityUtil.getScreenWidth() / 2)) - ((int) getResources().getDimension(R.dimen.room_video_margin_top));
            case 2:
            case 3:
                return (int) getResources().getDimension(R.dimen.room_public_chat_height_lan);
            case 4:
                return (int) getResources().getDimension(R.dimen.room_public_chat_height_por);
            default:
                return 0;
        }
    }

    public void setWrapRoomInfo(WrapRoomInfo wrapRoomInfo) {
        String videotype;
        CharSequence largepic;
        String str = null;
        this.as = wrapRoomInfo.getEventFloat();
        this.A = wrapRoomInfo.getRoominfoBean();
        this.mBasePublicChatItem = wrapRoomInfo.getPublicRoommsgBeans();
        this.mBasePrivateChatItem = wrapRoomInfo.getPrivateRoommsgBeans();
        this.s = wrapRoomInfo.getRoominfoBean().getId();
        LiveinfoBean liveinfoBean = wrapRoomInfo.getLiveinfoBean();
        this.b.init(liveinfoBean.getFlvtitle(), liveinfoBean.getSecflvtitle());
        g();
        h();
        RoominfoBean roominfoBean = wrapRoomInfo.getRoominfoBean();
        this.mLiveType = wrapRoomInfo.getRoominfoBean().getUoption().getLivetype();
        String str2 = "1";
        if (liveinfoBean != null) {
            videotype = liveinfoBean.getVideotype();
            largepic = liveinfoBean.getLargepic();
            if ("1".equals(videotype)) {
                this.ak = getChatHeight(2);
                this.al = getChatHeight(0);
            } else if ("6".equals(videotype)) {
                this.ak = getChatHeight(3);
                this.al = getChatHeight(3);
            } else if ("5".equals(videotype)) {
                this.ak = getChatHeight(4);
                this.al = getChatHeight(4);
            }
        } else {
            videotype = str2;
            largepic = null;
        }
        if (roominfoBean != null) {
            str = roominfoBean.getUoption().getPicuser();
        }
        if (!videotype.equals(this.aq)) {
            if (!VIDEOTYPE_UNKNOWN.equals(this.aq)) {
                d();
            }
            a(videotype);
            if (!TextUtils.isEmpty(largepic)) {
                CharSequence charSequence = largepic;
            }
            FrescoUtil.asyncGetBitmap(str, new ac(this));
            LogUtils.d(TAG, "doInBackground----end0000----" + System.currentTimeMillis());
        }
    }

    private void a(String str) {
        if (TextUtils.isEmpty(str)) {
            str = VIDEOTYPE_UNKNOWN;
        }
        this.aq = str;
        int i = -1;
        switch (str.hashCode()) {
            case 49:
                if (str.equals("1")) {
                    i = 2;
                    break;
                }
                break;
            case 53:
                if (str.equals("5")) {
                    i = 0;
                    break;
                }
                break;
            case 54:
                if (str.equals("6")) {
                    i = 1;
                    break;
                }
                break;
        }
        switch (i) {
            case 0:
                requestType(4);
                return;
            case 1:
                requestType(3);
                return;
            default:
                if (CheckRoomTypeUtils.isFamilyRoomType(this.s)) {
                    requestType(1);
                    return;
                } else {
                    requestType(0);
                    return;
                }
        }
    }

    public void setPriv(String str) {
        InroomPresenter.getInstance().getLocalRoomInfo().setIsUserSafe(str);
    }

    public void createSocket(WrapRoomInfo wrapRoomInfo) {
        String id;
        String str = "";
        if (LoginUtils.getLoginUserBean() != null) {
            id = LoginUtils.getLoginUserBean().getId();
            str = SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext());
        } else {
            id = SaveUserInfoUtils.getVisitorId(V6Coop.getInstance().getContext());
        }
        stopChatMsgSocket();
        if (wrapRoomInfo == null || wrapRoomInfo.getRoominfoBean() == null) {
            finish();
        } else {
            RoominfoBean roominfoBean = wrapRoomInfo.getRoominfoBean();
            if (roominfoBean != null) {
                createChatMsgSocket(id, str, roominfoBean.getId());
            } else {
                finish();
            }
        }
        LogUtils.d(TAG, "createSocket---mCurrentFragment--" + this.v);
    }

    public void processSocketListenerSet() {
        LogUtils.d("BaseRoomActivity", "processSocketListenerSet--");
        setCommonEventListener(new af(this));
        setChangzhanListener(new ag(this));
        setBoxingListener(new ah(this));
        setPigPkYellowDuckListener(new aj(this));
        setHeadLineListener(new ak(this));
        setSuperGMsgListener(new al(this));
        setMiniGameListener(new am(this));
    }

    public void setRtmpURL(String str) {
        String currentFlv = this.b.getCurrentFlv(this);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(currentFlv)) {
            playerviewFinished();
            playOver();
            return;
        }
        this.c = str;
        b(currentFlv);
        this.Y.setPlayerParameter(currentFlv, str);
    }

    private void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            String str2 = "rtmp://" + this.c + "/liverepeater/" + str;
            if (!str2.equals(this.l)) {
                if (!TextUtils.isEmpty(this.l)) {
                    this.Y.release();
                }
                this.l = str2;
                this.k = false;
                this.j = false;
                playStart();
            }
        }
    }

    public void dismissDialogs() {
        a(this.loginDialog);
        a(this.h);
    }

    private static void a(Dialog dialog) {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public void showEnterRoomDialog(String str) {
        showEnterRoomDialog(str, "");
    }

    public void showEnterRoomDialog(String str, String str2) {
        this.mDialogUtils.createConfirmDialog(9288, "要进入该房间吗?", new an(this, str, str2)).show();
    }

    public void playPrepare() {
        if (this.w != null) {
            this.w.getRtmp(this.s);
        }
    }

    public void playStart() {
        if (isFinishing()) {
            this.j = true;
        } else if (!this.j && !this.k && !TextUtils.isEmpty(this.l)) {
            this.k = true;
            this.Y.play(this.l);
            playerviewLoading();
        }
    }

    public void playPause() {
        this.k = false;
    }

    public void playOver() {
        this.l = null;
        this.k = false;
        this.Y.release();
    }

    public void playing() {
    }

    public void playerviewPlaying() {
        if (VIDEOTYPE_UNKNOWN.equals(this.aq)) {
            this.aq = this.t;
            a(this.aq);
        }
        this.H = PlayerState.PLAYING;
        if (this.G != null) {
            for (IRoomPlayerViewStateListener playerviewPlaying : this.G) {
                playerviewPlaying.playerviewPlaying();
            }
        }
        this.m.setVisibility(8);
        this.F.setVisibility(8);
    }

    public void playerviewLoading() {
        this.m.setVisibility(0);
        this.p.setVisibility(0);
        this.q.setVisibility(8);
        this.H = PlayerState.PLAYLONGIND;
        if (this.G != null) {
            for (IRoomPlayerViewStateListener playerviewLoading : this.G) {
                playerviewLoading.playerviewLoading();
            }
        }
    }

    public void playerviewFinished() {
        this.m.setVisibility(0);
        this.p.setVisibility(8);
        this.q.setVisibility(0);
        this.q.setText(getText(R.string.live_over));
        this.H = PlayerState.PLAYEND;
        if (this.G != null) {
            for (IRoomPlayerViewStateListener playerviewFinished : this.G) {
                playerviewFinished.playerviewFinished();
            }
        }
    }

    public PlayerState getCurPlayerState() {
        return this.H;
    }

    public boolean isPlayPause() {
        return !this.k;
    }

    public boolean isPlayOver() {
        return this.j;
    }

    public void processSocketRed(RoommsgBean roommsgBean, boolean z) {
        if (!this.aa) {
            InterfaceSurfaceAnimation interfaceSurfaceAnimation = this.E.getInterfaceSurfaceAnimation();
            if (interfaceSurfaceAnimation != null) {
                interfaceSurfaceAnimation.addRedPackage();
            }
        }
    }

    public int getRoomType() {
        return this.mRoomType;
    }

    public void stopMessage(String str) {
        super.stopMessage(str);
        this.chatMsgSocket.stopMessage(str, this.r);
    }

    public void addManager(String str) {
        super.addManager(str);
        this.chatMsgSocket.addManager(str, this.r);
    }

    public void revokeManager(String str) {
        super.revokeManager(str);
        this.chatMsgSocket.revokeManager(str, this.r);
    }

    public void revokeAdmin(String str) {
        super.revokeAdmin(str);
        this.chatMsgSocket.revokeAdmin(str, this.r);
    }

    public void addAdmin(String str) {
        super.addAdmin(str);
        this.chatMsgSocket.addAdmin(str, this.r);
    }

    public void recoverMessage(String str) {
        super.recoverMessage(str);
        this.chatMsgSocket.recoverMessage(str, this.r);
    }

    public void kickRoom(String str) {
        super.kickRoom(str);
        this.chatMsgSocket.kickRoom(str, this.r);
    }

    public void processUpgradeMessage(RoomUpgradeMsg roomUpgradeMsg) {
        super.processUpgradeMessage(roomUpgradeMsg);
        this.af.post(new ao(this, roomUpgradeMsg));
    }

    public void showOpenGuardianAnimation(GuardStausBean guardStausBean) {
        if (this.ao == null) {
            this.ao = (RelativeLayout) findViewById(R.id.activity_root_view);
        }
        this.af.post(new ap(this, guardStausBean));
    }

    private void m() {
        if (LoginUtils.getLoginUserBean() != null) {
            try {
                IMMsgSocket.createInstance(LoginUtils.getLoginUserBean().getId(), SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext())).setImListener(this.ap);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    public void addPlayerViewStateListener(IRoomPlayerViewStateListener iRoomPlayerViewStateListener) {
        this.G.add(iRoomPlayerViewStateListener);
    }

    public void removePlayerViewStateListener(IRoomPlayerViewStateListener iRoomPlayerViewStateListener) {
        this.G.remove(iRoomPlayerViewStateListener);
    }

    public void clearPlayerViewStateListeners() {
        this.G.clear();
    }

    public void shownNotificationDialog(String str, String str2, String str3, String str4, boolean z) {
        if (LoginUtils.isLogin()) {
            if (this.mDialogUtils == null) {
                a();
            }
            this.ar = this.mDialogUtils.createConfirmDialog(1, str, str2, str3, str4, new as(this));
            if (!this.ar.isShowing()) {
                this.ar.show();
                return;
            }
            return;
        }
        showLoginDialog();
    }

    public void hintProDialog() {
        if (this.mProDialog != null && this.mProDialog.isShowing()) {
            this.mProDialog.dismiss();
        }
    }

    public void doClickCommonEventFloat() {
        if (this.as != null) {
            if ("0".equals(this.as.getStatus())) {
                startEventActivity(this.as.getSignurl(), "");
                return;
            }
            if (this.at == null) {
                this.at = new CommonEventStatusEngine(new at(this));
            }
            this.at.getCommonEventStatus(this.A.getId(), this.as.getEid(), LoginUtils.getLoginUID(), SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()));
        }
    }

    protected void updateFreeVoteNum(String str) {
        if (this.av != null) {
            this.av.updateFreeVoteNum(str);
        }
    }

    private void n() {
        if (this.M != null) {
            ViewGroup.LayoutParams layoutParams;
            if (getResources().getConfiguration().orientation == 2) {
                layoutParams = new LayoutParams(-2, -2);
                layoutParams.addRule(11);
                layoutParams.rightMargin = getResources().getDimensionPixelSize(R.dimen.room_o_margin_right);
                layoutParams.topMargin = getResources().getDimensionPixelSize(R.dimen.room_o_margin_top);
                this.M.setLayoutParams(layoutParams);
                return;
            }
            layoutParams = new LayoutParams(-2, -2);
            layoutParams.addRule(11);
            layoutParams.rightMargin = getResources().getDimensionPixelSize(R.dimen.room_p_margin_right);
            layoutParams.topMargin = getResources().getDimensionPixelSize(R.dimen.room_p_margin_top);
            this.M.setLayoutParams(layoutParams);
        }
    }

    public void OnKeyBoardChange(boolean z, int i) {
        super.OnKeyBoardChange(z, i);
        if (!z) {
            this.J.setOffset(0, this.ad);
        } else if (this.isChatQuietly) {
            this.J.setOffset(0, (-i) + this.ad);
        } else {
            this.J.setOffset(0, ((-i) + getResources().getDimensionPixelSize(R.dimen.room_bottom_height)) + this.ad);
        }
    }

    public void showPrivateChatView(UserInfoBean userInfoBean) {
        super.showPrivateChatView(userInfoBean);
        if (this.v != null) {
            this.v.showPrivateChatView(userInfoBean);
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    public void showPublicChatView(UserInfoBean userInfoBean) {
        super.showPublicChatView(userInfoBean);
        if (this.v != null) {
            this.v.showPublicChatView(userInfoBean);
        }
    }

    public void hidePublicChatView() {
    }

    public void processBecomeGod(BecomeGodBean becomeGodBean) {
        if (this.P == null) {
            this.P = new BecomeGodSceneFactory();
        }
        this.J.addAnimScene(becomeGodBean, this.P);
    }

    public void processSocketSmallFlyText(SmallFlyTextBean smallFlyTextBean) {
        if (this.Q == null) {
            this.Q = new SmaillFlyScreenSceneFactory();
        }
        this.J.addAnimScene(smallFlyTextBean, this.Q);
    }

    public void processSuperFireworks(SuperFireworksBean superFireworksBean) {
        if (this.R == null) {
            this.R = new SuperFireworksSceneFactory();
        }
        this.J.addAnimScene(superFireworksBean, this.R);
    }

    public void processPublicNotice(PublicNoticeBean publicNoticeBean) {
        if (this.S == null) {
            this.S = new ConfessionSceneFactory();
        }
        this.J.addAnimScene(publicNoticeBean, this.S);
    }

    public void processBroadcast(BroadcastBean broadcastBean) {
        if (this.T == null) {
            this.T = new NotificationSceneFactory();
        }
        this.J.addAnimScene(broadcastBean, this.T);
    }

    static /* synthetic */ void a(RoomActivity roomActivity, Object obj, int i) {
        if (roomActivity.v != null) {
            roomActivity.v.sendSocketMessage(obj, i);
        }
    }

    static /* synthetic */ void M(RoomActivity roomActivity) {
        if (roomActivity.av == null) {
            roomActivity.eid = roomActivity.as.getEid();
            roomActivity.av = new CommonEventDialog(roomActivity, roomActivity.as, roomActivity.au, new av(roomActivity));
        }
        roomActivity.av.setmEventStatusbean(roomActivity.au);
        roomActivity.av.show();
    }
}
