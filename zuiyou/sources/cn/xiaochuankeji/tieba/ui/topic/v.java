package cn.xiaochuankeji.tieba.ui.topic;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.f.b;
import cn.xiaochuankeji.tieba.background.topic.TopicDetail;
import cn.xiaochuankeji.tieba.background.topic.TopicDetail.TopPostInfo;
import cn.xiaochuankeji.tieba.ui.post.postdetail.PostDetailActivity;
import cn.xiaochuankeji.tieba.ui.topic.TopicUppedMemberViewController.a;
import cn.xiaochuankeji.tieba.ui.widget.MultipleLineEllipsisTextView;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import java.util.ArrayList;
import java.util.List;

public class v extends LinearLayout implements a, OnBannerListener {
    public TopicUppedMemberViewController a;
    private Context b;
    private TopicDetail c;
    private WebImageView d;
    private MultipleLineEllipsisTextView e;
    private TextView f;
    private TextView g;
    private TextView h;
    private Banner i;

    public v(Context context) {
        super(context);
        this.b = context;
        inflate(context, R.layout.view_header_topic_detail, this);
        getViews();
    }

    private void getViews() {
        this.d = (WebImageView) findViewById(R.id.sdvCover);
        this.e = (MultipleLineEllipsisTextView) findViewById(R.id.tvTopicBrief);
        this.i = (Banner) findViewById(R.id.banner);
        this.h = (TextView) findViewById(R.id.ivFollow);
        this.f = (TextView) findViewById(R.id.tvTopicName);
        this.g = (TextView) findViewById(R.id.tvMore);
        this.a = (TopicUppedMemberViewController) findViewById(R.id.viewUpsArea);
        this.a.setVisibility(4);
        this.e.setEndDesc("更多");
        this.e.setEndDescColor(c.a.d.a.a.a().a((int) R.color.CM));
    }

    public View b() {
        return this.h;
    }

    public void a(TopicDetail topicDetail) {
        boolean z = topicDetail._isAttention;
        this.h.setSelected(z);
        this.h.setText(z ? "已关注" : "关注");
    }

    public void setDataBy(final TopicDetail topicDetail) {
        this.c = topicDetail;
        this.f.setText(topicDetail._topicName);
        this.d.setWebImage(b.c(topicDetail._topicCoverID, true));
        this.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ v b;

            public void onClick(View view) {
                TopicCoverActivity.a(this.b.b, topicDetail);
            }
        });
        this.g.setText(String.valueOf(topicDetail._partners + " " + topicDetail._attsTitle));
        if (!TextUtils.isEmpty(topicDetail._brief)) {
            this.e.a(topicDetail._brief, null, topicDetail._topicID, -6710887, 5);
        }
        this.a.setOnMemberListener(this);
        this.a.setData(topicDetail);
        this.h.setSelected(topicDetail._isAttention);
        this.h.setText(topicDetail._isAttention ? "已关注" : "关注");
    }

    public void a() {
        TopicMemberActivity.a(this.b, this.c._topic);
    }

    public void a(ArrayList<String> arrayList, List<String> list) {
        if (arrayList == null || list == null || arrayList.size() != list.size()) {
            this.i.setVisibility(8);
            return;
        }
        this.i.releaseBanner();
        this.i.setImages(arrayList).setImageLoader(new FrescoImageLoader(true)).setBannerTitles(list).setBannerStyle(4).setIndicatorGravity(6).setOnBannerListener(this).setDelayTime(5000).isAutoPlay(true).start();
        this.i.setVisibility(0);
        ViewCompat.setAlpha(this.i, 0.0f);
        ViewCompat.animate(this.i).alpha(1.0f).setDuration(250).start();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.i.setOnBannerListener(null);
        this.i.releaseBanner();
    }

    public void OnBannerClick(int i) {
        PostDetailActivity.a(getContext(), new Post(((TopPostInfo) this.c.topPostInfos.get(i % this.c.topPostInfos.size())).pid));
    }

    public void c() {
        if (this.i != null) {
            this.i.stopAutoPlay();
        }
    }

    public void d() {
        if (this.i != null) {
            this.i.startAutoPlay();
        }
    }
}
