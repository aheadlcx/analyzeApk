package cn.xiaochuankeji.tieba.ui.publish;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl.Type;
import cn.xiaochuankeji.tieba.ui.mediabrowse.MediaBrowseWhenSelectActivity;
import cn.xiaochuankeji.tieba.ui.selectlocalmedia.LocalMedia;
import cn.xiaochuankeji.tieba.ui.utils.e;
import cn.xiaochuankeji.tieba.ui.widget.SquareFrameLayout;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import com.zhihu.matisse.ResultItem;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class PublishPostPicturesView extends cn.htjyb.ui.widget.a.a implements cn.htjyb.ui.a {
    private Context a;
    private a b;
    private int c;
    private b d;
    private ArrayList<ResultItem> e = new ArrayList();

    public interface b {
        void k();
    }

    private class a extends BaseAdapter {
        final /* synthetic */ PublishPostPicturesView a;

        private a(PublishPostPicturesView publishPostPicturesView) {
            this.a = publishPostPicturesView;
        }

        public int getCount() {
            if (this.a.e.size() == 0) {
                return 0;
            }
            if (this.a.e.size() < this.a.c) {
                return this.a.e.size() + 1;
            }
            return this.a.c;
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public int getItemViewType(int i) {
            if (i < this.a.e.size()) {
                return 0;
            }
            return 1;
        }

        public int getViewTypeCount() {
            return 2;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            int itemViewType = getItemViewType(i);
            View cVar;
            if (itemViewType == 0) {
                cVar = view != null ? view : new c(this.a, this.a.a);
                ((c) cVar).a(i);
                return cVar;
            } else if (itemViewType != 1) {
                return null;
            } else {
                if (view != null) {
                    return view;
                }
                cVar = new SquareFrameLayout(this.a.a);
                View imageView = new ImageView(this.a.a);
                LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
                layoutParams.rightMargin = e.a(17.0f);
                layoutParams.topMargin = e.a(17.0f);
                imageView.setScaleType(ScaleType.FIT_XY);
                cVar.addView(imageView, layoutParams);
                if (c.a.c.e().c()) {
                    imageView.setImageResource(R.drawable.add_picture_night);
                } else {
                    imageView.setImageResource(R.drawable.add_picture);
                }
                imageView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        this.a.a.b();
                    }
                });
                return cVar;
            }
        }
    }

    private class c extends FrameLayout implements OnClickListener {
        final /* synthetic */ PublishPostPicturesView a;
        private int b;
        private ImageView c = ((ImageView) findViewById(R.id.viewEdited));
        private ImageView d = ((ImageView) findViewById(R.id.viewMediaType));
        private ImageView e = ((ImageView) findViewById(R.id.viewRemove));
        private WebImageView f = ((WebImageView) findViewById(R.id.viewPicture));

        public c(PublishPostPicturesView publishPostPicturesView, Context context) {
            this.a = publishPostPicturesView;
            super(context);
            LayoutInflater.from(context).inflate(R.layout.view_publish_post_picture, this, true);
            if (c.a.c.e().c()) {
                this.e.setImageResource(R.drawable.icon_delete_picture_night);
            } else {
                this.e.setImageResource(R.drawable.icon_delete_picture);
            }
            this.e.setOnClickListener(this);
            this.f.setOnClickListener(this);
        }

        protected void onMeasure(int i, int i2) {
            int size = MeasureSpec.getSize(i);
            setMeasuredDimension(size, size);
            cn.htjyb.ui.b.a((ViewGroup) this);
        }

        public void a(int i) {
            String str;
            this.b = i;
            ResultItem resultItem = (ResultItem) this.a.e.get(i);
            if (resultItem.mimeType != null && resultItem.mimeType.contains("video")) {
                this.d.setImageResource(R.drawable.img_video_flag_small);
                this.d.setVisibility(0);
            } else if (this.a.a(resultItem.path)) {
                this.d.setImageResource(R.drawable.img_gif_flag_small);
                this.d.setVisibility(0);
            } else {
                this.d.setVisibility(8);
            }
            String str2 = resultItem.path;
            if (TextUtils.isEmpty(resultItem.thumbnailPath)) {
                str = str2;
            } else {
                str = resultItem.thumbnailPath;
            }
            cn.xiaochuan.image.b.b.a(this.a.a).a(260, 260).b(true).a(1.0f).a(Uri.fromFile(new File(str))).a(e.a(4.0f)).a(new ColorDrawable(Color.parseColor("#808080"))).a(this.f);
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.viewPicture:
                    this.a.b(this.b);
                    return;
                case R.id.viewRemove:
                    this.a.a(this.b);
                    return;
                default:
                    return;
            }
        }
    }

    public PublishPostPicturesView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = context;
    }

    public void a(int i, b bVar) {
        this.c = i;
        this.d = bVar;
        this.b = new a();
        setAdapter(this.b);
    }

    public ArrayList<LocalMedia> getSelectMedias() {
        ArrayList<LocalMedia> arrayList = new ArrayList();
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            ResultItem resultItem = (ResultItem) it.next();
            LocalMedia localMedia = new LocalMedia();
            int i = (resultItem.mimeType == null || !resultItem.mimeType.contains("video")) ? 2 : 1;
            localMedia.type = i;
            localMedia.mimeType = resultItem.mimeType;
            localMedia.path = resultItem.path;
            localMedia.mediaID = (int) resultItem.id;
            localMedia.width = resultItem.width;
            localMedia.height = resultItem.height;
            arrayList.add(localMedia);
        }
        return arrayList;
    }

    public List<ResultItem> getSelectedItems() {
        return this.e;
    }

    public void setSelectMedias(Collection<ResultItem> collection) {
        this.e.clear();
        this.e.addAll(collection);
        a();
    }

    private void a(int i) {
        this.e.remove(i);
        a();
    }

    private void a() {
        this.b.notifyDataSetChanged();
        if (this.e.size() > 0) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
        if (this.d != null) {
            this.d.k();
        }
    }

    private void b() {
        cn.xiaochuankeji.tieba.ui.selectlocalmedia.b.b((Activity) this.a, 2, this.e);
    }

    private void b(int i) {
        cn.xiaochuankeji.tieba.background.picture.a f = cn.xiaochuankeji.tieba.background.a.f();
        ArrayList arrayList = new ArrayList();
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            Object a;
            ResultItem resultItem = (ResultItem) it.next();
            if (resultItem.mimeType.contains("video")) {
                a = f.a(resultItem.path, Type.kVideo, 0);
            } else if (resultItem.path.substring(resultItem.path.length() - 3, resultItem.path.length()).equalsIgnoreCase("gif")) {
                a = f.a(resultItem.path, Type.kGif, 0);
            } else {
                a = f.a(resultItem.path, Type.kPostPicLarge, 0);
            }
            arrayList.add(a);
        }
        MediaBrowseWhenSelectActivity.a(this.a, arrayList, null, i);
    }

    public void c() {
        this.e.clear();
    }

    private boolean a(String str) {
        if (TextUtils.isEmpty(str) || str.length() <= 3) {
            return false;
        }
        String substring = str.substring(str.length() - 3);
        if (substring.equals("gif") || substring.equals("GIF")) {
            return true;
        }
        return false;
    }
}
