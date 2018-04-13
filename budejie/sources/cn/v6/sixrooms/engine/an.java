package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class an extends VLAsyncHandler<String> {
    final /* synthetic */ String a;
    final /* synthetic */ PropListEngine b;

    an(PropListEngine propListEngine, String str) {
        this.b = propListEngine;
        this.a = str;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                JSONObject jSONObject2 = jSONObject.getJSONObject("content");
                String string = jSONObject.getString("flag");
                if ("001".equals(string)) {
                    int i;
                    JSONObject jSONObject3;
                    int i2 = 0;
                    int i3 = 0;
                    int i4 = 0;
                    int i5 = 0;
                    int i6 = 0;
                    JSONArray jSONArray = jSONObject2.getJSONArray("1");
                    for (i = 0; i < jSONArray.length(); i++) {
                        jSONObject3 = jSONArray.getJSONObject(i);
                        if (jSONObject3.getString("switch").equals("1")) {
                            String string2 = jSONObject3.getString("propid");
                            Object obj = -1;
                            switch (string2.hashCode()) {
                                case 1687134:
                                    if (string2.equals("7104")) {
                                        obj = null;
                                        break;
                                    }
                                    break;
                                case 1687135:
                                    if (string2.equals("7105")) {
                                        obj = 1;
                                        break;
                                    }
                                    break;
                                case 1691138:
                                    if (string2.equals("7559")) {
                                        obj = 2;
                                        break;
                                    }
                                    break;
                            }
                            switch (obj) {
                                case null:
                                    i4 = 1;
                                    break;
                                case 1:
                                    i3 = 1;
                                    break;
                                case 2:
                                    i2 = 1;
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                    JSONArray jSONArray2 = jSONObject2.getJSONArray("3");
                    for (i = 0; i < jSONArray2.length(); i++) {
                        jSONObject3 = jSONArray2.getJSONObject(i);
                        if (jSONObject3.getString("switch").equals("1")) {
                            String string3 = jSONObject3.getString("propid");
                            if (string3.equals("7570") || string3.equals("7569")) {
                                JSONArray jSONArray3 = jSONObject3.getJSONArray("list");
                                int i7 = i6;
                                int i8 = i7;
                                for (i6 = 0; i6 < jSONArray3.length(); i6++) {
                                    JSONObject jSONObject4 = jSONArray3.getJSONObject(i6);
                                    String string4 = jSONObject4.getString(HistoryOpenHelper.COLUMN_UID);
                                    if (jSONObject4.getString("switch").equals("1") && this.a.equals(string4)) {
                                        if (string3.equals("7570")) {
                                            i8 = 1;
                                        } else if (string3.equals("7569")) {
                                            i5 = 1;
                                        }
                                    }
                                }
                                i6 = i8;
                            }
                        }
                    }
                    PropListEngine.b(this.b).result(i2, i3, i4, i5, i6);
                    return;
                }
                PropListEngine.a(this.b).handleErrorInfo(string, jSONObject.getString("content"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && PropListEngine.a(this.b) != null) {
            PropListEngine.a(this.b).error(1006);
        }
    }
}
