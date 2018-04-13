package qsbk.app.service;

import android.content.Intent;
import com.baidu.mobstat.Config;
import java.util.HashMap;
import qsbk.app.QsbkApp;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.model.Vote;
import qsbk.app.utils.DebugUtil;

public class VoteManager {
    public static final String ACTION_VOTE_COUNT = "qsbk.app.ACTION_VOTE_COUNT";
    private static final String a = VoteManager.class.getSimpleName();
    private static VoteManager b;
    public HashMap<String, Vote> AllVotes;
    private int c;
    private Interceptor d;
    public HashMap<String, Vote> waitSendVotes;

    public interface Interceptor {
        void onIntercept();
    }

    public static VoteManager getInstance() {
        if (b == null) {
            synchronized (VoteManager.class) {
                if (b == null) {
                    b = new VoteManager();
                    b.a();
                }
            }
        }
        return b;
    }

    private void a() {
        this.AllVotes = QsbkDatabase.getInstance().queryVote();
        this.waitSendVotes = new HashMap();
        for (String str : this.AllVotes.keySet()) {
            if ("0".equals(((Vote) this.AllVotes.get(str)).state)) {
                this.waitSendVotes.put(str, this.AllVotes.get(str));
            }
        }
    }

    public void put(String str, String str2, Vote vote) {
        this.waitSendVotes.put(String.valueOf(str + "_" + str2), vote);
        this.AllVotes.put(String.valueOf(str + "_" + str2), vote);
    }

    void a(String str, String str2) {
        this.waitSendVotes.remove(String.valueOf(str + "_" + str2));
        this.AllVotes.remove(String.valueOf(str + "_" + str2));
    }

    public boolean vote(Vote vote, String str, String str2) {
        DebugUtil.debug(a, "投票:" + vote.toString());
        QsbkDatabase.getInstance().insertVote(vote);
        put(str2, str, vote);
        String str3 = str.equals("up") ? Config.DEVICE_NAME : "up";
        Integer queryVote = QsbkDatabase.getInstance().queryVote(str2, str3);
        if (queryVote != null) {
            QsbkDatabase.getInstance().deleteVote(queryVote);
            a(str2, str3);
        }
        QsbkApp.getVoteHandler().obtainMessage().sendToTarget();
        this.c++;
        if (this.d != null) {
            this.d.onIntercept();
        }
        if (queryVote == null) {
            return false;
        }
        return true;
    }

    public void setInterceptor(Interceptor interceptor) {
        this.d = interceptor;
    }

    public Intent createVoteCountIntent() {
        Intent intent = new Intent(ACTION_VOTE_COUNT);
        intent.putExtra("count", this.c);
        return intent;
    }

    public HashMap<String, Vote> getWaiteSendVotes() {
        return this.waitSendVotes;
    }

    public boolean containsVote(String str, String str2) {
        return this.AllVotes.containsKey(String.valueOf(str + "_" + str2));
    }

    public int getvoteCount() {
        return this.c;
    }
}
