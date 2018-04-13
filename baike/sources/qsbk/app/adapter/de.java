package qsbk.app.adapter;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.QiushiTopicActivity;
import qsbk.app.model.QiushiTopic;

class de implements OnClickListener {
    final /* synthetic */ QiushiTopic a;
    final /* synthetic */ QiushiTopicAdapter b;

    de(QiushiTopicAdapter qiushiTopicAdapter, QiushiTopic qiushiTopic) {
        this.b = qiushiTopicAdapter;
        this.a = qiushiTopic;
    }

    public void onClick(View view) {
        if (this.b.c) {
            Intent intent = new Intent();
            intent.putExtra("topic", this.a);
            this.b.k.setResult(-1, intent);
            this.b.k.finish();
            return;
        }
        intent = new Intent(this.b.k, QiushiTopicActivity.class);
        intent.putExtra("topic", this.a);
        this.b.k.startActivity(intent);
    }
}
