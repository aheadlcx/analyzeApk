package cn.xiaochuankeji.tieba.ui.my;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.net.a.b;
import cn.xiaochuankeji.tieba.background.topic.BlockTopicActionRequest;
import cn.xiaochuankeji.tieba.background.topic.CancelBlockTopicActionRequest;
import cn.xiaochuankeji.tieba.background.topic.Topic;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import java.util.HashSet;
import org.json.JSONObject;

public class e extends FrameLayout {
    private WebImageView a = ((WebImageView) findViewById(R.id.sdvCover));
    private TextView b = ((TextView) findViewById(R.id.tvTopicName));
    private TextView c = ((TextView) findViewById(R.id.tvBlock));
    private Topic d;
    private CancelBlockTopicActionRequest e;
    private BlockTopicActionRequest f;
    private HashSet<Long> g;

    public e(Context context, HashSet<Long> hashSet) {
        super(context);
        this.g = hashSet;
        LayoutInflater.from(context).inflate(R.layout.view_block_topic_item, this);
    }

    public void setData(Topic topic) {
        this.d = topic;
        this.a.setData(topic.topicCover());
        this.b.setText(topic._topicName);
        if (this.g.contains(Long.valueOf(this.d._topicID))) {
            this.c.setText("屏蔽话题");
        } else {
            this.c.setText("取消屏蔽");
        }
        this.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.g.contains(Long.valueOf(this.a.d._topicID))) {
                    this.a.b();
                } else {
                    this.a.a();
                }
            }
        });
        setTag(topic);
    }

    private void a() {
        if (this.e == null) {
            this.e = new CancelBlockTopicActionRequest(this.d._topicID, a.g().a(), null, new b<JSONObject>(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onResponse(Object obj, Object obj2) {
                    a((JSONObject) obj, obj2);
                }

                public void a(JSONObject jSONObject, Object obj) {
                    this.a.c.setText("屏蔽话题");
                    this.a.e = null;
                    this.a.g.add(Long.valueOf(this.a.d._topicID));
                }
            }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public void onErrorResponse(XCError xCError, Object obj) {
                    g.a(xCError.getMessage());
                }
            });
            this.e.execute();
        }
    }

    private void b() {
        if (this.f == null) {
            this.f = new BlockTopicActionRequest(this.d._topicID, a.g().a(), null, new b<JSONObject>(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onResponse(Object obj, Object obj2) {
                    a((JSONObject) obj, obj2);
                }

                public void a(JSONObject jSONObject, Object obj) {
                    this.a.c.setText("取消屏蔽");
                    this.a.f = null;
                    this.a.g.remove(Long.valueOf(this.a.d._topicID));
                }
            }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public void onErrorResponse(XCError xCError, Object obj) {
                    g.a(xCError.getMessage());
                }
            });
            this.f.execute();
        }
    }
}
