package qsbk.app.im;

import android.content.IntentFilter;
import android.os.Handler;
import android.text.TextUtils;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.activity.ReAuthActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.model.relationship.Relationship;
import qsbk.app.utils.AppContext;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.image.issue.Logger;

public class ChatClientManager implements MqttCallback, MsgDeliveryObserver {
    @Deprecated
    public static int KEEP_ALIVE_INTERVEL = Integer.MAX_VALUE;
    private static final String e = ChatClientManager.class.getSimpleName();
    public static String testConnectHost = null;
    Handler a = new Handler();
    MqttAsyncClient b = null;
    boolean c = false;
    IMqttActionListener d = new b(this);
    private String f;
    private String g = "unset";
    private ISyncChatMsgListener h = null;
    private List<String> i = null;
    private boolean j = false;
    private String k = null;
    private List<IOnConnectHostResp> l = new ArrayList();
    private AtomicBoolean m = new AtomicBoolean(false);
    public String mUserId = null;
    public IChatMsgListener msgListener = null;
    private SparseArray<Long> n = new SparseArray();
    private int o = -1;
    private int p = 0;
    private ChatClientManager$NetworkReceiver q = new ChatClientManager$NetworkReceiver(this);
    private int r = 3;
    private Runnable s = new a(this);
    private Runnable t = new c(this);

    public ChatClientManager(String str, String str2) {
        this.mUserId = str;
        this.g = str2;
    }

    public void setPassword(String str) {
        this.g = str;
    }

    public boolean initClient() {
        LogUtil.d("init client dsfsadfsdds====");
        if (this.b != null) {
            return true;
        }
        try {
            LogUtil.d("new client:connect host:" + this.f + " mUserId:" + this.mUserId);
            Logger.getInstance().debug(e, "initClient", String.format("初始化[host:%s, userId:%s]", new Object[]{this.f, this.mUserId}));
            e();
            this.b = new MqttAsyncClient(this.f, DeviceUtils.getAndroidId(), null);
            this.b.setCallback(this);
            return true;
        } catch (MqttException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void subscribe(List<String> list) {
        this.i = list;
    }

    private String b() {
        if (!TextUtils.isEmpty(this.k)) {
            return this.k;
        }
        this.k = SharePreferenceUtils.getSharePreferencesValue("im_lasthost_" + this.mUserId);
        return this.k;
    }

    private void a(String str) {
        this.k = str;
        SharePreferenceUtils.setSharePreferencesValue("im_lasthost_" + this.mUserId, str);
    }

    public boolean destroyConnectHost(IOnConnectHostResp iOnConnectHostResp) {
        boolean remove;
        synchronized (this.l) {
            remove = this.l.remove(iOnConnectHostResp);
        }
        return remove;
    }

    private void c() {
        this.a.removeCallbacks(this.t);
        if (TextUtils.isEmpty(testConnectHost)) {
            LogUtil.d("connectFailCount:" + this.p);
            if (this.p >= 2 || TextUtils.isEmpty(b())) {
                LogUtil.d("get host by http");
                Logger.getInstance().debug(e, "getConnectHost", this.m.get() + " , " + Thread.currentThread().getStackTrace()[3]);
                if (this.m.get()) {
                    Logger.getInstance().debug(e, "getConnectHost", "Getting connect host.");
                    return;
                } else {
                    new d(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]);
                    return;
                }
            }
            LogUtil.d("get last connect host:" + b());
            connect("tcp://" + b());
            d();
            return;
        }
        LogUtil.d("use test connect host:" + testConnectHost);
        connect("tcp://" + testConnectHost);
        d();
    }

    private void d() {
        synchronized (this.l) {
            List<IOnConnectHostResp> arrayList = new ArrayList(this.l.size());
            arrayList.addAll(this.l);
            for (IOnConnectHostResp iOnConnectHostResp : arrayList) {
                if (iOnConnectHostResp != null) {
                    iOnConnectHostResp.onHostCallback(this.f);
                }
            }
            arrayList.clear();
        }
    }

    public void getConnectHost(IOnConnectHostResp iOnConnectHostResp) {
        if (iOnConnectHostResp != null) {
            synchronized (this.l) {
                if (this.l.contains(iOnConnectHostResp)) {
                    return;
                }
                this.l.add(iOnConnectHostResp);
                c();
            }
        }
    }

    public String getAppVersion() {
        return "android$" + Constants.localVersionName;
    }

    public MqttConnectOptions getConnOptions() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setCleanSession(false);
        mqttConnectOptions.setUserName(this.mUserId);
        mqttConnectOptions.setPassword((this.g + "$" + getAppVersion()).toCharArray());
        mqttConnectOptions.setMqttVersion(3);
        LogUtil.d("version:" + getAppVersion());
        LogUtil.d("userid:" + this.mUserId);
        LogUtil.d("passwod:" + this.g);
        mqttConnectOptions.setKeepAliveInterval(KEEP_ALIVE_INTERVEL);
        mqttConnectOptions.setConnectionTimeout(10);
        return mqttConnectOptions;
    }

