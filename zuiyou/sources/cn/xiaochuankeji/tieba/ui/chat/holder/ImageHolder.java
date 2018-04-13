package cn.xiaochuankeji.tieba.ui.chat.holder;

import android.view.ViewGroup;
import butterknife.BindView;
import cn.xiaochuankeji.tieba.push.data.a;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import com.alibaba.fastjson.JSONObject;

public class ImageHolder extends a {
    @BindView
    WebImageView avatar;
    @BindView
    WebImageView image;

    public ImageHolder(ViewGroup viewGroup, int i) {
        super(viewGroup, i);
    }

    public void a(a aVar, int i) {
        a(aVar, i, this.avatar);
        Object a = a(aVar.f);
        if (a instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) a;
            long longValue = jSONObject.getLongValue("id");
            int intValue = jSONObject.getIntValue("w");
            int intValue2 = jSONObject.getIntValue("h");
            a(this.image, jSONObject.getString("fmt"), longValue, null, (float) intValue, (float) intValue2);
        }
        a(this.avatar, new b(this, this.a.session_type, aVar.a, aVar.c, aVar.e));
        b(this.image, new a(this, aVar, this.image.getContext()));
    }
}
