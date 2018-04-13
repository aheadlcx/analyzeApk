package cn.xiaochuankeji.tieba.ui.tale;

import cn.xiaochuankeji.tieba.background.utils.d.a;
import cn.xiaochuankeji.tieba.background.utils.newshare.ShareDataModel;
import cn.xiaochuankeji.tieba.json.tale.FollowPostThemeJson;

public class ThemeShareDataModel extends ShareDataModel {
    private FollowPostThemeJson json;
    private String mDescription;
    private int mSharePlatformFlag;
    private String mTargetUrl;
    private String mThumbPath;
    private String mTitle;

    public ThemeShareDataModel(FollowPostThemeJson followPostThemeJson, int i) {
        this.json = followPostThemeJson;
        this.mTitle = followPostThemeJson.title;
        this.mTargetUrl = a.b(followPostThemeJson.id);
        this.mSharePlatformFlag = i;
    }

    public String getTitleBy() {
        return this.mTitle;
    }

    public String getDescriptionBy() {
        return " ";
    }

    public String getTargetUrl() {
        return this.mTargetUrl;
    }

    public String getThumbPath() {
        return cn.xiaochuankeji.tieba.background.utils.share.a.a();
    }

    public int getSharePlatformFlag() {
        return this.mSharePlatformFlag;
    }

    public void setSharePlatformFlag(int i) {
    }

    public String getABTestId() {
        return null;
    }

    public long getReportShareId() {
        return 0;
    }
}
