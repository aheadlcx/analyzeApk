package qsbk.app.core.utils;

import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.core.model.LevelData;
import qsbk.app.core.model.TitleData;
import qsbk.app.core.model.User;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.core.net.response.ConfigResponse;

class e extends Callback {
    final /* synthetic */ int a;
    final /* synthetic */ ConfigInfoUtil b;

    e(ConfigInfoUtil configInfoUtil, int i) {
        this.b = configInfoUtil;
        this.a = i;
    }

    public Map<String, String> getParams() {
        User user = AppUtils.getInstance().getUserInfoProvider().getUser();
        Map<String, String> hashMap = new HashMap();
        hashMap.put("config_version", String.valueOf(this.a));
        if (user != null) {
            hashMap.put("user_id", Long.toString(user.getOriginId()));
        }
        return hashMap;
    }

    public void onSuccess(BaseResponse baseResponse) {
        Map hashMap;
        this.b.d = System.currentTimeMillis();
        ConfigResponse configResponse = (ConfigResponse) baseResponse.getResponse(new f(this));
        if (configResponse == null || (configResponse.config_version <= this.a && !(this.a == 0 && configResponse.config_version == 0))) {
            this.b.b = this.b.getLocalConfig();
            boolean z = false;
            if (!(this.b.b == null || configResponse == null || configResponse.android_price_data == null || configResponse.android_price_data.isEmpty())) {
                this.b.b.android_price_data = configResponse.android_price_data;
                z = true;
            }
            if (!(this.b.b == null || configResponse == null || configResponse.gift_counts == null || configResponse.gift_counts.isEmpty())) {
                this.b.b.gift_counts = configResponse.gift_counts;
                z = true;
            }
            if (!(this.b.b == null || configResponse == null || configResponse.gift_data == null || configResponse.gift_data.isEmpty())) {
                this.b.b.gift_data = configResponse.gift_data;
                z = true;
            }
            if (z) {
                this.b.getDiskCache().put("remix_config", AppUtils.toJson(this.b.b));
            }
        } else {
            this.b.getDiskCache().put("remix_config", baseResponse.response);
            this.b.b = configResponse;
        }
        Map map = this.b.b != null ? this.b.b.template : null;
        if (map == null) {
            hashMap = new HashMap();
        } else {
            hashMap = map;
        }
        if (!(this.b.b == null || this.b.b.user_title == null)) {
            ImagePipeline imagePipeline = Fresco.getImagePipeline();
            for (TitleData titleData : this.b.b.user_title) {
                if (hashMap.containsKey(titleData.tp)) {
                    imagePipeline.fetchDecodedImage(ImageRequest.fromUri(((String) hashMap.get(titleData.tp)).replace("$", titleData.p)), AppUtils.getInstance().getAppContext()).subscribe(new g(this, titleData), CallerThreadExecutor.getInstance());
                    this.b.mTitleDatas.put(titleData.tk, titleData);
                }
            }
        }
        if (!(this.b.b == null || this.b.b.scene_data == null)) {
            for (LevelData levelData : this.b.b.scene_data.values()) {
                if (hashMap.containsKey(levelData.t)) {
                    String str = (String) hashMap.get(levelData.t);
                    levelData.a = str.replace("$", levelData.a);
                    levelData.l = str.replace("$", levelData.l);
                    levelData.m = str.replace("$", levelData.m);
                    levelData.r = str.replace("$", levelData.r);
                    levelData.la = str.replace("$", levelData.la);
                    levelData.ra = str.replace("$", levelData.ra);
                    if (levelData.z != null) {
                        levelData.z = str.replace("$", levelData.z);
                    }
                }
            }
            this.b.b();
        }
        this.b.a();
        if (this.b.c != null) {
            this.b.c.onSuccess();
        }
        if (!PackageUtils.isPackageDoll(AppUtils.getInstance().getAppContext())) {
            GiftResSync.checkUpdate(true);
        }
    }

    public void onFailed(int i, String str) {
        if (this.b.c != null) {
            this.b.c.onFailed(i);
        }
    }

    public void onFinished() {
        if (this.b.c != null) {
            this.b.c.onFinish();
        }
    }
}
