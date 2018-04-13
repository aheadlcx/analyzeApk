package qsbk.app.model;

import com.umeng.commonsdk.proguard.g;
import org.json.JSONObject;
import qsbk.app.im.emotion.QiubaiEmotionProvider;

public class NewsAdControl {
    public int bdRatio;
    public int gdtRatio;
    public int interval;
    public boolean isShow;
    public boolean isUseQsRatio;
    public int position;
    public int qbRatio;
    public int qhRatio;
    public boolean showBdAd;
    public boolean showGdtAd;
    public boolean showQbAd;
    public boolean showQhAd;
    public boolean showS2sAd;

    public NewsAdControl() {
        this.isShow = true;
        this.isUseQsRatio = true;
        this.position = 4;
        this.interval = 4;
        this.showBdAd = false;
        this.showGdtAd = false;
        this.showQbAd = false;
        this.showS2sAd = false;
        this.showQhAd = false;
        this.bdRatio = 0;
        this.gdtRatio = 0;
        this.qbRatio = 0;
        this.qhRatio = 0;
        this.showGdtAd = true;
        this.gdtRatio = 50;
        this.showBdAd = true;
        this.bdRatio = 50;
    }

    public NewsAdControl(JSONObject jSONObject) {
        this.isShow = true;
        this.isUseQsRatio = true;
        this.position = 4;
        this.interval = 4;
        this.showBdAd = false;
        this.showGdtAd = false;
        this.showQbAd = false;
        this.showS2sAd = false;
        this.showQhAd = false;
        this.bdRatio = 0;
        this.gdtRatio = 0;
        this.qbRatio = 0;
        this.qhRatio = 0;
        if (jSONObject != null) {
            if (!jSONObject.isNull("position")) {
                this.position = jSONObject.optInt("position");
            }
            if (!jSONObject.isNull(g.az)) {
                this.interval = jSONObject.optInt(g.az);
            }
            if (!jSONObject.isNull("isShowAd")) {
                this.isShow = jSONObject.optBoolean("isShowAd", true);
            }
            if (!jSONObject.isNull("isUseQsRatio")) {
                this.isUseQsRatio = jSONObject.optBoolean("isUseQsRatio", true);
            }
            if (!jSONObject.isNull("source")) {
                JSONObject optJSONObject = jSONObject.optJSONObject("source");
                if (!optJSONObject.isNull("bd")) {
                    this.showBdAd = true;
                    this.bdRatio = optJSONObject.optInt("bd");
                }
                if (!optJSONObject.isNull("gdt")) {
                    this.showGdtAd = true;
                    this.gdtRatio = optJSONObject.optInt("gdt");
                }
                if (!optJSONObject.isNull(QiubaiEmotionProvider.NAMESPACE)) {
                    this.showQbAd = true;
                    this.qbRatio = optJSONObject.optInt(QiubaiEmotionProvider.NAMESPACE);
                }
                if (!optJSONObject.isNull("qh")) {
                    this.showQhAd = true;
                    this.qhRatio = optJSONObject.optInt("qh");
                }
            }
        }
    }
}
