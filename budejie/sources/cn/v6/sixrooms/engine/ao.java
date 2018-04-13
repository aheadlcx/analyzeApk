package cn.v6.sixrooms.engine;

import android.text.TextUtils;
import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.bean.CarBean;
import cn.v6.sixrooms.bean.PropBean;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.utils.DrawableResourceUtils;
import cn.v6.sixrooms.utils.JsonParseUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class ao extends VLAsyncHandler<String> {
    final /* synthetic */ PropListEngine a;

    ao(PropListEngine propListEngine) {
        this.a = propListEngine;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("content");
                String string2 = jSONObject.getString("flag");
                if ("001".equals(string2)) {
                    List arrayList = new ArrayList();
                    if (TextUtils.isEmpty(string)) {
                        PropListEngine.a(this.a).result(arrayList);
                        return;
                    }
                    JSONObject jSONObject2 = new JSONObject(string);
                    int i = 1;
                    while (i < PropListEngine.c(this.a).length + 1) {
                        JSONArray jSONArray = jSONObject2.getJSONArray(String.valueOf(i));
                        int i2 = 0;
                        while (i2 < jSONArray.length()) {
                            JSONObject jSONObject3 = jSONArray.getJSONObject(i2);
                            PropBean propBean = (PropBean) JsonParseUtils.json2Obj(jSONObject3.toString(), PropBean.class);
                            string2 = propBean.getPropid();
                            if (i == 2) {
                                CarBean carType = DrawableResourceUtils.getCarType(string2, "");
                                if (carType != null) {
                                    propBean.setPropImgId(carType.getCar());
                                } else {
                                    i2++;
                                }
                            } else {
                                int propImageResourceByPropId = DrawableResourceUtils.getPropImageResourceByPropId(string2);
                                if (propImageResourceByPropId != -1) {
                                    propBean.setPropImgId(propImageResourceByPropId);
                                } else {
                                    i2++;
                                }
                            }
                            if (i == 3 || i == 4 || i == 6) {
                                Object string3 = JsonParseUtils.getString(jSONObject3, "list");
                                if (!TextUtils.isEmpty(string3)) {
                                    JSONArray jSONArray2 = new JSONArray(string3);
                                    for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                                        propBean = (PropBean) JsonParseUtils.json2Obj(jSONObject3.toString(), PropBean.class);
                                        propBean.setPropImgId(DrawableResourceUtils.getPropImageResourceByPropId(propBean.getPropid()));
                                        arrayList.add(PropListEngine.a(this.a, propBean, jSONArray2.getJSONObject(i3), JsonParseUtils.getString(jSONObject3, "switch"), i));
                                    }
                                }
                                i2++;
                            } else {
                                propBean.setState(JsonParseUtils.getString(jSONObject3, "switch"));
                                if (string2.equals("7551")) {
                                    propBean.setTag("特殊座驾");
                                } else {
                                    propBean.setTag(PropListEngine.c(this.a)[i - 1]);
                                }
                                propBean.setTypeTag(i);
                                arrayList.add(propBean);
                                i2++;
                            }
                        }
                        i++;
                    }
                    PropListEngine.a(this.a).result(arrayList);
                    return;
                }
                PropListEngine.a(this.a).handleErrorInfo(string2, string);
            } catch (JSONException e) {
                PropListEngine.a(this.a).error(1007);
                e.printStackTrace();
            }
        } else if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && PropListEngine.a(this.a) != null) {
            PropListEngine.a(this.a).error(1006);
        }
    }
}
