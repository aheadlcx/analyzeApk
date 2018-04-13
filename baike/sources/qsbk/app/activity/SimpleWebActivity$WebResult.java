package qsbk.app.activity;

import android.webkit.JavascriptInterface;
import qsbk.app.model.ShareMsgItem;

public class SimpleWebActivity$WebResult {
    final /* synthetic */ SimpleWebActivity a;

    public SimpleWebActivity$WebResult(SimpleWebActivity simpleWebActivity) {
        this.a = simpleWebActivity;
    }

    @JavascriptInterface
    public void onResult(String str, String str2, String str3) {
        SimpleWebActivity.a(this.a, new ShareMsgItem());
        SimpleWebActivity.b(this.a).title = str;
        SimpleWebActivity.b(this.a).content = str2;
        SimpleWebActivity.b(this.a).imageUrl = str3;
        SimpleWebActivity.b(this.a).link = SimpleWebActivity.c(this.a);
        SimpleWebActivity.b(this.a).shareFor = 1;
    }
}
