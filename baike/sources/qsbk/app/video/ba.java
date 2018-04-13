package qsbk.app.video;

import android.support.v4.app.NotificationCompat;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.core.AsyncTask;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.HttpClient;
import qsbk.app.video.VideoLoopStatistics.ICallback;

class ba extends AsyncTask<Void, Void, String> {
    final /* synthetic */ ICallback a;
    final /* synthetic */ String b;
    final /* synthetic */ int c;
    final /* synthetic */ VideoLoopStatistics d;

    ba(VideoLoopStatistics videoLoopStatistics, ICallback iCallback, String str, int i) {
        this.d = videoLoopStatistics;
        this.a = iCallback;
        this.b = str;
        this.c = i;
    }

    protected void a(String str) {
        if (this.a != null) {
            if (str != null) {
                try {
                    if (new JSONObject(str).getInt(NotificationCompat.CATEGORY_ERROR) == 0) {
                        this.a.onSuccess(this.b);
                        return;
                    } else {
                        this.a.onFailure(this.b, null, null);
                        return;
                    }
                } catch (Exception e) {
                    this.a.onFailure(this.b, str, e);
                }
            }
            this.a.onFailure(this.b, null, null);
        }
    }

    protected String a(Void... voidArr) {
        String format = String.format(Constants.VIDEO_LOOP, new Object[]{this.b});
        try {
            Map hashMap = new HashMap();
            hashMap.put("rand_count", Integer.valueOf(this.c));
            format = HttpClient.getIntentce().post(format, hashMap);
            Log.e(VideoLoopStatistics.class.getSimpleName(), "loop value " + format + " , loop count " + this.c);
            return format;
        } catch (QiushibaikeException e) {
            e.printStackTrace();
            return null;
        }
    }
}