    public int publish(String str, String str2, long j) {
        LogUtil.d(String.format("publish:%s,%s", new Object[]{str, str2}));
        return publish(str, str2, 1, j);
    }

    private void a(long j, int i) {
        LogUtil.d("msg status change:" + j + " status:" + i);
        this.msgListener.onChatMsgStatusChanged(j, i);
    }

    public int publish(String str, String str2, int i, long j) {
        if (this.j) {
            try {
                IMqttDeliveryToken publish = this.b.publish(str, str2.getBytes(), i, false);
                if (i == 1) {
                    LogUtil.d("push id:" + publish.getMessageId() + " localId" + j);
                    this.n.put(publish.getMessageId(), Long.valueOf(j));
                }
                LogUtil.d("publish msg: " + publish.getMessageId());
                return 0;
            } catch (Exception e) {
                LogUtil.d("publish failed:" + str2);
                e.printStackTrace();
                a(j, 3);
                return -2;
            }
        }
        a(j, 3);
        return -1;
    }

    public void removeDisconnectEvent() {
        if (this.a != null) {
            this.a.removeCallbacks(this.t);
        }
    }

    public void disconnectLater(int i) {
        LogUtil.d(String.format("disconnect in %s seconds", new Object[]{Integer.valueOf(i % 1000)}));
        this.a.removeCallbacks(this.t);
        this.a.postDelayed(this.t, (long) i);
    }

