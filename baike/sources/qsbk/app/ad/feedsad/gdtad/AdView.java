package qsbk.app.ad.feedsad.gdtad;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
import qsbk.app.R;

public class AdView extends RelativeLayout {
    private String pkg;

    public AdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public AdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AdView(Context context) {
        super(context);
    }

    public void bindPkg(String str) {
        this.pkg = str;
    }

    public void updateButtonText(int i) {
        String str = "";
        switch (i) {
            case 1:
                str = "点击启动";
                break;
            case 4:
                str = "下载中";
                break;
            case 8:
                str = "点击安装";
                break;
            case 16:
                str = "点击重试";
                break;
            default:
                str = "下载";
                break;
        }
        updateButtonText(str);
    }

    public void updateButtonText(String str) {
        ((TextView) findViewById(R.id.downbt)).setText(str);
    }
}
