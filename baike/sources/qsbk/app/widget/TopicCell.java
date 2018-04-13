package qsbk.app.widget;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.TopicViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.qiushibaike.statsdk.StatSDK;
import java.util.ArrayList;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.CircleTopicActivity;
import qsbk.app.model.CircleTopic;
import qsbk.app.model.CircleTopicPackage;
import qsbk.app.utils.SubscribeReportHelper;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.Util;

public class TopicCell extends BaseCell {
    private final boolean a;
    private TopicViewPager b;
    private IndicatorView c;
    private TopicPagerAdapter d;

    public class TopicPagerAdapter extends PagerAdapter {
        final /* synthetic */ TopicCell a;
        private ArrayList<a> b = new ArrayList();
        private Drawable c = new ColorDrawable(UIHelper.getTopicBgColor(3));
        private int[] d = new int[5];

        private class a {
            int a;
            View b;
            ImageView c;
            TextView d;
            TextView e;
            final /* synthetic */ TopicPagerAdapter f;

            private a(TopicPagerAdapter topicPagerAdapter) {
                this.f = topicPagerAdapter;
            }
        }

        public TopicPagerAdapter(TopicCell topicCell) {
            this.a = topicCell;
        }

        private int a(int i) {
            if (i < 0) {
                i = 0;
            }
            int i2 = i % 5;
            if (this.d[i2] == 0) {
                this.d[i2] = UIHelper.getTopicDefaultBg(i2);
            }
            return this.d[i2];
        }

        public int getCount() {
            return (this.a.getItem().topics.length * 2) * 10000;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == ((a) obj).b;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            a aVar = (a) obj;
            viewGroup.removeView(aVar.b);
            this.b.add(aVar);
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            a aVar;
            if (this.b.size() == 0) {
                a aVar2 = new a();
                aVar2.b = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_qiuyoucircle_topic, viewGroup, false);
                aVar2.d = (TextView) aVar2.b.findViewById(R.id.topic_title);
                aVar2.e = (TextView) aVar2.b.findViewById(R.id.topic_btn);
                OnClickListener ewVar = new ew(this);
                aVar2.e.setOnClickListener(ewVar);
                aVar2.b.setOnClickListener(ewVar);
                aVar2.c = (ImageView) aVar2.b.findViewById(R.id.topic_image);
                aVar = aVar2;
            } else {
                aVar = (a) this.b.remove(this.b.size() - 1);
            }
            CircleTopicPackage item = this.a.getItem();
            CircleTopic[] circleTopicArr = item.topics;
            int length = i % circleTopicArr.length;
            aVar.a = length;
            CircleTopic circleTopic = circleTopicArr[length];
            CharSequence charSequence = TextUtils.isEmpty(circleTopic.recommendContent) ? circleTopic.content : circleTopic.recommendContent;
            if (!TextUtils.isEmpty(charSequence)) {
                charSequence = charSequence.replace(MqttTopic.MULTI_LEVEL_WILDCARD, "");
            }
            aVar.d.setText(charSequence);
            aVar.e.setText((this.a.a ? "糗友圈" : "") + String.format("已有 %d 条内容", new Object[]{Integer.valueOf(circleTopic.articleCount)}));
            this.a.displayImage(aVar.c, QsbkApp.absoluteUrlOfCircleWebpImage(item.getPic(length), item.getCreateAt(length)), this.c, aVar.c.getResources().getDrawable(a(item.picIndexs[length])));
            viewGroup.addView(aVar.b);
            return aVar;
        }
    }

    public TopicCell(boolean z) {
        this.a = z;
    }

    public void onCreate() {
        setCellView(R.layout.cell_qiuyoucircle_pager);
        this.b = (TopicViewPager) findViewById(R.id.pager);
        this.c = (IndicatorView) findViewById(R.id.indicator);
        if (UIHelper.isNightTheme()) {
            findViewById(R.id.divider_bellow).setVisibility(0);
        }
        this.b.setOnPageChangeListener(new ev(this));
        this.d = new TopicPagerAdapter(this);
        this.b.setAdapter(this.d);
    }

    public CircleTopicPackage getItem() {
        return (CircleTopicPackage) super.getItem();
    }

    public void onUpdate() {
        this.d.notifyDataSetChanged();
        CircleTopic[] circleTopicArr = getItem().topics;
        int i = getItem().currentPos;
        this.b.setCurrentItem((i % circleTopicArr.length) + (circleTopicArr.length * 10000), false);
        this.c.setCount(circleTopicArr.length);
    }

    public void onClick() {
        CircleTopicPackage item = getItem();
        CircleTopic[] circleTopicArr = item.topics;
        int currentItem = this.b.getCurrentItem() % circleTopicArr.length;
        CircleTopic circleTopic = circleTopicArr[currentItem];
        if (this.a) {
            StatSDK.onEvent(getContext(), "topic_click", circleTopic.content);
            SubscribeReportHelper.report(QsbkApp.mContext, circleTopic.hashCode());
        }
        CircleTopicActivity.launch(Util.getActivityOrContext(getCellView()), circleTopic, item.picIndexs[currentItem], false);
    }
}
