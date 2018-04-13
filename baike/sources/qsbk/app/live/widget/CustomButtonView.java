package qsbk.app.live.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.facebook.drawee.drawable.ScalingUtils.ScaleType;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import qsbk.app.core.model.CustomButton;
import qsbk.app.core.utils.AppUtils;

public class CustomButtonView extends SimpleDraweeView {
    private CustomButton a;

    public CustomButtonView(Context context, GenericDraweeHierarchy genericDraweeHierarchy) {
        super(context, genericDraweeHierarchy);
    }

    public CustomButtonView(Context context) {
        super(context);
    }

    public CustomButtonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CustomButtonView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public CustomButtonView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public void show(CustomButton customButton) {
        this.a = customButton;
        setVisibility(0);
        DraweeHierarchy draweeHierarchy = (GenericDraweeHierarchy) getHierarchy();
        if (draweeHierarchy == null) {
            draweeHierarchy = new GenericDraweeHierarchyBuilder(getContext().getResources()).setActualImageScaleType(ScaleType.FIT_XY).build();
        } else {
            draweeHierarchy.setActualImageScaleType(ScaleType.FIT_XY);
        }
        setHierarchy(draweeHierarchy);
        AppUtils.getInstance().getImageProvider().loadGift(this, customButton.button_url, false);
    }

    public CustomButton getBundle() {
        return this.a;
    }
}
