package qsbk.app.fragments;

import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.imagepipeline.request.ImageRequest;
import com.tencent.open.SocialConstants;
import java.io.File;
import qsbk.app.R;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.ImageInfo;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.FileUtils;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.widget.QBImageView;

public class BrowseLongImgFragment extends BrowseBaseFragment {
    public static final String TAG = BrowseLongImgFragment.class.getSimpleName();
    QBImageView a;
    SubsamplingScaleImageView b;
    ImageInfo c;
    OnClickListener d = new ad(this);
    private MediaScannerConnection e;
    private boolean f;

    public static BrowseLongImgFragment newInstance(ImageInfo imageInfo) {
        BrowseLongImgFragment browseLongImgFragment = new BrowseLongImgFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(SocialConstants.PARAM_IMG_URL, imageInfo);
        browseLongImgFragment.setArguments(bundle);
        return browseLongImgFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.c = (ImageInfo) arguments.getSerializable(SocialConstants.PARAM_IMG_URL);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragmenet_browse_long, null);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.a = (QBImageView) view.findViewById(R.id.image);
        this.a.setOnClickListener(this.d);
        this.b = (SubsamplingScaleImageView) view.findViewById(R.id.big_image);
        this.b.setOnClickListener(this.d);
        this.b.setMinimumScaleType(3);
        this.b.setOnImageEventListener(new ae(this, this.b));
        a();
    }

    private void a() {
        b();
        File diskCacheFile = FrescoImageloader.getDiskCacheFile(Uri.parse(this.c.getBigImageUrl()));
        if (diskCacheFile == null || !diskCacheFile.exists()) {
            Fresco.getImagePipeline().fetchEncodedImage(ImageRequest.fromUri(this.c.getBigImageUrl()), Boolean.valueOf(true)).subscribe(new af(this, getContext().getApplicationContext()), FrescoImageloader.getExecutorSupplier().forBackgroundTasks());
            return;
        }
        a(diskCacheFile);
    }

    private void b() {
        ((GenericDraweeHierarchy) this.a.getHierarchy()).setActualImageScaleType(FrescoImageloader.SCALE_CENTER_TOP);
        FrescoImageloader.displayImage(this.a, this.c.getImageUrl());
    }

    protected void a(File file) {
        this.b.setImage(ImageSource.uri(Uri.fromFile(file)));
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.e != null && this.e.isConnected()) {
            this.e.disconnect();
        }
    }

    public boolean isMediaSaved() {
        if (this.c == null) {
            return false;
        }
        File saveDrawableFile = FileUtils.getSaveDrawableFile(this.c.hashCode() + ".jpg", BrowseImgFragment.IMAGE_SAVE_FOLDER);
        if (saveDrawableFile == null || !saveDrawableFile.exists()) {
            return false;
        }
        return true;
    }

    public void saveMedia() {
        File diskCacheFile = FrescoImageloader.getDiskCacheFile(Uri.parse(this.c.getBigImageUrl()));
        String str = "保存失败，请确保SD卡能正确访问，并有足够的剩余空间。";
        if (diskCacheFile == null || !diskCacheFile.exists()) {
            ToastAndDialog.makeNeutralToast(getActivity(), "保存失败", Integer.valueOf(1)).show();
        } else if (TextUtils.isEmpty(DeviceUtils.getSDPath())) {
            ToastAndDialog.makeNeutralToast(getActivity(), str, Integer.valueOf(1)).show();
        } else {
            File saveDrawableFile = FileUtils.getSaveDrawableFile(this.c.hashCode() + ".jpg", BrowseImgFragment.IMAGE_SAVE_FOLDER);
            if (saveDrawableFile == null) {
                ToastAndDialog.makeNeutralToast(getActivity(), str, Integer.valueOf(1)).show();
            } else if (FileUtils.copyFile(diskCacheFile, saveDrawableFile)) {
                String path = saveDrawableFile.getPath();
                ToastAndDialog.makeNeutralToast(getActivity(), "图片已保存到 " + path, Integer.valueOf(1)).show();
                this.f = true;
                this.e = new MediaScannerConnection(getActivity(), new ah(this, path));
                this.e.connect();
            } else {
                ToastAndDialog.makeNeutralToast(getActivity(), "保存失败，请确保SD卡能正确访问，并有足够的剩余空间。", Integer.valueOf(1)).show();
            }
        }
    }
}
