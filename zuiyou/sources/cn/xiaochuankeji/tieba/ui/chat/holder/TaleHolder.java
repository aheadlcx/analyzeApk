package cn.xiaochuankeji.tieba.ui.chat.holder;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.json.tale.FollowPostArticleJson;
import cn.xiaochuankeji.tieba.json.tale.FollowPostThemeJson;
import cn.xiaochuankeji.tieba.push.data.a;
import cn.xiaochuankeji.tieba.ui.tale.TaleDetailActivity;
import cn.xiaochuankeji.tieba.ui.tale.TaleListActivity;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import rx.b.b;

public class TaleHolder extends a {
    @BindView
    WebImageView avatar;
    @BindView
    View click_area;
    @BindView
    TextView content;
    @BindView
    WebImageView thumb;
    @BindView
    TextView title;

    public TaleHolder(ViewGroup viewGroup, int i) {
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
            int intValue = jSONObject.getIntValue("stype");
            JSONObject jSONObject2 = jSONObject.getJSONObject("data");
            if (intValue == 1301 || intValue == 1001) {
                this.thumb.setVisibility(0);
                this.thumb.setImageResource(R.drawable.ic_chat_tale_default);
                final FollowPostThemeJson followPostThemeJson = (FollowPostThemeJson) JSONObject.parseObject(jSONObject2.toJSONString(), FollowPostThemeJson.class);
                string = jSONObject2.getString("title");
                if (TextUtils.isEmpty(string)) {
                    this.title.setVisibility(8);
                } else {
                    this.title.setVisibility(0);
                    this.title.setText(string);
                }
                a(this.click_area, new b<Void>(this) {
                    final /* synthetic */ TaleHolder b;

                    public /* synthetic */ void call(Object obj) {
                        a((Void) obj);
                    }

                    public void a(Void voidR) {
                        if (this.b.click_area != null && this.b.click_area.getContext() != null && followPostThemeJson != null) {
                            JSONArray jSONArray = new JSONArray();
                            if (followPostThemeJson.postList != null) {
                                for (FollowPostArticleJson followPostArticleJson : followPostThemeJson.postList) {
                                    jSONArray.add(Long.valueOf(followPostArticleJson.id));
                                }
                            }
                            TaleListActivity.a(this.b.click_area.getContext(), "chat", followPostThemeJson.id, jSONArray.toJSONString());
                        }
                    }
                });
                return;
            }
            final FollowPostArticleJson followPostArticleJson = (FollowPostArticleJson) JSONObject.parseObject(jSONObject2.toJSONString(), FollowPostArticleJson.class);
            long j = followPostArticleJson.coverId;
            this.thumb.setVisibility(0);
            if (j > 0) {
                this.thumb.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(j));
            } else {
                this.thumb.setImageResource(R.drawable.ic_chat_tale_default);
            }
            if (TextUtils.isEmpty(followPostArticleJson.summary)) {
                this.title.setVisibility(8);
            } else {
                this.title.setVisibility(0);
                this.title.setText(followPostArticleJson.summary);
            }
            a(this.click_area, new b<Void>(this) {
                final /* synthetic */ TaleHolder b;

                public /* synthetic */ void call(Object obj) {
                    a((Void) obj);
                }

                public void a(Void voidR) {
                    if (this.b.click_area != null && this.b.click_area.getContext() != null && followPostArticleJson != null) {
                        TaleDetailActivity.a(this.b.click_area.getContext(), "chat", followPostArticleJson.id);
                    }
                }
            });
        }
    }
}
