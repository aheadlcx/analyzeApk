package cn.v6.sixrooms.room.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.AuthKeyBean;
import cn.v6.sixrooms.bean.ChatPermissionBean;
import cn.v6.sixrooms.bean.FlyTextBean;
import cn.v6.sixrooms.bean.GiftListBean;
import cn.v6.sixrooms.bean.LiveStateBean;
import cn.v6.sixrooms.bean.NoticeTmBean;
import cn.v6.sixrooms.bean.RedBean;
import cn.v6.sixrooms.bean.RepertoryBean;
import cn.v6.sixrooms.bean.RoomEventFloatBean;
import cn.v6.sixrooms.bean.RoomEventFloatTwoBean;
import cn.v6.sixrooms.bean.RoominfoBean;
import cn.v6.sixrooms.bean.RoommsgBean;
import cn.v6.sixrooms.bean.SofaBean;
import cn.v6.sixrooms.bean.SubLiveListBean;
import cn.v6.sixrooms.bean.UpdateCoinWealthBean;
import cn.v6.sixrooms.bean.UpdateGiftNumBean;
import cn.v6.sixrooms.bean.UserInfoBean;
import cn.v6.sixrooms.bean.WrapRoomInfo;
import cn.v6.sixrooms.bean.WrapUserInfo;
import cn.v6.sixrooms.constants.CommonInts;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.event.EventManager;
import cn.v6.sixrooms.event.EventObserver;
import cn.v6.sixrooms.event.GiftBoxEvent;
import cn.v6.sixrooms.event.LoginEvent;
import cn.v6.sixrooms.listener.OnScrollToBottomListener;
import cn.v6.sixrooms.mvp.interfaces.PrivateChatrable;
import cn.v6.sixrooms.presenter.FollowPresenter;
import cn.v6.sixrooms.presenter.PropListPresenter;
import cn.v6.sixrooms.presenter.RedPresenter;
import cn.v6.sixrooms.presenter.SpectatorsPresenter;
import cn.v6.sixrooms.room.BaseRoomActivity;
import cn.v6.sixrooms.room.InitHeadLineBean;
import cn.v6.sixrooms.room.RoomActivity;
import cn.v6.sixrooms.room.RoomActivity.PlayerState;
import cn.v6.sixrooms.room.SmallFlyTextBean;
import cn.v6.sixrooms.room.StarlightCount;
import cn.v6.sixrooms.room.bean.OnHeadlineBeans;
import cn.v6.sixrooms.room.dialog.FansDialog;
import cn.v6.sixrooms.room.dialog.HeadLineDialog;
import cn.v6.sixrooms.room.dialog.InputSongDialog;
import cn.v6.sixrooms.room.dialog.MoreDialog;
import cn.v6.sixrooms.room.dialog.MoreDialog.MoreItemClickListener;
import cn.v6.sixrooms.room.dialog.SongDialog;
import cn.v6.sixrooms.room.dialog.SongDialog.SongOnClick;
import cn.v6.sixrooms.room.dialog.SpectatorsDialog;
import cn.v6.sixrooms.room.game.MiniGameBean;
import cn.v6.sixrooms.room.game.PigPkYellowDuckBean;
import cn.v6.sixrooms.room.gift.Gift;
import cn.v6.sixrooms.room.gift.GiftBoxDialog;
import cn.v6.sixrooms.room.gift.InitTopGiftBean;
import cn.v6.sixrooms.room.gift.ReadGiftEngine;
import cn.v6.sixrooms.room.interfaces.LaunchNotificationViewable;
import cn.v6.sixrooms.room.interfaces.RoomLiveCallBack;
import cn.v6.sixrooms.room.presenter.FansPresenter;
import cn.v6.sixrooms.room.presenter.HeadLinePresenter;
import cn.v6.sixrooms.room.presenter.InroomPresenter;
import cn.v6.sixrooms.room.presenter.InroomPresenter.Inroomable;
import cn.v6.sixrooms.room.presenter.PrivateChatPresenter;
import cn.v6.sixrooms.room.sofa.SofaPresenter;
import cn.v6.sixrooms.room.statistic.StatiscEvent;
import cn.v6.sixrooms.room.statistic.StatisticCodeTable;
import cn.v6.sixrooms.room.statistic.StatisticManager;
import cn.v6.sixrooms.room.statistic.StatisticValue;
import cn.v6.sixrooms.room.utils.SuperGirlSocketFilter;
import cn.v6.sixrooms.room.view.CustomSofaView;
import cn.v6.sixrooms.room.view.CustomSofaView$OnSeatClickListener;
import cn.v6.sixrooms.room.view.InterceptRelativeLayout;
import cn.v6.sixrooms.room.view.MiniGameWebview;
import cn.v6.sixrooms.room.view.PigPkDuckView;
import cn.v6.sixrooms.socket.common.SocketUtil;
import cn.v6.sixrooms.surfaceanim.util.FrescoUtil;
import cn.v6.sixrooms.ui.phone.input.BaseRoomInputDialog;
import cn.v6.sixrooms.ui.phone.input.BaseRoomInputDialog.OnKeyBoardLister;
import cn.v6.sixrooms.ui.phone.input.PrivateInputDialog;
import cn.v6.sixrooms.ui.phone.input.RoomFullInputDialog;
import cn.v6.sixrooms.utils.ChatStyleUtils;
import cn.v6.sixrooms.utils.CheckRoomTypeUtils;
import cn.v6.sixrooms.utils.DensityUtil;
import cn.v6.sixrooms.utils.DialogUtils;
import cn.v6.sixrooms.utils.DisPlayUtil;
import cn.v6.sixrooms.utils.FastDoubleClickUtil;
import cn.v6.sixrooms.utils.HandleErrorUtils;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import cn.v6.sixrooms.utils.ShareTextUtil;
import cn.v6.sixrooms.utils.SharedPreferencesUtils;
import cn.v6.sixrooms.utils.ToastUtils;
import cn.v6.sixrooms.view.interfaces.FollowViewable;
import cn.v6.sixrooms.view.interfaces.IRoomPlayerViewStateListener;
import cn.v6.sixrooms.view.interfaces.ProplistViewable;
import cn.v6.sixrooms.view.interfaces.RedViewable;
import cn.v6.sixrooms.view.interfaces.UpdateSpectatorsNumable;
import cn.v6.sixrooms.widgets.phone.FullScreenChatPage;
import cn.v6.sixrooms.widgets.phone.FullScreenChatPage$OnChatPageScrollListener;
import cn.v6.sixrooms.widgets.phone.FullScreenOpenGuardDialog;
import cn.v6.sixrooms.widgets.phone.WatchRoomUserInfoDialog;
import com.alibaba.wireless.security.SecExceptionCode;
import com.facebook.drawee.view.SimpleDraweeView;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FullScreenRoomFragment extends RoomBaseFragment implements OnClickListener, OnScrollToBottomListener, PrivateChatrable, LaunchNotificationViewable, Inroomable, CustomSofaView$OnSeatClickListener, FollowViewable, IRoomPlayerViewStateListener, ProplistViewable, RedViewable, UpdateSpectatorsNumable, FullScreenChatPage$OnChatPageScrollListener {
    private static final String b = FullScreenRoomFragment.class.getSimpleName();
    private int A;
    private int B;
    private ArrayList<RoommsgBean> C = new ArrayList();
    private ArrayList<RoommsgBean> D = new ArrayList();
    private boolean E;
    private boolean F;
    private boolean G;
    private boolean H = false;
    private LinearLayout I;
    @SuppressLint({"HandlerLeak"})
    private Handler J = new a(this);
    private View K;
    private GiftBoxDialog L;
    private TextView M;
    private TextView N;
    private ImageView O;
    private View P;
    private TextView Q;
    private ImageView R;
    private ImageView S;
    private TextView T;
    private RelativeLayout U;
    private View V;
    private ArrayList<RepertoryBean> W = new ArrayList();
    private RedPresenter X;
    private FollowPresenter Y;
    private PropListPresenter Z;
    List<Gift> a;
    private RelativeLayout aA;
    private PigPkDuckView aB;
    private CustomSofaView aC;
    private MiniGameWebview aD;
    private MiniGameBean aE;
    private RelativeLayout aF;
    private ImageView aG;
    private ImageView aH;
    private boolean aI = false;
    private boolean aJ = false;
    private boolean aK = false;
    private boolean aL = false;
    private int aM;
    private int aN;
    private boolean aO = false;
    private boolean aP = false;
    private SongOnClick aQ = new y(this);
    private MoreItemClickListener aR = new aa(this);
    private FullPopShowListener aS;
    private RoomLiveCallBack aT;
    private FrameLayout aa;
    private RelativeLayout ab;
    private ImageView ac;
    private ImageView ad;
    private SimpleDraweeView ae;
    private ImageView af;
    private TextView ag;
    private RelativeLayout ah;
    private ImageView ai;
    private ImageView aj;
    private PrivateChatPresenter ak;
    private EventObserver al;
    private EventObserver am;
    private boolean an;
    private RelativeLayout ao;
    private View ap;
    private SofaPresenter aq;
    private UserInfoBean ar;
    private FrameLayout as;
    private SimpleDraweeView at;
    private NumberFormat au;
    private RelativeLayout av;
    private SimpleDraweeView aw;
    private SimpleDraweeView ax;
    private View ay;
    private RelativeLayout az = null;
    private PlayerState c = PlayerState.PLAYLONGIND;
    private boolean d = false;
    private RoomActivity e;
    private WrapRoomInfo f;
    private BaseRoomInputDialog g;
    private BaseRoomInputDialog h;
    private BaseRoomInputDialog i;
    private OnKeyBoardLister j;
    private DialogUtils k;
    private Dialog l;
    private Dialog m;
    public FullScreenChatPage mPublicChatPage;
    public List<SubLiveListBean> menuListBean = new ArrayList();
    private FansDialog n;
    private MoreDialog o;
    private boolean p = true;
    private SpectatorsDialog q;
    public List<SubLiveListBean> queueListBean = new ArrayList();
    private FullScreenOpenGuardDialog r;
    private SongDialog s;
    private InputSongDialog t;
    private InitTopGiftBean u;
    private HeadLineDialog v;
    private boolean w = false;
    private String x;
    private String y;
    private volatile boolean z;

    public interface FullPopShowListener {
        void isShow(boolean z);
    }

    static /* synthetic */ void C(FullScreenRoomFragment fullScreenRoomFragment) {
        if (fullScreenRoomFragment.mRoomType == 3 || fullScreenRoomFragment.mRoomType == 2) {
            fullScreenRoomFragment.aa.setVisibility(0);
            fullScreenRoomFragment.ab.setVisibility(0);
            fullScreenRoomFragment.ad.setVisibility(0);
            fullScreenRoomFragment.S.setVisibility(0);
            fullScreenRoomFragment.aC.setVisibility(0);
            if (fullScreenRoomFragment.aB != null) {
                fullScreenRoomFragment.aB.setVisibility(0);
            }
            if (!fullScreenRoomFragment.g()) {
                fullScreenRoomFragment.showRed();
                fullScreenRoomFragment.aj.setVisibility(4);
                fullScreenRoomFragment.V.setVisibility(0);
            }
            if (fullScreenRoomFragment.f.getEventFloat() != null) {
                fullScreenRoomFragment.aw.setVisibility(0);
            }
            if (fullScreenRoomFragment.f.getEventFloatTwo() != null) {
                fullScreenRoomFragment.ax.setVisibility(8);
            }
        } else if (fullScreenRoomFragment.mRoomType == 4) {
            fullScreenRoomFragment.b();
        }
    }

    static /* synthetic */ void ac(FullScreenRoomFragment fullScreenRoomFragment) {
        LayoutParams layoutParams = (LayoutParams) fullScreenRoomFragment.aA.getLayoutParams();
        if (fullScreenRoomFragment.aI) {
            layoutParams.topMargin = (((DisPlayUtil.getPCPlayerHeight(fullScreenRoomFragment.e) - DensityUtil.dip2px(134.0f)) - fullScreenRoomFragment.aD.getLayoutParams().height) + fullScreenRoomFragment.e.getChatHeight(fullScreenRoomFragment.mRoomType)) - DensityUtil.dip2px(200.0f);
        } else {
            layoutParams.topMargin = DisPlayUtil.getPCPlayerHeight(fullScreenRoomFragment.e) - DensityUtil.dip2px(134.0f);
        }
        layoutParams.rightMargin = DensityUtil.dip2px(5.0f);
        layoutParams.addRule(11, -1);
        layoutParams.addRule(3, fullScreenRoomFragment.ao.getId());
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.x = arguments.getString("rid");
            this.y = arguments.getString(RoomBaseFragment.RUID_KEY);
            this.mRoomType = arguments.getInt(RoomBaseFragment.FRAGMENT_TYPE_KEY);
        }
        this.f = InroomPresenter.getInstance().getLocalRoomInfo();
        if (this.f != null) {
            this.mLiveType = this.f.getRoominfoBean().getUoption().getLivetype();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.K = layoutInflater.inflate(R.layout.phone_fragment_full_screen_room, viewGroup, false);
        return this.K;
    }

    public void onActivityCreated(Bundle bundle) {
        this.e = (RoomActivity) getActivity();
        this.aq = new SofaPresenter(this.e);
        if (V6Coop.getInstance().isShowMiniGame()) {
            this.aL = "1".equals(SharedPreferencesUtils.get(0, SharedPreferencesUtils.MINI_GAME_SWITCH, "0"));
        } else {
            this.aL = false;
        }
        if (CheckRoomTypeUtils.isFamilyRoomType(this.y)) {
            this.e.mRoomType = 1;
        }
        if (!(this.x == null || LoginUtils.getLoginUserBean() == null || !this.x.equals(LoginUtils.getLoginUserBean().getRid()))) {
            this.z = true;
        }
        this.aF = (RelativeLayout) this.K.findViewById(R.id.mini_game_layout);
        this.aG = (ImageView) this.K.findViewById(R.id.mini_game_open);
        this.aH = (ImageView) this.K.findViewById(R.id.mini_game_close);
        this.aD = (MiniGameWebview) this.K.findViewById(R.id.game_webview);
        this.aC = (CustomSofaView) this.K.findViewById(R.id.room_custom_sofa);
        this.az = (RelativeLayout) this.K.findViewById(R.id.room_root_rl);
        this.aA = (RelativeLayout) this.K.findViewById(R.id.pig_pk_duck_layout);
        this.ao = (RelativeLayout) this.K.findViewById(R.id.rl_info_top);
        this.ap = this.K.findViewById(R.id.room_chat_top_misty);
        this.U = (RelativeLayout) this.K.findViewById(R.id.ll_bottom_wrapper);
        this.V = this.K.findViewById(R.id.iv_more);
        this.aa = (FrameLayout) this.K.findViewById(R.id.iv_rank_layout);
        this.ae = (SimpleDraweeView) this.K.findViewById(R.id.iv_rank);
        this.af = (ImageView) this.K.findViewById(R.id.fans_crown_small);
        this.ab = (RelativeLayout) this.K.findViewById(R.id.iv_guard_layout);
        this.ac = (ImageView) this.K.findViewById(R.id.iv_guard);
        this.ag = (TextView) this.K.findViewById(R.id.guard_num);
        this.ad = (ImageView) this.K.findViewById(R.id.iv_close_room);
        this.ah = (RelativeLayout) this.K.findViewById(R.id.send_red_layout);
        this.ai = (ImageView) this.K.findViewById(R.id.iv_private_msg);
        this.aj = (ImageView) this.K.findViewById(R.id.iv_share);
        this.U.setVisibility(0);
        this.av = (RelativeLayout) this.K.findViewById(R.id.elogo_layout);
        this.aw = (SimpleDraweeView) this.K.findViewById(R.id.iv_elogo);
        this.ax = (SimpleDraweeView) this.K.findViewById(R.id.iv_elogo_two);
        this.as = (FrameLayout) this.K.findViewById(R.id.fl_chart);
        this.at = (SimpleDraweeView) this.K.findViewById(R.id.iv_host);
        this.ay = this.K.findViewById(R.id.rl_info_left);
        this.M = (TextView) this.K.findViewById(R.id.tv_host_name);
        this.N = (TextView) this.K.findViewById(R.id.tv_live_red_count);
        this.O = (ImageView) this.K.findViewById(R.id.v_attention);
        this.P = this.K.findViewById(R.id.rl_menu_down);
        this.Q = (TextView) this.K.findViewById(R.id.tv_spectator_num);
        this.R = (ImageView) this.K.findViewById(R.id.iv_msg);
        this.S = (ImageView) this.K.findViewById(R.id.iv_gift);
        this.T = (TextView) this.K.findViewById(R.id.tv_red_num);
        this.I = (LinearLayout) this.K.findViewById(R.id.root_view);
        this.aM = DisPlayUtil.getHeight(getContext()) / 8;
        j();
        this.aa.setOnClickListener(this);
        this.ab.setOnClickListener(this);
        this.ad.setOnClickListener(this);
        this.aj.setOnClickListener(this);
        this.aG.setOnClickListener(this);
        this.aH.setOnClickListener(this);
        this.V.setOnClickListener(this);
        this.aw.setOnClickListener(this);
        this.ax.setOnClickListener(this);
        this.e.addPlayerViewStateListener(this);
        this.ay.setOnClickListener(this);
        this.ah.setOnClickListener(this);
        this.R.setOnClickListener(this);
        this.S.setOnClickListener(this);
        this.O.setOnClickListener(this);
        this.ai.setOnClickListener(this);
        this.aC.setOnSeatClickListener(this);
        ((InterceptRelativeLayout) this.ao).setOnInterceptTouchListener(new l(this));
        this.I.addOnLayoutChangeListener(new w(this));
        InroomPresenter.getInstance().registerInroom(this);
        if (LoginUtils.getLoginUserBean() != null) {
            if (this.X == null) {
                this.X = RedPresenter.getInstance();
            }
            this.X.register(this, LoginUtils.getLoginUserBean().getId(), SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()));
            if (this.Z == null) {
                this.Z = PropListPresenter.getInstance();
            }
            this.Z.register(this);
        }
        if (this.al == null) {
            this.al = new t(this);
        }
        if (this.am == null) {
            this.am = new u(this);
        }
        EventManager.getDefault().attach(this.al, GiftBoxEvent.class);
        EventManager.getDefault().attach(this.am, LoginEvent.class);
        a(this.f);
        super.onActivityCreated(bundle);
        StatisticManager.getInstance().pageStatistic(StatisticCodeTable.ROOM);
    }

    public void onResume() {
        super.onResume();
        LogUtils.d(b, "onResume--");
        this.c = this.e.getCurPlayerState();
        switch (x.a[this.c.ordinal()]) {
            case 1:
                playerviewFinished();
                break;
            case 2:
                playerviewPlaying();
                break;
            case 3:
                playerviewLoading();
                break;
        }
        d();
        if (LoginUtils.getLoginUserBean() == null && this.O != null && this.T != null) {
            this.H = false;
            this.T.setText("0");
            if (this.X != null) {
                this.X.unregister(this);
            }
        }
    }

    public static FullScreenRoomFragment newInstance(String str, String str2, int i) {
        FullScreenRoomFragment fullScreenRoomFragment = new FullScreenRoomFragment();
        Bundle bundle = new Bundle();
        bundle.putString("rid", str);
        bundle.putString(RoomBaseFragment.RUID_KEY, str2);
        bundle.putInt(RoomBaseFragment.FRAGMENT_TYPE_KEY, i);
        fullScreenRoomFragment.setArguments(bundle);
        return fullScreenRoomFragment;
    }

    public void onSeatClick(int i) {
        if (LoginUtils.getLoginUserBean() == null) {
            showLoginDialog();
            StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ROOM, StatisticCodeTable.FRED);
        } else if (this.aq != null) {
            this.aq.showDialog(i);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.mini_game_open) {
            if (this.aE != null) {
                a(true);
            }
            this.aG.setVisibility(8);
        } else if (id == R.id.mini_game_close) {
            a(false);
            this.aG.setVisibility(0);
        } else if (id == R.id.iv_rank_layout) {
            if (InroomPresenter.getInstance().getLocalRoomInfo() != null) {
                if (this.n == null) {
                    this.n = new FansDialog(this.e, InroomPresenter.getInstance().getLocalRoomInfo());
                    this.n.setLayout(this.mRoomType);
                    this.n.setOnDismissListener(new ad(this));
                    this.a = new ReadGiftEngine().getGiftBeanList();
                }
                if (this.p && 1 != this.mRoomType) {
                    FansPresenter.getInstance().sendGiftListSocket();
                    this.p = false;
                }
                this.n.show();
                i();
                StatisticManager.getInstance().clickStatistic(StatisticCodeTable.ROOM, StatisticCodeTable.FRANK);
            }
        } else if (id == R.id.iv_guard_layout) {
            if (InroomPresenter.getInstance().getLocalRoomInfo() != null) {
                if (this.q == null) {
                    this.q = new SpectatorsDialog(this.e, InroomPresenter.getInstance().getLocalRoomInfo());
                    this.q.setLayout(this.mRoomType);
                    this.q.setOnDismissListener(new ae(this));
                }
                this.q.show();
                i();
                StatisticManager.getInstance().clickStatistic(StatisticCodeTable.ROOM, StatisticCodeTable.FVANGLE);
                StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ROOM, StatisticCodeTable.RMORE_ANGLE);
                StatisticValue.getInstance().setRechargePageModule(StatisticCodeTable.ROOM, StatisticCodeTable.RMORE_ANGLE);
            }
        } else if (id == R.id.iv_share) {
        } else {
            if (id == R.id.iv_close_room) {
                if (this.mRoomType == 2) {
                    this.mRoomType = 0;
                    this.e.requestType(0);
                    return;
                }
                this.e.finish();
            } else if (id == R.id.iv_more) {
                if (this.f != null) {
                    if (this.o == null) {
                        this.o = new MoreDialog(this.e, InroomPresenter.getInstance().getLocalRoomInfo(), false, this.mRoomType);
                        this.o.setOnMoreItemClickListener(this.aR);
                        this.o.addLiveCallBack(this.aT);
                        this.o.setOnDismissListener(new af(this));
                    }
                    if (!(getActivity() == null || getActivity().isFinishing() || this.o.isShowing())) {
                        this.o.show();
                    }
                    i();
                    StatisticManager.getInstance().clickStatistic(StatisticCodeTable.ROOM, StatisticCodeTable.RMORE);
                }
            } else if (id == R.id.rl_info_left) {
                if (this.f != null && 1 != this.mRoomType) {
                    if (g()) {
                        this.e.startEventActivity(h() ? "http://m.v.6.cn/supergirl-group/member/" : "http://m.v.6.cn/supergirl-group/member/" + this.f.getRoominfoBean().getRid(), "");
                    } else {
                        UserInfoBean userInfoBean = new UserInfoBean();
                        userInfoBean.setUid(this.f.getRoominfoBean().getId());
                        setChatClickable(userInfoBean);
                    }
                    StatisticManager.getInstance().clickStatistic(StatisticCodeTable.ROOM, StatisticCodeTable.FPROFILE);
                }
            } else if (id == R.id.iv_elogo) {
                this.e.doClickCommonEventFloat();
            } else if (id == R.id.iv_elogo_two) {
                this.e.startEventActivity(this.f.getEventFloatTwo().getUrl(), "");
            } else if (id == R.id.v_attention) {
                if (LoginUtils.getLoginUserBean() == null) {
                    showLoginDialog();
                    StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ROOM, StatisticCodeTable.FRFOLLOW);
                } else if (this.f != null) {
                    this.O.setClickable(false);
                    if (LoginUtils.getLoginUserBean() == null) {
                        showLoginDialog();
                        StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ROOM, StatisticCodeTable.FRFOLLOW);
                    } else {
                        this.O.setClickable(false);
                        FollowPresenter.getInstance().followOrCancel(this.f.getRoominfoBean().getId(), LoginUtils.getLoginUserBean().getId(), SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()));
                    }
                } else {
                    return;
                }
                if (this.f != null) {
                    StatisticManager.getInstance().clickStatistic(StatisticValue.getInstance().getFromAttentionPageModule(), StatisticCodeTable.ROOM, "follow", this.f.getRoominfoBean().getId());
                }
            } else if (id == R.id.iv_msg) {
                if (this.f != null) {
                    this.e.isChatQuietly = false;
                    showInputDialog(null);
                    StatisticManager.getInstance().clickStatistic(StatisticValue.getInstance().getFromFoomPageModule(), StatisticCodeTable.ROOM, StatiscEvent.ROOMMSG, this.f.getRoominfoBean().getId());
                }
            } else if (id == R.id.iv_private_msg) {
                if (this.f != null) {
                    showPrivateChatView(null);
                    StatisticManager.getInstance().clickStatistic(StatisticValue.getInstance().getFromFoomPageModule(), StatisticCodeTable.ROOM, StatiscEvent.ROOMMSG, this.f.getRoominfoBean().getId());
                }
            } else if (id == R.id.iv_gift) {
                if (!FastDoubleClickUtil.isFastDoubleClick()) {
                    if (InroomPresenter.getInstance().getLocalRoomInfo() != null) {
                        a("", "");
                        StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ROOM, StatisticCodeTable.FGIFT);
                        StatisticValue.getInstance().setRechargePageModule(StatisticCodeTable.ROOM, StatisticCodeTable.FGIFT);
                    }
                    StatisticManager.getInstance().clickStatistic(StatisticCodeTable.ROOM, StatisticCodeTable.FGIFT);
                }
            } else if (id != R.id.send_red_layout) {
            } else {
                if (LoginUtils.getLoginUserBean() == null) {
                    showLoginDialog();
                    StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ROOM, StatisticCodeTable.FRED);
                    return;
                }
                if (this.e != null) {
                    try {
                        id = Integer.parseInt(this.T.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        id = -1;
                    }
                    if (id != -1) {
                        id--;
                        if (id >= 0) {
                            this.X.updateLocalRed(id);
                            this.e.sendRedMessage(this.f.getRoominfoBean().getId(), 1);
                        } else {
                            this.e.showToast("红包送完啦，请稍等哦~");
                        }
                    }
                }
                StatisticManager.getInstance().clickStatistic(StatisticCodeTable.ROOM, StatisticCodeTable.FRED);
            }
        }
    }

    public void showLoginDialog() {
        if (this.k == null) {
            this.k = new DialogUtils(this.e);
        }
        if (this.m == null) {
            this.m = this.k.createConfirmDialog(1, getResources().getString(R.string.InfoAbout), getResources().getString(R.string.tip_this_function_need_login), getResources().getString(R.string.tip_login_after), getResources().getString(R.string.tip_login_now), new ag(this));
        }
        if (!this.m.isShowing()) {
            this.m.show();
        }
    }

    private void a(String str, String str2) {
        if (this.L == null) {
            this.L = new GiftBoxDialog(this.mRoomType, this.e, new b(this));
        }
        this.L.show();
        switch (this.mRoomType) {
            case 0:
            case 4:
                this.E = true;
                break;
            case 1:
            case 2:
            case 3:
                this.E = false;
                break;
        }
        i();
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            this.L.setToUser(str, str2);
        }
    }

    private void b() {
        this.U.setVisibility(0);
        if (this.aI) {
            this.aF.setVisibility(0);
        }
        if (!g()) {
            showRed();
        }
        this.d = false;
    }

    private void c() {
        this.U.setVisibility(4);
        if (this.aI) {
            this.aF.setVisibility(8);
        }
        if (!g()) {
            hideRed();
        }
        this.d = true;
    }

    public void showRed() {
        this.ah.setVisibility(0);
        if (this.aS != null) {
            this.aS.isShow(false);
        }
    }

    public void hideRed() {
        this.ah.setVisibility(4);
        if (this.aS != null) {
            this.aS.isShow(true);
        }
    }

    public void setChatClickable(UserInfoBean userInfoBean) {
        if (!CommonStrs.MYSTERY_MAN_UID.equals(userInfoBean.getUid())) {
            setUserInfo(userInfoBean);
            if (this.e.mUserInfoDialog == null) {
                this.e.mUserInfoDialog = new WatchRoomUserInfoDialog(this.e);
            }
            this.e.mUserInfoDialog.show(userInfoBean.getUid(), this.y, this.f.isRoomManager(), this.e.currentIdentity);
        }
    }

    public void setUserInfo(UserInfoBean userInfoBean) {
        this.ar = userInfoBean;
    }

    public void onBackPressed() {
        if (this.ak != null && this.ak.isShow()) {
            boolean z = false;
            if (this.g != null) {
                z = this.g.isShowing();
            }
            if (!z) {
                this.ak.hidePrivateChatView();
            }
        } else if (this.e != null && !this.e.isFinishing()) {
            this.e.finish();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return false;
    }

    public void onRestart() {
    }

    public void sendSocketMessage(Object obj, int i) {
        Message obtain = Message.obtain();
        obtain.obj = obj;
        obtain.what = i;
        this.J.sendMessage(obtain);
    }

    public void updateGiftNum(UpdateGiftNumBean updateGiftNumBean) {
        if (updateGiftNumBean != null) {
            this.J.post(new d(this, updateGiftNumBean));
        }
    }

    public void updateCoinWealth(UpdateCoinWealthBean updateCoinWealthBean) {
    }

    public void showSongQueueList(List<SubLiveListBean> list) {
        this.queueListBean = list;
        this.J.sendEmptyMessage(19);
    }

    public void showSongMenuList(List<SubLiveListBean> list) {
        this.menuListBean = list;
        this.J.sendEmptyMessage(18);
    }

    public void showSongUpdataList(List<SubLiveListBean> list) {
        if (this.queueListBean != null && list != null && list.size() > 0) {
            if ("add".equals(((SubLiveListBean) list.get(0)).getSong_status())) {
                this.queueListBean.add(list.get(0));
            } else {
                for (int i = 0; i < this.queueListBean.size(); i++) {
                    if (((SubLiveListBean) this.queueListBean.get(i)).getId().equals(((SubLiveListBean) list.get(0)).getId())) {
                        ((SubLiveListBean) this.queueListBean.get(i)).setStatus(((SubLiveListBean) list.get(0)).getStatus());
                    }
                }
            }
            this.J.sendEmptyMessage(19);
        }
    }

    public void endHeadLineMessage(OnHeadlineBeans onHeadlineBeans) {
        if (onHeadlineBeans != null) {
            sendSocketMessage(onHeadlineBeans, 135);
        }
    }

    public void miniGameStart(MiniGameBean miniGameBean) {
        if (miniGameBean != null) {
            sendSocketMessage(miniGameBean, SocketUtil.TYPEID_1501);
        }
    }

    public void miniGameEnd(MiniGameBean miniGameBean) {
        if (miniGameBean != null) {
            sendSocketMessage(miniGameBean, SocketUtil.TYPEID_1502);
        }
    }

    public void miniGameUpdate(String str) {
        if (str != null) {
            sendSocketMessage(str, SocketUtil.TYPEID_1503);
        }
    }

    public void updateHeadLineMsg(InitHeadLineBean initHeadLineBean) {
        if (this.v != null) {
            HeadLinePresenter.getInstance().updateTop8Info(initHeadLineBean.getContent(), true, false);
        }
    }

    public void reconnectChatSocket() {
    }

    public void receiveSpeakState(AuthKeyBean authKeyBean, boolean z) {
        authKeyBean.analyze();
        if (this.f != null) {
            this.f.setRoomManager(isRoomManager(authKeyBean));
        }
        Message obtain = Message.obtain();
        obtain.arg1 = authKeyBean.getSpeakState();
        obtain.what = 16;
        this.J.sendMessage(obtain);
    }

    public void receiveFansTm(String str) {
        Message obtain = Message.obtain();
        obtain.what = 11;
        obtain.obj = str;
        this.J.sendMessage(obtain);
    }

    public void receiveChatPermission(ChatPermissionBean chatPermissionBean) {
        this.J.sendEmptyMessage(15);
    }

    public void receiveChatList(String str) {
        Message obtain = Message.obtain();
        obtain.obj = str;
        obtain.what = 413;
        this.J.sendMessage(obtain);
    }

    public void receiveAllChatList(WrapUserInfo wrapUserInfo) {
        Message obtain = Message.obtain();
        obtain.what = 407;
        obtain.obj = wrapUserInfo;
        this.J.sendMessage(obtain);
    }

    public void notifyPublicDataSetChanged(RoommsgBean roommsgBean, boolean z) {
        if (z || "1304".equals(roommsgBean.getTypeID())) {
            this.A++;
        }
        Message obtain = Message.obtain();
        obtain.obj = roommsgBean;
        obtain.what = 1;
        this.J.sendMessage(obtain);
    }

    public void receiveRed(RoommsgBean roommsgBean, boolean z) {
    }

    public void notifyPrivateDataSetChanged(RoommsgBean roommsgBean) {
        if (LoginUtils.getLoginUserBean() != null) {
            String id = LoginUtils.getLoginUserBean().getId();
            if (id.equals(roommsgBean.getFid()) || id.equals(roommsgBean.getToid()) || roommsgBean.getTypeID().equals("112")) {
                this.ak.addData(roommsgBean);
            }
        }
    }

    public void liveStateReceive(LiveStateBean liveStateBean) {
    }

    public void receiveNoticeTm(NoticeTmBean noticeTmBean) {
    }

    public void receiveFlyText(FlyTextBean flyTextBean) {
    }

    public void receiveSofaUpdated(SofaBean sofaBean) {
        this.J.post(new e(this, sofaBean));
    }

    public void setSpectatorNum(String str) {
        TextView textView = this.Q;
        StringBuilder stringBuilder = new StringBuilder();
        NumberFormat instance = NumberFormat.getInstance();
        if (TextUtils.isEmpty(str)) {
            str = "0";
        }
        textView.setText(stringBuilder.append(instance.format((long) Integer.parseInt(str))).append("人在看").toString());
    }

    public void chatChange() {
    }

    public void showSpeakOverquick() {
        if (this.k == null) {
            this.k = new DialogUtils(this.e);
        }
        if (this.l == null) {
            this.l = this.k.createDiaglog(getResources().getString(R.string.str_speak_overquick));
        }
        if (this.l != null && !this.l.isShowing()) {
            this.l.show();
        }
    }

    public void showChatLengthy() {
    }

    public void refreshChat() {
        refreshPublicAdapter(null);
        if (this.mPublicChatPage != null) {
            this.mPublicChatPage.setSelection();
        }
    }

    public List<UserInfoBean> initChatListData() {
        if (this.f == null) {
            return null;
        }
        List<UserInfoBean> arrayList = new ArrayList();
        arrayList.addAll(this.f.getGiftUserConf());
        if (this.e.isChatQuietly) {
            return arrayList;
        }
        this.e.tempUserInfoBean = new UserInfoBean();
        this.e.tempUserInfoBean.setUname("所有人");
        this.e.tempUserInfoBean.setUid(RoomActivity.VIDEOTYPE_UNKNOWN);
        arrayList.add(0, this.e.tempUserInfoBean);
        return arrayList;
    }

    public void showOpenGuardPage() {
        if (this.e.isSuperGirlRoom().booleanValue()) {
            this.e.startEventActivity(SpectatorsDialog.OPEN_VFAN_URL, "");
            return;
        }
        if (this.r == null) {
            this.r = new FullScreenOpenGuardDialog(this.e, this.f.getRoominfoBean(), new f(this));
        }
        this.r.show();
    }

    public void resetData(String str, String str2) {
    }

    public void updateFollow(boolean z) {
        this.H = z;
        this.J.post(new g(this));
    }

    public void initFollow(boolean z) {
        this.H = z;
        this.J.post(new h(this));
    }

    public void updateFollowNetError(boolean z, int i) {
        this.H = z;
        this.J.post(new i(this, i));
    }

    public void updateFollowServerError(boolean z, String str, String str2) {
        this.H = z;
        this.J.post(new j(this, str, str2));
    }

    public void setWrapRoomInfo(WrapRoomInfo wrapRoomInfo) {
        this.f = wrapRoomInfo;
        a(this.f);
    }

    public void error(int i) {
        handleErrorResult(String.valueOf(i), "");
        this.e.hintProDialog();
    }

    public void handlerError(String str, String str2) {
        this.e.showToast(str2);
    }

    public void showMessage(byte b) {
        if (isAdded() && !isHidden()) {
            switch (b) {
                case (byte) 0:
                    ToastUtils.showToast("订阅成功");
                    return;
                case (byte) 1:
                    ToastUtils.showToast("您已经订阅过该主播");
                    return;
                default:
                    return;
            }
        }
    }

    public void onPrepare() {
    }

    public void onFinish() {
    }

    public void scrollBarState(int i) {
    }

    public void setPauseChat(boolean z) {
        this.E = z;
    }

    public void refreshPublicAdapter(RoommsgBean roommsgBean) {
        int i = 1;
        if (this.g != null && this.g.getKeyboardStatus() == 2) {
            i = 0;
        }
        if (!this.E || r0 != 0 || !this.F || !this.G) {
            if (roommsgBean != null) {
                i = this.D.size();
                while (true) {
                    int i2 = i - 1;
                    if (i > 0 && this.C.size() > 0) {
                        this.C.remove(0);
                        i = i2;
                    }
                }
                if (this.D.size() > 0) {
                    this.C.addAll(this.D);
                    this.D.clear();
                }
                if (this.C.size() >= 200) {
                    this.C.remove(0);
                }
                this.C.add(roommsgBean);
            }
            notifyPublicChatAdapter();
        }
    }

    public void notifyPublicChatAdapter() {
        if (this.mPublicChatPage != null) {
            this.mPublicChatPage.notifyAdapter();
        }
    }

    public void handleErrorResult(String str, String str2) {
        this.e.handleErrorResult(str, str2, this.e);
    }

    private void d() {
        if (LoginUtils.getLoginUserBean() != null) {
            String id = LoginUtils.getLoginUserBean().getId();
            if (this.Y == null) {
                this.Y = FollowPresenter.getInstance();
            }
            this.Y.register(this);
            this.Y.getFollowStatus(this.y, this.x, id);
            return;
        }
        this.H = false;
        if (this.Y != null) {
            this.Y.clearFollowStatus();
        }
        e();
    }

    private void e() {
        this.J.postDelayed(new k(this), 150);
    }

    private void a(WrapRoomInfo wrapRoomInfo) {
        if (wrapRoomInfo != null) {
            String str;
            this.e.isChatQuietly = true;
            this.ak = new PrivateChatPresenter(this.e);
            this.ak.setRoomType(this.mRoomType);
            this.ak.setPrevateChatPresenter(this);
            this.ak.setChatContentHeight(this.e.getChatHeight(this.mRoomType));
            this.mLiveType = wrapRoomInfo.getRoominfoBean().getUoption().getLivetype();
            RoomEventFloatBean eventFloat = wrapRoomInfo.getEventFloat();
            if (eventFloat != null) {
                this.aw.setVisibility(0);
                this.aw.setImageURI(!TextUtils.isEmpty(eventFloat.getElogo()) ? eventFloat.getElogo().replace(".png", "@2x.png") : eventFloat.getElogo());
            }
            RoomEventFloatTwoBean eventFloatTwo = wrapRoomInfo.getEventFloatTwo();
            if (eventFloatTwo != null) {
                this.ax.setVisibility(8);
                this.ax.setImageURI(!TextUtils.isEmpty(eventFloatTwo.getLogo()) ? eventFloatTwo.getLogo().replace(".png", "@2x.png") : eventFloatTwo.getLogo());
            }
            LogUtils.e(b, this.f.getRoominfoBean().getId() + "-------------------");
            this.aD.initValue(this.e, this.f.getRoominfoBean().getId());
            this.e.pubchat = wrapRoomInfo.getRoomParamInfoBean().getPubchat();
            RoominfoBean roominfoBean = wrapRoomInfo.getRoominfoBean();
            this.at.setImageURI(roominfoBean.getUoption().getPicuser());
            this.M.setText(roominfoBean.getAlias());
            new StringBuilder("(").append(roominfoBean.getRid()).append(")");
            if (this.au == null) {
                this.au = NumberFormat.getInstance();
                this.au.setMinimumFractionDigits(0);
                this.au.setMaximumIntegerDigits(64);
            }
            StringBuilder stringBuilder = new StringBuilder();
            NumberFormat numberFormat = this.au;
            if (TextUtils.isEmpty(wrapRoomInfo.getWrapUserInfo().getNum())) {
                str = "0";
            } else {
                str = wrapRoomInfo.getWrapUserInfo().getNum();
            }
            this.Q.setText(stringBuilder.append(numberFormat.format((long) Integer.parseInt(str))).append("人在看").toString());
            FansPresenter.getInstance().setFirstFansCallBack(new m(this));
            FansPresenter.getInstance().updateNowFans(this.f.getRoominfoBean().getId(), "");
            if (this.e.isSuperGirlRoom().booleanValue()) {
                this.ag.setText(this.f.getWrapUserInfo().getNtvsn());
            } else {
                List safeList = wrapRoomInfo.getWrapUserInfo().getSafeList();
                if (safeList != null) {
                    this.ag.setText(String.valueOf(safeList.size()));
                }
            }
            Object starlight;
            if (g()) {
                starlight = wrapRoomInfo.getSuperGMem().getStarlight();
                if (!TextUtils.isEmpty(starlight)) {
                    this.B = Integer.parseInt(starlight);
                    this.N.setText(this.au.format((long) this.B));
                }
            } else {
                starlight = wrapRoomInfo.getLiveinfoBean().getAllgetnum();
                if (!TextUtils.isEmpty(starlight)) {
                    this.A = Integer.parseInt(starlight);
                }
                this.N.setText(this.au.format((long) this.A));
            }
            this.C.clear();
            a(wrapRoomInfo.getPublicRoommsgBeans());
            this.ak.clearData();
            ArrayList privateRoommsgBeans = wrapRoomInfo.getPrivateRoommsgBeans();
            if (!this.e.isSuperGirlRoom().booleanValue()) {
                Iterator it = privateRoommsgBeans.iterator();
                while (it.hasNext()) {
                    RoommsgBean roommsgBean = (RoommsgBean) it.next();
                    if (!TextUtils.isEmpty(roommsgBean.getContent())) {
                        this.ak.addData(roommsgBean);
                    }
                }
            }
            String id = roominfoBean.getId();
            if (TextUtils.isEmpty(this.y)) {
                this.y = id;
            }
            if (this.mPublicChatPage == null) {
                this.as.removeAllViews();
                this.mPublicChatPage = new FullScreenChatPage(g(), this.e, this.C, id, CommonStrs.PUBLIC_CHAT, this);
                this.mPublicChatPage.setOnChatPageScrollListener(this);
                this.mPublicChatPage.setOnChatOnlickListener(new c(this));
                this.mPublicChatPage.setRoomType(this.mRoomType);
                this.as.addView(this.mPublicChatPage);
            }
            if (this.L != null) {
                this.L.cleanDada();
                this.L = null;
            }
            this.J.sendEmptyMessageDelayed(17, 3000);
            this.e.onFragmentCreate();
            SpectatorsPresenter.getInstance().register(this);
            SpectatorsPresenter.getInstance().setIsSuperGirlRoom(this.e.isSuperGirlRoom().booleanValue());
            str = ShareTextUtil.getPicUrl(wrapRoomInfo);
            if (str != null) {
                FrescoUtil.asyncGetBitmap(str, new n(this));
            }
            Map sofa = wrapRoomInfo.getRoomParamInfoBean().getSofa();
            this.aC.initSofa(sofa);
            if (this.aq != null) {
                this.aq.setSofaMap(sofa);
                this.aq.setRuid(this.y);
            }
        }
    }

    private void a(ArrayList<RoommsgBean> arrayList) {
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                RoommsgBean roommsgBean = (RoommsgBean) it.next();
                String typeID = roommsgBean.getTypeID();
                if ((TextUtils.isEmpty(typeID) || SocketUtil.TYPEID_1304 != Integer.parseInt(typeID)) && (!(this.e.isSuperGirlRoom().booleanValue() && SuperGirlSocketFilter.isFiltered(typeID)) && 102 == Integer.parseInt(typeID))) {
                    this.C.add(ChatStyleUtils.chatStyleHandle(roommsgBean));
                }
            }
        }
    }

    public void updateRed(RedBean redBean) {
        LogUtils.d(b, "updateRed--" + redBean.getCurrentRed());
        this.J.post(new o(this, redBean));
    }

    public void handerError(String str, String str2) {
        if (getActivity() != null) {
            HandleErrorUtils.handleErrorResult(str, str2, getActivity());
        }
    }

    public void setPriv(String str) {
    }

    public void onRooomActivityResult(int i, int i2, Intent intent) {
        if (i == CommonInts.USER_MANAGER_REQUEST_CODE) {
            this.J.sendEmptyMessageDelayed(17, 1000);
            if (LoginUtils.getLoginUserBean() != null && this.f != null) {
                d();
                this.T.setEnabled(true);
                if (LoginUtils.getLoginUserBean() != null) {
                    if (!(this.L == null || LoginUtils.getLoginUserBean().getCoin6() == null)) {
                        this.L.loadCurrency();
                    }
                    if (Long.valueOf(Long.parseLong(LoginUtils.getLoginUserBean().getCoin6all())).longValue() >= 10) {
                        this.J.sendEmptyMessageDelayed(6, 1500);
                    } else {
                        this.J.sendEmptyMessageDelayed(6, 6000);
                    }
                }
                if (this.X == null) {
                    this.X = RedPresenter.getInstance();
                }
                this.X.register(this, LoginUtils.getLoginUserBean().getId(), SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()));
                if (this.Z == null) {
                    this.Z = PropListPresenter.getInstance();
                }
                this.Z.register(this);
            }
        }
    }

    public void updatedPermission(int[] iArr, int[] iArr2) {
    }

    public void clearGiftList() {
        this.p = true;
    }

    public void openGiftBox(String str) {
        a("", "");
        this.L.setGiftPosition(str);
    }

    public void receiveGift(Gift gift) {
        Message obtain = Message.obtain();
        obtain.obj = gift;
        obtain.what = 201;
        this.J.sendMessage(obtain);
    }

    public void receiveGiftList(GiftListBean giftListBean) {
        Message obtain = Message.obtain();
        obtain.obj = giftListBean;
        obtain.what = 701;
        this.J.sendMessage(obtain);
    }

    public void receiveSmallFlyText(SmallFlyTextBean smallFlyTextBean) {
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.Y != null) {
            this.Y.onDestroy();
            this.Y = null;
        }
        if (this.Z != null) {
            this.Z.onDestroy();
            this.Z = null;
        }
        if (this.X != null) {
            this.X.onDestroy();
            this.Z = null;
        }
        if (this.v != null) {
            this.v.dismiss();
        }
        if (this.o != null) {
            this.o.dismiss();
        }
        if (this.aB != null) {
            this.aB.onActivityDestrory();
        }
        this.e.removePlayerViewStateListener(this);
    }

    public void playerviewPlaying() {
        this.c = PlayerState.PLAYING;
    }

    public void playerviewLoading() {
        this.c = PlayerState.PLAYLONGIND;
    }

    public void playerviewFinished() {
        this.c = PlayerState.PLAYEND;
    }

    public void updataSpectatorSize(String str) {
        setSpectatorNum(str);
    }

    public void updataGuardSize(String str) {
        this.ag.setText(str);
    }

    public void updateError(int i) {
        this.e.showErrorToast(i);
    }

    public void onIMMsgNumChange(int i) {
    }

    public void refreshNotificationUI(boolean z) {
        this.e.hintProDialog();
    }

    public void handleErrorInfo(String str, String str2) {
        handleErrorResult(str, str2);
        this.e.hintProDialog();
    }

    public void setFullPopShowListener(FullPopShowListener fullPopShowListener) {
        this.aS = fullPopShowListener;
    }

    public void setPrivateChatViewShow() {
        c();
        this.an = true;
    }

    public void setPrivateChatViewHide() {
        b();
        this.an = false;
    }

    public void showInputDialog(UserInfoBean userInfoBean) {
        if (userInfoBean != null) {
            this.e.currentUserInfoBean = userInfoBean;
        }
        if (LoginUtils.getLoginUserBean() == null) {
            showLoginDialog();
            StatisticValue.getInstance().setRegisterPageModule(StatisticCodeTable.ROOM, StatisticCodeTable.CHAT);
            return;
        }
        if (!this.an && this.aI) {
            this.aJ = true;
            a(false);
        }
        f();
        this.g.show();
    }

    private void f() {
        if (this.e.isChatQuietly) {
            if (this.h == null) {
                this.h = new PrivateInputDialog(this.e);
            }
            if (this.e.currentUserInfoBean != null) {
                ((PrivateInputDialog) this.h).setPrivateChatPresenter(this.ak);
                this.g = this.h;
                this.g.addOnGlobalLayoutListener(this.ak);
                this.g.setInputEditHint("悄悄对" + this.e.currentUserInfoBean.getUname() + "说");
                ((PrivateInputDialog) this.g).setContactImg(this.e.currentUserInfoBean.getUserpic());
            } else {
                return;
            }
        }
        if (this.i == null) {
            this.i = new RoomFullInputDialog(this.e);
            this.i.setInputListener(new p(this));
        }
        this.g = this.i;
        if (this.j == null) {
            this.j = new q(this);
        }
        this.g.addOnGlobalLayoutListener(this.j);
        this.g.addOnGlobalLayoutListener(this.e);
        this.g.setAutoDismiss(false);
    }

    public void setNewMsgViewShow() {
        if (this.ai != null) {
            this.ai.setImageResource(R.drawable.bt_msg_private_red_room_v6_selector);
        }
    }

    public void setNewMsgViewHide() {
        if (this.ai != null) {
            this.ai.setImageResource(R.drawable.bt_msg_private_room_v6_selector);
        }
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        this.F = i != 0;
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }

    public void setRoomLiveCallBack(RoomLiveCallBack roomLiveCallBack) {
        this.aT = roomLiveCallBack;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (2 == this.mRoomType) {
            if (this.aI) {
                this.aK = true;
            }
            a(false);
            this.aG.setVisibility(8);
        } else if (this.aE != null) {
            if (this.aK) {
                a(true);
                this.aK = false;
            } else {
                this.aG.setVisibility(0);
            }
        }
        j();
        if (this.s != null) {
            if (this.s.isShowing()) {
                this.s.dismiss();
            }
            this.s.setLayout(this.mRoomType);
        }
        if (this.n != null) {
            this.n.setLayout(this.mRoomType);
        }
        if (this.q != null) {
            this.q.setLayout(this.mRoomType);
        }
        if (this.r != null) {
            this.r = null;
        }
        if (this.v != null) {
            this.v.setLayout(this.mRoomType);
            if (this.v.getmHeadLineRuleDialog() != null) {
                this.v.setmHeadLineRuleDialog(null);
            }
        }
        if (this.o != null) {
            this.o = null;
        }
        if (this.ak != null) {
            this.ak.setRoomType(this.mRoomType);
        }
        if (this.mPublicChatPage != null) {
            this.mPublicChatPage.setRoomType(this.mRoomType);
        }
        if (this.L != null) {
            this.L.cleanDada();
            this.L = null;
        }
        if (this.g != null) {
            this.g.removeOnGlobalLayoutListener(this.j);
            this.g = null;
            this.h = null;
            this.i = null;
            this.j = null;
            f();
        }
    }

    private boolean g() {
        return this.mLiveType != null && (BaseRoomActivity.LIVE_TYPE_SUPER_GIRL_PERSON.equals(this.mLiveType) || BaseRoomActivity.LIVE_TYPE_SUPER_GIRL_FAMILY.equals(this.mLiveType));
    }

    private boolean h() {
        return this.mLiveType != null && BaseRoomActivity.LIVE_TYPE_SUPER_GIRL_FAMILY.equals(this.mLiveType);
    }

    private void i() {
        if (this.mRoomType == 3 || this.mRoomType == 2) {
            this.aa.setVisibility(4);
            this.ab.setVisibility(4);
            this.ad.setVisibility(4);
            this.S.setVisibility(4);
            this.aC.setVisibility(4);
            if (this.aB != null) {
                this.aB.setVisibility(4);
            }
            if (!g()) {
                hideRed();
                this.aj.setVisibility(4);
                this.V.setVisibility(4);
            }
            if (this.f.getEventFloat() != null) {
                this.aw.setVisibility(4);
            }
            if (this.f.getEventFloatTwo() != null) {
                this.ax.setVisibility(8);
            }
        } else if (this.mRoomType == 4) {
            c();
        }
    }

    private void j() {
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -2);
        LayoutParams layoutParams2 = (LayoutParams) this.as.getLayoutParams();
        LayoutParams layoutParams3 = (LayoutParams) this.av.getLayoutParams();
        LayoutParams layoutParams4 = (LayoutParams) this.aA.getLayoutParams();
        switch (this.mRoomType) {
            case 0:
                this.ap.setVisibility(0);
                this.P.setBackgroundResource(R.color.transparent_background);
                this.ao.setBackgroundResource(R.drawable.room_4_3_top_shadow);
                layoutParams.height = this.e.getChatHeight(this.mRoomType);
                layoutParams.addRule(12, -1);
                this.U.setLayoutParams(layoutParams);
                this.U.setBackgroundResource(R.drawable.room_chat_common_backgroud);
                if (this.ak != null) {
                    this.ak.setChatContentHeight(layoutParams.height);
                }
                if (g()) {
                    hideRed();
                    k();
                } else {
                    layoutParams = new LayoutParams(-2, -2);
                    layoutParams.addRule(13, -1);
                    this.S.setLayoutParams(layoutParams);
                    layoutParams = new LayoutParams(-2, -2);
                    layoutParams.addRule(0, this.V.getId());
                    layoutParams.rightMargin = (int) getResources().getDimension(R.dimen.room_share_marginright);
                    this.aj.setLayoutParams(layoutParams);
                }
                layoutParams2.bottomMargin = (int) getResources().getDimension(R.dimen.room_chat_parent_layout_marginbottom);
                layoutParams2.rightMargin = (int) getResources().getDimension(R.dimen.room_chat_layout_marginright);
                layoutParams3.topMargin = 0;
                layoutParams3.addRule(9, -1);
                layoutParams3.addRule(11, 0);
                layoutParams3.addRule(3, this.N.getId());
                layoutParams4.topMargin = DisPlayUtil.getPCPlayerHeight(this.e) - DensityUtil.dip2px(134.0f);
                layoutParams4.rightMargin = DensityUtil.dip2px(5.0f);
                layoutParams4.addRule(11, -1);
                layoutParams4.addRule(3, this.ao.getId());
                break;
            case 1:
                hideRed();
                this.ac.setImageResource(R.drawable.family_spectator);
                this.ag.setVisibility(4);
                this.O.setVisibility(4);
                this.ap.setVisibility(0);
                this.P.setBackgroundResource(R.color.transparent_background);
                this.ao.setBackgroundResource(R.drawable.room_4_3_top_shadow);
                layoutParams.height = this.e.getChatHeight(this.mRoomType);
                layoutParams.addRule(12, -1);
                layoutParams.bottomMargin = (int) getResources().getDimension(R.dimen.room_chat_layout_marginbottom);
                if (this.ak != null) {
                    this.ak.setChatContentHeight(layoutParams.height);
                }
                layoutParams.addRule(12, -1);
                this.U.setLayoutParams(layoutParams);
                this.U.setBackgroundResource(R.drawable.room_chat_common_backgroud);
                layoutParams2.bottomMargin = (int) getResources().getDimension(R.dimen.room_chat_parent_layout_marginbottom);
                layoutParams2.rightMargin = (int) getResources().getDimension(R.dimen.room_chat_layout_marginright);
                ViewGroup.LayoutParams layoutParams5 = new LayoutParams(-2, -2);
                layoutParams5.addRule(13, -1);
                this.S.setLayoutParams(layoutParams5);
                this.V.setVisibility(4);
                layoutParams5 = new LayoutParams(-2, -2);
                layoutParams5.addRule(0, this.V.getId());
                layoutParams5.rightMargin = (int) getResources().getDimension(R.dimen.room_share_marginright);
                this.aj.setLayoutParams(layoutParams5);
                break;
            case 2:
            case 3:
                this.ap.setVisibility(4);
                this.P.setBackgroundResource(R.drawable.room_buttom_shadow);
                this.ao.setBackgroundResource(R.drawable.room_top_shadow);
                layoutParams.height = this.e.getChatHeight(this.mRoomType);
                layoutParams.addRule(12, -1);
                this.U.setLayoutParams(layoutParams);
                this.U.setBackgroundResource(R.color.transparent_background);
                if (this.ak != null) {
                    this.ak.setChatContentHeight(layoutParams.height);
                }
                if (g()) {
                    hideRed();
                    k();
                } else {
                    layoutParams = new LayoutParams(-2, -2);
                    layoutParams.addRule(0, this.aj.getId());
                    layoutParams.rightMargin = (int) getResources().getDimension(R.dimen.room_gift_marginright);
                    this.S.setLayoutParams(layoutParams);
                    this.V.setVisibility(0);
                    layoutParams = new LayoutParams(-2, -2);
                    layoutParams.addRule(0, this.V.getId());
                    layoutParams.rightMargin = (int) getResources().getDimension(R.dimen.room_share_marginright);
                    this.aj.setLayoutParams(layoutParams);
                }
                layoutParams2.bottomMargin = (int) getResources().getDimension(R.dimen.room_chat_parent_layout_marginbottom);
                layoutParams2.rightMargin = (int) getResources().getDimension(R.dimen.room_chat_layout_marginright);
                layoutParams3.topMargin = DensityUtil.dip2px(46.0f);
                layoutParams3.addRule(9, 0);
                layoutParams3.addRule(11, -1);
                layoutParams3.addRule(3, this.aC.getId());
                layoutParams4.topMargin = DensityUtil.dip2px(34.0f);
                layoutParams4.rightMargin = DensityUtil.dip2px(5.0f);
                layoutParams4.addRule(11, -1);
                layoutParams4.addRule(3, this.aC.getId());
                break;
            case 4:
                this.ap.setVisibility(4);
                this.P.setBackgroundResource(R.drawable.room_buttom_shadow);
                this.ao.setBackgroundResource(R.drawable.room_top_shadow);
                layoutParams.height = this.e.getChatHeight(this.mRoomType);
                layoutParams.addRule(12, -1);
                this.U.setLayoutParams(layoutParams);
                this.U.setBackgroundResource(R.drawable.room_chat_common_backgroud);
                if (this.ak != null) {
                    this.ak.setChatContentHeight(layoutParams.height);
                }
                this.U.setBackgroundResource(R.color.transparent_background);
                if (g()) {
                    hideRed();
                    k();
                } else {
                    ViewGroup.LayoutParams layoutParams6 = new LayoutParams(-2, -2);
                    layoutParams6.addRule(13, -1);
                    this.S.setLayoutParams(layoutParams6);
                    this.V.setVisibility(0);
                    layoutParams6 = new LayoutParams(-2, -2);
                    layoutParams6.addRule(0, this.V.getId());
                    layoutParams6.rightMargin = (int) getResources().getDimension(R.dimen.room_share_marginright);
                    this.aj.setLayoutParams(layoutParams6);
                }
                layoutParams2.bottomMargin = (int) getResources().getDimension(R.dimen.room_chat_parent_layout_marginbottom);
                layoutParams2.rightMargin = (int) getResources().getDimension(R.dimen.room_chat_layout_marginright);
                layoutParams3.topMargin = 0;
                layoutParams3.addRule(9, -1);
                layoutParams3.addRule(11, 0);
                layoutParams3.addRule(3, this.N.getId());
                break;
        }
        if (this.mRoomType == 2) {
            this.ad.setImageResource(R.drawable.bt_full_colse_room_v6_selector);
        } else {
            this.ad.setImageResource(R.drawable.bt_colse_room_v6_selector);
        }
        ImageView imageView;
        if (this.mRoomType == 0) {
            imageView = (ImageView) this.K.findViewById(R.id.warter_mark_iv);
            layoutParams3 = (LayoutParams) imageView.getLayoutParams();
            layoutParams3.topMargin = DensityUtil.dip2px(44.0f);
            layoutParams3.rightMargin = DensityUtil.dip2px(16.0f);
            imageView.setVisibility(0);
        } else if (this.mRoomType == 2) {
            imageView = (ImageView) this.K.findViewById(R.id.warter_mark_iv);
            layoutParams3 = (LayoutParams) imageView.getLayoutParams();
            layoutParams3.topMargin = DensityUtil.dip2px(44.0f);
            layoutParams3.rightMargin = DensityUtil.dip2px(16.0f);
            imageView.setVisibility(0);
        } else {
            this.K.findViewById(R.id.warter_mark_iv).setVisibility(8);
        }
        int i = -this.aN;
        this.aI = this.aI;
        this.U.post(new s(this, i));
        ((LayoutParams) this.ah.getLayoutParams()).bottomMargin = (int) getResources().getDimension(R.dimen.room_red_layoutbottom);
        layoutParams2 = (LayoutParams) this.ab.getLayoutParams();
        layoutParams2.leftMargin = (int) getResources().getDimension(R.dimen.room_right_button_interval);
        layoutParams2.rightMargin = (int) getResources().getDimension(R.dimen.room_right_button_interval);
        ((LayoutParams) this.ai.getLayoutParams()).leftMargin = (int) getResources().getDimension(R.dimen.room_private_chat_marginleft);
    }

    private void k() {
        if (h()) {
            this.N.setVisibility(8);
            this.ac.setImageResource(R.drawable.family_spectator);
            this.ag.setVisibility(8);
        } else {
            this.ac.setImageResource(R.drawable.guard_gril_msg_room_v6_selector);
            this.ag.setVisibility(0);
        }
        this.O.setBackgroundResource(R.drawable.bt_gril_attention_add_room_v6_selector);
        this.af.setImageResource(R.drawable.gril_ranking_crown);
        this.N.setBackgroundResource(R.drawable.gril_starshine);
        this.N.setPadding((int) getResources().getDimension(R.dimen.gril_team_padding_left), this.N.getPaddingTop(), this.N.getPaddingRight(), this.N.getPaddingBottom());
        this.R.setImageResource(R.drawable.bt_msg_gril_room_v6_selector);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
        this.S.setImageResource(R.drawable.bt_gift_gril_room_v6_selector);
        layoutParams.addRule(11, -1);
        this.S.setLayoutParams(layoutParams);
        this.ai.setVisibility(8);
        this.V.setVisibility(8);
    }

    private void a(boolean z) {
        this.aI = z;
        this.U.post(new r(this));
    }

    public void onStop() {
        super.onStop();
        if (this.L != null) {
            this.L.cleanDada();
            this.L = null;
        }
    }

    public void onPause() {
        super.onPause();
        if (this.L != null && this.L.isShowing()) {
            this.L.dismiss();
        }
    }

    public void showPrivateChatView(UserInfoBean userInfoBean) {
        if (this.ak != null) {
            this.ak.showPrivateChatView(userInfoBean);
        }
    }

    public void showPublicChatView(UserInfoBean userInfoBean) {
        super.showPublicChatView(userInfoBean);
        this.e.isChatQuietly = false;
        showInputDialog(userInfoBean);
    }

    public void onDestroy() {
        EventManager.getDefault().detach(this.al, GiftBoxEvent.class);
        EventManager.getDefault().detach(this.am, LoginEvent.class);
        SpectatorsPresenter.getInstance().onDestroy();
        HeadLinePresenter.getInstance().onDestroy();
        FansPresenter.getInstance().onDestroy();
        this.J.removeCallbacksAndMessages(null);
        super.onDestroy();
        Log.e(b, FullScreenRoomFragment.class.getSimpleName() + "onDestroy");
        MiniGameWebview.clearCookies();
        MiniGameWebview.clearWebViewCache();
    }

    static /* synthetic */ void a(FullScreenRoomFragment fullScreenRoomFragment, StarlightCount starlightCount) {
        LogUtils.d(b, "receiveStartlightCount---bean---");
        if (starlightCount != null && starlightCount.getStar() != null) {
            String str;
            TextView textView = fullScreenRoomFragment.N;
            NumberFormat numberFormat = fullScreenRoomFragment.au;
            if (TextUtils.isEmpty(starlightCount.getStar())) {
                str = "0";
            } else {
                str = starlightCount.getStar();
            }
            textView.setText(numberFormat.format((long) Integer.parseInt(str)));
        }
    }

    static /* synthetic */ void a(FullScreenRoomFragment fullScreenRoomFragment, PigPkYellowDuckBean pigPkYellowDuckBean) {
        if (fullScreenRoomFragment.aB == null) {
            fullScreenRoomFragment.aB = new PigPkDuckView(fullScreenRoomFragment.e, fullScreenRoomFragment.y, pigPkYellowDuckBean, new v(fullScreenRoomFragment));
            fullScreenRoomFragment.aA.addView(fullScreenRoomFragment.aB);
            return;
        }
        fullScreenRoomFragment.aB.initData(pigPkYellowDuckBean);
    }

    static /* synthetic */ String a(int i) {
        int i2;
        if (i >= 0 && i <= 5) {
            i2 = 500;
        } else if (i <= 10) {
            i2 = 1000;
        } else {
            i2 = SecExceptionCode.SEC_ERROR_SIMULATORDETECT;
        }
        return "每首" + i2 + "六币,主播接受点歌后收取费用。";
    }

    static /* synthetic */ void ae(FullScreenRoomFragment fullScreenRoomFragment) {
        fullScreenRoomFragment.e.isInputShow = fullScreenRoomFragment.aI;
        if (fullScreenRoomFragment.mRoomType == 0 && fullScreenRoomFragment.mPublicChatPage != null) {
            fullScreenRoomFragment.mPublicChatPage.notifyAdapter();
        }
    }
}
