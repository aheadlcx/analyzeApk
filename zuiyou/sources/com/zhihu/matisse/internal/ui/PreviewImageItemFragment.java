package com.zhihu.matisse.internal.ui;

import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.davemorrissey.labs.subscaleview.a;
import com.zhihu.matisse.R;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.entity.SelectionSpec;
import com.zhihu.matisse.internal.utils.PhotoMetadataUtils;
import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase.DisplayType;
import java.io.File;

public class PreviewImageItemFragment extends Fragment {
    private static final String ARGS_ITEM = "args_item";
    private Item item;

    public static PreviewImageItemFragment newInstance(Item item) {
        PreviewImageItemFragment previewImageItemFragment = new PreviewImageItemFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGS_ITEM, item);
        previewImageItemFragment.setArguments(bundle);
        return previewImageItemFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_preview_image_item, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.item = (Item) getArguments().getParcelable(ARGS_ITEM);
        if (this.item != null) {
            ImageViewTouch imageViewTouch = (ImageViewTouch) view.findViewById(R.id.image_view);
            imageViewTouch.setDisplayType(DisplayType.FIT_TO_SCREEN);
            SubsamplingScaleImageView subsamplingScaleImageView = (SubsamplingScaleImageView) view.findViewById(R.id.image_view_big);
            Point bitmapSize = PhotoMetadataUtils.getBitmapSize(this.item.getContentUri(), getActivity());
            if (this.item.isGif()) {
                imageViewTouch.setVisibility(0);
                subsamplingScaleImageView.setVisibility(8);
                SelectionSpec.getInstance().imageEngine.a(getContext(), bitmapSize.x, bitmapSize.y, imageViewTouch, Uri.parse("file://" + this.item.path));
                return;
            }
            imageViewTouch.setVisibility(8);
            subsamplingScaleImageView.setVisibility(0);
            subsamplingScaleImageView.setImage(a.a(Uri.fromFile(new File(this.item.path))));
        }
    }
}
