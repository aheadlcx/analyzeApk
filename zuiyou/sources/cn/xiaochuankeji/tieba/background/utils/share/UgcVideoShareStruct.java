package cn.xiaochuankeji.tieba.background.utils.share;

public class UgcVideoShareStruct extends WebPageShareStruct {
    private String mDesp;
    private String mTitle;

    public UgcVideoShareStruct(String str, String str2, String str3, String str4) {
        this.mTitle = str;
        this.mDesp = str2;
        this.thumbPath = str3;
        this.targetUrl = str4;
    }

    public String getTitleBy(int i) {
        return this.mTitle;
    }

    public String getDescBy(int i) {
        return this.mDesp;
    }
}
