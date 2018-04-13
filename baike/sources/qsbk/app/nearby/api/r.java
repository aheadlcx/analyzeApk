package qsbk.app.nearby.api;

import android.util.Pair;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.core.AsyncTask;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.HttpClient;

class r extends AsyncTask<String, Void, Pair<Integer, String>> {
    final /* synthetic */ RandomLocationMgr a;

    r(RandomLocationMgr randomLocationMgr) {
        this.a = randomLocationMgr;
    }

    protected Pair<Integer, String> a(String... strArr) {
        RandomLocationMgr.a(this.a, true);
        try {
            JSONObject jSONObject = new JSONObject(HttpClient.getIntentce().get(String.format(Constants.URL_RANDOM_DOOR_UPDATE, new Object[]{RandomLocationMgr.b(this.a)})));
            if (jSONObject.has("data")) {
                String string = jSONObject.getString("data");
                if (string != null) {
                    QsbkApp.mContext.getSharedPreferences("random_door", 0).edit().putString("d", string).apply();
                    this.a.destroy();
                    RandomLocationMgr.a(this.a);
                }
                return new Pair(Integer.valueOf(0), string);
            }
        } catch (QiushibaikeException e) {
            e.printStackTrace();
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return null;
    }

    protected void a(Pair<Integer, String> pair) {
        RandomLocationMgr.b(this.a, true);
        RandomLocationMgr.a(this.a, false);
    }
}
