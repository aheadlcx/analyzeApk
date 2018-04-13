package com.zhihu.matisse.filter;

import android.content.Context;
import android.graphics.Point;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.R;
import com.zhihu.matisse.internal.entity.IncapableCause;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.utils.PhotoMetadataUtils;
import java.util.HashSet;
import java.util.Set;

public class GifSizeFilter extends Filter {
    private int mMaxSize;
    private int mMinHeight;
    private int mMinWidth;

    public GifSizeFilter(int i, int i2, int i3) {
        this.mMinWidth = i;
        this.mMinHeight = i2;
        this.mMaxSize = i3;
    }

    public Set<MimeType> constraintTypes() {
        return new HashSet<MimeType>() {
            {
                add(MimeType.GIF);
            }
        };
    }

    public IncapableCause filter(Context context, Item item) {
        if (!needFiltering(context, item)) {
            return null;
        }
        Point bitmapBound = PhotoMetadataUtils.getBitmapBound(context.getContentResolver(), item.getContentUri());
        if (bitmapBound.x >= this.mMinWidth && bitmapBound.y >= this.mMinHeight && item.size <= ((long) this.mMaxSize)) {
            return null;
        }
        return new IncapableCause(1, context.getString(R.string.error_gif, new Object[]{Integer.valueOf(this.mMinWidth), String.valueOf(PhotoMetadataUtils.getSizeInMB((long) this.mMaxSize))}));
    }
}
