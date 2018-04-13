package qsbk.app.im.CollectEmotion;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.interfaces.DraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.R;
import qsbk.app.im.ChatMsgEmotionData;
import qsbk.app.im.LatestUsedCollectionData;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.TileBackground;
import qsbk.app.utils.TileBackground.BgImageType;
import qsbk.app.utils.UIHelper;

public class CollectionGridView extends GridView {
    public static final int TOTAL_TYPE = 3;
    private static final String a = CollectionGridView.class.getSimpleName();
    private String b = "";
    private List<LatestUsedCollectionData> c;
    private CollectionGridViewAdapter d;
    private OnCollectionItemClickListener e;

    public class CollectionGridViewAdapter extends BaseAdapter {
        final /* synthetic */ CollectionGridView a;
        private ResizeOptions b;

        public CollectionGridViewAdapter(CollectionGridView collectionGridView) {
            this.a = collectionGridView;
            initImageloader();
        }

        public void initImageloader() {
        }

        private Drawable a() {
            return TileBackground.getBackgroud(this.a.getContext(), BgImageType.ARTICLE);
        }

        public int getCount() {
            return this.a.c.size();
        }

        public Object getItem(int i) {
            return this.a.c.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        private void a(ImageView imageView, String str) {
            if (imageView != null && str != null) {
                if (this.b == null) {
                    int dip2px = UIHelper.dip2px(imageView.getContext(), 64.0f);
                    this.b = new ResizeOptions(dip2px, dip2px);
                }
                SimpleDraweeView simpleDraweeView = (SimpleDraweeView) imageView;
                DraweeController build = ((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setOldController(simpleDraweeView.getController())).setImageRequest(ImageRequestBuilder.newBuilderWithSource(FrescoImageloader.get(str)).setResizeOptions(this.b).build())).build();
                DraweeHierarchy draweeHierarchy = (GenericDraweeHierarchy) simpleDraweeView.getHierarchy();
                if (draweeHierarchy == null) {
                    draweeHierarchy = new GenericDraweeHierarchyBuilder(simpleDraweeView.getResources()).build();
                }
                draweeHierarchy.setPlaceholderImage(a());
                draweeHierarchy.setFailureImage(a());
                build.setHierarchy(draweeHierarchy);
                simpleDraweeView.setController(build);
            }
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.collection_grid_item, null);
            }
            a a = a.a(view);
            LatestUsedCollectionData latestUsedCollectionData = (LatestUsedCollectionData) this.a.c.get(i);
            view.setOnClickListener(new a(this, i, latestUsedCollectionData));
            view.setOnLongClickListener(new b(this, i, latestUsedCollectionData));
            switch (latestUsedCollectionData.type) {
                case 1:
                    a.b.setVisibility(0);
                    if (!new File(DeviceUtils.getCollectSDPath() + File.separator + latestUsedCollectionData.collectImageDomain.url).exists()) {
                        a(a.a, latestUsedCollectionData.collectImageDomain.netUrl);
                        break;
                    }
                    a(a.a, "file://" + DeviceUtils.getCollectSDPath() + File.separator + latestUsedCollectionData.collectImageDomain.url);
                    break;
                case 2:
                    a.b.setVisibility(0);
                    ChatMsgEmotionData chatMsgEmotionData = latestUsedCollectionData.chatMsgEmotionData;
                    a.b.setText(chatMsgEmotionData.name.replace("[", "").replace("]", ""));
                    a.a.setImageResource(chatMsgEmotionData.localResource);
                    break;
                case 3:
                    a.b.setVisibility(0);
                    if (!latestUsedCollectionData.collectImageLocal.localUrl.startsWith("file://")) {
                        if (!new File(latestUsedCollectionData.collectImageLocal.localUrl).exists()) {
                            a(a.a, latestUsedCollectionData.collectImageLocal.netUrl);
                            break;
                        }
                        a(a.a, "file://" + latestUsedCollectionData.collectImageLocal.localUrl);
                        break;
                    } else if (!new File(latestUsedCollectionData.collectImageLocal.localUrl.substring(8)).exists()) {
                        a(a.a, latestUsedCollectionData.collectImageLocal.netUrl);
                        break;
                    } else {
                        a(a.a, latestUsedCollectionData.collectImageLocal.localUrl);
                        break;
                    }
                case 4:
                    a.b.setVisibility(0);
                    a.a.setImageResource(UIHelper.isNightTheme() ? R.drawable.image_add_dark : R.drawable.image_add);
                    view.setOnLongClickListener(new c(this));
                    break;
            }
            return view;
        }
    }

    public interface OnCollectionItemClickListener {
        void onCollectItemClick(int i, LatestUsedCollectionData latestUsedCollectionData);

        void onCollectItemLongClick(int i, LatestUsedCollectionData latestUsedCollectionData);
    }

    private static class a {
        ImageView a;
        TextView b;

        public a(View view) {
            this.a = (ImageView) view.findViewById(R.id.image);
            this.b = (TextView) view.findViewById(R.id.text);
        }

        static a a(View view) {
            a aVar = new a(view);
            view.setTag(view);
            return aVar;
        }
    }

    public CollectionGridView(Context context) {
        super(context);
    }

    public CollectionGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CollectionGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public CollectionGridView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
    }

    private void b() {
        if (this.c != null) {
            List arrayList = new ArrayList(this.c);
            if (arrayList != null && !arrayList.isEmpty()) {
                List arrayList2 = new ArrayList(arrayList.size());
                CharSequence charSequence = null;
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    CharSequence charSequence2;
                    LatestUsedCollectionData latestUsedCollectionData = (LatestUsedCollectionData) arrayList.get(i);
                    if (latestUsedCollectionData.type == 1) {
                        if (new File(DeviceUtils.getCollectSDPath() + File.separator + latestUsedCollectionData.collectImageDomain.url).exists()) {
                            charSequence2 = "file://" + DeviceUtils.getCollectSDPath() + File.separator + latestUsedCollectionData.collectImageDomain.url;
                        } else {
                            charSequence2 = latestUsedCollectionData.collectImageDomain.netUrl;
                        }
                    } else if (latestUsedCollectionData.type != 3) {
                        charSequence2 = charSequence;
                    } else if (latestUsedCollectionData.collectImageLocal.localUrl.startsWith("file://")) {
                        if (new File(latestUsedCollectionData.collectImageLocal.localUrl.substring(8)).exists()) {
                            charSequence2 = latestUsedCollectionData.collectImageLocal.localUrl;
                        } else {
                            charSequence2 = latestUsedCollectionData.collectImageLocal.netUrl;
                        }
                    } else if (new File(latestUsedCollectionData.collectImageLocal.localUrl).exists()) {
                        charSequence2 = "file://" + latestUsedCollectionData.collectImageLocal.localUrl;
                    } else {
                        charSequence2 = latestUsedCollectionData.collectImageLocal.netUrl;
                    }
                    if (!TextUtils.isEmpty(charSequence2)) {
                        arrayList2.add(charSequence2);
                    }
                    i++;
                    charSequence = charSequence2;
                }
                FrescoImageloader.evictFromMemoryCache(arrayList2);
            }
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        b();
    }

    public CollectionGridViewAdapter getCollectionGridViewAdapter() {
        return this.d;
    }

    public List<LatestUsedCollectionData> getData() {
        return this.c;
    }

    public void setData(List<LatestUsedCollectionData> list) {
        if (this.c != null) {
            this.c.clear();
        } else {
            this.c = new ArrayList();
        }
        this.c.addAll(list);
        if (this.d != null) {
            this.d.notifyDataSetChanged();
            return;
        }
        this.d = new CollectionGridViewAdapter(this);
        setAdapter(this.d);
    }

    public int getDataSize() {
        return this.c.size();
    }

    public void setOnCollectionItemClickListener(OnCollectionItemClickListener onCollectionItemClickListener) {
        this.e = onCollectionItemClickListener;
    }
}
