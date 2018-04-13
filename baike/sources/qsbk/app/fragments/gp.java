package qsbk.app.fragments;

import com.umeng.commonsdk.proguard.g;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.EventWindow;
import qsbk.app.model.FoundFragementItem.Duobao;
import qsbk.app.model.FoundFragementItem.FoundChicken;
import qsbk.app.model.FoundFragementItem.FoundGame;
import qsbk.app.utils.SharePreferenceUtils;

class gp implements SimpleCallBack {
    final /* synthetic */ MyProfileFragment a;

    gp(MyProfileFragment myProfileFragment) {
        this.a = myProfileFragment;
    }

    public void onSuccess(JSONObject jSONObject) {
        try {
            FoundGame foundGame = new FoundGame(jSONObject.optJSONObject(EventWindow.JUMP_GAME));
            FoundChicken foundChicken = new FoundChicken(jSONObject.optJSONObject("video"));
            Duobao duobao = new Duobao(jSONObject.optJSONObject(EventWindow.JUMP_DUOBAO));
            int i = jSONObject.getInt(g.az);
            FoundFragment.foundStaticGame = foundGame;
            FoundFragment.foundStaticChicken = foundChicken;
            MyProfileFragment.duobao = duobao;
            SharePreferenceUtils.setSharePreferencesValue("found_chicken_and_game", jSONObject.toString());
            SharePreferenceUtils.setSharePreferencesValue("found_interval", i);
            this.a.a();
        } catch (JSONException e) {
            this.a.getFoundInfoFromLocal();
            e.printStackTrace();
        } catch (Exception e2) {
            this.a.getFoundInfoFromLocal();
            e2.printStackTrace();
        }
    }

    public void onFailure(int i, String str) {
        this.a.getFoundInfoFromLocal();
    }
}
