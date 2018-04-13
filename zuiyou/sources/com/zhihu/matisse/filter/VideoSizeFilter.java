package com.zhihu.matisse.filter;

import android.content.Context;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.R;
import com.zhihu.matisse.internal.entity.IncapableCause;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.utils.PhotoMetadataUtils;
import java.util.HashSet;
import java.util.Set;

public class VideoSizeFilter extends Filter {
    private int mDuration;
    private int mMaxSize;

    public VideoSizeFilter(int i, int i2) {
        this.mMaxSize = i;
        this.mDuration = i2;
    }

    protected Set<MimeType> constraintTypes() {
        return new HashSet<MimeType>() {
            {
                addAll(MimeType.ofVideo());
            }
        };
    }

    public IncapableCause filter(Context context, Item item) {
        if (!needFiltering(context, item)) {
            return null;
        }
        if (item.duration > ((long) this.mDuration)) {
            return new IncapableCause(0, context.getString(R.string.error_video_length, new Object[]{String.valueOf(PhotoMetadataUtils.getLengthInMinute((long) this.mDuration))}));
        } else if (item.size <= ((long) this.mMaxSize)) {
            return null;
        } else {
            return new IncapableCause(0, context.getString(R.string.error_video_size, new Object[]{String.valueOf(PhotoMetadataUtils.getSizeInMB((long) this.mMaxSize))}));
        }
    }
}
