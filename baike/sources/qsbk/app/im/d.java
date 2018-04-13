package qsbk.app.im;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Pair;
import com.umeng.commonsdk.proguard.g;
import org.json.JSONObject;
import qsbk.app.core.AsyncTask;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.TimeDelta;
import qsbk.app.utils.image.issue.Logger;

class d extends AsyncTask<String, Void, Pair<Integer, String>> {
    JSONObject a = null;
    final /* synthetic */ ChatClientManager b;
    private String c;

    d(ChatClientManager chatClientManager) {
        this.b = chatClientManager;
    }

    protected Pair<Integer, String> a(String... strArr) {
        try {
            ChatClientManager.e(this.b).set(true);
            IMStaticstic.onAccessStart();
            TimeDelta timeDelta = new TimeDelta();
            String str = HttpClient.getIntentce().get("http://im.qiushibaike.com/imaccess?r=" + System.currentTimeMillis());
            LogUtil.d("time used in get access:" + timeDelta.getDelta());
            LogUtil.d("access_resp:" + str);
            this.a = new JSONObject(str);
            int i = this.a.getInt(NotificationCompat.CATEGORY_ERROR);
            if (i != 0) {
                return new Pair(Integer.valueOf(i), this.a.optString("err_msg", "获取IM连接地址失败"));
            }
            this.c = this.a.optString(g.P);
            ChatClientManager.c(this.b, 0);
            LogUtil.d("tmpHost:" + this.c);
            return new Pair(Integer.valueOf(i), this.a.optString("err_msg"));
        } catch (Exception e) {
            e.printStackTrace();
            return new Pair(Integer.valueOf(9999), HttpClient.getLocalErrorStr());
        }
    }

    protected void a(Pair<Integer, String> pair) {
        LogUtil.d("thread id:" + Thread.currentThread());
        LogUtil.d("on post execute");
        LogUtil.d("pair.first" + pair.first);
        LogUtil.d("pair.second" + ((String) pair.second));
        Logger.getInstance().debug(ChatClientManager.a(), "onPostExecute", String.format("获取IM链接地址[ThreadId:%s, 错误码:%s, 错误描述:%s]", new Object[]{Thread.currentThread(), pair.first, pair.second}));
        if (((Integer) pair.first).intValue() == 0) {
            IMStaticstic.onAccessSuccess();
        } else {
            IMStaticstic.onAccessFailed();
        }
        if (TextUtils.isEmpty(this.c)) {
            this.c = ChatClientManager.f(this.b);
            Logger.getInstance().debug(ChatClientManager.a(), "onPostExecute", "获取Access失败，use last connect host:" + this.c);
        } else {
            ChatClientManager.a(this.b, this.c);
            Logger.getInstance().debug(ChatClientManager.a(), "onPostExecute", "获取Access成功 " + this.c);
        }
        if (TextUtils.isEmpty(this.c)) {
            Logger.getInstance().debug(ChatClientManager.a(), "onPostExecute", String.format("获取IM连接地址失败，稍后后重新获取IM连接地址[ThreadId:%s]", new Object[]{Thread.currentThread()}));
            this.b.connectLater();
        } else {
            this.b.connect("tcp://" + this.c);
        }
        ChatClientManager.g(this.b);
        ChatClientManager.e(this.b).set(false);
    }
}
