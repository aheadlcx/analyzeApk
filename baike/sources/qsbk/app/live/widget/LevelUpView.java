package qsbk.app.live.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.core.widget.FrameAnimationView;
import qsbk.app.live.R;

public class LevelUpView extends FrameLayout {
    private SimpleDraweeView a;
    private TextView b;
    private FrameLayout c;
    private ImageView d;
    private ImageView e;
    private FrameAnimationView f;
    private boolean g;

    public LevelUpView(@NonNull Context context) {
        this(context, null);
    }

    public LevelUpView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LevelUpView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = false;
        a();
    }

    private void a() {
        View inflate = View.inflate(getContext(), R.layout.inflate_level_up, this);
        this.a = (SimpleDraweeView) inflate.findViewById(R.id.iv_levelup_avatar);
        this.b = (TextView) inflate.findViewById(R.id.tv_up_level);
        this.c = (FrameLayout) inflate.findViewById(R.id.fl_level_up);
        this.d = (ImageView) inflate.findViewById(R.id.iv_fan);
        this.e = (ImageView) inflate.findViewById(R.id.iv_arrow);
        this.f = (FrameAnimationView) inflate.findViewById(R.id.frames);
        this.f.setFramesInAssets("levelup_stars");
    }

    public void showLevelUp(String str, int i) {
        if (!this.g) {
            this.g = true;
            String string = getContext().getString(R.string.live_level_up);
            Object obj = string + "LV." + i;
            AppUtils.getInstance().getImageProvider().loadAvatar(this.a, str, true);
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#FDDB2E"));
            CharSequence spannableStringBuilder = new SpannableStringBuilder(obj);
            spannableStringBuilder.setSpan(foregroundColorSpan, string.length(), obj.length(), 18);
            this.b.setText(spannableStringBuilder);
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.c, View.TRANSLATION_X, new float[]{0.0f, (float) (-WindowUtils.dp2Px(200))});
            ofFloat.setDuration(200);
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.e, TRANSLATION_Y, new float[]{0.0f, (float) (-WindowUtils.dp2Px(25))});
            ofFloat2.setDuration(200);
            ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.b, TRANSLATION_Y, new float[]{0.0f, (float) (-WindowUtils.dp2Px(31))});
            ofFloat3.setDuration(400);
            ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.e, TRANSLATION_Y, new float[]{(float) (-WindowUtils.dp2Px(23)), (float) (-WindowUtils.dp2Px(46))});
            ofFloat4.setDuration(200);
            ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(this.a, ALPHA, new float[]{0.0f, 1.0f});
            ofFloat5.setDuration(200);
            ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(this.c, TRANSLATION_X, new float[]{(float) (-WindowUtils.dp2Px(200)), 0.0f});
            ofFloat6.setDuration(200);
            ObjectAnimator ofFloat7 = ObjectAnimator.ofFloat(this.d, ALPHA, new float[]{0.0f, 1.0f});
            ofFloat7.setDuration(200);
            ObjectAnimator ofFloat8 = ObjectAnimator.ofFloat(this.d, "rotation", new float[]{0.0f, 180.0f});
            ofFloat8.setDuration(2000);
            ObjectAnimator ofFloat9 = ObjectAnimator.ofFloat(this.d, ALPHA, new float[]{1.0f, 0.0f});
            ofFloat9.setDuration(200);
            ofFloat7.addListener(new gr(this, ofFloat8));
            ofFloat.start();
            ofFloat.addListener(new gs(this, ofFloat2));
            ofFloat2.addListener(new gt(this, ofFloat3, ofFloat7));
            ofFloat8.addListener(new gv(this, ofFloat4));
            ofFloat4.addListener(new gw(this, ofFloat5, ofFloat9));
            ofFloat5.addListener(new gx(this, ofFloat6));
            ofFloat6.addListener(new gz(this));
        }
    }
}
