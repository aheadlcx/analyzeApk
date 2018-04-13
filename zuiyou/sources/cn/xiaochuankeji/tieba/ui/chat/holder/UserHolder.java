package cn.xiaochuankeji.tieba.ui.chat.holder;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import cn.xiaochuankeji.tieba.push.data.a;
import cn.xiaochuankeji.tieba.ui.member.UserAllegeActivity;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import com.alibaba.fastjson.JSONObject;
import rx.b.b;

public class UserHolder extends a {
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

    public UserHolder(ViewGroup viewGroup, int i) {
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
            jSONObject = jSONObject.getJSONObject("data");
            final long longValue = jSONObject.getLongValue("admin_mid");
            final long longValue2 = jSONObject.getLongValue("tid");
            a(this.click_area, new b<Void>(this) {
                final /* synthetic */ UserHolder c;

                public /* synthetic */ void call(Object obj) {
                    a((Void) obj);
                }

                public void a(Void voidR) {
                    if (this.c.click_area != null && this.c.click_area.getContext() != null) {
                        UserAllegeActivity.a(this.c.click_area.getContext(), longValue, longValue2);
                    }
                }
            });
        }
    }
}
