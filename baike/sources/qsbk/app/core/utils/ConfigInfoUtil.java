package qsbk.app.core.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.util.Pair;
import android.text.TextUtils;
import com.alipay.android.phone.mrpc.core.RpcException.ErrorCode;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobstat.Config;
import com.qiushibaike.statsdk.StatSDK;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import qsbk.app.core.model.BarrageData;
import qsbk.app.core.model.BarrageDecorData;
import qsbk.app.core.model.Bitrate;
import qsbk.app.core.model.DecorateData;
import qsbk.app.core.model.Diamond;
import qsbk.app.core.model.FrameAnimationData;
import qsbk.app.core.model.GameConfig;
import qsbk.app.core.model.GiftCount;
import qsbk.app.core.model.GiftData;
import qsbk.app.core.model.HitColorData;
import qsbk.app.core.model.LevelData;
import qsbk.app.core.model.MarketData;
import qsbk.app.core.model.RedEnvelopesConfig;
import qsbk.app.core.model.RichUser;
import qsbk.app.core.model.TitleData;
import qsbk.app.core.model.User;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.net.response.ConfigResponse;
import qsbk.app.fragments.QiushiNotificationListFragment;
import qsbk.app.utils.ListViewHelper;
import qsbk.app.utils.UIHelper$Theme;

public class ConfigInfoUtil {
    private static ConfigInfoUtil a;
    private ConfigResponse b = getLocalConfig();
    private UpdateConfigCallback c;
    private long d;
    private ACache e;
    private String f = UrlConstants.REMIX_CONFIG;
    public Map<Long, FrameAnimationData> frameAnimations = new HashMap();
    public List<GiftData> mLiveGiftData = new ArrayList();
    public Map<String, TitleData> mTitleDatas = new HashMap();
    public Map<String, Drawable> mTitleDrawables = new HashMap();
    public List<GiftData> mVideoGiftData = new ArrayList();
    public Map<Pair<Integer, Integer>, LevelData> sceneMaps = new HashMap();

    public interface UpdateConfigCallback {
        void onFailed(int i);

        void onFinish();

        void onSuccess();
    }

    public static synchronized ConfigInfoUtil instance() {
        ConfigInfoUtil configInfoUtil;
        synchronized (ConfigInfoUtil.class) {
            if (a == null) {
                a = new ConfigInfoUtil();
            }
            configInfoUtil = a;
        }
        return configInfoUtil;
    }

    private ConfigInfoUtil() {
        a();
    }

