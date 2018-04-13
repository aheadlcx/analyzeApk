package cn.xiaochuankeji.tieba.ui.a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import c.a.d.a.a;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.f.b;
import cn.xiaochuankeji.tieba.background.topic.Topic;
import cn.xiaochuankeji.tieba.ui.base.j;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import cn.xiaochuankeji.tieba.ui.widget.text.BadgeTextView;

public class c extends j {
    private WebImageView a;
    private TextView b;
    private ImageView c;
    private ImageView d;
    private BadgeTextView e;

    public c(Context context) {
        super(context);
    }

    protected View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.view_item_my_topic_list, null);
    }

    protected void a(View view) {
        this.a = (WebImageView) view.findViewById(R.id.topic_cover_pv);
        this.b = (TextView) view.findViewById(R.id.topic_title_tv);
        this.c = (ImageView) view.findViewById(R.id.ivSubscript);
        this.d = (ImageView) view.findViewById(R.id.ivUpFlag);
        this.e = (BadgeTextView) view.findViewById(R.id.post_count_tv);
    }

    public void a(Topic topic, boolean z) {
        this.a.setWebImage(b.c(topic._topicCoverID, false));
        this.b.setText(topic._topicName);
        if (z) {
            this.d.setVisibility(topic._topTime > 0 ? 0 : 8);
            if (topic.role == 4) {
                this.c.setImageResource(R.drawable.topic_holder_small_icon);
                this.c.setVisibility(0);
            } else if (topic.role == 2) {
                this.c.setImageResource(R.drawable.topic_admin_small_icon);
                this.c.setVisibility(0);
            } else if (topic.role == 1) {
                this.c.setImageResource(R.drawable.topic_talent_small_icon);
                this.c.setVisibility(0);
            } else if (topic.role == 8) {
                this.c.setImageResource(R.drawable.topic_guard_small_icon);
                this.c.setVisibility(0);
            } else {
                this.c.setVisibility(8);
            }
            this.e.setBadgeCount(topic._newPostCount);
            return;
        }
        String str;
        this.c.setVisibility(8);
        this.d.setVisibility(8);
        this.e.setBackgroundColor(a.a().a((int) R.color.CB));
        this.e.setTextColor(a.a().a((int) R.color.CT_5));
        if (topic._ups == 0) {
            str = "";
        } else {
            str = topic._ups + " 个顶";
        }
        this.e.setBadgeText(str);
    }
}
