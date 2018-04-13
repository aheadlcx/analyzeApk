package cn.xiaochuankeji.tieba.ui.chat.holder;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import cn.xiaochuankeji.tieba.background.f.b;
import cn.xiaochuankeji.tieba.json.imgjson.ServerImgJson;
import cn.xiaochuankeji.tieba.push.data.a;
import cn.xiaochuankeji.tieba.ui.ugcvideodetail.UgcVideoActivity;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class UgcHolder extends a {
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

    public UgcHolder(ViewGroup viewGroup, int i) {
        super(viewGroup, i);
    }

    public void a(a aVar, int i) {
        a(aVar, i, this.avatar);
        a(this.avatar, new b(this, this.a.session_type, aVar.a, aVar.c, aVar.e));
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
            final int intValue = jSONObject.getIntValue("stype");
            jSONObject = jSONObject.getJSONObject("data");
            final long longValue = jSONObject.getLongValue("id");
            CharSequence string2 = jSONObject.getString("text");
            if (TextUtils.isEmpty(string2)) {
                this.title.setVisibility(8);
            } else {
                this.title.setVisibility(0);
                this.title.setText(string2);
            }
            this.thumb.setWebImage(b.c((long) ((ServerImgJson) JSON.parseObject(jSONObject.getJSONObject("img").toJSONString(), ServerImgJson.class)).id));
            a(this.click_area, new rx.b.b<Void>(this) {
                final /* synthetic */ UgcHolder c;

                public /* synthetic */ void call(Object obj) {
                    a((Void) obj);
                }

                public void a(Void voidR) {
                    if (this.c.click_area != null && this.c.click_area.getContext() != null) {
                        if (intValue == 1001) {
                            UgcVideoActivity.b(this.c.click_area.getContext(), longValue, "other");
                        } else if (intValue == 1002 || intValue == 1003) {
                            UgcVideoActivity.a(this.c.click_area.getContext(), longValue, "other");
                        }
                    }
                }
            });
        }
    }
}
