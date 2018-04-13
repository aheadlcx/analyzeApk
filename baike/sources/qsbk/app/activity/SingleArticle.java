package qsbk.app.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;

public class SingleArticle extends SingleArticleBase {
    public static Intent genIntent(Context context, String str) {
        Intent intent = new Intent();
        intent.setClass(context, SingleArticle.class);
        intent.putExtra("article_id", str);
        intent.putExtra("source", "only_article_id");
        if (!(context instanceof Activity)) {
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        }
        return intent;
    }

    public static void launch(Context context, String str) {
        context.startActivity(genIntent(context, str));
    }
}
