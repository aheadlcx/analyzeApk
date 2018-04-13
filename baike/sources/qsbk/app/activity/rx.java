package qsbk.app.activity;

import com.umeng.commonsdk.proguard.g;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.fragments.FoundFragment;
import qsbk.app.fragments.MyProfileFragment;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.EventWindow;
import qsbk.app.model.FoundFragementItem.Duobao;
import qsbk.app.model.FoundFragementItem.FoundChicken;
import qsbk.app.model.FoundFragementItem.FoundGame;
import qsbk.app.utils.SharePreferenceUtils;

class rx implements SimpleCallBack {
    final /* synthetic */ MainActivity a;

    rx(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void onSuccess(JSONObject jSONObject) {
        try {
            FoundGame foundGame = new FoundGame(jSONObject.optJSONObject(EventWindow.JUMP_GAME));
            FoundChicken foundChicken = new FoundChicken(jSONObject.optJSONObject("video"));
            Duobao duobao = new Duobao(jSONObject.optJSONObject(EventWindow.JUMP_DUOBAO));
            long j = (long) jSONObject.getInt(g.az);
            FoundFragment.foundStaticChicken = foundChicken;
            FoundFragment.foundStaticGame = foundGame;
            MyProfileFragment.duobao = duobao;
            long j2 = foundGame.timestamp;
            long j3 = foundChicken.timestamp;
            SharePreferenceUtils.setSharePreferencesValue("found_chicken_and_game", jSONObject.toString());
            SharePreferenceUtils.setSharePreferencesValue("found_interval", j);
            if ((foundGame.show && MainActivity.oldFoundGameTimestamp != j2) || (foundChicken.show && MainActivity.oldFoundChickenTimestamp != j3)) {
                if (!this.a.foundHaveRefresh) {
                    this.a.foundHaveRefresh = true;
                }
                SharePreferenceUtils.setSharePreferencesValue(MainActivity.FOUND_HAVE_REFRESH, this.a.foundHaveRefresh);
                if (foundGame.show && MainActivity.oldFoundGameTimestamp != j2) {
                    FoundFragment.foundGameHasTips = true;
                    SharePreferenceUtils.setSharePreferencesValue(FoundFragment.FOUND_GAME_HAS_TIPS, true);
                }
                if (foundChicken.show && MainActivity.oldFoundChickenTimestamp != j3) {
                    SharePreferenceUtils.setSharePreferencesValue(FoundFragment.FOUND_CHICKEN_HAS_TIPS, true);
                    FoundFragment.foundChickenHasTips = true;
                }
            }
            if (!this.a.duobaoHavaRefresh || this.a.foundHaveRefresh) {
                this.a.setSmallTips(MainActivity.TAB_MIME_ID);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void onFailure(int i, String str) {
    }
}
