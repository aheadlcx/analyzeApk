package cn.xiaochuankeji.tieba.ui.chat.holder;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import cn.xiaochuankeji.tieba.background.f.b;
import cn.xiaochuankeji.tieba.background.topic.Topic;
import cn.xiaochuankeji.tieba.push.data.a;
import cn.xiaochuankeji.tieba.ui.topic.TopicDetailActivity;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import com.alibaba.fastjson.JSONObject;
import org.json.JSONException;

public class TopicHolder extends a {
    @BindView
    WebImageView avatar;
    @BindView
    View click_area;
    @BindView
    TextView content;
    @BindView
    TextView count;
    @BindView
    WebImageView thumb;
    @BindView
    TextView title;

    public TopicHolder(ViewGroup viewGroup, int i) {
        super(viewGroup, i);
    }

    public void a(a aVar, int i) {
        a(aVar, i, this.avatar);
        Object a = a(aVar.f);
        if (a instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) a;
            CharSequence string = jSONObject.getString("msg");
            if (TextUtils.isEmpty(string)) {
                this.content.setVisibility(8);
            } else {
                this.content.setVisibility(0);
                this.content.setText(string);
            }
            try {
                final Topic topic = new Topic(new org.json.JSONObject(jSONObject.getJSONObject("data").getJSONObject("topic").toString()));
                this.thumb.setWebImage(b.c(topic._topicCoverID, false));
                if (TextUtils.isEmpty(topic._topicName)) {
                    this.title.setVisibility(8);
                } else {
                    this.title.setVisibility(0);
                    this.title.setText(topic._topicName);
                }
                if (TextUtils.isEmpty(topic._addition)) {
                    this.count.setVisibility(8);
                } else {
                    this.count.setVisibility(0);
                    this.count.setText(topic._addition);
                }
                a(this.click_area, new rx.b.b<Void>(this) {
                    final /* synthetic */ TopicHolder b;

                    public /* synthetic */ void call(Object obj) {
                        a((Void) obj);
                    }

                    public void a(Void voidR) {
                        if (this.b.click_area != null && this.b.click_area.getContext() != null && topic != null) {
                            TopicDetailActivity.a(this.b.click_area.getContext(), topic, null);
                        }
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
