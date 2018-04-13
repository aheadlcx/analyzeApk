package qsbk.app.widget;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import com.qiushibaike.statsdk.StatSDK;
import qsbk.app.model.CircleTopic;
import qsbk.app.model.CircleTopicPackage;

class ev implements OnPageChangeListener {
    final /* synthetic */ TopicCell a;

    ev(TopicCell topicCell) {
        this.a = topicCell;
    }

    public void onPageScrolled(int i, float f, int i2) {
        this.a.c.setPos(i % this.a.getItem().topics.length, f);
    }

    public void onPageSelected(int i) {
        CircleTopicPackage item = this.a.getItem();
        item.currentPos = i % item.topics.length;
        if (this.a.a) {
            StatSDK.onEvent(this.a.getContext(), "topic_show", "topic_show");
        }
    }

    public void onPageScrollStateChanged(int i) {
        if (i == 0) {
            CircleTopic[] circleTopicArr = this.a.getItem().topics;
            int currentItem = this.a.b.getCurrentItem();
            if (currentItem < circleTopicArr.length * 5000 || currentItem > circleTopicArr.length * 15000) {
                this.a.b.setCurrentItem((currentItem % circleTopicArr.length) + (circleTopicArr.length * 10000), false);
            }
        }
    }
}
