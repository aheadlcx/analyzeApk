package cn.v6.sixrooms.room.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.widget.ViewSwitcher;
import com.facebook.drawee.view.SimpleDraweeView;

public class CustomImageSwitcher extends ViewSwitcher {
    public CustomImageSwitcher(Context context) {
        super(context);
    }

    public CustomImageSwitcher(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setImageResource(@DrawableRes int i) {
        ((SimpleDraweeView) getNextView()).setImageResource(i);
        showNext();
    }

    public void setImageURI(Uri uri) {
        ((SimpleDraweeView) getNextView()).setImageURI(uri);
        showNext();
    }

    public void setImageDrawable(Drawable drawable) {
        ((SimpleDraweeView) getNextView()).setImageDrawable(drawable);
        showNext();
    }
}
