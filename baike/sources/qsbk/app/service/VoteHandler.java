package qsbk.app.service;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import java.util.Set;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.model.Vote;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.LogUtil;

public class VoteHandler extends Handler {
    private int a = 0;
    private int b = 0;
    public long lastSendVoteTime = 0;

    public VoteHandler(Looper looper) {
        super(looper);
    }

    public void handleMessage(Message message) {
        if (DebugUtil.DEBUG) {
            Log.i("VoteHandler", "启动VoteHandler:" + this);
        }
        int size = VoteManager.getInstance().getWaiteSendVotes().size();
        if (size == 0) {
            this.b++;
        } else {
            this.b = 0;
        }
        if (this.lastSendVoteTime == 0) {
            this.lastSendVoteTime = System.currentTimeMillis();
            if (size > 0) {
                a();
            }
        } else if (size >= 2) {
            this.lastSendVoteTime = System.currentTimeMillis();
            a();
        } else if (System.currentTimeMillis() - this.lastSendVoteTime > 60000 && size > 0) {
            this.lastSendVoteTime = System.currentTimeMillis();
            a();
        }
        if (DebugUtil.DEBUG) {
            Log.i("VoteService", "无投票运行次数:" + this.b);
        }
        if (this.a < 2 && this.b < 12) {
            postDelayed(new m(this), 15000);
        }
    }

    private void a() {
        if (sendVotes() != 0) {
            this.a++;
        } else {
            this.a = 0;
        }
    }

    public int sendVotes() {
        int i;
        QiushibaikeException e;
        Exception e2;
        try {
            String post = HttpClient.getIntentce().post(Constants.VOTE_QUEUE, b());
            LogUtil.e("投票结果：" + post);
            if (DebugUtil.DEBUG) {
                Log.i(getClass().getSimpleName(), "投票服务发送结果:" + post);
            }
            i = new JSONObject(post).getInt(NotificationCompat.CATEGORY_ERROR);
            if (i == 0) {
                try {
                    QsbkDatabase.getInstance().updateVote();
                    VoteManager.getInstance().getWaiteSendVotes().clear();
                } catch (QiushibaikeException e3) {
                    e = e3;
                    e.printStackTrace();
                    return i;
                } catch (Exception e4) {
                    e2 = e4;
                    e2.printStackTrace();
                    return i;
                }
            }
        } catch (QiushibaikeException e5) {
            QiushibaikeException qiushibaikeException = e5;
            i = 999;
            e = qiushibaikeException;
            e.printStackTrace();
            return i;
        } catch (Exception e6) {
            Exception exception = e6;
            i = 999;
            e2 = exception;
            e2.printStackTrace();
            return i;
        }
        return i;
    }

    private String b() {
        StringBuffer stringBuffer = new StringBuffer("{\"votes\":[");
        Set<String> keySet = VoteManager.getInstance().getWaiteSendVotes().keySet();
        StringBuffer stringBuffer2 = new StringBuffer();
        for (String str : keySet) {
            stringBuffer2.append(((Vote) VoteManager.getInstance().getWaiteSendVotes().get(str)).toString());
            stringBuffer2.append(com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP);
        }
        String str2 = "";
        if (stringBuffer2.toString().length() > 0) {
            str2 = stringBuffer2.toString().substring(0, stringBuffer2.toString().length() - 1);
        }
        stringBuffer.append(str2);
        stringBuffer.append("]}");
        return stringBuffer.toString();
    }
}
