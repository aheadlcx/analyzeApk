package qsbk.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.QsbkApp;
import qsbk.app.activity.SingleArticle;
import qsbk.app.adapter.VideoImmersionAdapter.VideoImmersionCell;
import qsbk.app.utils.Util;

class ed implements OnClickListener {
    final /* synthetic */ VideoImmersionCell a;

    ed(VideoImmersionCell videoImmersionCell) {
        this.a = videoImmersionCell;
    }

    public void onClick(View view) {
        Context activityOrContext = Util.getActivityOrContext(view);
        Intent intent = new Intent(activityOrContext, SingleArticle.class);
        intent.putExtra("source", "video" + (QsbkApp.currentSelectItem + 1));
        intent.putExtra("scroll_to_comment", false);
        intent.addFlags(67108864);
        intent.putExtra("article", this.a.article);
        if (!(activityOrContext instanceof Activity)) {
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        }
        activityOrContext.startActivity(intent);
    }
}
