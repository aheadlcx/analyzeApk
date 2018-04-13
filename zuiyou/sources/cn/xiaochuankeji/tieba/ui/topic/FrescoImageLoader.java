package cn.xiaochuankeji.tieba.ui.topic;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.utils.e;
import com.facebook.drawee.drawable.n$b;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.generic.b;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.loader.ImageLoader;

public class FrescoImageLoader extends ImageLoader {
    private boolean mEnableRound;

    public FrescoImageLoader(boolean z) {
        this.mEnableRound = z;
    }

    public void displayImage(Context context, Object obj, ImageView imageView) {
        imageView.setImageURI(Uri.parse((String) obj));
    }

    public ImageView createImageView(Context context) {
        ImageView simpleDraweeView = new SimpleDraweeView(context);
        b bVar = new b(context.getResources());
        bVar.b((int) R.drawable.icon_top_default_placeholde);
        bVar.a(n$b.g);
        if (this.mEnableRound) {
            RoundingParams roundingParams = new RoundingParams();
            roundingParams.a((float) e.a(5.0f));
            bVar.a(roundingParams);
        }
        simpleDraweeView.setHierarchy(bVar.t());
        return simpleDraweeView;
    }
}
