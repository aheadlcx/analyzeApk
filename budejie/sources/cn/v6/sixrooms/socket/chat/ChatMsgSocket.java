package cn.v6.sixrooms.socket.chat;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import cn.v6.sixrooms.listener.RedPackgeLisener;
import cn.v6.sixrooms.room.gift.BoxingListener;
import cn.v6.sixrooms.room.gift.HeadLineListener;
import cn.v6.sixrooms.room.gift.MiniGameListener;
import cn.v6.sixrooms.room.gift.MiniGameLiveListener;
import cn.v6.sixrooms.socket.SocketBusinessManager;
import cn.v6.sixrooms.socket.SocketBusinessManager$SocketBusinessListener;
import cn.v6.sixrooms.socket.SocketResultInterface;
import cn.v6.sixrooms.socket.TcpFactory;
import cn.v6.sixrooms.socket.common.SocketUtil;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

public class ChatMsgSocket implements SocketBusinessManager$SocketBusinessListener, SocketResultInterface {
    private static final String a = ChatMsgSocket.class.getSimpleName();
    private ChatSocketService b;
    private SocketBusinessManager c;
    private String d;
    private String e;
    private ExecutorService f;
    protected Context mContext;

    public ChatMsgSocket(ChatMsgSocketCallBack chatMsgSocketCallBack, Context context, String str, String str2, String str3) {
        this.c = new SocketBusinessManager(chatMsgSocketCallBack, this);
        this.mContext = context.getApplicationContext();
        this.b = new ChatSocketService(str, str2, str3, "CHAT_SOCKET", this);
        this.b.start();
    }

    public void setXiyouGameListener(XiyouGameListener xiyouGameListener) {
        this.c.addGameListener(xiyouGameListener);
    }

    public void setGamePlaneListener(GamePlaneListener gamePlaneListener) {
        this.c.setGamePlaneListener(gamePlaneListener);
    }

    public void setGameLuckIndianaListener(GameLuckIndianaListener gameLuckIndianaListener) {
        this.c.setGameLuckIndianaListener(gameLuckIndianaListener);
    }

    public void setSuperGMsgListener(SuperGMsgCallBack superGMsgCallBack) {
        this.c.setSuperGMsgListener(superGMsgCallBack);
    }

    public void setCommonEventListener(CommonEventVoteMsgCallBack commonEventVoteMsgCallBack) {
        this.c.setCommonEventListener(commonEventVoteMsgCallBack);
    }

    public void setChangzhanListener(ChangzhanSocketCallBack changzhanSocketCallBack) {
        this.c.setChangzhanListener(changzhanSocketCallBack);
    }

    public void setPigPkYellowDuckListener(PigPkYellowDuckSocketCallBack pigPkYellowDuckSocketCallBack) {
        this.c.setPigPkYellowDuckListener(pigPkYellowDuckSocketCallBack);
    }

    public void setRedPackgeLisener(RedPackgeLisener redPackgeLisener) {
        this.c.setRedPackgeLisener(redPackgeLisener);
    }

    public void setBoxingListener(BoxingListener boxingListener) {
        this.c.setBoxingListener(boxingListener);
    }

    public void setHeadLineListener(HeadLineListener headLineListener) {
        this.c.setHeadLineListener(headLineListener);
    }

    public void setMiniGameListener(MiniGameListener miniGameListener) {
        this.c.setMiniGameListener(miniGameListener);
    }

    public void setMiniGameLiveListener(MiniGameLiveListener miniGameLiveListener) {
        this.c.setMiniGameLiveListener(miniGameLiveListener);
    }

