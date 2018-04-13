package qsbk.app.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.QiushiTopic;

class je implements OnClickListener {
    final /* synthetic */ QiushiTopic a;
    final /* synthetic */ QiushiTopicListFragment b;

    je(QiushiTopicListFragment qiushiTopicListFragment, QiushiTopic qiushiTopic) {
        this.b = qiushiTopicListFragment;
        this.a = qiushiTopic;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(Constants.QIUSHI_TOPIC_UNSUBCRIBE, new jf(this));
        Map hashMap = new HashMap();
        hashMap.put("topic_id", Integer.valueOf(this.a.id));
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.execute();
    }
}
