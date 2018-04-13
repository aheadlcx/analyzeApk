package qsbk.app.fragments;

import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.drawee.interfaces.DraweeController;
import com.tencent.open.SocialConstants;
import qsbk.app.R;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.ImageInfo;
import qsbk.app.utils.FileUtils;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.Util;
import qsbk.app.widget.QBImageView;

public class BrowseUltraFragment extends BrowseBaseFragment {
    QBImageView a;
    ImageInfo b;
    private MediaScannerConnection c;

    public static BrowseUltraFragment newInstance(@NonNull ImageInfo imageInfo) {
        BrowseUltraFragment browseUltraFragment = new BrowseUltraFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(SocialConstants.PARAM_IMG_URL, imageInfo);
        browseUltraFragment.setArguments(bundle);
        return browseUltraFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b = (ImageInfo) getArguments().getSerializable(SocialConstants.PARAM_IMG_URL);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragmenet_browse_local_gif, null);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.a = (QBImageView) view.findViewById(R.id.imge_gif);
        this.a.setOnClickListener(new ai(this));
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.b != null) {
            FrescoImageloader.displayImage(this.a, this.b.getImageUrl(), null, true);
        }
    }

    public void onResume() {
        super.onResume();
        a(getUserVisibleHint());
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (this.a != null) {
            a(z);
        }
    }

    private void a(boolean z) {
        if (this.a != null) {
            DraweeController controller = this.a.getController();
            if (controller != null) {
                Animatable animatable = controller.getAnimatable();
                if (animatable == null) {
                    return;
                }
                if (z) {
                    animatable.start();
                } else {
                    animatable.stop();
                }
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.c != null && this.c.isConnected()) {
            this.c.disconnect();
        }
    }

    public boolean isMediaSaved() {
        return false;
    }

    public void saveMedia() {
        Bitmap bitmapFromDrawable = Util.getBitmapFromDrawable(this.a.getDrawable());
        if (bitmapFromDrawable != null) {
            String str = "图片已保存到系统相册";
            str = FileUtils.saveDrawable(bitmapFromDrawable, this.b.hashCode() + ".jpg", BrowseImgFragment.IMAGE_SAVE_FOLDER);
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
            this.c = new MediaScannerConnection(getActivity(), new aj(this, str));
            this.c.connect();
            return;
        }
        ToastAndDialog.makeNeutralToast(getActivity(), "保存失败", Integer.valueOf(1)).show();
    }
}
