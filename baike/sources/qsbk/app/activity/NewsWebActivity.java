package qsbk.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import qsbk.app.R;
import qsbk.app.cafe.plugin.AccountPlugin;
import qsbk.app.cafe.plugin.CommentPlugin;
import qsbk.app.cafe.plugin.NewsOtherPlugin;
import qsbk.app.cafe.plugin.SharePlugin;
import qsbk.app.model.News;
import qsbk.app.model.ShareMsgItem;
import qsbk.app.share.QYQShareInfo;

@Deprecated
public class NewsWebActivity extends WebEmotionActivity {
    public static void launch(Context context, News news) {
        launch(context, news, true);
    }

    public static void launch(Context context, News news, boolean z) {
        Intent intent = new Intent(context, NewsWebActivity.class);
        intent.putExtra(QYQShareInfo.TYPE_NEWS, news);
        intent.putExtra("need_share", z);
        context.startActivity(intent);
    }

    public static void launch(Context context, ShareMsgItem shareMsgItem, boolean z) {
        Intent intent = new Intent(context, NewsWebActivity.class);
        intent.putExtra("share_info", shareMsgItem);
        intent.putExtra("need_share", z);
        context.startActivity(intent);
    }

    public static void launch(Context context, String str, boolean z) {
        Intent intent = new Intent(context, NewsWebActivity.class);
        intent.putExtra("news_id", str);
        intent.putExtra("need_share", z);
        intent.putExtra("new_activity", false);
        context.startActivity(intent);
    }

    protected int a() {
        return R.layout.new_detail_layout;
    }

    protected void f() {
        this.o.put("account", AccountPlugin.class);
        this.o.put("comment", CommentPlugin.class);
        this.o.put("others", NewsOtherPlugin.class);
        this.o.put("share", SharePlugin.class);
    }

    protected void a(Bundle bundle) {
        finish();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        finish();
        return true;
    }
}
