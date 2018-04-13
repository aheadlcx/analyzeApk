package android.support.v7.view;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v7.appcompat.R;
import android.view.ViewConfiguration;
import qsbk.app.activity.publish.Publish_Image;
import qsbk.app.core.utils.PictureGetHelper;
import qsbk.app.video.VideoEditActivity;

@RestrictTo({Scope.LIBRARY_GROUP})
public class ActionBarPolicy {
    private Context a;

    public static ActionBarPolicy get(Context context) {
        return new ActionBarPolicy(context);
    }

    private ActionBarPolicy(Context context) {
        this.a = context;
    }

    public int getMaxActionButtons() {
        Configuration configuration = this.a.getResources().getConfiguration();
        int i = configuration.screenWidthDp;
        int i2 = configuration.screenHeightDp;
        if (configuration.smallestScreenWidthDp > 600 || i > 600 || ((i > Publish_Image.MIN_IMG_HEIGHT && i2 > 720) || (i > 720 && i2 > Publish_Image.MIN_IMG_HEIGHT))) {
            return 5;
        }
        if (i >= 500 || ((i > PictureGetHelper.IMAGE_SIZE && i2 > VideoEditActivity.MAX_VIDEO_WIDTH) || (i > VideoEditActivity.MAX_VIDEO_WIDTH && i2 > PictureGetHelper.IMAGE_SIZE))) {
            return 4;
        }
        if (i >= 360) {
            return 3;
        }
        return 2;
    }

    public boolean showsOverflowMenuButton() {
        if (VERSION.SDK_INT < 19 && ViewConfiguration.get(this.a).hasPermanentMenuKey()) {
            return false;
        }
        return true;
    }

    public int getEmbeddedMenuWidthLimit() {
        return this.a.getResources().getDisplayMetrics().widthPixels / 2;
    }

    public boolean hasEmbeddedTabs() {
        return this.a.getResources().getBoolean(R.bool.abc_action_bar_embed_tabs);
    }

    public int getTabContainerHeight() {
        TypedArray obtainStyledAttributes = this.a.obtainStyledAttributes(null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
        int layoutDimension = obtainStyledAttributes.getLayoutDimension(R.styleable.ActionBar_height, 0);
        Resources resources = this.a.getResources();
        if (!hasEmbeddedTabs()) {
            layoutDimension = Math.min(layoutDimension, resources.getDimensionPixelSize(R.dimen.abc_action_bar_stacked_max_height));
        }
        obtainStyledAttributes.recycle();
        return layoutDimension;
    }

    public boolean enableHomeButtonByDefault() {
        return this.a.getApplicationInfo().targetSdkVersion < 14;
    }

    public int getStackedTabMaxWidth() {
        return this.a.getResources().getDimensionPixelSize(R.dimen.abc_action_bar_stacked_tab_max_width);
    }
}
