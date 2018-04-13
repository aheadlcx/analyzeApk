package pl.droidsonroids.gif;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.widget.ImageView;
import java.io.IOException;

public class GifImageView extends ImageView {
    public GifImageView(Context context) {
        super(context);
    }

    public GifImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet, getResources());
    }

    public GifImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet, getResources());
    }

    @TargetApi(21)
    public GifImageView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a(attributeSet, getResources());
    }

    public void setImageResource(int i) {
        a(true, i, getResources());
    }

    public void setBackgroundResource(int i) {
        a(false, i, getResources());
    }

    void a(AttributeSet attributeSet, Resources resources) {
        if (attributeSet != null && resources != null && !isInEditMode()) {
            int attributeResourceValue = attributeSet.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "src", -1);
            if (attributeResourceValue > 0 && "drawable".equals(resources.getResourceTypeName(attributeResourceValue))) {
                a(true, attributeResourceValue, resources);
            }
            attributeResourceValue = attributeSet.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "background", -1);
            if (attributeResourceValue > 0 && "drawable".equals(resources.getResourceTypeName(attributeResourceValue))) {
                a(false, attributeResourceValue, resources);
            }
        }
    }

    @TargetApi(16)
    void a(boolean z, int i, Resources resources) {
        try {
            Drawable gifDrawable = new GifDrawable(resources, i);
            if (z) {
                setImageDrawable(gifDrawable);
            } else if (VERSION.SDK_INT >= 16) {
                setBackground(gifDrawable);
            } else {
                setBackgroundDrawable(gifDrawable);
            }
        } catch (IOException e) {
            if (z) {
                super.setBackgroundResource(i);
            } else {
                super.setImageResource(i);
            }
        } catch (NotFoundException e2) {
            if (z) {
                super.setBackgroundResource(i);
            } else {
                super.setImageResource(i);
            }
        }
    }

    public void setImageURI(Uri uri) {
        if (uri != null) {
            try {
                setImageDrawable(new GifDrawable(getContext().getContentResolver(), uri));
                return;
            } catch (IOException e) {
            }
        }
        super.setImageURI(uri);
    }
}