    public synchronized void disconnect() {
        LogUtil.d("force disconnect:" + Thread.currentThread().getStackTrace()[3] + " , " + this.b);
        Logger.getInstance().debug(e, "disconnect", "force disconnect....");
        this.c = true;
        this.j = false;
        this.a.removeCallbacks(this.s);
        this.a.removeCallbacks(this.t);
        a(6);
        try {
            if (this.b == null || !this.b.isConnected()) {
                LogUtil.d("client is not connected");
                try {
                    if (this.b != null) {
                        LogUtil.d("close client:");
                        this.b.close();
                    }
                    this.b = null;
                } catch (Exception e) {
                    LogUtil.d("close client connect");
                    e.printStackTrace();
                    this.b = null;
                } catch (Throwable th) {
                    this.b = null;
                }
            } else {
                this.b.disconnect();
                if (this.b != null) {
                    LogUtil.d("close client:");
                    this.b.close();
                }
                this.b = null;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public boolean isConnected() {
        return this.j;
    }

    public synchronized int connect(String str) {
        LogUtil.d("host:" + str);
        if (!(this.f == null || this.f.equals(str))) {
            LogUtil.d("connect host is not null && not equals");
            disconnect();
        }
        this.f = str;
        return connect();
    }

    public synchronized int connect() {
        int i = 1;
        synchronized (this) {
            this.a.removeCallbacks(this.t);
            MqttConnectOptions connOptions = getConnOptions();
            LogUtil.d("connect client========== " + this.b);
            int i2 = (this.b != null || initClient()) ? 1 : 0;
            if (i2 == 0) {
                LogUtil.d("create client fail");
            } else if (this.b.isConnected()) {
                LogUtil.d("client is connected================");
                i = 0;
            } else if (this.r == 1) {
                LogUtil.d("is connecting");
                i = 0;
            } else if (this.r == 2) {
                LogUtil.d("is connected");
                i = 0;
            } else {
                Logger.getInstance().debug(e, "connect", " 开始连接...");
                try {
                    this.c = false;
                    LogUtil.d("start connect ");
                    a(1);
                    this.o = this.b.connect(connOptions, this.mUserId, this.d).getMessageId();
                    Logger.getInstance().debug(e, "正在connect", "mConnectActionId：" + this.o);
                    i = 0;
                } catch (Exception e) {
                    if (((MqttException) e).getReasonCode() == 32100) {
                        a(2);
                        LogUtil.d("client is connected");
                        i = 0;
                    } else {
                        LogUtil.d("connect fail:" + e.getMessage());
                        e.printStackTrace();
                        connectLater();
                        i = -1;
                    }
                }
            }
        }
        return i;
    }

    public synchronized void connectionLost(Throwable th) {
        synchronized (this) {
            IMStaticstic.onLostConnectAccidently();
            LogUtil.e("connection lost:" + th.toString() + "  forceDisconnect:" + this.c);
            String str = "";
            for (Object obj : th.getStackTrace()) {
                str = obj + "\n";
            }
            Logger.getInstance().debug(e, "connectionLost", String.format("是否强制退出链接 [%s]", new Object[]{Boolean.valueOf(this.c)}));
            Logger.getInstance().debug(e, "connectionLost", String.format("连接丢失 [%s] \n %s", new Object[]{th.toString(), str}));
            th.printStackTrace();
            if (this.c) {
                this.c = false;
            } else {
                connectLater();
            }
            LogUtil.d("connection lost end");
        }
    }

    public void connectLater() {
        if (HttpUtils.isNetworkConnected(AppContext.getContext())) {
            Logger.getInstance().debug(e, "connectLater", "3s 后重连。");
            a(3);
            this.a.removeCallbacks(this.s);
            this.a.postDelayed(this.s, 2000);
            return;
        }
        AppContext.getContext().registerReceiver(this.q, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public int getConnectStatus() {
        return this.r;
    }

    private void a(int i) {
        Logger.getInstance().debug(e, "setConnectStatus", "cs:" + IChatMsgListener.connectString[i]);
        b(i);
        if (i != this.r) {
            Logger.getInstance().debug(e, "setConnectStatus", String.format("连接状态由[%s] 切换至[%s]。", new Object[]{IChatMsgListener.connectString[this.r], IChatMsgListener.connectString[i]}));
            this.r = i;
            if (this.r == 5 && QsbkApp.currentUser != null) {
                QsbkApp.mContext.startActivity(ReAuthActivity.getIntent(QsbkApp.mContext, "登录状态校验失败, 请重新登录"));
            }
            if (this.msgListener != null) {
                this.msgListener.onConnectStatusChange(i);
            }
        }
    }

    private void b(int i) {
        DebugUtil.debug("IMStaticstic", "Status:" + i);
        if (i == this.r) {
            return;
        }
        if (i == 3) {
            IMStaticstic.onConnectFailed();
        } else if (i == 2) {
            IMStaticstic.onConnectSuccess();
        } else if (i == 4 || i == 1) {
            IMStaticstic.onConnectStart();
        }
    }

    public void messageArrived(String str, MqttMessage mqttMessage) throws Exception {
        LogUtil.e("message arrived at topic:" + str + " => " + new String(mqttMessage.getPayload()));
        String str2 = new String(mqttMessage.getPayload());
        LogUtil.e("recv msg:" + str2);
        LogUtil.e("msg qos:" + mqttMessage.getQos());
        Logger.getInstance().debug(e, "messageArrived", String.format("Qos[%s], 收到Mqtt的消息[%s]。", new Object[]{Integer.valueOf(mqttMessage.getQos()), str2}));
        if (this.msgListener != null) {
            LogUtil.d("send to msg listener:");
            ChatMsg chatMsg = new ChatMsg();
            chatMsg.parseFromJSONObject(new JSONObject(str2));
            if (chatMsg.type == 108) {
                Logger.getInstance().debug(e, "messageArrived", " 服务器发来校时消息 ： " + chatMsg.time);
                IMTimer.getInstance().correctTime(chatMsg.time);
            } else if (chatMsg.type == 401) {
                if (this.h != null) {
                    this.h.onSyncMsgReceived(chatMsg);
                }
            } else if (chatMsg.type == 400) {
                if (this.h != null) {
                    this.h.onSyncMsgMaintenance(chatMsg);
                }
            } else if (chatMsg.type != 402) {
                if (chatMsg.type == 8) {
                    chatMsg.status = 5;
                    if (chatMsg.data.contains("回粉")) {
                        RelationshipCacheMgr.getInstance(this.mUserId).putRelationship(chatMsg.from, Relationship.FRIEND);
                    }
                }
                if (chatMsg.isDisconnectMsg()) {
                    Logger.getInstance().debug(e, "messageArrived", "在别处登录了！！！");
                    this.c = true;
                    LogUtil.d("disconnec by disconnect msg");
                    disconnectLater(0);
                    this.j = false;
                    if (QsbkApp.currentUser != null) {
                        QsbkApp.getInstance().startActivity(ReAuthActivity.getIntent(QsbkApp.getInstance()));
                    }
                    this.msgListener.onDuplicateConnect(chatMsg);
                } else if (str.startsWith(UserChatManager.USER_PREFIX_SERVER)) {
                    this.msgListener.onMessageReceived(chatMsg);
                } else if (str.startsWith(UserChatManager.GROUP_PREFIX_SERVER)) {
                    this.msgListener.onGroupMessageReceived(chatMsg);
                }
            } else if (this.h != null) {
                this.h.onSyncMsgControl(chatMsg);
            }
        }
    }

    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        long messageId = (long) iMqttDeliveryToken.getMessageId();
        LogUtil.d("on mqttmsg deviver ok:" + messageId);
        onMessageSended((int) messageId);
    }

    public void setMessageCallback(IChatMsgListener iChatMsgListener) {
        this.msgListener = iChatMsgListener;
    }

    public void setSyncMessageCallback(ISyncChatMsgListener iSyncChatMsgListener) {
        this.h = iSyncChatMsgListener;
    }

    public synchronized void onMessageSended(int i) {
        Long l = (Long) this.n.get(i);
        this.n.delete(i);
        Logger.getInstance().debug(e, "onMessageSended", String.format("onMessageSended...消息发送成功[mqttMsgId:%s, dbid:%s]。", new Object[]{Integer.valueOf(i), l}));
        if (l != null) {
            this.msgListener.onChatMsgStatusChanged(l.longValue(), 2);
        }
    }

    private synchronized void e() {
        if (this.n != null) {
            for (int i = 0; i < this.n.size(); i++) {
                Long l = (Long) this.n.get(this.n.keyAt(i));
                if (l != null) {
                    a(l.longValue(), 3);
                }
            }
            this.n.clear();
        }
    }

    public boolean isHostSetted() {
        return !TextUtils.isEmpty(this.f);
    }
}