    public void sendPublicChat(String str, String str2) {
        try {
            TcpFactory tcpFactory = this.b.getTcpFactory();
            if (tcpFactory != null) {
                tcpFactory.sendCmd(SocketUtil.sendPublicChat(str, str2, getAuthKey()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendPublicToPersonChat(String str, String str2, String str3, String str4, String str5) {
        try {
            this.b.getTcpFactory().sendCmd(SocketUtil.sendPublicChatToPerson(str, str2, getAuthKey(), str3, str4, str5));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendPrivateChat(String str, String str2, String str3, String str4, String str5) {
        try {
            this.b.getTcpFactory().sendCmd(SocketUtil.sendPrivateChat(str, str2, getAuthKey(), str3, str4, str5));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendRed(String str, int i) {
        try {
            this.b.getTcpFactory().sendCmd(SocketUtil.sendRedEnvelope(str, i));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendFreeVote(String str, String str2, int i) {
        try {
            this.b.getTcpFactory().sendCmd(SocketUtil.sendFreeVote(str, str2, i));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendGift(String str, String str2, String str3, int i, int i2, String str4) {
        try {
            this.b.getTcpFactory().sendCmd(SocketUtil.sendGift(str, getAuthKey(), str2, str3, i, i2, str4));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendAnonymGift(String str, String str2, String str3, int i, int i2, String str4) {
        try {
            this.b.getTcpFactory().sendCmd(SocketUtil.sendAnonymGift(str, getAuthKey(), str2, str3, i, i2, str4));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendFlyText(String str, String str2, String str3) {
        try {
            this.b.getTcpFactory().sendCmd(SocketUtil.sendFlyText(str, getAuthKey(), str2, str3));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendSmallFlyText(String str, String str2, String str3) {
        try {
            this.b.getTcpFactory().sendCmd(SocketUtil.sendSmallFlyText(str, getAuthKey(), str2, str3));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopChatService() {
        this.b.stop();
    }

    public void onDestroy() {
    }

    public void loadAllRoomList() {
        try {
            this.b.getTcpFactory().sendCmd(SocketUtil.sendPrivAllList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void kickRoom(String str, String str2) {
        try {
            this.b.getTcpFactory().sendCmd(SocketUtil.kill(str, str2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void recoverMessage(String str, String str2) {
        try {
            this.b.getTcpFactory().sendCmd(SocketUtil.recoverMessage(str, str2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopMessage(String str, String str2) {
        try {
            this.b.getTcpFactory().sendCmd(SocketUtil.stopMessage(str, str2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getSongList(String str) {
        try {
            TcpFactory tcpFactory = this.b.getTcpFactory();
            if (tcpFactory != null) {
                tcpFactory.sendCmd(SocketUtil.songLiveList(1, str));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getSongCalledList(String str) {
        try {
            TcpFactory tcpFactory = this.b.getTcpFactory();
            if (tcpFactory != null) {
                tcpFactory.sendCmd(SocketUtil.songCalledList(1, str));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSong(String str, String str2, String str3, String str4) {
        try {
            this.b.getTcpFactory().sendCmd(SocketUtil.addSong(str, str2, str3, str4));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMicroConnectRequest(String str) {
        try {
            if (this.b != null && !TextUtils.isEmpty(getAuthKey())) {
                this.b.getTcpFactory().sendCmd(SocketUtil.sendMicroRequest(getAuthKey(), str));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void kickSofa(String str, int i, int i2) {
        try {
            this.b.getTcpFactory().sendCmd(SocketUtil.kickSofa(str, i, i2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendChangzhanVote() {
        try {
            TcpFactory tcpFactory = this.b.getTcpFactory();
            if (tcpFactory != null) {
                tcpFactory.sendCmd(SocketUtil.sendChangzhanVoteRequest());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendChangzhanFinalVote(String str) {
        try {
            TcpFactory tcpFactory = this.b.getTcpFactory();
            if (tcpFactory != null) {
                tcpFactory.sendCmd(SocketUtil.sendChangzhanFinalVoteRequest(str));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendShareRequest(String str) {
        try {
            TcpFactory tcpFactory = this.b.getTcpFactory();
            if (tcpFactory != null) {
                tcpFactory.sendCmd(SocketUtil.sendShareRequest(getAuthKey(), str));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendPigPkYellowDuck() {
        try {
            TcpFactory tcpFactory = this.b.getTcpFactory();
            if (tcpFactory != null) {
                tcpFactory.sendCmd(SocketUtil.sendPigPkYellowDuckRequest());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendAddLiveSong(String str, String str2) {
        try {
            TcpFactory tcpFactory = this.b.getTcpFactory();
            if (tcpFactory != null) {
                tcpFactory.sendCmd(SocketUtil.sendAddLiveSong(str, str2));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendDeleteLiveSong(String str) {
        try {
            TcpFactory tcpFactory = this.b.getTcpFactory();
            if (tcpFactory != null) {
                tcpFactory.sendCmd(SocketUtil.sendDeleteLiveSong(str));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendUpdateLiveSong(String str, String str2, String str3) {
        try {
            TcpFactory tcpFactory = this.b.getTcpFactory();
            if (tcpFactory != null) {
                tcpFactory.sendCmd(SocketUtil.sendUpdateLiveSong(str, str2, str3));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendOpenMiniGame(String str) {
        try {
            TcpFactory tcpFactory = this.b.getTcpFactory();
            if (tcpFactory != null) {
                tcpFactory.sendCmd(SocketUtil.sendOpenMiniGame(str));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendCloseMiniGame(String str) {
        try {
            TcpFactory tcpFactory = this.b.getTcpFactory();
            if (tcpFactory != null) {
                tcpFactory.sendCmd(SocketUtil.sendCloseMiniGame(str));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendGiftList(String str) {
        try {
            TcpFactory tcpFactory = this.b.getTcpFactory();
            if (tcpFactory != null) {
                tcpFactory.sendCmd(SocketUtil.sendGiftListRequest(str));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendFreeVoteRequest(String str, String str2) {
        try {
            TcpFactory tcpFactory = this.b.getTcpFactory();
            if (tcpFactory != null) {
                tcpFactory.sendCmd(SocketUtil.sendFreeVoteRequest(getAuthKey(), str, str2));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void robRedPackage(String str) {
        try {
            this.b.getTcpFactory().sendCmd(SocketUtil.getRobRedPackageCommand(str));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addManager(String str, String str2) {
        try {
            this.b.getTcpFactory().sendCmd(SocketUtil.addManager(str, str2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void revokeManager(String str, String str2) {
        try {
            this.b.getTcpFactory().sendCmd(SocketUtil.revokeManager(str, str2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void revokeAdmin(String str, String str2) {
        try {
            this.b.getTcpFactory().sendCmd(SocketUtil.revokeAdmin(str, str2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addAdmin(String str, String str2) {
        try {
            this.b.getTcpFactory().sendCmd(SocketUtil.addAdmin(str, str2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getCallSongOperate(String str, int i) {
        try {
            TcpFactory tcpFactory = this.b.getTcpFactory();
            if (tcpFactory != null) {
                tcpFactory.sendCmd(SocketUtil.songCalleoperate(str, i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAuthKey() {
        if (TextUtils.isEmpty(this.e)) {
            new Handler().post(new a(this));
            this.b.reConnect();
        }
        return this.e;
    }

    public void onPigPkDuckInit() {
        sendPigPkYellowDuck();
    }

    public void onReceiveUserListTm(String str) {
        setUserListTm(str);
    }

    public void onAuthChange(String str) {
        this.e = str;
    }

    public String getUserListTm() {
        return this.d;
    }

    public void setUserListTm(String str) {
        this.d = str;
    }

    public void onReceiveFail(JSONObject jSONObject, String str) {
        this.c.onReceiveFail(jSONObject, str);
    }

    public void onReceiveSuccess(JSONObject jSONObject, String str) {
        if (this.c != null) {
            this.c.onReceiveSuccess(jSONObject, str);
        }
    }

    public void onReceiveSuccessOnMain(JSONObject jSONObject, String str) {
        a().submit(new b(this, jSONObject, str));
    }

    private synchronized ExecutorService a() {
        if (this.f == null) {
            this.f = Executors.newSingleThreadExecutor();
        }
        return this.f;
    }
}
