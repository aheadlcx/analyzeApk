package qsbk.app.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.facebook.imagepipeline.request.Postprocessor;
import java.lang.ref.WeakReference;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.utils.UIHelper;

public abstract class BaseCell {
    private View a;
    private Object b;
    protected int q;
    protected WeakReference<ViewGroup> r;
    protected Drawable s;

    public abstract void onCreate();

    public abstract void onUpdate();

    protected Drawable a(Context context) {
        if (this.s == null) {
            this.s = context.getResources().getDrawable(UIHelper.getShare2IMIcon());
        }
        return this.s;
    }

    public void displayImage(ImageView imageView, String str) {
        displayImage(imageView, str, a(imageView.getContext()));
    }

    public void displayImage(ImageView imageView, String str, Drawable drawable) {
        displayImage(imageView, str, drawable, null);
    }

    public void displayImage(ImageView imageView, String str, Drawable drawable, Drawable drawable2) {
        FrescoImageloader.displayImage(imageView, str, drawable, drawable2);
    }

    public void displayImage(ImageView imageView, String str, Drawable drawable, Drawable drawable2, Postprocessor postprocessor) {
        FrescoImageloader.displayImage(imageView, str, drawable, drawable2, postprocessor);
    }

    public void displayAvatar(ImageView imageView, String str) {
        FrescoImageloader.displayAvatar(imageView, str);
    }

    public View getCellView() {
        return this.a;
    }

    public void setCellView(View view) {
        this.a = view;
    }

    public void setCellView(int i) {
        ViewGroup viewGroup = (ViewGroup) this.r.get();
        if (viewGroup != null) {
            setCellView(LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false));
        }
    }

    public Context getContext() {
        if (this.a == null) {
            return ((ViewGroup) this.r.get()).getContext();
        }
        return this.a.getContext();
    }

    public <T extends View> T findViewById(int i) {
        return this.a.findViewById(i);
    }

    public void performCreate(int i, ViewGroup viewGroup, Object obj) {
        this.q = i;
        this.r = new WeakReference(viewGroup);
        this.b = obj;
        onCreate();
        onItemChange(null);
    }

    public void performUpdate(int i, ViewGroup viewGroup, Object obj) {
        this.q = i;
        this.r = new WeakReference(viewGroup);
        if (this.b != obj) {
            Object obj2 = this.b;
            this.b = obj;
            onItemChange(obj2);
        }
        onUpdate();
    }

    public Object getItem() {
        return this.b;
    }

    public int getPosition() {
        return this.q;
    }

    public void onItemChange(Object obj) {
    }

    public void onClick() {
    }
}
