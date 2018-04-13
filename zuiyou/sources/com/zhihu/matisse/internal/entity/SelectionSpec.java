package com.zhihu.matisse.internal.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.StyleRes;
import cn.xiaochuan.image.b.a;
import cn.xiaochuan.image.b.c;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.R;
import com.zhihu.matisse.ResultItem;
import com.zhihu.matisse.filter.Filter;
import java.util.List;
import java.util.Set;

public final class SelectionSpec implements Parcelable {
    public static final Creator<SelectionSpec> CREATOR = new Creator<SelectionSpec>() {
        public SelectionSpec createFromParcel(Parcel parcel) {
            return new SelectionSpec(parcel);
        }

        public SelectionSpec[] newArray(int i) {
            return new SelectionSpec[i];
        }
    };
    public boolean capture;
    public boolean countable;
    public List<Filter> filters;
    public int gridExpectedSize;
    public c imageEngine;
    public boolean isNightMode;
    public int maxSelectable;
    public boolean mediaTypeExclusive;
    public Set<MimeType> mimeTypeSet;
    public int orientation;
    public List<ResultItem> selectedItems;
    public boolean showSingleMediaType;
    public int spanCount;
    @StyleRes
    public int themeId;
    public String thumbnailDir;
    public float thumbnailScale;

    private static final class InstanceHolder {
        private static SelectionSpec INSTANCE = new SelectionSpec();

        private InstanceHolder() {
        }
    }

    private SelectionSpec() {
        this.mimeTypeSet = MimeType.ofNormal();
        this.imageEngine = new a();
    }

    protected SelectionSpec(Parcel parcel) {
        boolean z;
        boolean z2 = true;
        this.mimeTypeSet = MimeType.ofNormal();
        this.imageEngine = new a();
        this.mediaTypeExclusive = parcel.readByte() != (byte) 0;
        if (parcel.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.showSingleMediaType = z;
        this.themeId = parcel.readInt();
        this.orientation = parcel.readInt();
        if (parcel.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.isNightMode = z;
        this.thumbnailDir = parcel.readString();
        if (parcel.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.countable = z;
        this.maxSelectable = parcel.readInt();
        if (parcel.readByte() == (byte) 0) {
            z2 = false;
        }
        this.capture = z2;
        this.spanCount = parcel.readInt();
        this.gridExpectedSize = parcel.readInt();
        this.thumbnailScale = parcel.readFloat();
        this.selectedItems = parcel.createTypedArrayList(ResultItem.CREATOR);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        parcel.writeByte((byte) (this.mediaTypeExclusive ? 1 : 0));
        if (this.showSingleMediaType) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        parcel.writeInt(this.themeId);
        parcel.writeInt(this.orientation);
        if (this.isNightMode) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        parcel.writeString(this.thumbnailDir);
        if (this.countable) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        parcel.writeInt(this.maxSelectable);
        if (!this.capture) {
            i3 = 0;
        }
        parcel.writeByte((byte) i3);
        parcel.writeInt(this.spanCount);
        parcel.writeInt(this.gridExpectedSize);
        parcel.writeFloat(this.thumbnailScale);
        parcel.writeTypedList(this.selectedItems);
    }

    public int describeContents() {
        return 0;
    }

    public static SelectionSpec getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public static SelectionSpec getCleanInstance() {
        SelectionSpec instance = getInstance();
        instance.reset();
        return instance;
    }

    public static void restoreSavedInstance(SelectionSpec selectionSpec) {
        InstanceHolder.INSTANCE = selectionSpec;
    }

    private void reset() {
        this.mimeTypeSet = null;
        this.mediaTypeExclusive = true;
        this.showSingleMediaType = false;
        this.themeId = this.isNightMode ? R.style.Matisse_Dracula : R.style.Matisse_Zhihu;
        this.isNightMode = false;
        this.thumbnailDir = null;
        this.orientation = 0;
        this.countable = false;
        this.maxSelectable = 1;
        this.filters = null;
        this.capture = false;
        this.spanCount = 3;
        this.gridExpectedSize = 0;
        this.thumbnailScale = 0.5f;
        if (this.selectedItems != null) {
            this.selectedItems = null;
        }
    }

    public boolean singleSelectionModeEnabled() {
        return !this.countable && this.maxSelectable == 1;
    }

    public boolean needOrientationRestriction() {
        return this.orientation != -1;
    }

    public boolean onlyShowImages() {
        return this.showSingleMediaType && MimeType.ofImage().containsAll(this.mimeTypeSet);
    }

    public boolean onlyShowVideos() {
        return this.showSingleMediaType && MimeType.ofVideo().containsAll(this.mimeTypeSet);
    }
}
