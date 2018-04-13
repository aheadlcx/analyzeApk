package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.base.VLScheduler;
import cn.v6.sixrooms.constants.CommonInts;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.utils.JsonParseUtils;
import org.json.JSONException;
import org.json.JSONObject;

final class at extends VLAsyncHandler<String> {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;
    final /* synthetic */ String e;
    final /* synthetic */ RoomInfoEngine f;

    at(RoomInfoEngine roomInfoEngine, String str, String str2, String str3, String str4, String str5) {
        this.f = roomInfoEngine;
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject((String) getParam());
                String string = jSONObject.getString("flag");
                if ("001".equals(string)) {
                    if (CommonStrs.ROOMINFOENGINE_COMMON.equals(this.a)) {
                        VLScheduler.instance.schedule(0, 2, new au(this.f, jSONObject));
                    } else if (CommonStrs.ROOMINFOENGINE_PRIV.equals(this.a)) {
                        VLScheduler.instance.schedule(0, 2, new ax(this.f, jSONObject));
                    }
                    String[] split = JsonParseUtils.getString(jSONObject.getJSONObject("content").getJSONObject("liveinfo"), "sound").replace(" ", "").trim().split(":");
                    if (split.length == 2) {
                        this.f.a.getMicroIP_PORT(split[0], split[1]);
                    }
                } else if (this.f.c < 3) {
                    this.f.c = this.f.c + 1;
                    this.f.a(this.a, this.b, this.c, this.d, this.e);
                } else {
                    this.f.c = 0;
                    this.f.a.handleErrorInfo(string, jSONObject.getString("content"));
                }
            } catch (JSONException e) {
                JSONException jSONException = e;
                if (this.f.c < 3) {
                    this.f.c = this.f.c + 1;
                    this.f.a(this.a, this.b, this.c, this.d, this.e);
                } else {
                    this.f.c = 0;
                    this.f.a.error(1007);
                }
                jSONException.printStackTrace();
            } catch (NumberFormatException e2) {
                this.f.a.error(CommonInts.NUMBER_FORMAT_EXCEPTION);
                e2.printStackTrace();
            } catch (StringIndexOutOfBoundsException e3) {
                this.f.a.error(CommonInts.STRING_OUTOFBOUNDS_EXCEPTION);
                e3.printStackTrace();
            }
        } else if (this.f.c < 3) {
            this.f.c = this.f.c + 1;
            this.f.a(this.a, this.b, this.c, this.d, this.e);
        } else {
            this.f.c = 0;
            if (CommonStrs.NET_CONNECT_FAIL.equals(getStr()) && this.f.a != null) {
                this.f.a.error(1006);
            }
        }
    }
}
