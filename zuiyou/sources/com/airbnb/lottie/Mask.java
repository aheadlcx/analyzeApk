package com.airbnb.lottie;

import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import org.json.JSONObject;

class Mask {
    private final MaskMode a;
    private final h b;

    enum MaskMode {
        MaskModeAdd,
        MaskModeSubtract,
        MaskModeIntersect,
        MaskModeUnknown
    }

    static class a {
        static Mask a(JSONObject jSONObject, ai aiVar) {
            MaskMode maskMode;
            String optString = jSONObject.optString("mode");
            Object obj = -1;
            switch (optString.hashCode()) {
                case 97:
                    if (optString.equals("a")) {
                        obj = null;
                        break;
                    }
                    break;
                case 105:
                    if (optString.equals("i")) {
                        obj = 2;
                        break;
                    }
                    break;
                case 115:
                    if (optString.equals(NotifyType.SOUND)) {
                        obj = 1;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    maskMode = MaskMode.MaskModeAdd;
                    break;
                case 1:
                    maskMode = MaskMode.MaskModeSubtract;
                    break;
                case 2:
                    maskMode = MaskMode.MaskModeIntersect;
                    break;
                default:
                    maskMode = MaskMode.MaskModeUnknown;
                    break;
            }
            return new Mask(maskMode, a.a(jSONObject.optJSONObject("pt"), aiVar));
        }
    }

    private Mask(MaskMode maskMode, h hVar) {
        this.a = maskMode;
        this.b = hVar;
    }

    MaskMode a() {
        return this.a;
    }

    h b() {
        return this.b;
    }
}
