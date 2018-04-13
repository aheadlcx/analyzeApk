package cn.xiaochuankeji.tieba.background.beans;

import com.alibaba.fastjson.annotation.JSONField;

public class GrayConfigBean {
    @JSONField(name = "ab_webp_tack")
    public int abPicTackWebp = 0;
    @JSONField(name = "ab_short_refresh_btn")
    public int ab_short_refresh_btn;
    @JSONField(name = "ab_smz_heavy_enable")
    public int ab_smz_heavy_enable = 1;
    @JSONField(name = "ab_smz_heavy_force")
    public int ab_smz_heavy_force = 1;
    @JSONField(name = "ab_smz_light_enable")
    public int ab_smz_light_enable;
    @JSONField(name = "ab_smz_light_force")
    public int ab_smz_light_force;
    @JSONField(name = "ab_smz_open_register_enable")
    public int ab_smz_open_register_enable = 1;
    @JSONField(name = "ab_thirdparty_login_show_write_person_info")
    public int accountModifyAb;
    @JSONField(name = "ab_android_recommend_loadmore_tack")
    public int autoRefreshMoreTack = 0;
    @JSONField(name = "ab_android_recommend_refresh_tack")
    public int autoRefreshTack = 0;
    @JSONField(name = "ab_android_new_video_cache")
    public int enableNewVideoCache = 1;
    @JSONField(name = "ab_android_enable_self_start")
    public int enableSelfStart;
    @JSONField(name = "android_video_codec")
    public int enableVideoPlayCodec = 0;
    @JSONField(name = "flow_chat")
    public int flow_chat;
    @JSONField(name = "follow_enable")
    public int follow_enable;
    @JSONField(name = "flow_enable")
    public int hollowEnable;
    @JSONField(name = "ab_android_post_thumb_update")
    public int newPostThumbSize;
    @JSONField(name = "push_notification_dialog")
    public int push_notification_dialog = 1;
    @JSONField(name = "ab_android_recommend_cache_tack")
    public int recommendCacheTack = 0;
    @JSONField(name = "statistics_enable")
    public int statistics_enable;
    @JSONField(name = "tale_enable")
    public int tale_enable;
    @JSONField(name = "android_ugcvideo_cache_preload")
    public int ugcvideoCachePreloadEnabled;
    @JSONField(name = "voice_enable")
    public int voice_enable;
}
