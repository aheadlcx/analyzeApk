package qsbk.app.core.model;

public class ShareItem {
    public int iconResId;
    public int resultCode;
    public boolean selected;
    public String title;

    public ShareItem(int i, String str, int i2) {
        this.iconResId = i;
        this.title = str;
        this.resultCode = i2;
        this.selected = false;
    }

    public ShareItem(int i, String str, int i2, boolean z) {
        this.iconResId = i;
        this.title = str;
        this.resultCode = i2;
        this.selected = z;
    }
}
