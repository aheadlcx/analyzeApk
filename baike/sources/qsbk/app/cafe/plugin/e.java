package qsbk.app.cafe.plugin;

import android.content.Intent;
import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;

class e extends BaseBitmapDataSubscriber {
    final /* synthetic */ Intent a;
    final /* synthetic */ Intent b;
    final /* synthetic */ ShortcutPlugin c;

    e(ShortcutPlugin shortcutPlugin, Intent intent, Intent intent2) {
        this.c = shortcutPlugin;
        this.a = intent;
        this.b = intent2;
    }

    protected void onNewResultImpl(Bitmap bitmap) {
        this.a.putExtra("android.intent.extra.shortcut.ICON", bitmap);
        this.a.putExtra("android.intent.extra.shortcut.INTENT", this.b);
        this.c.b.getCurActivity().runOnUiThread(new f(this));
    }

    protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
    }
}
