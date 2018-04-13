package cn.xiaochuankeji.tieba.background.utils.share;

public class WebPageShare extends WebPageShareStruct {
    private String mDesc;
    private String mTitle;

    public WebPageShare(String str, String str2, String str3, String str4) {
        this.mTitle = str;
        this.mDesc = str2;
        this.thumbPath = str3;
        this.targetUrl = str4;
    }

    public String getTitleBy(int i) {
        return this.mTitle;
    }

    public String getDescBy(int i) {
        return this.mDesc;
    }
}
