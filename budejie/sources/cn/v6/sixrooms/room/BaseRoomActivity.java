package cn.v6.sixrooms.room;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.text.TextUtils;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.AnchorPrompt;
import cn.v6.sixrooms.bean.AuthKeyBean;
import cn.v6.sixrooms.bean.BroadcastBean;
import cn.v6.sixrooms.bean.CustomExceptionBean;
import cn.v6.sixrooms.bean.ErrorBean;
import cn.v6.sixrooms.bean.FlyTextBean;
import cn.v6.sixrooms.bean.GuardStausBean;
import cn.v6.sixrooms.bean.LiveMessage;
import cn.v6.sixrooms.bean.LiveStateBean;
import cn.v6.sixrooms.bean.RoomUpgradeMsg;
import cn.v6.sixrooms.bean.RoommsgBean;
import cn.v6.sixrooms.bean.SubLiveListBean;
import cn.v6.sixrooms.bean.UpdateGiftNumBean;
import cn.v6.sixrooms.bean.UserInfoBean;
import cn.v6.sixrooms.bean.WelcomeBean;
import cn.v6.sixrooms.engine.CrashErrorInfoEngine;
import cn.v6.sixrooms.engine.GetRoomMsgSysEngine;
import cn.v6.sixrooms.event.EventManager;
import cn.v6.sixrooms.event.EventObserver;
import cn.v6.sixrooms.room.chat.PrivateChatControlable;
import cn.v6.sixrooms.room.chat.PublicChatControlable;
import cn.v6.sixrooms.room.game.MiniGameMsgEvent;
import cn.v6.sixrooms.room.gift.BoxingListener;
import cn.v6.sixrooms.room.gift.Gift;
import cn.v6.sixrooms.room.gift.GiftResHelp;
import cn.v6.sixrooms.room.gift.HeadLineListener;
import cn.v6.sixrooms.room.gift.MiniGameListener;
import cn.v6.sixrooms.room.gift.MiniGameLiveListener;
import cn.v6.sixrooms.room.red.DragPopupWindowManager;
import cn.v6.sixrooms.room.red.DragPopupWindowManager.IRedPackage;
import cn.v6.sixrooms.room.verify.CaptchaEngine;
import cn.v6.sixrooms.room.verify.VerifyEngine;
import cn.v6.sixrooms.socket.chat.ChangzhanSocketCallBack;
import cn.v6.sixrooms.socket.chat.ChatMsgSocket;
import cn.v6.sixrooms.socket.chat.ChatMsgSocketCallBack;
import cn.v6.sixrooms.socket.chat.CommonEventVoteMsgCallBack;
import cn.v6.sixrooms.socket.chat.GameLuckIndianaListener;
import cn.v6.sixrooms.socket.chat.GamePlaneListener;
import cn.v6.sixrooms.socket.chat.PigPkYellowDuckSocketCallBack;
import cn.v6.sixrooms.socket.chat.SuperGMsgCallBack;
import cn.v6.sixrooms.socket.chat.XiyouGameListener;
import cn.v6.sixrooms.socket.common.SocketUtil;
import cn.v6.sixrooms.ui.phone.BaseFragmentActivity;
import cn.v6.sixrooms.ui.phone.DialogActivity;
import cn.v6.sixrooms.ui.phone.input.BaseRoomInputDialog.OnKeyBoardLister;
import cn.v6.sixrooms.utils.DialogUtils;
import cn.v6.sixrooms.utils.HandleErrorUtils;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import cn.v6.sixrooms.view.interfaces.OnRoomTypeChangeListener;
import cn.v6.sixrooms.view.interfaces.RoomTypeable;
import cn.v6.sixrooms.widgets.phone.UserInfoDialog;
import com.a.a.d;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class BaseRoomActivity extends BaseFragmentActivity implements PrivateChatControlable, PublicChatControlable, IRedPackage, OnKeyBoardLister {
    public static final int CHANGZHAN_BEGIN = 113;
    public static final int CHANGZHAN_FINAL = 129;
    public static final int CHANGZHAN_FINISH = 117;
    public static final int CHANGZHAN_TIME = 119;
    public static final int COMMON_EVENT = 1350;
    public static final int HEAD_LINE = 4081;
    public static final String LIVE_TYPE_SUPER_GIRL_FAMILY = "49";
    public static final String LIVE_TYPE_SUPER_GIRL_PERSON = "48";
    public static final int MINI_GAME = 4082;
    public static final int MINI_GAME_CLOSE = 7012;
    public static final int MINI_GAME_OPEN = 7011;
    public static final int PIGPKYELLOWDUCK = 1514;
    public static final int ROOM_TYPE_COMMON = 0;
    public static final int ROOM_TYPE_FAMILY = 1;
    public static final int ROOM_TYPE_FULL_SCREEN_PORTRAIT = 4;
    public static final int ROOM_TYPE_MOBILE_FULL_SCREEN_LANDSCAPE = 3;
    public static final int ROOM_TYPE_PC_FULL_SCREEN_LANDSCAPE = 2;
    public static final int SING_WAR = 1306;
    DialogUtils a;
    public List<UserInfoBean> allChatList;
    private CaptchaEngine b;
    private VerifyEngine c;
    public ChatMsgSocket chatMsgSocket;
    public int currentIdentity;
    public UserInfoBean currentUserInfoBean;
    private DragPopupWindowManager d = null;
    private WakeLock e;
    public String eid;
    private Dialog f;
    private Handler g = new Handler();
    private GetRoomMsgSysEngine h;
    public Handler handler = new Handler();
    private EventObserver i;
    public String id;
    public boolean isCanSpeak = false;
    public boolean isChatQuietly = false;
    public boolean isInputShow;
    private List<OnChatMsgSocketCallBack> j = new CopyOnWriteArrayList();
    private ChatMsgSocketCallBack k = new c(this);
    private GiftResHelp l;
    protected ArrayList<RoommsgBean> mBasePrivateChatItem = new ArrayList();
    protected ArrayList<RoommsgBean> mBasePublicChatItem = new ArrayList();
    public DialogUtils mDialogUtils;
    public String mFistFansUid;
    public String mLiveType = "";
    public int mRoomType = 0;
    public int mSpeakState;
    public UserInfoDialog mUserInfoDialog;
    public WindowColor mWindowColor;
    public String pubchat;
    public UserInfoBean tempUserInfoBean;
    protected List<OnRoomTypeChangeListener> typeChangeListeners = new ArrayList();

    public class GtAppDlgTask extends AsyncTask<Void, Void, Boolean> {
        final /* synthetic */ BaseRoomActivity a;
        private String b;
        private String c;

        private GtAppDlgTask(BaseRoomActivity baseRoomActivity, String str, String str2) {
            this.a = baseRoomActivity;
            this.b = str;
            this.c = str2;
        }

        protected Boolean doInBackground(Void... voidArr) {
            return Boolean.valueOf(true);
        }

        protected void onPostExecute(Boolean bool) {
            openGtTest(this.a, this.b, this.c, bool.booleanValue());
        }

        public void openGtTest(Context context, String str, String str2, boolean z) {
            d dVar = new d(context, str, str2, Boolean.valueOf(z));
            dVar.a(new q(this));
            dVar.show();
        }
    }

    public enum WindowColor {
        WHITE,
        TRANSPARENT
    }

    public abstract void chatChange();

    public abstract List<UserInfoBean> initChatListData();

    public abstract void processAnchorPrompt(AnchorPrompt anchorPrompt);

    public abstract void refreshChat();

    public abstract void showChatLengthy();

    public abstract void showOpenGuardPage();

    public abstract void showSpeakOverquick();

    static /* synthetic */ void a(BaseRoomActivity baseRoomActivity, ErrorBean errorBean) {
        if (!baseRoomActivity.isFinishing()) {
            Object content = errorBean.getContent();
            String flag = errorBean.getFlag();
            if (SocketUtil.T_PRIV_STOPMSG.equals(errorBean.getType())) {
                baseRoomActivity.receiveStopMessageState(true);
            } else if (SocketUtil.T_PRIV_RECOVER.equals(errorBean.getType())) {
                baseRoomActivity.receiveRecoverMessageState(true);
            }
            if (SocketUtil.T_PROP_FREEVOTE.equals(errorBean.getT())) {
                if ("001".equals(errorBean.getFlag())) {
                    baseRoomActivity.updateFreeVoteNum(errorBean.getContent());
                    return;
                } else if ("407".equals(errorBean.getFlag())) {
                    baseRoomActivity.verificationCode();
                    return;
                }
            }
            if (SocketUtil.T_ADD_LIVE_SONG.equals(errorBean.getType()) || SocketUtil.T_DELETE_LIVE_SONG.equals(errorBean.getType()) || SocketUtil.T_UPDATE_LIVE_SONG.equals(errorBean.getType())) {
                baseRoomActivity.sendSongListMessage(baseRoomActivity.id);
            } else if (SocketUtil.T_MSG_CHANGZHAN_VOTE.equals(errorBean.getT()) || SocketUtil.T_MSG_CHANGZHANFINAL_VOTE.equals(errorBean.getT())) {
                baseRoomActivity.preReceiveError(errorBean);
            } else if (SocketUtil.FLAG_ON_KICK_OUT.equals(flag)) {
                r1 = new Intent(baseRoomActivity, DialogActivity.class);
                r1.putExtra("msg", content);
                baseRoomActivity.startActivity(r1);
                baseRoomActivity.finish();
            } else if (SocketUtil.FLAG_ON_FULL.equals(flag)) {
                r1 = new Intent(baseRoomActivity, DialogActivity.class);
                r1.putExtra("msg", content);
                baseRoomActivity.startActivity(r1);
                baseRoomActivity.finish();
            } else if (SocketUtil.FLAG_ON_MISTAKE_OUT.equals(flag)) {
                r1 = new Intent(baseRoomActivity, DialogActivity.class);
                r1.putExtra("msg", content);
                baseRoomActivity.startActivity(r1);
                baseRoomActivity.finish();
            } else {
                if (baseRoomActivity.a == null) {
                    baseRoomActivity.a = new DialogUtils(baseRoomActivity);
                }
                if (baseRoomActivity.f != null && baseRoomActivity.f.isShowing()) {
                    baseRoomActivity.f.dismiss();
                    baseRoomActivity.f = null;
                }
                if ("406".equals(flag)) {
                    baseRoomActivity.showNotBoundMobileDialog(content, baseRoomActivity);
                } else if ("105".equals(flag)) {
                    HandleErrorUtils.show6CoinNotEnoughDialog(content, baseRoomActivity);
                } else if (TextUtils.isEmpty(content)) {
                    baseRoomActivity.showToast(baseRoomActivity.getResources().getString(R.string.tip_network_error_title));
                } else {
                    baseRoomActivity.f = baseRoomActivity.a.createDiaglog(content);
                    baseRoomActivity.f.show();
                }
            }
        }
    }

    public ChatMsgSocket getChatMsgSocket() {
        return this.chatMsgSocket;
    }

    public Boolean isSuperGirlRoom() {
        boolean z = LIVE_TYPE_SUPER_GIRL_PERSON.equals(this.mLiveType) || LIVE_TYPE_SUPER_GIRL_FAMILY.equals(this.mLiveType);
        return Boolean.valueOf(z);
    }

    public Boolean isSuperGirlFamilyRoom() {
        return Boolean.valueOf(LIVE_TYPE_SUPER_GIRL_FAMILY.equals(this.mLiveType));
    }

    public void stopMessage(String str) {
    }

    public void addManager(String str) {
    }

    public void revokeManager(String str) {
    }

    public void addAdmin(String str) {
    }

    public void revokeAdmin(String str) {
    }

    public void recoverMessage(String str) {
    }

    public void kickRoom(String str) {
    }

    public void setWindow(WindowColor windowColor) {
        this.mWindowColor = windowColor;
    }

    public void showLiveWarningMessage(LiveMessage liveMessage) {
    }

    public void showOpenGuardianAnimation(GuardStausBean guardStausBean) {
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.e = ((PowerManager) getSystemService("power")).newWakeLock(10, BaseRoomActivity.class.getSimpleName());
        this.e.acquire();
        if (this.a == null) {
            this.a = new DialogUtils(this);
        }
        if (this.i == null) {
            this.i = new b(this);
        }
        EventManager.getDefault().attach(this.i, MiniGameMsgEvent.class);
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.e != null) {
            this.e.release();
        }
        dismissAllRedPackage();
        if (this.g != null) {
            this.g.removeCallbacksAndMessages(null);
            this.g = null;
        }
        EventManager.getDefault().detach(this.i, MiniGameMsgEvent.class);
    }

    protected void dismissAllRedPackage() {
        if (this.d != null) {
            this.d.destoryPopupWindow();
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.d == null) {
            int i = 0;
            if (this instanceof RoomTypeable) {
                i = ((RoomTypeable) this).getRoomType();
            }
            this.d = new DragPopupWindowManager(this, getWindow().getDecorView(), this, i);
            this.typeChangeListeners.add(this.d);
            if (this.chatMsgSocket != null) {
                this.chatMsgSocket.setRedPackgeLisener(this.d);
            }
        }
    }

    public void addChatMsgSocketListener(OnChatMsgSocketCallBack onChatMsgSocketCallBack) {
        if (!this.j.contains(onChatMsgSocketCallBack)) {
            this.j.add(onChatMsgSocketCallBack);
        }
    }

    public void removeChatMsgSocketListener(OnChatMsgSocketCallBack onChatMsgSocketCallBack) {
        if (this.j.contains(onChatMsgSocketCallBack)) {
            this.j.remove(onChatMsgSocketCallBack);
        }
    }

    public void processGiftNumUpdate(UpdateGiftNumBean updateGiftNumBean) {
    }

    public void processSongMenuList(List<SubLiveListBean> list) {
    }

    public void processSocketGift(Gift gift) {
    }

    public void processFansTmChange(String str) {
    }

    public void processliveState(LiveStateBean liveStateBean) {
    }

    public void processSocketFlyText(FlyTextBean flyTextBean) {
    }

    public void processSocketSmallFlyText(SmallFlyTextBean smallFlyTextBean) {
    }

    public void processChatSocketReconnect() {
    }

    public void processSocketRed(RoommsgBean roommsgBean, boolean z) {
    }

    public void processSocketListenerSet() {
    }

    public void processUpgradeMessage(RoomUpgradeMsg roomUpgradeMsg) {
    }

    public void processSpeakStateChange(AuthKeyBean authKeyBean, boolean z) {
    }

    public void processWelcome(WelcomeBean welcomeBean) {
    }

    public void processBroadcast(BroadcastBean broadcastBean) {
    }

    public void processBecomeGod(BecomeGodBean becomeGodBean) {
    }

    public void processSuperFireworks(SuperFireworksBean superFireworksBean) {
    }

    public void processPublicNotice(PublicNoticeBean publicNoticeBean) {
    }

    public void createChatMsgSocket(String str, String str2, String str3) {
        if (this.chatMsgSocket == null) {
            this.chatMsgSocket = new ChatMsgSocket(this.k, this, str, str2, str3);
            if (this.d != null) {
                this.d.restart();
                this.chatMsgSocket.setRedPackgeLisener(this.d);
            }
        }
        LogUtils.d("BaseRoomActivity", "tcpFactory--createChatMsgSocket---chatMsgSocket" + this.chatMsgSocket);
    }

    protected void sendSocketException(CustomExceptionBean customExceptionBean) {
        CrashErrorInfoEngine crashErrorInfoEngine = new CrashErrorInfoEngine(new l(this));
        if (customExceptionBean != null) {
            runOnUiThread(new m(this, crashErrorInfoEngine, customExceptionBean));
        }
    }

    public void setBoxingListener(BoxingListener boxingListener) {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.setBoxingListener(boxingListener);
        }
    }

    public void setHeadLineListener(HeadLineListener headLineListener) {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.setHeadLineListener(headLineListener);
        }
    }

    public void setMiniGameListener(MiniGameListener miniGameListener) {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.setMiniGameListener(miniGameListener);
        }
    }

    public void setMiniGameLiveListener(MiniGameLiveListener miniGameLiveListener) {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.setMiniGameLiveListener(miniGameLiveListener);
        }
    }

    public void setSuperGMsgListener(SuperGMsgCallBack superGMsgCallBack) {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.setSuperGMsgListener(superGMsgCallBack);
        }
    }

    public void sendStopMessage(String str, String str2) {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.stopMessage(str, str2);
        }
    }

    public void sendRecoverMessage(String str, String str2) {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.recoverMessage(str, str2);
        }
    }

    public void sendKickRoom(String str, String str2) {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.kickRoom(str, str2);
        }
    }

    public void sendRedMessage(String str, int i) {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.sendRed(str, 1);
        }
    }

    public void sendKickSofa(String str, int i, int i2) {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.kickSofa(str, i, i2);
        }
    }

    public void sendFlyText(String str, String str2, String str3) {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.sendFlyText(str, str2, str3);
        }
    }

    public void sendSmallFltText(String str, String str2, String str3) {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.sendSmallFlyText(str, str2, str3);
        }
    }

    public void sendPublicChat(String str, String str2) {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.sendPublicChat(str, str2);
        }
    }

    public void sendPublicToPersonChat(String str, String str2, String str3, String str4, String str5) {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.sendPublicToPersonChat(str, str2, str3, str4, str5);
        }
    }

    public void sendPrivateChat(String str, String str2, String str3, String str4, String str5) {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.sendPrivateChat(str, str2, this.currentUserInfoBean.getUid(), this.currentUserInfoBean.getUname(), this.currentUserInfoBean.getUrid());
        }
    }

    public void sendChangzhanFinalVote(String str) {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.sendChangzhanFinalVote(str);
        }
    }

    public void sendShareRequest(String str) {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.sendShareRequest(str);
        }
    }

    public void sendAddLiveSong(String str, String str2) {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.sendAddLiveSong(str, str2);
        }
    }

    public void sendDeleteLiveSong(String str) {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.sendDeleteLiveSong(str);
        }
    }

    public void sendUpdateLiveSong(String str, String str2, String str3) {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.sendUpdateLiveSong(str, str2, str3);
        }
    }

    public void sendOpenMiniGame(String str) {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.sendOpenMiniGame(str);
        }
    }

    public void sendCloseMiniGame(String str) {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.sendCloseMiniGame(str);
        }
    }

    public void sendFreeVoteRequest(String str, String str2) {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.sendFreeVoteRequest(str, str2);
        }
    }

    public void sendChangzhanVote() {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.sendChangzhanVote();
        }
    }

    public void sendGiftList(String str) {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.sendGiftList(str);
        }
    }

    public void sendSongListMessage(String str) {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.getSongList(str);
        }
    }

    public void sendSongCalledListMessage(String str) {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.getSongCalledList(str);
        }
    }

    public void sendSetSong(String str, String str2, String str3, String str4) {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.setSong(str, str2, str3, str4);
        }
    }

    public void sendCallSongOperate(String str, int i) {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.getCallSongOperate(str, i);
        }
    }

    public void sendAnonymGift(String str, String str2, String str3, int i, int i2, String str4) {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.sendAnonymGift(str, str2, str3, i, i2, str4);
        }
    }

    public void sendGift(String str, String str2, String str3, int i, int i2, String str4) {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.sendGift(str, str2, str3, i, i2, str4);
        }
    }

    public String getLastUpadateTime() {
        if (this.chatMsgSocket != null) {
            return this.chatMsgSocket.getUserListTm();
        }
        return "";
    }

    public void sendLoadAllRoomList() {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.loadAllRoomList();
        }
    }

    public void setXiyouGameListener(XiyouGameListener xiyouGameListener) {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.setXiyouGameListener(xiyouGameListener);
        }
    }

    public void setGamePlaneListener(GamePlaneListener gamePlaneListener) {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.setGamePlaneListener(gamePlaneListener);
        }
    }

    public void setGameLuckIndianaListener(GameLuckIndianaListener gameLuckIndianaListener) {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.setGameLuckIndianaListener(gameLuckIndianaListener);
        }
    }

    public void setChangzhanListener(ChangzhanSocketCallBack changzhanSocketCallBack) {
        LogUtils.d("BaseRoomActivity", "setChangzhanListener---chatMsgSocket" + this.chatMsgSocket);
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.setChangzhanListener(changzhanSocketCallBack);
        }
    }

    public void setCommonEventListener(CommonEventVoteMsgCallBack commonEventVoteMsgCallBack) {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.setCommonEventListener(commonEventVoteMsgCallBack);
        }
    }

    public void setPigPkYellowDuckListener(PigPkYellowDuckSocketCallBack pigPkYellowDuckSocketCallBack) {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.setPigPkYellowDuckListener(pigPkYellowDuckSocketCallBack);
        }
    }

    public void stopChatMsgSocket() {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.stopChatService();
            this.chatMsgSocket = null;
        }
        if (this.d != null) {
            this.d.destoryPopupWindow();
        }
    }

    protected void updateFreeVoteNum(String str) {
    }

    protected void verificationCode() {
        if (this.b == null) {
            this.b = new CaptchaEngine(new n(this));
        }
        this.b.initCaptcha(LoginUtils.getLoginUID(), SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()));
    }

    public void receiveStopMessageState(boolean z) {
        if (z && this.mUserInfoDialog != null && this.mUserInfoDialog.isShowing()) {
            this.mUserInfoDialog.showOperateBtn(false);
        }
    }

    public void receiveRecoverMessageState(boolean z) {
        if (z && this.mUserInfoDialog != null && this.mUserInfoDialog.isShowing()) {
            this.mUserInfoDialog.showOperateBtn(true);
        }
    }

    public void dismissRoomErrorDialog() {
        if (this.f != null) {
            this.f.dismiss();
        }
    }

    public void preReceiveError(ErrorBean errorBean) {
    }

    public void clickRedPackage(String str) {
        if (this.chatMsgSocket != null) {
            this.chatMsgSocket.robRedPackage(str);
        }
    }

    public void OnKeyBoardChange(boolean z, int i) {
    }

    public void showPrivateChatView(UserInfoBean userInfoBean) {
    }

    public void hidePrivateChatView() {
    }

    public void showPublicChatView(UserInfoBean userInfoBean) {
    }

    public void hidePublicChatView() {
    }

    static /* synthetic */ boolean a(BaseRoomActivity baseRoomActivity, Gift gift) {
        if (gift == null || (!gift.getGtype().equals("2") && !gift.getGtype().equals("1") && !gift.getGtype().equals("3") && !gift.getGtype().equals("4"))) {
            return false;
        }
        if (baseRoomActivity.l == null) {
            baseRoomActivity.l = new GiftResHelp(new o(baseRoomActivity));
        }
        baseRoomActivity.l.enqueue(gift);
        return true;
    }

    static /* synthetic */ void a(BaseRoomActivity baseRoomActivity, String str) {
        if (baseRoomActivity.h == null) {
            baseRoomActivity.h = new GetRoomMsgSysEngine(new k(baseRoomActivity));
        }
        baseRoomActivity.h.getRoomMsgSys(str);
    }
}
