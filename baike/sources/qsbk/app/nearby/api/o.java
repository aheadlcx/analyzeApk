package qsbk.app.nearby.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.core.model.CustomButton;
import qsbk.app.core.model.FamilyInfo;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.ToastAndDialog;

class o implements SimpleCallBack {
    final /* synthetic */ PersonalListener a;

    o(PersonalListener personalListener) {
        this.a = personalListener;
    }

    public void onSuccess(JSONObject jSONObject) {
        FamilyInfo familyInfo = null;
        int i = -1;
        try {
            int optInt;
            int optInt2;
            BaseUserInfo[] baseUserInfoArr;
            if (jSONObject.has("receive_total")) {
                optInt = jSONObject.optInt("receive_total");
            } else {
                optInt = -1;
            }
            if (jSONObject.has("level")) {
                optInt2 = jSONObject.optInt("level");
            } else {
                optInt2 = -1;
            }
            if (jSONObject.has("send_total")) {
                i = jSONObject.optInt("send_total");
            }
            JSONArray jSONArray = jSONObject.getJSONArray("cr");
            if (jSONArray == null || jSONArray.length() <= 0) {
                baseUserInfoArr = null;
            } else {
                baseUserInfoArr = new BaseUserInfo[jSONArray.length()];
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    BaseUserInfo createAnonymous = BaseUserInfo.createAnonymous();
                    createAnonymous.parseBaseInfo(jSONArray.getJSONObject(i2));
                    baseUserInfoArr[i2] = createAnonymous;
                }
            }
            JSONObject optJSONObject = jSONObject.optJSONObject("family_info");
            if (optJSONObject != null) {
                familyInfo = new FamilyInfo();
                familyInfo.fid = (long) optJSONObject.optInt("fid");
                familyInfo.b = optJSONObject.optString(CustomButton.POSITION_BOTTOM);
                familyInfo.c = optJSONObject.optInt("c");
                familyInfo.n = optJSONObject.optString("n");
                familyInfo.r = optJSONObject.optString(CustomButton.POSITION_RIGHT);
            }
            this.a.g.getPersonalLiveSucc(baseUserInfoArr, optInt, optInt2, i, familyInfo);
        } catch (JSONException e) {
            ToastAndDialog.makeNegativeToast(this.a.k, "数据解析出错", Integer.valueOf(1)).show();
            this.a.g.getPersonalLiveFailed();
        } catch (Exception e2) {
            e2.printStackTrace();
            ToastAndDialog.makeNegativeToast(this.a.k, HttpClient.getLocalErrorStr(), Integer.valueOf(1)).show();
            this.a.g.getPersonalLiveFailed();
        }
    }

    public void onFailure(int i, String str) {
        this.a.g.getPersonalLiveFailed();
        if (!this.a.l) {
            this.a.l = true;
            ToastAndDialog.makeNegativeToast(this.a.k, str, Integer.valueOf(0)).show();
        }
    }
}
