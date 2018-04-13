package qsbk.app.fragments;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tencent.open.SocialConstants;
import java.io.File;
import qsbk.app.R;
import qsbk.app.model.ImageInfo;
import qsbk.app.utils.FileUtils;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.Util;
import qsbk.app.widget.imageview.FrescoTouchImageView;

public class BrowseImgFragment extends BrowseBaseFragment {
    public static final String IMAGE_SAVE_FOLDER = "qsbk/qiushibaike";
    FrescoTouchImageView a;
    private ImageInfo b;
    private MediaScannerConnection c;

    public static BrowseImgFragment newInstance(ImageInfo imageInfo) {
        BrowseImgFragment browseImgFragment = new BrowseImgFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(SocialConstants.PARAM_IMG_URL, imageInfo);
        browseImgFragment.setArguments(bundle);
        return browseImgFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.b = (ImageInfo) arguments.getSerializable(SocialConstants.PARAM_IMG_URL);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragmenet_browse_img, null);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.a = (FrescoTouchImageView) view.findViewById(R.id.image);
        this.a.loadImage(this.b.getImageUrl(), this.b.getBigImageUrl());
        this.a.setOnClickListener(new ab(this));
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.c != null && this.c.isConnected()) {
            this.c.disconnect();
        }
    }

    public boolean isMediaSaved() {
        if (this.b == null) {
            return false;
        }
        File saveDrawableFile = FileUtils.getSaveDrawableFile(this.b.hashCode() + ".jpg", IMAGE_SAVE_FOLDER);
        if (saveDrawableFile == null || !saveDrawableFile.exists()) {
            return false;
        }
        return true;
    }

    public void saveMedia() {
        Drawable drawable = this.a.getDrawable();
        if (drawable != null && this.b != null) {
            Bitmap bitmapFromDrawable = Util.getBitmapFromDrawable(drawable);
            if (bitmapFromDrawable != null) {
                String str = "图片已保存到系统相册";
                str = FileUtils.saveDrawable(bitmapFromDrawable, this.b.hashCode() + ".jpg", IMAGE_SAVE_FOLDER);
                if (str == null || str.length() == 0) {
                    try {
                        Media.insertImage(getContext().getContentResolver(), bitmapFromDrawable, null, null);
                        return;
                    } catch (Exception e) {
                        ToastAndDialog.makeNeutralToast(getActivity(), "保存失败，请确保SD卡能正确访问，并有足够的剩余空间。", Integer.valueOf(1)).show();
                        return;
                    }
                }
                ToastAndDialog.makeNeutralToast(getActivity(), "图片已保存到 " + str, Integer.valueOf(1)).show();
                this.c = new MediaScannerConnection(getActivity(), new ac(this, str));
                this.c.connect();
                return;
            }
            ToastAndDialog.makeNeutralToast(getActivity(), "保存失败", Integer.valueOf(1)).show();
        }
    }
}
