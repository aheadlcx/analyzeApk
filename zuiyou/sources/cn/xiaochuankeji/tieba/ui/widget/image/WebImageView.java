package cn.xiaochuankeji.tieba.ui.widget.image;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import c.a.i.b.b;
import c.a.i.u;
import cn.xiaochuankeji.tieba.R;
import com.facebook.drawee.a.a.c;
import com.facebook.drawee.a.a.e;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.g.f;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class WebImageView extends SimpleDraweeView implements u {
    private c.a.i.b.a a;
    private b b;
    @DrawableRes
    private int c;

    public interface a {
        void a(WebImageView webImageView);

        void a(WebImageView webImageView, Throwable th);
    }

    public WebImageView(Context context) {
        this(context, null);
    }

    public WebImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WebImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = 0;
        this.a = new c.a.i.b.a(this);
        this.a.a(attributeSet, i);
        this.b = new b(this, R.color.image_cover);
        this.b.a(attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.GenericDraweeHierarchy);
        if (obtainStyledAttributes.hasValue(3)) {
            this.c = obtainStyledAttributes.getResourceId(3, 0);
        }
        obtainStyledAttributes.recycle();
        d();
    }

    public void setWebImage(a aVar) {
        if (aVar.a()) {
            setImageURI(aVar.b());
        } else {
            setImageURI(aVar.c());
        }
    }

    public void a(a aVar, int i, int i2) {
        if (aVar.a()) {
            a(aVar.b(), i, i2);
        } else {
            a(aVar.c(), i, i2);
        }
    }

    public void setImagePath(String str) {
        setImageURI("file://" + str);
    }

    public void setImageURI(String str) {
        super.setImageURI(str);
    }

    public void a(String str, final a aVar) {
        setController(((e) c.a().a(new com.facebook.drawee.controller.b<f>(this) {
            final /* synthetic */ WebImageView b;

            public void a(String str, f fVar, Animatable animatable) {
                if (aVar != null) {
                    aVar.a(this.b);
                }
            }

            public void a(String str, Throwable th) {
                if (aVar != null) {
                    aVar.a(this.b, th);
                }
            }
        })).a(str).k());
    }

    public void a(String str, int i, int i2) {
        if (str != null) {
            setController(((e) c.a().b(ImageRequestBuilder.a(Uri.parse(str)).a(new com.facebook.imagepipeline.j.a(i, i2)).n())).k());
        }
    }

    public void setImageResource(int i) {
        setImageURI("res:///" + i);
    }

    public void setData(cn.htjyb.b.a aVar) {
        String str;
        if (aVar.hasLocalFile()) {
            str = "file://" + aVar.getLocalFile().getAbsolutePath();
        } else {
            str = aVar.downloadUrl();
        }
        setController(((e) c.a().a(str).b(true)).k());
    }

    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        if (this.a != null) {
            this.a.a(i);
        }
    }

    public void d() {
        if (this.a != null) {
            this.a.a();
        }
        if (this.b != null) {
            this.b.a();
        }
        if (this.c != 0) {
            this.c = c.a.i.b.e.b(this.c);
            ((com.facebook.drawee.generic.a) getHierarchy()).b(c.a.d.a.a.a().b(this.c));
        }
    }
}