    private void a() {
        if (this.b != null) {
            String str;
            FrameAnimationData frameAnimationData;
            long longValue;
            this.frameAnimations.clear();
            this.mLiveGiftData.clear();
            this.mVideoGiftData.clear();
            String str2 = "https://livegift.app-remix.com/$";
            if (this.b.template == null || !this.b.template.containsKey(IXAdRequestInfo.GPS)) {
                str = str2;
            } else {
                str = (String) this.b.template.get(IXAdRequestInfo.GPS);
            }
            if (this.b.all_gift_data != null) {
                long longValue2;
                for (GiftData giftData : this.b.all_gift_data.values()) {
                    if (!(giftData.ig == null || giftData.ig.startsWith("http"))) {
                        giftData.ig = str.replace("$", giftData.ig);
                    }
                    if (!(giftData.an == null || giftData.an.startsWith("http"))) {
                        giftData.an = str.replace("$", giftData.an);
                    }
                    if (giftData.ga != null && giftData.ga.length > 0) {
                        frameAnimationData = giftData.ga[0];
                        if (!(frameAnimationData.r == null || frameAnimationData.r.startsWith("http"))) {
                            frameAnimationData.r = str.replace("$", frameAnimationData.r);
                        }
                        this.frameAnimations.put(Long.valueOf(giftData.gd), frameAnimationData);
                    }
                    if (giftData.gb != null && giftData.gb.length > 0) {
                        frameAnimationData = giftData.gb[0];
                        if (!(frameAnimationData.r == null || frameAnimationData.r.startsWith("http"))) {
                            frameAnimationData.r = str.replace("$", frameAnimationData.r);
                        }
                        this.frameAnimations.put(Long.valueOf(giftData.gd), frameAnimationData);
                    }
                }
                if (this.b.gift_data != null) {
                    for (Long longValue3 : this.b.gift_data) {
                        longValue2 = longValue3.longValue();
                        if (this.b.all_gift_data.containsKey(Long.valueOf(longValue2))) {
                            this.mLiveGiftData.add(this.b.all_gift_data.get(Long.valueOf(longValue2)));
                        }
                    }
                }
                if (this.b.video_gift_data != null) {
                    for (Long longValue32 : this.b.video_gift_data) {
                        longValue2 = longValue32.longValue();
                        if (this.b.all_gift_data.containsKey(Long.valueOf(longValue2))) {
                            this.mVideoGiftData.add(this.b.all_gift_data.get(Long.valueOf(longValue2)));
                        }
                    }
                }
                if (this.b.games != null) {
                    for (String str22 : this.b.games.keySet()) {
                        GameConfig gameConfig = (GameConfig) this.b.games.get(str22);
                        if (gameConfig != null) {
                            if (gameConfig.giftDatas == null) {
                                gameConfig.giftDatas = new ArrayList();
                            } else {
                                gameConfig.giftDatas.clear();
                            }
                            if (gameConfig.gifts != null) {
                                for (Long longValue4 : gameConfig.gifts) {
                                    longValue = longValue4.longValue();
                                    if (this.b.all_gift_data.containsKey(Long.valueOf(longValue))) {
                                        gameConfig.giftDatas.add(this.b.all_gift_data.get(Long.valueOf(longValue)));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (this.b.anime_effect != null) {
                for (GiftData giftData2 : this.b.anime_effect) {
                    if (!(giftData2.ig == null || giftData2.ig.startsWith("http"))) {
                        giftData2.ig = str.replace("$", giftData2.ig);
                    }
                    if (!(giftData2.an == null || giftData2.an.startsWith("http"))) {
                        giftData2.an = str.replace("$", giftData2.an);
                    }
                    if (giftData2.ga != null && giftData2.ga.length > 0) {
                        frameAnimationData = giftData2.ga[0];
                        frameAnimationData.r = str.replace("$", frameAnimationData.r);
                        this.frameAnimations.put(Long.valueOf(giftData2.gd), frameAnimationData);
                    }
                    if (giftData2.gb != null && giftData2.gb.length > 0) {
                        frameAnimationData = giftData2.gb[0];
                        frameAnimationData.r = str.replace("$", frameAnimationData.r);
                        this.frameAnimations.put(Long.valueOf(giftData2.gd), frameAnimationData);
                    }
                }
            }
            String str3 = CachePath.REMIX_GIFT_PATH + MqttTopic.TOPIC_LEVEL_SEPARATOR;
            File file = new File(str3);
            if (file.exists()) {
                for (String parseLong : file.list()) {
                    String parseLong2;
                    longValue = Long.parseLong(parseLong2);
                    if (!this.frameAnimations.containsKey(Long.valueOf(longValue))) {
                        parseLong2 = str3 + longValue;
                        if (new File(parseLong2).exists()) {
                            FileUtils.deleteDir(parseLong2, true);
                        }
                    }
                }
            }
            File file2 = new File(CachePath.REMIX_MARKET_PATH + MqttTopic.TOPIC_LEVEL_SEPARATOR);
            if (file2.exists()) {
                for (String parseLong3 : file2.list()) {
                    String parseLong32;
                    longValue = Long.parseLong(parseLong32);
                    if (!(this.b.scene_ani == null || this.b.scene_ani.containsKey(Long.valueOf(longValue)))) {
                        parseLong32 = str3 + longValue;
                        if (new File(parseLong32).exists()) {
                            FileUtils.deleteDir(parseLong32, true);
                        }
                    }
                }
            }
        }
    }

    public ACache getDiskCache() {
        Context appContext = AppUtils.getInstance().getAppContext();
        if (this.e == null) {
            try {
                this.e = ACache.get(appContext, "config");
            } catch (RuntimeException e) {
                this.e = ACache.get(appContext.getCacheDir());
                StatSDK.onEvent(appContext, "acache_create_failed", "");
            }
        }
        return this.e;
    }

    public ConfigResponse getLocalConfig() {
        Object asString = getDiskCache().getAsString("remix_config");
        if (!TextUtils.isEmpty(asString)) {
            try {
                return (ConfigResponse) AppUtils.getGson().fromJson(asString, ConfigResponse.class);
            } catch (Exception e) {
                e.printStackTrace();
                deleteConfig();
            }
        }
        return null;
    }

    public int getConfigVersion() {
        return (this.b == null || this.b.config_version < 0) ? 0 : this.b.config_version;
    }

    public void updateConfigInfo() {
        updateConfigInfo(false);
    }

    public void updateConfigInfo(boolean z) {
        if (z || System.currentTimeMillis() - this.d >= ListViewHelper.DEFAULT_TIPS_TO_REFRESH_INTERVAL || this.c != null) {
            LogUtils.d("loading remix config...");
            try {
                NetRequest.getInstance().get(this.f, new e(this, getConfigVersion()), "remix_config", true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setPriceListUrl(String str) {
        this.f = str;
    }

    public void deleteConfig() {
        getDiskCache().clear();
        clearConfigCache();
    }

    public void deleteConfigAndUpdate() {
        deleteConfig();
        updateConfigInfo();
    }

    public int getQbaPercent() {
        return this.b != null ? this.b.qba : 100;
    }

    public String getHelpMsg() {
        return this.b != null ? this.b.help_msg : null;
    }

    public String getHelpUrl() {
        return this.b != null ? this.b.help_url : null;
    }

    public Map<String, BarrageData> getBarrageMap() {
        return this.b != null ? this.b.barrage_map : null;
    }

    public List<BarrageData> getBarrageList() {
        Map barrageMap = getBarrageMap();
        List<BarrageData> arrayList = new ArrayList();
        if (barrageMap != null) {
            for (String str : barrageMap.keySet()) {
                BarrageData barrageData = (BarrageData) barrageMap.get(str);
                if (TextUtils.isEmpty(barrageData.b) || TextUtils.isEmpty(barrageData.e) || (TimeUtils.isArrivedTime(barrageData.b) && !TimeUtils.isArrivedTime(barrageData.e))) {
                    barrageData.t = str;
                    arrayList.add(barrageData);
                }
            }
        }
        return arrayList;
    }

    public long getBarragePrice(String str) {
        Map barrageMap = getBarrageMap();
        if (barrageMap == null || !barrageMap.containsKey(str)) {
            return 1;
        }
        return ((BarrageData) barrageMap.get(str)).p;
    }

    public Map<String, BarrageDecorData> getBarrageDecorMap() {
        return this.b != null ? this.b.barrage_bg_map : null;
    }

    public BarrageDecorData getBarrageDecorData(String str) {
        Map barrageDecorMap = getBarrageDecorMap();
        if (barrageDecorMap == null || !barrageDecorMap.containsKey(str)) {
            return null;
        }
        return (BarrageDecorData) barrageDecorMap.get(str);
    }

    public int getEnterMarqueeMinLevel() {
        int i;
        if (this.b != null) {
            i = this.b.effect;
        } else {
            i = 30;
        }
        if (i < 30) {
            return 30;
        }
        return i;
    }

    public List<Integer> getGiftBlossomHitValues() {
        List<Integer> list = null;
        if (this.b != null) {
            list = this.b.blossom;
        }
        if (list == null) {
            list = new ArrayList();
        }
        if (list.size() == 0) {
            list.addAll(Arrays.asList(new Integer[]{Integer.valueOf(99), Integer.valueOf(520), Integer.valueOf(666), Integer.valueOf(QiushiNotificationListFragment.REQUEST_FOR_PROMOTE), Integer.valueOf(999), Integer.valueOf(1314), Integer.valueOf(ErrorCode.SERVER_BIZEXCEPTION), Integer.valueOf(8888), Integer.valueOf(9999)}));
            if (this.b != null) {
                this.b.blossom = list;
            }
        }
        return list;
    }

    public List<GiftData> getGiftList() {
        return this.mLiveGiftData;
    }

    public GiftData getGiftDataById(long j) {
        if (this.b == null || this.b.all_gift_data == null || !this.b.all_gift_data.containsKey(Long.valueOf(j))) {
            return null;
        }
        return (GiftData) this.b.all_gift_data.get(Long.valueOf(j));
    }

    public List<GiftData> getVideoGiftList() {
        return this.mVideoGiftData;
    }

    public Map<String, LevelData> getSceneDataMap() {
        if (this.b != null && this.b.scene_data != null) {
            return this.b.scene_data;
        }
        Map<String, LevelData> hashMap = new HashMap();
        LevelData levelData = new LevelData();
        levelData.a = "http://7xt9fk.com1.z0.glb.clouddn.com/1257e0411000c01.9.png";
        levelData.c = "#ff4c5672";
        levelData.d = "来了";
        levelData.l = "http://7xt9fk.com1.z0.glb.clouddn.com/1256f7eef201201.png";
        levelData.la = "http://7xt9fk.com1.z0.glb.clouddn.com/12572e546a01201.webp";
        levelData.m = "http://7xt9fk.com1.z0.glb.clouddn.com/1256f6cdf001201.png";
        levelData.r = "http://7xt9fk.com1.z0.glb.clouddn.com/1256f6e56601202.png";
        levelData.ra = "http://7xt9fk.com1.z0.glb.clouddn.com/12572e6be001202.webp";
        levelData.t = IXAdRequestInfo.GPS;
        hashMap.put("30:999", levelData);
        return hashMap;
    }

    public List<Diamond> getLocalDiamonds() {
        return this.b != null ? this.b.android_price_data : null;
    }

    public Map<Long, FrameAnimationData> getFrameAnimations() {
        return this.frameAnimations;
    }

    public void clearConfigCache() {
        this.d = 0;
        this.b = null;
    }

    public void setUpdateConfigCallback(UpdateConfigCallback updateConfigCallback) {
        this.c = updateConfigCallback;
    }

    public long getAliPayMax() {
        return (this.b == null || this.b.ali_max == 0) ? 100000 : this.b.ali_max;
    }

    public long getWechatPayMax() {
        return (this.b == null || this.b.wx_max == 0) ? 3000 : this.b.wx_max;
    }

    public double getTitleRatio(String str) {
        if (this.mTitleDatas == null || !this.mTitleDatas.containsKey(str) || this.mTitleDatas.get(str) == null) {
            return 0.0d;
        }
        return ((TitleData) this.mTitleDatas.get(str)).tr;
    }

    public Drawable getTitleDrawable(String str) {
        if (this.mTitleDrawables == null || !this.mTitleDrawables.containsKey(str)) {
            return null;
        }
        return (Drawable) this.mTitleDrawables.get(str);
    }

    public long getGiftPriceById(long j) {
        if (this.b == null || this.b.all_gift_data == null || !this.b.all_gift_data.containsKey(Long.valueOf(j))) {
            return 0;
        }
        return ((GiftData) this.b.all_gift_data.get(Long.valueOf(j))).pr;
    }

    public boolean isDecorateConfigurable() {
        return true;
    }

    public DecorateData getDecorateData() {
        return null;
    }

    public String getGameExplainText(String str) {
        if (this.b == null || this.b.games == null || !this.b.games.containsKey(str)) {
            return null;
        }
        return ((GameConfig) this.b.games.get(str)).explain_str;
    }

    public List<GiftData> getGameGiftList(String str) {
        if (this.b == null || this.b.games == null || !this.b.games.containsKey(str)) {
            return null;
        }
        return ((GameConfig) this.b.games.get(str)).giftDatas;
    }

    public Map<String, String> getGameButtonMap(String str) {
        if (this.b == null || this.b.games == null || !this.b.games.containsKey(str)) {
            return null;
        }
        return ((GameConfig) this.b.games.get(str)).buttons;
    }

    private void b() {
        this.sceneMaps.clear();
        Map sceneDataMap = getSceneDataMap();
        if (sceneDataMap != null) {
            for (Entry entry : sceneDataMap.entrySet()) {
                String str = (String) entry.getKey();
                LevelData levelData = (LevelData) entry.getValue();
                if (!(str == null || levelData == null)) {
                    String[] split = str.split(Config.TRACE_TODAY_VISIT_SPLIT);
                    if (split != null && split.length == 2) {
                        this.sceneMaps.put(new Pair(Integer.valueOf(Integer.parseInt(split[0])), Integer.valueOf(Integer.parseInt(split[1]))), levelData);
                    }
                }
            }
        }
    }

    public LevelData getEnterConfigByLevel(int i) {
        if (this.sceneMaps != null) {
            for (Entry entry : this.sceneMaps.entrySet()) {
                Pair pair = (Pair) entry.getKey();
                if (((Integer) pair.first).intValue() <= i && ((Integer) pair.second).intValue() >= i) {
                    return (LevelData) entry.getValue();
                }
            }
        }
        return null;
    }

    public String getEnterScopeByLevel(int i) {
        if (this.sceneMaps != null) {
            for (Entry entry : this.sceneMaps.entrySet()) {
                Pair pair = (Pair) entry.getKey();
                if (((Integer) pair.first).intValue() <= i && ((Integer) pair.second).intValue() >= i) {
                    return ((Pair) entry.getKey()).first + Config.TRACE_TODAY_VISIT_SPLIT + ((Pair) entry.getKey()).second;
                }
            }
        }
        return null;
    }

    public boolean isJiaMingSpecOn() {
        return this.b != null && this.b.jiamin_spec >= 1;
    }

    public boolean isFirstCharge() {
        if (this.b == null || this.b.android_price_data == null) {
            return false;
        }
        for (Diamond diamond : getLocalDiamonds()) {
            if (diamond.fc == 1) {
                return true;
            }
        }
        return false;
    }

    public List<RichUser> getRichUsers() {
        return (this.b == null || this.b.rich_spec_arr == null) ? null : this.b.rich_spec_arr;
    }

    public boolean isRichUser(User user) {
        List<RichUser> richUsers = getRichUsers();
        if (richUsers != null && richUsers.size() > 0) {
            for (RichUser richUser : richUsers) {
                if (Long.parseLong(richUser.i) == user.getOriginId() && Long.parseLong(richUser.s) == user.getOrigin()) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getHitNumColor(int i) {
        List list;
        List list2 = (this.b == null || this.b.double_send_color == null) ? null : this.b.double_send_color;
        if (list2 == null) {
            list2 = new ArrayList();
            list2.add(new HitColorData(1, "#FEDE44"));
            list2.add(new HitColorData(99, "#FEDE44"));
            list2.add(new HitColorData(520, "#2AF59A"));
            list2.add(new HitColorData(666, "#20FFEB"));
            list2.add(new HitColorData(QiushiNotificationListFragment.REQUEST_FOR_PROMOTE, "#FF4D7A"));
            list2.add(new HitColorData(999, "#F76C1D"));
            list2.add(new HitColorData(1314, "#00B7FF"));
            list2.add(new HitColorData(5200, "#B227FF"));
            list = list2;
        } else {
            list = list2;
        }
        Collections.sort(list, new h(this));
        for (int size = list.size() - 1; size >= 0; size--) {
            if (i >= ((HitColorData) list.get(size)).count) {
                return ((HitColorData) list.get(size)).color;
            }
        }
        return ((HitColorData) list.get(0)).color;
    }

    public String getGiftUrlById(long j) {
        if (this.b == null || this.b.all_gift_data == null || !this.b.all_gift_data.containsKey(Long.valueOf(j))) {
            return null;
        }
        return ((GiftData) this.b.all_gift_data.get(Long.valueOf(j))).ig;
    }

    public int getSuperUserMinLevel() {
        return (this.b == null || this.b.hl <= 0) ? 140 : this.b.hl;
    }

    public int getGiftCountById(long j) {
        if (this.b == null || this.b.gift_counts == null || !this.b.gift_counts.containsKey(Long.valueOf(j))) {
            return 0;
        }
        return ((GiftCount) this.b.gift_counts.get(Long.valueOf(j))).count;
    }

    public void setGiftCountById(long j, int i) {
        GiftCount giftCount;
        if (this.b == null || this.b.gift_counts == null) {
            if (this.b != null) {
                this.b.gift_counts = new HashMap();
                giftCount = new GiftCount();
                giftCount.giftId = j;
                giftCount.count = i;
                this.b.gift_counts.put(Long.valueOf(j), giftCount);
            }
        } else if (this.b.gift_counts.containsKey(Long.valueOf(j))) {
            ((GiftCount) this.b.gift_counts.get(Long.valueOf(j))).count = i;
        } else {
            giftCount = new GiftCount();
            giftCount.giftId = j;
            giftCount.count = i;
            this.b.gift_counts.put(Long.valueOf(j), giftCount);
        }
        if (!(this.b == null || this.b.gift_data == null || this.b.gift_data.contains(Long.valueOf(j)))) {
            this.b.gift_data.add(0, Long.valueOf(j));
            if (this.b.all_gift_data.containsKey(Long.valueOf(j))) {
                this.mLiveGiftData.add(0, this.b.all_gift_data.get(Long.valueOf(j)));
            }
        }
        getDiskCache().put("remix_config", AppUtils.toJson(this.b));
    }

    public RedEnvelopesConfig getRedEnvelopesConfig() {
        return this.b != null ? this.b.redpkg_conf : null;
    }

    public Map<String, String> getTemplate() {
        return this.b != null ? this.b.template : null;
    }

    public int[] getBitrateLevel(String str) {
        int[] iArr = new int[1];
        return (this.b == null || this.b.bitrate == null) ? iArr : ((Bitrate) this.b.bitrate.get(str)).l;
    }

    public int getBitrateWidth(String str) {
        return (this.b == null || this.b.bitrate == null) ? 0 : ((Bitrate) this.b.bitrate.get(str)).w;
    }

    public int getBitrateHeight(String str) {
        return (this.b == null || this.b.bitrate == null) ? 0 : ((Bitrate) this.b.bitrate.get(str)).h;
    }

    public Map getMarketDataMap() {
        return this.b != null ? this.b.scene_ani : null;
    }

    public MarketData getMarketDatById(int i) {
        return (this.b == null || this.b.scene_ani == null) ? null : (MarketData) this.b.scene_ani.get(Long.valueOf((long) i));
    }

    public void updateGiftData(long j, GiftData giftData) {
        if (this.b != null && this.b.all_gift_data != null) {
            this.b.all_gift_data.put(Long.valueOf(j), giftData);
            getDiskCache().put("remix_config", AppUtils.toJson(this.b));
        }
    }

    public String getTaburl(int i) {
        if (i == 0) {
            return (this.b == null || this.b.taburl == null || this.b.taburl.size() <= 1) ? "" : (String) this.b.taburl.get("day");
        } else {
            if (i == 1) {
                return (this.b == null || this.b.taburl == null || this.b.taburl.size() <= 1) ? "" : (String) this.b.taburl.get(UIHelper$Theme.THEME_NIGHT);
            } else {
                return "";
            }
        }
    }
}
