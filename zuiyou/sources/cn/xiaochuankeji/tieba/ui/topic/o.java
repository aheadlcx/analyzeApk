package cn.xiaochuankeji.tieba.ui.topic;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import c.a.d.a.a;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent.MessageEventType;
import cn.xiaochuankeji.tieba.background.topic.Topic;
import cn.xiaochuankeji.tieba.background.topic.TopicUtilityClass;
import cn.xiaochuankeji.tieba.ui.base.j;
import cn.xiaochuankeji.tieba.ui.discovery.moretopic.MoreTopicRecommendActivity;
import cn.xiaochuankeji.tieba.ui.widget.f;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import com.izuiyou.a.a.b;
import org.greenrobot.eventbus.c;

public class o extends j implements OnClickListener {
    private WebImageView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private View e;
    private View f;
    private TextView g;
    private boolean h;
    private boolean i;
    private boolean j;
    private Topic k;

    public o(Context context) {
        super(context);
    }

    protected View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.view_item_topic_in_list, null);
    }

    protected void a(View view) {
        this.a = (WebImageView) view.findViewById(R.id.topic_cover_pv);
        this.b = (TextView) view.findViewById(R.id.topic_title_tv);
        this.c = (TextView) view.findViewById(R.id.post_count_tv);
        this.e = view.findViewById(R.id.topic_admin_flag);
        this.f = view.findViewById(R.id.topic_rank_flag);
        this.g = (TextView) view.findViewById(R.id.tvFocus);
        this.d = (TextView) view.findViewById(R.id.tvTopicIntroduce);
    }

    public void a(boolean z, boolean z2, boolean z3) {
        int i = 0;
        this.h = z;
        this.i = z2;
        this.j = z3;
        if (z) {
            this.g.setVisibility(0);
            this.c.setVisibility(8);
            this.g.setOnClickListener(this);
        } else {
            this.g.setVisibility(8);
            this.c.setVisibility(0);
        }
        TextView textView = this.d;
        if (!z3) {
            i = 8;
        }
        textView.setVisibility(i);
    }

    public void a(Topic topic) {
        if (topic == null) {
            b.e(3 + "");
        }
        this.k = topic;
        this.a.setWebImage(cn.xiaochuankeji.tieba.background.f.b.c(topic._topicCoverID, false));
        if (topic._isadm == 1) {
            this.e.setVisibility(0);
        } else {
            this.e.setVisibility(8);
        }
        if (topic._trank >= 1) {
            this.f.setVisibility(0);
        } else {
            this.f.setVisibility(8);
        }
        this.b.setText(topic._topicName);
        if (this.h) {
            if (topic._isAttention) {
                this.g.setText("已关注");
                this.g.setBackgroundDrawable(a.a().b(R.drawable.bg_followed));
                this.g.setTextColor(a.a().a((int) R.color.CT_5));
            } else {
                this.g.setText("关注");
                this.g.setBackgroundDrawable(a.a().b(R.drawable.bg_follow));
                this.g.setTextColor(a.a().a((int) R.color.CW));
            }
        } else if (this.i) {
            this.c.setTextSize(2, 12.0f);
            if (topic._ups == 0) {
                r0 = "";
            } else {
                r0 = topic._ups + "个顶";
            }
            this.c.setText(r0);
        } else {
            this.c.setTextSize(2, 16.0f);
            r0 = String.valueOf(topic._newPostCount);
            if (topic._newPostCount == 0) {
                r0 = "";
            } else if (topic._newPostCount > 99) {
                r0 = "99+";
            }
            this.c.setText(r0);
        }
        if (!this.j) {
            return;
        }
        if (TextUtils.isEmpty(topic._addition)) {
            this.d.setVisibility(8);
            return;
        }
        this.d.setText(topic._addition);
        this.d.setVisibility(0);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvFocus:
                if (1 == this.k._isadm) {
                    f.a("提示", "你是本话题话事人,取消关注将取消话事人资格", (Activity) e_(), new f.a(this) {
                        final /* synthetic */ o a;

                        {
                            this.a = r1;
                        }

                        public void a(boolean z) {
                            if (z) {
                                this.a.c();
                            }
                        }
                    }, true);
                    return;
                } else {
                    c();
                    return;
                }
            default:
                return;
        }
    }

    private void c() {
        if (this.k._isAttention) {
            this.k._isAttention = false;
            this.g.setText("关注");
            this.g.setBackgroundDrawable(a.a().b(R.drawable.bg_follow));
            this.g.setTextColor(a.a().a((int) R.color.CW));
            this.k._isadm = 0;
            this.e.setVisibility(8);
        } else {
            this.k._isAttention = true;
            this.g.setText("已关注");
            this.g.setBackgroundDrawable(a.a().b(R.drawable.bg_followed));
            this.g.setTextColor(a.a().a((int) R.color.CT_5));
        }
        d();
        String str = "discovery";
        if (e_() instanceof MoreTopicRecommendActivity) {
            str = "topiclist";
        }
        TopicUtilityClass.asynchSendFollowRequest(this.k._topicID, this.k._isAttention, str, this.k == null ? null : this.k.click_cb);
    }

    private void d() {
        Topic topic = new Topic();
        topic._topicID = this.k._topicID;
        topic._isAttention = this.k._isAttention;
        topic._isadm = this.k._isadm;
        MessageEvent messageEvent = new MessageEvent(MessageEventType.MESSAGE_TOPIC_TOGGLE_FOLLOW_ACTION);
        messageEvent.setData(topic);
        c.a().d(messageEvent);
    }
}
