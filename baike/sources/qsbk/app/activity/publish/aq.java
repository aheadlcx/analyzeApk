package qsbk.app.activity.publish;

import qsbk.app.activity.QiushiTopicListActivity;
import qsbk.app.model.QiushiTopic;
import qsbk.app.widget.qiushitopic.MultiLayoutEditText.OnTopicClickListener;

class aq implements OnTopicClickListener {
    final /* synthetic */ PublishActivity a;

    aq(PublishActivity publishActivity) {
        this.a = publishActivity;
    }

    public void onTopicClick(QiushiTopic qiushiTopic) {
        this.a.j();
        QiushiTopicListActivity.launchForResult(this.a, 2, 3);
    }
}
