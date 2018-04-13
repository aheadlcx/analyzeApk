package cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable;

import android.content.Context;
import android.graphics.drawable.Animatable;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class AnimatedStickerDrawable extends a implements Animatable {
    public AnimatedStickerDrawable(Context context) {
        super(context);
    }

    public AnimatedStickerDrawable(Context context, JSONObject jSONObject) throws JSONException {
        super(context, jSONObject);
    }

    public boolean a() {
        return true;
    }
}
