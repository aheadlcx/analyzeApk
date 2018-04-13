package cn.xiaochuankeji.tieba.widget.rich;

import android.content.Context;
import android.net.Uri;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.bigImage.BigImageView;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import java.io.File;

public class a extends RelativeLayout {
    private cn.xiaochuankeji.tieba.widget.rich.RichTextEditor.a a;
    private int b;

    public interface a {
    }

    public a(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        View.inflate(context, R.layout.editor_image, this);
        this.b = (int) TypedValue.applyDimension(1, 12.0f, getResources().getDisplayMetrics());
    }

    public void a(Object obj, OnClickListener onClickListener) {
        View findViewById = findViewById(R.id.image_close);
        findViewById.setTag(obj);
        findViewById.setOnClickListener(onClickListener);
    }

    public void a(cn.xiaochuankeji.tieba.widget.rich.RichTextEditor.a aVar, int i, a aVar2) {
        this.a = aVar;
        BigImageView bigImageView = (BigImageView) findViewById(R.id.edit_imageView);
        bigImageView.setId(i);
        SubsamplingScaleImageView ssiv = bigImageView.getSSIV();
        if (ssiv != null) {
            ssiv.setQuickScaleEnabled(false);
            ssiv.setPanEnabled(false);
            ssiv.setZoomEnabled(false);
            ssiv.a();
            bigImageView.c();
        }
        bigImageView.setInitScaleType(1);
        bigImageView.a(Uri.fromFile(new File(aVar.g)));
    }

    public cn.xiaochuankeji.tieba.widget.rich.RichTextEditor.a getTale() {
        return this.a;
    }
}
